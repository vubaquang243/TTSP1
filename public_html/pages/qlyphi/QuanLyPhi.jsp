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
        f.action = 'themPhi.do';
        f.submit();
    }
    function khoa(i){
        var f = document.forms[0];
        f.action = 'updatePhi.do?id='+i;
        f.submit();
    }
    function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
    }
</script>

<title>Quản lý phí</title>
<html:form action="/quanLyPhi.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Quản lý phí</span>
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
              <td width="15%" align="right" bordercolor="#e1e1e1">Hệ thống ngân hàng</td>
              <td width="30%">
                <html:select property="he_thong_ngan_hang" styleId="he_thong_ngan_hang" style="width:80%">
                  <html:option value="">Chọn ngân hàng</html:option>
                  <html:options collection="listNganHang" property="ma_dv" labelProperty="ten_nh"/>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại giao dịch</td>
              <td width="30%">
                <html:select property="loai_gd" styleId="loai_gd" style="width:80%">
                  <html:option value="">Chọn loại giao dịch</html:option>
                  <html:option value="INT">Cùng hệ thống NH</html:option>
                  <html:option value="EXT">Khác hệ thống NH</html:option>
                </html:select>
              </td>
            </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại phí</td>
              <td width="30%">
                <html:select property="loai_phi" styleId="loai_phi" style="width:80%">
                  <html:option value="">Chọn loại phí</html:option>
                  <html:option value="MON">Theo món</html:option>
                  <html:option value="GIA_TRI">Theo giá trị</html:option>
                </html:select>
              </td>
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
            </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td width="30%">
                <html:text property="loai_tien" maxlength="3" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
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
            <font color="Gray">Thông tin khai phí</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                    <div align="center">Hệ thống</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:11%">
                    <div align="center">Loại GD</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Từ giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Đến giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Giá tiền từ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Đến giá tiền</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Loại</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Mức phí</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Giá trị sàn</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Giá trị trần</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">VAT</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Ngày bắt đầu</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Ngày hết hạn</div>
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
                <c:forEach items="${requestScope.listPhi}" var="items" >
                  <tr>
                    <td align="center"><c:out value="${items.he_thong_nh}"/></td>
                    <td align="center">
                      <c:if test="${items.loai_gd == 'INT'}">
                        Trong hệ thống
                      </c:if>
                      <c:if test="${items.loai_gd == 'EXT'}">
                        Khác hệ thống
                      </c:if>
                    </td>
                    <td align="center"><c:out value="${items.gio_tu}"/></td>
                    <td align="center"><c:out value="${items.gio_den}"/></td>
                    <td align="right"><c:out value="${items.gia_tri_tu}"/></td>
                    <td align="right"><c:out value="${items.gia_tri_den}"/></td>
                    <td align="center">
                      <c:if test="${items.loai_phi == 'MON'}">
                          Món
                      </c:if>
                      <c:if test="${items.loai_phi == 'GIA_TRI'}">
                          Giá trị
                      </c:if>
                    </td>
                    <td align="right"><c:out value="${items.muc_phi}"/></td>
                    <td align="right"><c:out value="${items.san}"/></td>
                    <td align="right"><c:out value="${items.tran}"/></td>
                    <td align="center"><c:out value="${items.vat}"/>%</td>
                    <td align="center"><c:out value="${items.tu_ngay}"/></td>
                    <td align="center"><c:out value="${items.den_ngay}"/></td>
                    <td align="center">
                        <input onclick='khoa(<c:out value="${items.id}"/>)' class="khoa" type="button" value="Sửa" style="width:70%"></input>
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