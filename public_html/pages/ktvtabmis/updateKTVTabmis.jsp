<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_content">
  <html:form action="KTVTabmisUpdateExcAction" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="ktvtabmis.listktvtabmis.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
      </tbody>
    </table>
    
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%" align="left">
      <tbody>
        <tr>
          <td class="title" colspan="6" bordercolor="#e1e1e1"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="ktvtabmis.addktvtabmis.title.suaktvtabmis"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
      
        <td bordercolor="#e1e1e1">
              
           <%-- ************************************--%>
    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
    <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
    <font color="Red" dir="ltr">
       <fmt:message key="<%=StrStatus%>"> 
        <fmt:param><%=id%></fmt:param>
      </fmt:message>
    </font>
    <%}%>
          <br/>
          <table width="45%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tbody>
              <tr>
                <td width="30%" align="left" bordercolor="#e1e1e1">
                  <fmt:message key="ktvtabmis.listktvtabmis.lable.maktvtabmis"/>
                </td>
                <td width="70%" align="left" bordercolor="#e1e1e1">
                  <html:text property="ma" size="50%"
                  onkeypress="return blockKeySpecial001(event)"
                  disabled="true"
                  onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  <html:hidden property="ma" />
                </td>
              </tr>
              <tr>
                <td width="30%" align="left" class="promptText"
                    bordercolor="#e1e1e1">
                  <fmt:message key="ktvtabmis.listktvtabmis.lable.tenktvtabmis"/>
                </td>
                <td width="70%" align="left" class="promptText"
                    bordercolor="#e1e1e1">
                  <html:text property="ten" size="50%"
                  onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr>
              <tr>
                <td>
                  <fmt:message key="ktvtabmis.selectktvtabmis.lable.chon.khobac"/>
                </td>
                <td>
                  <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               tabindex="102" styleClass="fieldText"
                               style="width:150px;" property="kb_id"
                               styleId="kb_id">
                    <option value="" selected="selected">Chọn Kho bạc </option>
                    <logic:notEmpty name="lstKB">
                                    <html:optionsCollection name="lstKB"
                                                            value="id"
                                              label="ten"/>
                    </logic:notEmpty>
                  </html:select>
                </td>
              </tr>
            </tbody>
          </table>
          <br/>
        </td>
      </tr>
       
      <tbody align="center">
        <tr>
          <td>
            <button type="button" onclick="sbKTVTabmis('save')"
                    class="ButtonCommon" accesskey="g">
              <fmt:message key="ktvtabmis.listktvtabmis.button.ghi"/>
            </button>
            <button type="button" onclick="sbKTVTabmis('close')"
                    class="ButtonCommon" accesskey="o">
              <fmt:message key="ktvtabmis.listktvtabmis.button.thoat"/>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <html:hidden property="id"></html:hidden>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  document.getElementById("ma").focus();

  function sbKTVTabmis(type) {
      var f = document.forms[0];
      if (type == 'save') {
          if (f.ten.value.length >= 100) {
              alert('Tên KTVtabmis không quá 100 kí tự');
              document.getElementById("ten").focus();
              return;
          }
          if (f.ten.value == null || f.ten.value == "") {
              alert('<fmt:message key="ktvtabmis.listktvtabmis.warning.sua.tennull"/>');
              document.getElementById("ten").focus();
              var longma = null;
              longma.value = f.ma.value;
              return;
          }
           if(f.kb_id.value.trim() == '' || f.kb_id.value.trim() == 'null'){
        alert('Bạn phải chọn kho bạc.');
        document.getElementById('kb_id').focus();
        return;
      }
      
          else {
              f.action = 'KTVTabmisUpdateExcAction.do';
          }
      }
      if (type == 'close') {
          f.ten.value = "";
          f.id.value = "";
          f.ma.value = "";
          f.kb_id.value="";
          f.ma[1].value = "";
          f.action = 'KTVTabmisListAction.do';
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
   var pattern=/[!@#$%^&*()_+='|`~"\[\]\.\-\,\:\;\{\}\<\>\?\\\/]/;
      if(pattern.match(character)){
             character.replace(character,"");
              return false;
      }else{
            return true;
      }
  }
</script>