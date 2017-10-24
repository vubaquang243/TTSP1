<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyNSDResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/QuanLyNhomNSDViewAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="qlynhomnsd.listphannhomnsd.title"/> </span>
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
                <fmt:message key="quanlynsd.listnsd.title.find"/>
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
                 <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.manhom"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
             
                <html:text property="ten_nhom" disabled="true" styleClass="fieldTextTrans"/>
                <html:hidden property="ten_nhom" />
                
                            </td>
                            <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.loainhom"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
             
                <html:text property="loai_nhom" disabled="true" styleClass="fieldTextTrans"/>
                <html:hidden property="loai_nhom" />
                
                            </td>
              
                 
                 </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
              </td>
              <%-- lay ten va ma kho bac--%>
              <td width="29%" align="left" bordercolor="#e1e1e1">
             
                <html:text property="ma_kb" styleId="ma_kb" maxlength="4"
                           size="4%"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="textfocus(this);" 
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           styleclass="promptText" indexed="ma_kb"
                           onblur="getTenKhoBac('ma_kb', 'ten_kb', 'kb_id','addNSDLoadDMKBAction.do');textlostfocus(this);"/>&nbsp; 
                
                 <html:text property="ten_kb" readonly="true" styleId="ten_kb"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 168px; charset=utf-8 ;"/>
                           
                 
                <!--<label id="lblNhkb_chuyen" onmouseout="UnTip()"  ></label>-->
                 
                <html:hidden property="kb_id" styleId="kb_id" value=""/>
                
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma_nsd"
                            onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr><tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.tennv"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ten_nsd"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:select property="trang_thai" styleId="abc"
                             style="width: 92%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.all"/>
                  </html:option>
                  <html:option value="01">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                  </html:option>
                  <html:option value="02">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.block"/>
                  </html:option>
                  <html:option value="03">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.disable"/>
                  </html:option>
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
    <table align="center">
      <tr>
        <td>
          <button type="button" onclick="check('find')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>          
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>&aacute;t
          </button>
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
    <html:hidden property="temp" value="<%=StrStatus%>" />
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
              <fmt:message key="quanlynsd.listnsd.title.findexc"/>
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
                  <div align="center">Domain</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.tennv"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.chucdanh"/>
                  </div>
                  <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
                  </div>
                </th>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstNhomNSD">
                  <logic:iterate id="NSDlist" name="lstNhomNSD">
                    <tr>
                      <td align="left" width="3%">
                        <bean:write name="NSDlist" property="domain"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="NSDlist" property="ma_nsd"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="NSDlist" property="ten_nsd"/>
                      </td>
                      <td align="left" width="22%">
                        <bean:write name="NSDlist" property="chuc_danh"/>
                        </td>
                        <td align="left" width="8%">
                        <bean:write name="NSDlist" property="ma"/>
                      </td>
                      
                      <td align="left" width="10%">
                        <bean:write name="NSDlist" property="ma_tabmis"/>
                      </td>
                      
                      <td align="left" width="8%">
                        <logic:equal value="01" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/>
                        </logic:equal>
                         
                        <logic:equal value="02" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.block"/>
                        </logic:equal>
                         
                        <logic:equal value="03" name="NSDlist"
                                     property="trang_thai">
                          <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.disable"/>
                        </logic:equal>
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
              <html:hidden property="id_nhom" />
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
  var f = document.forms[0];
  var f = document.forms[0];
  f.ma_kb.focus();
  document.getElementById("ma_kb").focus();
  if (f.temp.value != null || f.temp.value  != "") {
      
      if (f.ma_nsd.value != null ) {
      if( f.ma_nsd.value != ""){
          document.getElementById("ma_nsd").focus();
      }
      }
      if (f.ten_nsd.value != null) {
      if(f.ten_nsd.value != ""){
          document.getElementById("ten_nsd").focus();
      }
           }
      if (f.ma_kb.value != null ) {
      if(f.ma_kb.value != "")
      {
          document.getElementById("ma_kb").focus();
      }
     
      }
  }
  else {
   alert('thử nghiệm2');
      document.getElementById("ma_kb").focus();
  }
  var f = document.forms[0];
  // document.getElementById("ma_kb").focus(true);
  // f.ma_kb.focus=true;
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      if (type == 'close') {
          f.action = 'QuanLyNhomNSDListAction.do';
      }
      if (type == 'find') {
      
        if (f.ma_kb.value.length < 4) {
    
              alert('Mã kho bạc là mã 4 kí tự');
                    document.getElementById("ma_kb").focus();
                     return;
          }

          if (f.ma_nsd.value.length >= 50) {
    
              alert('mã người sử dụng không quá 50');
                    document.getElementById("ma_nsd").focus();
                     return;
          }
          if (f.ten_nsd.value.length >= 50) {
              alert('Tên người sử dụng nhập không quá 50');
                    document.getElementById("ten_nsd").focus();
              
              return;
          }
          //          if ((f.ma_kb.value == "" || f.ma_kb.value == null) && (f.ten_kb.value == "" || f.ten_kb.value == null)&&(f.ten_nsd.value == "" || f.ten_nsd.value == null) && (f.ten_nsd.value == "" || f.ten_nsd.value == null) &&(f.ma_nsd.value == "" || f.ma_nsd.value == null) && (f.ma_nsd.value == "" || f.ma_nsd.value == null)) {
          //              alert('Bạn phải nhập ít nhất 1 trường');
          //              return;
          //          }
          //           if ((f.ma_nsd.value == "" || f.ma_nsd.value == null) && (f.ma_nsd.value == "" || f.ma_nsd.value == null)) {
          //              alert('Bạn phải nhập trường mã nhân viên');
          //              return;
          //          }
          //          if ((f.ten_nsd.value == "" || f.ten_nsd.value == null) && (f.ten_nsd.value == "" || f.ten_nsd.value == null)) {
          //              alert('Bạn phải nhập trường tên  nhân viên');
          //              return;
          //          }
          else {
        
              f.action = 'QuanLyNhomNSDViewAction.do';
          }
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
      var pattern=/[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if(pattern.match(character)){
             character.replace(character,"");
              return false;
      }else{
            return true;
      }
  }
</script>