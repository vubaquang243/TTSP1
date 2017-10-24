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
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuyetToanToanQuocResource"/>
<%
   String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
   //20171005 thuongdt bo sung them format so tien theo loai tien tuong ung start
   String loai_tien = request.getAttribute("loai_tien")==null?"":request.getAttribute("loai_tien").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {


  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="TCuuBKeQToanList.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="QTToanQuoc.page.tcuu.title"/></span>
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
        <fieldset>
        <legend>&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m</legend>
        <div>
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="2" >
              <tr>
              <td align="right"  width="20%">
              <fmt:message key="QTToanQuoc.page.tcuu.sobke"/> &nbsp;
              </td>
              <td width="20%">
                 <html:text property="so_bke" styleClass="fieldText" styleId="so_bke" style="width:60%" maxlength="20"/>
              </td>
               <td    width="15%" align="right" bordercolor="#e1e1e1">
                    <fmt:message key="QTToanQuoc.page.tcuu.tthai"/>&nbsp;
                  </td>
                  <td  width="30%">
                    <html:select property="trang_thai" styleId="trang_thai" style="width:40%" onchange="tthaival()"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                        <html:option value="01">Ch&#7901; duy&#7879;t</html:option>
                        <html:option value="02">&#272;&#227; duy&#7879;t</html:option>
                        <html:option value="03">&#272;&#7849;y l&#7841;i</html:option>
                        <html:option value="04">H&#7911;y</html:option>
                         <html:option value="">T&#7845;t c&#7843;</html:option>
                    </html:select>
                  </td>
                  <td  align="right" >Lo&#7841;i ti&#7873;n</td>
                          <td align="left" >
                              <html:select styleClass="selectBox" property="tcg_loai_tien" 
                                           styleId="tcg_loai_tien"
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                <html:option value="">--chọn--</html:option>          
                                <html:option value="VND">VND</html:option>
                                <html:optionsCollection value="ma" label="ma" name="tienTe"/>
                              </html:select>
                          </td>
              </tr>
              <tr>
                <td align="right">
                    <fmt:message key="QTToanQuoc.page.tcuu.tungay"/>&nbsp;
                 </td>
                 <td>
                <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:40%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_btn"// id of the button
                      });
                    </script>
                </td>
                <td align="right">
                <fmt:message key="QTToanQuoc.page.tcuu.denngay"/>&nbsp;
                </td>
                <td>
                <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:25%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                  </td>
              </tr>
           </table>
         </div>
        </fieldset>
       </td>
       <tr>
          <td align="center">
            <button type="button" onclick="check('find')" class="ButtonCommon" accesskey="t">
              <span class="sortKey">T</span>&#236;m ki&#7871;m
              </button>
          
          </td>
      </tr>
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
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    <fmt:message key="QTToanQuoc.page.tcuu.sobke"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.ngayttoan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.sotien"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.nguoitao"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.ngaytao"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.nguoiduyet"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="QTToanQuoc.page.tcuu.ngayduyet"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center" >
                    <fmt:message key="QTToanQuoc.page.tcuu.tthai"/>
                  </div>
                </th>
                
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">             
              <logic:notEmpty name="colTCuu">
              <logic:present name="colTCuu" >          
                <logic:iterate id="items" name="colTCuu" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                    <a href="<html:rewrite page="/ViewDetailBKeQToan.do"/>?so_bke=<bean:write name="items" property="id"/><%=tcuu%>">
                      <bean:write name="items" property="id"/>
                    </a>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_htoan"/>
                  </td>
                  <td align="right">
                  <!--20171005 thuongdt bo sung them format so tien theo loai tien tuong ung start-->
                    <%
                    if(loai_tien.equals("") || loai_tien.equals("VND") ||loai_tien.equals("VNĐ")){
                    %>
                    <bean:write name="items" property="so_tien" format="###,###"/>
                    <%}else{%>
                    <bean:write name="items" property="so_tien" format="###,###.##"/>
                    <%}%>
                  <!--20171005 thuongdt bo sung them format so tien theo loai tien tuong ung stop-->
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_nguoi_tao"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_tao"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_nguoi_ks"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ks"/>
                  </td>
                  <td align="center">
                      <logic:equal value="01" property="trang_thai" name="items">
                        Ch&#7901; duy&#7879;t
                      </logic:equal>
                      <logic:equal value="02" property="trang_thai" name="items">
                        &#272;&#227; duy&#7879;t
                      </logic:equal>
                      <logic:equal value="03" property="trang_thai" name="items">
                        &#272;&#7849;y l&#7841;i
                      </logic:equal>
                      <logic:equal value="04" property="trang_thai" name="items">
                        H&#7911;y
                      </logic:equal>
                  </td>
                </tr>
                </logic:iterate>
                </logic:present>
              </logic:notEmpty>
              <logic:empty name="colTCuu">
                <tr>
                <td colspan="9" align="center">
                  <font color="red"><fmt:message key="QTToanQuoc.page.tcuu.empty"/></font>
                </td>
                </tr>
               </logic:empty> 
              </tbody>
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
       <tr>
          <td >                 
         <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
          </td>
      </tr>

      
    </table> 
    <html:hidden property="pageNumber" value="1"/>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">    

  function check(type) { 
  var f = document.forms[0];
     if (type == 'find') {
        f.action = 'ViewTCuuBKeQToan.do';     
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
        function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.action = 'ViewTCuuBKeQToan.do';  
      f.submit();
  
  }
    
   function tthaival() {
      var tthai;
      tthai=jQuery("#trang_thai").val();
      return tthai;
      
  }
</script>