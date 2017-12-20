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
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
  String chuc_danh = request.getAttribute("chuc_danh")==null?"":request.getAttribute("chuc_danh").toString();
  String ok = request.getAttribute("lst_ok")==null?"":request.getAttribute("lst_ok").toString();
  String notOk = request.getAttribute("notOk")==null?"":request.getAttribute("notOk").toString();
  String gui = request.getAttribute("gui")==null?"":request.getAttribute("gui").toString();
  String ex_fal = request.getAttribute("ex_fal")==null?"":request.getAttribute("ex_fal").toString();
%>

<script type="text/javascript">
  jQuery.noConflict();
    
  //************************************ LOAD PAGE **********************************
        var ok='<%=ok%>';
        var notOk='<%=notOk%>';
        var gui='<%=gui%>';
        var ex_fal='<%=ex_fal%>';
     
//    if (ok !=null && ''!=ok){
//     alert(GetUnicode('Danh s&#225;ch LTT k&#253; th&#224;nh c&#244;ng:')+ok);
//    }
     if (notOk !=null && ''!=notOk){
     alert(GetUnicode('Danh s&#225;ch LTT k&#253; kh&#244;ng th&#224;nh c&#244;ng:')+notOk);
      }
      if (gui !=null && ''!=gui){
     alert(GetUnicode('Danh s&#225;ch LTT k&#253; kh&#244;ng th&#224;nh c&#244;ng:')+gui+ GetUnicode(' \n G&#7917;i NH ko th&#224;nh c&#244;ng'));
      }
      if (ex_fal !=null && ''!=ex_fal){
      alert(GetUnicode('Danh s&#225;ch LTT k&#253; kh&#244;ng th&#224;nh c&#244;ng:')+ex_fal);
      }
  jQuery(document).init(function () {
//      getTenKhoBac();

      

  });
