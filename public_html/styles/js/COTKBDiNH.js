//************************************ RESET FROM **********************************
function resetFormCOT() {
    jQuery("#ma_nh").val("");
    jQuery("#loai_cot").val("");
    jQuery("#ma_kb_tinh").attr('disabled', "disabled");
    jQuery("#ma_kb_tinh").multiselect('refresh');
    jQuery("#kb_id").attr('disabled', "disabled");
    jQuery("#kb_id").html('');
    jQuery("#kb_id").multiselect('refresh');
    jQuery("#ten_nguoi_lap").val("");
    jQuery("#ngay_lap").val("");
    jQuery("#nguoi_ks").val("");
    jQuery("#ngay_ks").val("");
    jQuery("#ngay_cot").val("");
    jQuery("#gio_cot_cu").val("");
    jQuery("#gio_cot_moi").val("");
    jQuery("#mo_ta_trang_thai").html("");
    jQuery("#mo_ta_trang_thai2").html("");
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#idFieldFocus").val('');
    jQuery("#lydo_cot").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#lydo_daylai").attr( {
        disabled : "disabled", value : ""
    });
}
//************************************ DAY DU LIEU VAO FROM **********************************  
function fillDetailToFormCOT(data, tr_id_selected) {
    jQuery("#id").val(data.id);
    jQuery("#ma_nh").val(data.nhkb_nhan);
    jQuery("#maxTime").val("");
    jQuery("#loai_cot").val(data.loai_cot);
    jQuery("#ma_kb_tinh option").each(function (){
      jQuery(this).removeAttr('selected');
      jQuery(this).attr({disabled:true});
    });

    var arrMaKBTinh = (data.ma_kb_tinh != null) ? data.ma_kb_tinh.split(',') : null;
    if (arrMaKBTinh != null) {
        jQuery("#ma_kb_tinh").val(arrMaKBTinh);
        jQuery.each(arrMaKBTinh, function (i, idTinh) {
            jQuery("#ma_kb_tinh option[value='" + idTinh + "']").attr('selected', 'selected');
            var optgrp = jQuery('<optgroup>');
            optgrp.attr('label', jQuery("#ma_kb_tinh option[value='" + idTinh + "']").text());
            obj.filter(function (item) {
                if (item.id_cha === idTinh && item.ma_dv == jQuery("#ma_nh").val()) {
                    var opt = jQuery('<option />', 
                    {
                        value : item.ma_nh, text : item.ten, disabled : 'disabled'
                    });
                    opt.appendTo(optgrp);
                }
            });
            optgrp.appendTo(jQuery("#kb_id"));
        });
    }
    var newTime = new Date(data.tgian_cot_moi)
    var arrMaKBHuyen = (data.ma_kb_huyen) ? data.ma_kb_huyen.split(',') : null;
    
    if (arrMaKBHuyen != null){
        jQuery("#ma_kb_huyen_list").val(arrMaKBHuyen);
//        jQuery.each(arrMaKBHuyen, function (i, idHuyen) {
//            jQuery("#kb_id option[value='" + idHuyen + "']").attr('selected', 'selected');
//           
//        });
    }
    
    var minutes = null;
    if(newTime.getMinutes() < 10){
      minutes = "0"+newTime.getMinutes()
    }else{
      minutes = newTime.getMinutes()
    }
    
//    var arrListMaKBHuyen = (data.list_ma_nh_kb_huyen)//20150804
    //ManhNV-20150420    
    if(arrMaKBHuyen == null || arrMaKBHuyen == 'undefined'){      
      jQuery("#fieldset_ds_kb_noi_gio").hide();
    }else{
      jQuery("#fieldset_ds_kb_noi_gio").show();
    }
    //**************
    var arrMaNH = (data.ma_nh) ? data.ma_nh.split(',') : null;
    var arrTenKB = (data.ten_kb) ? data.ten_kb.split('@ttsp@') : null;
    var arrCOTCu = (data.cot_cu_list) ? data.cot_cu_list.split(',') : null;
    var maxTime = 0;
//20150804-begin
    jQuery("#tableKBHuyen").find("tr:gt(0)").remove();
      if(arrMaKBHuyen != null){
        for(var i=0; i < arrMaKBHuyen.length; i++){            
              $("#tableKBHuyen").append(
              "<tr>" +
              "<td align='center'>"+arrMaKBHuyen[i]+"</td>" +
              "<td>"+arrTenKB[i]+"</td>" +
              "<td align='center'>"+arrMaNH[i]+"</td>" +
              "<td align='center'>"+arrCOTCu[i]+"</td>" +
              "<td align='center'>"+newTime.getHours()+":"+minutes+"</td>" +
              "</tr>");
              var time = arrCOTCu[i].split(":");
              var temp = time[0]+time[1];
              if(maxTime<temp){
                maxTime = temp;
              }
        };
    }

//    if(arrListMaKBHuyen != null){
//        jQuery.each(arrListMaKBHuyen, function (i, maKBHuyen) {
//              $("#tableKBHuyen").append(
//              "<tr>" +
//              "<td align='center'>"+maKBHuyen.ma_nh_kb+"</td>" +
//              "<td>"+maKBHuyen.ten_nh_kb+"</td>" +
//              "<td align='center'>"+maKBHuyen.ma_nh+"</td>" +
//              "<td align='center'>"+maKBHuyen.cut_of_time+"</td>" +
//              "<td align='center'>"+newTime.getHours()+":"+minutes+"</td>" +
//              "</tr>");
//              var time = maKBHuyen.cut_of_time.split(":");
//              var temp = time[0]+time[1];
//              if(maxTime<temp){
//                maxTime = maKBHuyen.cut_of_time;
//              }
//        });
//    }
//20150804-end
    jQuery("#maxTime").val(maxTime);
    jQuery("#ten_nguoi_lap").val(data.ten_nguoi_lap);
    //alert(data.ngay_lap);
    jQuery("#ngay_lap").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_lap)));
    //jQuery("#ngay_lap").val(data.ngay_lap);
    jQuery("#ten_nguoi_ks").val(data.ten_nguoi_ks);
    if (data.ngay_ks != null && data.ngay_ks != '')
        jQuery("#ngay_ks").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_ks)));
        
    jQuery("#ngay_cot").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_lap)));
    if (data.loai_cot == '01') {
        if (data.tgian_cot_cu != null && data.tgian_cot_cu != '') {
            jQuery("#gio_cot_moi").timepicker('setTime', new Date(data.tgian_cot_cu));
            jQuery("#gio_cot_cu").val(jQuery("#gio_cot_moi").timepicker('getTime'));
        }
    }
    jQuery('#gio_cot_moi').timepicker('setTime', newTime);
   
    jQuery('#mo_ta_trang_thai').html(data.rv_meaning);
    jQuery('#mo_ta_trang_thai2').html(data.rv_meaning);
    jQuery("#lydo_cot").val(data.lydo_cot);
    jQuery("#lydo_daylai").val(data.lydo_daylai);
    jQuery("#rowSelected").val(tr_id_selected);
    jQuery("#trang_thai").val(data.trang_thai);
    jQuery("#kb_id").multiselect('refresh');
    jQuery("#ma_kb_tinh").multiselect('refresh');
    //manhnv-20150424    
    jQuery("#tn_cu").val(data.tn_cu);
    jQuery("#tn_moi").val(data.tn_moi);
    if(jQuery("#loai_cot").val() == '02'){          
            jQuery("#span_tn_cu").show();
            jQuery("#span_tn_moi").show();
            jQuery("#span_gio_cot_moi").hide();
            jQuery("#span_gio_cot_cu").hide();
    }else{
            jQuery("#span_tn_cu").hide();
            jQuery("#span_tn_moi").hide();
            jQuery("#span_gio_cot_moi").show();
            jQuery("#span_gio_cot_cu").show();    
    }
    //manhnv-20150424

}
//********************** GET DATA CHO form detail ***********************************
function fillDataDTS(id, tr_id, chucdanh, enableFocus) {
    resetFormCOT();
    if (id != undefined && id != 'undefined')
        jQuery.ajax( {
            type : "POST", url : "checkstatebucot.do", async : true, data :  {
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
                            enableButtonCOT(chucdanh, data.trang_thai);
                            fillDetailToFormCOT(data, tr_id);
                            if (chucdanh.indexOf('CBPT-TTTT') >= 0) {
                                if (data.trang_thai == '02' && enableFocus) {
                                    jQuery("#lydo_daylai").removeAttr('disabled');
                                    jQuery("#lydo_daylai").focus();
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
    else{
      jQuery("#ma_kb_tinh option").each(function (){
      jQuery(this).attr({disabled:true});
      jQuery("#ma_kb_tinh").multiselect('refresh');
    });
    }

    if (chucdanh.indexOf('CB-TTTT') >= 0)
        jQuery("#btn_Themmoi").show();
}

//********************** CLICK NUT btn_sua ***********************************  
function btnSuaClick() {
    jQuery("#ma_nh").removeAttr("disabled");
    jQuery("#gio_cot_moi").focus();  
    if(jQuery("#loai_cot").val()=='01'){
      jQuery("#ma_kb_tinh option").each(function (){
        jQuery(this).removeAttr("disabled");
      });
      jQuery("#kb_id option").each(function (){
        jQuery(this).removeAttr("disabled");
      });
    }
    jQuery("#loai_cot").attr({"disabled" : true});
    jQuery("#lydo_cot").attr( {"disabled" : false});
    jQuery("#btn_ghi").show();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_huy").hide();
    jQuery("#btn_sua").hide();
    jQuery("#btn_Themmoi").hide();
    jQuery("#ma_kb_tinh").multiselect("refresh");
    jQuery("#kb_id").multiselect("refresh");
}
//********************** CLICK NUT btn_DAYLAI *********************************** 
function btnDayLaiClick() {
    jQuery("#lydo_daylai").removeAttr("disabled");
    jQuery("#lydo_daylai").focus();
    jQuery("#btn_ghi").show();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_duyet").hide();
    jQuery("#tra_cuu_COT").hide();
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
    jQuery("#btn_Themmoi").hide();
}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * @param chucdanh
   * @param trang_thai
   */
function enableButtonCOT(chucdanh, trang_thai) {
    jQuery("#btn_ghi").hide();
    jQuery("#btn_timKiem").show();
    if (chucdanh == null || chucdanh == '') {
        disableButton();
    }
    else if (chucdanh != null && chucdanh != 'null') {
        if (chucdanh.indexOf("CB-TTTT") >= 0 && chucdanh.indexOf("CBPT-TTTT") ==  - 1) {
            //span sua,ghi,huy,timkiem,thoat
            if (trang_thai == '00') {
                jQuery("#btn_sua").show();
                jQuery("#btn_huy").show();
                jQuery("#btn_duyet").hide();
                jQuery("#btn_dayLai").hide();
            }else {
                disableButton();
            }
            jQuery("#btn_Themmoi").show();
        }
        else if (chucdanh.indexOf("CBPT-TTTT") >= 0 && chucdanh.indexOf("CB-TTTT") ==  - 1) {
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
        else if (chucdanh.indexOf("CBPT-TTTT") >= 0 && chucdanh.indexOf("CB-TTTT") >= 0) {
            if (trang_thai == '02') {
                jQuery("#btn_sua").show();
                jQuery("#btn_huy").show();
                jQuery("#btn_duyet").show();
                jQuery("#btn_dayLai").show();
             }
            else {
                disableButton();
            }
            jQuery("#btn_Themmoi").show();
        }
        else {
            disableButton();
        }
    }
}
//********************** Tim kiem dien tra soat***********************************   
function findCOT(contextRoot, chucdanh) {
    var dialogId = jQuery("#dialog-form ~ .ui-dialog-buttonpane button"), maTTV = jQuery("#ma_ttvnhan").val(), nh_kb_nhan = jQuery("#nh_kb_nhan").val(), so_COT = jQuery("#so_COT").val(), so_lenh_tt = jQuery("#so_lenh_tt").val();
    jQuery(dialogId[0]).button("disable");
    jQuery.ajax( {
        type : "POST", url : "COToatlttdiView.do", data :  {
            "ma_ttv_nhan" : maTTV, "ma_don_vi_nhan_tra_soat" : nh_kb_nhan, "COT_id" : so_COT, "ltt_id" : so_lenh_tt, "action" : 'FIND', "nd" : Math.random() * 100000
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
                            resetFormCOT();
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
function fillDataTableCOT(data, contextRoot, chucdanh) {
    var strTableData = "";
    jQuery("#data-grid").html('');
    if (data != null && data != '')
        jQuery.each(data, function (i, objectCOT) {
            strTableData += "<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_" + i + "\" ondblclick=\"rowSelectedFocus('row_dts_" + i + "');\" onclick=\"rowSelectedFocus('row_dts_" + i + "');fillDataDTS('" + objectCOT.id + "','row_dts_" + i + "','" + chucdanh + "',true);\">";
            strTableData += "<td id=\"td_dts_" + i + "\" width=\"121px;\" align=\"center\">";
            strTableData += "<input name=\"row_item\" id=\"" + i + "\" type=\"text\" style=\"border:0px;font-size:10px;float:left;width:121px;\" value=\"" + objectCOT.id + "\" onkeydown=\"arrowUpDown(event)\" readonly=\"true\"/></td><td align=\"center\">";
            if (objectCOT.trang_thai == '00') {
                strTableData += "<img src=" + contextRoot + "/styles/images/return.jpeg title=\"KTT &#273;&#7849;y l&#7841;i\"/>";
            }else if (objectCOT.trang_thai == '02') {
                strTableData += "<img src=" + contextRoot + "/styles/images/wait.jpeg title=\"Ch&#7901; duy&#7879;t\"/>";
            }
            else if (objectCOT.trang_thai == '03') {
                strTableData += "<img src=" + contextRoot + "/styles/images/active.gif title=\"&#272;&#227; duy&#7879;t\"/>";
            }
            else if (objectCOT.trang_thai == '04') {
                strTableData += "<img src=" + contextRoot + "/styles/images/delete1.gif title=\"&#272;&#227; h&#7911;y\"/>";
            }
            else if (objectCOT.trang_thai == '18') {
                strTableData += "<img src=" + contextRoot + "/styles/images/send-success.jpg title=\"G&#7917;i NH th&#224;nh c&#244;ng\"/>";
            }
            else if (objectCOT.trang_thai == '19') {
                strTableData += "<img src=" + contextRoot + "/styles/images/sended-false.jpg title=\"G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng\"/>";
            }
            else if (objectCOT.trang_thai == '08') {
                strTableData += "<img src=" + contextRoot + "/styles/images/dong_y.gif title=\"NH &#273;&#7891;ng &#253;\"/>";
            }
            else if (objectCOT.trang_thai == '09') {
                strTableData += "<img src=" + contextRoot + "/styles/images/active.gif title=\"NH &#273;&#7891;ng &#253;-kh&#244;ng h&#7907;p l&#7879;\"/>";
            }
            else if (objectCOT.trang_thai == '10') {
                strTableData += "<img src=" + contextRoot + "/styles/images/delete-icon.png title=\"NH kh&#244;ng &#273;&#7891;ng &#253;\"/>";
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
    jQuery("#tong_ban_ghi").html('T&#7893;ng s&#7889;: ' + data.length + ' &#273;i&#7879;n COT');
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
        type : "POST", url : "CutOffTimeKBView.do", data :  {
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
                        fillDataTableCOT(data, contextRoot, chucdanh);
                        disableButton();
                        defaultRowSelected(chucdanh);
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
        resetFormCOT();
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

function checkCreateCOT() {
    var currentTime;
    var strCurrentTime;
    var resp = true;
    jQuery.ajax( {
        type : "POST", url : "url.do", dataType : 'json', async : false, success : function (data, textstatus) {
        },
        error : function (xhr, status, error) {
            strCurrentTime = xhr.getResponseHeader('date');
            currentTime = new Date(Date.parse(strCurrentTime));
        }
    });

    var oldTime = new Date(strCurrentTime);
    if (jQuery("#loai_cot").val() == '00') {
        objCOT.filter(function (item) {
            if (item.ma_nh.indexOf(jQuery("#ma_nh").val()) >= 0) {
                var time = item.cut_of_time.split(':');
                oldTime.setHours(time[0]);
                oldTime.setMinutes(time[1]);
                oldTime.setSeconds(0);
                oldTime.setMilliseconds(0);
                var milli = currentTime - oldTime;
                if (milli > 1800000) {
                    resp = false;
                    return false;
                }
            }
        });
    }
    else if (jQuery("#loai_cot").val() == '01') {
        var arrMaNHKB = jQuery("#kb_id").val();
        jQuery.each(arrMaNHKB, function (i, value) {
            objCOT.filter(function (item) {
                if (item.ma_nh.indexOf(jQuery("#ma_nh").val()) >= 0 && item.ma_nh_kb == value) {
                    var time = item.cut_of_time.split(':');
                    oldTime.setHours(time[0]);
                    oldTime.setMinutes(time[1]);
                    oldTime.setSeconds(0);
                    oldTime.setMilliseconds(0);
                    var milli = currentTime - oldTime;
                    if (milli > 1800000) {
                        resp = false
                        return false;
                    }
                }
            });
        });
    }
    return resp;
}