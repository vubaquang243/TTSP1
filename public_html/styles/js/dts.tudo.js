//************************************ LIMIT CHARS ********************************** 

 function getTenNganHang_KB(ma,ten, id){
	 var shkb =document.getElementById(ma).value;
   v_ten = ten;   
   v_id = id;
	 if (shkb.length<8 || shkb.length>8){
		 document.getElementById(ten).value = "";
     document.getElementById(id).value = "";
	 }else{
	 var objJSON = {
        "ma" : document.getElementById(ma).value};    
    var strJSON = encodeURIComponent(JSON.stringify(objJSON));        
    new Ajax.Request("loadDVTSAction.do",
     {
       method: "post",
       async: false,
       parameters: "strJSON=" + strJSON,
       onSuccess: onSuccessNH,

       onError: onError,
       onLoading: onLoading
     });
	 }
}

function limitChars(id, maxlength) {

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

function resetFormDTSTuDo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    });
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

function removeFormDTSTuDo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
//    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
//        disabled : "disabled"
//    }).val('');
    //    setNewValueForNHKB(' ');
    //    jQuery("#ma_don_vi_nhan_tra_soat option[value=' ']").attr('selected', 'selected');
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ttv_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_duyet").attr( {
        readonly : "true"
    }).val('');
    jQuery("#noidung").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ktt").attr( {
        readonly : "true"
    }).val('');
    jQuery("#mo_ta_trang_thai").attr( {
        readonly : "true"
    }).val('');

}

function resetFormThemDTSTuDo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_nhan").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ma_ktt").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ngay_duyet").attr( {
        readonly : "true"
    }).val('');

//    jQuery("#ma_don_vi_nhan_tra_soat").removeAttr("disabled").removeAttr("readonly").focus();
//    document.getElementById("ma_don_vi_nhan_tra_soat").options[1].selected = true;

    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });

    jQuery("#noidung").val('').attr( {
        readonly : ""
    }).focus();

    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true", 'class' : 'fieldTextAreaRO'
    }).val('');

    jQuery("#mo_ta_trang_thai").val('').attr( {
        readonly : "true"
    });

    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');

    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');

    
    jQuery("#ma_don_vi_nhan_tra_soat").removeAttr("disabled").val('');

    jQuery("#data-grid tr").removeAttr("onclick");
}

function resetFormSuaDTSTuDo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
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
    jQuery("#ma_ktt").attr( {
        readonly : "true"
    });
    jQuery("#ngay_duyet").attr( {
        readonly : "true"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").removeAttr('disabled').focus();
//    document.getElementById("ma_don_vi_nhan_tra_soat").options[1].selected = true;
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#noidung").attr( {
        readonly : ""
    }).focus();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "true"
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
    jQuery("#data-grid tr").removeAttr("onclick");
}

function resetFormDayLaiDTSTuDo() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : "disabled"
    });
    jQuery("#ttv_nhan").attr( {
        readonly : "true"
    });
    jQuery("#ngay_nhan").attr( {
        disabled : "disabled"
    }).val('');
    jQuery("#ktt_duyet").attr( {
        readonly : "true"
    });
    jQuery("#ngay_duyet").attr( {
        disabled : "disabled"
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {

    }).keyup(function () {
        this.value = this.value.replace(/[^0-9\.]/g, '');
    });

    jQuery("#noidung").removeAttr("disabled").val('');
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : ""
    }).val('').focus();

    jQuery("#mo_ta_trang_thai").val("");
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#ten_don_vi_tra_soat").attr( {
        readonly : "true"
    }).val('');
    jQuery("#ten_don_vi_nhan_tra_soat").attr( {
        readonly : "true"
    }).val('');

}

