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
    function doichieu(){
        var f = document.forms[0];
        f.submit();
        alert("Đối chiếu toàn tỉnh tốn một khoảng thời gian ngắn. \nXin vui lòng chờ trong giây lát")
    }
</script>
<title>Đối chiếu sổ chi tiết tỉnh</title>
<html:errors/>
<html:form action="/DChieuSoChiTiet_Tinh.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Đối chiếu sổ chi tiết tỉnh</span>
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
              <td width="15%" align="right" bordercolor="#e1e1e1">Kho bạc tỉnh</td>
              <td width="30%">
                <html:select property="kb_tinh" styleId="kb_tinh" style="width:80%">
                  <html:options collection="listKBTinh" property="id_cha" labelProperty="kb_tinh"/>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngày</td>
              <td width="30%">
                <html:text property="ngay" styleId="ngay" styleClass="fieldText"
                           onkeypress="return numbersonly(this,event,true) "
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay');"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            style="WIDTH: 70px;vertical-align:middle;"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="btn_ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "ngay", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "btn_ngay"// id of the button
                  });
                </script>
              </td>
            </tr>
            <tr>
              <td><input type="hidden" name="execute" value="execute" /></td>
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
            <button type="button" onclick="doichieu()" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">X</span>em
            </button>
          </span>
        </td>
      </tr>
    </table>
    <%--<c:if test="${requestScope.listSoChiTiet != null}"> --%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:19%" rowspan="2">
                    <div align="center">Kho bạc</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:32%" rowspan="2">
                    <div align="center">Ngân hàng</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%" rowspan="2">
                    <div align="center">Khớp đúng</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:45%" colspan="3">
                    <div align="center">Chênh lệch (số dư ngân hàng - kho bạc)</div>
                  </th>
                </tr>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Số dư</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">P/s Có</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">P/s Nợ</div>
                  </th>
                </tr>
              </thead>
<%--<%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
  int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
%>--%>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                <c:if test="${requestScope.listSoChiTiet != null}">
                <c:forEach var="item" items="${requestScope.listSoChiTiet}">
                <tr>
                    <td align="left">
                      <c:out value="${item.ten_kb}"/>
                    </td>
                    <td align="left">
                      <c:out value="${item.ten_nh}"/>
                    </td>
                    <td align="center">
                      <c:if test="${item.ket_qua == '0'}">
                        <b><font color="blue"> Khớp đúng </font></b>
                      </c:if>
                      <c:if test="${item.ket_qua == '1'}">
                        <b><font color="red"> Chênh lệch </font></b>
                      </c:if>
                    </td>
                    <c:if test="${item.ket_qua == '0'}">
                    <td align="right"></td>
                    <td align="right"></td>
                    <td align="right"></td>
                    </c:if>
                    <c:if test="${item.ket_qua == '1'}">
                    <td align="right">
                      <c:out value="${item.ss_so_du}"/>
                    </td>
                    <td align="right">
                      <c:out value="${item.ss_ps_co}"/>
                    </td>
                    <td align="right">
                      <c:out value="${item.ss_ps_no}"/>
                    </td>
                    </c:if>
                </tr>
                </c:forEach>
                <tr>
                  <td colspan="14" align="center">
                    <%--<%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>--%>
                  </td>
                </tr>
                </c:if>
              </tbody>
              <html:hidden property="pageNumber" value="1"/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%--</c:if>--%>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>