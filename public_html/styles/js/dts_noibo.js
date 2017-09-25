function arrowUpDownDTSNBoDi(e) {
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

    function rowSelectedFocusNBoDi(rowId) {
        classRowHighLight(rowId);
        jQuery('#' + rowId).find('input').focus();

    }
function limitCharsDTSNoiBo(id, maxlength) {

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
//focus vao truong day lai 
function focusDayLaiDTSNoiBo(chucdanh, trangthai) {
    // neu la KTT va trang thai la cho duyet    
    if (chucdanh == 'KTT' && trangthai.value == '02') {
        // thi moi duoc su dung phim esc de focus len lydo_ktt_day_lai
        if (event.keyCode == 13) {
            jQuery("#lydo_ktt_day_lai").removeAttr("readonly");
            jQuery("#lydo_ktt_day_lai").focus();
        }
    }
}

function focusDayLaiDTSNoiBoMaster(check) {
    if (check) {
        if (event.keyCode == 27) {
            defaultRowSelectedTuDo(check);
        }
    }
}
//************************************ RESET FROM **********************************
function resetFormDTSNoiBo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    });
//    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//        disabled : "disabled"
//    });
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    });
    jQuery("#ma_ttv_nhan").attr( {
        readonly : "true"
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
        readonly : "true"
    });
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ma_ktt").attr( {
        readonly : "true"
    });
}

function resetFormThemDTSNoiBo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ktt").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    }).val('');
//    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//        disabled : "disabled"
//    }).focus().val();
    jQuery("#ma_don_vi_nhan_tra_soat").removeClass("fieldTextCode").addClass("fieldText");
    //    document.getElementById("ma_don_vi_nhan_tra_soat").options[0].selected = true;
    jQuery("#noidung").removeAttr("readonly").val('').focus();
    jQuery("#noidung").attr( {
        tabindex:"102"
    });
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true"
    }).val('');

    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "readonly"
    }).val('');
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "readonly"
    }).val('');
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#data-grid tr").removeAttr("onclick");
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "readonly"
    });
}

function resetFormDayLaiDTSNoiBo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    });
//    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//        readonly : "true"
//    });
    jQuery("#ttv_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").keyup(function () {
        this.value = this.value.replace(/ [ ^ 0 - 9\ .] /g, '');
    });

    jQuery("#noidung").removeAttr("disabled").val('');
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : ""
    }).val('').focus();

    jQuery("#mo_ta_trang_thai").val("");
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#ten_don_vi_tra_soat").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    }).val('');

}

function removeFormDTSNoiBoDi() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        disabled : "disabled"
    }).val('');
//    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//        disabled : "disabled"
//    }).val('');
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
        readonly : "readonly"
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

function setStyleRowEmptyDTSNoiBoDi() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}

function selectedTopRowNoiBoDi(id, col, chucdanh, bCheck) {
    // viet 1 ham select dau tien giong ham nay
    if (chucdanh != null || chucdanh != '') {
        roleUserDTSNoiBoDi(chucdanh, '');
    }
    if (bCheck == true) {
        ButtonPhanQuyenGuestDTSNoiBo();
        defaultRowSelectedNBo(true);
        resetFormDTSNoiBo();
    }
    else {
        if (id == null || id == '' || id == 'undefined') {
            setStyleRowEmptyDTSNoiBoDi();
            removeFormDTSNoiBoDi();
        }
        else {
            defaultRowSelectedNBo(bCheck);
            resetFormDTSNoiBo();
        }
    }

}

