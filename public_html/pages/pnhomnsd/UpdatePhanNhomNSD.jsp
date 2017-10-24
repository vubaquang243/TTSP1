<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seatech.ttsp.nhomnsd.NhomNSDVO"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.NhomNSDResoure"/>
<%@ include file="/includes/ttsp_header.inc"%>
<div class="box_common_conten">
  <html:form action="QuanLyNhomNSDUpdateAction" method="post">
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
           bordercolor="#999999" width="100%" align="left">
      <tbody>
        <tr>
          <td class="title" colspan="6" bordercolor="#e1e1e1"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="qlynhomnsd.updatephannhomnsd.title.update"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tbody>
        <tr>
          <td>
            <table width="80%" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="0" align="center"
                   style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="10%" align="right">
                  <fmt:message key="qlynhomnsd.listphannhomnsd.lable.manhom"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="id" disabled="true"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                   
                  <html:hidden property="id"/>
                </td>
              </tr>
               
              <tr></tr><tr>
                <td width="10%" align="right">
                  <fmt:message key="qlynhomnsd.updatephannhomnsd.lable.tennsd"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="ten_nhom"
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
                  <fmt:message key="qlynhomnsd.updatephannhomnsd.lable.loainhom"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:select property="loai_nhom" styleId="loai_nhom" disabled="true"
                               onblur="getTenNhom()"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <logic:notEmpty name="listNNSD">
                      <html:optionsCollection name="listNNSD"
                                              value="rv_low_value"
                                              label="rv_meaning"/>
                    </logic:notEmpty>
                  </html:select>
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
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
document.getElementById("ten_nhom").focus();
  function sbcheck() {
      var f = document.forms[0];
      if (f.trangthai.value == "tontai") {
          alert('<fmt:message key="ktvtabmis.listktvtabmis.warning.sua.tontai"/>');
      }
  }

  function sbbut(type) {
      var f = document.forms[0];

      if (type == 'save') {
          if (f.ten_nhom.value.length >= 50) {
              alert('Tên nhóm không được nhập quá 50 kí tự');
              document.getElementById("ten_nhom").focus();
              return;
          }
          if (f.ten_nhom.value == null || f.ten_nhom.value == "") {
              alert('Không được để trống tên nhóm');
              document.getElementById("ten_nhom").focus();
              return;
          }
          f.action = 'QuanLyNhomNSDUpdateExcAction.do';
      }
      if (type == 'close') {
          f.action = 'QuanLyNhomNSDListAction.do';
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