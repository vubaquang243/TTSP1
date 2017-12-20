
function updateExcuteQT(strActionKTT) {
    jQuery.ajax( {
        type : "POST", url : "XuLyLenhQuyetToanUpdateExc.do", data :  {
            "id" : jQuery("#id").val(),"ldo_hach_toan" : jQuery("#ldo_hach_toan").val(),"action" : strActionKTT,"loai_hach_toan" : jQuery("#loai_hach_toan").val(), 
            "noi_dung_ky" : jQuery("#noi_dung_ky").val(),
            "certSerial" : jQuery("#certSerial").val(),
            "chu_ky" : jQuery("#chu_ky").val(),
            "ma_nt" : jQuery("#ma_nt").val(),
            "ldo_day_lai" : jQuery("#ldo_day_lai").val(), "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data.Success!=null) {
                    if(data.typeAction=='daylai'){
                      jQuery("#dialog-message").html('&#272;&#7849;y l&#7841;i l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');  
                    }else if(data.typeAction =='duyet'){
                      jQuery("#dialog-message").html('Duy&#7879;t l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');  
                    }else{
                      jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#224;nh c&#244;ng !');
                    }
                }else if(data.Failure!=null){
                  jQuery("#dialog-message").html('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra :' + data.Failure + '!');
                }
                else {
                    if(data.typeAction=='daylai'){
                      jQuery("#dialog-message").html('&#272;&#7849;y l&#7841;i l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');  
                    }else if(data.typeAction =='duyet'){
                      jQuery("#dialog-message").html('Duy&#7879;t l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');  
                    }else{
                      jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n : <span style="font-weight:bold;color:red;">' + data.id + '</span> th&#7845;t b&#7841;i !');
                    }
                }
                defaultRowSelectedQT();
                jQuery("#dialog-message").dialog("open");
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html('Chuy&#7875;n l&#7879;nh quy&#7871;t to&#225;n th&#7845;t b&#7841;i ! \n'+textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}
function changeStateArea() {
    jQuery('#loai_hach_toan').change(function () {
        jQuery("select option:selected").each(function () {
            value = jQuery(this).val();
            if (value != null && '' != value) {
                if(value == 'P'){
                  jQuery('#ldo_hach_toan').removeAttr('style');
                  jQuery("#ldo_hach_toan").removeAttr('readonly').focus();              
                }else{
                  jQuery("#ldo_hach_toan").attr('readonly', 'readonly');
                  jQuery('#ldo_hach_toan').attr({'style' : 'BACKGROUND-COLOR: #f0f0f0;'});
                }
            }
            else {
                jQuery("#ldo_hach_toan").attr('readonly', 'readonly');
                jQuery('#ldo_hach_toan').attr({'style' : 'BACKGROUND-COLOR: #f0f0f0;'});
            }
        });
    }).change();
}

function fillDataQuyetToan(id, tr_id) {
    jQuery.ajax( {
        type : "POST", url : "XuLyLenhQuyetToanView.do", data :  {
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
                        jQuery("#id_ref").val(data.id_ref);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#ten_don_vi_chuyen").val(data.ten_don_vi_chuyen);
                        jQuery("#ten_don_vi_nhan").val(data.ten_don_vi_nhan);
                        jQuery("#ngay_htoan").val(data.ngay_htoan);
                        jQuery("#ngay_qtoan").val(data.ngay_qtoan);
                        jQuery('#loai_hach_toan option').remove();
                        jQuery("#so_tchieu").val(data.so_tchieu);
                        jQuery("#tr_ttin_lenh_goc").hide();
                        
                        if(data.loai_qtoan=='D'){
                          jQuery("#loai_qtoan").val(GetUnicode("Thu"));
                        }else if(data.loai_qtoan=='C'){
                          jQuery("#loai_qtoan").val(GetUnicode("Chi"));
                        }
                        if(data.lai_phi=='02'){
                           if(data.loai_qtoan=='D'){
                            jQuery("#lai_phi").val(GetUnicode("Ph&#237; TT"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n v&#224;o 3713")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("H&#7841;ch to&#225;n ch&#7901; x&#7917; l&#253;")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="1339">H&#7841;ch to&#225;n v&#224;o 1339/3999<\/option>');    
                            
                            //jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            //jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');
                            //jQuery('#loai_hach_toan').append('<option value="1339">H&#7841;ch to&#225;n v&#224;o TK 1339<\/option>');                            
                          }else if(data.loai_qtoan=='C'){
                            jQuery("#lai_phi").val(GetUnicode("L&#227;i SDTK"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n v&#224;o 3713")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("H&#7841;ch to&#225;n ch&#7901; x&#7917; l&#253;")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="3999">H&#7841;ch to&#225;n v&#224;o 1339/3999<\/option>');
                            
                            //jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            //jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');
                            //jQuery('#loai_hach_toan').append('<option value="3999">H&#7841;ch to&#225;n v&#224;o TK 3999<\/option>');
                          }
                        }else if(data.lai_phi=='04'){
                          jQuery("#so_tchieu").val(data.mt_refid);  
                          if(data.loai_qtoan=='D'){
                            jQuery("#loai_qtoan").val(GetUnicode("N&#7907;"));
                            jQuery("#lai_phi").val(GetUnicode("C.L&#7879;ch T.Gi&#225;"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');
                          }else if(data.loai_qtoan=='C'){
                            jQuery("#loai_qtoan").val(GetUnicode("C&#243;"));
                            jQuery("#lai_phi").val(GetUnicode("C.L&#7879;ch T.Gi&#225;"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');                            
                          }
                          jQuery("#tr_ttin_lenh_goc").show(); 
                          jQuery("#ttin_lenh_goc").val(GetUnicode(data.ttin_lenh_goc));
                        }else if(data.lai_phi=='06'){
                            jQuery("#loai_qtoan").val(GetUnicode("N&#7907;"));
                            jQuery("#lai_phi").val(GetUnicode("Thu ph&#237; POS"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="1339">H&#7841;ch to&#225;n v&#224;o TK 1339<\/option>');                            
                        }else if(data.lai_phi=='05'){
                            jQuery("#loai_qtoan").val(GetUnicode("C&#243;"));
                            jQuery("#lai_phi").val(GetUnicode("Thu qua POS"));
                            jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                            jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');  
                        }else{
                          jQuery("#lai_phi").val(GetUnicode("Quy&#7871;t to&#225;n"));
                          jQuery('#loai_hach_toan').append('<option value="T">'+GetUnicode("H&#7841;ch to&#225;n &#273;&#250;ng")+'<\/option>');
                          jQuery('#loai_hach_toan').append('<option value="P">'+GetUnicode("Ch&#7901; x&#7917; l&#253;")+'<\/option>');
                        }
                        
                        jQuery("#mo_ta_trang_thai").val(data.mo_ta_trang_thai);
                        
                        jQuery("#ma_tchieu_gd").val(data.so_tchieu);
                        jQuery("#nguoi_nhap_nh").val(data.nguoi_nhap_nh);
                        jQuery("#ngay_nhap_nh").val(data.ngay_nhap_nh);
                        jQuery("#nguoi_ks_nh").val(data.nguoi_ks_nh);
                        jQuery("#ngay_ks_nh").val(data.ngay_ks_nh);
                        jQuery("#ndung_tt").val(data.ndung_tt);
                        jQuery("#ma_nt").val(data.ma_nt);
                        jQuery("#so_tien").val(CurrencyFormatted(data.so_tien,data.ma_nt));

                        /*
                         * thong tin nhan
                         * */
                        jQuery("#ten_kh_chuyen").val(data.ten_kh_chuyen);
                        jQuery("#tk_chuyen").val(data.tk_chuyen);
                        jQuery("#ma_nh_chuyen").val(data.ma_nh_chuyen);
                        jQuery("#ten_nh_chuyen").val(data.ten_nh_chuyen);
                        /*
                          * thong tin phat
                          * */
                        jQuery("#ten_kh_nhan").val(data.ten_kh_nhan);
                        jQuery("#tk_nhan").val(data.tk_nhan);
                        jQuery("#ma_nh_nhan").val(data.ma_nh_nhan);
                        jQuery("#ten_nh_nhan").val(data.ten_nh_nhan);
                        /*
                           * thong tin hoan thien
                           * */
                        if (data.ma_ttv_hoanthien != null && data.ma_ttv_hoanthien != 'undefined')
                            jQuery("#ma_ttv_hoanthien").val(data.ma_ttv_hoanthien + '-' + data.ten_ttv_hoanthien);
                        jQuery("#ngay_chuyen_ks").val(data.ngay_chuyen_ks);
                        if (data.ma_ktt_hoanthien != null && data.ma_ktt_hoanthien != 'undefined')
                            jQuery("#ma_ktt_hoanthien").val(data.ma_ktt_hoanthien + '-' + data.ten_ktt_hoanthien);
                        jQuery("#ngay_ks").val(data.ngay_ks);
                        jQuery("#loai_hach_toan").val(data.loai_hach_toan);
                        jQuery("#ldo_hach_toan").val(data.ldo_hach_toan);
                        jQuery("#ldo_day_lai").val(data.ldo_day_lai);                        
                        jQuery("#noi_dung_ky").val(data.noi_dung_ky);                        
                        jQuery("#rowSelected").val(tr_id);                        
                        roleUserQT(data.chuc_danh, data.trang_thai,data.loai_hach_toan);
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }

    });
}
//ManhNV-2014: rao vi trung ham************************************************
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
//      return (((sign)?'':'-')  + num + '.' + cents);
//    else
//      return (((sign)?'':'-')  + num );
//}
//******************************************************************************
function defaultRowSelectedQT() {
    var row_default = "row_qt_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataQuyetToan(input_default.val(), row_default);
        rowSelectedFocusQT(row_default);
    }
     var nhap_thu_cong = jQuery('tr#row_qt_0').find('#nhap_thu_cong').val();
     var trang_thai = jQuery('tr#row_qt_0').find('input#trang_thai').val();
      if(nhap_thu_cong == "Y" && trang_thai == "02"){
        jQuery('#btnUpdate').show();
        jQuery('#btnUpdate').click(function(){
          document.forms[0].action = "updateLenhQuyetToan.do";
          document.forms[0].submit();
        });
      }else{
        jQuery('#btnUpdate').hide();
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

function rowSelectedFocusQT(rowId) {
    classRowHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();

}

function refreshGridQT() {
    //    resetFormDTSTuDo();
    jQuery.ajax( {
        type : "POST", url : "XuLyLenhQuyetToanList.do", data :  {
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
                        fillDataTableQT(data, path);
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
function fillDataTableQT(data, path) {
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
                trangthai_path = " <img src=\"" + path + "styles/images/sended-but.jpg\" " + "border=\"0\" title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (trangthai == '04') {
                trangthai_path = " <img src=\"" + path + "styles/images/active.gif\" " + "border=\"0\" title=\"&#272;&#227; t&#7841;o b&#7843;ng k&#234;\"/>";
            }
            else if (trangthai == '11') {
                trangthai_path = " <img src=\"" + path + "styles/images/sended-but.jpg\" " + "border=\"0\" title=\"Ch&#7901; ch&#7841;y giao di&#7879;n\"/>";
            }
            else if (trangthai == '12') {
                trangthai_path = " <img src=\"" + path + "styles/images/send-success.jpg\" " + "border=\"0\" title=\"Giao di&#7879;n th&#224;nh c&#244;ng\"/>";
            }
            else if (trangthai == '13') {
                trangthai_path = " <img src=\"" + path + "styles/images/sended-false.jpg\" " + "border=\"0\" title=\"Giao di&#7879;n kh&#244;ng th&#224;nh c&#244;ng\"/>";
            }
            else if (trangthai == '16') {
                trangthai_path = " <img src=\"" + path + "styles/images/sended-false.jpg\" " + "border=\"0\" title=\"L&#7895;i truy&#7873;n tin\"/>";
            }
            var chucdanh = objectQT.chuc_danh;
            if (null != chucdanh && 'undefined' != chucdanh) {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_qt_" + i + "\" ondblclick=\"rowSelectedFocusQT('row_qt_" + i + "');\" onclick=\"rowSelectedFocusQT('row_qt_" + i + "');fillDataQuyetToan('" + objectQT.id + "','row_qt_" + i + "');\">";
            }
            else {
                strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_qt_" + i + "\" ondblclick=\"rowSelectedFocusQT('row_qt_" + i + "');\" onclick=\"rowSelectedFocusQT('row_qt_" + i + "');fillDataQuyetToan('" + objectQT.id + "','row_qt_" + i + "',false);\">";
            }
            strTableData += "<td id=\"td_qt_" + i + "\" width=\"44%;\" align=\"center\">";
            strTableData += "<input type=\"hidden\" name=\"row_item\" id=\"col_qt_" + i + "\" type=\"text\" style=\"border:0px;font-size:10px;float:left;width:100%;\" value=\"" + objectQT.id + "\" />";
            strTableData += "<input name=\"row_item\" id='col_qt' type=\"text\" style=\"border:0px;font-size:10px;float:left;width:100%;\" value=\"" + objectQT.id_ref + "\" onkeydown=\"arrowUpDownQT(event)\" readonly=\"true\"/></td>";
            strTableData += "<td width=\"30%;\" align=\"center\">" + trangthai_path + "</td></tr>";
        }

    });
    if (data == "" || data == null) {
        jQuery("#data-grid").html('<tbody><tr><td colspan="3" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr></tbody>');
        setStyleRowEmptyQT();
        //ButtonPhanQuyenTuDo();
    }
    else {
        jQuery("#data-grid").html('<tbody>' + strTableData + '</tbody>');
        defaultRowSelectedQT();
    }
    tableHighlightRowQT();
}

function tableHighlightRowQT() {
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

function arrowUpDownQT(e) {
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
            rowSelectedFocusQT(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }

}

function setStyleRowEmptyQT() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}

function roleUserQT(pQuyen, trangthai,loai_ht) {
    var typeUser;   
    if (pQuyen != null || pQuyen != '') {
        // set quyen
        // truong hop 1 : TTV
        // hien thi button ttv
        if (pQuyen.indexOf("TTV") !=  - 1 && pQuyen.indexOf("KTT") ==  - 1) {
            typeUser = 0;
            buttonWithTrangThaiQT(typeUser, trangthai,loai_ht);
        }else if (pQuyen.indexOf("KTT") !=  - 1 && pQuyen.indexOf("TTV") ==  - 1) {
            //truong hop 2 : User la  KTT 
            typeUser = 1;
            buttonWithTrangThaiQT(typeUser, trangthai,loai_ht);
        }else if (pQuyen.indexOf("KTT") !=  - 1 && pQuyen.indexOf("TTV") !=  - 1) {
            //truong hop 2 : User la  KTT 
            typeUser = 2;
            buttonWithTrangThaiQT(typeUser, trangthai,loai_ht);
        }
    }
}
function defaultButton(){
  jQuery("#chuyen").hide();
    jQuery("#daylai").hide();
    jQuery("#duyet").hide();
    jQuery("#in").show();
}
function buttonForTTV() {
    jQuery("#chuyen").show();
    jQuery("#daylai").hide();
    jQuery("#duyet").hide();
    jQuery("#in").show();
}

function buttonForKTT() {
    jQuery("#chuyen").hide();
    jQuery("#daylai").show();
    jQuery("#duyet").show();
    jQuery("#in").show();    
}
function buttonForTTVandKTT() {
    jQuery("#chuyen").show();
    jQuery("#daylai").show();
    jQuery("#duyet").show();
    jQuery("#in").show();    
}

function buttonWithTrangThaiQT(type, trangthai,loai_ht) {
    defaultButton();
    // TTV
    /*
     * Trang thai
     * 00 - cho ttv hoan thien
     * 01 - cho ktt duyet
     * 02 - ktt day lay
     * 03 - da duyet     
     * */
    if (type == 0) {
        buttonForTTV();
        if (trangthai == 01 || trangthai == 03) {
            jQuery("#chuyen").hide();
            jQuery('#frmQT select').attr('disabled', 'disabled');
            defaultStateForm();
        }else if(trangthai == 00 || trangthai ==02){
            jQuery('#frmQT select').removeAttr('disabled', 'disabled');
            jQuery("#chuyen").show();
        }
        else {
            jQuery('#frmQT select').removeAttr('disabled', 'disabled');
            jQuery("#chuyen").hide();
        }
    }
    // ktt
    else if (type == 1){
        buttonForKTT();
        jQuery('#frmQT select').attr('disabled', 'disabled');
        if (trangthai != 01) {
            jQuery("#daylai").hide();
            jQuery("#duyet").hide();
            defaultStateForm();
        }else{
          if(trangthai == 01){
            jQuery('#ldo_day_lai').removeAttr('style');
            jQuery("#ldo_day_lai").removeAttr('readonly').focus();
          }else{          
            jQuery('#frmQT textarea').attr('readonly', 'readonly');
            jQuery('#frmQT textarea').attr( {
                'style' : 'BACKGROUND-COLOR: #f0f0f0;'
            });
          }
        }
    }else{
       buttonForTTVandKTT();
       if (trangthai == 01 || trangthai == 03) {
            jQuery("#chuyen").hide();
            jQuery('#frmQT select').attr('disabled', 'disabled');
            defaultStateForm();
            if(trangthai == '01'){            
              jQuery('#ldo_day_lai').removeAttr('style');
              jQuery("#ldo_day_lai").removeAttr('readonly').focus();
            }else{
              jQuery("#duyet").hide();
              jQuery("#daylai").hide();
              jQuery("#ldo_day_lai").attr('readonly', 'readonly');
            }
        }else if(trangthai == 00 || trangthai ==02){
            jQuery('#frmQT select').removeAttr('disabled', 'disabled');
            jQuery("#chuyen").show();
            jQuery("#duyet").hide();
            jQuery("#daylai").hide();
            jQuery("#ldo_day_lai").attr('readonly', 'readonly');
        }
        else {
            jQuery('#frmQT select').removeAttr('disabled', 'disabled');
            jQuery("#chuyen").hide();
            jQuery("#duyet").hide();
            jQuery("#daylai").hide();
            jQuery("#ldo_day_lai").attr('readonly', 'readonly');
        }
    }
}

function defaultStateForm() {
    jQuery('#frmQT input').attr('readonly', 'readonly');
    jQuery('#frmQT select').attr('disabled', 'true');
    jQuery('#frmQT textarea').attr('readonly', 'readonly');
    jQuery('#frmQT textarea').attr( {
        'style' : 'BACKGROUND-COLOR: #f0f0f0;'
    });
}

function nextElementFocusQT(e) {
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
function keyDownLHTQT() {
    if (event == null) {
        return;
    }
    if (event.keyCode == 8) {
        window.event.keyCode = 0;
    }
    if (jQuery(document.activeElement).attr('name') != 'row_item') {
        if (event.keyCode == 13) {
            if (event.target) {
                targ = event.target;
            }
            else if (event.srcElement) {
                targ = event.srcElement;
                if (targ.tagName != 'BUTTON') {
                    event.keyCode = 9;
                }
            }
        }
        else if (event.keyCode == 27) {
            defaultRowSelectedQT();
        }
        //        else if (event.keyCode == 8) {alert(event.keyCode);
        //            jQuery("#"+jQuery(document.activeElement).attr('name')).focus();
        //        }
    }
    else if (event.keyCode == 8) {
        //            alert("BackSpace");
        event.keyCode = 46;
        if (event.target) {
            targ = event.target;
        }
        else if (event.srcElement) {
            targ = event.srcElement;
            if (targ.tagName == 'INPUT' || targ.tagName == 'TEXTAREA') {
                if (targ.readOnly == true || targ.id == 'lydo_ktt_day_lai' || targ.id == 'lydo_gd_day_lai') {
                    return false
                }
            }
            else {
                return false;
            }
        }
    }
    else if (event.keyCode == 27) {
        defaultRowSelectedQT();
        return false;
    }
}