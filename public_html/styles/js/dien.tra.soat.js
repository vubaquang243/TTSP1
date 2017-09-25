//************************************ RESET FROM **********************************
function resetFormDTS() {
    jQuery("#ltt_id").val("");
    jQuery("#ngay_thanh_toan").val("");
    jQuery("#ma_don_vi_tra_soat").val("");
    jQuery("#ma_don_vi_nhan_tra_soat").val("");
    jQuery("#ttv_nhan").val("");
    jQuery("#ma_nsd").val("");
    jQuery("#ma_ktt").val("");
    jQuery("#ngay_nhan").val("");
    jQuery("#noi_dung").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#thong_tin_khac").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#lydo_ktt_day_lai").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#ngay_ks_nh").val("");
    jQuery("#ngay_duyet").val("");
    jQuery("#nguoi_ks_nh").val("");
    jQuery("#mo_ta_trang_thai").html("");
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#ten_don_vi_tra_soat").val("");
    jQuery("#ten_don_vi_nhan_tra_soat").val("");
    jQuery("#nhkb_nhan_ltt").val("");
}
//************************************ DAY DU LIEU VAO FROM **********************************  
function fillDetailToFormDTS(data, tr_id_selected) {
    jQuery("#id").val(data.id);
    jQuery("#ltt_id").val(data.ltt_id);
    jQuery("#ngay_thanh_toan").val(data.ngay_thanh_toan);
    jQuery("#nhkb_cbtn_huyen").val(data.nhkb_cbtn_huyen);
    jQuery("#nhkb_nhan").val(data.nhkb_nhan);
    jQuery("#ma_ktt").val(data.ma_ktt);
    jQuery("#ngay_duyet").val(data.ngay_duyet);
    jQuery("#ma_nsd").val(data.ma_ttv_nhan);
    jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
    jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
    jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
    jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
    jQuery("#nhkb_nhan").val(data.nhkb_nhan);
    jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
    jQuery("#ttv_nhan").val(data.ttv_nhan);
    jQuery("#ngay_nhan").val(data.ngay_nhan);
    jQuery("#noi_dung").val(data.noidung);
    jQuery("#thong_tin_khac").val(data.thong_tin_khac);
    jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
    jQuery("#ngay_ks_nh").val(data.ngay_ks_nh);
    jQuery("#nguoi_ks_nh").val(data.nguoi_ks_nh);
    jQuery("#mo_ta_trang_thai").html(data.mo_ta_trang_thai);
    jQuery("#rowSelected").val(tr_id_selected);
    jQuery("#trang_thai").val(data.trang_thai);
    // neu trang thai la 19 - gui ngan hang khong thanh cong thi hien thong tin loi
    if (data.trang_thai != undefined && data.trang_thai != null) {
        if (data.trang_thai == '19') {
            jQuery("#error_desc").val(data.error_code + " - " + data.error_desc)
        }
        else {
            jQuery("#error_desc").val("");
        }
    }
    // end
    jQuery("#nhkb_nhan_ltt").val(data.nhkb_nhan_ltt);
    jQuery("#noi_dung_ky").val(data.noi_dung_ky);

}
//********************** GET DATA CHO form detail ***********************************
function fillDataDTS(id, tr_id, chucdanh, enableFocus) {
    resetFormDTS();
    jQuery.ajax( {
        type : "POST", url : "checkstatebudts.do", async : false, data :  {
            "id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.ERROR != undefined) {
                        jQuery("#dialog-message").html(data.ERROR);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.ERROR == undefined) {
                        enableButtonDTS(chucdanh, data.trang_thai);
                        fillDetailToFormDTS(data, tr_id);
                        if (chucdanh.indexOf('KTT') >= 0) {
                            if (data.trang_thai == '02' && enableFocus) {
                                jQuery("#lydo_ktt_day_lai").attr( {
                                    disabled : ""
                                });
                                jQuery("#lydo_ktt_day_lai").focus();
                            }
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.messge);
            jQuery("#dialog-message").dialog("open");
        }

    });

}

