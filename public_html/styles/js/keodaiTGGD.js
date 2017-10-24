function defaultButton(chucdanh) {
    if (chucdanh == "TTV") {
        jQuery("#tao").show();
    }
    else {
        jQuery("#tao").hide();
    }
    jQuery("#traloi").hide();
//    jQuery("#sua").hide();
    jQuery("#ghi").hide();
//    jQuery("#huy").hide();
//    jQuery("#duyet").hide();
//    jQuery("#dayLai").hide();
    jQuery("#xacnhan").hide();
}

function removeAllField() {
    jQuery("#ma_don_vi_tra_soat").val('');
    jQuery("#ten_don_vi_tra_soat").val('');
    jQuery("#ma_don_vi_nhan_tra_soat").val('');
    jQuery("#ten_don_vi_nhan_tra_soat").val('');
    jQuery("#fieldTextCode").val('');
    jQuery("#ten_ttv_nhan").val('');
    jQuery("#ngay_nhan").val('');
    jQuery("#ma_ktt").val('');
    jQuery("#ten_ktt_duyet").val('');
    jQuery("#ngay_duyet").val('');
    jQuery("#noidung").val('');
    jQuery("#thoi_gian_keo_dai").val('');
    jQuery("#gio_keo_dai").val('');
    jQuery("#phut_keo_dai").val('');
    jQuery("#lydo_ktt_day_lai").val('');
    jQuery("#noidung_traloiY").attr('checked', '');
    jQuery("#noidung_traloiN").attr('checked', '');
}

function dataGridReadonly() {
    jQuery("#data-grid tr").removeAttr("onclick");
    jQuery("#data-dtsden-grid tr").removeAttr("onclick");
}

function returnViewDTS() {
    document.forms[0].action = "KeoDaiTGGD.do";
    document.forms[0].submit();
}

function defaultRowSelected() {
    var row_default = "row_dts_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataTGGD(input_default.val(), row_default);
        rowSelectedFocus(row_default);
    }
}

function rowSelectedFocus(rowId) {
    classRowHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();

}

