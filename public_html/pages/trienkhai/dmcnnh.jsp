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
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/CSS/step.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<style type="text/css">
	/*body { margin:10px; padding:0; font-family:Arial, Helvetica, sans-serif;}
	#green_and_orange_step_menu { margin:0; padding:5px; display:inline-table; list-style:none; }
	#green_and_orange_step_menu li{ display:inline; float:left; background:url(<%=request.getContextPath()%>/styles/images/step_orange_green.gif) no-repeat -50px bottom; height:41px; line-height:41px; font-size:11px; padding:0 30px 0 25px; position:relative; }
	#green_and_orange_step_menu li span{ margin:0px; z-index:2000; background:url(<%=request.getContextPath()%>/styles/images/step_orange_green.gif) no-repeat left bottom; display:block; position:absolute; height:41px; line-height:41px; width:13px; top:0; left:-10px; padding:0;}
	#green_and_orange_step_menu li.inprogress { background-position:-50px 0px} 
	#green_and_orange_step_menu li.inprogress span, #green_and_orange_step_menu li.lastinprogress span{background-position:left 0px }
	#green_and_orange_step_menu li.done {background-position: -50px -41px}
	#green_and_orange_step_menu li.done span, #green_and_orange_step_menu li.ldone span {background-position:left -41px }
	#green_and_orange_step_menu li.frist, #green_and_orange_step_menu li.done, #green_and_orange_step_menu li.inprogress, #green_and_orange_step_menu li.ldone, #green_and_orange_step_menu li.lastinprogress  { color:#FFF;}
	#green_and_orange_step_menu li.frist {background-position: -20px 0px }
	#green_and_orange_step_menu li.fdone { background-position: -20px -41px  }
	#green_and_orange_step_menu li.last {background-position: right bottom }
	#green_and_orange_step_menu li.lastinprogress { background-position: right 0}
	#green_and_orange_step_menu li.ldone { background-position: right -41px}*/
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
            <div class="completed-step"><a><span>2</span>Mã NH KB</a></div>
            <div class="completed-step"><a><span>3</span>Mã NH-SHKB</a></div>
            <div class="completed-step"><a><span>4</span>SHKB-ĐVNS</a></div>
            <div class="active-step"><a><span>5</span>CN NH</a></div>
            <div><a><span>6</span>Tài khoản</a></div>
            <div><a><span>7</span>Ngày TK</a></div>
            <div><a><span>8</span>Số dư</a></div>
            <div><a><span>9</span>COT</a></div>
          </div>
          <table class="tableTop">
            <tr>
              <td>
                <h3><i><B>KIỂM TRA MÃ NGÂN HÀNG</B></i></h3>
                <table width="100%">
                  <tr>
                    <td width="10%" align="right">
                    Mã NH của KB:
                    </td>
                    <td width="10%" align="right">
                    <html:hidden property="type" styleId="type"/>
                    <html:hidden property="type_srv" styleId="type_srv"/>
                    <html:hidden property="step" styleId="step" value="4_"/>
                    <html:hidden property="shkb" styleId="shkb"/>
                    <html:hidden property="ma_nh" styleId="ma_nh"/>
                    <html:hidden property="ma_dvsdns" styleId="ma_dvsdns" />
                    <html:hidden property="nh_id" styleId="nh_id" />
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
                    <td align="left"><html:text property="ma_cn_nh" styleId="ma_cn_nh" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="WIDTH: 30%;"/>
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
      //thuongdt-20170518 bo sung kiem tra dieu kiem ma 31 cua MBBank
      if((document.forms[0].ma.value).substring(2, 4) == '20'||(document.forms[0].ma.value).substring(2, 4) == '31'){
      }else{
        alert('Định dạng mã NH của kho bạc không đúng');
        document.forms[0].ma.focus();
        return;
      }
    }else if(type == 'save'){
//      if((document.forms[0].ma_cn_nh.value).substring(2, 5) != '20'){
//        alert('Định dạng mã NH của kho bạc không đúng');
//        document.forms[0].ma_nh.focus();
//        return;
//      }
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
      document.getElementById("ma_cn_nh").readOnly = true; 
    }else{
      document.getElementById("btn_save").disabled = false; 
      document.getElementById("btn_next").disabled = false; 
      document.getElementById("ma_cn_nh").readOnly = true; 
    }
  }
</script>