<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
    
</script>
<%@ include file="/includes/ttsp_header.inc"%>
<title>Sửa Lãi Suất</title>
<html:form action="/updateExeLaiSuat.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Lãi suất</span>
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
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Sửa Lãi Suất</font></span>
          </td>
        </tr>
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
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
              <td width="30%">
                <html:text property="ma_dv" styleId="ma_dv" style="width:78%" disabled="true"></html:text>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Ngày bắt đầu</td>
              <td width="30%">
                <html:text property="fromDate" styleId="fromDate" styleClass="fieldText"
                           onkeypress="return numbersonly(this,event,true) "
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('fromDate');"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            style="WIDTH: 70px;vertical-align:middle;"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="btn_fromDate" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "fromDate", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "btn_fromDate"// id of the button
                  });
                </script>
              </td>
            </tr>
            <tr>
              <td align="right" bordercolor="#e1e1e1">Loại lãi suất</td>
              <td>
                <html:select property="loaiLaiSuat" styleId="loaiLaiSuat" style="width:80%">
                  <html:option value="">Chọn loại lãi suất</html:option>
                  <html:option value="D">Ngày</html:option>
                  <html:option value="M">Tháng</html:option>
                  <html:option value="Y">Năm</html:option>
                </html:select>
              </td>
              <td align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td>
                <html:text property="loaiTien" maxlength="3" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td align="right" bordercolor="#e1e1e1">Lãi suất</td>
              <td>
                <html:text property="laiSuat" maxlength="4" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>%
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
          <span id="update">
            <input type="submit" class="ButtonCommon" accesskey="c" style="width:100px" value="Cập nhật"/>
          </span>
          <span id="exit"> 
            <button type="button" onclick="exit()"
                    class="ButtonCommon" accesskey="m" style="width:100px">
                    <span class="sortKey">T</span>hoát
            </button>
          </span>
        </td>
      </tr>
    </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>