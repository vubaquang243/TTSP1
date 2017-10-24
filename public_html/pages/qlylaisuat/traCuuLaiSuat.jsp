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
    jQuery(document).init(function () {
        
    });
    function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
    }
    function check(type) {
        var f = document.forms[0];
        f.target = '';
        if(type == 'clear'){
            f.ma_dv.value = '';
            f.fromDate.value = ''
            f.toDate.value = ''
            f.loaiTien.value = ''
            f.laiSuat.value = ''
            f.loaiLaiSuat.value = ''
            return;
        }
        if (type == 'find') {
            f.submit();
        }else if (type == 'add') {
            f.action = 'addLaiSuat.do';
        }
        f.submit();
    }
    function xoa(index) {
        if(confirm("(Khi xóa lãi suất liền kề sẽ được mở lại) Bạn có chắc chắn muốn xóa lãi suất này không ?")){
            var f = document.forms[0];
            f.action = 'deleteLaiSuat.do?index='+index;
            f.submit();
        }
    }
</script>

<title>Tra cứu Lãi Suất</title>
<html:form action="/traCuuLaiSuat.do" method="post">
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Điều kiện
                                                             t&igrave;m kiếm</font></span>
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
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
              <td width="30%">
                <html:select property="ma_dv" styleId="ma_dv" style="width:80%">
                  <html:option value="">Chọn ngân hàng</html:option>
                  <html:options collection="listNganHang" property="ma_dv" labelProperty="ten_nh"/>
                </html:select>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Từ ngày</td>
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
              <td align="right" bordercolor="#e1e1e1">Đến ngày</td>
              <td>
                <html:text property="toDate" styleId="toDate" styleClass="fieldText"
                           onkeypress="return numbersonly(this,event,true) "
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('toDate');"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            style="WIDTH: 70px;vertical-align:middle;"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="btn_toDate" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "toDate", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "btn_toDate"// id of the button
                  });
                </script>
              </td>
            </tr>
            <tr>
              <td align="right" bordercolor="#e1e1e1">Lãi suất</td>
              <td>
                <html:text property="laiSuat" maxlength="4" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>%
              </td>
              <td align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td>
                <html:text property="loaiTien" maxlength="3" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
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
          <span id="tracuu">
            <button type="button" onclick="check('find')" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>ra cứu
            </button>
          </span>
          <span id="clear">
            <button type="button" onclick="check('clear')" class="ButtonCommon"
                    accesskey="l" style="width:100px">
              <span class="sortKey">L</span>àm mới
            </button>
          </span>
          <span id="themMoi"> 
            <button type="button" onclick="check('add')"
                    class="ButtonCommon" accesskey="m" style="width:100px">
              Thêm<span class="sortKey"> M</span>ới
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
            <font color="Gray">Kết quả t&igrave;m kiếm</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
                   bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:30%">
                    <div align="center">Ngân Hàng</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Lãi Suất</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Loại</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:14%">
                    <div align="center">Ngày Hiệu Lực</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:14%">
                    <div align="center">Ngày Hết Hiệu Lực</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Loại Tiền</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:15%">
                    <div align="center">#</div>
                  </th>
                </tr>
              </thead>
<%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
  int rowBegin = (pagingBean.getCurrentPage() -1) * 20;
%>
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1"
                     style="font-size:1.2em">
                <logic:notEmpty name="listLaiSuat">
                  <c:forEach items="${requestScope.listLaiSuat}" var="item" varStatus="status">
                    <tr>
                      <td>
                          <bean:write name="item" property="he_thong_nh"/>
                          <input type="hidden" name="he_thong_nh" value='<bean:write name="item" property="he_thong_nh"/>'/>
                      </td>
                      <td align="center">
                          <bean:write name="item" property="lai_suat"/>
                          <input type="hidden" name="lai_suat" value='<bean:write name="item" property="lai_suat"/>'/>
                      </td>
                      <td align="center">
                        <logic:equal value="D" name="item" property="loai_lai_suat">Ngày</logic:equal>
                        <logic:equal value="M" name="item" property="loai_lai_suat">Tháng</logic:equal>
                        <logic:equal value="Y" name="item" property="loai_lai_suat">Năm</logic:equal>
                        <input type="hidden" name="loai_lai_suat" value='<bean:write name="item" property="loai_lai_suat"/>'/>
                      </td>
                      <td align="center">
                        <c:if test="${item.ngay_het_hieu_luc == null}">
                            <span style="color:green"><bean:write name="item" property="ngay_hieu_luc"/></span>
                        </c:if>
                        <c:if test="${item.ngay_het_hieu_luc != null}">
                            <span style="color:red"><bean:write name="item" property="ngay_hieu_luc"/></span>
                        </c:if>
                          <input type="hidden" name="ngay_hieu_luc" value='<bean:write name="item" property="ngay_hieu_luc"/>'/>
                      </td>
                      <td align="center">
                          <span style="color:red"><bean:write name="item" property="ngay_het_hieu_luc"/></span>
                          <input type="hidden" name="ngay_het_hieu_luc" value='<bean:write name="item" property="ngay_het_hieu_luc"/>'/>
                      </td>
                      <td align="center">
                          <bean:write name="item" property="loai_tien"/>
                          <input type="hidden" name="loai_tien" value='<bean:write name="item" property="loai_tien"/>'/>
                      </td>
                      <td align="center">
                        <input onclick='xoa(<c:out value="${status.index}"/>)' class="xoa" type="button" value="Xóa" style="width:48%"></input>
                      </td>
                    </tr>
                  </c:forEach>
                </logic:notEmpty>
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