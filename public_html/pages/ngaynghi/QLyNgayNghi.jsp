<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.utils.DateUtils "%>
<%@ page import="java.util.Calendar "%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QLyNgayNghiResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>

<title><fmt:message key="ngaynghi.page.title"/></title>
<title>
  <fmt:message key="ngaynghi.page.title"/>
</title>
<script type="text/javascript">
 jQuery.noConflict();
  jQuery(document).ready(function () {
    jQuery("#ngaytk").focus();
   jQuery("#btn_timKiem").click(function (){
        var frm = document.forms[0];
        frm.action = 'xemNgayNghi.do';
        frm.target='';
        frm.submit();
     });
     jQuery("#btn_thoat").click(function (){
        if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].target='';
              document.forms[0].submit();
          }
     });
     jQuery("#btn_ghingaynghi").click(function (){
      if('<%=request.getAttribute("colNgayNghi")%>'=="null"){
      alert('Bạn phải chọn nút tìm kiếm trước khi nhấn nút ghi');
      return;
      }
          if (confirm('Bạn có chắc chắn muốn ghi lại ngày nghỉ này?') == false)
              return false;
          else {
           document.forms[0].action = 'ghiNgayNghi.do';
           document.forms[0].target='';
           document.forms[0].submit();
          }
          });
     jQuery("#btn_in").click(function (){
        var frm = document.forms[0];
        frm.action = 'inNgayNghi.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);              
        frm.target='_form';
        frm.submit();
     });
  });
	
  function checkNgay(fieldtext) {
    var value= fieldtext.value.trim();
    if (value!='' &&(value>31  ||value <1))
    {
      alert ('Nhập ngày trong khoảng từ 1 đến 31');
      fieldtext.focus();
      return false;
    }
  }
</script>
<%
  Calendar calendar = Calendar.getInstance();
  int iCurrYear = calendar.get(Calendar.YEAR);
  int iCurrMonth = calendar.get(Calendar.MONTH) + 1;
