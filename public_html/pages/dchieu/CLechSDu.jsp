<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>

<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String thu_tcong = request.getAttribute("thu_tcong")==null?"0":request.getAttribute("thu_tcong").toString();
  String chi_tcong = request.getAttribute("chi_tcong")==null?"0":request.getAttribute("chi_tcong").toString();
  String chenh_cot = request.getAttribute("chenh_cot")==null?"0":request.getAttribute("chenh_cot").toString();
  String chkTT = request.getAttribute("chkTT")==null?"":request.getAttribute("chkTT").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  var chkTT= '<%=chkTT%>';
//  alert(chkTT);
//  enableTxtArea();
//  function enableTxtArea() {
//      var a = document.getElementById('khop_lech');
//      if (a.checked == true) {
//          document.forms[0].ly_do_xn.disabled = false;
//      }
//      else {
//          document.forms[0].ly_do_xn.disabled = true;
//      }
//  }

//  if(chkTT == null||''==chkTT){
//  alert(chkTT);
//    jQuery('#khop_dung').attr('disabled', 'disabled');
//    alert(chkTT);
//  }
  function check(type) {
      var f = document.forms[0];
      if (type == 'create') {
          if (document.forms[0].ly_do_xn.disabled == false) {
              if (""==document.forms[0].ly_do_xn.value.trim() || (document.forms[0].ly_do_xn.value == null)) {
                  alert(GetUnicode('Nhập lý do xác nhận đối chiếu'));                 
                  return false;
              }           
          }
          f.action = 'TaoDTSChenhLechAction.do';
          f.submit();
      }
      else if (type == 'close') {
          f.action = 'XNDCTHop1Action.do';
          f.submit();
      }
  }
  
