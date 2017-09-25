<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
<fmt:setBundle basename="com.seatech.ttsp.resource.ReportsResource"/>
<title>Bảng tổng hợp lệnh thanh to&aacute;n</title>
<%
String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
jQuery(document).init(function () {
      getTenKhoBac();
      getTenNHang();
  });
  //************************************ LOAD PAGE **********************************
  function formAction() {
      var f = document.forms[0];
      f.target='';
      var loai_lenh_field = jQuery("#loai_lenh").val();
      var nhkb_tinh=jQuery('#nhkb_tinh').val();
      var nhkb_huyen=jQuery('#nhkb_huyen').val();
      
      if(''==nhkb_tinh || nhkb_tinh==null){
        alert(GetUnicode('C&#7847;n ch&#7885;n th&#244;ng tin KBNN t&#7881;nh.'));
        return false;
      }
      else if( ''==nhkb_huyen||nhkb_huyen==null){
        alert(GetUnicode('C&#7847;n ch&#7885;n th&#244;ng tin KBNN huy&#7879;n.'));
        return false;
      }
      else if(''!=nhkb_tinh && nhkb_tinh!=null && ''!=nhkb_huyen && nhkb_huyen!=null){  
            if (loai_lenh_field == 'LTT.DI') {
                f.action = "bkeLTTRptAction.do";
            }
            else {
                f.action = "bkeLTTDenRptAction.do";
            }  
            var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
            newDialog = window.open('', '_form', params);
            f.target = '_form';            
            f.submit();
      }
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form styleId="frmBCBKLTT" action="/bkeLTTRptAction.do">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">
            <fmt:message key="reports.bkltt.di.title"/></span>
        </td>
        <td width="62">
          <img width="62" height="30"
               src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img>
        </td>
        <td width="20%"
            background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4">
          <img width="4" height="30"
               src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
        </td>
      </tr>
    </tbody>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="2" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="reports.page.dieukien"/>
        </td>
      </tr>
      <tr>
        <td width="20%" align="right" bordercolor="#e1e1e1">KBNN tỉnh:&nbsp;</td>
        <td width="25%" bordercolor="#e1e1e1">
          <html:select property="nhkb_tinh" styleId="nhkb_tinh"
                       onchange="getTenKhoBac()"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <%if(request.getAttribute("dftinh") != null){
                           %>
            <html:option value="">-----Chọn th&ocirc;ng tin KBNN tỉnh-----</html:option>
            <%}%>
            <html:optionsCollection name="dmuckb_tinh" value="id_cha"
                                    label="kb_tinh"/>
          </html:select>
        </td>
        <td width="15%" align="right" bordercolor="#e1e1e1">KBNN huyện:&nbsp;</td>
        <td bordercolor="#e1e1e1">
          <html:select property="nhkb_huyen" styleId="nhkb_huyen"
                       onchange="nhkb_huyenval(); getTenNHang();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <html:option value="">-----Chọn th&ocirc;ng tin KBNN huyện-----</html:option>
          </html:select>
        </td>
        </tr>
        <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">Loại lệnh :</td>
        <td align="left" bordercolor="#e1e1e1">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        styleClass="fieldText"
                       style="width:145px;height:20px;" property="loai_lenh" onchange="getLTT();"
                       styleId="loai_lenh">
            <html:option value="LTT.DI">
              <fmt:message key="reports.page.selectbox.di"/>
            </html:option>
            <html:option value="LTT.DEN">
              <fmt:message key="reports.page.selectbox.den"/>
            </html:option>
          </html:select>
        </td>
        <td width="15%" align="right" bordercolor="#e1e1e1">Ng&acirc;n
                                                            h&agrave;ng: &nbsp;</td>
        <td bordercolor="#e1e1e1">
          <html:select property="ngan_hang" styleId="ngan_hang"
                       onchange="nhangval()"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <html:option value="">-----Chọn th&ocirc;n tin ng&acirc;n
                                  h&agrave;ng-----</html:option>
            <%-- <html:optionsCollection name="dmucNH" value="ma_nh"
                 label="ten_nh"/>--%>
          </html:select>
          <html:hidden property="ma_ngan_hang" styleId="ma_ngan_hang"/> 
        </td>
      </tr>
      <tr>
        <td align="right" width="15%" bordercolor="#e1e1e1">
          <label for="tu_ngay">
            <fmt:message key="reports.page.lable.tu_ngay"/>
          </label>
        </td>
        <td bordercolor="#e1e1e1">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      style="WIDTH: 70px;vertical-align:middle;"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" bordercolor="#e1e1e1">
          Loại tiền
        </td>
        <td align="left" bordercolor="#e1e1e1">
          <html:select property="loai_tien" styleId="loai_tien"
                       onchange=""
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <%--<html:option value="177">VND</html:option>--%>
            <%-- <html:optionsCollection name="dmucNH" value="ma_nh"
                 label="ten_nh"/>--%>
          </html:select>
          
        </td>
      </tr>
      <tr>
      <td bordercolor="#e1e1e1"></td><td bordercolor="#e1e1e1"></td>
      <td align="right" bordercolor="#e1e1e1">
          S&#7855;p x&#7871;p theo:
          </td>
        <td align="left" bordercolor="#e1e1e1">
          <div id="chk1" style="float:left;"><input type="radio" id="chkbox1" name="dk_loc" value="tien" checked="checked"> S&#7889; ti&#7873;n  &nbsp; &nbsp;&nbsp;&nbsp;</div>
          <div id="chk2" style="float:left;"><input type="radio" id="chkbox2" name="dk_loc" value="ma"/> KTV TABMIS - S&#7889; YCTT  &nbsp;</div>
          <div id="chk3" style="float:left;"><input type="radio" id="chkbox3" name="dk_loc" value="ma"/> S&#7889; LTT &nbsp; &nbsp;&nbsp;&nbsp;</div>  &nbsp;
          
        </td>
      </tr>
      <tr>
        <td colspan="4" align="center" valign="top" bordercolor="#e1e1e1">
          <div style="padding:10px 0px 10px 0px;vertical-align:top; ">
            <button type="button"  onclick="formAction();"
                    accesskey="i" class="ButtonCommon">
              <fmt:message key="reports.page.button.in"/>
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
  function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen = document.getElementById("nhkb_huyen").value;
      return nhkb_huyen;
  }

  function nhangval() {
      var ngan_hang;
      selectNH = document.getElementById("ngan_hang");
      ngan_hang = selectNH.value; 
      valueSelected = selectNH.options[selectNH.selectedIndex].value;
      jQuery("#ma_ngan_hang").val(valueSelected);
      return ngan_hang;
  }
  getLTT();
    function getLTT() {
      var loai_lenh_field = jQuery("#loai_lenh").val();
      if(loai_lenh_field=='LTT.DEN'){
      jQuery('#chkbox1').attr("checked", "checked");
          document.getElementById("chk1").show();
          document.getElementById("chk2").hide();
          document.getElementById("chk3").show();

      }
      if(loai_lenh_field=='LTT.DI'){
      jQuery('#chkbox1').attr("checked", "checked");
          document.getElementById("chk1").show();
          document.getElementById("chk2").show();
          document.getElementById("chk3").hide();
      }   
  }

  function getTenKhoBac() {
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
      document.getElementById('loai_tien').options.length = 0;// clear du lieu option cu
      var kb_id = document.forms[0].nhkb_tinh.value;

      var kb_huyen = "<%=kb_huyen%>";
      var strTinh = "<%=strTinh%>";
      if (kb_id == null || "" == kb_id) {
          document.getElementById('ngan_hang').options.length = 1;
      }
      else if (kb_id != null && "" != kb_id) {
          jQuery.ajax( {
              type : "POST", url : "getDMucKBLTT.do", data :  {
                  "kb_id" : kb_id
              },
              success : function (data, textstatus) {
                  if (textstatus != null && textstatus == 'success') {
                      if (data != null) {
                          var objectDMHTKB = new Object();
                          var objectDMTien = new Object();
                        
                          objectDMHTKB = JSON.parse(data[0]);
                          objectDMTien = JSON.parse(data[1]);
                          
                          for (var i = 0;i < objectDMHTKB.length;i++) {
                            document.getElementById('nhkb_huyen').options.add(new Option(objectDMHTKB[i].kb_huyen, objectDMHTKB[i].id));
                          }
                          for (var i = 0;i < objectDMTien.length;i++) {
                            document.getElementById('loai_tien').options.add(new Option(objectDMTien[i].ma, objectDMTien[i].id));
                          }
//                          jQuery.each(data, function (i, objectDM) {
//                              // truong hop 1 - luc load khong co thang nao    
//                              objectDMHTKB = objectDM
//                              document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
//                          });
                          if (strTinh == null || strTinh == '') {
                              // request set dftinh ==null
                              if (document.getElementById('nhkb_huyen').options.length == 2) {
                                  jQuery("#nhkb_huyen option:eq(0)").remove();
                                  getTenNHang();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('nhkb_huyen').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                                  getTenNHang();
                              }
                              else if (kb_huyen == '0' || kb_huyen == null || '' == kb_huyen) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                                  getTenNHang();
                              }
                              else if (kb_huyen != '0') {
                                  jQuery('#nhkb_huyen option:eq(' + kb_huyen + ')').attr('selected', true);
                                  getTenNHang();
                              }
                          }
                      }
                  }

              },
              error : function (textstatus) {
                  alert(textstatus);
              }
          });
      }
  }

      function getTenNHang() {
          document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
          var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
          var strTinh = "<%=strTinh%>";
          var ngan_hang = "<%=ngan_hang%>";
          if (nhkb_id != null && "" != nhkb_id) {
              jQuery.ajax( {
                  type : "POST", url : "getDMucNHLTT.do", data :  {
                      "nhkb_id" : nhkb_id
                  },
                  success : function (data, textstatus) {
                      if (textstatus != null && textstatus == 'success') {
                          if (data != null) {
                              jQuery.each(data, function (i, objectDM) {
                                  document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh));
                              });
                          }
                          if (strTinh == null || strTinh == '') {
                              // request set dftinh ==null
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  jQuery("#ngan_hang option:eq(0)").remove();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                              }
                              else if (ngan_hang == '0' || ngan_hang == null || '' == ngan_hang) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                              }
                              else if (ngan_hang != '0') {
                                  jQuery('#ngan_hang option:eq(' + ngan_hang + ')').attr('selected', true);
                              }
                          }
                      }
                  },
                  error : function (textstatus) {
                      alert(textstatus);
                  }
              });
          }
      }
</script>