function defaultRowSelectedNBo(bCheck) {
    var row_default = "row_dts_0",
        input_default = jQuery('#col_dts_0');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataDTSNoiBo(input_default.val(), row_default, bCheck);
        rowSelectedFocus(row_default);
    }
}
//********************** Find data to form add *****************************
function fillDataThemDTSNoiBo() {
    jQuery.ajax( {
        type : "POST", url : "DTSoatNoiBoAdd.do", data :  {
            "action" : 'add', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.loi_add != null) {
                            if (data.loi_add == "error") {
                                jQuery("#dialog-message-check").html('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra. \n com.seatech.ttsp.dts.action.Add(). D&#7919; li&#7879;u b&#7843;n ghi kh&#244;ng h&#7907;p l&#7879; !');
                                jQuery("#dialog-message-check").dialog("open");
                            }
                        }
                        jQuery("#row_selectedId").val(data.id_sdts);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#ten_don_vi_tra_soat").val(data.nhkb_chuyen_ten);
                        jQuery("#ma_don_vi_tra_soat").val(data.nhkb_chuyen_ma);
                        jQuery("#ttv_nhan").val(data.ten_ttv_nhan);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        // kiem tra ngan hang kho bac chuyen la TTTT hay kho bac
                        if (data.nhkb_chuyen == '1638') {
                            if (data.nhkb_nhan == null || data.nhkb_nhan == '') {
                                jQuery("#ma_don_vi_nhan_tra_soat").val('').attr( {
                                    disabled : ''
                                }).focus();
                            }
                        }
                        else {
//                            jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//                                disabled : 'disabled'
//                            }).focus();
                            jQuery('#ma_don_vi_nhan_tra_soat').val(data.nhkb_nhan_code);
                            jQuery("#ten_don_vi_nhan_tra_soat").val(data.nhkb_nhan_ten);
                            jQuery("#noidung").focus();
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert('�?ã có lỗi xảy ra' + textstatus.toString());
        }
    });
}

function buttonThemDTSNBo() {
    // Button
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").show();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();

    //jQuery("#data-grid tr").attr("disabled", true);
}

function fillDataDTSNoiBo(id, tr_id, bCheck) {
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatNoiBoView.do", data :  {
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
                        if (data.exception_message != null) {
                            if (data.exception_message == "uncorrect") {
                                jQuery("#dialog-message-check").html('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra. \n com.seatech.ttsp.dts.action.View(). D&#7919; li&#7879;u b&#7843;n ghi kh&#244;ng h&#7907;p l&#7879; !');
                                jQuery("#dialog-message-check").dialog("open");
                            }
                        }
                        jQuery("#id").val(data.id);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                        jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
                        jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                        jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
                        jQuery("#ttv_nhan").val(data.ten_ttv_nhan);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#ktt_duyet").val(data.ten_ktt_duyet);
                        jQuery("#noidung").val(data.noidung);
                        jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                        jQuery("#ngay_duyet").val(data.ngay_duyet);
                        jQuery("#mo_ta_trang_thai").val(data.mo_ta_trang_thai);
                        jQuery("#rowSelected").val(tr_id);
                        jQuery("#trang_thai").val(data.trang_thai);
                        if (!bCheck) {
                            roleUserDTSNoiBoDi(data.chuc_danh, data.trang_thai);
                        }
                        else {
                            disabledAllButtonDTS();
                        }
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ma_ktt").val(data.ma_ktt);
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

//********************** HIGHLIGHT ROW SELECT OR ONMOUSE OVER AN OUT ***********************************     
/**
   * highlignt so dien tra soat
   */
function setStyleRowAdd() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
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
        readonly : 'readonly'
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : 'disabled'
    });
}

function tableHighlightRow_DTSNBoDI() {
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
//********************** CLICK NUT SUA ***********************************  
function buttonSuaDTSNBo() {
    if (jQuery("#ma_don_vi_nhan_tra_soat").val() == '01701001') {    
        jQuery("#ma_don_vi_nhan_tra_soat").attr( {
            disabled : "disabled"
        });
        jQuery("#noidung").removeAttr("readonly").focus();
    }
    else {
        jQuery("#ma_don_vi_nhan_tra_soat").attr( {
            disabled : ""
        }).focus();
        jQuery("#noidung").removeAttr("readonly");
    }
    jQuery("#ttv_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    });
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "readonly"
    });

    jQuery("#ktt_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ten_don_vi_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#mo_ta_trang_thai").attr( {
        disabled : "disabled"
    });
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").show();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#data-grid tr").removeAttr("onclick");
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
}

function buttonKTTUpdateDTSTD() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").show();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : ""
    }).val('').focus();
    jQuery("#data-grid tr").removeAttr("onclick");
}

//********************** CLICK NUT GHI ***********************************  
function btnGhiClick() {
    jQuery("#noidung").removeAttr("readonly");
    jQuery("#noidung").focus();
    jQuery("#btnSua").attr( {
        disabled : 'disabled'
    });
    jQuery("#btnGhi").attr( {
        disabled : ''
    });
    jQuery("#btnDuyet").attr( {
        disabled : 'disabled'
    });
    jQuery("#btnDayLai").attr( {
        disabled : 'disabled'
    });
    jQuery("#btnHuy").attr( {
        disabled : 'disabled'
    });
    jQuery("#btnTim").attr( {
        disabled : 'disabled'
    });

}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * 
   */
