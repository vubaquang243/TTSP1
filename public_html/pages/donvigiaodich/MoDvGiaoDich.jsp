<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DonViGiaoDichResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/MoDvGiaoDichViewAction.do" method="post">
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
              <fmt:message key="donvigiaodich.moketnoi.title"/></span>
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
                <fmt:message key="donvigiaodich.moketnoi.title.thongtin"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
           
          <%-- Title hiển thị kho bạc và nhân viên--%>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <!-- <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="donvigiaodich.moketnoi.lable.list.makb"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
             
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
                           style="WIDTH: 250px; charset=utf-8 ;"/>-->
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
              <td align="right" width="15%">
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
              <!--<label id="lblNhkb_chuyen" onmouseout="UnTip()"  ></label>-->
              <!-- <html:hidden property="kb_id" styleId="kb_id" value=""/>-->
            </tr>
             
            <tr>
              <td align="right">
                <fmt:message key="donvigiaodich.moketnoi.lable.list.ngayngat"/>
              </td>
              <td width="15%" align="left" valign="middle">
                <html:text property="tu_ngay" styleId="tu_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:40%"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_ngat_btn" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "tu_ngay", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "ngay_ngat_btn"// id of the button
                  });
                </script>
                (dd/mm/yyyy)
              </td>
              <td align="right">
                <fmt:message key="donvigiaodich.moketnoi.lable.list.ngaynoi"/>
              </td>
              <td width="15%" align="left" valign="middle">
                <html:text property="den_ngay" styleId="den_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
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
                (dd/mm/yyyy)
              </td>
            </tr>
             
            <tr>
              <td colspan="4">
                <%-- 2 button ghi và thoát--%>
                <table class="buttonbar" border="0" cellspacing="0"
                       cellpadding="0" width="100%">
                  <tr>
                    <td align="center">
                      <span>
                        <button type="button" class="ButtonCommon"
                                onclick="bsubmit('find')" accesskey="o">
                          <span class="sortKey">T</span>
                          &igrave;m kiếm
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
              </td>
            </tr>
          </table>
          <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
           
          <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
           
          <font color="Red" dir="ltr">
            <fmt:message key="<%=StrStatus%>">
              <fmt:param>
                <%=id%>
              </fmt:param>
            </fmt:message>
          </font>
           
          <%}%>
          <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
                 bordercolor="#999999" width="100%">
            <tbody>
              <tr>
                <td class="title" colspan="6"
                    background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                    height="24">
                  <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                      <fmt:message key="donvigiaodich.moketnoi.title.ketqua"/>
                    </font></span>
                </td>
              </tr>
            </tbody>
             
            <tr>
              <td align="right">
                <table class="buttonbar" border="0" cellspacing="0"
                       cellpadding="0" width="100%">
                  <tr>
                    <td align="center">
                      <span>
                        <button type="button" onclick="bsubmit('save')"
                                accesskey="M" class="ButtonCommon">
                          <span class="sortKey">M</span>
                          ở giao dịch
                        </button></span>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
             
            <tr>
              <td colspan="2">
                <table class="navigateable focused" cellspacing="0"
                       cellpadding="1" bordercolor="#e1e1e1" border="1"
                       align="center" width="99.8%"
                       style="BORDER-COLLAPSE: collapse">
                  <thead>
                    <th class="promptText" height="22" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="donvigiaodich.moketnoi.lable.list.makb"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="donvigiaodich.moketnoi.lable.list.ngayngat"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="donvigiaodich.moketnoi.lable.list.ngaynoi"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="donvigiaodich.moketnoi.lable.list.lidongat"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="donvigiaodich.moketnoi.lable.list.moketnoi"/>
                         
                        <input type="checkbox" name="Checkall" value="yes"
                               onclick="Check(this)"></input>
                      </div>
                    </th>
                  </thead>
                   
                  <tbody class="navigateable focused" cellspacing="0"
                         cellpadding="1" bordercolor="#e1e1e1">
                    <logic:notEmpty name="listDVGD">
                      <logic:iterate id="KTVlist" name="listDVGD" indexId="stt">
                        <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                          <td align="center" width="5 %">
                            <bean:write name="KTVlist" property="ma"/>
                          </td>
                          <td>
                            <bean:write name="KTVlist" property="tu_ngay"/>
                          </td>
                          <td>
                            <bean:write name="KTVlist" property="den_ngay"/>
                          </td>
                          <td>
                            <bean:write name="KTVlist" property="lido_ngat"/>
                          </td>
                          <td align="center" width="7%">
                            <input type="CHECKBOX" name="check" id="checkbox"
                                   value='<bean:write name="KTVlist" property="kb_id"/> '/>
                          </td>
                        </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                  </tbody>
                </table>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <html:hidden property="trangthai"/>
  </html:form>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      checkstt();
      var ma_kb_tinh = jQuery("#ma_kb_tinh").val();
      if (ma_kb_tinh != '')
          danhsachkbhuyen(ma_kb_tinh);
  });

  function checkstt() {
      var f = document.forms[0];
      if (f.trangthai.value == "again") {
          if (confirm("Ghi thành công bạn có muốn dùng tiếp chức năng?")) {
              f.action = 'MoDvGiaoDichViewAction.do';
          }
          else {
              //parent.location
              f.action = 'mainAction.do';
              //newPopup(parent.location);
          }
          f.submit();
      }
      if (f.trangthai.value == "ton tai") {
          if (confirm("mã kho bạc đã bị ngắt kết nối từ trước, bạn muốn dung tiếp chức năng")) {
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
      if (type == 'find') {
          //      
          //        if (f.ma_kb.value.length < 4 && f.ma_kb.value.length >0 ) {
          //    
          //              alert('Mã kho bạc là mã 4 kí tự');
          //                    document.getElementById("ma_kb").focus();
          //                     return;
          //          }
          //          if (f.tu_ngay.value != null && f.den_ngay.value == null || f.tu_ngay.value != "" && f.den_ngay.value == "") {
          //              alert("Bạn phải nhập vào ô ngày nối");
          //              document.getElementById("den_ngay").focus();
          //              return;
          //          }
          //          if (f.tu_ngay.value == null && f.den_ngay.value != null || f.tu_ngay.value == "" && f.den_ngay.value != "") {
          //              alert("Bạn phải nhập vào ô ngày ngắt");
          //              document.getElementById("tu_ngay").focus();
          //              return;
          //          }
          if (CompareDate(f.tu_ngay.value, f.den_ngay.value) ==  - 1) {
              alert("Từ ngày phải là ngày trước đến ngày");
              document.getElementById("tu_ngay").focus();
              return;
          }
          if (CompareDate(f.tu_ngay.value, curr_date) ==  - 1) {
              alert('Từ ngày không được lớn hơn ngày hiện tại');
              document.getElementById("tu_ngay").focus();
              return;
          }

          if (d1 > d2) {
              alert("Ngày kết nối không được trước ngày ngắt");
              document.getElementById("den_ngay").focus();
              return;
          }
          else {
              f.action = 'MoDvGiaoDichViewAction.do';
          }
      }
      if (type == 'save') {
          f.action = 'MoDvGiaoDichAddExcAction.do';
          var checkb = document.getElementsByName('check');
          var check = 0;
          for (var i = 0;i < checkb.length;i++) {
              if (checkb[i].checked) {
                  check = 1;
                  break;
              }
          }
          if (check == 0) {
              alert("Bạn phải tích ít nhất 1 đơn vị")
              return;
          }
      }

      f.submit();
  }

  function Check(obj) {
      //if (obj.checked){
      var checkObjs = document.getElementsByName('check');
      for (i = 0;i < checkObjs.length;i++) {
          checkObjs[i].checked = obj.checked;
      }
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
                  if ('<%=request.getParameter("kb_id")%>' != null && '<%=request.getParameter("kb_id")%>' != '') {
                      jQuery("#kb_id").val('<%=request.getParameter("kb_id")%>')
                      jQuery("#kb_id").attr( {
                          disabled : false
                      });
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