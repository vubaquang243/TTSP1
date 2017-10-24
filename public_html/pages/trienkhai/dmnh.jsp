<%@ page contentType="text/html; charset=UTF-8"%>


<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/CSS/step.css"/>
<style type="text/css">
	
</style>

<html:form styleId="frmTrienKhai" action="/trienKhaiAction.do">
    <table width="99%">
      <tbody>
        <tr>
          <td><font color="red"><html:errors/></font></td>
        </tr>
      </tbody>
    </table>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          Thiết lập triển khai</span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table> 
    
    <table  class="tableBound">
      <tr>
        <td>
          <!--<ul id="green_and_orange_step_menu">
              <li class="frist fdone">Bước 1</li>
              <li class="inprogress">Bước 2<span></span></li>
              <li>Bước 3<span></span></li>
              <li>Bước 4<span></span></li>
              <li>Bước 5<span></span></li>
              <li>Bước 6<span></span></li>
              <li>Bước 7<span></span></li>
              <li class="last">Bước 8<span></span></li>
          </ul>-->
          <div class="trienkhai-steps">
            <div class="completed-step"><a><span>1</span>SHKB</a></div>
            <div class="active-step"><a><span>2</span>Mã NH KB</a></div>
            <div><a><span>3</span>Mã NH-SHKB</a></div>
            <div><a><span>4</span>SHKB-ĐVNS</a></div>
            <div><a><span>5</span>CN NH</a></div>
            <div><a><span>6</span>Tài khoản</a></div>
            <div><a><span>7</span>Ngày TK</a></div>
            <div><a><span>8</span>Số dư</a></div>
            <div><a><span>9</span>COT</a></div>
          </div>
          <table class="tableTop">
            <tr>
              <td>
                <h3><i><B>KIỂM TRA MÃ NGÂN HÀNG CỦA KHO BẠC</B></i></h3>
                <table width="100%">
                  <tr>
                    <td width="10%" align="right">
                    Mã NH của KB:
                    </td>
                    <td width="10%" align="right">
                    <html:hidden property="type" styleId="type"/>
                    <html:hidden property="type_srv" styleId="type_srv"/>
                    <html:hidden property="step" styleId="step" value="2"/>
                    <html:hidden property="shkb" styleId="shkb"/>
                    <html:hidden property="nh_id" styleId="nh_id"/>
                    <html:text property="ma" styleId="ma" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH: 90%;" maxlength="8"/>
                    </td>
                    <td align="left">
                      <button  id="btn_timKiem"
                                accesskey="K" type="button" onclick="submmitTK('check');"
                              class="ButtonCommon" >
                        Kiểm tra
                      </button>
                    </td>
                  </tr>
                </table>
                <logic:notEqual value="" name="TrienKhaiForm" property="exeption">
                  <font color="Red"><bean:write name="TrienKhaiForm" property="exeption"/></font>
                </logic:notEqual>
                <logic:equal value="" name="TrienKhaiForm" property="exeption">
                  <logic:equal value="INSERT" name="TrienKhaiForm" property="type_srv">
                    <font color="Red">Chưa tìm thấy Mã NH, cần thực hiện thêm mới Mã NH sau đó làm bước kế tiếp:</font>
                  </logic:equal>
                  <logic:equal value="UPDATE" name="TrienKhaiForm" property="type_srv">
                    <font color="Blue">Đã tìm thấy Mã NH, hãy cập nhật thông tin hoặc chuyển bước tiếp theo:</font>
                  </logic:equal>                
                </logic:equal>
                <table width="100%">                  
                  <tr>
                    <td align="right" width="10%">Mã NH của KB:</td>
                    <td align="left"><html:text property="ma_nh" styleId="ma_nh" styleClass="fieldText" readonly="true"
                     style="WIDTH: 30%;" />
                    </td>                   
                  </tr>
                  <tr>
                    <td align="right">Tên:</td>
                    <td align="left"><html:text property="ten" styleId="ten" styleClass="fieldText"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH: 30%;"/>
                    </td>
                  </tr>
                 
                  <tr>
                    <td></td>
                    <td align="left">
                      <button  id="btn_save"
                                accesskey="G" type="button" onclick="submmitTK('save');"
                              class="ButtonCommon" >
                        Ghi
                      </button>
                      <button  id="btn_next"
                                accesskey="G" type="button" onclick="submmitTK('next');"
                              class="ButtonCommon" >
                        Tiếp >>
                      </button>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
</html:form>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function submmitTK(type) {
    if(type == 'next'){
      if('INSERT' == document.forms[0].type_srv.value){
        alert('Chưa hoàn thành bước 2. Cần thêm danh mục HTKB');
        return;
      }
    }else if(type == 'check'){
      if((document.forms[0].ma.value).substring(2, 5) != '701'){
        alert('Định dạng mã NH của kho bạc không đúng');
        document.forms[0].ma.focus();
        return;
      }
    }else if(type == 'save'){
      if((document.forms[0].ma_nh.value).substring(2, 5) != '701'){
        alert('Định dạng mã NH của kho bạc không đúng');
        document.forms[0].ma_nh.focus();
        return;
      }
    }
    document.forms[0].type.value = type;
    document.forms[0].action = "trienKhaiAction.do";       
    document.forms[0].submit();
  }
  window.onload = controlButton;
  function controlButton(){ 
    if(document.forms[0].type_srv.value == null || document.forms[0].type_srv.value == ''){
      document.getElementById("btn_save").disabled = true; 
      document.getElementById("btn_next").disabled = true; 
    }else if(document.forms[0].type_srv.value == 'INSERT'){
      document.getElementById("btn_save").disabled = false; 
      document.getElementById("btn_next").disabled = true; 
      document.getElementById("ma_nh").readOnly = true; 
    }else{
      document.getElementById("btn_save").disabled = false; 
      document.getElementById("btn_next").disabled = false; 
      document.getElementById("ma_nh").readOnly = true; 
    }
  }
</script>