</script>
<div class="app_error">
  <html:errors/>
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
</div>
<div class="box_common_conten">
  <html:form action="loadDuyetLTT.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> K&#253; duy&#7879;t LTT </span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
   </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">

      <tr>
        <td align="center" colspan="2">
          <table  class="data-grid" id="data-grid" 
                                              border="0"
                                             cellspacing="2" cellpadding="2"                                  
                                             width="100%">
                <tr>
                <td width="8%">
                    S&#7889; LTT
                  </td>
                  <td align="left" width="17%">
                    <html:text style="width:95%; text-align:right; font-size:12px" styleId="so_ltt" maxlength="25" property="so_ltt"/>
                  </td>
                  <td width="8%">
                    S&#7889; YCTT
                  </td>
                  <td align="left" width="17%">
                    <html:text styleId="so_yctt" style="width:95%; text-align:right; font-size:12px"  maxlength="25"  property="so_yctt"/>
                  </td>
                  <td width="12%">
                    <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                  </td>
                  <td width="38%" colspan="2">
                    <html:select property="ma_nh" styleId="ngan_hang" onchange="nhangval()"
                             style="width: 100%;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value=""   >-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection  name="dmucNH" value="id" label="ten_nh"/>
                </html:select>
                  </td>               
                </tr>
                <tr>
                  <td >
                     Loại tiền
                  </td>
                  <td >
                    <html:select property="nt_id" styleId="nt_id" 
                             style="width: 100%;font-size:12px"  onchange="changNTDuyetLo()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="177">VND</html:option>
                    <html:optionsCollection name="listDMTienTe" value="id" label="ma"/>
                    </html:select>   
                    <input type="hidden" id="nt_id_old" value="VND"/>
                  </td>
                  <td >
                      S&#7889; ti&#7873;n
                  </td  width="20%">
                  <td align="left">
                      <html:text property="so_tien" styleId="so_tien" onblur="fmt_money(this);" style="width:95%; text-align:right;font-size:12px"/>
                  </td>
                  
                  <td >
                    <fmt:message key="doi_chieu.page.lable.qldc.tthai"/>
                  </td>
                  <td align="left" >
                    <html:select property="trang_thai" styleId="trang_thai" onchange="tthaival()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;" style="font-size:12px">
                    <% if(chuc_danh.contains("KTT")){%>
                      <html:option value="" >Ch&#7901; KTT duy&#7879;t</html:option>
                    <%}else if(chuc_danh.contains("GD")){ %>
                      <html:option value=""  >Ch&#7901; G&#272; duy&#7879;t</html:option>
                    <%}%>
                  </html:select>
                  </td>
                  
                </tr>
              <tr>
                <td>
                  TTV
                </td>
                <td>
                  <html:select property="ma_nsd" styleId="ma_nsd" 
                               style="width: 100%;font-size:12px" onchange="TTVval()" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----Ch&#7885;n TTV-----</html:option>
                      <html:optionsCollection name="colTTV" value="id" label="ma_nsd"/>
                  </html:select>
                </td>
                <td >
                    KTV
                </td>
                <td >
                    <html:select property="ma_tabmis" styleId="ma_tabmis" 
                             style="width: 100%;font-size:12px"  onchange="KTVval()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">-----Ch&#7885;n KTV-----</html:option>
                    <html:optionsCollection name="colTTVTABMIS" value="ma" label="ten"/>
                    </html:select>
                 </td>
                 <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                 <td  align="right">
                    <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                        <span class="sortKey">T</span>&igrave;m kiếm
                    </button>
                  </td>
                  <td  align="left">   
                    <font color="Blue">T&#7893;ng m&#243;n &#273;&#227; ch&#7885;n:</font>
                    <html:text property="mon_selected" readonly="true" style="text-align:right; border:0;width:15px;font-weight:bold;font-size:12px" value="0" styleId="mon_selected"/><br/>
                    <font color="Blue">T&#7893;ng ti&#7873;n &#273;&#227; ch&#7885;n &nbsp;:</font> 
                    <html:text property="tien_selected" readonly="true" style="text-align:left; border:0;width:140px;font-weight:bold;font-size:12px" value="0" styleId="tien_selected"/>                    
                  </td>
              </tr>
               </table>
          </td>
        </tr>
        <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
        <tr>
           <td colspan="2">
            <fieldset>
            <legend><font color="Blue">Danh s&#225;ch ch&#7901; duy&#7879;t</font></legend>
              <table  class="data-grid" id="data-grid" 
                                                  border="1"
                                                 cellspacing="0" cellpadding="0"                                  
                                                 width="100%">
                     <tr>
                     <td align="center" width="9%" class="ui-state-default ui-th-column">Số YCTT</td>
                     <td align="center" width="25%" class="ui-state-default ui-th-column">KH chuy&#7875;n</td>
                     <td align="center" width="25%" class="ui-state-default ui-th-column">T&#224;i kho&#7843;n - KH nh&#7853;n</td>
                     <td align="center" width="25%" class="ui-state-default ui-th-column">NH nh&#7853;n </td>
                     
                     <td align="center" width="12%" class="ui-state-default ui-th-column">S&#7889; ti&#7873;n</td>
                     <td align="center" width="4%" class="ui-state-default ui-th-column">
                      <input type="checkbox" id="check_all" onClick="checkall(this)" /> 
                     </td>
                     </tr>
                     
                    <logic:empty name="colLTT">
                      <tr>
                        <td colspan="6">
                          <font color="Red">Kh&#244;ng t&#236;m th&#7845;y th&#244;ng tin th&#7887;a m&#227;n &#273;i&#7873;u ki&#7879;n </font>
                        </td>
                      </tr>
                    </logic:empty>
                   <logic:notEmpty name="colLTT">
                      <logic:iterate id="items" name="colLTT" indexId="index">
                     <tr id="row_dts_<bean:write name="index"/>"  class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' onmouseout="window.status =''" onmouseover="window.status = 'Thanh toán viên: <bean:write  name="items" property="ten_nsd"/> - <bean:write  name="items" property="ma_nsd"/>';">
                     <td align="left" width="9%" onmouseover="style.fontWeight='bold'" onmouseout="style.fontWeight='normal'">
                           <a href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>','true')" title="<bean:write  name="items" property="so_yctt"/>-<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen"/>-<bean:write name="items" property="kh_nhan"/>-<bean:write name="items"  property="ten_nhkb_nhan"/>">
                              <bean:write name="items" property="so_yctt"/>
                           </a>
                       </td>
                       <td align="left" width="25%">
                       <div title="<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen" />" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden; width:230px; font-size:13px ">
                           &nbsp;
                              <bean:write  name="items" property="kh_chuyen" />
                              </div>
                       </td>
                       <td align="left" width="25%"  title="<bean:write name="items" property="kh_nhan"/>">
                        <div style="text-overflow:ellipsis; width:230px; white-space:nowrap;  overflow:hidden; font-size:13px">
                        &nbsp;<bean:write name="items" property="kh_nhan"/></div>
                       </td>
                       <td align="left" width="25%" >
                       <div title="<bean:write name="items"  property="ten_nhkb_nhan"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:230px; overflow:hidden; font-size:13px">
                       &nbsp;<bean:write name="items"  property="ten_nhkb_nhan"/></div>
                       </td>
                       <td align="right" width="12%" style="font-size:12px">
                        
                        <fmt:setLocale value="vi_VI"/>
                          <b> <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">              
                          <bean:write name="items"   property="tong_sotien"/>
                          
                          </fmt:formatNumber>
                          </b>
                       </td>
                       <td width="4%">
                        <input name="selector" id="ad_<bean:write name="index"/>" onclick="sumSelected(this);" class="ads_Checkbox" type="checkbox" value="<bean:write name="items" property="id"/>se@techit<bean:write name="items" property="so_yctt"/>yctt@seatechit<bean:write name="items" property="ndung_ky"/>" />                         
                          <input type="hidden" name="chuky_ktt" id="chuky_ktt_<bean:write name="index"/>"/>
                          <input type="hidden" name="chuky_gd" id="chuky_gd_<bean:write name="index"/>"/>                          
                          <input type="hidden" name="tien_select" id="tien_select_<bean:write name="items" property="id"/>" value="<bean:write name="items" property="tong_sotien"/>"/>
                          <input type="hidden" name="signature" id="signature_<bean:write name="index"/>"/>
                          <input type="hidden" name="noi_dung_ky" id="noi_dung_ky_<bean:write name="index"/>" value="<bean:write name="items" property="ndung_ky"/>"/>
                          <!--<input type="hidden" name="so_yctt" id="so_yctt_<bean:write name="index"/>" value="<bean:write name="items" property="so_yctt"/>"/>-->
                      </td>
                     </tr>
                      </logic:iterate>
                      <tr>
                            <td colspan="10">  
                            <input type="hidden" name="certserial" id="certserial"/>
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                        </tr>
                    </logic:notEmpty>             
                 </table>
            </fieldset>
           </td>
          </tr>
          <tr>
            <td align="left">
              <logic:present name="colMonTien">
               <logic:notEmpty name="colMonTien">
                  <logic:iterate id="items" name="colMonTien" indexId="index">
                     <font color="Blue">T&#7893;ng s&#7889; m&#243;n</font>
                    <b><bean:write name="items" property="tong_mon"/></b>&nbsp;&nbsp;
                    <font color="Blue">t&#7893;ng ti&#7873;n </font> 
                     <b>                     
                      <logic:equal value="177" name="duyetLTTForm" property="nt_id">
                           <fmt:setLocale value="vi_VI"/>
                           <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                           <bean:write name="items" property="tong_tien"/>
                           </fmt:formatNumber>
                      </logic:equal>  
                      <logic:notEqual value="177" name="duyetLTTForm" property="nt_id">
                           <fmt:setLocale value="vi_VI"/>
                           <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                           <bean:write name="items" property="tong_tien"/>
                           </fmt:formatNumber>
                       </logic:notEqual>    
                     </b>
                  </logic:iterate>
                </logic:notEmpty>
                </logic:present>
            </td>
            <td align="right">
              <button type="button"  onclick="check('update');" class="ButtonCommon" id="update_bton" accesskey="t" >
                  <span class="sortKey">D</span>uy&#7879;t
              </button> &nbsp;&nbsp;&nbsp;
              <!--<button type="button"  class="ButtonCommon" onclick="check('view');" id="view" accesskey="t" >
                  <span class="sortKey">C</span>hi ti&#7871;t
              </button>-->&nbsp;&nbsp;&nbsp;
              <button type="button" onclick="check('close');" class="ButtonCommon" accesskey="t" >
                  Th<span class="sortKey">o</span>&#225;t
              </button>
            </td>
          </tr>

