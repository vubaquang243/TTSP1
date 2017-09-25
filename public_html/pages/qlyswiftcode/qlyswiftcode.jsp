<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript">
    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () {
    });
    
    function traCuu(){
        var f = document.forms[0];
        f.submit();
    }
    function themMoi(){
        var f = document.forms[0];
        f.action = 'addSpSwiftCode.do';
        f.submit();
    }
    function sua(i){
        var f = document.forms[0];
        f.action = 'updateSpSwiftCode.do?id='+i;
        f.submit();
    }
    function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
    }
</script>
<title>Quản lý swift code</title>
<html:form action="/traCuuSpSwiftCode.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Điều kiện lọc swift code</span>
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
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <c:if test="${requestScope.error != null}">
          <tr>
            <td style="color:red"><c:out value="${requestScope.error}"/></td>
          </tr
        </c:if>
        <c:if test="${requestScope.success != null}">
          <tr>
            <td style="color:green"><c:out value="${requestScope.success}"/></td>
          </tr
        </c:if>
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Tên nước</td>
              <td width="30%">
                <html:text property="country_name" styleId="country_name" />
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Thành phố</td>
              <td width="30%">
                <html:text property="city_heading" styleId="city_heading" />
              </td>
            </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Tổ chức</td>
              <td width="30%">
                <html:text property="institution_name" styleId="institution_name" />
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Bic code</td>
              <td width="30%">
                <html:text property="bic_code" styleId="bic_code" />
              </td>
            </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Branch code</td>
              <td width="30%">
                <html:text property="branch_code" styleId="branch_code" />
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="traCuu">
            <button type="button" onclick="traCuu()" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>ra cứu
            </button>
          </span>
          <span id="themMoi">
            <button type="button" onclick="themMoi()" class="ButtonCommon"
                    accesskey="m" style="width:100px">
              Thêm <span class="sortKey">M</span>ới
            </button>
          </span>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Thông tin swift code</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:17%">
                    <div align="center">Tên nước</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Mã thành phố</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:45%">
                    <div align="center">Tên tổ chức</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Bic code</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Branch code</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:8%">
                    <div align="center">#</div>
                  </th>
                </tr>
              </thead>
<%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
  int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
%>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                <c:forEach items="${requestScope.swiftCodeList}" var="items" varStatus="countVar">
                  <c:if test="${countVar.count % 2 == 0}">
                    <tr class='trDanhSachChan'>
                  </c:if>
                  <c:if test="${countVar.count % 2 != 0}">
                    <tr class='trDanhSachLe'>
                  </c:if>
                    <td align="left"><c:out value="${items.country_name}"/></td>
                    <td align="center"><c:out value="${items.city_heading}"/></td>
                    <td align="left"><c:out value="${items.institution_name}"/></td>
                    <td align="left"><c:out value="${items.bic_code}"/></td>
                    <td align="left"><c:out value="${items.branch_code}"/></td>
                    <td align="center">
                        <input onclick='sua(<c:out value="${items.id}"/>)' class="khoa" type="button" value="Sửa" style="width:70%"></input>
                    </td>
                  </tr>
                </c:forEach>
                <tr>
                  <td colspan="14" align="center">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                    <html:hidden property="pageNumber" value="1"/>
                  </td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>