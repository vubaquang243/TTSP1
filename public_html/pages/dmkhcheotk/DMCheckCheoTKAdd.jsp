<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script language="JavaScript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dm.check.cheo.tk.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.dmKHCheoTKResource"/>
<title>
<fmt:message key="dmKHCheoTKResource.page.title"/>
</title>
<html:form action="/DMKHCheoTKExc.do" styleId="frmDMCheoTK">
<table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="dmKHCheoTKResource.page.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
      </tbody>
    </table>
<!--<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr >
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
               width="13" height="30"/>
        </td>
       <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title" style="height:15px;">
              <font color="#006699" size="2" >
                  <fmt:message key="dmKHCheoTKResource.page.title"/>
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
  </table>-->
  
  <table border="1" cellspacing="0" class="bordertableChungTu" width="100%">
    <tbody>
          <!--<tr>
            <td class="title" >
              --><!--<span>
                <fmt:message key="dmKHCheoTKResource.page.ts"/></span>--><!--
            </td>
          </tr>-->
          
          <tr>
            <td align="center" width="100%"> 
              <table width="60%"  cellspacing="0"  cellpadding="0" align="center" class="bordertableChungTu">
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="tai_khoan">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.tk"/>
                      </label>
                    </td>
                    <td class="promptText" align="center"  colspan="3">
                        <html:text styleId="tk" property="tk"  onKeyPress="return numberBlockKey(this,event,true)"
                         styleClass="fieldText" style="width:36.5%;"
                         onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                         onfocus="textfocus(this);"/>
                    </td>
                     
                </tr>
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="ma_cap">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_cap"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_cap"
                                   styleId="ma_cap" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                     <td align="right" width="20%">
                      <label for="ma_chuong">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_chuong"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_chuong"
                                   styleId="ma_chuong" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="ma_nganh">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_nganh"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_nganh"
                                   styleId="ma_nganh" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                     <td align="right" width="20%">
                      <label for="ma_ndkt">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_ndkt"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_ndkt"
                                   styleId="ma_ndkt" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="ma_dvsdns">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_dvsdns"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_dvsdns"
                                   styleId="ma_dvsdns" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                     <td align="right" width="20%">
                      <label for="ma_diaban">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_diaban"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_diaban"
                                   styleId="ma_diaban" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="ma_quy">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_quy"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_quy"
                                   styleId="ma_quy" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                     <td align="right" width="20%">
                      <label for="ma_ctmt">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_ctmt"/>
                      </label>
                    </td>
                    <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_ctmt"
                                   styleId="ma_ctmt" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td align="right" width="20%">
                      <label for="ma_nguon">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_nguon"/>
                      </label>
                    </td>
                     <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_nguon"
                                   styleId="ma_nguon" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <%--<html:option value="">
                          <fmt:message key="dmKHCheoTKResource.page.option.trang_thai.default"/>
                        </html:option>--%>
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                     <td align="right" width="20%">
                      <label for="ma_dphong">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_dphong"/>
                      </label>
                    </td>
                     <td class="promptText">
                      <html:select styleClass="selectBox" property="ma_dphong"
                                   styleId="ma_dphong" style="width:164;"
                                   onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                   onfocus="textfocus(this);">
                        <option value='0'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.batbuoc"/>
                        <option value='1'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.khongbatbuoc"/>
                        <option value='2'/>
                          <fmt:message key="dmKHCheoTKResource.page.giatri.tuychon"/>                      
                      </html:select>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="4" width="100%">
                      <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                          <td align="center">
                            <span>
                              <button id="btnGhi" type="button" accesskey="o" class="ButtonCommon">
                                <fmt:message key="dmKHCheoTKResource.page.button.ghi"/>
                              </button>
                            </span>
                            <span>
                              <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon">
                                <fmt:message key="dmKHCheoTKResource.page.button.thoat"/>
                              </button>
                            </span>
                          </td>
                        </tr>
                      </table>
                    </td>
                </tr>
              </table>
            </td>
          </tr>
    </tbody>
  </table>
