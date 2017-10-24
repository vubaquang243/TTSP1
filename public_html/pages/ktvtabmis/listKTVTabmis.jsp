<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function()
      {  
        jQuery('#ma').focus();
      });
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }
  function sbKTVTabmis(type) {
      var f = document.forms[0];
      f.target='';
      f.trangthai.value = "";
       if (type == 'find' || type == 'print') {
        if (f.ma.value.length >= 50) {
              alert('Mã KTV tabmis không quá 50 kí tự');
              document.getElementById("ma").focus();
              return;
        }
          if (f.ten.value.length >=100) {
              alert('Tên KTVtabmis không quá 100 kí tự');
              document.getElementById("ten").focus();
              return;

          }
          if(type == 'find'){
            f.action = 'KTVTabmisListAction.do';
          }else{
            f.action = 'KTVTabmisPrintAction.do';
            var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
            newDialog = window.open('', '_form', params);  
                  
            f.target='_form';
          }
          
       }else if (type == 'add') {
      f.ma.value="";
      f.ten.value="";
      f.kb_id.value="";
          f.action = 'KTVTabmisAddAction.do';
      }else if (type == 'close') {
       f.ma.value="";
      f.ten.value="";
      f.kb_id.value="";
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
    var pattern=/[!@#$%^&*()_+='|`~"\[\]\.\-\,\:\;\{\}\<\>\?\\\/]/;
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
  <html:form action="/KTVTabmisListAction.do" method="post">
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
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="ktvtabmis.listktvtabmis.title.dieukientimkiem"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="95%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="ktvtabmis.listktvtabmis.lable.maktvtabmis"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma" styleId="ma"
                onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="ktvtabmis.listktvtabmis.lable.tenktvtabmis"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ten" 
                onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="5%" align="right">
               <fmt:message key="ktvtabmis.selectktvtabmis.lable.chon.khobac"/>
              </td>
              <td > 
                <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="102"
                       styleClass="fieldText" style="width:150px;" 
                       property="kb_id" styleId="kb_id" >
            <option value="" selected="selected">
            
            </option>
             <logic:notEmpty name="lstKB">
                                    <html:optionsCollection name="lstKB"
                                                            value="id"
                                                            label="ten"/>
                                  </logic:notEmpty>
          </html:select>
          </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
          <span id="tim">
            <button type="button" onclick="sbKTVTabmis('find')" class="ButtonCommon" accesskey="t">
               <fmt:message key="ktvtabmis.listktvtabmis.button.timkiem"/>
            </button>
           </span>
           <span id="them">
          <button type="button" onclick="sbKTVTabmis('add')"
                  class="ButtonCommon" accesskey="m">
            <fmt:message key="ktvtabmis.listktvtabmis.button.them"/>
          </button>
          </span>
          <span id="in">
          <button type="button" onclick="sbKTVTabmis('print')"
                  class="ButtonCommon" accesskey="i">
            <fmt:message key="ktvtabmis.listktvtabmis.button.in"/>
          </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="sbKTVTabmis('close')"
                    class="ButtonCommon" accesskey="o">
              <fmt:message key="ktvtabmis.listktvtabmis.button.thoat"/>
            </button>
          </span>
        </td>
      </tr>
    </table>
    
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
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="ktvtabmis.listktvtabmis.title.ketquatimkiem"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.maktvtabmis"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.tenktvtabmis"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.ngaylap"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.nguoilap"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.sua"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ktvtabmis.listktvtabmis.lable.xoa"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstKTV">
                  <logic:iterate id="KTVlist" name="lstKTV" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="20%">
                        <bean:write name="KTVlist" property="ma"/>
                      </td>
                      <td>
                        <bean:write name="KTVlist" property="ten"/>
                      </td>
                      <td align="center" width="20%">
                        <bean:write name="KTVlist" property="ngay_tao"/>
                      </td>
                      <td align="left" width="20%">
                        <bean:write name="KTVlist" property="ma_nsd"/>
                      </td>
                      
                      <td align="center" width="10%">
                        <a href='<html:rewrite page="/KTVTabmisUpdateAction.do"/>?longid=<bean:write name="KTVlist" property="id"/>
            &longma=<bean:write name="KTVlist" property="ma"/>
            &longkb_id=<bean:write name="KTVlist" property="kb_id"/>
            &strten=<bean:write name="KTVlist" property="ten"/>'>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none"/></a>
                      </td>
                      <td align="center" width="10%">
                        <a href='<html:rewrite page="/KTVTabmisDeleteAction.do"/>?longid=<bean:write name="KTVlist" property="id"/>'
                           onclick="return confirm('<fmt:message key="ktvtabmis.listktvtabmis.warning.xoa"/> ?')">
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                               style="border-style: none"/></a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="7">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
               
              <html:hidden property="trangthai" value=""/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>


<%@ include file="/includes/ttsp_bottom.inc"%>