function classRowHighLight(tr_id) {
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    var trsDen = document.getElementById('data-dtsden-grid').getElementsByTagName('tr');
    for (var j = 0;j < trsDen.length;j++) {
        jQuery("#row_dts_den_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_dts_den_" + j).find('input').attr( {
            'class' : ''
        });
    }
    for (var j = 0;j < trs.length;j++) {
        jQuery("#row_dts_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_dts_" + j).find('input').attr( {
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

function rowDTSDenSelectedFocus(rowId) {
    classRowDTSDenHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();

}

function classRowDTSDenHighLight(tr_id) {
    var trs = document.getElementById('data-dtsden-grid').getElementsByTagName('tr');
    var trsDi = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trsDi.length;j++) {
        jQuery("#row_dts_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_dts_" + j).find('input').attr( {
            'class' : ''
        });
    }
    for (var j = 0;j < trs.length;j++) {
        jQuery("#row_dts_den_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_dts_den_" + j).find('input').attr( {
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

function fillDataForCreate() {
    removeAllField();
    jQuery.ajax( {
        type : "POST", url : "KeoDaiTGGDAdd.do", data :  {
            "nd" : Math.random() * 100000
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
                        jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
                        jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
                        jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                        jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                        jQuery("#ttv_nhan").val(data.ttv_nhan);
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ten_ttv_nhan").val(data.ten_ttv_nhan);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#ktt_duyet").val(data.ktt_duyet);
                        jQuery("#ngay_duyet").val(data.ngay_duyet);
                        jQuery("#noidung").val(GetUnicode(data.noidung));

                        if (data.noidung_traloi != null && 'undefined' != data.noidung_traloi) {
                            if (data.noidung_traloi.toUpperCase() == 'Y') {
                                jQuery("#noidung_traloiY").attr('checked', 'checked');
                            }
                            else {
                                jQuery("#noidung_traloiN").attr('checked', 'checked');
                            }
                            jQuery("#noidung_traloi_hidden").val(data.noidung_traloi);

                        }
                        jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                        jQuery("#thoi_gian_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(0, 10) : '');
                        jQuery("#gio_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(11, 13) : '');
                        jQuery("#phut_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(14, 16) : '');
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }

    });
}

function fillDataTGGD(id, tr_id) {
    jQuery.ajax( {
        type : "POST", url : "KeoDaiTGGDView.do", data :  {
            "id" : id, "nd" : Math.random() * 100000
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
                        jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
                        jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
                        jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                        jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                        jQuery("#ttv_nhan").val(data.ttv_nhan);
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ten_ttv_nhan").val(data.ten_ttv_nhan);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#ktt_duyet").val(data.ktt_duyet);
                        jQuery("#ma_ktt").val(data.ma_ktt);
                        jQuery("#ten_ktt_duyet").val(data.ten_ktt_duyet);
                        jQuery("#ngay_duyet").val(data.ngay_duyet);
                        jQuery("#noidung").val(data.noidung);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#mo_ta_trang_thai").val(data.mo_ta_trang_thai);
                        if (data.noidung_traloi != null && '' != data.noidung_traloi) {
                            if (data.noidung_traloi.toUpperCase() == 'Y') {
                                jQuery("#noidung_traloiY").attr('checked', 'checked');
                            }
                            else {
                                jQuery("#noidung_traloiN").attr('checked', 'checked');
                            }
                        }
                        else {
                            jQuery("#noidung_traloiN").attr('checked', '');
                            jQuery("#noidung_traloiY").attr('checked', '');
                        }
                        var strTypeDTS = (data.dts_id != null && data.dts_id != '' && data.dts_id !='undefined') ? data.dts_id : '';
                        jQuery("#noidung_traloi_hidden").val(data.noidung_traloi);
                        jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                        jQuery("#thoi_gian_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(0, 10) : '');
                        jQuery("#gio_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(11, 13) : '');
                        jQuery("#phut_keo_dai").val((data.thoi_gian_keo_dai != null && data.thoi_gian_keo_dai != 'undefined') ? data.thoi_gian_keo_dai.substring(14, 16) : '');
                        jQuery("#rowSelected").val(tr_id);
                        roleUser(data.chuc_danh, data.trang_thai, strTypeDTS);
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }

    });
}

function handleClick(myRadio) {
    currentValue = myRadio.value;
    jQuery("#noidung_traloi_hidden").val(currentValue);
}

function nextElementFocusKDTG(e) {
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

function refreshGrid() {
    //    resetFormDTSTuDo();
    jQuery.ajax( {
        type : "POST", url : "KeoDaiTGGD.do", data :  {
            "nd" : Math.random() * 100000
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
                        fillDataTable(data, path);
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function refreshDTSDenGrid() {
    //    resetFormDTSTuDo();
    jQuery.ajax( {
        type : "POST", url : "KeoDaiTGGD.do", data :  {
            "nd" : Math.random() * 100000
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
                        fillDataTableDTSDen(data, path);
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}
//fill data table
function fillDataTable(data, path) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    jQuery.each(data, function (i, objectQT) {
        var trangthai = objectQT.trang_thai;
        var trangthai_path = "";
        if (trangthai != null) {
            if (trangthai == '00') {
                trangthai_path = "<img src=\"" + path + "styles/images/edit.gif\" " + "border=\"0\" title=\"Ch&#7901; TTV ho&#224;n thi&#7879;n\"/>";
            }
            else if (trangthai == '01') {
                trangthai_path = "<img src=\"" + path + "styles/images/wait.jpeg\" " + "border=\"0\" title=\"Ch&#7901; KTT duy&#7879;t\"/>";
            }
            else if (trangthai == '02') {
                trangthai_path = " <img src=\"" + path + "styles/images/return.jpeg\" " + "border=\"0\" title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }
            else if (trangthai == '03') {
                trangthai_path = " <img src=\"" + path + "styles/images/active.gif\" " + "border=\"0\" title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (trangthai == '05') {
                trangthai_path = " <img src=\"" + path + "styles/images/send-success.jpg\" " + "border=\"0\" title=\"&#272;&#227; x&#7917; l&#253;\"/>";
            }
            var chucdanh = objectQT.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataTGGD('" + objectQT.id + "','row_dts_" + i + "');\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataTGGD('" + objectQT.id + "','row_dts_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_qt_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_dts_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectQT.id + "\" onkeydown=\"arrowUpDownKDTG(event)\" readonly=\"true\"/></td>";
            strTableData += "<td  align=\"center\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyQT();
        //ButtonPhanQuyenTuDo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        defaultRowSelected();
    }
    tableHighlightRow();
}
//fill data table
function fillDataTableDTSDen(data, path) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    jQuery.each(data, function (i, objectQT) {
        var trangthai = objectQT.trang_thai;
        var trangthai_path = "";
        if (trangthai != null) {
            if (trangthai == '00') {
                trangthai_path = "<img src=\"" + path + "styles/images/edit.gif\" " + "border=\"0\" title=\"Ch&#7901; TTV ho&#224;n thi&#7879;n\"/>";
            }
            else if (trangthai == '01') {
                trangthai_path = "<img src=\"" + path + "styles/images/wait.jpeg\" " + "border=\"0\" title=\"Ch&#7901; KTT duy&#7879;t\"/>";
            }
            else if (trangthai == '02') {
                trangthai_path = " <img src=\"" + path + "styles/images/return.jpeg\" " + "border=\"0\" title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }
            else if (trangthai == '03') {
                trangthai_path = " <img src=\"" + path + "styles/images/active.gif\" " + "border=\"0\" title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            var chucdanh = objectQT.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_den_" + i + "\" ondblclick=\"rowDTSDenSelectedFocus('row_dts_den_" + i + "');\" onclick=\"rowDTSDenSelectedFocus('row_dts_den_" + i + "');fillDataTGGD('" + objectQT.id + "','row_dts_den_" + i + "');\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_den_" + i + "\" ondblclick=\"rowDTSDenSelectedFocus('row_dts_den_" + i + "');\" onclick=\"rowDTSDenSelectedFocus('row_dts_den_" + i + "');fillDataTGGD('" + objectQT.id + "','row_dts_den_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_qt_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_dts_den_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectQT.id + "\" onkeydown=\"arrowUpDownDTSDen(event)\" readonly=\"true\"/></td>";
            strTableData += "<td  align=\"center\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyQT();
        //ButtonPhanQuyenTuDo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        defaultRowSelected();
    }
    tableHighlightRow();
}

function tableHighlightRow() {
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

function arrowUpDownKDTG(e) {
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
            jQuery('#' + idTRSelected).click();
            rowSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }

}

function arrowUpDownDTSDen(e) {
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
                jQuery("#row_dts_den_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_den_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_den_" + input_id).focus();

            }
            break;
        case arrow.down:
            total_row = (jQuery("input[name='row_item']").length);
            input_id = parseInt(input_id_value);
            if (input_id <= parseInt(total_row) - 1) {
                //remove class highlight tr select
                if (input_id < parseInt(total_row) - 1)
                    input_id = parseInt(input_id) + 1;
                jQuery("#col_dts_den_" + input_id).focus();
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');
                //add class highligh tr previous
                jQuery("#row_dts_den_" + input_id).addClass('ui-state-highlight');
                jQuery("#col_dts_den_" + input_id).addClass('ui-state-highlight');
            }
            break;
        case arrow.enter:
            if (jQuery(document.activeElement).attr('class') == 'ui-state-highlight') {
                jQuery('#row_dts_den_' + input_id_value).click();
            }
            break;
        case arrow.esc:
            var idTRSelected = jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
            jQuery('#' + idTRSelected).click();
            rowDTSDenSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }

}

function roleUser(pQuyen, trangthai, typeDTS) {
    var typeUser;
    if (pQuyen != null || pQuyen != '') {
        // set quyen
        // truong hop 1 : TTV
        // hien thi button ttv
        if (pQuyen.indexOf("TTV") !=  - 1) {
            typeUser = 0;
            buttonWithTrangThaiKDTG(typeUser, trangthai, typeDTS);
        }
//        else if (pQuyen.indexOf("KTT") !=  - 1) {
//            //truong hop 2 : User la  KTT 
//            typeUser = 1;
//            buttonWithTrangThaiKDTG(typeUser, trangthai, typeDTS);
//        }
    }
}

function buttonForTTV() {
    jQuery("#tao").show();
    jQuery("#traloi").hide();
//    jQuery("#sua").hide();
    jQuery("#ghi").hide();
//    jQuery("#huy").hide();
//    jQuery("#duyet").hide();
//    jQuery("#dayLai").hide();
    jQuery("#xacnhan").hide();
}

function defaultStateFormKDTG() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        "readonly" : true
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        "disabled" : true
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        "class" : "fieldTextCode"
    });
    jQuery("#ma_ttv_nhan").attr( {
        "readonly" : true
    });
    jQuery("#ngay_nhan").attr( {
        "readonly" : true
    });
    jQuery("#ma_ktt").attr( {
        "readonly" : true
    });
    jQuery("#ngay_duyet").attr( {
        "readonly" : true
    });
    jQuery("#noidung").attr( {
        "readonly" : true
    });
    jQuery("#noidung").attr( {
        "class" : "fieldTextAreaRO"
    });
    jQuery("#thoi_gian_keo_dai").attr( {
        "readonly" : true
    });
    jQuery("#gio_keo_dai").attr( {
        "readonly" : true
    });
    jQuery("#phut_keo_dai").attr( {
        "readonly" : true
    });
    jQuery("#thoi_gian_keo_dai").attr( {
        "class" : "fieldTextCode"
    });
    jQuery("#gio_keo_dai").attr( {
        "class" : "fieldTextCode"
    });
    jQuery("#phut_keo_dai").attr( {
        "class" : "fieldTextCode"
    });
    jQuery("#noidung_traloiY").attr( {
        "disabled" : true
    });
    jQuery("#noidung_traloiN").attr( {
        "disabled" : true
    });
    jQuery("#lydo_ktt_day_lai").attr( {
        "readonly" : true
    });
    jQuery("#lydo_ktt_day_lai").attr( {
        "class" : "fieldTextAreaRO"
    });
}

function buttonWithTrangThaiKDTG(type, trangthai, loai_dts) {
    // TTV
    /*
     * Trang thai
     * 00 - cho xu ly
     * 04 - HUY
     * 01 - DAY LAI
     * 02 - CHO KTT DUYET
     * 03 - da duyet     
     * 05 - da xu ly
     * */
    defaultButton();
    defaultStateFormKDTG();

    if (type == 0) {
        buttonForTTV();
        if (trangthai == '00') {
            // trang thai cho xu ly hien thi tao dien,tra loi
            if (loai_dts != ''){
                jQuery("#xacnhan").show();
            }
            else {
                jQuery("#traloi").show();
                }
        }
//        if (trangthai == '01') {
//            // trang thai cho xu ly hien thi tao dien,tra loi
//            jQuery("#sua").show();
//        }
    }
    // ktt
//    else {
//        if (trangthai == 02) {
//            jQuery("#dayLai").show();
//            jQuery("#duyet").show();
//            jQuery("#lydo_ktt_day_lai").attr( {
//                "class" : "fieldTextArea"
//            });
//            jQuery("#lydo_ktt_day_lai").removeAttr("readonly").focus();
//        }
//    }
}