//********************** CLICK NUT btn_sua ***********************************  
function btnSuaClick() {
    jQuery("#noi_dung").removeAttr("disabled");
    jQuery("#noi_dung").focus();
    jQuery("#thong_tin_khac").removeAttr("disabled");
    jQuery("#btn_sua").hide();
    jQuery("#btn_ghi").show();
    jQuery("#btn_duyet").hide();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_huy").hide();
    jQuery("#btn_xemLTT").hide();
    jQuery("#btn_timKiem").hide();
}
//********************** CLICK NUT btn_DAYLAI *********************************** 
function btnDayLaiClick() {
    jQuery("#lydo_ktt_day_lai").removeAttr("disabled");
    jQuery("#lydo_ktt_day_lai").focus();
    jQuery("#btn_ghi").show();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_duyet").hide();
    jQuery("#tra_cuu_dts").hide();
    jQuery("#btn_timKiem").hide();
    jQuery("#btn_xemLTT").hide();

}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * 
   */
function disableButton() {
    jQuery("#btn_duyet").hide();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_ghi").hide();
    jQuery("#btn_sua").hide();
    jQuery("#btn_huy").hide();
    jQuery("#btn_xemLTT").hide();
    jQuery("#btn_traLoi").hide();
    jQuery("#btn_timKiem").show();
}

function disableButtonForFind() {
    jQuery("#btn_duyet").hide();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_ghi").hide();
    jQuery("#btn_sua").hide();
    jQuery("#btn_huy").hide();
    jQuery("#btn_xemLTT").show();
    jQuery("#btn_timKiem").hide();
    jQuery("#btn_Refresh").hide();
}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * @param chucdanh
   * @param trang_thai
   */
