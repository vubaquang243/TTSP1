function toggle(source) {
  checkboxes = document.getElementsByName('chklist');
  for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}
function messageUpdate(id,strAction){
  if(strAction.toUpperCase() == 'ACTION_PASS'){
        alert(GetUnicode('Duy&#7879;t b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
      }else if(strAction.toUpperCase() == 'ACTION_RETURN'){
        alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
      }else if(strAction.toUpperCase() == 'ACTION_REMOVE'){
        alert(GetUnicode('H&#7911;y b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
      }
}
function updateExcuteBKQT(strAction) {   
    document.forms[0].action="XuLyQToanTQuocUpdateExc.do?action="+strAction;
    document.forms[0].submit();
  
//    jQuery.ajax( {
//        type : "POST", url : "XuLyLenhQuyetToanUpdateExc.do", data :  {
//            "id" : jQuery("#id").val(),"ldo_hach_toan" : jQuery("#ldo_hach_toan").val(),"action" : strActionKTT,"loai_hach_toan" : jQuery("#loai_hach_toan").val(), "ldo_day_lai" : jQuery("#ldo_day_lai").val(), "nd" : Math.random() * 100000
//        },
//        dataType : 'json', success : function (data, textstatus) {
//            if (textstatus != null && textstatus == 'success') {
//                if (data.Success) {
//                    if(data.typeAction=='daylai'){
//                      jQuery("#dialog-message").html('&#272;&#7849;y l&#7841;i l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');  
//                    }else if(data.typeAction =='duyet'){
//                      jQuery("#dialog-message").html('Duy&#7879;t l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');  
//                    }else{
//                      jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');
//                    }
//                }
//                else {
//                    if(data.typeAction=='daylai'){
//                      jQuery("#dialog-message").html('&#272;&#7849;y l&#7841;i l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');  
//                    }else if(data.typeAction =='duyet'){
//                      jQuery("#dialog-message").html('Duy&#7879;t l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');  
//                    }else{
//                      jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');
//                    }
//                }
//                defaultRowSelectedBKQT();
//                jQuery("#dialog-message").dialog("open");
//            }
//        },
//        error : function (textstatus) {
//            jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n th&#7845;t b&#7841;i !');
//            jQuery("#dialog-message").dialog("open");
//        }
//    });
//    refreshGridBKQT();
}
function changeStateArea() {
    jQuery('#loai_hach_toan').change(function () {
        jQuery("select option:selected").each(function () {
            value = jQuery(this).val();
            if (value != null && '' != value) {
                jQuery('#ldo_hach_toan').removeAttr('style');
                jQuery("#ldo_hach_toan").removeAttr('readonly').focus();              
            }
            else {
                jQuery("#ldo_hach_toan").attr('readonly', 'readonly');
            }
        });
    }).change();
}

function fillDataBKQuyetToan(id, tr_id) {
    document.forms[0].action="XuLyQToanTQuocView.do?id="+id+"&rowSelected="+tr_id;
    document.forms[0].submit();
//    jQuery.ajax( {
//        type : "POST", url : "XuLyQToanTQuocView.do", data :  {
//            "id" : id, "nd" : Math.random() * 100000
//        },
//        dataType : 'json', success : function (data, textstatus) {
//            if (textstatus != null && textstatus == 'success') {
//                if (data != null) {
//                    if (data.logout != null && data.logout) {
//                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
//                        document.forms[0].submit();
//                    }
//                    else {
//                        jQuery("#id").val(data.id);
//                        
//                        jQuery("#so_tien").val(data.so_tien);
//                        jQuery("#nguoi_tao").val(data.nguoi_tao);
//                        jQuery("#ten_nguoi_tao").val(data.ten_nguoi_tao);
//                        jQuery("#ngay_tao").val(data.ngay_tao);
//                        jQuery("#nguoi_ks").val(data.nguoi_ks);
//                        jQuery("#ten_nguoi_ks").val(data.ten_nguoi_ks);
//                        jQuery("#ngay_ks").val(data.ngay_ks);
//                        jQuery("#lydo_daylai").val(data.lydo_daylai);
//                        if(data.trang_thai=='01'){
//                          jQuery("#mo_ta_trang_thai").val(GetUnicode("Ch&#7901; KTT duy&#7879;t"));
//                          jQuery("#trang_thai").val(GetUnicode("Ch&#7901; KTT duy&#7879;t"));
//                        }else if(data.trang_thai=='02'){
//                          jQuery("#mo_ta_trang_thai").val(GetUnicode("&#272;&#227; duy&#7879;t"));
//                          jQuery("#trang_thai").val(GetUnicode("&#272;&#227; duy&#7879;t"));
//                        }else if(data.trang_thai=='03'){
//                          jQuery("#mo_ta_trang_thai").val(GetUnicode("KTT &#272;&#7849;y l&#7841;i"));
//                          jQuery("#trang_thai").val(GetUnicode("KTT &#272;&#7849;y l&#7841;i"));
//                        }else if(data.trang_thai=='04'){
//                          jQuery("#mo_ta_trang_thai").val(GetUnicode("H&#7911;y"));
//                          jQuery("#trang_thai").val(GetUnicode("H&#7911;y"));
//                        }
//                        
//                        jQuery("#rowSelected").val(tr_id);
//                        roleUserBKQT(data.chuc_danh, data.trang_thai);
//                    }
//                }
//            }
//        },
//        error : function (textstatus) {
//            alert(textstatus.messge);
//        }
//
//    });
}
//ManhNV-2014: rao vi trung ham*************************************************
//function CurrencyFormatted(num,ma_nt)
//{
//    if(num==null || num=='')
//      return;
//    num = num.toString().replace(/\$|\,/g,'');
//    if(isNaN(num))
//    num = "0";
//    sign = (num == (num = Math.abs(num)));
//    num = Math.floor(num*100+0.50000000001);
//    cents = num%100;
//    num = Math.floor(num/100).toString();
//    if(cents<10)
//    cents = "0" + cents;
//    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
//    num = num.substring(0,num.length-(4*i+3))+'.'+
//    num.substring(num.length-(4*i+3));
//    if(ma_nt.toUpperCase()!='VND')
//      return (((sign)?'':'-')  + num + ',' + cents);
//    else
//      return (((sign)?'':'-')  + num );
//}
//******************************************************************************
function defaultRowSelectedBKQT() {
    var row_default = "row_qt_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        //fillDataBKQuyetToan(input_default.val(), row_default);
        rowSelectedFocusBKQT(row_default);
    }
}

function classRowHighLight(tr_id) {
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trs.length;j++) {
        jQuery("#row_qt_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_qt_" + j).find('input').attr( {
            'class' : ''
        });
        if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
            jQuery("#" + tr_id).attr( {
                'class' : 'ui-row-ltr ui-state-highlight'
            });
            jQuery("#" + tr_id).find('input').attr( {
                'class' : 'ui-state-highlight'
            });
        }
    }
    jQuery('#' + tr_id).find('input').focus();
}

function rowSelectedFocusBKQT(rowId) {
    classRowHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();

}

function refreshGridBKQT() {
    document.forms[0].action="XuLyQToanTQuoc.do";
    document.forms[0].submit();
    //    resetFormDTSTuDo();
//    jQuery.ajax( {
//        type : "POST", url : "XuLyQToanTQuoc.do", data :  {
//            "action" : 'REFRESH', "nd" : Math.random() * 100000
//        },
//        dataType : 'json', success : function (data, textstatus) {
//            if (textstatus != null && textstatus == 'success') {
//                if (data != null) {
//                    if (data.logout != null && data.logout) {
//                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
//                        document.forms[0].submit();
//                    }
//                    else {
//                        var loc = window.location;
//                        var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
//                        var path = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
//                        fillDataTableBKQT(data, path);
//                    }
//                }
//            }
//        },
//        error : function (textstatus) {
//            alert(textstatus);
//        }
//    });
}
//fill data table
function fillDataTableBKQT(data, path) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    jQuery.each(data, function (i, objectQT) {
        var trangthai = objectQT.trang_thai;
        var trangthai_path = "";
        if (trangthai != null) {
            if (trangthai == '01') {
                trangthai_path = "<img src=\"" + path + "styles/images/edit.gif\" " + "border=\"0\" title=\"Ch&#7901; KTT duy&#7879;t\"/>";
            }
            else if (trangthai == '02') {
                trangthai_path = "<img src=\"" + path + "styles/images/wait.jpeg\" " + "border=\"0\" title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (trangthai == '03') {
                trangthai_path = " <img src=\"" + path + "styles/images/return.jpeg\" " + "border=\"0\" title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }
            else if (trangthai == '04') {
                trangthai_path = " <img src=\"" + path + "styles/images/active.gif\" " + "border=\"0\" title=\"H&#7911;y\"/>";
            }
            var chucdanh = objectQT.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_qt_" + i + "\" ondblclick=\"rowSelectedFocusBKQT('row_qt_" + i + "');\" onclick=\"rowSelectedFocusBKQT('row_qt_" + i + "');fillDataBKQuyetToan('" + objectQT.id + "','row_qt_" + i + "');\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_qt_" + i + "\" ondblclick=\"rowSelectedFocusBKQT('row_qt_" + i + "');\" onclick=\"rowSelectedFocusBKQT('row_qt_" + i + "');fillDataBKQuyetToan('" + objectQT.id + "','row_qt_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_qt_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_qt_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectQT.id + "\" onkeydown=\"arrowUpDownBKQT(event)\" readonly=\"true\"/></td>";
            strTableData += "<td  align=\"center\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyBKQT();
        //ButtonPhanQuyenTuDo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        defaultRowSelectedBKQT();
    }
    tableHighlightRowBKQT();
}

function tableHighlightRowBKQT() {
    if (document.getElementById && document.createTextNode) {
        var tables = document.getElementsByTagName('table');
        for (var i = 0;i < tables.length;i++) {
            if (tables[i].className == 'data-grid') {
                var trs = tables[i].getElementsByTagName('tr');
                for (var j = 0;j < trs.length;j++) {
                    if (trs[j].parentNode.nodeName == 'TBODY') {
                        trs[j].onmouseover = function () {
                            if (this.className != 'ui-row-ltr ui-state-highlight')
                                this.className = 'ui-row-ltr ui-state-hover';
                            return false
                        }
                        trs[j].onmouseout = function () {
                            if (this.className != 'ui-row-ltr ui-state-highlight')
                                this.className = 'ui-widget-content ui-row-ltr';
                            return false;
                        }

                    }
                }
            }
        }
    }
}

function arrowUpDownBKQT(e) {
    var keyCode = e.keyCode || e.charCode, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
    var input_id = "";
    var total_row = "";
    var input_id_length = jQuery(document.activeElement).attr('id').length;
    var input_id_value = jQuery(document.activeElement).attr('id').substring(7, input_id_length);
    switch (keyCode) {
        case arrow.up:
            input_id = parseInt(input_id_value);
            if (input_id >= 0) {
                //remove class highlight tr select
                if (input_id > 0)
                    input_id = input_id - 1;
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');

                //add class highligh tr previous
                jQuery("#row_qt_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_qt_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_qt_" + input_id).focus();

            }
            break;
        case arrow.down:
            total_row = (jQuery("input[name='row_item']").length);
            input_id = parseInt(input_id_value);
            if (input_id <= parseInt(total_row) - 1) {
                //remove class highlight tr select
                if (input_id < parseInt(total_row) - 1)
                    input_id = parseInt(input_id) + 1;
                jQuery("#col_qt_" + input_id).focus();
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');
                //add class highligh tr previous
                jQuery("#row_qt_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_qt_" + input_id).addClass('ui-state-highlight');
            }
            break;
        case arrow.enter:
            if (jQuery(document.activeElement).attr('class') == 'ui-state-highlight') {
                jQuery('#row_qt_' + input_id_value).click();
            }
            break;
        case arrow.esc:
            var idTRSelected = jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
            jQuery('#' + idTRSelected).click();
            rowSelectedFocusBKQT(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }

}

function setStyleRowEmptyBKQT() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}

function roleUserBKQT(pQuyen, trangthai) {
    var typeUser;
    if (pQuyen != null || pQuyen != '') {
        // set quyen
        // truong hop 1 : TTV
        // hien thi button ttv
        if (pQuyen.indexOf("TTV") !=  - 1) {
            typeUser = 0;
            buttonWithTrangThaiBKQT(typeUser, trangthai);
        }
        else if (pQuyen.indexOf("KTT") !=  - 1) {
            //truong hop 2 : User la  KTT 
            typeUser = 1;
            buttonWithTrangThaiBKQT(typeUser, trangthai);
        }else{
            buttonForBKeQToan();
        }
    }
}

function buttonForBKeQToan() {
    jQuery("#daylai").hide();
    jQuery("#huy").hide();
    jQuery("#duyet").hide();
    jQuery("#in").hide();
    jQuery("#thoat").show();
}



function buttonWithTrangThaiBKQT(type, trangthai) {
    // TTV
    /*
     * Trang thai
     * 01 - cho ktt duyet
     * 02 - da duyet
     * 03 - ktt day lay
     * 04 - huy     
     * */
     buttonForBKeQToan();
     defaultStateFormBK();
    if (type == 0) {
        jQuery("#in").show();
        if (trangthai == 03) {
            jQuery('#frmBKQT textarea').attr('readonly', '');
            jQuery('#frmBKQT textarea').attr( {
                  'style' : 'BACKGROUND-COLOR: #ffffff;'
              });
            jQuery("#huy").show();
        }
        else {
        }
    }
    // ktt
    else {
        if (trangthai == 01) {
            jQuery("#daylai").show();
            jQuery("#duyet").show();
            jQuery('#frmBKQT textarea').attr('readonly', '');
            jQuery('#frmBKQT textarea').attr( {
                  'style' : 'BACKGROUND-COLOR: #ffffff;'
              });
        }
    }
}

function defaultStateFormBK() {
    jQuery('#frmBKQT input').attr('readonly', 'readonly');
    jQuery('#frmBKQT textarea').attr('readonly', 'readonly');
    jQuery('#frmBKQT textarea').attr( {
        'style' : 'BACKGROUND-COLOR: #f0f0f0;'
    });
}

function nextElementFocusBKQT(e) {
    var keynum;
    if (window.event)// IE
    {
        keynum = e.keyCode;
        if (keynum == 13) {
            window.event.keyCode = 9;
        }
    }
    else if (e.which)// Netscape/Firefox/Opera
    {
        keynum = e.which;
        if (keynum == 13) {
            try {
                e.keyCode = 9;
            }
            catch (e) {
                alert(e);
            }
        }
    }
}