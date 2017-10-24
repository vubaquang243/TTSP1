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
      f.action = 'listTsoHachToanAction.do?pageNumber=' + page;
      jQuery("#TCuuDMuc").submit();
  }
  function check(type) {
      var f = document.forms[0];

      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else if (type == 'add') {
          f.action = 'addTsoHachToanAction.do';
      }
      f.submit();
  }

  jQuery(document).ready(function () {
      jQuery('#ma_nh').focus();
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="listTsoHachToanAction" method="post" styleId="TCuuDMuc">
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
             Tham số hạch toán </span>
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
    <logic:notEmpty name="TsoHachToanForm" property="action_status">
      <font color="Red"><bean:write name="TsoHachToanForm" property="action_status"/></font>
    </logic:notEmpty>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
              Điều kiện tìm kiếm
              </font>
            </span>
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
               <td align="right" width="12%">Mã ngân hàng</td>
                <td>
                  <html:select property="ma_nh" styleId="ma_nh">
                            <html:option value="">--Chọn ngân hàng--</html:option>
                            <html:option value="201">Ngân hàng Công Thương</html:option>
                            <html:option value="202">Ngân hàng Đầu Tư</html:option>
                            <html:option value="203">Ngân hàng Ngoại Thương</html:option>
                            <html:option value="204">Ngân hàng Nông Nghiệp</html:option>
                        </html:select>
                </td>
                <td align="right" width="15%">Loại hạch toán</td>
                <td>
                <html:text  property="loai_htoan" styleId="loai_htoan" size="20%"
                            maxlength="200"
                            onfocus="this.style.backgroundColor='#ffffb5'"
                            onblur="this.style.backgroundColor='#ffffff'"                           
                            styleClass="promptText"></html:text>
                </td>
                <td width="15%" align="right">                           
                               Mã tài khoản tự nhiên                       
                          </td>
                          <td>
                           <html:text property="tktn" styleId="tktn" size="20%"
                            maxlength="4"
                            onfocus="this.style.backgroundColor='#ffffb5'"
                            onkeypress="return numberBlockKey(event)"
                            onblur="this.style.backgroundColor='#ffffff'"                           
                            styleClass="promptText"></html:text>
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
            <span class="sortKey">T</span>ra cứu
          </button>
           </span>
           <span id="them">
          <button type="button" onclick="check('add')" class="ButtonCommon"
                  accesskey="i">
            Th&ecirc;<span class="sortKey">m</span> mới            
          </button>
           </span>
           <span id="thoat">
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
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
            <font color="Gray">
             Kết quả tìm kiếm
            </font>
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
                  <div align="center">
                    Mã ngân hàng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Loại hạch toán
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                  Mô tả
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                 Mã TKTN
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                  Mã quỹ
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                  Mã ĐVSDNS
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                  Mã cấp NS
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã chương
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã NKT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã NDKT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã ĐBHC
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã CTMT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã nguồn
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã kho bạc
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã dự phòng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Ghi chú
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Sửa
                  </div>
                </th>
              </thead>
               
            <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
         int rowBegin = (pagingBean.getCurrentPage() -1) * 20;
      %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                     <logic:empty name="listTso">
                  <tr>
                    <td colspan="19">
                      <font color="Red">Không có kết quả tìm kiếm</font>
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="listTso">
                  <logic:iterate id="items" indexId="index" name="listTso">
                    <tr id='row_dts_<bean:write name="index"/>'
                        class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'
                        onmouseout="window.status =''">
                      <td width="70" align="center">
                        <%=index+1+rowBegin%>
                      </td>
                     
                      <td width="70" align="center"
                          onmouseover="style.fontWeight='bold'"
                          onmouseout="style.fontWeight='normal'">
                        <bean:write name="items" property="ma_nh"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="loai_htoan"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="mo_ta"/>
                      </td>
                    <td width="80" align="center">
                        <bean:write name="items" property="tktn"/>
                      </td>
                   <td width="80" align="center">
                        <bean:write name="items" property="quy"/>
                      </td>
                     <td width="80" align="center">
                        <bean:write name="items" property="dvsdns"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="cap_ns"/>
                      </td>
                     <td width="80" align="center">
                        <bean:write name="items" property="chuong"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="nganh_kt"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="ndkt"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="dbhc"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="ctmt"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="nguon"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="ma_kb"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="du_phong"/>
                      </td>
                      <td width="80" align="center">
                        <bean:write name="items" property="ghi_chu"/>
                      </td>
                     
                      <td align="center">
<a href='<html:rewrite page="/updateTsoHachToanAction.do"/>?ma_nh=<bean:write name ="items" property="ma_nh"/>&loai_htoan=<bean:write name="items" property="loai_htoan"/>&mo_ta=<bean:write name="items" property="mo_ta"/>&tktn=<bean:write name="items" property="tktn"/>&quy=<bean:write name="items" property="quy"/>&dvsdns=<bean:write name="items" property="dvsdns"/>&cap_ns=<bean:write name ="items" property="cap_ns"/>&chuong=<bean:write name ="items" property="chuong"/>&nganh_kt=<bean:write name ="items" property="nganh_kt"/>&ndkt=<bean:write name ="items" property="ndkt"/>&dbhc=<bean:write name ="items" property="dbhc"/>&ctmt=<bean:write name ="items" property="ctmt"/>&nguon=<bean:write name ="items" property="nguon"/>&ma_kb=<bean:write name ="items" property="ma_kb"/>&du_phong=<bean:write name ="items" property="du_phong"/>&ghi_chu=<bean:write name ="items" property="ghi_chu"/>&id=<bean:write name ="items" property ="id"/>'>
<img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
     style="border-style: none;"/></a>                  
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="19" align="center">
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
