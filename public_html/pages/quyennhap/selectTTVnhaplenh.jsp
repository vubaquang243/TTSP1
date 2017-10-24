<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuyenNhapThuCongResource"/>
<script type="text/javascript">
  jQuery.noConflict();
  var id = new Array();
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function sbKTVTabmis(type) {
      var check = document.getElementsByName("check");
      var f = document.forms[0];
      f.trangthai.value = "";
      if (type == 'save') {
          f.action = 'TTVCoQNhapAddExcAction.do';
      }

      if (type == 'close') {
          var checkItem = false;
          for (i = 1;i < f.length;i++) {
              if (f.elements[i].type == 'checkbox' && f.elements[i].checked == true) {
                  checkItem = true;
              }
          }

          f.action = 'mainAction.do';
      }
      f.submit();
  }

  function Check(obj) {
      //if (obj.checked){
      var checkObjs = document.getElementsByName('check');
      for (i = 0;i < checkObjs.length;i++) {
          checkObjs[i].checked = obj.checked;
      }
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
  <html:form action="/TTVCoQNhapListAction.do" method="post">
    <%-- Title--%>
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="ttvcoqnhap.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
      </tbody>
    </table>
    <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    %>
    <script type="text/javascript">      
      alert('Bạn đã ghi thành công !! ');
    </script>
    <!--<div style="margin-left : 100px">
      <font color="Red" dir="ltr">
        <fmt:message key="<%=StrStatus%>"/>
      </font>
    </div>-->
    <%}%>
    <%-- Title Danh sach KTV Tabmis--%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="ttvcoqnhap.title.danhsachttv"/>
            </font>
          </td>
        </tr>
        <%-- Hiển thị list kho bạc và nhân viên--%>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" height="22" width="30%"
                    bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttvcoqnhap.lable.mathanhtoanvien"/>
                  </div>
                </th>
                <th class="promptText" width="40%" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttvcoqnhap.lable.tenthanhtoanvien"/>
                  </div>
                </th>
                <th class="promptText" width="10%" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttvcoqnhap.lable.quyennhaplenh"/>
                     
                    <input type="checkbox" name="Checkall" value="yes"
                           onclick="Check(this)"></input>
                  </div>
                </th>
              </thead>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstTTV">
                  <logic:iterate id="TTVlist" name="lstTTV" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="20%" onclick="Check(this)">
                        <bean:write name="TTVlist" property="ma_nsd"/>
                      </td>
                      <td>
                        <bean:write name="TTVlist" property="ten_nsd"/>
                      </td>
                      <td align="center" width="5 %">
                        <logic:equal name="TTVlist"
                                        property="trang_thai_chon" value="0">
                          <input type="CHECKBOX" name="check"
                                 value='<bean:write name="TTVlist" property="nsd_id"/>'/>
                        </logic:equal>
                         
                        <logic:equal name="TTVlist"
                                        property="trang_thai_chon" value="1">
                          <input type="CHECKBOX" name="check" checked="checked"
                                 value='<bean:write name="TTVlist" property="nsd_id"/>'/>
                        </logic:equal>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- 2 button ghi và thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
        <span>
          <button type="button" onclick="sbKTVTabmis('save')" accesskey="G" style="width:30"
                  class="ButtonCommon">
            <span class="sortKey">G</span>hi
          </button>
        </span>
        <span>
          <button type="button" onclick="sbKTVTabmis('close')"
                  class="ButtonCommon" style="width:50" accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
        </span>
        </td>
      </tr>
    </table>
    <html:hidden property="trangthai" value=""/>
    <%-- thẻ đóng--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
