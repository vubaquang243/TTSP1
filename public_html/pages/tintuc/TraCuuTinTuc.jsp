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
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="searchTinTuc.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Quản lý tin tức </span>
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
      <td align="left" >
      <fieldset>
        <legend><font color="Blue">&#272;i&#7873;u ki&#7879;n tra c&#7913;u </font></legend>
        
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
            <tr>
              <td align="right" width="10%">
                  <label for="tieu_de">Tiêu đề</label>
                </td>
                <td  align="left" width="20%">
                  <html:text property="tieu_de"/>
                </td>
              <td align="right" width="10%">
                    Ngày  đăng &nbsp;
                </td>
                <td  align="left" width="20%">
                <html:text property="ngay_dang" styleId="ngay_dang" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_dang');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_dang_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay_dang", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_dang_btn"// id of the button
                      });
                    </script> &nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
            <td colspan="4" align="center">
              <button type="button" onclick="check('find','')" accesskey="t" id="bt">
                <span class="sortKey">T</span>&#236;m ki&#7871;m
              </button> &nbsp;&nbsp;
              <button type="button" onclick="check('create','')" accesskey="i" id="bt">
                &#272;&#259;ng T<span class="sortKey">i</span>n
              </button>
              <button type="button" onclick="check('exit','')" accesskey="i" id="bt">
                Th<span class="sortKey">&#243;</span>at
              </button>
            </td>
            </tr>
        </table>
      </fieldset>
    </td>
  </tr>
  <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
  <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="18%">
                  <div align="center" >
                    Ti&#234;u &#273;&#7873;
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="8%">
                  <div align="center">
                    Ng&#224;y &#273;&#259;ng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    Ng&#224;y h&#7871;t h&#7841;n
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Ng&#432;&#7901;i &#273;&#259;ng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="36%">
                  <div align="center">
                    &#272;&#417;n v&#7883; &#273;&#259;ng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="14%">
                  <div align="center" >
                Tr&#7841;ng th&#225;i
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
  
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
  
                  </div>
                </th>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
              
              <logic:notEmpty name="colTinTuc">
              <logic:present name="colTinTuc" >          
                <logic:iterate id="items" name="colTinTuc" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"> 
                    <%=stt+1+rowBegin%>
                  </td>
                  <td>
                    <bean:write name="items" property="tieu_de"/>
                  </td>
                  <td align="center">
                    <div>
                      <bean:write name="items" property="ngay_dang"/>
                    </div>
                  </td>
                  <td align="center">
                    <div>
                      <bean:write name="items" property="ngay_het_han"/>
                    </div>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ma_nsd"/>
                  </td>
                  <td align="left" style="word-break:break-all">
                    <logic:equal value="TQ" property="dv_dang" name="items">
                      To&#224;n qu&#7889;c
                    </logic:equal>
                    <logic:notEqual value="TQ" property="dv_dang"  name="items">
                      <bean:write name="items" property="dv_dang"/>
                    </logic:notEqual>
                    
                  </td>
                 <td>
                    <logic:equal value="0" property="trang_thai" name="items">
                      Tin &#273;&#432;&#7907;c &#273;&#259;ng
                    </logic:equal>
                     <logic:equal value="1" property="trang_thai" name="items">
                      Tin kh&#244;ng &#273;&#432;&#7907;c &#273;&#259;ng
                    </logic:equal>
                  </td>
                  <td align="center">
                     <a href="<html:rewrite page="/loadTinTuc.do"/>?id=<bean:write name="items" property="id"/>">view</a> 
                  </td>
                  <td>
                    <span id="huy" onclick="check('huy','<bean:write name="items" property="id"/>')"  title="Huy" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/delete-icon.png" /></span>
                  </td>
                </tr>
                </logic:iterate>
                <tr>
                    <td colspan="10">  
                    <input type="hidden" name="certserial" id="certserial"/>
                   <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                    </td>
                </tr>
                </logic:present>
              </logic:notEmpty>
              <logic:empty name="colTinTuc">
                <tr>
                <td colspan="10">
                  <font color="red">Kh&#244;ng c&#243; k&#7871;t qu&#7843;</font>
                </td>
                </tr>
               </logic:empty> 
              </tbody>
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
</table>
 <html:hidden property="pageNumber" styleId="pageNumber"/>
</html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">   
var f = document.forms[0];
  function check(type,id) { 
    
     if (type == 'find') {
        f.action = 'searchTinTuc.do';
      }
      if (type == 'huy') {
        f.action = 'delTinTuc.do?id='+id;
      }
     if (type == 'create') {
        f.action = 'loadTinTuc.do';
      }
      if (type == 'exit') {
        f.action = 'mainAction.do';  
      }
         f.submit();
}
function goPage(page) {
 
      f.pageNumber.value = page;
      f.action = 'searchTinTuc.do?pageNumber='+page;
      f.submit();
  }
  </script>