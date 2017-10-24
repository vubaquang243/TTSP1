<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strGoPage = request.getAttribute("strGoPage")==null?"":request.getAttribute("strGoPage").toString();
%>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="lstCTietTheoDoiDChieuAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.theodoi"/></span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
   </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">
      <tr>
         <td>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    Tên KB tỉnh
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    Tên KB huyện
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    Tên NH
                  </div>
                </th>
                <%
                  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
                %>
                <logic:notEmpty name="colTDoi">
              <logic:present name="colTDoi" >          
                <logic:iterate id="items" name="colTDoi" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"> 
                    <%=stt+1+rowBegin%>
                  </td>
                  <td>
                    <bean:write name="items" property="ten_kb_tinh"/>
                  </td>
                  <td>
                    <bean:write name="items" property="ten_kb_huyen"/>              
                  </td>
                  <td>
                    <bean:write name="items" property="ten_ngan_hang"/>
                </tr>
                </logic:iterate>
                </logic:present>
                <tr>
                  <td colspan="4">                 
                 <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                  </td>
              </tr>
              </logic:notEmpty>      
              </table>
            </div>
         </td>
      </tr>
      <tr>
      <td align="right">
              <button type="button" onclick="check('close');" class="ButtonCommon" accesskey="t" >
                  Th<span class="sortKey">o</span>&#225;t
              </button>
            </td>
          </tr>
    </table> 
    <html:hidden property="pageNumber" value="1"/>
    <html:hidden property="bkid" styleId="bkid"/>
    <html:hidden property="ttsp" styleId="ttsp"/>
    <html:hidden property="pht" styleId="pht" />
    <html:hidden property="tthai_066" styleId="tthai_066" />
    <html:hidden property="loaiqt" styleId="loaiqt" />
    <html:hidden property="ma_dv" styleId="ma_dv" />
    <html:hidden property="ngay_tdoi" styleId="ngay_tdoi" />
  </html:form>
      
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
var strGoPage='<%=strGoPage%>';
//alert(strGoPage);
function goPage(page) {

var f = document.forms[0];
      f.pageNumber.value = page;
      f.action = 'lstCTietTheoDoiDChieuAction.do?pageNumber='+page; 
      f.submit();
  }
function check(type) { 
   var f = document.forms[0];
     if (type == 'close') {
//        var ma_dv = jQuery('#ma_dv').val(),
//        bkid = jQuery('#bkid').val(),
//        ttsp = jQuery('#ttsp').val(),
//        pht = jQuery('#pht').val(),
//        loaiqt = jQuery('#loaiqt').val(),
//        xnth = jQuery('#xnth').val();
      f.action = 'lstTheoDoiDChieuAction.do';//?ma_dv='+ma_dv+'&bkid='+bkid+'&ttsp='+ttsp+'&pht='+pht+'&loaiqt'+loaiqt+'&xnth='+xnth; 
      } 
       f.submit();
    }
</script>