%>
<html:form action="/xemNgayNghi.do" styleId="frmTraCuuNgayNghi">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr >
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
               width="13" height="30"/>
        </td>
       <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2" style="height:15px;">
              <font color="#006699" size="2" >
                <fmt:message key="ngaynghi.page.title.dieukientimkiem"/>
              </font></span>
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
  <table  border="1" cellspacing="0" class="bordertableChungTu" width="100%">
      <tbody>
        <tr>
          <td class="title" >
            <span>
              <fmt:message key="ngaynghi.page.title.dieukientimkiem"/></span>
          </td>
        </tr>
        <tr>
          <td> 
            <table width="80%" cellspacing="0" cellpadding="0"  align="center" class="bordertableChungTu">
             <tr>
              <td align="right" width="14%">
                <label for="ngay">
                  <fmt:message key="ngaynghi.page.lable.ngay"/>
                </label>
              </td>
              <td class="promptText" align="right">
                <html:text styleId="ngaytk" property="ngaytk" styleClass="fieldText" style="width:120px;" onkeypress="return numbersonly(this,event,true)" maxlength="2"
                 onblur="textlostfocus(this);checkNgay(this);" onfocus="textfocus(this);"/>
              </td>
              <td  align="right">
                <label for="thang">
                  <fmt:message key="ngaynghi.page.lable.thang"/>
                </label>
              </td>
              <td class="promptText"> 
              <!--HungBM20170324 sua loi trung thang begin-->
                <html:select styleClass="selectBox" property="thangtk" styleId="thang" style="width:80px;"
                  onblur="textlostfocus(this);"  onfocus="textfocus(this);">
                  
                  <html:option  value='<%="" + iCurrMonth%>'>
                  Tháng <%=iCurrMonth%>
                    </html:option>
                  <% 
                  int i =1;                  
                  while (i<=12)
                    { 
                    if(i!=iCurrMonth){
                  %>
                  <html:option  value='<%=""+i%>'>
                  Tháng <%=i%>
                    </html:option>
                    
                     <%}i=i+1;}%>
                </html:select>
                <!--HungBM20170324 sua loi trung thang end-->
              </td>
               <td  align="right">
                <label for="nam">
                  <fmt:message key="ngaynghi.page.lable.nam"/>
                </label>
              </td>
               <td class="promptText">
                <html:select styleClass="selectBox" property="namtk" styleId="nam" style="width:100px;"
                  onblur="textlostfocus(this);" onfocus="textfocus(this);">                  
                    <html:option value='<%="" + iCurrYear%>'>
                    Năm <%=iCurrYear%>
                    </html:option>
                  <%
                    int i =2010;
                    while (i<=2020)
                    { 
                  %>
                  <html:option value='<%=""+ i%>'>
                  Năm <%=i%>
                  </html:option>
                    <%i=i+1;}%>
                </html:select>
              </td>
              <td>
                <button id="btn_timKiem" accesskey="t" type="button" 
                            class="ButtonCommon"  value="search">
                      Tìm kiếm
                </button>
              </td>
              <td>
                <button id="btn_in" accesskey="i" type="button" 
                            class="ButtonCommon"  value="in">
                      In
                </button>
              </td>
            </tr>
            </table>
          </td>
      </tbody>
    </table>
  <div style="padding:10px 0px 10px 0px;height:300px; overflow-x:hidden; overflow-y:scroll">
    <table border="2" align="center" width="100%" class="borderTableChungTu" > 
        <tr>
          <td class="title" colspan="4">
            <span>
              <fmt:message key="ngaynghi.page.title.ketquatimkiem"/></span>
          </td>
        </tr>
        <tr>
          <th width="15%"  class="th">
            Thứ
          </th>
          <th width="25%"  class="th">
            <fmt:message key="ngaynghi.page.lable.ngay"/>
          </th>
          <th width="5%" class="th">&nbsp;
          </th>
          <th class="th">&nbsp;
          </th>
        </tr>
   </table>    
   <table cellspacing="0" cellpadding="1" border="2" class="borderTableChungTu" width="100%" align="center">
      <tbody class="navigateable focused" 
             bordercolor="#e1e1e1">
        <logic:notEmpty name="colNgayNghi">
          <logic:iterate id="items" name="colNgayNghi" indexId="stt" type="com.seatech.ttsp.ngaynghi.NgayNghiVO">
            <tr  height="20" class=' <%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' >
              <td align="left"  width="15%">
                <font class='<%=items.getDay_of_week()  == 1 || items.getDay_of_week()  == 7 ? "trChuNhat" :"" %>' >
                  <logic:equal value="1" name="items" property="status">
                    <font color="Red"><b>
                      <logic:equal value="1" name="items" property="day_of_week">
                        Chủ nhật                        
                      </logic:equal>
                      <logic:notEqual value="1" name="items" property="day_of_week">
                        Thứ <bean:write name="items" property="day_of_week"/>
                      </logic:notEqual>
                    </b></font>
                  </logic:equal>
                  <logic:equal value="0" name="items" property="status">                    
                      <logic:equal value="1" name="items" property="day_of_week">
                        Chủ nhật                        
                      </logic:equal>
                      <logic:notEqual value="1" name="items" property="day_of_week">
                        Thứ <bean:write name="items" property="day_of_week"/>
                      </logic:notEqual>                  
                  </logic:equal>
                </font>
              </td>
              <td align="left"  width="25%">
              <font class='<%=items.getDay_of_week()  == 1 || items.getDay_of_week()  == 7 ? "trChuNhat" :"" %>' >
                <logic:equal value="1" name="items" property="status">
                  <font color="Red"><b>
                  <%
                    String strDate = "";
                      strDate=DateUtils.LongToStrDateDDMMYYYY(items.getNgay());               
                  %>
                  <%=strDate%>
                  </b></font>
                </logic:equal>
                <logic:equal value="0" name="items" property="status">
                  <%
                    String strDate = "";
                      strDate=DateUtils.LongToStrDateDDMMYYYY(items.getNgay());               
                  %>
                  <%=strDate%>
                </logic:equal>
                </font>                
              </td>             
              <td align="center" width="5%"  >
               <logic:equal value="0" name="items" property="status">
                <input type="checkbox" name="chklist"  value='<bean:write name="items" property="ngay"/>'/>
                 <!--20170301-thuongdt sua cap nhat ngay nghi theo tung ngay begin -->
                <!--<input  type = "hidden" name = "dlist" value='<bean:write name="items" property="ngay"/>'/>-->
                <!--20170301-thuongdt sua cap nhat ngay nghi theo tung ngay end -->
               </logic:equal>
                <logic:equal value="1" name="items" property="status">
                 <input type="checkbox" name="chklist"  checked="checked" value='<bean:write name="items" property="ngay"/>'>
                 <input  type = "hidden" name = "dlist" value='<bean:write name="items" property="ngay"/>'/>
               </logic:equal>
              </td>
              <td align="left">  
               <logic:equal value="1" name="items" property="status">
                 <font color="Red"><b>Ngày nghỉ</b></font>
               </logic:equal>
              </td>
            </tr>
         </logic:iterate>
        </logic:notEmpty>
      </tbody>
    </table>
  </div>
<div style="padding:10px 10px 10px 0px;float:right ">
<button id="btn_ghingaynghi" type="button" accesskey="g" class="ButtonCommon" >
    <fmt:message key="ngaynghi.page.button.ghi"/>
  </button>
  <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon">
    <fmt:message key="ngaynghi.page.button.thoat"/>
  </button>
</div>

</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>