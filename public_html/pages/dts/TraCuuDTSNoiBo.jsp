<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuDTSNoiBoResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/xuly.dtsoat.noibo.js"  type="text/javascript"></script>
<title><fmt:message key="DTSoatNoiBo.page.title"/></title>
<title>
  <fmt:message key="DTSoatNoiBo.page.title"/>
</title>
<%
  // tham so truyen vao de focus len lydo_ktt_day_lai
  String strChucDanh = "";  
  strChucDanh = request.getAttribute("chucdanh").toString();   
%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      resetFormDTSTraCuu();
      //*************************************************KHAI BAO BIEN ***********************************
      var den_ngay_lap_field = jQuery("#den_ngay_lap_dien"),
      tu_ngay_lap_field = jQuery("#tu_ngay_lap_dien"), 
      ttv_nhan_field = jQuery("#ttv_nhan"),
      trang_thai_field = jQuery("#trang_thai"),
      ma_nhkb_chuyen_field = jQuery("#nhkb_chuyen"),
      ma_nhkb_nhan_field = jQuery("#nhkb_nhan"),
      id_field = jQuery("#id"), 
      dialog_message = jQuery("#dialog-message"), 
      dialog_confirm = jQuery("#dialog-confirm"),
      fieldNameForcus = jQuery("#fieldNameForcus"),
      frmTraCuuDTS = jQuery("#frmTraCuuDTSNBO");
      allFields = jQuery([]).add(den_ngay_lap_field).add(trang_thai_field).
      add(ttv_nhan_field).add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).
      add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).add(tu_ngay_lap_field)
      .add(id_field);      
      ttv_nhan_field.focus();
      //************************************ dialog message ******************************************************
      dialog_message.dialog( {
          autoOpen : false, height : 200, width : 380, modal : true, buttons :  {
              Ok : function () {
                  jQuery(this).dialog("close");
                  var fieldForcus = fieldNameForcus.val();
                  if (fieldForcus != null && fieldForcus != '') {
                      jQuery("#" + fieldForcus).focus();
                  }

              }
          }
      });

      //************************************ dialog confirm message	***********************************************
      dialog_confirm.dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  jQuery("#frmTraCuuDTSNBO").attr( {
                      action : '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/thoatView.do'
                  });
                  jQuery("#frmTraCuuDTSNBO").submit();
                  jQuery(this).dialog("close");
              },
                "Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });
      //**************************BUTTON Sua CLICK********************************************
      jQuery("#btn_timKiem").click(function () {
          frmTraCuuDTS = document.forms[0]; 
          frmTraCuuDTS.target = '';
          frmTraCuuDTS.action = 'TraCuuDTSoatNoiBo.do';
          
          allFields.removeClass('ui-state-error');
          frmTraCuuDTS.submit();
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {
          if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].submit();
          }
      });
      
  });
   jQuery("#viewDetailsDTSNoiBo").click(function(){
     var action = '<%=AppConstants.ACTION_VIEW_DETAIL%>';
            jQuery.ajax( {
                  type : "POST", url : "DTSoatNoiBo.do", data :  {
                      "action" : action, "ttv_nhan" : jQuery("#ttv_nhan").val(), 
                       "nd" : Math.random() * 100000
                  },
                  success : function (data, textstatus) {
                          window.location.href = "DTSoatNoiBo.do?action='"+action+"'&id='"+id+"'";
                          jQuery("#ttv_nhan").val("asdasd");
                  },
                  error : function (textstatus) {
                      jQuery("#dialog-message").html(textstatus);
                      jQuery("#dialog-message").dialog("open");
                  }
              });
   });
  // In
    function formAction(type){
      var f = document.forms[0];
      if(type=='print'){
        f.action = "tracuudtsNoiBoRptAction.do";
      }
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);  
      f.target='_form';
      f.submit();
    }
  //**************************LINK TRANG CLICK********************************************
  function makeGetRequest(id,type){
    var ttv_nhan = jQuery("#ttv_nhan").val();
    var trang_thai = jQuery("#trang_thai option:selected'").val();
    var tu_ngay = jQuery("#tu_ngay_lap_dien").val();
    var den_ngay = jQuery("#den_ngay_lap_dien").val();
    var nhkb_chuyen = jQuery("#ma_don_vi_tra_soat").val();
    var nhkb_nhan = jQuery("#ma_don_vi_nhan_tra_soat").val();
    var so_dts = jQuery("#id").val();
    var ten_nhkb_chuyen = jQuery("#ten_don_vi_tra_soat").val();
    var ten_nhkb_nhan = jQuery("#ten_don_vi_nhan_tra_soat").val();
    var urlRequest  =null;
    if(type == "true"){
     urlRequest = "DTSoatNoiBoDen.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id+"";
    }else{
     urlRequest = "DTSoatNoiBo.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id+"";
    }
    if(ttv_nhan != null && ttv_nhan != ''){
      urlRequest += "&ttv_nhan="+ttv_nhan+"";
    }
    if(trang_thai != null && trang_thai != ''){
      urlRequest += "&trang_thai="+trang_thai+"";
    }    
    if(tu_ngay != null && tu_ngay != ''){
      urlRequest += "&tu_ngay="+tu_ngay+"";
    }
    if(den_ngay != null && den_ngay != ''){
      urlRequest += "&den_ngay="+den_ngay+"";
    }
    if(so_dts != null && so_dts != ''){
      urlRequest += "&so_dts="+so_dts+"";
    }
    if(nhkb_chuyen != null && nhkb_chuyen != ''){
    urlRequest += "&nhkb_chuyen="+nhkb_chuyen+"";
    }if(nhkb_nhan != null && nhkb_nhan != ''){
      urlRequest += "&nhkb_nhan="+nhkb_nhan+"";
    }
    if(ten_nhkb_chuyen!=null && ten_nhkb_chuyen!= ''){
      urlRequest += "&ten_nhkb_chuyen="+ten_nhkb_chuyen+"";
    }
    if(ten_nhkb_nhan!=null && ten_nhkb_nhan!= ''){
       urlRequest += "&ten_nhkb_nhan="+nhkb_nhan+"";
    }
    
    window.location = urlRequest;
  }
  function goPage(page) {
      jQuery("#frmTraCuuDTSNBO").attr( {
          action : 'TraCuuDTSoatNoiBo.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuDTSNBO").submit();
  }

  //**************************validFormatDate********************************************
  function validFormatDate(field) {
      allFields.removeClass('ui-state-error');
      var fieldId = field.id;
      if (!CheckDate(field)) {
          jQuery("#" + fieldId).val('');
          jQuery("#" + fieldId).addClass("ui-state-error");
          jQuery("#" + fieldId).focus();
      }
  }
</script>
<html:form action="/TraCuuDTSoatNoiBo.do" styleId="frmTraCuuDTSNBO">
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
    <input type="hidden" id="fieldNameForcus"/>
    <div class="app_error"><html:errors/></div>
   <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
       <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
              <span class="title2">
                <fmt:message key="DTSoatNoiBo.page.title"/>
              </font></span>
        </td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
      </tbody>
    </table>
      <table class="tableBound">
        <tbody>
          <tr>
            <td class="title" height="24"
                background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                colspan="8" style="text-align:left;">
              <fmt:message key="DTSoatNoiBo.page.dieukien"/>
          </td>
        </tr>
      
        <tr>
          <td> 
           
          <table width="100%" align="center" class="bordertableChungTu">
           <tr>
            <td align="right" width="14%">
              <label for="nguoi_lap">
                <fmt:message key="DTSoatNoiBo.page.lable.nguoi_lap"/>
              </label>
            </td>
            <td class="promptText" align="right" width="36%">
              <html:text styleId="ttv_nhan" property="ttv_nhan"
                         styleClass="fieldText" style="width:70%;"
                         onblur="textlostfocus(this);" tabindex="1" onkeydown="nextFocus();"
                         onfocus="textfocus(this);"/>
            </td>
            <td width="14%" align="right">
              <label for="trang_thai">
                <fmt:message key="DTSoatNoiBo.page.lable.trang_thai"/>
              </label>
            </td>
            <td class="promptText">
              <html:select styleClass="selectBox" property="trang_thai"
                           styleId="trang_thai" style="width:71%;height:20px"
                           onblur="textlostfocus(this);" tabindex="2"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
                <html:option value="">
                  <fmt:message key="DTSoatNoiBo.page.option.trang_thai.default"/>
                </html:option>
                <html:option value='00'>
                Chờ xử lý
                </html:option>
                <html:option value='01'>
                KTT đẩy lại
                </html:option>
                <html:option value='02'>
                Chờ KTT duyệt
                </html:option>
                <html:option value='03'>
                Đ&atilde; duyệt
                </html:option>
                <html:option value='04'>
                Hủy
                </html:option>
              </html:select>
            </td>
          </tr>         
          <%--<logic:present  name="FlagTTTT">
          asdasd <%=request.getAttribute("FlagTTTT")%>
          </logic:present>--%>
         <!-- neu khong phai tu TTTT thi khong hien thi ra DVKBac
          -->          
          <logic:equal name="FlagTTTT"  value="true">
            <tr>
              <td align="right">
                <label for="ma_don_vi_tra_soat">
                  <fmt:message key="DTSoatNoiBo.page.lable.don_vi_tra_soat"/>
                </label>
              </td>
              <td class="promptText">
                <html:text property="ma_don_vi_tra_soat"
                           styleId="ma_don_vi_tra_soat"
                           onkeypress="return numbersonly(this,event,true) "
                           styleClass="fieldText" style="width:70%;"
                           onblur="textlostfocus(this); getTenNganHang('ma_don_vi_tra_soat', 'ten_don_vi_tra_soat', 'nhkb_chuyen')" 
                           onkeydown="nextFocus();"
                           onfocus="textfocus(this);" tabindex="3"/>
                  <html:text property="ten_don_vi_tra_soat"
                           styleId="ten_don_vi_tra_soat"
                           styleClass="fieldTextTrans" readonly="true"
                           onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 120px;"/>
                <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
              </td>
              <td align="right" width="13%">
                <label for="ma_don_vi_nhan_tra_soat">
                  <fmt:message key="DTSoatNoiBo.page.lable.don_vi_nhan_tra_soat"/>
                </label>
              </td>
              <td class="promptText" colspan="3">
                <html:text property="ma_don_vi_nhan_tra_soat"
                           styleId="ma_don_vi_nhan_tra_soat"
                           onkeypress="return numbersonly(this,event,true) "
                           styleClass="fieldText" style="width:70%;"
                           onblur="textlostfocus(this); getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);" tabindex="4"/>
                  <html:text property="ten_don_vi_nhan_tra_soat"
                           styleId="ten_don_vi_nhan_tra_soat"
                           styleClass="fieldTextTrans" readonly="true"
                           onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 120px;"/>
                <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
              </td>
            </tr>
          </logic:equal>
          <tr>
            <td align="right">
              <label for="tu_ngay_lap_dien">
                <fmt:message key="DTSoatNoiBo.page.lable.tu_ngay_lap_dien"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="tu_ngay_lap_dien"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onKeyPress="return numbersonly(this,event,true) "
                         onblur="validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" property="tu_ngay_lap_dien"
                         style="width:70%;" tabindex="5"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="tu_ngay_lap_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="DTSoatNoiBo.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "tu_ngay_lap_dien", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_lap_btn"// id of the button
                });
              </script>
            </td>
            <td align="right">
              <label for="den_ngay_lap_dien">
                <fmt:message key="DTSoatNoiBo.page.lable.den_ngay_lap_dien"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="den_ngay_lap_dien" tabindex="6" property="den_ngay_lap_dien"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onKeyPress="return numbersonly(this,event,true) "
                         onblur="validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" style="width:70%;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="den_ngay_lap_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="DTSoatNoiBo.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "den_ngay_lap_dien", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "den_ngay_lap_btn"// id of the button
                });
              </script>
            </td>
          </tr>
        <tr>
            <td align="right">
              <label for="id">
                <fmt:message key="DTSoatNoiBo.page.lable.so_dien_tra_soat"/>
              </label>
            </td>
            <td class="promptText" align="left">
              <html:text property="id" styleId="id" styleClass="fieldText" style="width:70%;"
                         onkeypress="return numbersonly(this,event,true)" tabindex="7"  onkeydown="nextFocus();"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <div style="padding:10px 0px 10px 0px; ">
                <button id="btn_timKiem" accesskey="i" type="button"
                        class="ButtonCommon" tabindex="8" value="search">
                  <fmt:message key="DTSoatNoiBo.page.button.search"/>
                </button> 
                <button id="btn_in" accesskey="i" type="button"
                        class="ButtonCommon"  value="print" onclick="formAction('print')">
                  <fmt:message key="DSoatNoiBo.page.button.in"/>
                </button>
              </div>
            </td>
          </tr>
          </table>
          </td>
      </tbody>
    </table>