//********************** Find data to form add *****************************
function fillDataThemDTS() {
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatTuDoAdd.do", data :  {
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
                        var exception_message = data.exception_message;
                        if (exception_message != null && exception_message != '' && exception_message != 'null') {
                            alert("Load thêm DTS không thành công! \n" + exception_message);
                        }
                        else {                     
                            jQuery("#row_selectedId").val(data.id_sdts);
                            var dtsJsonObj = new Object();
                            dtsJsonObj =   JSON.parse(data[0]); 
                            jQuery("#nhkb_chuyen").val(dtsJsonObj.nhkb_chuyen);
                            jQuery("#ten_don_vi_tra_soat").val(dtsJsonObj.ten_don_vi_tra_soat);
                            jQuery("#ma_don_vi_tra_soat").val(dtsJsonObj.ma_don_vi_tra_soat);
                            jQuery("#ttv_nhan").val(dtsJsonObj.ten_ttv_nhan);
                            jQuery("#ngay_nhan").val(dtsJsonObj.ngay_nhan);
                            jQuery("#ttv_nhan").val(dtsJsonObj.ten_ttv_nhan);
                            jQuery("#ma_ttv_nhan").val(dtsJsonObj.ma_ttv_nhan);
                            var dmnhJsonObj = new Object();
                            dmnhJsonObj =  JSON.parse(data[1]);;
                            countDmnhNhan = 0;
                            if (dmnhJsonObj != null) {
                                //Xoa option cho NHKN Nhan
                                jQuery('#ma_don_vi_nhan_tra_soat option').remove();
                                countDmnhNhan = dmnhJsonObj.size();                         
                            }
                            if (countDmnhNhan > 0) {
                                for (var h = 0;h < countDmnhNhan;h++) {
                                    if (dmnhJsonObj.ma_nh == dmnhJsonObj[h].ma_nh) {
                                        jQuery('#ma_don_vi_nhan_tra_soat').append('<option value="' + dmnhJsonObj[h].ma_nh + '" selected="selected">' + dmnhJsonObj[h].ma_nh + '<\/option>');
                                        jQuery("#ten_don_vi_nhan_tra_soat").val(dmnhJsonObj[h].ten);
//                                        alert(dmnhJsonObj[h].ten);
                                    }
                                    else {
                                        jQuery('#ma_don_vi_nhan_tra_soat').append('<option value="' + dmnhJsonObj[h].ma_nh + '" >' + dmnhJsonObj[h].ma_nh + '<\/option>');
                                        jQuery("#ten_don_vi_nhan_tra_soat").val(dmnhJsonObj[h].ten);
//                                        alert(dmnhJsonObj[h].ten);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }
    });
}

function buttonThemDTSTD() {
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
//********************** fill form detail ***********************************
function setNewValueForNHKB(duplicateData) {
    // Validate that the browser knows getElementById 
    if (!document.getElementById)
        return;
    // get the "pages" select box 
    var pagesObj = document.getElementById("ma_don_vi_nhan_tra_soat");
    // Validate that the object exists 
    if (!pagesObj)
        return;
    // get the options array. This works for both 
    // single select and multi select boxes 
    if (typeof (pagesObj.options) == "undefined" || pagesObj.options == null)
        return;
    var o = pagesObj.options;
    // get the selected options into an array 
    var s = new Array();
    var bCheck = false;
    for (var i = 0;i < o.length;i++) {
        if (duplicateData == o[i].value) {
            bCheck = true;
            break;
        }
    }
    if (!bCheck) {
        addMoreElement(duplicateData);
    }
}

function addMoreElement(valueNHKB_nhan) {
    var create = '<option value="' + valueNHKB_nhan + '">' + valueNHKB_nhan + '</option>';
    jQuery('#ma_don_vi_nhan_tra_soat').append(create);
    jQuery('#ma_don_vi_nhan_tra_soat').change(function () {
        jQuery("select option:selected").each(function () {
            valueNHKB_nhan = jQuery(this).val();
        });
        jQuery('#ma_don_vi_nhan_tra_soat').val(valueNHKB_nhan);
        var pagesObj = document.getElementById("ma_don_vi_nhan_tra_soat");
    }).change();
}

function fillDataDTSTuDo(id, tr_id, bCheck) {
    //    resetFormDTSTuDo();
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatTuDoView.do", data :  {
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
                        //select box       
                        var valueNHKB_nhan = data.ma_don_vi_nhan_tra_soat;
                        setNewValueForNHKB(valueNHKB_nhan);
                        //end
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
                            roleUserDTSTuDo(data.chuc_danh, data.trang_thai);
                        }
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ma_ktt").val(data.ma_ktt);
                        jQuery("#noi_dung_ky").val(data.noi_dung_ky);
                    }
                }
                else {
                    var exception_message = data.exception_message;
                    if (exception_message != null && exception_message != 'null') {
                        alert("Duy?t không thành công! \n" + exception_message);
                    }
                    else {
                        alert("Duy?t không thành công! \n");
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
function setStyleRowEmpty() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}
// select len ban ghi dau tien
function selectedTopRow(id, col, chucdanh, checkButton) {
    // viet 1 ham select dau tien giong ham nay
    if (chucdanh != null || chucdanh != '') {
        roleUserDTSTuDo(chucdanh, '');
    }
    // neu nguoi dang nhap khong co quyen
    if (checkButton == true) {
        ButtonPhanQuyenGuest();
        defaultRowSelectedTuDo(true);
        resetFormDTSTuDo();
    }
    else {
        if (id == null || id == '' || id == 'undefined') {
            setStyleRowEmpty();
            removeFormDTSTuDo();
        }
        else {
            // truong hop co quyen user
            //        rowSelectedFocus("row_dts_0");
            defaultRowSelectedTuDo(checkButton);
            resetFormDTSTuDo();
        }
    }
}

function defaultRowSelectedTuDo(bCheck) {
    var row_default = "row_dts_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataDTSTuDo(input_default.val(), row_default, bCheck);
        rowSelectedFocus(row_default);
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
        disabled : 'disabled'
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        disabled : 'disabled'
    });
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
    jQuery("#noidung").removeAttr("disabled");
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
   * phan quyen button hien thi theo user va theo trang thai
   */
function roleUserDTSTuDo(pQuyen, trangthai) {
    var delimiter = '|';
    var chucdanh;
    var chucdanhTTV;
    var chucdanhKTT;
    var chucdanhGD;
    if (pQuyen != null || pQuyen != '') {
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
            }else{
                chucdanhKTT = chucdanh[i];
            }
        }
        var typeUser = "0";
        // set quyen
        // truong hop 1 : User co tat ca cac quyen
        // chi can set 
        if ((chucdanhTTV != null && chucdanhKTT != null) || (chucdanhTTV != null && chucdanhGD != null)) {
            typeUser = '1';
            buttonWithTrangThai(typeUser, trangthai);
        }
        else {
            //truong hop 2 : User la giam doc hoac KTT 
            if (chucdanhKTT != null || chucdanhGD != null) {
                if (chucdanhKTT == "KTT" || chucdanhGD == "GD") {
                    typeUser = '2';
                    buttonWithTrangThai(typeUser, trangthai);

                }else{
                    typeUser = '4';
                    initStateButton();
                    jQuery("#btnTim").hide();
                    jQuery("#btnRefresh").hide();
                }
            }
            else {
                if (chucdanhTTV != null && chucdanhTTV == "TTV") {
                    typeUser = '3';
                    buttonWithTrangThai(typeUser, trangthai);
                }
            }
        }
    }
}

