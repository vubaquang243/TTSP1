<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.NhomNSDResoure"/>
<%@ include file="/includes/ttsp_header.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
    jQuery('#ten_nhom').focus();     
  });
 
 
  function sbbut(type) {
      var f = document.forms[0];
     
      if (type == 'save') {
       if(f.ten_nhom.value.length >=50 )
      {
      alert('Tên nhóm không được nhập quá 50 kí tự');
      document.getElementById("ten_nhom").focus();
      return;
      }
      if(f.ten_nhom.value == null ||f.ten_nhom.value =="" )
      {
      alert('Không được để trống tên nhóm');
      document.getElementById("ten_nhom").focus();
      return;
      }
          f.action = 'QuanLyNhomNSDAddExcAction.do';
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
<div class="box_common_conten">
  <html:form action="QuanLyNhomNSDAddAction" method="post">
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
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="qlynhomnsd.addphannhomnsd.title.add"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
        <tr>
          <td>
            <table width="80%" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="0" align="center"
                   style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="10%" align="right">
                  <fmt:message key="qlynhomnsd.addphannhomnsd.lable.tennsd"/> 
                </td>
               
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="ten_nhom" 
                              onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             style="width:50%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr>
               
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="qlynhomnsd.addphannhomnsd.lable.loainhom"/> 
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:select property="loai_nhom" 
                               onblur="getTenNhom()" style="width:51%"
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
       
      <tbody align="center" bordercolor="#e1e1e1">
        <tr>
          <td bordercolor="#e1e1e1">
          <table class="buttonbar" align="center">
            <tr>
              <td>
              <span id="tracuu">
                <button type="button" onclick="sbbut('save')" accesskey="g"
                        class="ButtonCommon">
                  <span class="sortKey">G</span>hi
                </button>
                </span>
                <span>
                <button type="button" onclick="sbbut('close')" accesskey="o"
                        class="ButtonCommon">
                  Th<span class="sortKey">o</span>&aacute;t
                </button>
              </span>
              </td>
            </tr>
          </table>
          </td>
        </tr>
      </tbody>
    </table>
    
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>


