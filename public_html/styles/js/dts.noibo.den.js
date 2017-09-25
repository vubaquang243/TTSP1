function arrowUpDownDTSNBoDen(e) {
var keyCode = e.keyCode || e.which, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
    var input_id = "";
    var total_row = "";
    var input_id_length = jQuery(document.activeElement).attr('id').length;
    var input_id_value = jQuery(document.activeElement).attr('id').substring(8,input_id_length);
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
                jQuery("#row_dts_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_" + input_id).focus();

            }
            break;
        case arrow.down:
            total_row = (jQuery("input[name='row_item']").length);            
            input_id = parseInt(input_id_value);
            if (input_id <= parseInt(total_row) - 1) {
                //remove class highlight tr select
                if (input_id < parseInt(total_row) - 1)
                    input_id = parseInt(input_id) + 1;
                jQuery("#col_dts_" + input_id).focus();
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');
                //add class highligh tr previous
                jQuery("#row_dts_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_" + input_id).addClass('ui-state-highlight');
            }
            break;
        case arrow.enter:
            if (jQuery(document.activeElement).attr('class') == 'ui-state-highlight') {
                jQuery('#row_dts_' + input_id_value).click();
            }
            break;
        case arrow.esc:
            var idTRSelected = jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
            if (jQuery(document.activeElement).attr('id') == 'lydo_ktt_day_lai') {
                jQuery('#' + idTRSelected).click();
                rowSelectedFocusTuDo(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            }
            else {
                jQuery('#' + idTRSelected).click();
                rowSelectedFocusTuDo(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            }
            break;
        }
    }

    function rowSelectedFocusNBoDen(rowId) {
        classRowHighLight(rowId);
        jQuery('#' + rowId).find('input').focus();

    }
function limitCharsDTSNoiBoDen(id, maxlength) {

    var limit = maxlength;
    var text = jQuery("#" + id).val();
    var textlength = text.length;
    if (parseInt(textlength) > parseInt(limit)) {
        jQuery("#word_count_" + id).html("B&#7841;n &#273;&#227; nh&#7853;p v&#432;&#7907;t qu&#225; " + limit + " k&#237; t&#7921;!");
        jQuery("#lydo_ktt_day_lai").keyup(function () {
            this.value = this.value.substr(0, limit);
        });
        jQuery("#noidung").keyup(function () {
            this.value = this.value.substr(0, limit);
        });
        return false;
    }
    else if (parseInt(textlength) == 0) {
        jQuery('#word_count_' + id).html('T&#7893;ng s&#7889; k&#237; t&#7921; ' + limit + '.');
    }
    else {
        jQuery('#word_count_' + id).html('C&#242;n l&#7841;i ' + (limit - textlength) + ' k&#237; t&#7921;.');
        return true;
    }

}

//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * 
   */
function roleUserDTSNoiBoDen(pQuyen, trangthai) {
    var delimiter = '|';
    var chucdanh;
    var chucdanhTTV;
    var chucdanhKTT;
    var chucdanhCBTTTT;
    var chucdanhCBPTTTTT;
    var chucdanhGD;
    if (pQuyen != null && pQuyen != '') {
        chucdanh = pQuyen.split(delimiter);
        // gan quyen
        for (var i = 0;i < chucdanh.length;i++) {
            if (chucdanh[i] == "TTV") {
                chucdanhTTV = chucdanh[i];
            }
            else if (chucdanh[i] == "KTT") {
                chucdanhKTT = chucdanh[i];
            }
            else if (chucdanh[i] == "GD") {
                chucdanhGD = chucdanh[i];
            }
            else if (chucdanh[i] == "CB-TTTT") {
                chucdanhCBTTTT = chucdanh[i];
            }
            else if (chucdanh[i] == "CBPT-TTTT") {
                chucdanhCBPTTTTT = chucdanh[i];
            }
        }
        var typeUser = "0";
        // set quyen
        // truong hop 1 : User co tat ca cac quyen
        // chi can set 
        if ((chucdanhTTV != null && chucdanhKTT != null) || (chucdanhTTV != null && chucdanhGD != null)) {
            typeUser = '1';
            buttonWithTrangThaiDTSNoiBoDen(typeUser, trangthai);
        }
        else {
            //truong hop 2 : User la giam doc hoac KTT 
            if (chucdanhKTT != null || chucdanhGD != null||chucdanhCBPTTTTT!=null) {
                if (chucdanhKTT == "KTT" || chucdanhGD == "GD" || chucdanhCBPTTTTT=="CBPT-TTTT") {
                    typeUser = '2';
                    buttonWithTrangThaiDTSNoiBoDen(typeUser, trangthai);
                   
                }
            }
            else {
                if ((chucdanhTTV != null && chucdanhTTV == "TTV")||chucdanhCBTTTT=="CB-TTTT") {
                    typeUser = '3';
//                     alert(chucdanhCBTTTT +"-----"+typeUser);
                    buttonWithTrangThaiDTSNoiBoDen(typeUser, trangthai);
                }
            }
        }
    }
}

