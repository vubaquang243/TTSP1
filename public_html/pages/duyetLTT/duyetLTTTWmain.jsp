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
String idxKB_huyen = request.getAttribute("idxKB_huyen")==null?"":request.getAttribute("idxKB_huyen").toString();
String inMax = request.getAttribute("tien_max")==null?"":request.getAttribute("tien_max").toString();
String inMin = request.getAttribute("tien_min")==null?"":request.getAttribute("tien_min").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
   //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
      getTenKhoBacLOTW();
  });
</script>
<div class="app_error">
  <html:errors/>
  
</div>
<div class="box_common_conten">
  <html:form action="loadDuyetLTTTW.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> LTT TW cần duyệt </span>
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
          <td  align="right" bordercolor="#e1e1e1">
          <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
          </td>
          <td >
          
          <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="width: 100%;font-size:12px" onchange="getTenKhoBacLOTW(); nhkb_tinhval();"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;">           
                    <html:option value="">-----KB tinh-----</html:option>        
                    <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>           
          </html:select>
          </td>
          <td  align="right" bordercolor="#e1e1e1">
          <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
          </td>
          <td>
          <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="width: 100%;font-size:12px" onchange="nhkb_huyenval();"
                      onkeydown="if(event.keyCode==13) event.keyCode=9;">                          
                    <html:option value="">-----Chon KB huyen-----</html:option>
                    
          </html:select>
          </td>
          <td  align="right">
              <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
            </td>
            <td >
              <html:select property="ma_dv" styleId="ma_dv" onchange="nhangval()"
                       style="width: 100%;font-size:12px"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
              <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
              <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
          </html:select>
          </td>
          <td  align="center"  rowspan="2" width="13%">
            <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                <span class="sortKey">T</span>&igrave;m kiếm
            </button>
          </td>
        </tr>
        <tr>
          <td width="10%" align="right">
            S&#7889; LTT
          </td>
          <td align="left" width="17%">
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="so_ltt" maxlength="25" property="so_ltt"/>
          </td>
          <td width="10%" align="right">
            S&#7889; ti&#7873;n l&#7899;n h&#417;n
          </td>
          <td align="left" width="20%">
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="tien_min" onblur="fmt_money('tien_min',this.value);" maxlength="25" property="tien_min"/>
          </td>
          <td width="13%" align="right">
            S&#7889; ti&#7873;n nh&#7887; h&#417;n
          </td>
          <td align="left" width="17%">
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="tien_max" onblur="fmt_money('tien_max',this.value);" maxlength="25" property="tien_max"/>          
          </td>                
        </tr>
        <tr>
          <td  align="right" colspan="7">   
          <font color="Blue">T&#7893;ng s&#7889; m&#243;n &#273;&#227; ch&#7885;n</font>
          <html:text property="mon_selected" readonly="true" style="text-align:right; border:0;width:15px;font-weight:bold;font-size:12px" value="0" styleId="mon_selected"/>&nbsp;&nbsp;
          <font color="Blue">t&#7893;ng ti&#7873;n &#273;&#227; ch&#7885;n</font> 
          <html:text property="tien_selected" readonly="true" style="text-align:left; border:0;width:120px;font-weight:bold;font-size:12px" value="0" styleId="tien_selected"/>       
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
            <div style="overflow-x: scroll;width:100%" >
              <table  class="data-grid" id="data-grid" 
                                                  border="1"
                                                 cellspacing="0" cellpadding="0"                                  
                                                 width="100%">
                     <tr>
                     <td align="center" width="10%" class="ui-state-default ui-th-column">Số YCTT</td>
                     <td align="center" width="15%" class="ui-state-default ui-th-column">KH chuy&#7875;n</td>
                     <td align="center" width="15%" class="ui-state-default ui-th-column">T&#224;i kho&#7843;n - KH nh&#7853;n</td>                              
                     <td align="center" width="14%" class="ui-state-default ui-th-column">S&#7889; ti&#7873;n</td>            
                     <td align="center" width="21%" class="ui-state-default ui-th-column">Lý do Tinh</td>
                     <td align="center" width="21%" class="ui-state-default ui-th-column">Lý do TW</td>
                     <td align="center" width="4%" class="ui-state-default ui-th-column">
                      <input type="checkbox" id="check_all" onClick="checkall(this)" /> 
                     </td>
                     </tr>
                     
                    <logic:empty name="colLTT">
                      <tr>
                        <td colspan="5">
                          <font color="Red">Kh&#244;ng t&#236;m th&#7845;y th&#244;ng tin th&#7887;a m&#227;n &#273;i&#7873;u ki&#7879;n </font>
                        </td>
                      </tr>
                    </logic:empty>
                   <logic:notEmpty name="colLTT">
                      <logic:iterate id="items" name="colLTT" indexId="index">
                     <tr id="row_dts_<bean:write name="index"/>" class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' onmouseout="window.status =''" onmouseover="window.status = 'Thanh toán viên: <bean:write  name="items" property="ten_nsd"/> - <bean:write  name="items" property="ma_nsd"/>';">
                     <td align="left"  onmouseover="style.fontWeight='bold'" onmouseout="style.fontWeight='normal'">
                           <a href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>','true')" title="<bean:write  name="items" property="so_yctt"/>-<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen"/>-<bean:write name="items" property="kh_nhan"/>-<bean:write name="items"  property="ten_nhkb_nhan"/>">
                              <bean:write name="items" property="so_yctt"/>
                           </a>
                       </td>
                       <td align="left" >
                       <div title="<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen" />" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden; width:180px; font-size:13px ">
                               <bean:write  name="items" property="kh_chuyen" />
                        </div>
                       </td>
                       <td align="left" width="20%"  title="<bean:write name="items" property="kh_nhan"/>">
                        <div style="text-overflow:ellipsis;width:180px;white-space:nowrap;  overflow:hidden; font-size:13px">
                        &nbsp;<bean:write name="items" property="kh_nhan"/></div>
                       </td>
                                              
                       <td align="right"  style="font-size:12px">
                        
                        <fmt:setLocale value="vi_VI"/>
                          <b> <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">              
                          <bean:write name="items"   property="tong_sotien"/>
                          
                          </fmt:formatNumber>
                          </b>
                       </td>     
                       <td align="left"  style="font-size:12px" title="<bean:write name="items" property="ly_do_tinh"/>">
                       <div style="text-overflow:ellipsis;width:180px;white-space:nowrap;  overflow:hidden; font-size:13px">
                        <bean:write name="items" property="ly_do_tinh"/>
                        </div>
                       </td>
                       <td align="right"  style="font-size:12px">
                        <input type="text" name="ly_do_tw" id="ly_do_tw_<bean:write name="index"/>" onkeydown="arrowUpDownTW(event,'<bean:write name="index"/>')" style="width:98%; background-color:white" class="fieldTextLeft"/>
                        <input type="hidden" name="id_ldo" id="id_ldo"/>
                       </td>
                       <td  >
                        <input name="selector" id="ad_<bean:write name="index"/>" onclick="sumSelected(this);" class="ads_Checkbox" type="checkbox" value="<bean:write name="items" property="id"/>se@techit<bean:write name="items" property="so_yctt"/>yctt@seatechit<bean:write name="index"/>" />                       
                          <input type="hidden" name="tien_select" id="tien_select_<bean:write name="items" property="id"/>" value="<bean:write name="items" property="tong_sotien"/>"/>
                      </td>
                     </tr>
                      </logic:iterate>
                      <tr>
                            <td colspan="10">                 
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                        </tr>
                    </logic:notEmpty>              
                 </table>
                </div>
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
                     <fmt:setLocale value="vi_VI"/>
                           <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                           <bean:write name="items" property="tong_tien"/>
                           </fmt:formatNumber>
                           </b>
                  </logic:iterate>
                </logic:notEmpty>
                </logic:present>
            </td>
            <td align="right">
              <button type="button"  onclick="check('update');" class="ButtonCommon" id="update_bton" accesskey="d" >
                  <span class="sortKey">D</span>uy&#7879;t
              </button> &nbsp;&nbsp;&nbsp;
              <button type="button"  onclick="check('ko_duyet');" accesskey="k" >
                  <span class="sortKey">K</span>hông Duy&#7879;t
              </button> &nbsp;&nbsp;&nbsp;
              <!--<span id="in"> 
                <button onclick="formAction()"
                        id="btnIn" class="ButtonCommon"
                        type="button" accesskey="I">
                  <span class="sortKey">I</span>n
                </button>
              </span>-->
              <button type="button" onclick="check('close');" class="ButtonCommon" accesskey="o" >
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

