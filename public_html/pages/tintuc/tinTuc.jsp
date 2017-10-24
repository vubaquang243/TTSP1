<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ include file="/includes/ttsp_header.inc"%>
	<link rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ckeditor/sample.css">
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>


<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/prettify.css"/>

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/prettify.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.ui.timepicker.js"  ></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ckeditor/ckeditor.js"></script>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/COTKBDiNH.js"  type="text/javascript"></script>
<%
  String noi_dung = request.getAttribute("noi_dung")==null?"":request.getAttribute("noi_dung").toString();
   String tieu_de = request.getAttribute("tieu_de")==null?"":request.getAttribute("tieu_de").toString();
    String ngay_dang = request.getAttribute("ngay_dang")==null?"":request.getAttribute("ngay_dang").toString();
     String ngay_het_han = request.getAttribute("ngay_het_han")==null?"":request.getAttribute("ngay_het_han").toString();
%>
 <script type="text/javascript">
    jQuery.noConflict();
    var role='<%=request.getAttribute("chucdanh")%>';
    var noi_dung='<%=noi_dung%>';
    

    var obj = jQuery.parseJSON('<%=request.getAttribute("lstKBHuyen")%>');
  //************************************ LOAD PAGE **********************************
    jQuery(document).ready(function()
      {
      var tieu_de='<%=tieu_de%>';
      var ngay_dang='<%=ngay_dang%>';
      var ngay_het_han='<%=ngay_het_han%>';
      if(tieu_de!=null&&""!=tieu_de){
        jQuery("#tieu_de").val(tieu_de);
      }
      if(ngay_dang!=null&&""!=ngay_dang){
        jQuery("#ngay_dang").val(ngay_dang);
      }
      if(ngay_het_han!=null&&""!=ngay_het_han){
        jQuery("#ngay_het_han").val(ngay_het_han);
      }
       /*
         * List daanh sach kho bac huyen
         */
      jQuery("#ma_kb_tinh").change(function (){
        var state=true, ma_kb_tinh=jQuery("#ma_kb_tinh").val();
            if(ma_kb_tinh==null || ma_kb_tinh==''){
              jQuery("#kb_id").html('');
              jQuery("#kb_id").attr({disabled:true});
            }else{
              state = !state;
              jQuery("#kb_id").multiselect(state ? 'disable' : 'enable');              
              if (ma_kb_tinh!=null){
               jQuery("#kb_id").html('');
               jQuery.each(ma_kb_tinh, function (i, value) {              
                var optgrp=jQuery('<optgroup>');
                optgrp.attr('label',jQuery("#ma_kb_tinh option[value='"+value+"']").text());
                  obj.filter(function(item) {
                    if(item.ma_cha == value){
                       var opt = jQuery('<option />', {value: item.ma,text: item.ten}); 
                       opt.appendTo(optgrp);
                    }
                   });
                  optgrp.appendTo(jQuery("#kb_id") );
                });             
              }
            }
            jQuery("#kb_tinh").val(ma_kb_tinh)
            jQuery("#kb_id").multiselect('refresh'); 
        });

jQuery("#kb_id").change(function (){
             var ma_kb_huyen=jQuery("#kb_id").val();
             jQuery("#kb_huyen").val(ma_kb_huyen);
        });

});
</Script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="loadTinTuc.do" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Quản lý tin tức </span>
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
      <td align="left" >
      <fieldset>
        <legend><font color="Blue">&#272;i&#7873;u ki&#7879;n tra c&#7913;u </font></legend>
        
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
            <tr>

               <td align="right" width="10%">
                <label for="kb_tinh">Kho bạc tỉnh</label>
              </td>
              <td width="20%">
               <html:select multiple="multiple" property="ma_kb_tinh" styleId="ma_kb_tinh" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()">
                  <html:optionsCollection name="lstKBTinh" value="ma" label="ten"/>
              </html:select>
              <html:hidden property="kb_tinh" styleId="kb_tinh"/>
              </td>
              <td align="right" width="10%">
                <label for="kb_huyen">Kho bạc huyện</label>
              </td>
              <td width="20%">
               <html:select multiple="multiple" property="ma_kb_huyen" styleId="kb_id" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()">
              </html:select>
              <html:hidden property="kb_huyen" styleId="kb_huyen"/>
              </td>
            </tr>
            <tr>
              <td align="right" width="10%">
                  <label for="tieu_de">Tiêu đề</label>
                </td>
                <td  align="left" width="20%">
                  <html:text property="tieu_de" styleId="tieu_de"/>
                </td>
              <td align="right" width="10%">
                    Ngày  đăng &nbsp;
                </td>
                <td  align="left" width="20%">
                <html:text property="ngay_dang" styleId="ngay_dang" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_dang');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_dang_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay_dang", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_dang_btn"// id of the button
                      });
                    </script> &nbsp;&nbsp;&nbsp;
                </td>
                <td  align="right" width="10%">
                     Ngày hết hạn &nbsp;
                </td>
                <td  align="left" width="20%">
                <html:text property="ngay_het_han" styleId="ngay_het_han" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_het_han');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_het_han_btn"
                     style="vertical-align:middle;width:13"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay_het_han", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_het_han_btn"// id of the button
                      });
                    </script>
                  </td>
            </tr>
            <tr>
              
              <td colspan="10" align="right">
                  <textarea cols="80" id="noi_dung" name="noi_dung" rows="1">
                    <%if(noi_dung!=null&&!"".equals(noi_dung)){%>
                       <%=noi_dung%>
                    <%}%>
                </textarea>
          
          <p>
          <% if(!"".equals(tieu_de)&& tieu_de!=null){%>
              <button type="button" onclick="check('update')" accesskey="c" id="bt_update">
                <span class="sortKey">C</span>&#7853;p nh&#7853;t
            </button> &nbsp;&nbsp;&nbsp;
          <%}%>
            <button type="button" onclick="check('create')" accesskey="i" id="bt">
                &#272;&#259;ng t<span class="sortKey">i</span>n
            </button> &nbsp;&nbsp;
            <button type="button" onclick="check('exit')" accesskey="t" id="bt_exit">
                Th<span class="sortKey">&#243;</span>at
            </button>
          </p>
                </td>

            </tr>
        </table>
      </fieldset>
    </td>
  </tr>
