<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuLSuTacNghiepResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<title><fmt:message key="tra_cuu_lsu_tacnghiep.page.title"/></title>
<script type="text/javascript">
  jQuery.noConflict();
   jQuery(document).ready(function () {
    document.getElementById("ma_kb").focus();
    jQuery("#btn_thoat").click(function (){
        if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].target='';
              document.forms[0].submit();
          }
     });
   jQuery("#btn_timKiem").click(function (){
        var tu_ngay=document.getElementById('tu_ngay').value;
        var den_ngay=document.getElementById('den_ngay').value;
        var ma_kb=document.getElementById('ma_kb').value;
            if (CompareDate(tu_ngay, den_ngay) ==  - 1) {
                  alert('Bạn phải nhập đến ngày lớn hơn hoặc bằng từ ngày ');
                 document.getElementById('den_ngay').focus(); 
                  return false;
             }
              if (ma_kb.length<8 && ma_kb.length>0){
        alert('Mã kho bạc phải là mã 8 số ');
                 document.getElementById('ma_kb').focus(); 
                  return false;
       }
            if (document.getElementById('ma_kb').value.length>0 && document.getElementById('id_kb').value =="") {
                document.getElementById('id_kb').value ="0";
           }
              document.forms[0].action = 'tracuuLSuTacNghiep.do';
              document.forms[0].target='';
              document.forms[0].submit();
     });
     jQuery("#btn_in").click(function (){
        var tu_ngay=document.getElementById('tu_ngay').value;
        var den_ngay=document.getElementById('den_ngay').value;
        var ma_kb=document.getElementById('ma_kb').value;
            if (CompareDate(tu_ngay, den_ngay) ==  - 1) {
                  alert('Bạn phải nhập đến ngày lớn hơn hoặc bằng từ ngày ');
                 document.getElementById('den_ngay').focus(); 
                  return false;
             }
              if (ma_kb.length<8 && ma_kb.length>0){
                  alert('Mã kho bạc phải là mã 8 số ');
                 document.getElementById('ma_kb').focus(); 
                  return false;
       }
            if (document.getElementById('ma_kb').value.length>0 && document.getElementById('id_kb').value =="") {
                document.getElementById('id_kb').value ="0";
           }
              document.forms[0].action = 'inLSuTacNghiep.do';
              var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
              newDialog = window.open('', '_form', params);                 
              document.forms[0].target='_form';
              document.forms[0].submit();
     });
    });
    function nextOnEnter(obj,e) {
        var e=(typeof event!='undefined')?window.event:e;// IE : Moz 
         if(e.keyCode==13){ 
           var ele = document.forms[0].elements; 
           for(var i=0;i<ele.length;i++){ 
             var q=(i==ele.length-1)?0:i+1;// if last element : if any other 
             if(obj==ele[i]){ele[q].focus();break} 
           } 
        return false; 
         } 
      }
      // returns an array containing all input text/submit fields with a
      // tabindex attribute, in the order of the tabindex values