</html:form>
  <div style="padding:10px 0px 10px 0px;">
    <table border="1" align="center" width="100%" class="borderTableChungTu"
           >
      <thead class="TR_Selected">
        <tr>
          <td class="title" colspan="9">
            <span>
              <fmt:message key="DTSoatNoiBo.page.ketqua"/></span>
          </td>
        </tr>
        <tr>
          <th width="11%"  class="th">
            <fmt:message key="DTSoatNoiBo.page.lable.so_dien_tra_soat"/>
          </th>
          <th width="9%" class="th">
            <fmt:message key="DTSoatNoiBo.page.lable.ngay_lap_dien"/>
          </th>          
          <th class="th">
            <fmt:message key="DTSoatNoiBo.page.lable.don_vi_tra_soat"/>
          </th>
          <th class="th">
            <fmt:message key="DTSoatNoiBo.page.lable.don_vi_nhan_tra_soat"/>
          </th>
          <th width="9%" class="th">
            <fmt:message key="DTSoatNoiBo.page.lable.trang_thai"/>
          </th>
         
          <th width="5%" class="th">&nbsp;</th>
        </tr>
      </thead>
       
      <tbody class="navigateable focused" cellspacing="0" cellpadding="1"
             bordercolor="#e1e1e1">
       
        <logic:notEmpty name="lstTraCuuDTSNoiBo">
          <%
                com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
          <logic:iterate id="items" name="lstTraCuuDTSNoiBo" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' height="20">
              <td align="center">
                <bean:write name="items" property="id"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ngay_nhan"/>
              </td>             
              <td align="left">
                <bean:write name="items" property="ten_don_vi_tra_soat"/>
              </td>
              <td align="left">
                <bean:write name="items" property="ten_don_vi_nhan_tra_soat"/>
              </td>
              <td align="left">
              <logic:equal name="items" property="trang_thai" value='00'>
                Chờ xử l&yacute;
                </logic:equal>
                <logic:equal name="items" property="trang_thai" value='02'>
                Chờ KTT duyệt
                </logic:equal>
                <logic:equal name="items" property="trang_thai" value='01'>
                Đẩy lại
                </logic:equal>
                <logic:equal name="items" property="trang_thai" value='03'>
                Đ&atilde; duyệt
                </logic:equal>
                <logic:equal name="items" property="trang_thai" value='04'>
                Hủy
                </logic:equal>               
              </td>               
              <td align="center">
              <%if("CB-TTTT".equals(strChucDanh)||"CBPT-TTTT".equals(strChucDanh)){%>
                  <logic:equal name="items" property="nhkb_nhan" value="1738">
                       <%--<html:link   href="DTSoatNoiBo.do"  paramId="id" paramName="items" paramProperty="id">Xem DTS</html:link>--%>
                     <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,'true')">XemDTS</a>
                       <!--<a  href='DTSoatNoiBo.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id=<bean:write name="items" property="id"/>'>XemDTS</a>-->
                  </logic:equal>
                  <logic:notEqual name="items" property="nhkb_nhan" value="1738">
                     <a  href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,'false')">XemDTS</a>
                  </logic:notEqual>
                  
              <%}else if(!"CB-TTTT".equals(strChucDanh) && !"CBPT-TTTT".equals(strChucDanh)){%>
                  <logic:equal name="items" property="nhkb_nhan" value="1738">
                     <a  href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,'false')">XemDTS</a>
                  </logic:equal>
                  <logic:notEqual name="items" property="nhkb_nhan" value="1738">
                       <%--<html:link   href="DTSoatNoiBo.do"  paramId="id" paramName="items" paramProperty="id">Xem DTS</html:link>--%>
                     <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,'true')">XemDTS</a>
                       <!--<a  href='DTSoatNoiBo.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id=<bean:write name="items" property="id"/>'>XemDTS</a>-->
                  </logic:notEqual>
              <%}%>
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="6">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
        <%
          if(request.getAttribute("lstTraCuuDTSNoiBo") != null){          
        %>
          <logic:empty name="lstTraCuuDTSNoiBo">
                <tr>
                    <td colspan="6" align="center">
                       <span style="color:red"><fmt:message key="DTSoatNoiBo.error.not_found"/></span>
                    </td>
                </tr>
          </logic:empty>
        <%
        }
        %>
        
      </tbody>
    </table>
  </div>
<div style="padding:10px 10px 10px 0px;float:right ">
  <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon"
          tabindex="23">
    <fmt:message key="DTSoatNoiBo.page.button.exit"/>
  </button>
</div>
<!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm" 
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<!-- ---------------------------------Message --------------------------------->
<div id="dialog-message"
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div> 
<%@ include file="/includes/ttsp_bottom.inc"%>