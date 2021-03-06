<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
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
        type = "text/javascript" > 
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/utils.js"
        type = "text/javascript" > 
</script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  function goPage(page) {
      jQuery("#pageNumber").val(page);
      var f = document.forms[0];
      f.action = 'listSoDuAction.do?pageNumber=' + page;
      jQuery("#TCuuDMuc").submit();
  }

  function check(type) {
      var f = document.forms[0];

      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else if (type == 'add') {
          f.action = 'addSoDuAction.do';
      }
      f.submit();
  }

  jQuery(document).ready(function () {
      jQuery('#ma_kb').focus();
  });

  
  function checkKey123(txt_id) {
      var e = window.event;
      var keyCode = e.keyCode || e.charCode, arrow = {
          backspace : 8, del : 46, left : 37, right : 39, up : 38, down : 40, enter : 13, esc : 27
      };
      //var matches = /^\$?(\d{1,3},?(\d{3},?)*\d{3}(\.\d{0,2})?|\d{1,3}(\.\d{0,2})?|\.\d{1,2}?)/; [^\-0-9]
      var matches = /^[-]?\d*\.?\d*$/;
      var need_match = jQuery("#" + txt_id + "").val();
      if (!matches.test(need_match)) {
          jQuery("#" + txt_id + "").val('');
      }
      var count_dot = jQuery("#" + txt_id + "").val().split(".")
      if (count_dot.length > 1) {
          if (count_dot[1].length > 2) {
              jQuery("#" + txt_id + "").val('');
          }
      }
  }

  
  function changeValue(txt_id, allowNegativeNumber) {  
      var value = jQuery("#"+txt_id +"").val().replace(/\s/g,"");
      var loai_tien = jQuery("#loai_tien").val();
      
      if(allowNegativeNumber == undefined){
        allowNegativeNumber = false;
      }
        
        if(loai_tien == "VND"){
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'VND', allowNegativeNumber));
        }else{
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'USD', allowNegativeNumber));
        }

  }

  
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="listSoDuAction" method="post" styleId="TCuuDMuc">
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
            <span class="title2">Số dư </span>
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
    <logic:notEmpty name="SoDuForm" property="action_status">
      <font color="Red">
        <bean:write name="SoDuForm" property="action_status"/>
      </font>
    </logic:notEmpty>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Điều kiện
                                                             t&igrave;m kiếm</font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td align="right" width="10%">M&atilde; kho bạc</td>
              <td align="left" width="20%">
                <html:text property="ma_kb" styleId="ma_kb" size="20%"
                           onkeypress="return numberBlockKey(event)"
                           maxlength="8"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="getTenNganHang('ma_kb', 'ten_nhkb_nhan', 'id_nhkb_nhan');textlostfocus(this); this.style.backgroundColor='#ffffff';"
                           styleClass="promptText"></html:text>
              </td>
              <td width="40%" align="left">
              <html:text property="ten_nhkb_nhan" readonly="true" styleId="ten_nhkb_nhan"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                 
              <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
              </td>
              <td align="right" width="10%">Số dư</td>
              <td align="left" width="20%">
                <html:text property="so_du" styleId="so_du" size="20%"
                           maxlength="200"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="if (this.value !='') {changeValue('so_du', true);};textlostfocus(this); this.style.backgroundColor='#ffffff';" 
                          onkeypress="return numbersonly2(event,false) "
                           styleClass="promptText"
                           style="text-align:right"></html:text>
              </td>
             
             
            </tr>
             
            <tr>
               <td align="right" >M&atilde; ng&acirc;n h&agrave;ng</td>
              <td>
                <html:text property="ma_nh" styleId="ma_nh" size="20%"
                           maxlength="8"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onkeypress="return numberBlockKey(event)"
                           onblur="getTenNganHang('ma_nh', 'ten_nhkb_nhan1', 'id_nhkb_nhan');textlostfocus(this); this.style.backgroundColor='#ffffff';"
                           styleClass="promptText"></html:text>
              </td>
              <td>
              <html:text property="ten_nhkb_nhan1" readonly="true" styleId="ten_nhkb_nhan1"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                 
              <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
              </td>
              <td align="right" >Số dư COT</td>
              <td>
                <html:text property="so_du_cot" styleId="so_du_cot" size="20%"
                           maxlength="200"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff';textlostfocus(this);if (this.value !='') {changeValue('so_du_cot');}"
                           onkeypress="return numbersonly2(event,true) "
                           styleClass="promptText"
                           style="text-align:right"></html:text>
              </td>
              
            </tr>
            <tr>
             <td align="right">Ng&agrave;y giao dịch</td>
              <td align="left" valign="middle">
                <html:text property="ngay_gd" styleId="ngay_gd"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_gd');textlostfocus(this);"
                           onfocus="textfocus(this);" style="WIDTH:60%"
                           value='<%=request.getParameter("ngay_gd")%>'/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "ngay_gd", ifFormat : "%d/%m/%Y", button : "ngay"
                  });
                </script>
              </td>
              <td>
              </td>
              <td align="right">Loại tiền</td>
              <td >
                <html:select styleClass="selectBox" property="loai_tien"
                             styleId="loai_tien" style="width:125px;height:20px"
                             onchange="changeValue('so_du_cot');changeValue('so_du');">
                  <html:option value="VND">VND</html:option>
                  <html:optionsCollection value="ma" label="ma"
                                          name="lstLoaiTien"/>
                </html:select>
              </td>
            </tr>
            <tr>
            <td align="right"> 
              Loại tài khoản
            </td>
            <td>
                <html:select styleClass="selectBox" property="loai_tk"
                                           styleId="loai_tk" style="width:125px;height:20px">
                        <html:option value="00">Chọn loại tài khoản</html:option>                   
                        <html:option value="01">Thanh toán tổng hợp</html:option>
                        <html:option value="02">Thanh toán</html:option>
                        <html:option value="03">Chuyên thu</html:option>
                    </html:select>
            
            <td align="right">
           
            </td>
            <td align="left" width="10%">
               
            </td>
            
        </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tracuu">
            <button type="button" onclick="check('find')" class="ButtonCommon"
                    accesskey="t">
              <span class="sortKey">T</span>
              ra cứu
            </button></span>
           
          <span id="them"> 
            <button type="button" onclick="check('add')" class="ButtonCommon"
                    accesskey="i">
              Th&ecirc;
              <span class="sortKey">m</span>
              mới
            </button>
             </span>
           
          <span id="thoat"> 
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Th
              <span class="sortKey">o</span>
              &aacute;t
            </button>
             </span>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Kết quả t&igrave;m kiếm</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
                   width="100%">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">STT</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">M&atilde; kho bạc</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">M&atilde; ng&acirc;n h&agrave;ng</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ng&agrave;y giao dịch</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Số dư</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ng&agrave;y th&ecirc;m mới</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Số dư COT</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Loại tiền</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Loại tài khoản</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Sửa</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">X&oacute;a</div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
         int rowBegin = (pagingBean.getCurrentPage() -1) * 20;
      %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:empty name="listSoDu">
                  <tr>
                    <td colspan="10">
                      <font color="Red">Kh&ocirc;ng c&oacute; kết quả t&igrave;m
                                        kiếm</font>
                    </td>
                  </tr>
                </logic:empty>
                <logic:notEmpty name="listSoDu">
                  <logic:iterate id="items" indexId="index" name="listSoDu">
                    <tr id='row_dts_<bean:write name="index"/>'
                        class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                        onmouseout="window.status =''">
                      <td width="70" align="center">
                        <%=index+1+rowBegin%>
                      </td>
                      <td width="70" align="center"
                          onmouseover="style.fontWeight='bold'"
                          onmouseout="style.fontWeight='normal'">
                        <bean:write name="items" property="ma_kb"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="ma_nh"/>
                      </td>
                      <td align="70">
                        <bean:write name="items" property="ngay_gd"/>
                      </td>
                      <td align="60">
                        <logic:equal property="loai_tien" name="items"
                                     value="VND">
                          <fmt:setLocale value="vi_VI"/>
                          <fmt:formatNumber type="currency"
                                            maxFractionDigits="0"
                                            currencySymbol="">
                            <bean:write name="items" property="so_du"/>
                          </fmt:formatNumber>
                        </logic:equal>
                         
                        <logic:notEqual property="loai_tien" name="items"
                                        value="VND">
                          <fmt:setLocale value="vi_VI"/>
                          <fmt:formatNumber type="currency" currencySymbol="">
                            <bean:write name="items" property="so_du"/>
                          </fmt:formatNumber>
                        </logic:notEqual>
                      </td>
                      <td align="60">
                        <bean:write name="items" property="insert_date"/>
                      </td>
                      <td align="60">
                        <logic:equal property="loai_tien" name="items"
                                     value="VND">
                          <fmt:formatNumber type="currency"
                                            maxFractionDigits="0"
                                            currencySymbol="">
                            <bean:write name="items" property="so_du_cot"/>
                          </fmt:formatNumber>
                        </logic:equal>
                         
                        <logic:notEqual property="loai_tien" name="items"
                                        value="VND">
                          <fmt:formatNumber type="currency" currencySymbol="">
                            <bean:write name="items" property="so_du_cot"/>
                          </fmt:formatNumber>
                        </logic:notEqual>
                      </td>
                      <td align="60">
                        <bean:write name="items" property="loai_tien"/>
                      </td>
                      <td align="60">
                        <logic:equal property="loai_tk" name="items"
                                        value="01">
                              Thanh toán tổng hợp          
                        </logic:equal>
                        <logic:equal property="loai_tk" name="items"
                                        value="02">
                              Thanh toán         
                        </logic:equal>
                        <logic:equal property="loai_tk" name="items"
                                        value="03">
                              Chuyên thu         
                        </logic:equal>
                      </td>
                      <td align="center" width="8%">
                        <a href='<html:rewrite page="/updateSoDuAction.do"/>?so_du=<bean:write name ="items" property="so_du"/>&ma_kb=<bean:write name="items" property="ma_kb"/>&ma_nh=<bean:write name="items" property="ma_nh"/>&loai_tien=<bean:write name="items" property="loai_tien"/>&ngay_gd=<bean:write name="items" property="ngay_gd"/>&so_du_cot=<bean:write name="items" property="so_du_cot"/>&loai_tk=<bean:write name="items" property="loai_tk"/>'>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none;"/></a>
                      </td>
                      <td align="center">
                        <a href='<html:rewrite page="/deleteSoDuAction.do"/>?so_du=<bean:write name ="items" property="so_du"/>&ma_kb=<bean:write name="items" property="ma_kb"/>&ma_nh=<bean:write name="items" property="ma_nh"/>&loai_tien=<bean:write name="items" property="loai_tien"/>&ngay_gd=<bean:write name="items" property="ngay_gd"/>&so_du_cot=<bean:write name="items" property="so_du_cot"/>&type=delete'
                           onclick="return confirm('Bạn có chắc chắn muốn xóa không ?')">
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                               style="border-style: none;"/></a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="10" align="center">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
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
<%@ include file="/includes/ttsp_bottom.inc"%>