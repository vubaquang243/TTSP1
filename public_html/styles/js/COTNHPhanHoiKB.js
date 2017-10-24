//************************************ RESET FROM **********************************
function resetFormCOT() {
    jQuery("#ma_nh").val("");
    jQuery("#loai_cot").val("");
   jQuery("#ma_kb_tinh").attr('disabled',"disabled");
    jQuery("#ma_kb_tinh").multiselect('refresh');
    jQuery("#kb_id").attr('disabled',"disabled");
    jQuery("#kb_id").html('');
    jQuery("#kb_id").multiselect('refresh');     
    jQuery("#ma_kb_huyen").val("");
    jQuery("#ten_nguoi_lap").val("");
    jQuery("#ngay_lap").val("");
    jQuery("#nguoi_ks").val("");
    jQuery("#ngay_ks").val("");
    jQuery("#ngay_cot").val("");
    jQuery("#gio_cot_cu").val("");
    jQuery("#gio_cot_moi").val("");
    jQuery("#mo_ta_trang_thai").html("");
    jQuery("#trang_thai").val('');
    jQuery("#rowSelected").val('');
    jQuery("#idFieldFocus").val('');
    jQuery("#lydo_cot").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#lydo_daylai").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#gio_cot_moi_nh").val('');
    jQuery("#lydo_cot_nh").val('');
    jQuery("#ma_nh").val('');
    jQuery("#rowSelected").val('');
    jQuery("#idFieldFocus").val('');
    jQuery("#lydo_cot").attr( {
        disabled : "disabled", value : ""
    });
    jQuery("#lydo_daylai").attr( {
        disabled : "disabled", value : ""
    });

}

