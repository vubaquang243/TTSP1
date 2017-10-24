<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DonViGiaoDichResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/NgatDvGiaoDichAddExcAction.do" method="post">
    <%-- Title--%>
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="donvigiaodich.ngatketnoi.title"/></span>
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
    <%-- Title NSD--%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="donvigiaodich.ngatketnoi.title.thongtin"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
           
          <%-- Title hiển thị kho bạc và nhân viên--%>
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td align="right" width="15%">
                <label for="kb_tinh">
                  <fmt:message key="donvigiaodich.ngatketnoi.lable.kb_tinh"/>
                </label>
              </td>
              <td width="17%">
                <html:select property="id_cha" styleId="ma_kb_tinh"
                             styleClass="fieldText" style="width:160px;"
                             onmouseover="Tip(this.value)" onmouseout="UnTip()">
                  <html:option value="">-----Chọn KB tỉnh-----</html:option>
                  <html:optionsCollection name="lstKBTinh" value="id_cha"
                                          label="kb_tinh"/>
                </html:select>
              </td>
              <td align="right">
                <label for="kb_huyen">
                  <fmt:message key="donvigiaodich.ngatketnoi.lable.kb_huyen"/>
                </label>
              </td>
              <td>
                <html:select property="kb_id" styleId="kb_id"
                             styleClass="fieldText" style="width:160px;"
                             onmouseover="Tip(this.value)" onmouseout="UnTip()"
                             disabled="true"></html:select>
              </td>
              <!-- <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="donvigiaodich.ngatketnoi.lable.list.makb"/>
                 :
              </td>
              <td width="17%" align="left" bordercolor="#e1e1e1">
              
             
                  <html:text property="ma_kb" styleId="ma_kb" maxlength="4"
                           size="4%"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="textfocus(this);" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           styleclass="promptText" indexed="ma_kb"
                           onblur="getTenKhoBac('ma_kb', 'ten_kb', 'kb_id','addNSDLoadDMKBAction.do');textlostfocus(this);"/>&nbsp; 
                <html:text property="ten_kb" readonly="true" styleId="ten_kb"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 168px;"/>-->
              <!--<label id="lblNhkb_chuyen" onmouseout="UnTip()"  ></label>-->
              <!--<html:hidden property="kb_id" styleId="kb_id" value=""/>-->
            </tr>
             
            <tr>
              <td align="right" width="7%">
                <fmt:message key="donvigiaodich.ngatketnoi.lable.list.ngayngat"/>
                 :
              </td>
              <td width="30%" align="left" valign="middle">
                <html:text property="tu_ngay" styleId="tu_ngay" readonly="true"
                           styleClass="promptText" onmouseout="UnTip()"
                           onfocus="textfocus(this);"
                           onkeypress="dateBlockKey(event)"
                           onblur="textlostfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:40%"/>
              </td>
            </tr>
             
            <tr>
              <td align="right" bordercolor="#e1e1e1">
                <fmt:message key="donvigiaodich.ngatketnoi.lable.list.lidongat"/>
                 :
              </td>
              <td align="left" bordercolor="#e1e1e1">
                <html:textarea property="lido_ngat" value="" styleId="ma"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               size="40%"
                               onkeypress="return blockKeySpecial001(event)"
                               onblur="this.style.backgroundColor='#ffffff'"
                               styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td align="right">
                <fmt:message key="donvigiaodich.ngatketnoi.lable.list.ngaynoi"/>
                 :
              </td>
              <td align="left" valign="middle">
                <html:text property="den_ngay" styleId="den_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onfocus="textfocus(this);"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:40%"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_noi_btn" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "den_ngay", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "ngay_noi_btn"// id of the button
                  });
                </script>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- 2 button ghi và thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0"
           width="100%">
      <tr>
        <td align="center">
          <span>
            <button type="button" onclick="bsubmit('save')" accesskey="G"
                    class="ButtonCommon">
              <span class="sortKey">N</span>
              gắt giao dịch
            </button></span>
           
          <span> 
            <button type="button" onclick="bsubmit('close')"
                    class="ButtonCommon" accesskey="o">
              Th
              <span class="sortKey">o</span>
              &aacute;t
            </button>
             </span>
        </td>
      </tr>
    </table>
    <html:hidden property="trangthai"/>
  </html:form>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#ma_kb').focus();
      checkstt();
      time();
      var ma_kb_tinh = jQuery("#ma_kb_tinh").val();
      if (ma_kb_tinh != '')
          danhsachkbhuyen(ma_kb_tinh);
        
  });

  function time() {
      var f = document.forms[0];
      var currentTime = new Date()
      var month = currentTime.getMonth() + 1;
      var day = currentTime.getDate();
      var year = currentTime.getFullYear();
      if (day < 10) {
          f.tu_ngay.value = "0" + day + "/" + month + "/" + year;
      }
      else {
          f.tu_ngay.value = day + "/" + month + "/" + year;
      }
      if (month < 10) {
          f.tu_ngay.value = day + "/" + "0" + month + "/" + year;
      }
      else {
          f.tu_ngay.value = day + "/" + month + "/" + year;
      }
  }

  function checkstt() {

      var f = document.forms[0];

      if (f.trangthai.value == "again") {
          if (confirm("Ghi thành công, bạn có muốn dùng tiếp chức năng?")) {
              return;
          }
          else {
              //parent.location
              f.action = 'mainAction.do';
              //newPopup(parent.location);
          }
          f.submit();
      }
      if (f.trangthai.value == "ton tai") {
          if (confirm("Đơn vị đã bị ngắt, bạn có muốn dùng tiếp chức năng này ?")) {
              return;
          }
          else {
              //parent.location
              f.action = 'mainAction.do';
              //newPopup(parent.location);
          }
          f.submit();
      }
  }

  function bsubmit(type) {
      var f = document.forms[0];
      var d1 = Date.parse(f.tu_ngay.value);
      var d2 = Date.parse(f.den_ngay.value);

      var curr_date = showNowDate();
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      if (type == 'save') {
          if (f.lido_ngat.value.length >= 100) {
              alert("Lý do ngắt không được quá 100 kí tự");
              return;
          }
          if (d1 > d2) {
              alert("Ngày kết nối không được trước ngày ngắt");
              document.getElementById("den_ngay").focus();
              return;
          }
          if (CompareDate(f.tu_ngay.value, curr_date) ==  - 1) {
              alert('Ngày ngắt không được lớn hon ngày hiện tại');
              document.getElementById("tu_ngay").focus();
              return;
          }

          if (f.tu_ngay.value == null && f.den_ngay.value != null || f.tu_ngay.value == "" && f.den_ngay.value != "") {
              alert("Bạn phải nhập vào ô từ ngày");
              document.getElementById("tu_ngay").focus();
              return;
          }
          if (f.id_cha.value == null || f.id_cha.value == "") {
              alert("Bạn chọn KB tỉnh");
              document.getElementById("id_cha").focus();
              return;
          }
          if (f.kb_id.value == null || f.kb_id.value == "") {
              alert("Bạn chọn KB huyện");
              document.getElementById("kb_id").focus();
              return;
          }
          else {
              if (confirm("Bạn chắc chắn ngắt đơn vị giao dịch này ?")) {
                  f.action = 'NgatDvGiaoDichAddExcAction.do';
              }
              else {
                  return;
              }
          }

      }
      f.submit();
  }

  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }

  /* List daanh sach kho bac huyen*/
  jQuery("#ma_kb_tinh").change(function () {
      var ma_kb_tinh = jQuery("#ma_kb_tinh").val();
      if (ma_kb_tinh == '') {
          jQuery("#kb_id").html('');
          jQuery("#kb_id").attr( {
              disabled : true
          });
      }
      else {
          jQuery("#kb_id").attr( {
              disabled : false
          });
          danhsachkbhuyen(ma_kb_tinh);
      }
  });

  function danhsachkbhuyen(ma_kb_tinh) {
      jQuery.ajax( {
          type : "POST", url : "kbhuyenlist.do", data :  {
              "kb_id" : ma_kb_tinh, "nd" : Math.random() * 100000
          },
          dataType : 'json', success : function (data, textstatus) {
              if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                  var ma_kb_huyen = jQuery("#kb_id");
                  var options = ma_kb_huyen.attr('options');
                  jQuery("#kb_id").html('');
                  options[0] = new Option('--- Chọn KB huyện----', '')
                  jQuery.each(data, function (i, kbhuyen) {
                      options[i + 1] = new Option(kbhuyen.kb_huyen, kbhuyen.id);
                  });
                  if('<%=request.getParameter("kb_id")%>'!=null && '<%=request.getParameter("kb_id")%>'!=''){
                     jQuery("#kb_id").val('<%=request.getParameter("kb_id")%>')
                     jQuery("#kb_id").attr( {disabled : false});
                  }
              }
          },
          error : function (xhr, status, error) {
              focus_field.val(status);
              dialog_message.html(status + xhr.responseText);
              dialog_message.dialog("open");
          }
      });
  }
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>