function ButtonPhanQuyenTuDo() { 
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
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

function ButtonPhanQuyenGuest() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").hide();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "readonly"
    }).val('');
    jQuery("#refreshButton *").attr( {
        disabled : 'disabled'
    });
    jQuery("#data-grid tr").removeAttr("onclick");
}
//********************** Thuc hien them moi dts*********************************** 
function addExcFormDTSTuDo() {
    var MaTTV = jQuery("#ttv_nhan").val();
    var NH_KB_chuyen_Code = jQuery("#ma_don_vi_tra_soat").val();
    var NH_KB_nhan_Code = jQuery("#ma_don_vi_nhan_tra_soat").val();
    var noidung = jQuery("#noidung").val();
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatTuDoAddExc.do", data :  {
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
                        if (data.sohieu_kb == null || data.sohieu_kb == '') {
                            jQuery("#dialog-message").html('Th&#234;m m&#7899;i &#273;i&#7879;n tra so&#225;t : <span style="font-weight:bold;color:red;">' + data.id_dts + '</span> th&#224;nh c&#244;ng !');
                            jQuery("#ma_don_vi_nhan_tra_soat").attr( {
                                disabled : "disabled"
                            });
                            jQuery("#noidung").attr( {
                                readonly : "true"
                            })
                            jQuery("#dialog-message").dialog("open");
                        }
                        else {
                            jQuery("#dialog-message-check").html('&#272;&#417;n v&#7883; nh&#7853;n tra so&#225;t kh&#244;ng h&#7907;p l&#7879; !');
                            jQuery("#ma_don_vi_nhan_tra_soat").focus();
                            buttonThemDTSTD();
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
function findDTSTuDo() {
    removeFormDTSTuDo();
 
    var maTTV = jQuery("#ma_ttv_tk").val(), nh_kb_nhan = jQuery("#nh_kb_nhan_tk").val(), so_dts = jQuery("#so_dts_tk").val();

    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatTuDo.do", data :  {
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
                        fillDataTableDSTTuDo(data, path);
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
function fillDataTableDSTTuDo(data, path) {
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
            var chucdanh = objectDTS.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                //            onkeydown=\"focusDayLai('"+objectDTS.chuc_danh+"',trang_thai);\" 
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSTuDo('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTSTuDo('" + objectDTS.id + "','row_dts_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_dts_" + i + "\" width=\"44%;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id='col_dts_" + i + "' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:100%;\" value=\"" + objectDTS.id + "\" onkeydown=\"arrowUpDownDTSTuDo(event)\" readonly=\"true\"/></td>";
            strTableData += "<td  align=\"center\" width=\"30%;\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmpty();
        ButtonPhanQuyenTuDo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        selectedTopRow(jQuery("#col_dts_0").val(), "row_dts_0", '', false);
    }
    tableHighlightRow();
}

/**
 * CHON BAN GHI BEN SO DIEN TRA SOAT
 */
function selectedRowAfterClickButton() {
    var rSelected = jQuery("#rowSelected").val();
}

function selectedRowBeforeClickButtonTuDo() {
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

function refreshGridDTSTD() {
    //    resetFormDTSTuDo();
    jQuery.ajax( {
        type : "POST", url : "XuLyDTSoatTuDo.do", data :  {
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
                        fillDataTableDSTTuDo(data, path);
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}
//focus vao truong day lai 
//function focusDayLai(chucdanh, trangthai) {
//    // neu la KTT va trang thai la cho duyet    
//    if (chucdanh == 'KTT' && trangthai.value == '02') {
//        // thi moi duoc su dung phim esc de focus len lydo_ktt_day_lai
//        if (event.keyCode == 13) {
//            jQuery("#lydo_ktt_day_lai").removeAttr("readonly");
//            jQuery("#lydo_ktt_day_lai").focus();
//        }
//    }
//}
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

function arrowUpDownDTSTuDo(e) {
    var keyCode = e.keyCode || e.charCode, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
    var input_id = "";
    var total_row = "";
    var input_id_length = jQuery(document.activeElement).attr('id').length;
    var input_id_value = jQuery(document.activeElement).attr('id').substring(8, input_id_length);
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

function rowSelectedFocusTuDo(rowId) {
    classRowHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();

}
//function focusDayLaiMaster(check) {
//    if (check) {
//        if (event.keyCode == 27 || event.keyCode == 8) {
//            defaultRowSelectedTuDo(check);
//        }
//    }
//}
// Exit 
function exitViewDTSTuDo() {
    document.forms[0].action = "thoatView.do";
    document.forms[0].submit();
}

function returnViewDTSTuDo() {
    document.forms[0].action = "XuLyDTSoatTuDo.do";
    document.forms[0].submit();
}

function initStateButton() {
    jQuery("#them").hide();
    jQuery("#sua").hide();
    jQuery("#ghi").hide();
    jQuery("#huy").hide();
    jQuery("#timKiem").show();
    jQuery("#duyet").hide();
    jQuery("#dayLai").hide();
    jQuery("#thoatDTSTD").show();
    jQuery("#lydo_ktt_day_lai").attr( {
        readonly : "readonly"
    });
    //    jQuery("#refreshButton *").attr( {
    //        disabled : 'disabled'
    //    });
}

function buttonWithTrangThai(typeUser, trangthai) {
    initStateButton();
    // ca quyen KTT VA TTV
    if (typeUser == '1') {
        if (trangthai == '' || trangthai == null) {
            jQuery("#them").show();
        }
        else if (trangthai == '02') {
            // cho KTT duyet
            jQuery("#them").show();
            jQuery("#duyet").show();
            jQuery("#dayLai").show();
            jQuery("#lydo_ktt_day_lai").attr( {
                readonly : ""
            });
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