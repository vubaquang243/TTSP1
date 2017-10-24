<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyNSDResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#temp').focus();
       enable();
  });
   
  var counter = 0;
  function enable() {
      var f = document.forms[0];
      if (f.enable.value == "1") {
          f.ma_kb.disabled = false;
          f.ma_tabmis.disabled = false;
          f.ten_nhom.disabled = false;
          f.abc.disabled = false;
          f.bfind.disable = false;
          f.mac_address.disable = false;
          document.getElementById("displayname").focus();
      }
      if (f.enable.value == "0") {
          f.displayname.value = "";
          f.telephonenumber.value = "";
          f.title.value = "";
          f.department.value = "";
          f.email.value = "";
          f.description.value = "";
          f.temp.focus();
      }
  }

  function subString() {
      var f = document.forms[0];
      var string = f.temp.value;
      f.domain.value = string.substring(0, 2);
      f.ma_nsd.value = string.substring(3);

  }
  //  function sbcheck() {
  //      var f = document.forms[0];
  //      if (f.trangthai.value == "tontai") {
  //          alert('<fmt:message key="ktvtabmis.listktvtabmis.warning.sua.tontai"/>');
  //      }
  //  }
  //
  function blockKeySpecial(e) {
      var key;
      if (window.event)// IE
      {
          key = e.keyCode;
      }
      else if (e.which)// Netscape/Firefox/Opera
      {
          key = e.which;
      }
      if (key == 37 || key == 38 || key == 39 || key == 40 || key == 16//arrow key
 || key == 35 || key == 36 || key == 9//home,end,tab
 || key == 46 || key == 8//del,insert,backspace
 || key == 45 || (key > 31 && key < 44) || (key > 57 && key < 65) || (key > 90 && key < 97) || (key > 123 && key < 127))//Cac ki tu dac biet
          event.returnValue = false;
      else 
          return event.returnValue = true;
  }

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'find') {
      
          if (f.temp.value.length >= 50) {
              alert('Mã NSD nhập không quá 50 kí tự');
              f.temp.focus();
              return;
          }
          if (f.temp.value == null || f.temp.value == "") {
              alert('Nhập vào mã nhân viên');
              f.temp.focus();
              return;
          }
          subString();
          f.action = 'QuanLyNSDExecuteAction.do';
           f.submit();
          // f.action = 'QuanLyNSDAddExcAction.do';
      }else if (type == 'save') {        
        subString();
        if (f.ma_kb.value.length < 4 && f.ma_kb.value.length >0 ) {    
              alert('Mã kho bạc là mã 4 kí tự');
              document.getElementById("ma_kb").focus();
              return;
        }
        else if (f.temp.value == null || f.temp.value == "") {
            alert('Bạn phải nhập mã nhân viên và tìm kiếm');
            f.temp.focus();
            return;
        }        
        else if (f.ma_kb.value == null || f.ma_kb.value == "") {
            alert('Bạn phải nhập mã kho bạc');
            f.ma_kb.focus();
            return;
        }else if((f.id_nhom.value == 481 || f.id_nhom.value == 482) && f.ma_kb.value != '0003'){
          alert('Chỉ có thể gán cán bộ thuộc SGD cho nhóm này!');
          f.ma_kb.focus();
          return;
        }else if(f.id_nhom.value == null || f.id_nhom.value == ''){
          alert('Phải chọn nhóm NSD.');
          return;
        }else { 
              if(++counter==1){
                f.bfind.disable = true;
                f.action = 'QuanLyNSDAddExcAction.do' ;
                f.submit();

              }
              
        }
          
      }
      if (type == 'close') {
          f.kb_id.value = "";
          f.ma_kb.value = "";
          f.ma_nsd.value = "";
          f.ten_nsd.value = "";
          f.trang_thai.value = "";
          f.action = 'QuanLyNSDListAction.do';
           f.submit();
      }
  }

  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\/\[\]\.\,\:\;\{\}\<\>\?]/;
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
  <html:form action="QuanLyNSDAddExcAction" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
          <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="quanlynsd.listnsd.title"/></span>
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
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="quanlynsd.listnsd.title.add"/>
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
              <tbody>
                <tr>
                  <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
                  </td>
                  <td align="left" width="20%">
                    <html:text property="temp" 
                               onkeypress= "return blockKeySpecial001(event);"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               size="40%" styleClass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                  </td>
                  <td align="left">
                    <button type="button" onclick="sbbut('find')" accesskey="t"
                            class="ButtonCommon">
                      <span class="sortKey">T</span>&igrave;m Kiếm
                    </button>
                  </td>
                </tr>
              </tbody>
               
              <tr>
                <td colspan="3" align="center">
                  &nbsp; 
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
                   &nbsp;
                </td>
              </tr>
            </table>
          </td>
        </tr>
       
      <tbody>
        <tr>
          <td>
            <table width="100%" cellspacing="1" cellpadding="0">
              <tr>
                <td width="45%">
                  <div class="clearfix">
                    <fieldset class="fieldsetCSS" style="width:98%; height:200px">
                      <legend style="vertical-align:middle">
                        <b> <fmt:message key="quanlynsd.addnsd.lable.thongtin"/></b>
                        
                      </legend>
                        <div align="center">
                        <br/>
                          <table style="width:90%" cellspacing="0" cellpadding="2">
                            <tr>
                              <td align="left" class="keyInfo"><fmt:message key="quanlynsd.addnsd.lable.thongtin.hoten"/></td>
                              <td align="left" class="valueInfo">
                                <html:text
                                          style="width:95%;border:1"
                                           onkeypress="return blockKeySpecial001(event)"
                                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                            onfocus="textfocus(this);" 
                                            onblur="textlostfocus(this);"
                                            tabindex="1" styleClass="promptText"
                                           property="displayname"/>
                              </td>
                            </tr>
                             <tr>
                              <td align="left" ><fmt:message key="quanlynsd.addnsd.lable.thongtin.chucvu"/></td>
                              <td align="left">
                                <html:text readonly="true" style="width:95%;border:1" property="title"/>
                              </td>
                            </tr>
                             
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.addnsd.lable.thongtin.phongban"/></td>
                              <td align="left">
                                <html:text
                                           readonly="true"  style="width:95%;border:1" styleClass="promptText"
                                           property="department"/>
                                           
                              </td>
                            </tr>
                             
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.addnsd.lable.thongtin.sodienthoai"/></td>
                              <td align="left">
                                <html:text 
                                           readonly="true"  style="width:95%;border:1" styleClass="promptText"
                                           property="telephonenumber"/>
                              </td>
                            </tr>
                             
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.addnsd.lable.thongtin.email"/></td>
                              <td align="left">
                                <html:text 
                                           readonly="true" style="width:95%;border:1" styleClass="promptText"
                                           property="email"/>
                              </td>
                            </tr>
                             
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.addnsd.lable.thongtin.ghichu"/></td>
                              <td align="left">
                                <html:text 
                                           readonly="true" style="width:95%;border:1" styleClass="promptText"
                                           property="description"/>
                              </td>
                            </tr>
                          </table>
                        </div>
                    </fieldset>
                  </div>
                </td>
                <td width="45%">
                  <div class="clearfix">
                    <fieldset class="fieldsetCSS" style="width:95%; height:200px">
                      <legend style="vertical-align:middle">
                        <b><fmt:message key="quanlynsd.addnsd.lable.thongtin.nhap"/></b>
                      </legend>
                        <div>
                        <br/>
                          <table style="width:90%" cellspacing="0" cellpadding="2" border="0">
                            <tr>
                              <td align="left" style="keyInfo"><fmt:message key="quanlynsd.listnsd.lable.list.makb"/><font color="Red">*</font> :</td>
                              <td align="left" style="valueInfo">
                                <html:text property="ma_kb" styleId="ma_kb" 
                                          onkeypress="return numberBlockKey(event)"
                                           maxlength="4" size="4%"
                                           disabled="true"
                                           tabindex="2"
                                           style="width:20%;"
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                           styleClass="promptText"
                                           onfocus="textfocus(this);" 
                                            onblur="getTenKhoBac('ma_kb', 'ten_kb', 'kb_id','addNSDLoadDMKBAction.do');
                                            textlostfocus(this);"/>                              
                               <html:text property="ten_kb" readonly="true" styleId="ten_kb"  onmouseout="UnTip()"
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;" styleClass="promptText"
                                           style="width:65%; charset=utf-8 ;border:1"/> 
                                <html:hidden property="kb_id" styleId="kb_id"/>
                                
                              </td>
                            </tr>
                             <tr>
                              <td align="left"><fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>:</td>
                              <td align="left" bordercolor="#e1e1e1">
                                <html:text disabled="true"
                                           property="ma_tabmis"
                                           tabindex="3"
                                           onkeypress="return blockKeySpecial001(event)"
                                           onfocus="this.style.backgroundColor='#ffffb5'"
                                           onblur="this.style.backgroundColor='#ffffff'"
                                           styleClass="promptText"
                                           style="width:88%;"
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                              </td>
                            </tr>
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.listnsd.lable.list.nhomnsd"/>:</td>
                              <td align="left" bordercolor="#e1e1e1">
                                <html:select disabled="true" property="id_nhom"
                                             styleId="ten_nhom"
                                             style="width:90%;"
                                             tabindex="4"
                                             onblur="getTenNhom()"
                                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                               <logic:notEmpty name="listNNSD">
                                    <html:optionsCollection name="listNNSD"
                                                            value="id"
                                                            style="width:28%;"
                                                            label="ten_nhom"/>
                                  </logic:notEmpty>
                                </html:select>
                              </td>
                            </tr>
                            <tr>
                              <td align="left"><fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>:</td>
                              <td align="left" bordercolor="#e1e1e1">
                                <html:select disabled="true"
                                tabindex="5"
                                             property="trang_thai" styleId="abc"
                                             style="width:90%;"
                                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
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
                            <tr>
                            <td align="left">  <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.Mac"/>:</td>
                               <td>
                                <html:text property="mac_address" 
                                tabindex="6" style="width:88%;"
                                           onkeypress="return blockKeySpecial001(event)"
                                           onfocus="this.style.backgroundColor='#ffffb5'"
                                           onblur="this.style.backgroundColor='#ffffff'"
                                          styleClass="promptText"
                                          onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                              </td>                 
                            </tr>
                          </table>
                        </div>
                    </fieldset>
                  </div>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </tbody>
       
      <tbody align="center">
        <tr>
          <td>
            <table class="buttonbar" align="center">
              <tr>
                <td>
                <span id="save">
                  <button type="button" onclick="sbbut('save')" accesskey="g" tabindex="7"
                          id="bfind" class="ButtonCommon">
                    <span class="sortKey">G</span>hi
                  </button>
                </span>
                <span>
                  <button type="button" onclick="sbbut('close')" accesskey="o" tabindex="8"
                          class="ButtonCommon">
                    Th<span class="sortKey">o</span>&aacute;t
                  </button>
                </span>
              </td>
            </tr>
            </table>
            <br/>
          </td>
        </tr>
      </tbody>
    </table>
    <html:hidden property="domain"/>
    <html:hidden property="ma_nsd"/>
    <html:hidden property="enable"/>
    <html:hidden property="ten_nsd"/>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
