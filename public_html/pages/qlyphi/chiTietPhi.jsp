<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
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
    function thoat(){
        var f = document.forms[0];
        f.action = "hoTroTinhPhi.do?execute=yes";
        f.submit();
    }
    function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
    }
</script>
<title>Chi Tiết Tính Phí</title>
<html:errors/>
<html:form action="/chiTietPhi.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Tra cứu chi tiết phí</span>
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
            <html:hidden property="ma_nh"/>
            <html:hidden property="loai_tien"/>
            <html:hidden property="ma_kb"/>
            <br/>
            <table width="80%" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" border="0" align="center" style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="10%" align="right" bordercolor="#e1e1e1">Loại giao dịch</td>
                <td>
                  <html:select property="loai_gd" styleId="loai_gd" style="width:50%">
                    <option value="">-- Chọn --</option>
                    <option value="EXT">Ngoài hệ thống</option>
                    <option value="INT">Trong hệ thống</option>
                  </html:select>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Loại phí</td>
                <td>
                  <html:select property="loai_phi" styleId="loai_phi" style="width:35%">
                    <option value="">-- Chọn --</option>
                    <option value="GIA_TRI">Giá trị</option>
                    <option value="MON">Món</option>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Từ ngày</td>
                <td width="30%">
                  <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_tu_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "tu_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_tu_ngay"// id of the button
                    });
                  </script>
                </td>
                <td align="right" bordercolor="#e1e1e1">Đến ngày</td>
                <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_den_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "den_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_den_ngay"// id of the button
                    });
                  </script>
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
          <span id="tinhPhi">
            <button type="button" onclick="traCuu()" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>ra cứu
            </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="thoat()" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              Th<span class="sortKey">o</span>át
            </button>
          </span>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6" background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg" height="24">
            <font color="#006699">Kết quả</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:3%">
                    <div align="center">STT</div>
                  </th>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Loại GD</div>
                  </th>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Ngày thanh toán</div>
                  </th>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Giờ NH phản hồi</div>
                  </th>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Số lệnh thanh toán</div>
                  </th>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:15%">
                    <div align="center">Số yêu cầu thanh toán</div>
                  </th>
                  <c:if test="${loaiTien == 'VND'}">
                    <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:22%">
                      <div align="center">Số tiền</div>
                    </th>
                  </c:if>
                  <c:if test="${loaiTien != 'VND'}">
                    <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:11%">
                      <div align="center">Số tiền</div>
                    </th>
                  </c:if>
                  <th colspan="2" class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Mức phí</div>
                  </th>
                  <c:if test="${loaiTien != 'VND'}">
                    <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:11%">
                      <div align="center">Tiền phí (<c:out value="${loaiTien}"/>)</div>
                    </th>
                  </c:if>
                  <th rowspan="2" class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Tiền phí (VND)</div>
                  </th>
                </tr>
                <tr>
                  <th>Món</th>
                  <th>Giá trị</th>
                </tr>
              </thead>
<%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
  int rowBegin = (pagingBean.getCurrentPage() -1) * 20;
%>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em" id="phiTableBody">
                <c:forEach items="${requestScope.chiTietPhi}" var="items" varStatus="stt">
                  <c:if test="${stt.count%2==0}">
                    <tr>
                  </c:if>
                  <c:if test="${stt.count%2!=0}">
                    <tr style="background-color:#cdebe8">
                  </c:if>
                    <td align="center"><c:out value="${stt.count}"/></td>
                    <td align="center">
                      <c:if test="${items.loai_gd == 'INT'}">Trong HT</c:if>
                      <c:if test="${items.loai_gd == 'EXT'}">Ngoài HT</c:if>
                    </td>
                    <td align="center"><c:out value="${items.ngay_tt}"/></td>
                    <td align="center"><c:out value="${items.gio_phan_hoi}"/></td>
                    <td align="right"><c:out value="${items.ltt_id}"/></td>
                    <td align="right"><c:out value="${items.so_yctt}"/></td>
                    <td align="right">
                      <c:if test="${requestScope.loaiTien == 'VND'}">
                        <fmt:formatNumber maxFractionDigits="0" type="currency" currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </c:if>
                      <c:if test="${requestScope.loaiTien != 'VND'}">
                        <fmt:formatNumber type="currency" currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </c:if>
                    </td>
                    <td align="right">
                      <c:if test="${items.loai_phi == 'MON'}">
                        <c:out value="${items.muc_phi}"/>
                      </c:if>
                    </td>
                    <td align="right">
                      <c:if test="${items.loai_phi == 'GIA_TRI'}">
                        <c:out value="${items.muc_phi}"/>
                      </c:if>
                    </td>
                    <c:if test="${requestScope.loaiTien != 'VND'}">
                      <td align="right">
                        <fmt:formatNumber type="currency" currencySymbol="">
                          <bean:write name="items" property="phi_nguyen_te"/>
                        </fmt:formatNumber>
                      </td>
                    </c:if>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0" type="currency" currencySymbol="">
                        <bean:write name="items" property="tien_phi"/>
                      </fmt:formatNumber>
                    </td>
                  </tr>
                </c:forEach>
                <tr>
                  <td colspan="10" align="center">
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