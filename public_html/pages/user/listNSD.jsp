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
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#ma_kb').focus();
      if (jQuery('#ma_nsd') != null) {
          if (""!=jQuery('#ma_nsd').val()) {
              jQuery('#ma_nsd').focus();
          }
      }
      if (jQuery('#ten_nsd') != null) {
          if (""!=jQuery('#ten_nsd').val()) {
              jQuery('#ten_nsd').focus();
          }
      }
      if (jQuery('#ma_kb') != null) {
          if (""!=jQuery('#ma_kb').val()) {
              jQuery('#ma_kb').focus();
          }
      }
      
      
      <%
         if(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().equals("0001")){       
      %>
      // TaiDD : Ẩn img sửa và xóa với user đăng nhập QTHT-TW và các user NSD của kho bạc đơn vị.
        // Display theo trDanhSachChan và trDanhSachLe
     jQuery(".trDanhSachChan, .trDanhSachLe").each(function(){
      var nhom = jQuery(this).find("td:eq(4)").text().trim(); // 4 : nhóm quyền NSD
       var maKb = jQuery(this).find("td:eq(5)").text().trim(); // 5 : cột mã kho bạc
       if(maKb !== "0001" && maKb !== "0002" && maKb !== "0003" 
            && nhom.search("QTHT-DV") < 0){
         jQuery(this).find("td:eq(8) img").attr("style","display:none"); // 8 : cột sửa.
         jQuery(this).find("td:eq(9) img").attr("style","display:none"); // 9 : cột xóa.
       }
     });
     
      <%}else if(session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString().contains("QTHT-DV")){%> 
     // TaiDD : Ẩn img sửa và xóa với user đăng nhập QTHT-DV và các user NSD QTHT-DV của kho bạc đơn vị.
      
     jQuery(".trDanhSachChan, .trDanhSachLe").each(function(){
       var nhom = jQuery(this).find("td:eq(4)").text().trim(); // 4 : nhóm quyền NSD
       if(nhom.search("QTHT-DV") >= 0){
         jQuery(this).find("td:eq(8) img").attr("style","display:none"); // 8 : cột sửa.
         jQuery(this).find("td:eq(9) img").attr("style","display:none"); // 9 : cột xóa.
       }
     });
      <%}%>
    
     
    });
    function goPage(page) {
          var f = document.forms[0];
          f.pageNumber.value = page;
          f.submit();
    }
  
    function check(type) {
        var f = document.forms[0];
        f.target='';
        if (type == 'add') {
            f.abc.value = ""
            f.trang_thai.value = "";
            f.kb_id.value = "";
            f.ma_kb.value = "";
            f.ma_nsd.value = "";
            f.ten_nsd.value = "";
            f.ten_kb.value = "";
            // f.ten_kb[1].value ="";
            f.action = 'QuanLyNSDAddAction.do';
        }
        else if (type == 'close') {
            f.action = 'mainAction.do';
        }
        else if (type == 'find') {
            if (f.ma_kb.value == null || f.ma_kb.value == "") {
                f.kb_id.value = "";
            }
            if (f.ma_kb.value.length < 4 && f.ma_kb.value.length > 0) {
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
  
            f.action = 'QuanLyNSDListAction.do';
        }
        else if (type == 'in') {
          f.action = 'QuanLyNSDPrintAction.do';
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);      
          f.target='_form';
        }
        f.submit();
    }

    function blockKeySpecial001(e) {
        //      e.keyCode
        var code;
        if (!e)
           // var e = window.event;
        if (e.keyCode)
            code = e.keyCode;
        else if (e.which)
            code = e.which;
        var character = String.fromCharCode(code);
        var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
        if (pattern.match(character)) {
            character.replace(character, "");
            return false;
        }
        else {
            return true;
        }
    }
    
    
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/QuanLyNSDListAction.do" method="post">
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
              <fmt:message key="quanlynsd.listnsd.title"/></span>
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
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="quanlynsd.listnsd.title.find"/>
              </font>
            </span>
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
                <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
              </td>
              <%-- lay ten va ma kho bac--%>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma_kb"  styleId="ma_kb" maxlength="4"
                           size="4%" onkeypress="return numberBlockKey(event)"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           styleClass="promptText" indexed="ma_kb"
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
    <%-- 4 nút Thêm mới, tra cứu , thoát, in--%>
    <table class="buttonbar" align="center">
      <tr>
        <td>
        <span id="tracuu">
          <button type="button" onclick="check('find')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>
           </span>
           <span id="In">
          <button type="button" onclick="check('in')" class="ButtonCommon"
                  accesskey="I">
            <span class="sortKey">I</span>n
          </button>
           </span>
           <span id="them">
          <button type="button" onclick="check('add')" class="ButtonCommon"
                  accesskey="i">
            Th&ecirc;<span class="sortKey">m</span> mới            
          </button>
           </span>
           <span id="thoat">
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
    <div class="scroll_box">
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
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
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
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
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Nhóm quyền NSD
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                   Mã kb
                  </div>
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
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.sua"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="quanlynsd.listnsd.lable.list.xoa"/>
                  </div>
                </th>
              </thead>
               
              <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstNSD">
                  <logic:iterate id="NSDlist" name="lstNSD" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="3%">
                        <bean:write name="NSDlist" property="domain"/>
                      </td>
                      <td align="left" width="10%">
                        <bean:write name="NSDlist" property="ma_nsd"/>
                      </td>
                      <td align="left" width="15%">
                        <bean:write name="NSDlist" property="ten_nsd"/>
                      </td>
                      <td dalign="left" width="15%">
                        <bean:write name="NSDlist" property="chuc_danh"/>
                      </td>
                      <td dalign="left" width="10%">
                        <bean:write name="NSDlist" property="nhom"/>
                      </td>
                      
                      <td align="left" width="5%">
                        <bean:write name="NSDlist" property="ma"/>
                      </td>
                      <td align="left" width="6%">
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
                      <td align="center" width="2%">
                        <logic:equal value="1" name="NSDlist"
                                     property="trang_thai">
                          <a href='<html:rewrite page="/QuanLyNSDUpdateAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
            &makb=<bean:write name="NSDlist" property="kb_id"/>
            &tennsd=<bean:write name="NSDlist" property="ten_nsd"/>
            &chucdanh=<bean:write name="NSDlist" property="chuc_danh"/>
            &trangthai=<bean:write name="NSDlist" property="trang_thai"/>
            &matabims=<bean:write name="NSDlist" property="ma_tabmis"/>
            &macaddress=<bean:write name="NSDlist" property="mac_address"/>
            &tenmaytruycap=<bean:write name="NSDlist" property="ten_may_truycap"/>
            &usermaytruycap=<bean:write name="NSDlist" property="user_may_truycap"/>
            &masnd=<bean:write name="NSDlist" property="ma_nsd"/>
            '>
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                                 style="border-style: none;"/></a>
                        </logic:equal>
                         
                        <logic:equal value="2" name="NSDlist"
                                     property="trang_thai">
                          <a href='<html:rewrite page="/QuanLyNSDUpdateAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
            &makb=<bean:write name="NSDlist" property="kb_id"/>
            &tennsd=<bean:write name="NSDlist" property="ten_nsd"/>
            &chucdanh=<bean:write name="NSDlist" property="chuc_danh"/>
            &trangthai=<bean:write name="NSDlist" property="trang_thai"/>
            &matabims=<bean:write name="NSDlist" property="ma_tabmis"/>
            &masnd=<bean:write name="NSDlist" property="ma_nsd"/>
            '>
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                                 style="border-style: none;"/></a>
                        </logic:equal>
                         
                        <logic:equal value="3" name="NSDlist"
                                     property="trang_thai">
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none; display:none;"/>
                        </logic:equal>
                      </td>
                      <td align="center" width="2%">
                        <logic:equal value="1" name="NSDlist"
                                     property="trang_thai">
                          <a href='<html:rewrite page="/QuanLyNSDDeleteAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
                        &masnd=<bean:write name="NSDlist" property="ma_nsd"/>'
                             onclick="return confirm('Bạn có muốn xóa NSD này ?')">
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                 style="border-style: none"/></a>
                        </logic:equal>
                         
                        <logic:equal value="2" name="NSDlist"
                                     property="trang_thai">
                          <a href='<html:rewrite page="/QuanLyNSDDeleteAction.do"/>?longid=<bean:write name="NSDlist" property="id"/>
                        &masnd=<bean:write name="NSDlist" property="ma_nsd"/>'
                             onclick="return confirm('Bạn có muốn xóa NSD này ?')">
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                 style="border-style: none"/></a>
                        </logic:equal>
                         
                        <logic:equal value="3" name="NSDlist"
                                     property="trang_thai"></logic:equal>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="10" align="center">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
            </table>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
