<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TTrangTToanResource"/>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="TTThanhToanMainAction.do" method="post" >
  <input type="hidden" name="check_write" value='<%=request.getAttribute("check_allow_write")%>'>
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2> <fmt:message key="ttrangttoan.ttrangttoan.main"/></span>
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
                <fmt:message key="ttrangttoan.ttrangttoan.title"/>
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
              <%-- lay ten va ma kho bac--%>
            <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="ttrangttoan.ttrangttoan.label.list.nhtm"/> <font color="red">(*)</font>&nbsp;
            </td>
              <td width="40%" align="center" bordercolor="#e1e1e1">
              
               <html:select property="nhkb_nhan" styleId="abc" onchange="change()"
                             style="width: 100%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">-----<fmt:message key="ttrangttoan.ttrangttoan.dmuc.default"/>-----</html:option>
                    <html:optionsCollection name="dmucNH" value="ma_nh" label="ten_nh"/>
                </html:select>                
              </td>
              <td>&nbsp;&nbsp;
            <button type="button" onclick="check('next','')"  accesskey="G">
            <span class="sortKey">G</span>ửi yêu cầu tra cứu
          </button> 
              </td>
            </tr>
            <tr>              
            <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="ttrangttoan.ttrangttoan.label.list.ngay"/>
            </td>
            <td>
          <html:text property="ngay_tao" styleId="ngay_tao" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_tao');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 30%;"/>
           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="ngay_tao_btn" width="20"
               style="vertical-align:middle;"/>
               
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "ngay_tao", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "ngay_tao_btn"// id of the button
            });
          </script>             
            </td>
            </tr>
            <tr><td>&nbsp;</td></tr>
            <tr>            
        <td align="center" colspan="3">
          <button type="button" onclick="check('find')"  accesskey="t">
            <span class="sortKey">T</span>ìm kiếm
          </button>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
        </td>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <br/>
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
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
<table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="ttrangttoan.ttrangttoan.findexc"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.stt"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.mayc"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.nycau"/>
                  </div>
                  </th>
                  <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Ngân hàng
                  </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.tthai"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.makq"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.chitiet"/>
                  </div>
                </th>                
              </thead>
       
              
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                        <logic:notEmpty name="lstUD">
                  <logic:iterate id="UDlist" name="lstUD"
                                 indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                       <td align="center" width="2%">
                       <%=stt+1%>
                      </td>
                      <td align="left" width="10%">
                        &nbsp;<bean:write name="UDlist" property="id"/>
                      </td>
                      <td align="center" width="8%">
                        <bean:write name="UDlist" property="ngay_tao"/>
                      </td>
                      <td align="center" width="15%">
                        <bean:write name="UDlist" property="nhkb_nhan"/>
                      </td>
                      <td align="center" width="8%">                       
                        <logic:equal name="UDlist" property="trang_thai" value="P">
                           <fmt:message key="ttrangttoan.ttrangttoan.label.list.cnhan"/>
                        </logic:equal>
                        <logic:equal name="UDlist" property="trang_thai" value="S">
                          <a href="<html:rewrite page="/THopTToanAction.do"/>?id=<bean:write name="UDlist" property="id"/>">
                        <fmt:message key="ttrangttoan.ttrangttoan.label.list.dnhan"/>
                          </a>                         
                        </logic:equal>
                        <logic:equal name="UDlist" property="trang_thai" value="F">
                           <fmt:message key="ttrangttoan.ttrangttoan.label.list.loi"/>
                        </logic:equal>
                    </td>
                      <td align="left" width="8%">
                        <bean:write name="UDlist" property="ma_kq"/>
                      </td>
                      <td align="center" width="3%"> 
                      
                        <logic:equal name="UDlist" property="trang_thai" value="P">
                           &nbsp;
                        </logic:equal>
                        <logic:equal name="UDlist" property="trang_thai" value="S">
                           <a href="<html:rewrite page="/THopTToanAction.do"/>?id=<bean:write name="UDlist" property="id"/>">
                        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none" />
                          </a>
                        </logic:equal>
                        <logic:equal name="UDlist" property="trang_thai" value="F">
                           &nbsp;
                        </logic:equal>                          
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
    <%-- ************************************--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

  currentdate();
    var f = document.forms[0];
    //document.getElementById("nhkb_nhan").value="";
    
  function check(type) {
  //var f = document.forms[0];
      if (type == 'next') {
        if (f.nhkb_nhan.value != null && f.nhkb_nhan.value !=""){
        alert('Yêu cầu cầu tra cứu đã được gửi tới ngân hàng.');
        f.action = 'GuiYCTraCuuAction.do';
          
        }else{
         alert('Chọn thông tin ngân hàng.');
         f.nhkb_nhan.focus();
          return false;
        }       
      }else if (type == 'close') {
          f.action = 'mainAction.do';
      }else if (type == 'find') {
          if (f.nhkb_nhan.value != null && f.nhkb_nhan.value !=""){
              f.action = 'TTThanhToanMainAction.do';
          }else{
             alert('Chọn thông tin ngân hàng.');
              f.nhkb_nhan.focus();
              return false;
            }
      }
       f.submit();

  }
  
    function goPage(page) {
      //var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  
  }
  
  function change() {
      var ma;
      ma=f.nhkb_nhan.value; 
      return ma;   
  }

  function currentdate(){
  var a;
  a = document.getElementById("ngay_tao").value;
  
  if (a== null || ""==a){
      var currentDate = new Date()
      var day = currentDate.getDate();
      if (day < 10){
         day="0" + day;
      }else{
        day = day;
      }
      var month = currentDate.getMonth() + 1;
      if (month < 10){
         month="0" + month;
      }else{
        month = month;
      }
      var year = currentDate.getFullYear();
      var my_date = " "+day+"/"+month+"/"+year;
      document.getElementById("ngay_tao").value=my_date;
      }
 }
</script>