</html:form>
<div id="dialog-message-check"
     title='<fmt:message key="dmKHCheoTKResource.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
      jQuery.noConflict();
    var action = '<%=request.getAttribute("action")%>';
    resetFormThemTK();
     if(action=='EDIT'){
        jQuery("#tk").val('<%=request.getAttribute("tk_field")%>').attr({
            disabled :"disabled"
        });
        jQuery("#ma_chuon").val('<%=request.getAttribute("ma_chuong_field")%>');
        jQuery("#ma_ctmt").val('<%=request.getAttribute("ma_ctmt_field")%>');
        jQuery("#ma_diaban").val('<%=request.getAttribute("ma_diaban_field")%>');
        jQuery("#ma_dphong").val('<%=request.getAttribute("ma_dphong_field")%>');
        jQuery("#ma_dvsdns").val('<%=request.getAttribute("ma_dvsdns_field")%>');
        jQuery("#ma_ndkt").val('<%=request.getAttribute("ma_ndkt_field")%>');
        jQuery("#ma_nganh").val('<%=request.getAttribute("ma_nganh_field")%>');
        jQuery("#ma_nguon").val('<%=request.getAttribute("ma_nguon_field")%>');
        jQuery("#ma_quy").val('<%=request.getAttribute("ma_quy_field")%>');
        jQuery("#ma_cap").val('<%=request.getAttribute("ma_cap_field")%>');
     }    
    var correct = false;
    jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
              document.forms[0].action = 'DMKHCheoTK.do?action=AddSuccess';
              document.forms[0].submit();
              jQuery(this).dialog("close");
                }
              }
      });
    jQuery("#btn_thoat").click(function () {
              document.forms[0].action = 'DMKHCheoTK.do';
              document.forms[0].submit();
       });
       jQuery("#btnGhi").click(function () {               
          if(action=='ADD'){
            if (confirm('Bạn có chắc chắn muốn ghi tài khoản '+jQuery("#tk").val()+' không?') == false)
              return false;
            else {
                if(jQuery("#tk").val()==null || jQuery("#tk").val() == ""){
                  alert('Yêu cầu nhập số tài khoản');
                  jQuery("#tk").focus();
                  jQuery("#tk").addClass('ui-state-error');
//                  jQuery("#tk").removeClass('ui-state-error');
                  return correct;
                }
                jQuery.ajax( {
                      type : "POST", url : "DMKHCheoTKExc.do", data :  {
                          tk : jQuery("#tk").val(),ma_nganh :jQuery("#ma_nganh").val(),
                          ma_cap : jQuery("#ma_cap").val(),ma_chuong :jQuery("#ma_chuong").val(),
                          ma_ndkt :jQuery("#ma_ndkt").val(),
                          ma_dvsdns : jQuery("#ma_dvsdns").val(),ma_diaban :jQuery("#ma_diaban").val(),
                          ma_quy : jQuery("#ma_quy").val(),ma_ctmt :jQuery("#ma_ctmt").val(),
                          ma_dphong : jQuery("#ma_dphong").val(),ma_nguon :jQuery("#ma_nguon").val()
                          , "nd" : Math.random() * 100000
                      },
                      success : function (json, textstatus) {
                          if (textstatus != null && textstatus == 'success') {
                                if(json.Existed){
                                  alert('Số tài khoản này đã tồn tại !');
                                  return correct;
                                }else{
                                    jQuery("#dialog-message-check").html('<fmt:message key="dmKHCheoTKResource.page.them.thanhcong"/>');
                                    jQuery("#tk").removeClass('ui-state-error');
                                    jQuery("#dialog-message-check").dialog("open");
                                   
                                }
                          }
                      },
                      error : function (textstatus) {
                          alert(textstatus);
                      }
                  });
              }
          }else if(action=="EDIT"){
              
              if (confirm('Bạn có chắc chắn muốn sửa tài khoản này không?') == false){
                return false;
              }
              else {
                  jQuery("#tk").attr({
                    disabled : "disalbed"
                  });
                  jQuery.ajax( {
                        type : "POST", url : "DMKHCheoTKExc.do", data :  {
                          tk : jQuery("#tk").val(),ma_cap :jQuery("#ma_cap").val(),
                          ma_chuong : jQuery("#ma_chuong").val(),ma_nganh :jQuery("#ma_nganh").val(),
                          ma_ndkt :jQuery("#ma_ndkt").val(),
                          ma_dvsdns : jQuery("#ma_dvsdns").val(),ma_diaban :jQuery("#ma_diaban").val(),
                          ma_quy : jQuery("#ma_quy").val(),ma_ctmt :jQuery("#ma_ctmt").val(),
                          ma_nguon : jQuery("#ma_nguon").val(),ma_dphong :jQuery("#ma_dphong").val()
                            , "nd" : Math.random() * 100000
                        },
                        success : function (json, textstatus) {
                            jQuery("#dialog-message-check").html('<fmt:message key="dmKHCheoTKResource.page.sua.thanhcong"/>');
                            jQuery("#dialog-message-check").dialog("open");
                        },
                        error : function (textstatus) {
                            alert(textstatus);
                        }
                    });
                }
          }
          
       });
</script>