function ButtonPhanQuyenKTT() {
    jQuery("#xacnhan").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").show();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : ""
    }).val('');
}

function ButtonPhanQuyenTTV() {
    jQuery("#xacnhan").show();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : "disabled"
    }).val('');
}

function ButtonPhanQuyen() {
    jQuery("#xacnhan").show();
    jQuery("#timKiem").show();
    jQuery("#duyet").show();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : ""
    });
}

function ButtonPhanQuyenTKiemNBoDen() {
    jQuery("#xacnhan").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : ""
    }).val('');
}

function removeFormDTSNoiBoDen() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ttv_nhan").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#noidung").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ktt").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "true"
    }).val('');

}

function setStyleRowEmptyDTSNoiBoDen() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}

function ButtonPhanQuyenGuestDTSNoiBoDen() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : "disabled"
    });
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#data-grid tr").removeAttr("onclick");
}

function selectedTopRowNoiBoDen(id, col, chucdanh, bCheck) {
    // viet 1 ham select dau tien giong ham nay
    if (chucdanh != null && chucdanh != '') {
        roleUserDTSNoiBoDen(chucdanh, '');
        resetFormDTSNoiBoDen();
    }
    if (bCheck == true) {
        ButtonPhanQuyenGuestDTSNoiBoDen();
        defaultRowSelectedNBoDen(true);
        resetFormDTSNoiBoDen();
    }
    else {
        if (id == null || id == '' || id == 'undefined') {
            setStyleRowEmptyDTSNoiBoDen();
            removeFormDTSNoiBoDen();
        }
        else {
            defaultRowSelectedNBoDen(bCheck);
            //        resetFormDTSNoiBoDen();
        }
    }

}

function defaultRowSelectedNBoDen(bCheck) {
    var row_default = "row_dts_0",
        input_default = jQuery('#col_dts_0');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataDTSNoiBoDen(input_default.val(), row_default, bCheck);
        rowSelectedFocus(row_default);
    }
}
//************************************ RESET FROM **********************************
function resetFormDTSNoiBoDen() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    }).focus();
    jQuery("#ttv_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#ktt_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#noidung").attr( {
        disabled : "disabled"
    });
        jQuery("#lydo_ktt_day_lai").attr( {
        disabled : "disabled"
    });
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "readonly"
    });
    jQuery("#ten_don_vi_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_ttv_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_ktt").attr( {
        disabled : "disabled"
    });
}

function fillDataDTSNoiBoDen(id, tr_id, bCheck) {
    //    resetFormDTSNoiBoDen();
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatNoiBoDenView.do", data :  {
            "id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        jQuery("#id").val(data.id);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                        jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
                        jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                        jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
                        jQuery("#ttv_nhan").val(data.ten_ttv_nhan);
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ma_ktt").val(data.ma_ktt);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#ktt_duyet").val(data.ten_ktt_duyet);
                        jQuery("#noidung").val(data.noidung);
                        jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                        jQuery("#ngay_duyet").val(data.ngay_duyet);
                        jQuery("#mo_ta_trang_thai").val(data.mo_ta_trang_thai);
                        jQuery("#rowSelected").val(tr_id);
                        jQuery("#trang_thai").val(data.trang_thai);
                        if (!bCheck) {
                            roleUserDTSNoiBoDen(data.chuc_danh, data.trang_thai);
                        }
                        else {
                            disabledAllButtonDTSDen();
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }

    });
    //Change style
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trs.length;j++) {
        jQuery("#row_dts_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
            jQuery("#" + tr_id).attr( {
                'class' : 'ui-row-ltr ui-state-highlight'
            });
        }
    }
}

