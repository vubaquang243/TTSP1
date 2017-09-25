<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>


<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/prettify.css"/>
<!--<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui.css" />-->

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/prettify.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.ui.timepicker.js"  ></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/COTKBDiNH.js"  type="text/javascript"></script>

<fmt:setBundle basename="com.seatech.ttsp.resource.COTResource"/>
<%java.util.Date now=new java.util.Date();%>
 <script type="text/javascript">
//************************************ LOAD PAGE **********************************

jQuery.noConflict();
var obj = jQuery.parseJSON('<%=request.getAttribute("lstKBHuyen")%>');
var objCOT = jQuery.parseJSON('<%=request.getAttribute("colCOT")%>');
jQuery(document).ready(function(){
    //ManhNV-20150424
            jQuery("#span_tn_cu").hide();
            jQuery("#span_tn_moi").hide();
            jQuery("#span_label_gio_tn_cu").hide();
            jQuery("#span_label_gio_tn_moi").hide();
            
            jQuery("#span_label_gio_cot_cu").show();
            jQuery("#span_label_gio_cot_moi").show();
            jQuery("#span_gio_cot_moi").show();
            jQuery("#span_gio_cot_cu").show();
    //ManhNV-20150424
    //KHAI BAO BIEN
    var ma_nh = jQuery("#ma_nh"),
            loai_cot = jQuery("#loai_cot"),
            ma_kb_tinh = jQuery("#ma_kb_tinh"),
            kb_id = jQuery("#kb_id"),
            nguoi_lap = jQuery("#nguoi_lap"),
            ngay_lap = jQuery("#ngay_lap"),
            ngay_cot = jQuery("#ngay_cot"),
            dialog_message = jQuery("#dialog-message"),
            focus_field = jQuery("#focusField"),
            dialog_confirm = jQuery("#dialog-confirm"),
            evenButtonBefore = jQuery("#evenButtonBefore"),
            gio_cot_moi = jQuery("#gio_cot_moi"),
            gio_cot_cu = jQuery("#gio_cot_cu"),
            lydo_cot = jQuery("#lydo_cot"),
            allFields = jQuery([]).add(ma_nh).add(loai_cot).add(ma_kb_tinh).add(nguoi_lap).add(nguoi_lap).add(gio_cot_cu)
            .add(gio_cot_moi).add(lydo_cot).add(kb_id);
    jQuery("#dialog:ui-dialog").dialog("destroy");
    gio_cot_moi.val('');
    gio_cot_moi.focus();
    
    lydo_cot.keyup(function() {
        limitChars(lydo_cot.attr('id'), 146);
    });

    //dialog message
    dialog_message.dialog({
        autoOpen: false,
        height: 200,
        resizable: false,
        width: 380,
        modal: true,
        buttons: {
            Ok: function() {
                jQuery(this).dialog("close");
            }
        },
        close: function() {
            var id_field_focus = focus_field.val();
            if (id_field_focus == null || id_field_focus == '') {
                allFields.val("");
                //quay ve man hinh tra cuu ltt
                jQuery("#frmCOTAdd").attr({action: 'CutOffTimeKBView.do'});
                jQuery("#frmCOTAdd").submit();
            } else
                jQuery("#" + id_field_focus).focus();
        }
    });
    //dialog confirm message	
    dialog_confirm.dialog({
        autoOpen: false,
        resizable: false,
        height: 200,
        width: 380,
        modal: true,
        buttons: {
            "Có": function() {
                jQuery(this).dialog("close");
                var action = evenButtonBefore.val();
                if (action != null && action == '<%=AppConstants.ACTION_ADD%>') {
                    addExcuteDTS();
                    allFields.val("");
                } else {
                    allFields.val("");
                    //quay ve man hinh tra cuu ltt
                    if ('<%=request.getParameter("referrer")%>' != 'null') {
                        strLastAct = '<%=request.getParameter("referrer")%>';
                        if (strLastAct == 'thuchiendoichieu')
                        {
                            var tu_nh = '<%=request.getParameter("nhkb_chuyen_cm")%>';
                            var ma_nh = '<%=request.getParameter("ma_nhkb")%>';
                            jQuery("#frmDTSAdd").attr({action: 'thuchiendoichieu.do?action=back&nhkb_chuyen_cm_back=' + tu_nh + '&ma_nh_back=' + ma_nh});
                            jQuery("#frmDTSAdd").submit();
                        }
                    } else
                        jQuery("#frmDTSAdd").attr({action: 'TraCuuLTT.do?targetid=dts'});
                    jQuery("#frmDTSAdd").submit();
                }
            },
            "Không": function() {
                evenButtonBefore.val('');
                jQuery(this).dialog("close");
            }
        }
    });

    //**************************BUTTON Ghi CLICK********************************************
    jQuery("#btn_ghi").click(function() { 
        allFields.removeClass('ui-state-error');
        var bValid = true;
        if (ma_nh.val() == null || ma_nh.val() == '') {
            alert('Cần chọn hệ thống ngân hàng');
//            dialog_message.dialog("open");
            focus_field.val('ma_nh');
            ma_nh.addClass("ui-state-error");
            bValid = false;
        }
      
        if (bValid && loai_cot.val() == '01') {
            if (ma_kb_tinh == null || ma_kb_tinh.val() == null) {
                alert('Cần chọn KBNN tỉnh');
//                dialog_message.dialog("open");
                focus_field.val('ma_kb_tinh');
                ma_kb_tinh.addClass("ui-state-error");
                bValid = false;
            }
        }
        
        if (bValid && (ma_kb_tinh.val() != null && ma_kb_tinh.val().toString() != '')) {
            if (kb_id == null || kb_id.val() == null) {
                alert('Cần chọn KBNN huyện');
//                dialog_message.dialog("open");
                focus_field.val('kb_id');
                kb_id.addClass("ui-state-error");
                bValid = false;
            }
        }
      
         //manhnv-20150424
              if(loai_cot.val()!='02' ){
                if(bValid && (gio_cot_moi.val()==null || gio_cot_moi.val()=='')){
                    alert('Cần nhập giờ COT mới');
                    focusField.val('gio_cot_moi');
                    gio_cot_moi.addClass( "ui-state-error" );
                    bValid=false;
//                }else if(bValid && (gio_cot_cu.val()!=null && gio_cot_cu.val()!='')){    
//                   gio_cot_cu.timepicker();
//                    if(gio_cot_cu.timepicker('getTimeAsDate')>=gio_cot_moi.timepicker('getTimeAsDate'))
//                    {
//                      alert('Gi&#7901; COT m&#7899;i ph&#7843;i l&#7899;n h&#417;n Gi&#7901; COT c&#361;');
//                      focusField.val('gio_cot_moi');
//                      gio_cot_moi.addClass( "ui-state-error" );
//                      bValid=false;
//                    }
//                    gio_cot_cu.timepicker('destroy');
                }
              }else{ 
                if(bValid && jQuery("#tn_moi").val()==''){
                  alert('Cần nhập giờ truyền nhận mới');                  
                  jQuery("#tn_moi").focus();
                  bValid=false;
                }
              }
             
              /*if (bValid && (gio_cot_moi.val() == null || gio_cot_moi.val() == '')) {
            dialog_message.html('<fmt:message key="cot_kbnn.page.message.gio_cot_moi"/>');
            dialog_message.dialog("open");
            focus_field.val('gio_cot_moi');
            gio_cot_moi.addClass("ui-state-error");
            bValid = false;
        } else if (gio_cot_cu.val() != null && gio_cot_cu.val() != '') {
            gio_cot_cu.timepicker();
            if (gio_cot_cu.timepicker('getTimeAsDate') >= gio_cot_moi.timepicker('getTimeAsDate')){
                dialog_message.html('Gi&#7901; COT m&#7899;i ph&#7843;i l&#7899;n h&#417;n Gi&#7901; COT c&#361;');
                dialog_message.dialog("open");
                focus_field.val('gio_cot_moi');
                gio_cot_moi.addClass("ui-state-error");
                bValid = false;
            }
            gio_cot_cu.timepicker('destroy');
        }*/
              //manhnv-20150424
        
        
        if (bValid && (lydo_cot.val() == null || lydo_cot.val() == '')) {
            alert('Cần nhập lý do không đồng ý nới giờ');
//            dialog_message.dialog("open");
            bValid = false;
            focus_field.val('lydo_cot');
            lydo_cot.addClass("ui-state-error");

        }
        
        if (bValid && lydo_cot.val().length > 146) {
            alert('Lý do không đồng ý nới giờ không hợp lệ (Yêu cầu < 146 ký tự)');
//            dialog_message.dialog("open");
            bValid = false;
            focus_field.val('lydo_cot');
            lydo_cot.addClass("ui-state-error");

        }       
        
        if (bValid) {
            addExcuteCOT();
            allFields.val("");
            ma_kb_tinh.attr({disabled: true});
            kb_id.attr({disabled: true});
        }        
    });
    jQuery('#gio_cot_moi').timepicker();
    //**************************BUTTON xem lenh thanh toan CLICK********************************************
    jQuery("#btn_xemLTT").click(function() {
        //ltt den
        if (nhkb_nhan_ltt_field.val() == '<%=request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>')
            window.location.href = 'listLttDenAdd.do?action=VIEW_LTTDen&so_ltt=' + ltt_id_field.val() + '&referrer=themdtslttdi';
        else
            //ltt di
            window.location.href = 'listLttAdd.do?action=VIEW_LTTDi&so_ltt=' + ltt_id_field.val() + '&referrer=themdtslttdi';

    });
    //**************************BUTTON Thoat CLICK********************************************
    jQuery("#btn_thoat").click(function() {
        document.forms[0].action = "CutOffTimeKBView.do";
        document.forms[0].submit();

    });
    jQuery("#ma_nh").change(function() {
        jQuery("#ma_kb_tinh").html('');
        jQuery.ajax({
            type: "POST",
            url: "COTAdd.do",
            data: {"ma_nh": ma_nh.val(),
                "nd": Math.random() * 100000},
            dataType: 'json',
            success: function(data, textstatus) {
                if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                    jQuery.each(data, function(i, item) {
                        var opt = jQuery('<option />', {value: item.id_cha, text: item.ten});
                        opt.appendTo(jQuery("#ma_kb_tinh"));
                    });
                    jQuery("#ma_kb_tinh").multiselect('refresh');
                }

            },
            error: function(xhr, status, error) {
                focus_field.val(status);
                alert(status + xhr.responseText);
//                dialog_message.dialog("open");
            }
        });
    });
    /*
     * List daanh sach kho bac huyen
     */
    jQuery("#loai_cot").change(function() {        
        var loai_cot = jQuery("#loai_cot").val();
        if (loai_cot == '00') {
            //ManhNV-20150424
            jQuery("#span_tn_cu").hide();
            jQuery("#span_tn_moi").hide();
            jQuery("#span_gio_cot_moi").show();
            jQuery("#span_gio_cot_cu").show();
            
            jQuery("#span_label_gio_tn_cu").hide();
            jQuery("#span_label_gio_tn_moi").hide();
            jQuery("#span_label_gio_cot_moi").show();
            jQuery("#span_label_gio_cot_cu").show();
            //ManhNV-20150424
            jQuery("#ma_kb_tinh").html('');
            jQuery("#ma_kb_tinh").multiselect('disable');
            jQuery("#ma_kb_tinh").multiselect('refresh');
            jQuery("#kb_id").html('');
            jQuery("#kb_id").multiselect('disable');
            jQuery("#kb_id").multiselect('refresh');
            gio_cot_cu.val('');
        } else if (loai_cot == '01') {
            //ManhNV-20150424
            jQuery("#span_tn_cu").hide();
            jQuery("#span_tn_moi").hide();
            jQuery("#span_gio_cot_moi").show();
            jQuery("#span_gio_cot_cu").show();
            
            jQuery("#span_label_gio_tn_cu").hide();
            jQuery("#span_label_gio_tn_moi").hide();
            jQuery("#span_label_gio_cot_moi").show();
            jQuery("#span_label_gio_cot_cu").show();
            //ManhNV-20150424
            // lay danh sach kho bac tinh theo NH

            jQuery("#ma_kb_tinh").multiselect().multiselectfilter();
            jQuery("#ma_kb_tinh").val('');
            jQuery("#ma_kb_tinh").multiselect('enable');
        }else{
          //ManhNV-20150424
            jQuery("#span_tn_cu").show();
            jQuery("#span_tn_moi").show();
            jQuery("#span_gio_cot_moi").hide();
            jQuery("#span_gio_cot_cu").hide();
            
            jQuery("#span_label_gio_tn_cu").show();
            jQuery("#span_label_gio_tn_moi").show();
            jQuery("#span_label_gio_cot_moi").hide();
            jQuery("#span_label_gio_cot_cu").hide();
            //ManhNV-20150424
        }

    });
    /*
     * List daanh sach kho bac huyen
     */
    jQuery("#ma_kb_tinh").change(function() {
        var state = true, ma_kb_tinh = jQuery("#ma_kb_tinh").val();
        if (ma_kb_tinh == null || ma_kb_tinh == '') {
            jQuery("#kb_id").html('');
            jQuery("#kb_id").attr({disabled: true});
        } else {
            state = !state;
            jQuery("#kb_id").multiselect(state ? 'disable' : 'enable');
            if (ma_kb_tinh != null) {
                jQuery("#kb_id").html('');
                jQuery.each(ma_kb_tinh, function(i, value) {
                    var optgrp = jQuery('<optgroup>');
                    optgrp.attr('label', jQuery("#ma_kb_tinh option[value='" + value + "']").text());
                    obj.filter(function(item) {
                        if (item.id_cha === value && item.ma_dv == jQuery("#ma_nh").val()) {
                            var opt = jQuery('<option />', {value: item.ma_nh, text: item.ten});
                            opt.appendTo(optgrp);
                        }
                    });
                    optgrp.appendTo(jQuery("#kb_id"));
                });


            }
        }
        jQuery("#kb_id").multiselect('refresh');
    });

    jQuery("#kb_id").change(function() {
        var kbIdVal = jQuery("#kb_id").val();
        if (kbIdVal != null && kbIdVal.length == 1) {
            jQuery.ajax({
                type: "POST",
                url: "danhsachkbhuyen.do",
                data: {"ma_kb_huyen": kbIdVal[0].toString(),
                    "ma_dv": ma_nh.val(),
                    "nd": Math.random() * 100000},
                dataType: 'json',
                success: function(data, textstatus) {
                    if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                        jQuery("#gio_cot_cu").val(data.cut_of_time);
                    }
                },
                error: function(xhr, status, error) {
                    focus_field.val(status);
                    alert(status + xhr.responseText);
//                    dialog_message.dialog("open");
                }
            });
        } else {
            jQuery("#gio_cot_cu").val('');
        }
    });

    //********************** EXCUTE ADD ***********************************  
    /*
     * action update excute
     */
    function addExcuteCOT() {
        jQuery.ajax({
            type: "POST",
            url: "COTAddExc.do",
            data: {"ma_nh": ma_nh.val(),
                "loai_cot": loai_cot.val(),
                "ma_kb_tinh": (ma_kb_tinh.val() != null) ? ma_kb_tinh.val().toString() : null,
                "ma_kb_huyen": (kb_id.val() != null) ? kb_id.val().toString() : null,
                "nguoi_lap": nguoi_lap.val(),
                "f_ngay_lap": ngay_lap.val(),
                "f_ngay_cot": ngay_cot.val(),
                "gio_cot_cu": gio_cot_cu.val(),
                "gio_cot_moi": gio_cot_moi.val(),
                "tn_cu": jQuery("#tn_cu").val(),
                "tn_moi": jQuery("#tn_moi").val(),
                "lydo_cot": lydo_cot.val(),
                "nd": Math.random() * 100000},
            dataType: 'json',
            success: function(data, textstatus) {
                if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                    focus_field.val('');
                    if (data.success != undefined && data.success != 'undefined') {
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.them_moi_thanh_cong"><fmt:param value="' + data.success + '"/></fmt:message>');
                    } else if (data.ERROR != null && data.ERROR == '<%=AppConstants.FAILURE%>') {
                        dialog_message.html('<fmt:message key="cot_kbnn.error.ttsp_error_0004"/>');
                    } else {
                        dialog_message.html('<span style=\"color:red;\">' + data.ERROR + '</span>');
                    }
                    dialog_message.dialog("open");
                }
            },
            error: function(xhr, status, error) {
                focus_field.val(status);
                alert(status + xhr.responseText);
//                dialog_message.dialog("open");
            }
        });
    }
});
</script>
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="cot_kbnn.page.title_add"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
          </tbody>
        </table>
      <table class=bordertableChungTu cellSpacing=0 cellPadding=0 width="100%">
        <tbody>
          <tr>
            <td vAlign=top>
            <div class="clearfix">
              <fieldset class="fieldsetCSS">
                <legend style="vertical-align:middle">
                  <fmt:message key="cot_kbnn.page.title_cot_yc_noigio"/>
                </legend>
                <div style="padding:3px;">
                  <div style="width:193px;">
                      <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                        <thead>
                          <tr>
                            <th class="ui-state-default ui-th-column" height="20" width="46.5%;">
                             <fmt:message key="cot_kbnn.page.lable.so_cot"/>
                            </th>
                            <th class="ui-state-default ui-th-column">
                             <fmt:message key="cot_kbnn.page.lable.tinh_trang"/>
                            </th>
                            <th class="ui-state-default ui-th-column" width="14px;">&nbsp;</th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                    <div class="ui-jqgrid-bdiv ui-widget-content" style="height: 230px; width:192px;background:#F0F0F0;"></div>
                    <div style="width:193px;">
                        <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                          <thead><tr><td class="ui-state-default ui-th-column">&nbsp;</td></tr></thead>
                        </table>
                    </div>
                </div>
               </fieldset>
              </div>
           </td>
           <td width="85%" valign="top">
             <fieldset class="fieldsetCSS">
              <legend style="vertical-align:middle">
                <fmt:message key="cot_kbnn.fieldset.title.thong_tin_chung"/>
              </legend>
              <div style="padding:10px 5px 0px 5px;">
              <input type="hidden" id="focusField"/>
              <input type="hidden" id="evenButtonBefore"/>
              <html:form styleId="frmCOTAdd"   action="/COTAdd.do">
              <html:hidden property="nguoi_lap" styleId="nguoi_lap"/>
                <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                  <tbody>
                    <tr>
                      <td align="right" width="15%">
                        <label for="ngan_hang"><fmt:message key="cot_kbnn.page.lable.ngan_hang" /></label>
                      </td>
                      <td  align="left" class="box_common_content" width="32%">
                        <html:select property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true">
                          <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                          <html:optionsCollection name="colDMNH" value="ma_dv" label="ten_nh"/>
                        </html:select>
                      </td>
                      <td align="right" width="16%" >
                        <label for="loai_cot"><fmt:message key="cot_kbnn.page.lable.loai_cot"/></label>
                      </td>
                      <td align="left">
                      <html:select property="loai_cot" styleId="loai_cot" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true">
                        <html:option value="00" >To&#224;n h&#7879; th&#7889;ng</html:option>
                        <html:option value="01">T&#7915;ng &#273;&#7883;a ph&#432;&#417;ng</html:option>
                        <html:option value="02">Nới giờ truyền nhận toàn hệ thống</html:option>
                      </html:select>
                      </td>                   
                    </tr>
                   <tr>
                         <td align=right >
                          <label for="kb_tinh"><fmt:message key="cot_kbnn.page.lable.kb_tinh"/></label>
                        </td>
                        <td width="10%">
                         <html:select multiple="multiple" property="ma_kb_tinh" styleId="ma_kb_tinh" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" disabled="true">
                           <!--<html:option value="">-----Ch&#7885;n KB t&#7881;nh-----</html:option>-->                                                 
                            <html:optionsCollection name="lstKBTinh" value="id_cha" label="ten"/>
                        </html:select>
                        </td>
                        <td align=right >
                          <label for="kb_huyen"><fmt:message key="cot_kbnn.page.lable.kb_huyen"/></label>
                        </td>
                        <td width="10%">
                         <html:select multiple="multiple" property="ma_kb_huyen" styleId="kb_id" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" disabled="true">
                        </html:select>
                        </td>
                      </tr>
                    <!--<tr>
                      <td align=right>
                        <label for="nguoi_lap"><fmt:message key="cot_kbnn.page.lable.nguoi_lap"/></label>
                      </td>
                      <td align="left">
                        
                        <html:text property="ten_nguoi_lap"  styleId="ten_nguoi_lap" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                      </td>
                      <td align=right>
                        <label for="ngay_lap"><fmt:message key="cot_kbnn.page.lable.ngay_lap"/></label>
                      </td>
                      <td align="left">
                        <html:text property="f_ngay_lap" styleId="ngay_lap" styleClass="fieldTextCode"   onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="nguoi_ks"><fmt:message key="cot_kbnn.page.lable.nguoi_kiem_soat"/></label>
                      </td>
                      <td align="left">
                        <html:text property="nguoi_ks" styleId="nguoi_ks" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true"/>
                      </td>
                      <td align=right>
                        <label for="ngay_kiem_soat"><fmt:message key="cot_kbnn.page.lable.ngay_kiem_soat"/></label>
                      </td>
                      <td align="left" >
                        <html:text property="f_ngay_ks" styleId="ngay_ks" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                      </td>
                    </tr>-->
                    <tr>
                      <td align=right>
                        <label for="ngay"><fmt:message key="cot_kbnn.page.lable.ngay"/></label>
                      </td>
                       <td align="left" >
                        <html:text property="f_ngay_cot" styleId="ngay_cot" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                       <td align=right>
                        <span id="span_label_gio_cot_cu"><label for="gio_cot_cu"><fmt:message key="cot_kbnn.page.lable.gio_cot_cu"/></label></span>
                        <span id="span_label_gio_tn_cu">Giờ truyền nhận cũ</span>
                      </td>
                    
                       <td align="left">
                        <span id="span_gio_cot_cu"><html:text property="gio_cot_cu" styleId="gio_cot_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" /></span>
                        <span id="span_tn_cu"><html:text property="tn_cu" styleId="tn_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                      </td>
                    </tr>
                    <tr>
                    <td colspan="2"></td>
                     <td align=right>
                        <span id="span_label_gio_cot_moi"><label for="gio_cot_moi"><fmt:message key="cot_kbnn.page.lable.gio_cot_moi"/></label></span>
                        <span id="span_label_gio_tn_moi">Giờ truyền nhận mới</span>
                     </td>
                     <td align="left" colspan="3">
                        <span id="span_gio_cot_moi"><html:text property="gio_cot_moi" styleId="gio_cot_moi" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                        <span id="span_tn_moi"><html:text property="tn_moi" styleId="tn_moi" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" /></span>
                     </td>
                    </tr>
                    <tr>
                       <td align=right>
                        <label for="lydo_cot"><fmt:message key="cot_kbnn.page.lable.lydo_cot"/></label>
                      </td>
                      <td  colspan="3">
                        <html:textarea property="lydo_cot"  onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="lydo_cot" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                        <span style="color:#FF0000" id="word_count_noi_dung">
                        <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_noi_dung">146</span></span>
                      </td>
                    </tr>
                  </tbody>
                </table>
                </html:form>
               </div>
              </fieldset>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div style="padding:10px 20px 0;float:right;">
                <button id=btn_ghi  type="button" accessKey=G class=buttonCommon style="width:80px;">
                <fmt:message key="cot_kbnn.page.button.save"/>
                </button>
                <button id="btn_thoat" accessKey=O  class=buttonCommon  style="width:80px;">
                <fmt:message key="cot_kbnn.page.button.exit"/>
                </button>
                <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
                
              </div>
            </td>
          </tr>
        </tbody>  
      </table>
    <div id="dialog-message" title="<fmt:message key="cot_kbnn.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
    <div id="dialog-confirm" title="<fmt:message key="cot_kbnn.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>
      <script type="text/javascript">
        jQuery("#ma_kb_tinh").multiselect({noneSelectedText: "---- Chọn KB tỉnh ----",
        selectedText:"Bạn đã chọn # KB tỉnh"}).multiselectfilter();
        jQuery("#kb_id").multiselect({noneSelectedText: "---- Chọn KB huyện ----",
        selectedText:"Bạn đã chọn # KB huyện"}).multiselectfilter();
      </script>
  <%@ include file="/includes/ttsp_bottom.inc"%>
