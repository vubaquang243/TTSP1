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
    jQuery.noConflict();

    jQuery(document).init(function () {
        
    });
    
    function check(type) {
        var f = document.forms[0];
        f.target = '';
        if (type == 'close') {
            f.action = 'mainAction.do';
        }
        f.submit();
    }    
</script>
<%@ include file="/includes/ttsp_header.inc"%>
<title>Theo dõi sổ chi tiết</title>
<html:form styleId="TheoDoiSoChiTiet" action="/listTheoDoiSoChiTiet.do">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">
            Theo dõi sổ chi tiết</span>
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
    <table style="BORDER-COLLAPSE: collapse" border="2" cellspacing="2" bordercolor="#e1e1e1" width="100%">
      <tbody>
        <tr>
          <td align="right" width="15%" bordercolor="#e1e1e1">
            <label for="tu_ngay">Từ ngày</label>
          </td>
          <td bordercolor="#e1e1e1">
          <html:text property="from_date" styleId="from_date" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('from_date');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      style="WIDTH: 70px;vertical-align:middle;"/>
           
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="btn_from_date" width="20"
               style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "from_date", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "btn_from_date"// id of the button
              });
            </script>
          </td>
          
          <td align="right" width="15%" bordercolor="#e1e1e1">
            <label for="den_ngay">Đến ngày</label>
          </td>
          <td bordercolor="#e1e1e1">
            <html:text property="to_date" styleId="to_date" styleClass="fieldText"
                       onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('to_date');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        style="WIDTH: 70px;vertical-align:middle;"/>
             
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="btn_to_date" width="20"
                 style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "to_date", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "btn_to_date"// id of the button
              });
            </script>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tracuu">
            <button type="button" onclick="check('find')" class="ButtonCommon" accesskey="t">
              <span class="sortKey">T</span>ra cứu
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
    <div><!-- note-->
       <table style="BORDER-COLLAPSE: collapse;" border="1" cellspacing="0"
           bordercolor="#999999" width="100%" >
        <thead>
          <th bgcolor="#f0f0f0" style="20%">
            <div align="center">Hệ thống ngân hàng</div>
          </th>
          <logic:notEmpty name="dmNganHang">
            <logic:iterate id="nganHang" name="dmNganHang">
              <th class="promptText" bgcolor="#f0f0f0" colspan="2">
                <div align="center"><bean:write name="nganHang" property="ten_nh"/></div>
              </th>
            </logic:iterate>
          </logic:notEmpty>
        </thead>
        <tbody>
          <tr>
            <th bgcolor="#f0f0f0" align="center">Trạng thái sổ chi tiết</th>
            <logic:iterate id="trangthai" name="trangThai">
              <th bgcolor="#f0f0f0" style="width:10%">
                <div align="center"><bean:write name="trangthai"/></div>
              </th>
            </logic:iterate>
          </tr>
          <logic:notEmpty name="colName">
            <c:forEach items="${requestScope.colName}" var="row">
              <tr>
                <th><div align="center"><bean:write name="row" property="date"/></div></th>
                <c:forEach items="${row.resultRow}" var="result" varStatus="count">
                  <c:choose>
                    <c:when test="${count.index%2==0}">
                      <td><div align="center"><a href="detailTheoDoiSoChiTiet.do?date=<bean:write name="row" property="date"/>&type=1&index=<bean:write name="count" property="index"/>&from_date=<bean:write name="form" property="from_date"/>&to_date=<bean:write name="form" property="to_date"/>"><bean:write name="result"/></a></div></td>
                    </c:when>
                    <c:when test="${count.index%2!=0}">
                      <td><div align="center"><a href="detailTheoDoiSoChiTiet.do?date=<bean:write name="row" property="date"/>&index=<bean:write name="count" property="index"/>&from_date=<bean:write name="form" property="from_date"/>&to_date=<bean:write name="form" property="to_date"/>"><bean:write name="result"/></a></div></td>
                    </c:when>
                  </c:choose>
                </c:forEach>
              </tr>
            </c:forEach>
          </logic:notEmpty>
        </tbody>
      </table>
    </div>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>