//********************** Tim kiem dien tra soat***********************************   
function findDTSNoiBoDen() {
    removeFormDTSNoiBoDen();
    var maTTV = jQuery("#ma_ttv_tk").val(), nh_kb_nhan = jQuery("#nh_kb_nhan_tk").val(), so_dts = jQuery("#so_dts_tk").val();

    jQuery.ajax( {
        type : "POST", url : "DTSoatNoiBoDen.do", data :  {
            "ttv_nhan" : maTTV, "nhkb_nhan" : nh_kb_nhan, "id" : so_dts, "action" : 'FIND', "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data.logout != null && data.logout) {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    var loc = window.location;
                    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
                    var path = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
                    fillDataTableDSTNoiBoDen(data, path);
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });

}

//fill data table
function fillDataTableDSTNoiBoDen(data, path) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    jQuery.each(data, function (i, objectDTS) {
        var trangthai = objectDTS.trang_thai;
        var trangthai_path = "";
        if (trangthai != null) {
            if (trangthai == '00') {
                trangthai_path = "<img src=\"" + path + "styles/images/choxuly.jpg\" " + "border=\"0\" title=\"Ch&#7901; x&#7917; l&#253;\"/>";
            }
            if (trangthai == '02') {
                trangthai_path = "<img src=\"" + path + "styles/images/wait.jpeg\" " + "border=\"0\" title=\"Ch&#7901; KTT duy&#7879;t\"/>";
            }
            else if (trangthai == '01') {
                trangthai_path = "<img src=\"" + path + "styles/images/return.jpeg\" " + "border=\"0\" title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }
            else if (trangthai == '03') {
                trangthai_path = " <img src=\"" + path + "styles/images/active.gif\" " + "border=\"0\" title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (trangthai == '04') {
                trangthai_path = " <img src=\"" + path + "styles/images/delete1.gif\" " + "border=\"0\" title=\"H&#7911;y\"/>";
            }
            var chucdanh= objectDTS.chuc_danh;
            if(null !=chucdanh && 'undefined'!=chucdanh){
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" onkeydown=\"focusDayLaiDTSNBoDen('"+objectDTS.chuc_danh+"',trang_thai);\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSNoiBoDen('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }else{
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSNoiBoDen('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_dts_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_dts_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectDTS.id + "\" onkeydown=\"arrowUpDownDTSNBoDen(event)\" readonly=\"true\"/></td>";
            strTableData += "<td align=\"center\">" + trangthai_path + "</td></tr>";
        }
    });
    if (data == "" || data == null) {
        //        jQuery("#data-grid").html('<tbody> <center>Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</center> </tbody>');
        jQuery("#data-grid").html('<tbody><tr><td colspan="5" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyDTSNoiBoDen();
        ButtonPhanQuyenTKiemNBoDen();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        selectedTopRowNoiBoDen(jQuery("#col_dts_0").val(), "row_dts_0", '');
    }
    tableHighlightRow_DTS_NBo_Den();
}

function tableHighlightRow_DTS_NBo_Den() {
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

function refreshGridDTSNoiBoDen() {
    jQuery.ajax( {
        type : "POST", url : "DTSoatNoiBoDen.do", data :  {
            "action" : 'REFRESH', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        var loc = window.location;
                        var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
                        var path = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
                        fillDataTableDSTNoiBoDen(data, path);
                    }
                }

            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function setStyleRowSelected() {

    var rowSelected = jQuery("#rowSelected").val();
    if (rowSelected != null && rowSelected != '')
        jQuery("#" + rowSelected).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
    if (jQuery("#" + rowSelected).attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#" + rowSelected).attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
    jQuery("#noidung").attr( {
        disabled : 'disabled'
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : 'disabled'
    });
}

function disabledAllButtonDTSDen() {
    jQuery("#xacnhan").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : 'disabled'
    });
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#data-grid tr").attr("disabled", true);
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "readonly"
    });
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "readonly"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        readonly : "readonly"
    });
    jQuery("#ttv_nhan").attr( {
        readonly : "readonly"
    });
    jQuery("#ngay_nhan").attr( {
        readonly : "true"
    });
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    });
    jQuery("#ngay_duyet").attr( {
        readonly : "true"
    });
    jQuery("#noidung").attr( {
        disabled : 'disabled'
    });
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "true"
    });
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ma_ttv_nhan").attr( {
        readonly : "true"
    });
    jQuery("#ma_ktt").attr( {
        readonly : "true"
    });
}

