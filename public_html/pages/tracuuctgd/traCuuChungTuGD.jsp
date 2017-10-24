<%@ page contentType="text/html;charset=UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.ltt.LTTTraCuuVO"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.seatech.framework.utils.DateUtils"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuChungTuGDResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"  type="text/javascript"></script>
<title><fmt:message key="tracuu_ctgd.page.title"/></title>
<%
  String strMa_nhkb_nhan = "";
  if(request.getAttribute("MaNhkbNhan") != null){
    strMa_nhkb_nhan = (String)request.getAttribute("MaNhkbNhan");
  }
  int stt_dong = 0;
  Long previousIdLtt;
  Long lastIdLttOnPage;
%>
<script type="text/javascript">
   var target='<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
    
   jQuery(document).ready(function()
   {
        var tu_ngay_field =  jQuery("#ngay_tt"),
            den_ngay_field =  jQuery("#ngay_ct"),
            so_ltt_field =  jQuery("#id"),
            so_tien_field = jQuery("#so_tien" ),
            ma_nhkb_chuyen_field =  jQuery("#ma_nhkb_chuyen"),
            ma_nhkb_nhan_field = jQuery("#ma_nhkb_nhan" ),
            
            dialog_message=jQuery("#dialog-message"),
            dialog_confirm=jQuery("#dialog-confirm"),
            fieldNameForcus=jQuery("#fieldNameForcus"),
            frmTracuuCTGD=jQuery("#frmTracuuCTGD");
            allFields = jQuery([]).add( tu_ngay_field ).add( den_ngay_field ).add( so_ltt_field ).
              add( so_tien_field ).add( ma_nhkb_chuyen_field ).add( ma_nhkb_nhan_field );
              
        //dialog message
        dialog_message.dialog({
          autoOpen: false,
          modal: true,
          buttons: {
            Ok: function() {
             jQuery(this).dialog( "close" );
              var fieldForcus=fieldNameForcus.val();
              if(fieldForcus!=null && fieldForcus!=''){
                jQuery("#"+fieldForcus).focus();
              }
             
            }
         }
        });
        
        //dialog confirm message	
        dialog_confirm.dialog({
          autoOpen: false,
          resizable: false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            "Có": function() {
              jQuery( this ).dialog( "close" );
              frmTracuuCTGD.attr({action:'thoatView.do'});
              frmTracuuCTGD.submit();
            },
            "Không": function() {
              jQuery( this ).dialog( "close" );
            }
          }
        });
        //************************************ HIDE-SHOW BUTTON*****************************************************         
//        var elementLinkTraSoat=document.getElementsByName("tra_soat");
//        var elementLinkViewLTT=document.getElementsByName("view_ltt");
//        if(target!=null && target!=''){
//         jQuery.each(elementLinkTraSoat,function(i) {
//           jQuery("#tra_soat_"+i).show();
//          });
//          jQuery.each(elementLinkViewLTT,function(i) {
//            jQuery("#view_ltt_"+i).hide();
//          });
//        }else{
//          jQuery.each(elementLinkTraSoat,function(i) {
//           jQuery("#tra_soat_"+i).hide();
//          });
//          jQuery.each(elementLinkViewLTT,function(i) {
//            jQuery("#view_ltt_"+i).show();
//          });
//        }
				//**************************BUTTON Tim kiem CLICK********************************************
        jQuery("#btn_timKiem").click(function() {
          var flag=true;
          allFields.removeClass('ui-state-error');
          var tu_ngay = tu_ngay_field.val(),
             den_ngay = den_ngay_field.val();
             var tungay = toDate(tu_ngay, 'dd/mm/yyyy'),
             denngay = toDate(den_ngay, 'dd/mm/yyyy');
            
            if((tu_ngay != null && tu_ngay != '') && (den_ngay != null && den_ngay != '')){
              if (tungay.getTime() > denngay.getTime()) {
                //dialog_message.html('<fmt:message key="tracuu_ctgd.page.message.tu_ngay_nho_hon_den_ngay"/>');
                dialog_message.html('Từ ngày phải nhỏ hơn đến ngày!');
                dialog_message.dialog("open");
                fieldNameForcus.val("tu_ngay");
                tu_ngay_field.addClass( "ui-state-error" );
                flag=false;
            }
           }
           if(flag){
            frmTracuuCTGD.attr({action:'traCuuCTGD.do'+target});
            frmTracuuCTGD.submit();
           }   
          });
          //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            dialog_confirm.html('<fmt:message key="tracuu_ctgd.page.message_confirm.thoat"/>');
            dialog_confirm.dialog( "open" );
         });
         
      });
      //**************************LINK TRANG CLICK********************************************
         function goPage(page) {
            jQuery("#frmTracuuCTGD").attr({action:'traCuuCTGD.do'+target});
            jQuery("#pageNumber").val(page);
            jQuery("#frmTracuuCTGD").submit();
        }
        function go2Url(strUrl, so_yctt){
            window.location.href=(strUrl);
            loadDetailLTTJson('lttView.do', so_yctt, '', '');
        }
     
