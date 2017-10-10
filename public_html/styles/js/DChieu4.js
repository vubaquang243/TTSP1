function DChieu4Detail(id){ 
    if (id != null && "" != id) {
        jQuery.ajax( {
            type : "POST", url : "DChieu4Detail.do", data :  {
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

function ExcuteDChieu() {
    jQuery.ajax( {
        type : "POST", url : "THienDChieu4Action.do", data :  {
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
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

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
                }
            }

        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function defaultRowSelectedDC4(chucdanh) {
    var row_default = "row_dts_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        DChieu3Detail(jQuery('#id_0').val());
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