//      function getTabbableFields(){
//          var ret = [],
//              inpts = document.getElementsByTagName('input'), 
//              i = inpts.length;
//          while (i--){
//              var tabi = parseInt(inpts[i].getAttribute('tabindex'),10),
//                  txtType = inpts[i].getAttribute('type');
//                  // [txtType] could be used to filter out input fields that you
//                  // don't want to be 'tabbable'
//                  ret[tabi] = inpts[i];
//          }
//          return ret;
//      }
      function getTabbableField1s(){
          var ret = [],
              inpts = document.getElementsByTagName('select'), 
              i = inpts.length;
          while (i--){
              var tabi = parseInt(inpts[i].getAttribute('tabindex'),10),
                  txtType = inpts[i].getAttribute('type');
                  // [txtType] could be used to filter out input fields that you
                  // don't want to be 'tabbable'
                  ret[tabi] = inpts[i];
          }
          return ret;
      }
      
      
      function getTen_NH(textma,textten,textid) {
          v_ten = textten;   
          v_id = textid;
       var ma_nh =document.getElementById(textma).value;      
       
         if (ma_nh.length<8 || ma_nh.length>8){
          if (ma_nh.length<8 && ma_nh.length>0){
          document.getElementById(textten).value = "";
         alert('Mã kho bạc phải là mã 8 số ');
       document.getElementById('ma_kb').focus(); 
                  return false;
        }
         document.getElementById(textten).value = "";
         document.getElementById(textma).value = "";
         document.getElementById(textid).value ="";
 
       }else{
       
        var objJSON = {"ma" : ma_nh};  
        var strJSON = encodeURIComponent(JSON.stringify(objJSON));  
        new Ajax.Request("loadTenDMNH.do",
         {
           method: "post",
           async: false,
           parameters: "strJSON=" + strJSON,
           onSuccess: onSuccessNH,
    
           onError: onError,
           onLoading: onLoading
     });
	 }
 }
 function onSuccessNH(transport, json){  
      if(json == null){      
       	document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
      }else if(json.executeError != null && json.executeError != 'null'){
        document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
        alert(convertToUTF8(json.executeError));
      }else if(json.ten != null && json.ten != 'null'){
        if(json.ten != '0'){          
          document.getElementById(v_ten).value = convertToUTF8(json.ten);
          document.getElementById(v_id).value = json.id;
        }else{
          document.getElementById(v_ten).value = "";
          document.getElementById(v_id).value = "";
          alert("Mã kho bạc không tồn tại.");
           document.getElementById('ma_kb').focus(); 
           return false;
        }
      }      
   }
   function onLoading(){
        
      	document.getElementById('indicator').style.display = "";               
   }
   function onError(){
       alert("Error");
   } 
   function isGreaterCurrDate(dateValue){
    var d=new Date();
    var y=d.getFullYear();
    var m=d.getMonth()+1;
    var day=d.getDate();
    if (m<10) m="0"+m;
    if (day<10) day="0"+day;
    var curDate=day+"/"+m+"/"+y
    
    date1 = new Date(dateValue);	
    date2 = new Date(curDate);
    if(CompareDate(curDate, dateValue) ==  - 1)
      return  true;
    else
      return false;
  }
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      document.forms[0].action = 'tracuuLSuTacNghiep.do';
      document.forms[0].submit();
  }
</script>
<input type="hidden" id="fieldNameForcus"/>
<html:form action="/loadLSuTacNghiep.do" styleId="frmTraCuuLSu_TacNghiep">
  <html:hidden property="search_dts" value="true"/>
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
  <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr >
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
               width="13" height="30"/>
        </td>
       <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title" style="height:15px;">
              <font  size="2" >
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.title"/>
              </font></span>
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

      <table  border="1" cellspacing="0" class="bordertableChungTu" width="100%">
      <tbody>
        <tr>
          <td class="title" >
            <span>
              <fmt:message key="tra_cuu_lsu_tacnghiep.page.dieukien"/></span>
          </td>
        </tr>
      
        <tr>
          <td> 
           
          <table width="100%" cellspacing="0" cellpadding="0" align="center" class="bordertableChungTu">
           <tr>
            <td align="right" width="20%">
              <label for="ma_khobac">
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.ma_khobac"/>
              </label>
            </td>
            <td class="promptText" align="right" width="36%">
            <html:text property="ma_kb" tabindex="1" styleId="ma_kb" styleClass="fieldText"
                  style="width:20%;" maxlength="8" onkeypress="return numbersonly(this,event,true)"
                    onkeydown="return nextOnEnter(this,event);"
                    onblur="textlostfocus(this);getTen_NH('ma_kb','ten_kb','id_kb');" onfocus="textfocus(this);"/>
                <html:text tabindex="2"  property="ten_kb" styleId="ten_kb" 
                           styleClass="fieldTextTrans" readonly="true"  
                           onkeydown="return nextOnEnter(this,event);"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                     style="WIDTH: 75%;"/>
                 <html:hidden property="id_kb" styleId="id_kb"/>
            </td>
            <td width="14%" align="right">
              <label for="ma_nhanvien">
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.ma_nhanvien"/>
              </label>
            </td>
            <td class="promptText">
              <html:text styleId="ma_nv" tabindex="3" property="ma_nv" 
                         styleClass="fieldText" style="width:70%;" maxlength="50"
                         onblur="textlostfocus(this);" 
                         onkeydown="return nextOnEnter(this,event);"
                         onfocus="textfocus(this);"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="tu_ngay">
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.tu_ngay"/>
              </label>
            </td>
          <td class="promptText" align="right">
              <html:text styleId="tu_ngay" tabindex="4"
                         onfocus="textfocus(this);"  
                         onkeydown="return nextOnEnter(this,event);"
                         onblur="validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" property="tu_ngay"
                         style="width:62%;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="tu_ngay_lap_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "tu_ngay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_lap_btn"// id of the button
                });
              </script>
            </td>
            <td align="right" width="13%">
              <label for="den_ngay">
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.den_ngay"/>
              </label>
            </td>
           <td class="promptText" align="right">
              <html:text styleId="den_ngay" property="den_ngay" tabindex="5"
                         onblur="javascript:mask(this.value,this,'2,5','/');validFormatDate(this);textlostfocus(this);"
                         onfocus="textfocus(this);"  onkeydown="return nextOnEnter(this,event);"
                         styleClass="fieldText" style="width:62%;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="den_ngay_lap_ltt_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.format_date"/></span>
              <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "den_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "den_ngay_lap_ltt_btn"// id of the button
                    });
              </script>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="loai_lenh">
                <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.loai_lenh"/>
              </label>
            </td>
            <td class="promptText" align="left" colspan="2">
                   <html:select styleClass="selectBox" property="loai_lenh_tt"
                           styleId="loai_lenh_tt" style="width:50%;"
                           onblur="textlostfocus(this);"  onkeydown="return nextOnEnter(this,event);"
                           onfocus="textfocus(this);">
                <html:option value="">
                  <fmt:message key="tra_cuu_lsu_tacnghiep.page.option.loai_lenh.default"/>
                </html:option>
                 <html:option value="01"> Lệnh thanh toán đi
                </html:option>
                 <html:option value="02"> Lệnh thanh toán đến
                </html:option>
                </html:select>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
                <tr>
                  <td align="center">
                    <span>
                      <button id="btn_timKiem" accesskey="t" type="button"
                              class="ButtonCommon"  value="search">
                        <fmt:message key="tra_cuu_lsu_tacnghiep.page.button.search"/>
                      </button>
                      </span>
                      <span>
                      <button id="btn_in" accesskey="i" type="button"
                              class="ButtonCommon"  value="print">
                        <fmt:message key="tra_cuu_lsu_tacnghiep.page.button.print"/>
                      </button>
                      </span>
                      <span>
                       <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon" >
                    <fmt:message key="tra_cuu_lsu_tacnghiep.page.button.exit"/>
                     </button>
                      </span>
                    </td>
                  </tr>
                </table>
            </td>
          </tr>
          </table>
          </td>
      </tbody>
    </table>
