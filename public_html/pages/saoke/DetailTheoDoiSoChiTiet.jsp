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
        f.submit();
    } 
    

</script>
<%@ include file="/includes/ttsp_header.inc"%>
<title>Chi tiết</title>
<html:form styleId="TheoDoiSoChiTiet" action="/listTheoDoiSoChiTiet.do">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Chi tiết</span>
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
    <table style="BORDER-COLLAPSE: collapse;margin:auto;margin-top:20px;font-size:10pt" border="1" cellspacing="0" bordercolor="#999999" width="70%">
           <thead>
           <c:if test="${requestScope.existSaoKeTK != null}">
                <tr>
                  <th colspan="5">Hệ thống ngân hàng <%=request.getAttribute("nganHang")%></th>
                </tr>
            </c:if>
            <c:if test="${requestScope.existSaoKeTK == null}">
                <tr>
                  <th colspan="4">Hệ thống ngân hàng <%=request.getAttribute("nganHang")%></th>
                </tr>
            </c:if>
            <c:if test="${requestScope.existSaoKeTK != null}">
                <tr>
                  <th colspan="5">Ngày <%=request.getParameter("date")%></th>
                </tr>
            </c:if>
            <c:if test="${requestScope.existSaoKeTK == null}">
                <tr>
                  <th colspan="4">Ngày <%=request.getParameter("date")%></th>
                </tr>
            </c:if>
            <c:if test="${requestScope.existSaoKeTK != null}">
                <tr>
                  <th colspan="5">Đơn vị chốt sổ: <span id = "dv_chotso"/></th>
                </tr>
            </c:if>
             <c:if test="${requestScope.existSaoKeTK == null}">
                <tr>
                  <th colspan="4">Đơn vị chốt sổ: <span id = "dv_chotso"/></th>
                </tr>
            </c:if>
            <tr>
              <th width="10%">STT</th>
              <th width="15%">Mã KBNN</th>
              <th width="40%">Tên đơn vị</th>
              <th width="15%">Trạng thái</th>
               <c:if test="${requestScope.existSaoKeTK != null}">
               <th width="15%">Trạng thái chốt sổ</th>
               </c:if>
            </tr>
           </thead>
           <tbody>
           <%int count = 1;%>
            <c:if test="${requestScope.existSaoKeTK != null}">
              <c:forEach items="${requestScope.existSaoKeTK}" var="item" >
                <tr>
                  <td><div align="center"><%=count++%></div></td>
                  <td><div align="center"><bean:write name="item" property="shkb"/></div></td>
                  <td><div align="left"><bean:write name="item" property="ten_kb"/></div></td>
                  <td><div align="center" style="color:green;"><bean:write name="item" property="trang_thai"/></div></td>
                  <!--20171130 thuongdt bo sung them tt_chot_so-->
                  <td>
                  <logic:equal value="00" name="item" property="tt_chot_so" >
                    <div align="center">Chưa chốt sổ</div>
                  </logic:equal>
                  <logic:equal value="01" name="item" property="tt_chot_so" >
                    <div align="center">Đã chốt sổ</div>
                  </logic:equal>
                  </td>
                  <input type="hidden" name="tt_chot_so" value="<bean:write name="item" property="tt_chot_so"/>"/>
                </tr>
              </c:forEach>
            </c:if>
            <c:if test="${requestScope.existSaoKeTK == null}">
              <c:forEach items="${requestScope.notExistSaoKeTK}" var="item">
                <tr>
                  <td><div align="center"><%=count++%></div></td>
                  <td><div align="center"><bean:write name="item" property="shkb"/></div></td>
                  <td><div align="left"><bean:write name="item" property="ten_kb"/></div></td>
                  <td><div align="center"><bean:write name="item" property="trang_thai"/></div></td>
                </tr>
              </c:forEach>
            </c:if>
           </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="thoat"> 
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </span>
        </td>
      </tr>
    </table>
    <html:hidden property="from_date" value='<%=request.getParameter("from_date")%>'/>
    <html:hidden property="to_date" value='<%=request.getParameter("to_date")%>'/>
</html:form>

<script type="text/javascript">
    checktt_chot_so();
   function checktt_chot_so(){
      var vDVCS = document.getElementById ("dv_chotso");
      var vTTDVCS = document.getElementsByName ("tt_chot_so");
      var sodv = 0;
      if(vDVCS != null){
        for(var i = 0; i < vTTDVCS.length; i++){
          if(vTTDVCS[i].value == '01'){
            sodv++;
          }
        }
        vDVCS.innerHTML = sodv+'/'+vTTDVCS.length
      }
   }
    
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>