</script>

<html:form action="/traCuuCTGD.do" styleId="frmTracuuCTGD">
  <input type="hidden" id="fieldNameForcus"/>
  <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr>
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width="13" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T2.jpg" width="50%">
          <span class="title2"><fmt:message key="tracuu_ctgd.page.title"/></span>
        </td>
        <td width="62">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"  width="62" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="45%">&nbsp;</td>
        <td width="4">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width="4" height="30"></img>
        </td>
      </tr>
    </tbody>
  </table>
   <table style="border-collapse:collapse;" class="bordertableChungTu" border="0" cellspacing="0" width="100%">
      <tbody>
        <tr>
          <td class="title" height="24" background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg" colspan="8" style="text-align:left;">
            <fmt:message key="tracuu_ctgd.fieldset.title.dieu_kien_tk"/>
          </td>
        </tr>
        <tr>
          <td align="right" width="25%">
            <label for="ma_nhkb_nhan"><fmt:message key="tracuu_ctgd.page.label.ma_kb_nhan"/></label>
          </td>
          <td class="promptText" align="right" width="25%">
            <html:select styleClass="fieldText" style="width:150px;" property="ma_nhkb_nhan" styleId="ma_nhkb_nhan">
              <html:option value="">
                Chọn
              </html:option>
              <logic:notEmpty name="colDMNH">
                <logic:iterate id="dmnh" name="colDMNH">
                  <logic:equal value="<%=strMa_nhkb_nhan%>" name="dmnh" property="ma_nh">
                    <option value='<bean:write name="dmnh" property="ma_nh" />' selected="selected"> <bean:write name="dmnh" property="ma_nh" /> </option>
                  </logic:equal>
                  <logic:notEqual value="<%=strMa_nhkb_nhan%>" name="dmnh" property="ma_nh">
                    <option value='<bean:write name="dmnh" property="ma_nh" />'> <bean:write name="dmnh" property="ma_nh" /> </option>
                  </logic:notEqual>
                </logic:iterate>
              </logic:notEmpty>
            </html:select>
          </td>
          <td align="right" width="25%">
             <label for="ma_nhkb_chuyen"><fmt:message key="tracuu_ctgd.page.label.ma_nh_chuyen"/></label>
          </td>
          <td class="promptText" width="25%" align="right">
            <html:text property="ma_nhkb_chuyen" styleId="ma_nhkb_chuyen" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleClass="fieldText" style="width:150px;"/>            
          </td>          
        </tr>
        <tr>
          <td  align="right" width="25%">
            <label for="id"><fmt:message key="tracuu_ctgd.page.label.so_ltt"/></label>
          </td>
          <td class="promptText" width="25%">
            <html:text onmouseover="Tip(this.value)" onKeyPress="return numbersonly(this,event,true)" onmouseout="UnTip()" maxLength="20" styleId="id" styleClass="fieldText" property="id" style="width:150px;"/>
          </td>          
          <td align ="right" width="25%">
            <label for="so_tien"><fmt:message key="tracuu_ctgd.page.label.so_tien"/></label>
          </td>
          <td class="promptText" align="right" width="25%">
            <html:text onmouseover="Tip(this.value)" onkeyup="formatNumber(this,0);" onKeyPress="return numbersonly(this,event,true)" onmouseout="UnTip()" maxLength="20" styleId="so_tien" styleClass="fieldTextRight" property="so_tien" style="width:150px;"/>  
          </td>
        </tr>
        <tr>
          <td align="right" width="25%">
            <label for="ngay_tt"><fmt:message key="tracuu_ctgd.page.label.tu_ngay_thanh_toan"/></label>
          </td>
          <td class="promptText" align="left" valign="middle" width="25%">
            <html:text property="ngay_tt" styleId="ngay_tt"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_tt');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 60px;"/>
             
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="tu_ngay_btn" width="20" style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngay_tt", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "tu_ngay_btn"// id of the button
              });
            </script>
          </td>
          <td align="right" width="25%">
            <label for="ngay_ct"><fmt:message key="tracuu_ctgd.page.label.den_ngay_thanh_toan"/></label>
          </td>
          <td class="promptText" align="right" width="25%">
            <html:text property="ngay_ct" styleId="ngay_ct"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_ct');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 60px;"/>
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="den_ngay_btn" width="20"
                 style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngay_ct", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "den_ngay_btn"// id of the button
              });
            </script>
          </td>          
        </tr>
        <tr>
          <td colspan="8" align="center">
          <div style="padding:10px 0px 10px 0px; ">
              <button id="btn_timKiem" accesskey="T" type="button" class="ButtonCommon" tabindex="21">
               <fmt:message key="tracuu_ctgd.page.button.find"/>
             </button>&nbsp;
             <button id="btn_thoat" accesskey="o" type="button" class="ButtonCommon" tabindex="22">
               <fmt:message key="tracuu_ctgd.page.button.exit"/>
             </button>
          </div>
          </td>
        </tr>
      </tbody>
    </table>

  <div style="padding:20px 0px 0px 0px;">
    <table cellpadding="0" style="BORDER-COLLAPSE: collapse" cellspacing="0" border="1"  width="100%">
          <thead class="TR_Selected">
            <tr >
              <th width="12%">
                <fmt:message key="tracuu_ctgd.page.label.ten_but_toan_kb_den"/>
              </th>
              <th width="8%">
                <fmt:message key="tracuu_ctgd.page.label.loai_but_toan"/>
              </th>
              <th width="7%">
                <fmt:message key="tracuu_ctgd.page.label.ma_kho_bac"/>
              </th>
              <th width="20%">
                <fmt:message key="tracuu_ctgd.page.label.dien_giai"/>
              </th>
              <th width="8%">
                <fmt:message key="tracuu_ctgd.page.label.ma_nh_nha_cung_cap"/>
              </th>
              <th width="8%">
                <fmt:message key="tracuu_ctgd.page.label.ngay_hieu_luc"/>
              </th>
              <th width="8%">
                <fmt:message key="tracuu_ctgd.page.label.ngay_giao_dich"/>
              </th>
              <th width="5%">
                <fmt:message key="tracuu_ctgd.page.label.loai_tien"/>
              </th>
              <th width="7%">
                <fmt:message key="tracuu_ctgd.page.label.stt_dong"/>
              </th>
              <th width="7%">
                <fmt:message key="tracuu_ctgd.page.label.ma_quy"/>
              </th>
            </tr>
          </thead>
          <tbody>
          <logic:empty name="lstLTT">
             <tr><td colspan="10" align="center">&nbsp;</td></tr>
          </logic:empty>
          <logic:notEmpty name="lstLTT">
          <%
          com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
          int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
          int stt = 0;
          Collection collTraCuu = new ArrayList();
          List lst = new ArrayList();
          if(request.getAttribute("lstLTT") != null)
            //collTraCuu = (Collection)request.getAttribute("lstLTT");
            lst = (List)request.getAttribute("lstLTT");
          int countSearch = 0;
          if(collTraCuu != null)
            countSearch = lst.size();
          
          LTTTraCuuVO items = null;
          LTTTraCuuVO itemsPrevious = null;
          LTTTraCuuVO itemsLastOnPage = null;
          for(int i=0; i<countSearch; i++){
            items = (LTTTraCuuVO) lst.get(i);
          %>
          <%--
            <logic:iterate id="items" name="lstLTT" indexId="stt" type="com.seatech.ttsp.ltt.LTTTraCuuVO">          
          --%>
              <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td align="center">
                  <%--<bean:write name="items" property="id"/>--%>
                  <%=items.getId()%>
                </td>
                <td align="center">
                  LNH
                </td>
                <td align="center">
                  <%--<bean:write name="items" property="nhkb_nhan"/>--%>
                  <%=items.getNhkb_nhan()%>
                </td>
                <td align="left">
                  &nbsp;<%--<bean:write name="items" property="dien_giai"/>--%>
                  <%=items.getDien_giai()%>
                </td>
                <td align="center">
                  <%--<bean:write name="items" property="nhkb_chuyen"/>--%>
                  <%=items.getNhkb_chuyen()%>
                </td>
                <td align="center">
                  <%--<bean:write name="items" property="tong_sotien" format=",000.00"/>  type="com.seatech.ttsp.ltt.LTTTraCuuVO"--%>
                  <%--<bean:write name="items" property="ngay_ktt_duyet"/>--%>
                  <%=items.getNgay_ktt_duyet() == null ? "" : items.getNgay_ktt_duyet()%>
                </td>
                <td align="center">
                  <%=DateUtils.LongToStrDateDDMMYYYY(items.getNgay_tt()) %>
                </td>
                <td align="center">
                  <%--<bean:write name="items" property="ten_ngoai_te"/>--%>
                  <%=items.getTen_ngoai_te()%>
                </td>
                <td align="center">
                  <%
                    if(i == 14){
                      itemsLastOnPage = (LTTTraCuuVO) lst.get(i);
                      lastIdLttOnPage = itemsLastOnPage.getId();                      
                    }
                    
                    if(i == 0){
                      stt_dong = 1;
                    }else{
                      itemsPrevious = (LTTTraCuuVO) lst.get(i-1);
                      if(items.getLtt_id().compareTo( itemsPrevious.getId()) == 0){
                        //stt_dong = 1;
                        stt_dong = stt_dong + 1;
                      }else{
                        stt_dong = 1;
                      }
                    }                    
                  %>
                  <%=items.getSott_dong()%>
                </td>
                <td align="center">
                  <%--<bean:write name="items" property="ma_quy"/>--%>
                  <%=items.getMa_quy() == null ? "" : items.getMa_quy()%>
                </td>
              </tr>            
            <%--
            </logic:iterate>            
            --%>
            <%
            }
            %>
            <tr>
              <td colspan="10" >
                <div style="float:right;padding:10px 10px 10px 0px;">
                  <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                </div>
              </td>
            </tr>
            <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
        </logic:notEmpty>
      </tbody>
     </table>
  </div>
  <!--<div style="padding:10px 10px 10px 0px;float:right ">
     <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon" tabindex="23">
      <fmt:message key="tracuu_ctgd.page.button.exit"/>
    </button>
  </div>-->
  </html:form>
  <!------------------------------------------ Message confirm ------------------------------->
  <div id="dialog-confirm" title="<fmt:message key="tracuu_ctgd.page.title.dialog_confirm"/>" >
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
      <span id="message_confirm"></span>
    </p>
  </div>
  
<!-- ---------------------------------Message --------------------------------->
  <div id="dialog-message" title="<fmt:message key="tracuu_ctgd.page.title.dialog_message"/>">
    <p>
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
  </div>
<%@ include file="/includes/ttsp_bottom.inc"%>