//getNgan_hang();
//getTTV();
disable_bton();


jQuery('#so_ltt').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_ltt').val(this.value.replace(matches, ''),true);
         });
         
 jQuery('#tien_max').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_tien').val(this.value.replace(matches, ''),true);
         });
 jQuery('#tien_minn').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_ltt').val(this.value.replace(matches, ''),true);
         });

function fmt_money(tien_type,tien){
  jQuery('#'+tien_type+'').val(toFormatNumber(tien,0,'.'));
}

function disable_bton(){
  if(document.getElementsByName('selector').length==0){
  document.getElementById("update_bton").disabled=true;
  }
}
  function check(type) { 
   var f = document.forms[0];
   jQuery('#id_ldo').val('');
     if (type == 'find') {
       var idxKB = jQuery('#nhkb_huyen option:selected').index();
        f.action = 'loadDuyetLTTTW.do?idxKB='+idxKB      
      }
      if (type == 'update') {
        var check_item = jQuery('input[type=checkbox]:checked');
        if (check_item.length < 1){
          alert("Cần chọn LTT cần duyệt");
            return false;
            
        }
        else if (check_item.length >0){ 
          
              var val = [];   
            jQuery('input[name=selector]:checked').each(function(i){
            val[i] = jQuery(this).val();
              var val1= val[i].toString();
              var ly_do= jQuery('#ly_do_tw_'+val1.substr(val1.lastIndexOf('yctt@seatechit')+14)).val();
                 if(ly_do.trim()==null || ''==ly_do.trim()){
                    jQuery('#id_ldo').val('#ly_do_tw_'+val1.substr(val1.lastIndexOf('yctt@seatechit')+14));
                    return false;                   
                 }
           });
           var ldo_empty=jQuery('#id_ldo').val();
           if(ldo_empty!=null && ''!=ldo_empty){
                    alert("Cần nhập lý do xác nhận LTT");
                    jQuery(ldo_empty).focus();
                    jQuery(ldo_empty).css('background-color', 'yellow');
                    return false;                   
                 }
         }
           f.action = 'updateDuyetLTTTW.do?type=DUYET';  
            
      }if (type == 'ko_duyet') {
        var check_item = jQuery('input[type=checkbox]:checked');
        if (check_item.length < 1){
          alert("Cần chọn LTT không duyệt");
            return false;
            
        }
        else if (check_item.length >0){ 
          
              var val = [];   
            jQuery('input[name=selector]:checked').each(function(i){
            val[i] = jQuery(this).val();
              var val1= val[i].toString();
              var ly_do= jQuery('#ly_do_tw_'+val1.substr(val1.lastIndexOf('yctt@seatechit')+14)).val();
                 if(ly_do.trim()==null || ''==ly_do.trim()){
                    jQuery('#id_ldo').val('#ly_do_tw_'+val1.substr(val1.lastIndexOf('yctt@seatechit')+14));
                    return false;                   
                 }
           });
           var ldo_empty=jQuery('#id_ldo').val();
           if(ldo_empty!=null && ''!=ldo_empty){
                    alert("Cần nhập lý do xác nhận không duyệt LTT");
                    jQuery(ldo_empty).focus();
                    jQuery(ldo_empty).css('background-color', 'yellow');
                    return false;                   
                 }
         }
           f.action = 'updateDuyetLTTTW.do?type=KO_DUYET';  
            
      }
     if (type == 'close') {
        f.action = 'mainAction.do';      
      } 
     f.submit(); 
    }
  function nhangval() {
      var ma_dv;
      nhkb_nhan=document.getElementById("ma_dv").value; 
      return ma_dv;
  }
    function nhkb_tinhval() {
      var nhkb_tinh;
      nhkb_tinh=document.getElementById("nhkb_tinh").value; 
      return nhkb_tinh;
  }
    function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=document.getElementById("nhkb_huyen").value; 
      return nhkb_huyen;
  }