function roleUserDTSNoiBoDi(pQuyen, trangthai) {
    if (pQuyen != null || pQuyen != '') {
        
    var typeUser = "0";
      if (pQuyen == "KTT" || pQuyen == "GD"||pQuyen == "CBPT-TTTT" ) {
          typeUser = '2';
          buttonWithTrangThaiNoiBo(typeUser, trangthai);
      }
      
      if ( pQuyen == "TTV"||pQuyen == "CB-TTTT") {
          typeUser = '3';
          buttonWithTrangThaiNoiBo(typeUser, trangthai);
      }
    }
}
function ButtonPhanQuyenTKiemNBo() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
}
function ButtonPhanQuyenKTT() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").show();
    jQuery("#dayLai").show();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : ""
    }).val('');
}

function ButtonPhanQuyenTTV() {
    jQuery("#them").show();
    jQuery("#sua").show();
    jQuery("#ghi").hide();
    jQuery("#huy").show();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true"
    }).val('');
}

function ButtonPhanQuyen() {
    jQuery("#them").show();
    jQuery("#sua").show();
    jQuery("#ghi").hide();
    jQuery("#huy").show();
    jQuery("#timKiem").show();
    jQuery("#duyet").show();
    jQuery("#dayLai").show();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : ""
    }).val('');
}

