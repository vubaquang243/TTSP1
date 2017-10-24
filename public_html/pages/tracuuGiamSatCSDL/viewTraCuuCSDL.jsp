<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuCSDLResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/TraCuuGiamSatCSDLViewAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="tracuu.csdl.title"/></span>
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
                <fmt:message key="tracuu.csdl.title.chitiet.dulieu"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <table border="2" align="center" width="100%"
                 style="BORDER-COLLAPSE: collapse">
            <tbody>
              <tr>
                <td class="title" colspan="6"
                    background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                    height="24">
                  <font color="Gray">
                    <fmt:message key="tracuu.csdl.title.sqlbin"/>
                  </font>
                </td>
              </tr>
            </tbody>
             
            <tr>
              <td>
                 <html:textarea property="sql_bind" cols="55%" rows="9" />
              </td>
            </tr>
          </table>
        </td>
        <td>
          <table border="2" align="center" width="100%"
                 style="BORDER-COLLAPSE: collapse">
            <tbody>
              <tr>
                <td class="title" colspan="6"
                    background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                    height="24">
                  <font color="Gray">
                    <fmt:message key="tracuu.csdl.title.sqltext"/>
                  </font>
                </td>
              </tr>
            </tbody>
             
            <tr>
              <td>
              <html:textarea property="sql_text" cols="55%" rows="9" />
               
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    
    <%-- ************************************--%>
     <table align="center">
      <tr>
        <td>         
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
        </td>
      </tr>
    </table>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }
  //
  function check(type) {
      var f = document.forms[0];
      if (type == 'add') {
          f.action = '';
      }
      if (type == 'close') {
          f.action = 'TraCuuGiamSatCSDLListAction.do';
      }
      if (type == 'find') {
          f.action = 'TraCuuGiamSatCSDLListAction.do';
      }
      f.submit();
  }
  
</script>