</table>
 <html:hidden property="pageNumber" styleId="pageNumber"/>
  </html:form>

      
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
var chuc_danh = "<%=chuc_danh%>"; 
getNgan_hang();
getTTV();
disable_bton();

var f = document.forms[0];
jQuery('#so_ltt').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_ltt').val(this.value.replace(matches, ''),true);
         });
         
 jQuery('#so_tien').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_tien').val(this.value.replace(matches, ''),true);
         });
 jQuery('#so_yctt').keyup(function () {
            var matches = /[a-z,A-Z,',*,|,-]/gi;
            jQuery('#so_yctt').val(this.value.replace(matches, ''),true);
         });

function goPage(page) {
 
      f.pageNumber.value = page;
      f.action = 'loadDuyetLTT.do?pageNumber='+page;
      f.submit();
  }

function disable_bton(){
  if(document.getElementsByName('selector').length==0){
  document.getElementById("update_bton").disabled=true;
  }
}

function fmt_money(obj){
  var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;  
  if (nt_id == null || '' == nt_id || nt_id == 'undefined') nt_id = 'VND';
  var tien = convertCurrencyToNumber(obj.value, nt_id);  
  obj.value = CurrencyFormatted(tien, nt_id);  
}

  function check(type) { 
   var f = document.forms[0];
     if (type == 'find') {
        f.action = 'loadDuyetLTT.do';      
      }
      if (type == 'update') {
        var all_len = jQuery('input[type=checkbox]:checked').length;
        if (all_len < 1){
          alert("Cần chọn LTT cần duyệt");
            return false;
        }
        if("Y"==strChkKy){
          if(!ky(chuc_danh)){
            alert("Bạn ký Không thành công");
            return false;
          }
        }
          document.getElementById("update_bton").disabled=true;
            f.action = 'updateDuyetLTT.do';      
      }
     if (type == 'close') {
        f.action = 'mainAction.do';   
      } 
       f.submit();
    }
      function TTVval() {
      var ma_nsd;
      ma_nsd=document.getElementById("ma_nsd").value;
      return ma_nsd;
  }
   function KTVval() {
      var ma_tabmis;
      ma_tabmis=document.getElementById("ma_tabmis").value;
      return ma_tabmis;
  }
      function nhangval() {
      var nhkb_nhan;
      nhkb_nhan=document.getElementById("ngan_hang").value; 
      return nhkb_nhan;
  }
  
  function sumSelected(){   
        var val = [];
        var tien=0;
        var mon=0;
        
        jQuery('#check_all').attr('checked', false);
        jQuery('input[type=checkbox]:checked').each(function(i){
          val[i] = jQuery(this).val();
           var val1= val[i].toString();
              tien = tien + parseInt(jQuery('#tien_select_'+val1.substr(0,val1.indexOf("se@techit"))).val());
              mon = i+1; 
        });
        var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
        if (nt_id == null || '' == nt_id || nt_id == 'undefined')
          nt_id = 'VND';
        jQuery('#tien_selected').val(CurrencyFormatted(tien, nt_id));
        jQuery('#mon_selected').val(mon);
  }
  
  function getTTV(){
      jQuery("#trang_thai").attr("disabled", "disabled");
      if(document.getElementById('ma_nsd').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
          jQuery("#ma_nsd option:eq(0)").remove();
          jQuery("#ma_nsd option:eq(1)").attr('selected', true);
      }
      if(document.getElementById('ma_tabmis').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
          jQuery("#ma_tabmis option:eq(0)").remove();
          jQuery("#ma_tabmis option:eq(1)").attr('selected', true);
      }
      if(document.getElementById('ma_tabmis').options.length==1){ // select dong thu 2 neu select box co 2 value voi user cap tinh
          jQuery("#ma_tabmis").attr("disabled", "disabled");        
      }
  }
  
  function getNgan_hang(){
      if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
          jQuery("#ngan_hang option:eq(0)").remove();
          jQuery("#ngan_hang option:eq(1)").attr('selected', true);
      }
      else if(document.getElementById('ngan_hang').options.length > 2){
         jQuery('#ngan_hang option:eq(0)').attr('selected', true);
      }
  }
  
  
  function checkall(source) {
        var tien=0;
        var mon=0;
        checkboxes = document.getElementsByName('selector');
      for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = source.checked; 
           var val1= checkboxes[i].value.toString();
           if (source.checked == true){
              tien += parseInt(jQuery('#tien_select_'+val1.substr(0,val1.indexOf("se@techit"))).val());
              mon = i+1; 
           }
            if (source.checked == false){
              tien =0;
              mon = 0;
            }

      }
       jQuery('#tien_selected').val(toFormatNumber(tien,0,'.'));
        jQuery('#mon_selected').val(mon);
  }
  function ky(chuc_danh){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert(); 
            
           // 20171120 thuongdt bo sung canh bao han su dung CTS
             var strdomain = '<%=strdomain%>';
            var struser_name = '<%=struser_name%>';
            var strcheckcts = '<%=strcheckcts%>';           
            if(!checkCTSdate(cert,strdomain+'/'+struser_name,strcheckcts)){
             return false;
            }
            
            var serial = cert.Serial;
            jQuery("#certserial").val(serial);
              jQuery('input[name=selector]:checked').each(function(i){
                    var nd = jQuery("#noi_dung_ky_"+i).val();
                    var sign = cert.Sign(nd);          
                    jQuery("#signature_"+i).val(sign);
                    
                    if(chuc_danh != null){
                        if(chuc_danh.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
                          jQuery("#chuky_ktt_"+i).val(sign);
                        }else if(chuc_danh.indexOf("<%=AppConstants.NSD_GD%>") != -1){
                          jQuery("#chuky_gd_"+i).val(sign);
                        }
//                        alert(i+ '---' + jQuery("#chuky_gd_"+i).val());
                    }
                    
//            }

            });
            
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }

function makeGetRequestView(id, type) {
      var urlRequest = null;
      var tcuu="";
      var so_ltt =id,
      so_yctt= jQuery('#so_yctt').val(),
      so_tien= jQuery('#so_tien').val(),
      ma_nh = jQuery('#ngan_hang').val(),
      ma_nsd = jQuery('#ma_nsd').val(),
      ma_tabmis = jQuery('#ma_tabmis').val();
      pageNumber = jQuery('#pageNumber').val();
      tcuu="&so_yctt="+so_yctt+"&so_tien="+so_tien+"&ma_nh="+ma_nh+"&ma_nsd="+ma_nsd+"&ma_tabmis="+ma_tabmis+"&pageNumber="+pageNumber
          urlRequest = "listLttAdd.do?action=VIEW_LTTDi_DUYETLO&so_ltt_details=" + id + tcuu;
      window.location = urlRequest;
  }   
function changNTDuyetLo() {
    var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
    var nt_id_old = '';
    var final_value = '';
    var formated_value = '';
    try{
      nt_id_old = jQuery("#nt_id_old").val(); 
    }catch(ex){
      nt_id_old = '';
      alert(ex);
    }

    if (nt_id == null || '' == nt_id || nt_id == 'undefined')
        nt_id = 'VND';
        
    if (nt_id_old == null || '' == nt_id_old || nt_id_old == 'undefined')
        nt_id_old = nt_id;
        
    final_value = convertCurrencyToNumber(jQuery("#so_tien").val(),nt_id_old);   
    formated_value = CurrencyFormatted(final_value, nt_id);    
    jQuery("#so_tien").val(formated_value);
    
    final_value = convertCurrencyToNumber(jQuery("#tien_selected").val(),nt_id_old);   
    formated_value = CurrencyFormatted(final_value, nt_id);
    jQuery("#tien_selected").val(formated_value);   
  
    try{
      document.getElementById("nt_id_old").value = nt_id;
    }catch(ex){
      alert(ex);
    }
}    
</script>