</table>
<html:hidden property="id" styleId="id"/>
</html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
    jQuery("#ma_kb_tinh").multiselect({noneSelectedText: "---- Chọn KB tỉnh ----",
    selectedText:"Bạn đã chọn # KB tỉnh"}).multiselectfilter();
    jQuery("#kb_id").multiselect({noneSelectedText: "---- Chọn KB huyện ----",
    selectedText:"Bạn đã chọn # KB huyện"}).multiselectfilter();
    CKEDITOR.replace( 'noi_dung', {
	toolbar: [
//		{ name: 'document', items: [ 'Source'] },	// Defines toolbar group with name (used to create voice label) and items in 3 subgroups.
		[ 'Undo', 'Redo' ],			// Defines toolbar group without name.
		{ name: 'basicstyles', items: [ 'Bold', 'Italic' ] },{ name: 'colors', items: [ 'TextColor', 'BGColor' ] }
	]
});

            
  function check(type) { 
 var f = document.forms[0];
 var ngay_dang = jQuery('#ngay_dang').val();
   var   tieu_de = jQuery('#tieu_de').val();
   var   ngay_het_han = jQuery('#ngay_het_han').val();
   var   chk_tinh = jQuery("#ma_kb_tinh").val();
    var  chk_huyen= jQuery("#kb_huyen").val();
     if (type == 'create') {
      
         if(""==ngay_dang.trim()){
            alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;n tin ng&#224;y b&#7855;t &#273;&#7847;u &#273;&#259;ng tin'));
            jQuery('#ngay_dang').focus();
            return false;
          }else if(""==ngay_het_han.trim()){
            alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;n tin ng&#224;y k&#7871;t th&#250;c &#273;&#259;ng tin'));
            jQuery('#ngay_het_han').focus();
            return false;
          }else if((''!=chk_tinh && chk_tinh!=null)&&(chk_huyen ==null || ''==chk_huyen)){
                  jQuery('#ma_kb_tinh').focus();
                  return false;
          }else if(tieu_de==null||""==tieu_de.trim()){
            alert(GetUnicode('C&#7847;n nh&#7853;p ti&#234;u &#273;&#7873;'));
            jQuery('#tieu_de').focus();
            return false;
          } else{    
            f.action = 'addTinTuc.do?type=CREATE';
          }
      } else if (type == 'update') {
           if(""==ngay_dang.trim()){
              alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;n tin ng&#224;y b&#7855;t &#273;&#7847;u &#273;&#259;ng tin'));
              jQuery('#ngay_dang').focus();
              return false;
            }else if(""==ngay_het_han.trim()){
              alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;n tin ng&#224;y k&#7871;t th&#250;c &#273;&#259;ng tin'));
              jQuery('#ngay_het_han').focus();
              return false;
            }else if((''!=chk_tinh && chk_tinh!=null)&&(chk_huyen ==null || ''==chk_huyen)){
                  jQuery('#ma_kb_tinh').focus();
                  return false;
          }else if(tieu_de==null||""==tieu_de.trim()){
              alert(GetUnicode('C&#7847;n nh&#7853;p ti&#234;u &#273;&#7873;'));
              jQuery('#tieu_de').focus();
              return false;
            } else{    
              f.action = 'addTinTuc.do?type=UPDATE';
            }
      } else if (type == 'exit') {
        
         f.action = 'searchTinTuc.do?id='+jQuery('#id').val();
      }
         f.submit();
}
fillDataTinTuc();
function fillDataTinTuc() {
   var id = jQuery('#id').val();
    if (id!=undefined && id!='undefined')
    jQuery.ajax( {
        type : "POST", url : "fillTinTucForm.do", async : true, data :  {
            "id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                
                    if (data.ERROR != undefined) {
                    }
                    else if (data.ERROR == undefined) {
                        fillDetailTinTucForm(data);
                        
                    }
                }
            }
        },
        error : function (textstatus) {
        }

    });
    else{
      jQuery("#ma_kb_tinh option").each(function (){
      jQuery(this).attr({disabled:true});
      jQuery("#ma_kb_tinh").multiselect('refresh');
    });
    }
}

function fillDetailTinTucForm(data) {
//    jQuery("#id").val(data.id);
//    jQuery("#ma_nh").val(data.nhkb_chuyen);
    if(data.dv_dang!=null && data.dv_dang!=''){
     jQuery("#kb_id").html('');
     var arrMaKBHuyen=data.dv_dang.split(',');
      jQuery.each(arrMaKBHuyen, function (i, idhuyen) {
      
        obj.filter(function(item) {
              if(item.ma == arrMaKBHuyen[i] ){
                 var opt = jQuery('<option />', {value: item.ma,text: item.ten,disabled:'disabled',selected:'selected'}); 
//                 alert(opt);
                 opt.appendTo(jQuery("#kb_id"));
              }
             });
      });          
    jQuery("#kb_id option").each(function (){
      jQuery(this).attr('disabled','disabled');
    });
     jQuery("#kb_id").multiselect('refresh'); 
    }
}

  </script>
  