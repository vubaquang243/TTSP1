function DChieu3Detail(id){ 
    if (id != null && "" != id) {
        jQuery.ajax( {
            type : "POST", url : "DChieu3Detail.do", data :  {
                "id" : id
            },
            success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {               
                    if (data != null) {                    
                        var lstBKInf = new Object();
                        lstBKInf = JSON.parse(data[0]);
                        var lstBKInfSize = lstBKInf.size();
                        var strTRTag;
                        
                        if (lstBKInf != null) {
                            if (lstBKInfSize > 0) { 
                                for (var i = 0;i < lstBKInfSize;i++) {
                                    var objectDC = lstBKInf[i];
                                    jQuery("#sodu_daungay").val(CurrencyFormatted(objectDC.sodu_daungay == null ? 0 : objectDC.sodu_daungay, 'VND'));
                                    jQuery("#tong_chi").val(CurrencyFormatted(objectDC.tong_chi == null ? 0 : objectDC.tong_chi, 'VND'));
                                    jQuery("#tong_thu").val(CurrencyFormatted(objectDC.tong_thu == null ? 0 : objectDC.tong_thu, 'VND'));
                                    jQuery("#so_du_cuoi_ngay").val(CurrencyFormatted(objectDC.so_du_cuoi_ngay == null ? 0 : objectDC.so_du_cuoi_ngay, 'VND'));
                                    jQuery("#id").val(objectDC.id);
                                    jQuery("#ngay_dc").val(objectDC.ngay_dc);
                                    jQuery("#send_bank").val(objectDC.send_bank);
                                    jQuery("#trang_thai_in").val(objectDC.trang_thai);                                   
                                    jQuery("#kq_id").val(objectDC.kq_id);                                  
                                    jQuery("#lan_dc").val(objectDC.lan_dc);                                
                                    jQuery("#mt910thua").val(objectDC.mt910_thua);
                                    jQuery("#mt910thieu").val(objectDC.mt910_thieu);
                                    jQuery("#mt900thua").val(objectDC.mt900_thua);
                                    jQuery("#mt900thieu").val(objectDC.mt900_thieu);
                                    if (objectDC.trang_thai == '00') {
                                        jQuery("#btnDC").attr( {
                                            'disabled' : false
                                        });
                                        jQuery("#btnTaoDXN").attr( {
                                            'disabled' : true
                                        });
                                        jQuery("#btnInBKDC").attr( {
                                            'disabled' : true
                                        });
                                    }
                                    else {
                                        jQuery("#btnDC").attr( {
                                            'disabled' : true
                                        });
                                        jQuery("#btnTaoDXN").attr( {
                                            'disabled' : false
                                        });
                                        jQuery("#btnInBKDC").attr( {
                                            'disabled' : false
                                        });
                                    }
                                    if (objectDC.trang_thai_kq == '01') {
                                        jQuery("#btnTaoDXN").attr( {
                                            'disabled' : true
                                        });
                                    }
                                    if (objectDC.trang_thai_kq == '00') {
                                        jQuery("#btnTaoDXN").attr( {
                                            'disabled' : false
                                        });
                                        
                                    }else{
                                    jQuery("#btnTaoDXN").attr( {
                                            'disabled' : true
                                        });
                                    }
                                }
                            }
                        }                       
                        var lstMT900Inf = new Object();
                        lstMT900Inf = JSON.parse(data[1]);
                        jQuery("#tblMT900 > tbody").html("");    
                        strTRTag = "";
                        if (lstMT900Inf != null) {
                            if (lstMT900Inf.size() > 0) {
                                for (i = 0;i < lstMT900Inf.size();i++) {
                                    // 20170221 sua them format so tien su dung ham CurrencyFormatted2
                                    strTRTag = strTRTag + "<tr>" + "<td align='center'>" + lstMT900Inf[i].mt_id + "</td><td align='center'>" + lstMT900Inf[i].ma_kb + "</td>" + "<td align='left'>" + lstMT900Inf[i].ten_kb + "</td>" + "<td align='center'>" + lstMT900Inf[i].ngay_ct + "</td>" + "<td align='right'>" + CurrencyFormatted2(lstMT900Inf[i].so_tien == null ? 0 : lstMT900Inf[i].so_tien) + "</td>" + "<td align='left'>";
                                    if (lstMT900Inf[i].trang_thai == '00') {
                                        strTRTag = strTRTag + GetUnicode("SGD KBNN thi&#7871;u  &#8211; H&#7897;i s&#7903; ch&#237;nh NH th&#7915;a");
                                    }
                                    else if (lstMT900Inf[i].trang_thai == '01') {
                                        strTRTag = strTRTag + GetUnicode("SGD KBNN  th&#7915;a &#8211; H&#7897;i s&#7903; ch&#237;nh NH thi&#7871;u");
                                    }
                                    strTRTag += "</td>" + "</tr>";                                    
                                }
                                jQuery('#tblMT900').append(strTRTag);
                            }

                        }                        
                        jQuery("#tblMT910 > tbody").html("");
                        strTRTag = "";
                        var lstMT910Inf = new Object();
                        lstMT910Inf = JSON.parse(data[2]);                        
                        if (lstMT910Inf != null) {
                            if (lstMT910Inf.size() > 0) {                                
                                for (i = 0;i < lstMT910Inf.size();i++) {
                                    // 20170221 sua them format so tien su dung ham CurrencyFormatted2
                                    strTRTag = strTRTag + "<tr>" + "<td align='center'>" + lstMT910Inf[i].mt_id +  "</td>" + "<td align='center'>" + lstMT910Inf[i].ma_kb + "</td>" + "<td align='left'>" + lstMT910Inf[i].ten_kb + "</td>" + "<td align='center'>" + lstMT910Inf[i].ngay_ct + "</td>" + "<td align='right'>" + CurrencyFormatted2(lstMT910Inf[i].so_tien == null ? 0 : lstMT910Inf[i].so_tien) + "</td>" + "<td align='left'>";
                                    if (lstMT910Inf[i].trang_thai == '00') {
                                        strTRTag = strTRTag + GetUnicode("SGD KBNN thi&#7871;u  &#8211; H&#7897;i s&#7903; ch&#237;nh NH th&#7915;a");
                                    }
                                    else if (lstMT910Inf[i].trang_thai == '01') {
                                        strTRTag = strTRTag + GetUnicode("SGD KBNN  th&#7915;a &#8211; H&#7897;i s&#7903; ch&#237;nh NH thi&#7871;u");
                                    }
                                    strTRTag += "</td>" + "</tr>";
                                    //strTRTag = "" + "<tr>" + "<td align='center'>" + lstMT910Inf[i].solenhqt + "</td>" + "<td align='center'>" + lstMT910Inf[i].nhkbchuyen + "</td>" + "<td align='left'>" + lstMT910Inf[i].ten_receive_bank + "</td>" + "<td align='center'>" + lstMT910Inf[i].tennhkbchuyen + "</td>" + "<td align='center'>" + lstMT910Inf[i].nhkbnhan + "</td>" + "<td align='left'>" + lstMT910Inf[i].tennhkbnhan + "</td>" + "<td align='right'>" + lstMT910Inf[i].ngayct + "</td>" + "<td align='right'>" + lstMT910Inf[i].stien + "</td>" + "<td align='left'>" + lstMT910Inf[i].tthai + "</td>" + "</tr>";
                                }                                
                                jQuery('#tblMT910').append(strTRTag);
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
}

function DChieu2Detail(send_bank, ngay_dc) {
    jQuery('#send_bank').val(send_bank);
    jQuery.ajax( {
        type : "POST", url : "DChieu2Detail.do", data :  {
            "ngaydc" : ngay_dc, "ma_nh_chuyen" : send_bank
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDC) {                       
                        jQuery("#mt900kbnn").val(CurrencyFormatted(objectDC.mt900kbnn != 'undefined' ? 0 : objectDC.mt900kbnn, 'VND'));
                        jQuery("#mt910kbnn").val(CurrencyFormatted(objectDC.mt910kbnn != 'undefined' ? 0 : objectDC.mt910kbnn, 'VND'));
                        jQuery("#mt900nhtm").val(CurrencyFormatted(objectDC.mt900nhtm != 'undefined' ? 0 : objectDC.mt900nhtm, 'VND'));
                        jQuery("#mt910nhtm").val(CurrencyFormatted(objectDC.mt910nhtm != 'undefined' ? 0 : objectDC.mt910nhtm, 'VND'));
                        jQuery("#chikbnn").val(CurrencyFormatted(objectDC.chikbnn != 'undefined' ? 0 : objectDC.chikbnn, 'VND'));
                        jQuery("#thukbnn").val(CurrencyFormatted(objectDC.thukbnn != 'undefined' ? 0 : objectDC.thukbnn, 'VND'));
                    });
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

/*function TaoXacNhan() {
    var id = jQuery('#id').val();
    if (id != null && "" != id) {
        jQuery.ajax( {
            type : "POST", url : "TaoXacNhanDCAction.do", data :  {
                "id" : id
            },
            success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data.error_code != null && !data.error_code == '00') {
                        jQuery("#dialog-message").html('T&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n th&#224;nh c&#244;ng.');
                        jQuery("#dialog-message").dialog("open");
                        jQuery("#btnTaoDXN").attr( {'disabled' : true});
                    }
                    else {
                        jQuery("#dialog-message").html('Kh&#244;ng t&#7841;o &#273;&#432;&#7907;c &#273;i&#7879;n x&#225;c nh&#7853;n.');
                        jQuery("#dialog-message").dialog("open");
                    }
                }

            },
            error : function (textstatus) {
                alert(textstatus);
            }
        });
        
    }
    else {
        jQuery("#dialog-message").html('B&#7841;n ch&#7885;n 1 b&#7843;ng k&#234; trong danh s&#225;ch b&#7843;ng k&#234; .');
        jQuery("#dialog-message").dialog("open");
    }
    
   
}*/

function ExcuteDChieu() {
    jQuery.ajax( {
        type : "POST", url : "THienDChieu3Action.do", data :  {
            "BK_ID" : jQuery("#id").val(), "NgayDC" : jQuery("#ngay_dc").val()
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data.error_code != null && data.error_code == '00') {
                    jQuery("#dialog-message").html('&#272;&#227; kh&#7899;p &#273;&#250;ng.');
                    jQuery("#dialog-message").dialog("open");
                }
                else {
                    jQuery("#dialog-message").html(data.error_desc);
                    jQuery("#dialog-message").dialog("open");
                    //View MT Chi tiet 900
                    //View MT Chi tiet 910
                    //Tong hop MT Chi tiet 900 & 910
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

//************************************ RESET FROM **********************************
function resetFormDC2() {
    jQuery("#sodu_daungay").val("");
    jQuery("#ketchuyen_chi").val("");
    jQuery("#ps_thu").val("");
    jQuery("#hanmuc").val("");
    jQuery("#ketchuyen_thu").val("");
    jQuery("#sodu_cuoingay").val("");
}
//ManhNV-2014: rao vi trung ham*************************************************
//function CurrencyFormatted(num, ma_nt) {
//    if (num == null || num == '')
//        return;
//    num = num.toString().replace(/\$|\,/g,'');
//    if (isNaN(num))
//        num = "0";
//    sign = (num == (num = Math.abs(num)));
//    num = Math.floor(num * 100 + 0.50000000001);
//    cents = num % 100;
//    num = Math.floor(num / 100).toString();
//    if (cents < 10)
//        cents = "0" + cents;
//    for (var i = 0;i < Math.floor((num.length - (1 + i)) / 3);i++)
//        num = num.substring(0, num.length - (4 * i + 3)) + '.' + num.substring(num.length - (4 * i + 3));
//    if (ma_nt.toUpperCase() != 'VND')
//        return (((sign) ? '' : '-') + num + ',' + cents);
//    else 
//        return (((sign) ? '' : '-') + num);
//}
//*****************************************************************************
//function CurrencyFormatted(num) {
//    num = num.toString().replace(/\$|\,/g, '');
//    if (isNaN(num))
//        num = "0";
//    sign = (num == (num = Math.abs(num)));
//    num = Math.floor(num * 100 + 0.50000000001);
//    cents = num % 100;
//    num = Math.floor(num / 100).toString();
//    if (cents < 10)
//        cents = "0" + cents;
//    for (var i = 0;i < Math.floor((num.length - (1 + i)) / 3);i++)
//        num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
//    return (((sign) ? '' : '-') + num + '.' + cents);
//}
function MT900CTiet() {
    jQuery.ajax( {
        type : "POST", url : "MT900CTiet.do", data :  {
            "BK_ID" : jQuery("#id").val(), "NgayDC" : jQuery("#ngay_dc").val()
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data.error_code != null && data.error_code == '00') {
                    jQuery("#dialog-message").html('&#272;&#227; kh&#7899;p &#273;&#250;ng.');
                    jQuery("#dialog-message").dialog("open");
                }
                else {
                    jQuery("#dialog-message").html(data.error_desc);
                    jQuery("#dialog-message").dialog("open");
                    //View MT Chi tiet 900
                    //View MT Chi tiet 910
                    //Tong hop MT Chi tiet 900 & 910
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function MT910CTiet() {
    jQuery.ajax( {
        type : "POST", url : "MT900CTiet.do", data :  {
            "BK_ID" : jQuery("#id").val(), "NgayDC" : jQuery("#ngay_dc").val()
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data.error_code != null && data.error_code == '00') {
                    jQuery("#dialog-message").html('&#272;&#227; kh&#7899;p &#273;&#250;ng.');
                    jQuery("#dialog-message").dialog("open");
                }
                else {
                    jQuery("#dialog-message").html(data.error_desc);
                    jQuery("#dialog-message").dialog("open");
                    //View MT Chi tiet 900
                    //View MT Chi tiet 910
                    //Tong hop MT Chi tiet 900 & 910
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}
/*
   * defaultRowSelectedDC()
   * Lua chon ban ghi dau tin o master
   * Su dung khi refresh/find/load page
   * Cung su dung sua khi thao tac xong duyet/huy/sua...
   */
function defaultRowSelectedDC3(chucdanh) {
    var row_default = "row_dts_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        DChieu3Detail(jQuery('#id_0').val());
        rowSelectedFocus(row_default);
    }
}

function defaultRowSelectedDC2(chucdanh) {
    var row_default = "row_dts_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        DChieu2Detail(jQuery('#id_0').val());
        rowSelectedFocus(row_default);
    }
}


function confirmTaoDXNhan() {
    jQuery('#eventAction').val('create');
    jQuery("#dialog-confirm").html('B&#7841;n c&#243; ch&#7855;c ch&#7855;n t&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n.');
    jQuery("#dialog-confirm").dialog("open");
}

function ThoatDC() {
    jQuery('#eventAction').val('exit');
    jQuery("#dialog-confirm").html('B&#7841;n c&#243; ch&#7855;c ch&#7855;n mu&#7889;n tho&#225;t kh&#244;ng .');
    jQuery("#dialog-confirm").dialog("open");
}