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
  <html:form action="/TraCuuGiamSatCSDLListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="tracuu.csdl.title"/></span>
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
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="tracuu.csdl.title.find"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.loaitacdong"/>
              </td>
              <td width="26%" align="left" bordercolor="#e1e1e1">
                <html:select property="loai_tac_dong" styleId="abc"
                             style="width: 92%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value=""></html:option>
                  <html:option value="UPDATE">
                    <fmt:message key="tracuu.csdl.title.loaitacdong.update"/>
                  </html:option>
                  <html:option value="INSERT">
                    <fmt:message key="tracuu.csdl.title.loaitacdong.insert"/>
                  </html:option>
                  <html:option value="DELETE">
                    <fmt:message key="tracuu.csdl.title.loaitacdong.delete"/>
                  </html:option>
                </html:select>
              </td>
              <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.tenbang"/>
              </td>
              <td>
                <html:text property="object_name" styleId="object_name"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr>
            <tr>
              <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.userClient"/>
              </td>
              <td>
                <html:text property="os_user" styleId="os_user"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.hostCSDL"/>
              </td>
              <td>
                <html:text property="userhost" styleId="userhost"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
             
            </tr>
            <tr>
             <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.userCSDL"/>
              </td>
              <td>
                <html:text property="db_user" styleId="db_user"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
               <td width="20%" align="right">
                <fmt:message key="tracuu.csdl.title.mansd"/>
              </td>
              <td>
                <html:text property="client_id" styleId="client_id"
                           onkeypress="return blockKeySpecial001(event);"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="39%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr>
            <tr>
             <td align="right" width="100">
          <label for="NH_chuyen">
            Từ ngày
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
          
        </td>
               <td align="right" width="100">
          <label for="NH_chuyen">
           Đến ngày
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_btn" width="16%"
               style="vertical-align:middle;"/>
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
        </td>
      </tr>
    </table>
    <br/>
    <%-- ************************************--%>
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span>
          <button type="button" onclick="check('find')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>
          </span>
          <span>
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
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
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="tracuu.csdl.title.findexc"/>
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
                    <fmt:message key="tracuu.csdl.title.userCSDL"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.userClient"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.hostCSDL"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.mansd"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.ip.client"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.loaitacdong"/>
                  </div>
                </th>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.loaitruycap"/>
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Thời gian
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="tracuu.csdl.title.chitiet"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstCSDL">
                  <logic:iterate id="CSDList" name="lstCSDL" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                        
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="object_schema"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="os_user"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="CSDList" property="userhost"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="ma_nsd"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="econtext_id"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="statement_type"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="timestamp"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="CSDList" property="object_name"/>
                      </td>
                      <td align="center" width="5%">
                        <a href='<html:rewrite page="/TraCuuGiamSatCSDLViewAction.do"/>?sessionId=<bean:write name="CSDList" property="session_id"/>
            '>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/select.gif"
                               style="border-style: none"/></a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="9">
            <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
          </td>
        </tr>
      </tbody>
       
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function()
      { 
        jQuery('#loai_tac_dong').focus();
      });
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
          f.action = 'mainAction.do';
      }
      if (type == 'find') {
          //        if(f.ma_kb.value == null || f.ma_kb.value == "")
          //          {
          //         
          //              f.kb_id.value ="";
          //            
          //          }
          //  
          //            if (f.ma_nsd.value.length >= 50) {
          //      
          //                alert('mã người sử dụng không quá 50');
          //                      document.getElementById("ma_nsd").focus();
          //                       return;
          //            }
          //            if (f.ten_nsd.value.length >= 50) {
          //                alert('Tên người sử dụng nhập không quá 50');
          //                      document.getElementById("ten_nsd").focus();
          //                
          //                return;
          //            }
          f.action = 'TraCuuGiamSatCSDLListAction.do';
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
      var pattern=/'"/;
//        var pattern=/[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
        if(pattern.match(character)){
               character.replace(character,"");
                return false;
        }else{
              return true;
        }
    }
</script>