function ButtonPhanQuyenGuestDTSNoiBo() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true"
    });
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#data-grid tr").removeAttr("onclick");
}
//********************** Thuc hien them moi dts*********************************** 
function addExcFormDTSNoiBo() {
    var MaTTV = jQuery("#ttv_nhan").val();
    var NH_KB_chuyen_Code = jQuery("#ma_don_vi_tra_soat").val();
    var NH_KB_nhan_Code = jQuery("#ma_don_vi_nhan_tra_soat").val();
    var noidung = jQuery("#noidung").val();
    jQuery.ajax( {
        type : "POST", url : "DTSoatNoiBoAddExc.do", data :  {
            ttv_nhan : MaTTV, ma_don_vi_tra_soat : NH_KB_chuyen_Code, ma_don_vi_nhan_tra_soat : NH_KB_nhan_Code, noidung : noidung, "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.sohieu_kb == 'NH') {
                            jQuery("#dialog-message-check").html('&#272;&#417;n v&#7883; nh&#7853;n tra so&#225;t ph&#7843;i l&#224; kho b&#7841;c !');
                            jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
                            buttonThemDTSNBo();
                            jQuery("#dialog-message-check").dialog("open");
                        }
                        if (data.sohieu_kb == 'KB') {
                            jQuery("#dialog-message").html('Th&#234;m m&#7899;i &#273;i&#7879;n tra so&#225;t : <span style="font-weight:bold;color:red;">' + data.id_dts + '</span> th&#224;nh c&#244;ng !');
                            jQuery("#ma_don_vi_nhan_tra_soat").attr( {
                                disabled : "disabled"
                            });
                            jQuery("#noidung").attr( {
                                readonly : "readonly"
                            })
                            jQuery("#dialog-message").dialog("open");
                        }
                        if (data.ID_DV_KBNhan_ERR != null || data.ID_DV_KBNhan_ERR == 'failure') {
                            jQuery("#dialog-message-check").html('M&#227; &#273;&#417;n v&#7883; nh&#7853;n tra so&#225;t kh&#244;ng t&#7891;n t&#7841;i !');
                            jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
                            buttonThemDTSNBo();
                            jQuery("#dialog-message-check").dialog("open");
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}
//********************** Tim kiem dien tra soat***********************************   
function findDTSNoiBoDi() {
    removeFormDTSNoiBoDi();
    var maTTV = jQuery("#ma_ttv_tk").val(), nh_kb_nhan = jQuery("#nh_kb_nhan_tk").val(), so_dts = jQuery("#so_dts_tk").val();

    jQuery.ajax( {
        type : "POST",
        url : "DTSoatNoiBo.do",
        async:false,
        data :  {
            "ttv_nhan" : maTTV, "nhkb_nhan" : nh_kb_nhan, "id" : so_dts, "action" : 'FIND', "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
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
                        fillDataTableDSTNoiBo(data, path);
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
function fillDataTableDSTNoiBo(data, path) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    jQuery.each(data, function (i, objectDTS) {
        var trangthai = objectDTS.trang_thai;
        var trangthai_path = "";
        if (trangthai != null) {
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
            //            strTableData = strTableData + "<tr class=\"ui-widget-content jqgrow ui-row-ltr\"" + "id=\"row_dts_" + i + "\" onclick=\"" + "fillDataDTSNoiBo('" + objectDTS.id + "','row_dts_" + i + "');\">" + "" + "<td width=\"68%;\" align=\"center\">" + objectDTS.id + "</td>" + "" + "<td align=\"center\">" + trangthai_path + "</td></tr>";
            var chucdanh = objectDTS.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" onkeydown=\"focusDayLaiDTSNoiBo('" + objectDTS.chuc_danh + "',trang_thai);\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSNoiBo('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSNoiBo('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_dts_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_dts_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectDTS.id + "\" onkeydown=\"arrowUpDownDTSNBoDi(event)\" readonly=\"true\"/></td>";
            strTableData += "<td  align=\"center\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyDTSNoiBoDi();
        ButtonPhanQuyenTKiemNBo();
//        ButtonPhanQuyenGuestDTSNoiBo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        selectedTopRowNoiBoDi(jQuery("#col_dts_0").val(), "row_dts_0", '', false);
    }
    tableHighlightRow_DTSNBoDI();

}

/**
 * CHON BAN GHI BEN SO DIEN TRA SOAT
 */
function selectedRowAfterClickButton() {
    var rSelected = jQuery("#rowSelected").val();
}

function selectedRowBeforeClickButtonNoiBo() {
    var rSelected = jQuery("#rowSelected").val();
    if (rSelected == null || rSelected == '') {
        jQuery("#dialog-message-check").html('B&#7841;n ph&#7843;i ch&#7885;n m&#7897;t S&#7889; &#273;i&#7879;n tra so&#225;t trong danh s&#225;ch s&#7889; &#273;i&#7879;n tra so&#225;t.');
        jQuery("#dialog-message-check").dialog("open");
        return false;
    }
    return true;
}

/**
 * RESET BUTTON & FIELD THEM MOI DTS
 */

/**
 * Refresh danh sach so dien tra soat
 */

function refreshGridDTSNoiBo() {
    jQuery.ajax( {
        type : "POST", url : "DTSoatNoiBo.do", data :  {
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
                        fillDataTableDSTNoiBo(data, path);
                    }
                }

            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function disabledAllButtonDTS() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : 'true'
    });
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#data-grid tr").attr("disabled", true);
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "true"
    });
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
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
        readonly : 'readonly'
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
// Exit 
function exitViewDTSNoiBo() {
    document.forms[0].action = "thoatView.do";
    document.forms[0].submit();
}

function returnViewDTSNoiBo() {
    document.forms[0].action = "DTSoatNoiBo.do";
    document.forms[0].submit();
}

function initStateButtonNoiBo() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true"
    });
    //    jQuery("#refreshButton *").attr( {
    //        disabled : 'disabled'
    //    });
}

function buttonWithTrangThaiNoiBo(typeUser, trangthai) {
    initStateButtonNoiBo();

    // quyen ktt
     if (typeUser == '2') {
        if (trangthai == '' || trangthai == null) {
            initStateButtonNoiBo();
        }
        else if (trangthai == '00') {
            //        jQuery("#huy").hide();
        }
        else if (trangthai == '01') {
            // cho day lai
            jQuery("#lydo_ktt_day_lai").attr( {
                readonly : "true"
            });
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#duyet").show();
            jQuery("#dayLai").show();
            jQuery("#lydo_ktt_day_lai").attr( {
                readonly : ""
            }).val('');
        }
    }
    // quyen ttv
    
    else if (typeUser == '3') {
    
        if (trangthai == '' || trangthai == null) {
            jQuery("#them").show();
        }
        else if (trangthai == '00') {
            //        jQuery("#huy").hide();
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#them").show();
        }
        else if (trangthai == '01') {
            //  KTT day lai
            jQuery("#sua").show();
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