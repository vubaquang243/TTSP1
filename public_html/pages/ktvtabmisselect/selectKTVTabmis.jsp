<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/KTVTabmisListSelectAction.do" method="post">
    <%-- Title--%>
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="ktvtabmis.selectktvtabmis.title"/></span>
          </td>
          <td width="62">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
                 width="62" height="30"/>
          </td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
              width="20%">&nbsp;</td>
          <td width="4">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
                 width="4" height="30"/>
          </td>
        </tr>
      </tbody>
     </table>
    <br/>
		<%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    %>
    <font color="Red" dir="ltr">
      <fmt:message key="<%=StrStatus%>"/>
    </font>
    <%}%>
    <%-- Title Danh sach KTV Tabmis--%>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Điều kiện lọc</font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="99.8%"
                   style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="10%" align="right">Tích chọn : </td>
                <td width="10%">
                  <html:select property="isCheckSort" style="font-size:12px;width:100%" onchange="this.form.submit()">
                    <html:option value="false">Chưa chọn</html:option>
                    <html:option value="true">Đã chọn</html:option>
                  </html:select>
                </td>
                <td width="10%" align="right">Mã TTV : </td>
                <td>
                  <html:text property="ma_ttv" style="font-size:12px;width:100%" onkeypress="if(event.keyCode == 13) this.form.submit()" onblur="this.form.submit()" />
                </td>
                <td width="10%" align="right">Mã KTV Tabmis : </td>
                <td>
                  <html:text property="ma_ktv_tabmis" style="font-size:12px;width:100%" onkeypress="if(event.keyCode == 13) this.form.submit()" onblur="this.form.submit()" />
                    
                </td>
                
                <td></td>
                <td></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="ktvtabmis.selectktvtabmis.title.danhsachktvtabmis"/>
            </font>
          </td>
        </tr>
        <%-- Hiển thị list kho bạc và nhân viên--%>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="99.8%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <tr>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.mansd"/>
                  </div>
                </th>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.tensd"/>
                  </div>
                </th>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.selectktvtabmis.lable.maktvtabmis"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.selectktvtabmis.lable.tenktvtabmis"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <input type="checkbox" name="Checkall" value="yes"
                           onclick="Check(this)"></input>
                  </div>
                </th>
                </tr>
              </thead>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1" id="tbodySelectKTTTamis">
                <logic:notEmpty name="lstKTV">
                  <logic:iterate id="KTVlist" name="lstKTV" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' id='tr_<bean:write name="KTVlist" property="ma_nsd"/>'>
                      <td align="center" width="20%" onclick="Check(this)">
                        <bean:write name="KTVlist" property="ma_nsd"/>
                      </td>
                      <td>
                        <bean:write name="KTVlist" property="ten_nsd"/>
                      </td>
                      <td align="center">
                        <bean:write name="KTVlist" property="ma"/>
                      </td>
                      <td>
                        <bean:write name="KTVlist" property="ten"/>
                      </td>
                      <td align="center" width="5 %">
                        <logic:equal name="KTVlist" property="ischeck"
                                     value="0">
                          <input type="CHECKBOX" name="check"
                                 value='<bean:write name="KTVlist" property="ttv_id"/>_<bean:write name="KTVlist" property="ktv_id"/>'/>
                        </logic:equal>
                         
                        <logic:notEqual name="KTVlist" property="ischeck"
                                        value="0">
                          <input type="CHECKBOX" name="check" checked="checked"
                                 value='<bean:write name="KTVlist" property="ttv_id"/>_<bean:write name="KTVlist" property="ktv_id"/>'/>
                        </logic:notEqual>
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
    <table align="center">
      <tr>
        <td>
          <button type="button" onclick="sbKTVTabmis('save')" accesskey="G"
                  class="ButtonCommon">
            <span class="sortKey">G</span>
            hi
          </button>
           
          <button type="button" onclick="sbKTVTabmis('close')"
                  class="ButtonCommon" style="width:50" accesskey="o">
            Th
            <span class="sortKey">o</span>
            &aacute;t
          </button>
        </td>
      </tr>
    </table>
    <%-- thẻ đóng--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      var trId;
      var classattr;
      //var htmlTBody=jQuery("#tbodySelectKTTTamis").html();
      var countTagTr = jQuery("#tbodySelectKTTTamis").find('tr');
      countTagTr.each(function (index, trEle) {
          if (index == 0)
              trId = trEle.id;
          if (trEle.id != trId) {
              var html = jQuery("#" + trEle.id).html();
              //  var classTr=jQuery("#"+trId).attr('class');
              // jQuery("#"+trId).remove();
              jQuery("#" + trEle.id).replaceWith("<tr style=\"background:yellow;height:1px;\"><td colspan=\"5\"></td></tr><tr>" + html + "</tr>");
              //jQuery("#tbodySelectKTTTamis").append("<tr><td colspan=\"5\">aaaaaaaaaaaaaa</td></tr>");
              trId = trEle.id;
          }

      });

  });
  //  document.getElementById("ma_kb").focus();
  var id = new Array();
  var matches = "/[^0-9][A-Z][a-z]/g";

  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function sbKTVTabmis(type) {
      var check = document.getElementsByName("check");
      var f = document.forms[0];
      if (type == 'save') {
          f.action = 'KTVTabmisAddSelectAction.do';
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
</script>