<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.ttsp.ltt.LTTVO"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/date.format.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<div class="app_error"><html:errors/></div>
<div class="box_common_content">
   <html:form action="/lttSearchExc.do" method="post">
      <table width="99%">
        <tbody>
        <tr>
          <td><font color="red">
              <html:errors/>
            </font> 
          </td></tr>
        </tbody>
      </table>
      <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">Tra cứu Lệnh thanh toán đi</span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
          </tr>
        </tbody>
      </table>      
      <table width="99%" cellpadding="0" cellspacing="0" class="bgTable">
        <tbody>
          <tr>
            <td>
              <fieldset style="width:auto;">
                <legend style="vertical-align:middle;"><b>Điều kiện tìm kiếm</b></legend>
                <div style="width:auto;">
                  <table width="90%">
                    <tr>
                      <td width="20%" class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.nguoi_lap"/>
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="ttv_nhan" styleId="ttv_nhan" styleClass="fieldText"/>
                      </td>
                      <td width="20%" class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.trang_thai"/>                    
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="trang_thai" styleId="trang_thai" styleClass="fieldText"/>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.nh_kb_chuyen"/>
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="ma_nhkb_chuyen_cm" styleId="ma_nhkb_chuyen_cm" maxlength="8" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                            onblur="getTenNganHang('ma_nhkb_chuyen_cm', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 55px;"/>&nbsp;
                          <html:text property="ten_nhkb_chuyen_cm" styleId="ten_nhkb_chuyen_cm" styleClass="fieldTextTransparent" onmouseout="UnTip()" onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 168px;"/>
                          <!--<label id="lblNhkb_chuyen" onmouseout="UnTip()" onmouseover="Tip(this.value)" ></label>-->
                          <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen" />
                      </td>
                      <td width="20%" class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.nh_kb_nhan"/>
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="ma_nhkb_nhan_cm" styleId="ma_nhkb_nhan_cm" maxlength="8" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                            onblur="getTenNganHang('ma_nhkb_chuyen_cm', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 55px;"/>&nbsp;
                          <html:text property="ten_nhkb_nhan_cm" styleId="ten_nhkb_nhan_cm" styleClass="fieldTextTransparent" onmouseout="UnTip()" onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 168px;"/>
                          <html:hidden property="nhkb_nhan" styleId="nhkb_nhan" />
                      </td>
                    </tr>
                    <tr>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.tu_ngay" />
                      </td>                      
                      <td width="30%" align="left">
                        <input type="text" name="tu_ngay" id="tu_ngay" class="fieldText"
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');" 
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 55px;"/>
                        
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="btn_tu_ngay" width="20" style="vertical-align:middle;" />														
														<script type="text/javascript">
													    Calendar.setup(
													    {
													        inputField  : "tu_ngay",      // id of the input field
													        ifFormat    : "%d-%m-%Y",      // the date format
													        button      : "btn_tu_ngay"    // id of the button
						   							    } );
												 		</script>
                      </td>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.den_ngay"/>
                      </td>                      
                      <td width="30%" align="left">
                        <input type="text" name="den_ngay" id="den_ngay" class="fieldText"
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');" 
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="WIDTH: 55px;"/>
                        
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="btn_den_ngay" width="20" style="vertical-align:middle;" />														
														<script type="text/javascript">
													    Calendar.setup(
													    {
													        inputField  : "den_ngay",      // id of the input field
													        ifFormat    : "%d-%m-%Y",      // the date format
													        button      : "btn_den_ngay"    // id of the button
						   							    } );
												 		</script>
                      </td>
                    </tr>
                    <tr>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.so_yctt"/>
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="so_yctt" styleId="so_yctt" styleClass="fieldText"/>
                      </td>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.so_tien" />
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="tong_sotien" styleId="tong_sotien" styleClass="fieldText"/>
                      </td>
                    </tr>
                    <tr>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.so_lenh_thanh_toan"/>
                      </td>                      
                      <td width="30%" align="left">
                        <html:text property="id" styleId="id" styleClass="fieldText"/>
                      </td>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.loai_lenh"/>
                      </td>                      
                      <td width="30%" align="left">
                        <select id="loai_lenh" name="loai_lenh">
                          <option value="0">Lệnh đi</option>
                          <option value="1">Lệnh đến</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <td class="textNormal" align="right">
                        <fmt:message key="ltt_di.page.label.trang_thai_truyen_tin"/>
                      </td>                      
                      <td width="30%" align="left">
                        <input type="text" name="trang_thai_truyen_tin" id="trang_thai_truyen_tin" class="fieldText"/> 
                      </td>
                      <td class="textNormal" align="right">                        
                      </td>                      
                      <td width="30%" align="left">                        
                      </td>
                    </tr>                    
                    <tr>
                      <td width="100%" colspan="4" style="padding-right:10px;">
                        <button id="search" class="ButtonCommon" type="button" accesskey="T"><span class="sortKey">T</span>ìm kiếm</button>
                      <button id="view" class="ButtonCommon" type="button" accesskey="T"><span class="sortKey">X</span>em LTT</button>
                      <button id="exit" onclick="sbExitLttDi();" tabindex="103" class="ButtonCommon" type="button" accesskey="o">Th<span class="sortKey">o</span>át</button>
                      </td>
                    </tr>
                  </table>
                </div>
              </fieldset>
            </td>
          </tr>
          <tr>
            <td width="100%">
              <fieldset style="width:auto;">
                <legend id="lblKetQua" style="vertical-align:middle"><b>Kết quả</b></legend>
                  <div style="width:auto; ">
                    <%
                    Collection colTraCuu = null;
                    if(request.getAttribute("colTraCuuLTT") != null){
                      colTraCuu = (Collection)request.getAttribute("colTraCuuLTT");
                      colTraCuu.size();
                      }
                  %>
                    <table style="width:100%; border: 1px solid #8eb9e5;" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="15%" align="right">
                            <fmt:message key="ltt_di.page.label.so_yctt"/>
                          </td>
                          <td width="15%" align="left">
                            <fmt:message key="ltt_di.page.label.so_lenh_thanh_toan"/>
                          </td>
                          <td width="10%" align="right" valign="top">
                            <fmt:message key="ltt_di.page.label.nh_kb_chuyen"/>
                          </td>
                          <td width="10%" align="left">
                            <fmt:message key="ltt_di.page.label.nh_kb_nhan"/>
                          </td> 
                          <td width="10%" align="left">
                            Ngày LTT
                          </td>
                          <td width="15%" align="right" valign="top">
                            <fmt:message key="ltt_di.page.label.so_tien" />
                          </td>
                          <td width="15%" align="left">
                            <fmt:message key="ltt_di.page.label.trang_thai"/>  
                          </td> 
                        </tr>
                      <logic:present name="colTraCuuLTT">
                        <logic:iterate id="lttTraCuu" name="colTraCuuLTT" indexId="index">
                          <tr>
                            <td width="15%" align="right">
                              <bean:write name="lttTraCuu" property="so_yctt"/>
                            </td>
                            <td width="15%" align="left">
                              <bean:write name="lttTraCuu" property="id" />
                            </td>
                            <td width="10%" align="right" valign="top">
                              <bean:write name="lttTraCuu" property="nhkb_chuyen" />
                            </td>
                            <td width="10%" align="left">
                              <bean:write name="nhkb_nhan" property="nhkb_nhan" />
                            </td> 
                            <td width="10%" align="left">
                              <bean:write name="ngay_nhan" property="ngay_nhan" />
                            </td>
                            <td width="15%" align="right" valign="top">
                              <bean:write name="tong_sotien" property="tong_sotien" />
                            </td>
                            <td width="15%" align="left">
                              <bean:write name="trang_thai" property="trang_thai" />
                            </td> 
                          </tr>
                        </logic:iterate>
                        <logic:empty name="colTraCuuLTT" >
                        <tr>
                          <td colspan="7" align="center">
                            <span class="text_red">Chưa có dữ liệu</span>
                          </td>
                        </tr>
                      </logic:empty>
                      </logic:present>  
                      
                      
                    </table>    
                  </div>
              </fieldset> 
            </td>
          </tr>
        </tbody>
      </table>
      <input type="hidden" id="focusField"/>
  </html:form>
