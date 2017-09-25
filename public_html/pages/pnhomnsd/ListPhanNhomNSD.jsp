<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.NhomNSDResoure"/>
<script type="text/javascript">
  //document.getElementById("ma_kb").focus();
  jQuery.noConflict();
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      if (type == 'add') {        
        f.action = 'QuanLyNhomNSDAddAction.do';       
      }else if (type == 'print') {
        f.action = 'QuanLyNhomNSDPrintAction.do';
         newDialog = window.open('about:blank', "_form");        
        f.target='_form';
      }else if (type == 'close') {
          f.action = 'mainAction.do';
      }
     
      f.submit();
  }
  
    function blockKeySpecial001(e){
//      e.keyCode
    var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    var character = String.fromCharCode(code);
   var pattern=/[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if(pattern.match(character)){
             character.replace(character,"");
              return false;
      }else{
            return true;
      }
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/QuanLyNhomNSDListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
       <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2> <fmt:message key="qlynhomnsd.listphannhomnsd.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
      </tbody>  
    </table>
    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
    <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
    <font color="Red" dir="ltr">
      <fmt:message key="<%=StrStatus%>">
        <fmt:param>
          <%=id%>
        </fmt:param>
      </fmt:message>
    </font>
    <%}%>
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">          
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="qlynhomnsd.listphannhomnsd.title.danhsachnhom"/>
            </font>
          </td>
        </tr>
      </tbody>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
              bordercolor="#999999" width="100%">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.manhom"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.tennhom"/></div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.loainhom"/></div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.chitiet"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.sua"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="qlynhomnsd.listphannhomnsd.lable.xoa"/></div>
                </th>
              </thead>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstNhomNSD">
                  <logic:iterate id="nhomNSDlist" name="lstNhomNSD"
                                 indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="5%">
                        <bean:write name="nhomNSDlist" property="id"/>
                      </td>
                      <td align="left" width="20%">
                        <bean:write name="nhomNSDlist" property="ten_nhom"/>
                      </td>
                      <td align="left" width="8%">
                        <bean:write name="nhomNSDlist" property="loai_nhom"/>
                      </td>
                        <td align="center" width="3%">
                        <a href='<html:rewrite page="/QuanLyNhomNSDViewAction.do"/>?longid=<bean:write name="nhomNSDlist" property="id"/>
            &loainhom=<bean:write name="nhomNSDlist" property="loai_nhom"/>
            &tennhom=<bean:write name="nhomNSDlist" property="ten_nhom"/> '>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/select.gif"
                               style="border-style: none"/></a>
                      </td>
                      <td align="center" width="3%">
                        <a href='<html:rewrite page="/QuanLyNhomNSDUpdateAction.do"/>?longid=<bean:write name="nhomNSDlist" property="id"/>
            &loainhom=<bean:write name="nhomNSDlist" property="loai_nhom"/>
            &tennhom=<bean:write name="nhomNSDlist" property="ten_nhom"/>
            '>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none"/></a>
                      </td>
                      <td align="center" width="3%">
                        <a href='<html:rewrite page="/QuanLyNhomNSDDeleteAction.do"/>?longid=<bean:write name="nhomNSDlist" property="id"/>'
                           onclick="return confirm('Bạn có muốn xóa nhóm NSD này ?')">
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                               style="border-style: none"/></a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
              </tbody>
             </table>
          </td>
        </tr>
      
    </table>
    <%-- ************************************--%>
    <%-- ************************************--%>
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="them">
          <button type="button" onclick="check('add')" class="ButtonCommon"
                  accesskey="m">
            Th&ecirc;m <span class="sortKey">m</span>ới </button>
            </span>
            <span id="in">
          <button type="button" onclick="check('print')" class="ButtonCommon"
                  accesskey="i">
            <span class="sortKey">I</span>n </button> 
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
    <html:hidden property="trangthai"/>
    <%-- ************************************--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>