</html:form>
  <div style="padding:10px 0px 10px 0px;">
  <logic:empty name="lstlsutacnghiep">
   <%
    if(request.getAttribute("lstlsutacnghiep") != null){
    %>
  <font color="Red" dir="ltr">
      Không có kết quả tìm kiếm
    </font>
    <% }%>
  </logic:empty>
    <table border="2" align="center" width="100%" class="borderTableChungTu">
      <thead class="TR_Selected">
        <tr>
          <td class="title" colspan="6">
            <span>
              <fmt:message key="tra_cuu_lsu_tacnghiep.page.ketqua"/></span>
          </td>
        </tr>
        <tr>
          <th width="20%"  class="th">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.so_yctt"/>
          </th>
          <th width="14%" class="th">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.ma_nhanvien"/>
          </th>
          <th width="18%" class="th">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.hoten_nv"/>
          </th>
            <th width="14%" class="th">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.ma_nguoi_daylai"/>
          </th>
          <th class="th" width="18%">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.nguoi_daylai"/>
          </th>
          <th class="th">
            <fmt:message key="tra_cuu_lsu_tacnghiep.page.lable.thoigian_daylai"/>
          </th>
        </tr>
      </thead>
       
      <tbody class="navigateable focused" cellspacing="0" cellpadding="1"
             bordercolor="#e1e1e1">
       
        <logic:notEmpty name="lstlsutacnghiep">
          <%
                com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
          <logic:iterate id="items" name="lstlsutacnghiep" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' height="20">
              <td align="center">
                <bean:write name="items" property="so_yctt"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_ttt"/>
              </td>
              <td align="left">
                <bean:write name="items" property="ten_ttt"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_ktt_duyet"/>
              </td>
              <td align="left">
                <bean:write name="items" property="ten_ktt_duyet"/>
              </td>
              <td align="center">
                <bean:write name="items" property="tgian_ghi"/>
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="6">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
    </table>
  </div>
<%@ include file="/includes/ttsp_bottom.inc"%>