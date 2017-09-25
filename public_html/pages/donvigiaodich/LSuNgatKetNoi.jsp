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
  <html:form action="/LSuNgatKetNoiListAction.do" method="post">
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
              <fmt:message key="lsungatketnoi.title"/></span>
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
                <fmt:message key="lsungatketnoi.title.dieukien"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <!-- <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr >
              <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="lsungatketnoi.lable.makhbac"/>
            </td>
              <%-- lay ten va ma kho bac--%>
              <td width="30%" align="left" bordercolor="#e1e1e1" >
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
        <!--<html:hidden property="kb_id" styleId="kb_id" value=""/>
                
              </td>-->
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
          <html:select property="kb_id" styleId="kb_id" styleClass="fieldText"
                       style="width:160px;" onmouseover="Tip(this.value)"
                       onmouseout="UnTip()" disabled="true"></html:select>
        </td>
      </tr>
       
      <tr>
        <td align="right" bordercolor="#e1e1e1" width="7%">
          <fmt:message key="lsungatketnoi.lable.nguoi.ngat"/>
        </td>
        <td align="left" width="30%">
          <html:text property="ten_ng_ngat"
                     onkeypress="return blockKeySpecial001(event)"
                     onfocus="this.style.backgroundColor='#ffffb5'"
                     onblur="this.style.backgroundColor='#ffffff'" size="48%"
                     styleclass="promptText"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
        </td>
      </tr>
       
      <tr>
        <td align="right">
          <fmt:message key="lsungatketnoi.lable.tungay"/>
        </td>
        <td align="left" valign="middle">
          <html:text property="tu_ngay" styleId="tu_ngay"
                     styleClass="promptText" onmouseout="UnTip()"
                     onkeypress="dateBlockKey(event)"
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                     onfocus="textfocus(this);"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH:81%"/>
           
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
        </td>
        <td align="right">
          <fmt:message key="lsungatketnoi.lable.denngay"/>
        </td>
        <td align="left" valign="middle">
          <html:text property="den_ngay" styleId="den_ngay"
                     styleClass="promptText" onmouseout="UnTip()"
                     onkeypress="dateBlockKey(event)"
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');textlostfocus(this);"
                     onfocus="textfocus(this);"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH:81%"/>
           
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
            <button type="button" class="ButtonCommon" accesskey="i"
                    onclick="sbKTVTabmis('print')">
              <span class="sortKey">I</span>
              n
            </button>
             </span>
           
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
              <fmt:message key="donvigiaodich.moketnoi.title.ketqua"/>
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
                    <fmt:message key="lsungatketnoi.lable.makhbac"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.ten.khobac"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.tungay"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.denngay"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.ma.nhanvien"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.ten.nguoingat"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsungatketnoi.lable.lydongat"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="ListLSuNgat">
                  <logic:iterate id="LSuList" name="ListLSuNgat" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="4%">
                        <bean:write name="LSuList" property="ma"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="ten"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="tu_ngay"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="den_ngay"/>
                      </td>
                      <td align="left" width="8%">
                        <bean:write name="LSuList" property="ma_nsd"/>
                      </td>
                      <td align="left" width="13%">
                        <bean:write name="LSuList" property="ten_nsd"/>
                      </td>
                      <td align="left" width="30%">
                        <bean:write name="LSuList" property="lido_ngat"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
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

          if (type == 'find') {
              f.action = 'LSuNgatKetNoiListAction.do';
          }
          else if (type == 'print') {
              f.action = 'LSuNgatKetNoiPrintAction.do';
              var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
              newDialog = window.open('', '_form', params);
              f.target = '_form';
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