function enableButtonDTS(chucdanh, trang_thai) {
    jQuery("#btn_ghi").hide();
    jQuery("#btn_timKiem").show();
    if (chucdanh == null || chucdanh == '') {
        disableButton();
    }
    else if (chucdanh != null && chucdanh != 'null') {
        if (chucdanh.indexOf("TTV") >= 0 && chucdanh.indexOf("KTT") ==  - 1) {
            //span sua,ghi,huy,timkiem,thoat
            if (trang_thai == '01') {
                jQuery("#btn_sua").show();
                jQuery("#btn_huy").show();
                jQuery("#btn_duyet").hide();
                jQuery("#btn_dayLai").hide();
            }
            else if (trang_thai == '02') {
                jQuery("#btn_sua").hide();
                jQuery("#btn_huy").hide();
                jQuery("#btn_duyet").hide();
                jQuery("#btn_dayLai").hide();
            }
            else {
                disableButton();
            }
        }
        else if (chucdanh.indexOf("KTT") >= 0 && chucdanh.indexOf("TTV") ==  - 1) {
            if (trang_thai == '02') {
                jQuery("#btn_duyet").show();
                jQuery("#btn_dayLai").show();
                jQuery("#btn_sua").hide();
                jQuery("#btn_huy").hide();
            }
            else {
                disableButton();
            }
        }
        else if (chucdanh.indexOf("KTT") >= 0 && chucdanh.indexOf("TTV") >= 0) {
            if (trang_thai == '01') {
                jQuery("#btn_sua").show();
                jQuery("#btn_huy").show();
                jQuery("#btn_duyet").hide();
                jQuery("#btn_dayLai").hide();
            }
            else if (trang_thai == '02') {
                jQuery("#btn_duyet").show();
                jQuery("#btn_dayLai").show();
                jQuery("#btn_sua").hide();
                jQuery("#btn_huy").hide();
            }
            else {
                disableButton();
            }
        }
        else {
            disableButton();
        }
        jQuery("#btn_xemLTT").show();
    }
}
//********************** Tim kiem dien tra soat***********************************   
function findDTS(contextRoot, chucdanh) {
    var dialogId = jQuery("#dialog-form ~ .ui-dialog-buttonpane button"), maTTV = jQuery("#ma_ttvnhan").val(), nh_kb_nhan = jQuery("#nh_kb_nhan").val(), so_dts = jQuery("#so_dts").val(), so_lenh_tt = jQuery("#so_lenh_tt").val();
    jQuery(dialogId[0]).button("disable");
    jQuery.ajax( {
        type : "POST", url : "dtsoatlttdiView.do", data :  {
            "ma_ttv_nhan" : maTTV, "ma_don_vi_nhan_tra_soat" : nh_kb_nhan, "dts_id" : so_dts, "ltt_id" : so_lenh_tt, "action" : 'FIND', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.ERROR != undefined) {
                        jQuery("#dialog-message").html(data.ERROR);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.ERROR == undefined) {
                        fillDataTableDST(data, contextRoot, chucdanh);
                        if (data != null && data != '') {
                            defaultRowSelected(chucdanh);
                        }
                        else {
                            resetFormDTS();
                        }
                    }
                }
                jQuery(dialogId[0]).button("enable");
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}

//fill data table
function fillDataTableDST(data, contextRoot, chucdanh) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    if (data != null && data != '')
        jQuery.each(data, function (i, objectDTS) {
            strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTS('" + objectDTS.id + "','row_dts_" + i + "','" + chucdanh + "',true);\">";
            strTableData += "<td id=\"td_dts_" + i + "\" width=\"110px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id=\"" + i + "\" type=\"text\" style=\"border:0px;font-size:10px;float:left;width:110px;\" value=\"" + objectDTS.id + "\" onkeydown=\"arrowUpDown(event)\" readonly=\"true\"/></td><td align=\"center\">";
            if (objectDTS.trang_thai == '01') {
                strTableData += "<img src=" + contextRoot + "/styles/images/return.jpeg title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }
            else if (objectDTS.trang_thai == '02') {
                strTableData += "<img src=" + contextRoot + "/styles/images/wait.jpeg title=\"Ch&#7901; KTT duy&#7879;t\"/>";
            }
            else if (objectDTS.trang_thai == '03') {
                strTableData += "<img src=" + contextRoot + "/styles/images/active.gif title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (objectDTS.trang_thai == '04') {
                strTableData += "<img src=" + contextRoot + "/styles/images/delete1.gif title=\"&#272;&#227; h&#7911;y\"/>";
            }
            else if (objectDTS.trang_thai == '18') {
                strTableData += "<img src=" + contextRoot + "/styles/images/send-success.jpg title=\"G&#7917;i NH th&#224;nh c&#244;ng\"/>";
            }
            else if (objectDTS.trang_thai == '19') {
                strTableData += "<img src=" + contextRoot + "/styles/images/sended-false.jpg title=\"G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng\"/>";
            }

            strTableData += "</td></tr>";
        });
    else {
        strTableData += "<tr>";
        strTableData += "<td colspan=\"5\" align=\"center\">";
        strTableData += "<span style=\"color:red;\">Kh&#244;ng c&#243; b&#7843;n ghi</span>";
        strTableData += " </td>";
        strTableData += "</tr>";
    }
    jQuery("#tong_ban_ghi").html('T&#7893;ng s&#7889;: ' + data.length + ' &#273;i&#7879;n tra so&#225;t');
    jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
    tableHighlightRow();

}

/**
 * Dieu kien la chon 1 dien tra soat can su ly ben master
 */
function selectedRowBeforeClickButton() {
    var rSelected = jQuery("#rowSelected").val();
    if (rSelected == null || rSelected == '') {
        jQuery("#dialog-message").html('B&#7841;n ph&#7843;i ch&#7885;n m&#7897;t S&#7889; &#273;i&#7879;n tra so&#225;t trong danh s&#225;ch s&#7889; &#273;i&#7879;n tra so&#225;t.');
        jQuery("#dialog-message").dialog("open");
        return false;
    }
    return true;
}

/**
 * Refresh danh sach so dien tra soat
 */
function refreshGrid(id, contextRoot, chucdanh, action) {
    if (id == 'null')
        id = '';
    jQuery.ajax( {
        type : "POST", url : "dtsoatlttdiView.do", data :  {
            "action" : 'REFRESH', "id" : id, "async" : false, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.ERROR != undefined) {
                        jQuery("#dialog-message").html(data.ERROR);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.ERROR == undefined) {
                        fillDataTableDST(data, contextRoot, chucdanh);
                        disableButton();
                        defaultRowSelected(chucdanh);
                        if (action != null && action != 'null') {
                            jQuery("#btn_xemLTT").hide();
                            jQuery("#btn_timKiem").hide();
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
    if (id == null || id == '') {
        resetFormDTS();
    }

}

function nextElementFocus(e) {
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
