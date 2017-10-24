<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.COTResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/tracuucot.do" method="post">
    <html:hidden property="ma_nh_kb" styleId="ma_nh_kb"/>
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
              <fmt:message key="cot_kbnn.page.title.tracuu_cot"/></span>
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
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="cot_kbnn.page.title.dieukien"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td align="right" width="20%">
          <label for="kb_tinh">
            <fmt:message key="cot_kbnn.page.lable.kb_tinh"/>
          </label>
        </td>
        <td width="20%">
          <html:select property="id_cha" styleId="ma_kb_tinh"
                       styleClass="fieldText" style="width:160px;"
                       onmouseover="Tip(this.value)" onmouseout="UnTip()">
            <html:option value="">-----Chọn KB tỉnh-----</html:option>
            <html:optionsCollection name="lstKBTinh" value="id_cha"
                                    label="kb_tinh"/>
          </html:select>
        </td>
        <td align="right" width="20%">
          <label for="kb_huyen">
            <fmt:message key="cot_kbnn.page.lable.kb_huyen"/>
          </label>
        </td>
        <td>
          <html:select property="kb_id" styleId="kb_id" styleClass="fieldText"
                       style="width:160px;" onmouseover="Tip(this.value)"
                       onmouseout="UnTip()" disabled="true"
                       onchange="getTenNHang();"></html:select>
        </td>
      </tr>
       
      <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">
          <fmt:message key="cot_kbnn.page.lable.ngan_hang"/>&nbsp;
        </td>
        <td>
          <html:select property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:160px;"
                       onmouseover="Tip(this.value)" onmouseout="UnTip()"
                       disabled="true"></html:select>
        </td>
      </tr>
       
      <tr>
        <td align="right">
          <fmt:message key="cot_kbnn.page.lable.tu_ngay"/>
        </td>
        <td align="left" valign="middle">
          <html:text property="tu_ngay_gd" styleId="tu_ngay_gd"
                     styleClass="promptText" onmouseout="UnTip()"
                     onkeypress="dateBlockKey(event)"
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_gd');textlostfocus(this);"
                     onfocus="textfocus(this);"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH:81%"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_gd_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay_gd", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_gd_btn"// id of the button
            });
          </script>
        </td>
        <td align="right">
          <fmt:message key="cot_kbnn.page.lable.den_ngay"/>
        </td>
        <td align="left" valign="middle">
          <html:text property="den_ngay_gd" styleId="den_ngay_gd"
                     styleClass="promptText" onmouseout="UnTip()"
                     onkeypress="dateBlockKey(event)"
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_gd');textlostfocus(this);"
                     onfocus="textfocus(this);"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_gd_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "den_ngay_gd", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "den_ngay_gd_btn"// id of the button
            });
          </script>
        </td>
      </tr>
    </table>
    <br/>
    <%-- ************************************--%>
    <%-- 2 nút, tra cứu , thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0"
           width="100%">
      <tr>
        <td align="center">
          <span>
            <button type="button" class="ButtonCommon" accesskey="t"
                    onclick="sbKTVTabmis('find')">
              <span class="sortKey">T</span>
              ra cứu
            </button></span>
           
          <span> 
            <button type="button" onclick="sbKTVTabmis('close')"
                    class="ButtonCommon" accesskey="o">
              Th
              <span class="sortKey">o</span>
              &aacute;t
            </button>
             </span>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
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
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="cot_kbnn.page.title.ketqua"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.ma_kb"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.ten_kb"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.ma_nh"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.ten_nh"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.ngay_gd"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.time_cot"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="cot_kbnn.page.lable.so_yc"/>
                  </div>
                </th>
              </thead>               
              <%
              com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
              int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstCOT">
                  <logic:iterate id="itemCOT" name="lstCOT" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center" width="4%">
                        <bean:write name="itemCOT" property="ma_nh_kb"/>
                      </td>
                      <td align="left" width="25%">
                        <bean:write name="itemCOT" property="ten_nh_kb"/>
                      </td>
                      <td align="center" width="15%">
                        <bean:write name="itemCOT" property="ma_nh"/>
                      </td>
                      <td align="left" width="25%">
                        <bean:write name="itemCOT" property="ten_nh"/>
                      </td>
                      <td align="center" width="8%">
                        <bean:write name="itemCOT" property="ngay_gd"/>
                      </td>
                      <td align="center" width="13%">
                        <bean:write name="itemCOT" property="cut_of_time"/>
                      </td>
                      <td align="center">
                        <bean:write name="itemCOT" property="so_yc_cot"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                 <logic:empty name="lstCOT">
                <tr>
                <td colspan="10">
                  
                </td>
                </tr>
               </logic:empty> 
                <tr>
                  <td colspan="8">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                  </td>
                </tr>
              </tbody>               
              <html:hidden property="pageNumber" value="1"/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      var ma_kb_tinh = jQuery("#ma_kb_tinh").val();
      if (ma_kb_tinh != '')
          danhsachkbhuyen(ma_kb_tinh);
  });

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function sbKTVTabmis(type) {
      var curr_date = showNowDate();
      var f = document.forms[0];
      f.target = '';
      if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit();
      }
      else {
          if (f.ma_kb_tinh.value!=null && f.ma_kb_tinh.value!=''){
            if (f.kb_id.value==null || f.kb_id.value==''){
              alert("Bạn chọn kho bạc huyện");
              document.getElementById("kb_id").focus();
              return;
            }
          }
          if (CompareDate(f.tu_ngay_gd.value, f.den_ngay_gd.value) ==  - 1) {
              alert("Từ ngày phải là ngày trước đến ngày");
              document.getElementById("tu_ngay").focus();
              return;
          }
          if (CompareDate(f.tu_ngay_gd.value, curr_date) ==  - 1) {
              alert('Từ ngày không được lớn hơn ngày hiện tại');
              document.getElementById("tu_ngay").focus();
              return;
          }

          if (type == 'find') {
              f.action = 'tracuucot.do';
          }
          f.submit();

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
      jQuery("#ma_nh").html('');
          jQuery("#ma_nh").attr( {disabled : true});
      if (ma_kb_tinh == '') {
          jQuery("#kb_id").html('');          
          jQuery("#kb_id").attr( {disabled : true});
          jQuery("#ma_nh_kb").val('');
      }
      else {
          jQuery("#kb_id").attr( {disabled : false});
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
                      options[i + 1] = new Option(kbhuyen.kb_huyen, kbhuyen.id + '-' + kbhuyen.ma);
                  });
                  if ('<%=request.getParameter("kb_id")%>' != null && '<%=request.getParameter("kb_id")%>' != '') {
                      jQuery("#kb_id").val('<%=request.getParameter("kb_id")%>')
                      jQuery("#kb_id").attr( {
                          disabled : false
                      });
                      getTenNHang();
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

      function getTenNHang() {

          var nhkb_id = jQuery("#kb_id").val();
          var arrKB = nhkb_id.split('-');
          jQuery("#ma_nh_kb").val(arrKB[1]);
          if (nhkb_id != null && "" != nhkb_id) {
              jQuery.ajax( {
                  type : "POST", url : "getDMucNHang.do", data :  {
                      "nhkb_id" : arrKB[0]
                  },
                  success : function (data, textstatus) {
                      if (textstatus != null && textstatus == 'success') {
                          if (data != null) {
                              var ma_nh = jQuery("#ma_nh");
                              var options = ma_nh.attr('options');
                              jQuery("#ma_nh").html('');
                              jQuery.each(data, function (i, objectDM) {
                                  options[i] = new Option(objectDM.ten, objectDM.ma_nh);
                              });
                              if ('<%=request.getParameter("ma_nh")%>' != null && '<%=request.getParameter("ma_nh")%>' != '') {
                                  jQuery("#ma_nh").val('<%=request.getParameter("ma_nh")%>')
                                  jQuery("#ma_nh").attr( {
                                      disabled : false
                                  });
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
<%@ include file="/includes/ttsp_bottom.inc"%>