</div>
<div id="dialog-message" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p>
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
</div>
<div id="dialog-confirm" title="<fmt:message key="ltt_di.page.title.dialog_confirm"/>" >
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
    <span id="message_confirm"></span>
  </p>
</div>

<script type="text/javascript" charset="utf-8">
  jQuery.noConflict(); 	
  jQuery(document).ready(function(){
    jQuery("#view").hide();
    
    jQuery("#dialog:ui-dialog").dialog( "destroy" );
    //dialog message
      jQuery( "#dialog-message").dialog({
        autoOpen: false,
        height:200,
        width:380,
        modal: true,
        buttons: {
          Ok: function() {
            jQuery(this).dialog( "close" );
            var idFieldFocus=jQuery("#focusField").val();
            
            if(idFieldFocus!=null && idFieldFocus!='')
              jQuery("#"+idFieldFocus).focus();
          }
       }
      });
      //dialog confirm message	
      jQuery("#dialog-confirm" ).dialog({
        autoOpen: false,
        resizable: false,
        height:200,
        width:380,
        modal: true,
        buttons: {
          "Có": function() {
            
            jQuery(this).dialog("close");        
          },
          "Không": function() {
            jQuery(this).dialog("close");
          }
        }
      });
          
        
    //Dieu khien su kien click vao nut
    //**************************BUTTON Tim kiem CLICK********************************************
     jQuery("#search").click(function(){
        var tu_ngay=jQuery("#tu_ngay").val(),
            den_ngay=jQuery("#den_ngay").val(),
            loai_lenh=jQuery("#loai_lenh").val();
        document.forms[0].action = 'lttTraCuuExc.do?tu_ngay='+tu_ngay+'&den_ngay='+den_ngay+'&loai_lenh='+loai_lenh;
        document.forms[0].submit();
     });
  });
  
  function sbExitLttDi() {	 
      if(confirm('Bạn có chắc chắn thoát khỏi chức năng này?')==false)
          return false;
       else {             
          //window.location="loginAction.do";
          window.location="listLttAdd.do";         
       }
  }
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>