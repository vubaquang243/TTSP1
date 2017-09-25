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
  <html:form action="QuanLyNSDUpdateExcAction" method="post">
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
           bordercolor="#999999" width="100%" align="left">
      <tbody>
        <tr>
          <td class="title" colspan="6" bordercolor="#e1e1e1"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="quanlynsd.listnsd.title.update"/>
              </font></span>
          </td>
        </tr>
      </tbody>
      <tbody>
      <tr><td>
      <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">
                 <fmt:message key="quanlynsd.listnsd.lable.list.makb"/>
              </td>
              <%-- lay ten va ma kho bac --%>
               <td width="29%" align="left" bordercolor="#e1e1e1">
      <html:text property="ma_kb" styleId="ma_kb" maxlength="4" onblur="getTenKhoBac('ma_kb','ten_kb','kb_id','addNSDLoadDMKBAction.do');textlostfocus(this);"
                           size="4%" disabled="true"
                           onfocus="textfocus(this);" 
                          styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>&nbsp;
                          <html:text property="ten_kb" styleId="ten_kb" disabled="true" styleclass="promptText" size="30%" />
                          <!--<label id="lblNhkb_chuyen" onmouseout="UnTip()"  ></label>-->
                          <html:hidden property="kb_id" styleId="kb_id" />
                          <html:hidden property="ten_kb" styleId="ten_kb" />          
            </td>        
            </tr>
            <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
               <fmt:message key="quanlynsd.listnsd.lable.list.manv"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma_nsd"  disabled="true"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              
              <td width="15%" align="right" bordercolor="#e1e1e1">
              <fmt:message key="quanlynsd.listnsd.lable.list.tennv"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ten_nsd" 
                          onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <tr>
             
                <td width="15%" align="right" bordercolor="#e1e1e1">
             <fmt:message key="quanlynsd.listnsd.lable.list.matabmis"/>
              </td>
               <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma_tabmis"
                onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
               <td width="15%" align="right" bordercolor="#e1e1e1">
             <fmt:message key="quanlynsd.listnsd.lable.list.chucdanh"/>
              </td>
               <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="chuc_danh"
                onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr>
            <tr>
             <td width="15%" align="right" bordercolor="#e1e1e1">
              <fmt:message key="quanlynsd.listnsd.lable.list.trangthai"/>
              </td>
              <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:select property="trang_thai" styleId="abc"   onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:92%">
                 <html:option value="01" > <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.action"/></html:option>
                <html:option value="02"><fmt:message key="quanlynsd.listnsd.lable.list.trangthai.block"/></html:option>
                <html:option value="03" ><fmt:message key="quanlynsd.listnsd.lable.list.trangthai.disable"/></html:option>
               
                </html:select>
              </td> 
              <td width="15%" align="right" bordercolor="#e1e1e1">
              <fmt:message key="quanlynsd.listnsd.lable.list.trangthai.Mac"/>
              </td>
               <td width="29%" align="left" bordercolor="#e1e1e1">
                <html:text property="mac_address"
                onkeypress="return blockKeySpecial001(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="40%"
                           onblur="this.style.backgroundColor='#ffffff'"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr>
          </table>
          </td>
          </tr>
      </tbody>
      <tbody align="center" bordercolor="#e1e1e1">
        <tr>
          <td bordercolor="#e1e1e1">
            <button type="button" onclick="sbbut('save')" accesskey="g"
                    class="ButtonCommon">
              <span class="sortKey">G</span>hi
            </button>
             
            <button type="button" onclick="sbbut('close')" accesskey="o"
                    class="ButtonCommon">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </td>
        </tr>
      </tbody>
    </table>
   <html:hidden property="id" />
    <html:hidden property="ma_nsd" styleId="ma_nsd_hd"/>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
 document.getElementById("ten_nsd").focus();

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'save') {
          if(f.ma_tabmis.value.length >=20)
          {
          alert('mã tabmis không quá 20 kí tự');
           document.getElementById("ma_tabmis").focus();
          return;
          }
          if(f. ten_nsd.value.length >=100)
          {
          alert('Tên nhân viên không được quá 100 kí tự');
           document.getElementById("chuc_danh").focus();
          return;
          }
           if(f.chuc_danh.value.length >=100)
          {
          alert('Mã chức danh không quá 100 kí tự');
           document.getElementById("chuc_danh").focus();
          return;
          } 
          if (f.chuc_danh.value == null || f.chuc_danh.value == "") {
              alert('Bạn phải nhập chức danh');
               document.getElementById("chuc_danh").focus();
              return;
          }
          f.action='QuanLyNSDUpdateExcAction.do';
      }
      if (type == 'close') {
      //alert('asasa');
      f.trang_thai.value = "";
      f.ma_nsd[0].value="";
      f.ma_nsd[1].value="";
      f.ten_nsd.value="";
      f.ten_kb[0].value="";
      f.ten_kb[1].value="";
      f.ma_kb.value ="";
      
      f.action = 'QuanLyNSDListAction.do';
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