<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
<fmt:setBundle basename="com.seatech.ttsp.resource.ReportsResource"/>
<title>Bảng tổng hợp lệnh thanh toán </title>
   <script type="text/javascript">
     jQuery.noConflict();

  //************************************ LOAD PAGE **********************************
  function formAction(){
//      if(jQuery("#tu_ngay").val()==''){
//        alert('Trường từ ngày không được bỏ trống');
//        jQuery("#tu_ngay").focus();
//        return;
//      }
//      if(jQuery("#kb_id").val()==''){
//        alert('phải chọn ngân hàng kho bạc');
//        jQuery("#kb_id").focus();
//       return false;
//      }
      if(jQuery("#ngan_hang").val()==null || ''==jQuery("#ngan_hang").val()){
        alert('Cần chọn thông tin ngân hàng');
         return false;
      }
      var f = document.forms[0];
         f.action = "bkeLTTRutTienRptAction.do";
      
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
  }
  function thoatForm(){
    var f = document.forms[0];
    f.target='';
    f.action = "thoatView.do";
    f.submit();
  }
</script>
<div class="app_error"><html:errors/></div>
  <html:form styleId="frmBCBKLTT" action="/bkeLTTRutTienAction.do">
     <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2"><fmt:message key="reports.bkrt.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
      </table>  
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="reports.page.dieukien"/>
        </td>
      </tr>
      <tr>
      
      <td align="right">
      Ngân hàng
      </td>
      <td>
        <html:select property="ngan_hang" styleId="ngan_hang" onchange="nhangval()"
                             style="width: 100%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection name="colNH" value="ma_nhang" label="ten_nh"/>
                </html:select>
      </td>
      <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
        <td bordercolor="#e1e1e1">
          <html:select property="loai_tien" styleId="loai_tien" style="width:90%"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <html:option value="VND">VND</html:option>
            <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
          </html:select>
        </td>
    </tr>
      <tr>
        <td align="right" width="25%">
          <label for="tu_ngay">
            <fmt:message key="reports.page.lable.tu_ngay"/>
          </label>
        </td>
       <td width="25%" align="left" valign="middle">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 70px;vertical-align:middle;"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td width="10%" align="right">
          <label for="den_ngay">
            <fmt:message key="reports.page.lable.den_ngay"/>
          </label>
        </td>
       <td width="40%" class="promptText" align="right" valign="middle">
          <html:text property="den_ngay" styleId="den_ngay"
                     styleClass="fieldText" 
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="108"
                     style="WIDTH: 70px;vertical-align:middle;"/>           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_btn" width="20"
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
       <tr>
      
        <td align="right" colspan="4">
          <div style="padding:10px 0px 10px 0px;vertical-align:top; ">
          <button  id="btn_timKiem" 
                      tabindex="111"
                      onclick="formAction();" 
                      accesskey="i" 
                    class="ButtonCommon" >
              <fmt:message key="reports.page.button.in"/>
            </button>
            <button  id="btn_timKiem" 
                      tabindex="111"
                      onclick="thoatForm();"
                      accesskey="i" 
                    class="ButtonCommon" >
              Thoát
            </button>
          </div>
        </td>
       </tr>
      </tr>
    </tbody>
  </table>  
  </html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
initLoad();
 function initLoad(){
   var opLen= document.getElementById('ngan_hang').options.length;
   if(opLen==2){
     jQuery("#ngan_hang option:eq(0)").remove();
   }     
 }
 
 function nhangval() {
    var ngan_hang;
    ngan_hang=document.getElementById("ngan_hang").value;
    return ngan_hang;
  }
 
// function chk_kqdc() {
// var ma_nh= jQuery('#ngan_hang').val();
//  jQuery.ajax( {
//      type : "POST", url : "chkkqdcAction.do", data :  { "ma_nhang" : ma_nh     
//      },   
//      success : function (data, textstatus) {   
//          if (textstatus != null && textstatus == 'success') {
//              if (data != null && ''!=data) {     
//                  jQuery.each(data, function (i, chk_kqua) { 
//                  if(chk_kqua.trang_thai==2 && chk_kqua.ket_qua ==0){
//                   formAction();
//                  } else {
//                    alert('Chưa hoàn thành đối chiếu truyền tin.');
//                    return false;
//                  }           
//                  });
//              }else
//              alert('Chưa hoàn thành đối chiếu truyền tin.');
//                    return false
//          }
//      }, 
//      error : function (textstatus) {
//          alert(textstatus);
//      }
//  });
}
 
 
</script>