jQuery(document).ready(function () {
// confirm khi tao dien khop dung
jQuery("#dialog-confirm1").dialog( {
        autoOpen: false,
                  resizable: false,
                  height:200,
                  width:380,
                  modal: true,
                  buttons:{
               "Không" : function () {   
                  jQuery(this).dialog("close");
              },
              "Có" : function () {    
                  document.forms[0].action = "TaoDTSChenhLechAction.do";
                  document.forms[0].submit();   
                  jQuery(this).dialog("close");
              }
              
          }
      });
 // confirm khi co checnh l&#7865;ch COT     
  var chenh_cot = "<%=chenh_cot%>"; 
  jQuery("#chi_tcong_kbnn").val(toFormatNumber(jQuery("#chi_tcong_kbnn").val(),0,','));
  jQuery("#thu_tcong_kbnn").val(toFormatNumber(jQuery("#thu_tcong_kbnn").val(),0,','));
    if(chenh_cot!=0 && chenh_cot!='0'){
        jQuery("#dialog-confirm").dialog( {
        autoOpen: false,
                  resizable: false,
                  height:200,
                  width:380,
                  modal: true,
                  buttons:{
              "Có" : function () {    
                  document.forms[0].action = "TaoDTSChenhLechCOTAction.do";
                  document.forms[0].submit();   
                  jQuery(this).dialog("close");
              },
              "Không" : function () {    
                  jQuery(this).dialog("close");
              }
          }
      });
       jQuery("#btnCreate").click(function () {
            var selct=jQuery('input[name=khop_dung]:checked').val();
            if(selct=='2' || selct==2){
                jQuery("#dialog-confirm").html('C&#243; ch&#234;nh l&#7879;ch s&#7889; d&#432; Cut-off-time: '+ toFormatNumber("<%=chenh_cot%>",0,',') + '. B&#7841;n c&#243; mu&#7889;n t&#7841;o &#272;XN ch&#234;nh l&#7879;ch kh&#244;ng? ');
                jQuery("#dialog-confirm").dialog("open");
            }else if(selct=='1' || selct==1){
               if (document.forms[0].ly_do_xn.disabled == false) {
                  if (""==document.forms[0].ly_do_xn.value.trim() || (document.forms[0].ly_do_xn.value == null)) {
                    alert(GetUnicode('Nhập lý do xác nhận đối chiếu'));                 
                      return false;
                  }           
                }
                  
          document.forms[0].action = 'TaoDTSChenhLechAction.do';
          document.forms[0].submit();            
            }
      });
    }else if(chenh_cot==0 || chenh_cot=='0'){
    
      jQuery("#btnCreate").click(function () {
      var selct=jQuery('input[name=khop_dung]:checked').val();
              if (""==document.forms[0].ly_do_xn.value.trim() || (document.forms[0].ly_do_xn.value == null)) {
                  alert(GetUnicode('Nhập lý do xác nhận đối chiếu'));                 
                  return false;        
            }
            if(selct=='2' || selct==2){
                jQuery("#dialog-confirm1").html('Anh/Ch&#7883; x&#225;c nh&#7853;n &#273;&#7889;i chi&#7871;u kh&#7899;p &#273;&#250;ng ?');
                jQuery("#dialog-confirm1").dialog("open");
            }
            if(selct=='1' || selct==1){
                document.forms[0].action = 'TaoDTSChenhLechAction.do';
               document.forms[0].submit();
            }
          
      });
    }
  });
  </script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="TaoDTSChenhLechAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="doi_chieu.page.title"/></span>
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
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0"
           class="tableBound" bordercolor="#999999" width="100%">
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="2" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td style="font-size:15pt; color:red; FONT-WEIGHT: bold"
                  align="center" bordercolor="#e1e1e1" colspan="2">
                <fmt:message key="doi_chieu.page.lable.tbao1"/> <br/>
              </td>
            </tr>
            <tr>
           
              <td align="center" bordercolor="#e1e1e1" style="font-size:11pt; FONT-WEIGHT: bold"> <br/>S&#7889; ti&#7873;n chi th&#7911; c&#244;ng
              <html:text property="tien_chi_tcong_kbnn" styleId="chi_tcong_kbnn" value="<%=chi_tcong%>" style="border:1; width:120px; font-size:11px; text-align:right;FONT-WEIGHT: bold" readonly="true" />
                v&#224; s&#7889; ti&#7873;n thu th&#7911; c&#244;ng <input type="text" name="tien_thu_tcong_kbnn" id="thu_tcong_kbnn" value="<%=thu_tcong%>" style="border:1; width:120px; font-size:11px; text-align:right;FONT-WEIGHT: bold" readonly="true" />
              </td>
            </tr>
            <tr>
              <td style="font-size:10pt; FONT-WEIGHT: bold" align="center"
                  bordercolor="#e1e1e1" colspan="2">
                <br/>                 
                <fmt:message key="doi_chieu.page.lable.tbao3"/>
              </td>
            </tr>             
            <tr>
              <td>
                <fieldset>
                  <legend>X&#225;c nh&#7853;n k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</legend>
                  <table width="99%" cellspacing="0" cellpadding="1"
                         bordercolor="#e1e1e1" border="0" align="center"
                         style="BORDER-COLLAPSE: collapse">
                    <tr>
                      <td colspan="2">
                        <%if(request.getAttribute("DXN_dung_du") != null){
                         %>                       
                        <font color="Red" dir="ltr">
                          <fmt:message key="doi_chieu.page.lable.dxndung"/>
                        </font>
                         
                        <%}else if(request.getAttribute("DXN_lech_du") != null){
                         %>                       
                        <font color="Red" dir="ltr">
                          <fmt:message key="doi_chieu.page.lable.dxnlech"/>
                        </font>                       
                        <%}%>
                      </td>
                    </tr>                  
                    <tr>
                    <td width="20%">&nbsp;</td>
                      <td>
                      <%if("N".equals(chkTT)){%>
                        <input type="radio" name="khop_dung" id="khop_dung" disabled="disabled"
                               value="2"
                               /><fmt:message key="doi_chieu.page.lable.kdung"/><br/>(H&#227;y li&#234;n h&#7879; v&#7899;i trung &#432;&#417;ng n&#7871;u mu&#7889;n x&#225;c nh&#7853;n kh&#7899;p &#273;&#250;ng)
                       <%}else if("Y".equals(chkTT)){%>
                        <input type="radio" name="khop_dung" id="khop_dung"
                               value="2"
                               /><fmt:message key="doi_chieu.page.lable.kdung"/><br/>(H&#227;y li&#234;n h&#7879; v&#7899;i trung &#432;&#417;ng n&#7871;u mu&#7889;n x&#225;c nh&#7853;n kh&#7899;p &#273;&#250;ng)
                       <%}%>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="khop_dung" id="khop_lech" checked="true"
                               value="1"/><fmt:message key="doi_chieu.page.lable.chenhlech"/>
                      </td>
                    </tr>                     
                    <tr>
                      <td>
                        <fmt:message key="doi_chieu.page.lable.ldo"/>
                      </td>
                      <td>
                        <html:textarea property="ly_do_xn" rows="5" style="width:100%" cols="60"
                                       value="" />
                      </td>
                    </tr>
                  </table>
                </fieldset>
              </td>
            </tr>            
            <tr>
              <td align="center">
                <button type="button" id="btnCreate" accesskey="t">
                  <span class="sortKey">X</span>&#225;c nh&#7853;n
                </button>                
                 <button  onclick="check('close');"  class="ButtonCommon"
                        type="button" accesskey="O">
                  Th<span class="sortKey">o</span>&aacute;t
                </button>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <html:hidden property="ttsp_id"/>
    <html:hidden property="pht_id"/>

  </html:form>
  
  <div id="dialog-confirm"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>
  <p
  </p>
</div>
  <div id="dialog-confirm1"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>
  <p
  </p>
</div>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

  </script>