// In
  function formAction(){
    var f = document.forms[0];
    f.action = "printTCuuDHV.do";
//      if(asyncCommand != null)
//      {
//         asyncCommand.Cancel();
//      }
     var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
    newDialog = window.open('', '_form', params);          
    f.target='_form';
    f.submit();
  }
  function sumSelected(){   
        var val = [];
        var tien=0;
        var mon=0;
        
        jQuery('#check_all').attr('checked', false);
        jQuery('input[name=selector]:checked').each(function(i){
          val[i] = jQuery(this).val();
           var val1= val[i].toString();
              tien = tien + parseInt(jQuery('#tien_select_'+val1.substr(0,val1.indexOf("se@techit"))).val());
              mon = i+1; 
        });

        jQuery('#tien_selected').val(toFormatNumber(tien,0,'.'));
        jQuery('#mon_selected').val(mon);
        
    
  }
function goPage(page) {
 
      f.pageNumber.value = page;
      f.action = 'loadDuyetLTTTW.do?pageNumber='+page;
      f.submit();
  }
  
//  function getNgan_hang(){
//      if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
//          jQuery("#ngan_hang option:eq(0)").remove();
//          jQuery("#ngan_hang option:eq(1)").attr('selected', true);
//      }
//            else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
//         jQuery('#ngan_hang option:eq(0)').attr('selected', true);
//      }
//      else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
//         jQuery('#ngan_hang option:eq(0)').attr('selected', true);
//      }
//      else if(ngan_hang!='0'){
//         jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
//      }
//  }
  
  
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


