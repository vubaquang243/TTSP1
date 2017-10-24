<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuyenNhapThuCongResource"/>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/LSuQuyenNhapLenhListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2> <fmt:message key="lsuquyennhap.title"/></span>
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
                <fmt:message key="lsuquyennhap.title.dieukientimkiem"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
           <tr>
            <td align="right" bordercolor="#e1e1e1" width="5%">
                    <fmt:message key="lsuquyennhap.lable.ten.nguoisudung"/>
                   
                  </td>
           <td align="left" width="15%" >
                    <html:text property="ten_nsd"
                     onkeypress="return blockKeySpecial001(event)"
                     
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               size="40%" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
           </td>
                 
                 <td width="5%" >
                 </td>
                 <td width="15%">
                 </td>
           </tr>
           <tr>
            
           
            <td align="right" bordercolor="#e1e1e1" >
                 
                   <fmt:message key="lsuquyennhap.lable.ten.nguoirut"/>
                  </td>
                  <td align="left" >
                    <html:text property="ten_nguoi_rut"
                    onkeypress="return blockKeySpecial001(event)"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               size="40%" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  </td>
                  <td align="right" bordercolor="#e1e1e1"  >
                   <fmt:message key="lsuquyennhap.lable.ten.nguoigan"/>
                   
                  </td>
                  <td align="left" >
                    <html:text property="ten_nguoi_gan"
                    onkeypress="return blockKeySpecial001(event)"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               size="40%" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  </td>
           </tr>
           
            <tr>
             <td align="right">
                
                <fmt:message key="lsuquyennhap.lable.ngay.rut"/>
              </td>
              <td align="left" valign="middle">
              <html:text property="tu_ngay" styleId="tu_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                            onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:70%"/>
                 
                 <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_ngat_btn" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "tu_ngay", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "ngay_ngat_btn"// id of the button
                  });
                </script>
              </td>
              <td align="right">
             
               <fmt:message key="lsuquyennhap.lable.ngay.gan"/>
              </td>
              <td  align="left" valign="middle">
                  <html:text property="den_ngay" styleId="den_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                          onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');textlostfocus(this);"
                            onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:70%"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_noi_btn" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "den_ngay", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "ngay_noi_btn"// id of the button
                  });
                </script>
              </td>
              
               
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- 2 nút, tra cứu , thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
          <span>
            <button type="button" class="ButtonCommon" accesskey="t"
                    onclick="sbKTVTabmis('find')"> 
              <span class="sortKey">T</span>ra cứu
            </button>
          </span>
          <span>
            <button type="button" class="ButtonCommon" accesskey="i"
                  onclick="sbKTVTabmis('print')"> 
              <span class="sortKey">I</span>n
            </button>
          </span>
          <span>
            <button type="button" onclick="sbKTVTabmis('close')"
                  class="ButtonCommon"  accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
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
        <fmt:param>
          <%=id%>
        </fmt:param>
      </fmt:message>
    </font>
    <%}%>
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table border="1" align="center" cellspacing="0" width="100%"
           style="BORDER-COLLAPSE: collapse" bordercolor="#999999">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="lsuquyennhap.title.ketqua"/>
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
                   <fmt:message key="lsuquyennhap.lable.ten.nguoisudung"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"> 
                   <fmt:message key="lsuquyennhap.lable.ngay.rut"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   <fmt:message key="lsuquyennhap.lable.ngay.gan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   <fmt:message key="lsuquyennhap.lable.ten.nguoirut"/>
                  </div>
                </th>
                 <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                  <fmt:message key="lsuquyennhap.lable.ten.nguoigan"/>
                  </div>
                </th>
                 <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                          <fmt:message key="lsuquyennhap.lable.lydo.rut"/>
                  </div>
                </th>
                 <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="lsuquyennhap.lable.lydo.gan"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="ListLSuQNhap">
                  <logic:iterate id="LSuList" name="ListLSuQNhap" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="10%">
                        <bean:write name="LSuList" property="ma_nsd"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="tu_ngay"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="den_ngay"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="ten_nguoi_rut"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="ten_nguoi_gan"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="lido_rut"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="LSuList" property="lido_gan"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="8">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function()
      { 
        jQuery('#ten_nsd').focus();
      });
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function sbKTVTabmis(type) {
var curr_date = showNowDate();
      var f = document.forms[0];
      f.target='';
      if (type == 'close') {
          f.action = 'mainAction.do';
          f.submit();
      }else{

          if (CompareDate(f.tu_ngay.value, f.den_ngay.value) == - 1) {
              alert("Ngày gán phải là ngày trước ngày rút");
              document.getElementById("tu_ngay").focus();
              return;
          }
          if(CompareDate(f.tu_ngay.value,curr_date) == -1){
                alert('Ngày gán không được lớn hơn ngày hiện tại');
             document.getElementById("tu_ngay").focus();
                  return;
          }

//          if (f.tu_ngay.value != null && f.den_ngay.value == null || f.tu_ngay.value != "" && f.den_ngay.value == "") {
//              alert("Bạn phải nhập vào ô ngày rút");
//              document.getElementById("den_ngay").focus();
//              return;
//          }
//          if (f.tu_ngay.value == null && f.den_ngay.value != null || f.tu_ngay.value == "" && f.den_ngay.value != "") {
//              alert("Bạn phải nhập vào ô ngày gán");
//              document.getElementById("tu_ngay").focus();
//              return;
//          }

          if (type == 'find'){
            f.action = 'LSuQuyenNhapLenhListAction.do';
          }else if (type == 'print'){
            f.action = 'LSuQuyenNhapLenhPrintAction.do';
            var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
            newDialog = window.open('', '_form', params);                 
            f.target='_form';
          }
          f.submit();

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
<%@ include file="/includes/ttsp_bottom.inc"%>
