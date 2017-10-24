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
<script src="src">
"<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
  type = "text/javascript"
</script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  function goPage(page) {
      jQuery("#pageNumber").val(page);
      var f = document.forms[0];
      f.action = 'listDMNgoaiTe_ManhAction.do?pageNumber=' + page;
      jQuery("#TCuuDMuc").submit();
  }

  function check(type) {
      var f = document.forms[0];

      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else if (type == 'add') {
          f.action = 'addDMNgoaiTe_ManhAction.do';
      }
      
      f.submit();
  }
jQuery(document).ready(function () {
      jQuery('#ma').focus();
  });

</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="listDMNgoaiTe_ManhAction" method="post" styleId="TCuuDMuc">
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
            <span class="title2">Danh mục ngoại tệ mạnh</span>
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
    <logic:notEmpty name="DMNgoaiTe_ManhForm" property="action_status">
      <font color="Red">
        <bean:write name="DMNgoaiTe_ManhForm" property="action_status"/>
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
           <td align="right" width="15%">Mã ngoại tệ</td>
                <td align="left" width="25%">
                <html:select styleClass="selectBox" property="ma"
                                           styleId="ma" style="width:50%;height:20px">
                                <html:option value="">Các ngoại tệ</html:option>
                                <html:optionsCollection value="ma" label="ma" name="lstLoaiTien"/>
                              </html:select>
                </td>
                </tr>  
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- 4 nút tra cứu , thiết lập, ngừng hoạt động,thoát--%>
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
              Th
              <span class="sortKey">i</span>
              ết lập
            </button>
             </span>
           
          <span id="ngung"> 
             </span>
             <span id="thoat"> 
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Tho
              <span class="sortKey">á</span>
              t
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
                  <div align="center">Mã ngoại tệ</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Người tạo</div>
                </th>  
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ngày tạo</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ngày hiệu lực</div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ngừng hoạt động</div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
         int rowBegin = (pagingBean.getCurrentPage() -1) * 20;
      %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:empty name="Ngoai_te">
                  <tr>
                    <td colspan="11">
                      <font color="Red">Kh&ocirc;ng c&oacute; kết quả t&igrave;m
                                        kiếm</font>
                    </td>
                  </tr>
                </logic:empty>
                <logic:notEmpty name="Ngoai_te">
                  <logic:iterate id="items" indexId="index" name="Ngoai_te">
                    <tr id='row_dts_<bean:write name="index"/>'
                        class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                        onmouseout="window.status =''">
                      <td width="3%" align="center">
                        <%=index+1+rowBegin%>
                      </td>
                      <td width="9%" align="center"
                          onmouseover="style.fontWeight='bold'"
                          onmouseout="style.fontWeight='normal'">
                        <bean:write name="items" property="ma"/>
                      </td>
                      <td width="8%" align="center">
                        <bean:write name="items" property="nt"/>
                      </td>                
                      <td width="7%">
                        <bean:write name="items" property="ngay_tao"/>
                      </td>
                      <td width="7%">
                        <bean:write name="items" property="ngay_hieu_luc"/>
                      </td>
                       <td align="center" width="8%">
                        <a href='<html:rewrite page="/updateDMNgoaiTe_ManhAction.do"/>?id=<bean:write name ="items" property="id"/>&ma=<bean:write name="items" property="ma"/>'>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg"
                               style="border-style: none;"/></a>
                      </td>
                     
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="11" align="center">
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