function makeGetRequestView(id, type) {
      var urlRequest = null;
          urlRequest = "listLttAdd.do?action=VIEW_LTTDi_DUYETLO&so_ltt_details=" + id + "&typeLO=TW";
      window.location = urlRequest;
  }   

 function getTenKhoBacLOTW() {  
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
      var kb_id = document.forms[0].nhkb_tinh.value;
      var kb_huyen="<%=idxKB_huyen%>";   
      if (kb_id ==null || ""==kb_id){
          document.getElementById('ngan_hang').options.length = 1; 
      }else if (kb_id !=null && ""!=kb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMKBLTTTW.do", data :  {
              "kb_id" : kb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                      // truong hop 1 - luc load khong co thang nao                  
                      document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                      });
                          if(kb_huyen==null||''==kb_huyen){
                             if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                             }
                          }else if(kb_huyen!='0'){
                            jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                          }
                  }
              }

          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }

function arrowUpDownTW(e,idx_ldo) {
    var keyCode = e.keyCode || e.charCode, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
    switch (keyCode) {

        case arrow.enter:
//        alert(parseInt(idx_ldo)+1);
        var nxtIdx = parseInt(idx_ldo)+1;
        jQuery('#ly_do_tw_'+idx_ldo).css('background-color', 'white');
            jQuery('#ly_do_tw_'+nxtIdx).focus();
            break;
        case arrow.esc:
           jQuery('#ly_do_tw_'+idx_ldo).val('');
            break;
    }

}


</script>