function selectedRowBeforeClickButtonNoiBoDen() {
    var rSelected = jQuery("#rowSelected").val();
    if (rSelected == null || rSelected == '') {
        jQuery("#dialog-message-check").html('B&#7841;n ph&#7843;i ch&#7885;n m&#7897;t S&#7889; &#273;i&#7879;n tra so&#225;t trong danh s&#225;ch s&#7889; &#273;i&#7879;n tra so&#225;t.');
        jQuery("#dialog-message-check").dialog("open");
        return false;
    }
    return true;
}
// Exit 
function exitViewDTSNoiBoDen() {
    document.forms[0].action = "thoatView.do";
    document.forms[0].submit();
}

function initStateButton() {
    jQuery("#xacnhan").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#thoatDTSTD").show();
}

function buttonWithTrangThaiDTSNoiBoDen(typeUser, trangthai) {
    initStateButton();
    // ca quyen KTT VA TTV
    if (typeUser == '1') {
        if (trangthai == '' || trangthai == null) {
            initStateButton();
        }
        else if (trangthai == '00') {
            jQuery("#xacnhan").show();
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#duyet").show();
        }
        else if (trangthai == '01') {
            //  KTT day lai
            jQuery("#sua").show();
            jQuery("#them").show();
        }
        else if (trangthai == '03') {
            //  KTT duyet
            jQuery("#them").show();
        }
        else if (trangthai == '04') {
            //  KTT duyet
            jQuery("#them").show();
        }
    }
    // quyen ktt
    else if (typeUser == '2') {
        if (trangthai == '' || trangthai == null) {
            initStateButton();
        }
        else if (trangthai == '00') {
            //        jQuery("#huy").hide();
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#duyet").show();
            jQuery("#dayLai").show();
        }
    }
    // quyen ttv
    else if (typeUser == '3') {
        if (trangthai == '' || trangthai == null) {
            jQuery("#them").show();
        }
        else if (trangthai == '00') {
            //        jQuery("#huy").hide();
            jQuery("#xacnhan").show();
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#them").show();
        }
        else if (trangthai == '01') {
            //  KTT day lai
            jQuery("#huy").show();
            jQuery("#them").show();
        }
        else if (trangthai == '03') {
            //  KTT duyet
            jQuery("#them").show();
        }
        else if (trangthai == '04') {
            //  KTT duyet
            jQuery("#them").show();
        }
    }
}
//focus vao truong day lai 
function focusDayLaiDTSNBoDen(chucdanh, trangthai) {
    // neu la KTT va trang thai la cho duyet    
    if (chucdanh == 'KTT' && trangthai.value == '02') {
        // thi moi duoc su dung phim esc de focus len lydo_ktt_day_lai
        if (event.keyCode == 13) {
            jQuery("#lydo_ktt_day_lai").removeAttr("readonly");
            jQuery("#lydo_ktt_day_lai").focus();
        }
    }
}

function focusDayLaiDTSNBoDenMaster(check) {
    if (check) {
        if (event.keyCode == 27) {
            defaultRowSelectedTuDo(check);
        }
    }
}