function fillDetailToFormPhanHoi(idVal, chucdanh) {
    var cotid = null;
    jQuery.ajax( {
        type : "POST", url : "diencotphanhoiNH.do", data :  {
            "id" : idVal, "nd" : Math.random() * 100000
        },
        dataType : 'json', async : false, success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                enableButtonCOT(chucdanh, data.trang_thai);
                if (data.tgian_cot_moi != null && data.tgian_cot_moi != '') {
                    jQuery("#cot_nh_id").val(data.id);
                    jQuery("#trang_thai").val(data.trang_thai);
                    jQuery("#lydo_cot_nh").val(data.lydo_cot_nh);
                    jQuery("#dong_y").val(data.dong_y);
                    var newTime = new Date(data.tgian_cot_moi);
                    jQuery("#gio_cot_moi_nh").timepicker('setTime', newTime);
                    jQuery('#mo_ta_trang_thai').html(data.rv_meaning);
                    cotid = data.cot_id;
                }
            }
        },
        error : function (xhr, status, error) {
        }
    });

    return cotid;

}
//************************************ DAY DU LIEU VAO FROM **********************************  
function fillDetailToFormCOT(data, tr_id_selected) {
    jQuery("#id").val(data.id);
    jQuery("#ma_nh").val(data.nhkb_nhan);
    jQuery("#loai_cot").val(data.loai_cot);
     jQuery("#ma_kb_tinh option").each(function (){
      jQuery(this).removeAttr('selected');
      jQuery(this).attr({disabled:true});
    });
    if(data.ma_kb_tinh!=null){
      var arrMaKBTinh = data.ma_kb_tinh.split(',');
      jQuery("#ma_kb_tinh option").each(function () {
          jQuery(this).attr('disabled', 'disabled');
      });
       jQuery("#ma_kb_tinh").val(arrMaKBTinh);
      jQuery.each(arrMaKBTinh, function (i, idTinh) {
          var optgrp = jQuery('<optgroup>');
          optgrp.attr('label', jQuery("#ma_kb_tinh option[value='" + idTinh + "']").text());
          obj.filter(function (item) {
              if (item.id_cha === idTinh) {
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
    jQuery("#ma_kb_tinh").multiselect('refresh');
    jQuery("#kb_id").multiselect('refresh');

    //      jQuery.each(arrMaKBTinh, function (i, idTinh) {
    //       jQuery("#ma_kb_tinh option[value='"+idTinh+"']").attr({selected:'selected'});
    //      });
    //   
    //    if(data.ma_kb_tinh!=null && data.ma_kb_tinh!='')
    //    jQuery.ajax( {
    //        type : "POST", url : "kbhuyenlist.do", data :  {
    //            "kb_id" : data.ma_kb_tinh, "nd" : Math.random() * 100000
    //        },
    //        dataType : 'json', async : true, success : function (listkbhuyen, textstatus) {
    //            if (textstatus != null && textstatus == 'success') {
    //                var ma_kb_huyen = jQuery("#kb_id");
    //                var options = ma_kb_huyen.attr('options');
    //                jQuery("#kb_id").html('');
    //                options[0] = new Option(GetUnicode('--- Ch&#7885;n KB huy&#7879;n----'), '')
    //                jQuery.each(listkbhuyen, function (i, itemkbhuyen) {
    //                    options[i + 1] = new Option(itemkbhuyen.kb_huyen, itemkbhuyen.ma_nh);
    //                });
    //                jQuery("#kb_id").val(data.ma_kb_huyen);
    //            }
    //        },
    //        error : function (textstatus) {
    //            jQuery("#dialog-message").html(textstatus.messge);
    //            jQuery("#dialog-message").dialog("open");
    //        }
    //    });
    jQuery("#ten_nguoi_lap").val(data.ten_nguoi_lap);
    jQuery("#ngay_lap").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_lap)));
    jQuery("#ten_nguoi_ks").val(data.ten_nguoi_ks);
    jQuery("#ngay_ks").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_ks)));
    var newTime = new Date(data.tgian_cot_moi);
    jQuery("#ngay_cot").val(jQuery.datepicker.formatDate('dd/mm/yy', newTime));
    if (data.loai_cot == '01') {
        jQuery("#gio_cot_moi").timepicker('setTime', new Date(data.tgian_cot_cu));
        jQuery("#gio_cot_cu").val(jQuery("#gio_cot_moi").timepicker('getTime'));
    }
    var minutes = null;
    if(newTime.getMinutes() < 10)
      minutes = "0"+newTime.getMinutes()
    else
      minutes = newTime.getMinutes()
    var arrListMaKBHuyen = (data.list_ma_nh_kb_huyen)
    jQuery("#tableKBHuyen").find("tr:gt(0)").remove();
    if(arrListMaKBHuyen != null){
        jQuery.each(arrListMaKBHuyen, function (i, maKBHuyen) {
              $("#tableKBHuyen").append(
              "<tr>" +
              "<td align='center'>"+maKBHuyen.ma_nh_kb+"</td>" +
              "<td>"+maKBHuyen.ten_nh_kb+"</td>" +
              "<td align='center'>"+maKBHuyen.ma_nh+"</td>" +
              "<td align='center'>"+maKBHuyen.cut_of_time+"</td>" +
              "<td align='center'>"+newTime.getHours()+":"+minutes+"</td>" +
              "</tr>");
        });
    }
    
    jQuery('#gio_cot_moi').timepicker('setTime', newTime);
    jQuery("#lydo_cot").val(data.lydo_cot);
    jQuery("#lydo_daylai").val(data.lydo_daylai);
    jQuery("#rowSelected").val(tr_id_selected);

}
//********************** GET DATA CHO form detail ***********************************
function fillDataDTS(id, tr_id, chucdanh, enableFocus) {
    resetFormCOT();
    var cot_id = fillDetailToFormPhanHoi(id, chucdanh);
    //    alert(cot_id);
    jQuery.ajax( {
        type : "POST", url : "checkstatebucot.do", async : false, data :  {
            "id" : cot_id, "action" : 'fillDataForm', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.ERROR != undefined) {
                        jQuery("#dialog-message").html(data.ERROR);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.ERROR == undefined) {
                        fillDetailToFormCOT(data, tr_id);

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
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * 
   */
function disableButton() {
    jQuery("#btn_xacnhan").hide();
}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * @param chucdanh
   * @param trang_thai
   */
function enableButtonCOT(chucdanh, trang_thai) {
    if (chucdanh == null || chucdanh == '') {
        disableButton();
    }
    else if (chucdanh != null && chucdanh != 'null') {
        if (trang_thai == '10') {
            jQuery("#btn_xacnhan").show();
        }
        else {
            disableButton();
        }
    }
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
            if (objectCOT.trang_thai == '10') {
                strTableData += "<img src=" + contextRoot + "/styles/images/wait.jpeg title=\"Ch&#7901; x&#225;c nh&#7853;n\"/>";
            }
            else if (objectCOT.trang_thai == '11') {
                strTableData += "<img src=" + contextRoot + "/styles/images/active.gif title=\"&#272;&#227; x&#225;c nh&#7853;n\"/>";
            }
            else if (objectCOT.trang_thai == '12') {
                strTableData += "<img src=" + contextRoot + "/styles/images/delete1.gif title=\"&#272;&#227; x&#225;c nh&#7853;n\"/>";
            }
            else if (objectCOT.trang_thai == '18') {
                strTableData += "<img src=" + contextRoot + "/styles/images/send-success.jpg title=\"G&#7917;i NH th&#224;nh c&#244;ng\"/>";
            }
            else if (objectCOT.trang_thai == '19') {
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
        type : "POST", url : "PhanHoiCOTNH.do", data :  {
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