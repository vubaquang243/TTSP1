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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>
"<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
  type = "text/javascript" > 
</script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
 

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
  <html:form action="loadTCuuDMuc.do" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Tra cứu danh mục </span>
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
          <td width="15%" align="right">
          Lo&#7841;i danh m&#7909;c
          </td width="30%">
          <td  align="left" bordercolor="#e1e1e1" >          
            <html:select property="table_name" styleId="table_name" style="font-size:12px;width:100%" onchange="tbl_name()"
                                   onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                          <html:option value="">Ch&#7885;n danh m&#7909;c</html:option>
                          <html:option value="SP_DM_CAPNS">DM c&#7845;p ng&#226;n s&#225;ch</html:option>
                          <html:option value="SP_DM_CHUONG">DM ch&#432;&#417;ng</html:option>
                          <html:option value="SP_DM_HTKB">DM h&#7879; th&#7889;ng kho b&#7841;c</html:option>
                          <html:option value="SP_DM_MA_QUY">DM m&#227; qu&#7929;</html:option>
                          <html:option value="SP_DM_NGAN_HANG">DM ng&#226;n h&#224;ng</html:option>
                          <html:option value="SP_DM_NGANHKT">DM ng&#224;nh kinh t&#7871;</html:option>
                          <html:option value="SP_DM_NGUONCHI">DM ngu&#7891;n chi</html:option>
                          <html:option value="SP_DM_DVSDNS">DM &#273;&#417;n v&#7883; s&#7917; d&#7909;ng ng&#226;n s&#225;ch</html:option>
                          <html:option value="SP_DM_TIENTE">DM ti&#7873;n t&#7879;</html:option>
                          <html:option value="SP_DM_TKKT">DM t&#224;i kho&#7843;n k&#7871; to&#225;n</html:option>                      
              </html:select>
            </td>
            <td width="15%" align="right">T&#234;n 
            </td>
            <td align="left" width="30%">
                <html:text property="ten" style="width:100%" maxlength="200" styleId="ten"/>
            </td>
            </tr>
            <tr>
            <td align="right">Mã 
            </td>
            <td align="left">
                <html:text property="ma" style="width:70px" maxlength="10" styleId="ma"/>
            </td>
            <td align="right">
            Tr&#7841;ng th&#225;i
            </td>
            <td align="left">
            
            <html:select property="tinhtrang" styleId="tinhtrang" style="font-size:12px;width:40%" onchange="tthaival()"
                                   onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                          <html:option value="">Ch&#7885;n tr&#7841;ng th&#225;i</html:option>
                          <html:option value="1">C&#242;n hi&#7879;u l&#7921;c</html:option>
                          <html:option value="0">H&#7871;t hi&#7879;u l&#7921;c</html:option>                     
            </html:select>
              </td>
            <td  align="left">
           <button type="button" onclick="check('search')" accesskey="t" id="bt">
              <span class="sortKey">T</span>ra Cứu
            </button>
            
          <!--  
          thuongdt-20170310 yeu cau P1 thong nhat bo button them moi
          <button type="button" onclick="check('add')" accesskey="t" id="bt">
              Thêm <span class="sortKey">M</span>ới
            </button>
            -->
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
       <td colspan="2">
        <fieldset>
        <legend><font color="Blue">K&#7871;t qu&#7843; tra c&#7913;u</font></legend>
        <div style="overflow-x: scroll;width:100%" >
          <table  class="data-grid" id="data-grid" 
                                              border="1"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                 <tr>
                 <td align="center" class="ui-state-default ui-th-column">STT</td>
                 <td align="center" class="ui-state-default ui-th-column">Mã</td>
                 <td align="center"  class="ui-state-default ui-th-column">Tên</td>
                 <td align="center"  class="ui-state-default ui-th-column">Trạng thái</td>                                     
                 </tr>
                 
                <logic:empty name="colDMuc">
                  <tr>
                    <td colspan="4">
                      <font color="Red">Kh&#244;ng t&#236;m th&#7845;y th&#244;ng tin th&#7887;a m&#227;n &#273;i&#7873;u ki&#7879;n </font>
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDMuc">
                  <logic:iterate id="items" name="colDMuc" indexId="index">
                 <tr id="row_dts_<bean:write name="index"/>" class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' onmouseout="window.status =''">
                 <td align="center">
                    <%=index+1+rowBegin%>
                  </td>
                  <td  align="center"  onmouseover="style.fontWeight='bold'" onmouseout="style.fontWeight='normal'">
                    <bean:write name="items" property="ma"/>   
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ten"/>  
                  </td>
                  <td  align="center">
                    <logic:equal value="0" property="tinhtrang" name="items">
                      Hết hiệu lực
                    </logic:equal>
                    <logic:equal value="1" property="tinhtrang" name="items">
                      Còn hiệu lực
                    </logic:equal>                   
                  </td>                  
                 </tr>
                  </logic:iterate>
                  <tr>
                    <td align="right" colspan="4">  
                       <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                    </td> 
                 </tr>
                </logic:notEmpty>              
             </table>
            </div>
        </fieldset>
       </td>
      </tr>
    </table>
  </html:form>   
   <html:hidden property="pageNumber" value="1"/>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function tbl_name() {
      var table_name;
      table_name = document.getElementById("table_name").value;
      return table_name;
  }

  function goPage(page) {
      jQuery("#pageNumber").val(page);
      var f = document.forms[0];
      f.action = 'loadTCuuDMuc.do?pageNumber=' + page;
      jQuery("#TCuuDMuc").submit();
  }

  function check(type) {
      var f = document.forms[0];
      if (type == 'search') {
          var table_name = jQuery('#table_name').val();
          if (table_name == null || '' == table_name) {
              alert(GetUnicode('Cần chọn danh mục'));
              jQuery('#table_name').focus();
              return false;
          }
          else if (table_name != null && '' != table_name) {
              document.getElementById("bt").disabled = true;
              f.action = 'loadTCuuDMuc.do';
          }
      }else if (type == 'close') {
          f.action = 'mainAction.do';
      }else  if (type == 'add') {
          f.action = 'addDMuc.do';
      }
      f.submit();
  }
</script>