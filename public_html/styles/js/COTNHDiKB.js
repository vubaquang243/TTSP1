
//************************************ RESET FROM **********************************
function resetFormCOT() {
    jQuery("#cot_id").val('');
    jQuery("#ma_nh").val("");
    jQuery("#loai_cot").val("");
    jQuery("#ma_kb_tinh").val("");
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
    jQuery("#lydo_cot_nh").attr( {disabled : "disabled", value : ""});
    jQuery("#dong_y").attr( {disabled : "disabled", value : ""});
    jQuery("#lydo_cot_kb").attr( {disabled : "disabled", value : ""});
    jQuery("#lydo_ko_dongy").attr( {disabled : "disabled", value : ""});
    jQuery("#gio_cot_moi_kb").val('');
   
}
//************************************ DAY DU LIEU VAO FROM **********************************  
function fillDetailToFormCOT(data, tr_id_selected) {
    jQuery("#id").val(data.id);
    jQuery("#cot_id").val(data.cot_id);
    jQuery("#ma_nh").val(data.nhkb_chuyen);
    jQuery("#loai_cot").val(data.loai_cot);
    jQuery("#ten_nguoi_lap").val(data.nguoi_lap_nh);
    jQuery("#ngay_lap").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_lap_nh)));
    jQuery("#ten_nguoi_ks").val(data.nguoi_ks_nh);
    jQuery("#ngay_ks").val(jQuery.datepicker.formatDate('dd/mm/yy', new Date(data.ngay_ks_nh)));
    var newTime = new Date(data.tgian_cot_moi);
    if(data.tgian_cot_moi!=null && data.tgian_cot_moi!=''){
      jQuery("#ngay_cot").val(jQuery.datepicker.formatDate('dd/mm/yy',  newTime));
      if (data.loai_cot == '00') {
      jQuery("#gio_cot_moi").val(data.cot_moi);
      jQuery("#gio_cot_cu").val(data.cot_cu);
//          jQuery("#gio_cot_moi").timepicker('setTime', new Date(data.tgian_cot_moi));
//          jQuery("#gio_cot_cu").timepicker('setTime', new Date(data.tgian_cot_moi));
//          jQuery("#gio_cot_cu").val(jQuery("#gio_cot_moi").timepicker('getTime'));
      }else{
        jQuery("#gio_cot_moi").val('');
        jQuery("#gio_cot_cu").val('');
      } 
//      jQuery("#gio_cot_moi_kb").timepicker('setTime', newTime);
//      jQuery('#gio_cot_moi').timepicker('setTime', newTime);
    }
    if(data.loai_cot == '02'){
      if(data.tn_moi!=null && data.tn_moi!=''){
        jQuery('#tn_moi').val(data.tn_moi);
      }
      if(data.tn_cu!=null && data.tn_cu!=''){
        jQuery('#tn_cu').val(data.tn_cu);
      }
      jQuery('#span_label_gio_tn_cu').show();
      jQuery('#span_tn_cu').show();
      jQuery('#span_label_gio_tn_moi').show();
      jQuery('#span_tn_moi').show();
      
      jQuery('#span_gio_cot_cu').hide();
      jQuery('#span_label_gio_cot_cu').hide();      
      jQuery('#span_label_gio_cot_moi').hide();
      jQuery('#span_gio_cot_moi').hide();
    }else{
      jQuery('#span_label_gio_tn_cu').hide();
      jQuery('#span_tn_cu').hide();
      jQuery('#span_label_gio_tn_moi').hide();
      jQuery('#span_tn_moi').hide();
      
      jQuery('#span_gio_cot_cu').show();
      jQuery('#span_label_gio_cot_cu').show();      
      jQuery('#span_label_gio_cot_moi').show();
      jQuery('#span_gio_cot_moi').show();
    }
    var minutes = null;
    if(newTime.getMinutes() < 10)
      minutes = "0"+newTime.getMinutes()
    else
      minutes = newTime.getMinutes()
//BEGIN-20150806     
    jQuery("#tableKBHuyen").find("tr:gt(0)").remove();
      var arrMaKBHuyen  = (data.ma_kb_huyen) ? data.ma_kb_huyen.split(',') : null;
      var arrMaNH = (data.ma_nh) ? data.ma_nh.split(',') : null;
      var arrTenKB = (data.ten_kb) ? data.ten_kb.split('@ttsp@') : null;
      var arrCOTCu = (data.cot_cu_list) ? data.cot_cu_list.split(',') : null;
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
        };
    }
//    var arrListMaKBHuyen = (data.list_ma_nh_kb_huyen)
//    jQuery("#tableKBHuyen").find("tr:gt(0)").remove();
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
//        });
//    }
//END-20150806
    jQuery('#mo_ta_trang_thai').html(data.rv_meaning);
    jQuery("#lydo_cot_nh").val(data.lydo_cot_nh);
    jQuery("#lydo_cot_kb").val(data.lydo_traloi);
    jQuery("#lydo_ko_dongy").val(data.lydo_ko_dongy);
    if(data.trang_thai=='05'){
      jQuery("#lydo_ko_dongy").attr({"disabled":false});
    }else
      jQuery("#lydo_ko_dongy").attr({"disabled":true});
    
    if(data.trang_thai=='08'|| data.trang_thai=='09'){
      jQuery("#lydo_daylai").attr({"disabled":true});
    }else{
      jQuery("#lydo_daylai").attr({"disabled":false});
    }
    jQuery("#lydo_daylai").val(data.lydo_daylai);
    jQuery("#rowSelected").val(tr_id_selected);
    jQuery("#trang_thai").val(data.trang_thai);
    if(data.ma_kb_huyen!=null && data.ma_kb_huyen!=''){
//     var ma_kb_huyen = jQuery("#ma_kb_huyen");
//     var options = ma_kb_huyen.attr('options');
//     options[0]=new Option(data.ten_kb_huyen, data.ma_kb_huyen);   
      jQuery("#ma_kb_huyen").html('');
      var arrMaKBHuyen=data.ma_kb_huyen.split(',');
      jQuery.each(arrMaKBHuyen, function (i, idhuyen) {
        obj.filter(function(item) {
              if(item.ma_nh == arrMaKBHuyen[i] && item.ma_dv==jQuery("#ma_nh").val()){
                 var opt = jQuery('<option />', {value: item.ma_nh,text: item.ten,disabled:'disabled',selected:'selected'}); 
                 opt.appendTo(jQuery("#ma_kb_huyen"));
              }
             });
      });          
      jQuery("#ma_kb_huyen option").each(function (){
        jQuery(this).attr('disabled','disabled');
      });
      jQuery("#ma_kb_huyen").multiselect('refresh'); 
    }
    jQuery("#dong_y").val(data.dong_y);
    if(data.trang_thai=='05')
      jQuery("#lydo_cot_kb").attr({"disabled":false,value:data.lydo_cot});
    else
      jQuery("#lydo_cot_kb").attr({"disabled":true,value:data.lydo_cot});

}
//********************** GET DATA CHO form detail ***********************************
function fillDataDTS(id, tr_id, chucdanh, enableFocus) {
//    resetFormCOT();
    var status;
    jQuery.ajax( {
        type : "POST", url : "thongtinyccotnh.do", async : false, data :  {
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
                        status=data.trang_thai;
                    }
                }
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.messge);
            jQuery("#dialog-message").dialog("open");
        }

    });
    
    if(chucdanh.indexOf("CB-TTTT")>=0){
          jQuery("#cbpttttt").hide();
          jQuery("#cbpttttt1").hide();
          jQuery("#lydo_daylai").attr({disabled:true});
       }else if (chucdanh.indexOf("CBPT-TTTT") >= 0 ){
          if(status!='07')
            jQuery("#cbtttt").hide();
          else
            jQuery("#cbtttt").show();         
      }

}


//********************** CLICK NUT btn_DAYLAI *********************************** 
function btnDayLaiClick() {
    jQuery("#lydo_daylai").removeAttr("disabled");
    jQuery("#lydo_daylai").focus();
    jQuery("#btn_dayLai").hide();
    jQuery("#btn_duyet").hide();
    jQuery("#dongy").hide();
    jQuery("#khongdongy").hide();

}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * 
   */
function disableButton() {
    jQuery("#btn_duyet").hide();
//    jQuery("#btn_dayLai").hide();
//    jQuery("#btn_dongy").hide();
    jQuery("#btn_khongdongy").hide();
}
//********************** ROLE USER SHOW HIDE BUTTON*********************************** 
/**
   * @param chucdanh
   * @param trang_thai
   */
function enableButtonCOT(chucdanh, trang_thai) {
    
    if (chucdanh != null && chucdanh != 'null') {
//      if (chucdanh.indexOf("CB-TTTT") >= 0) {
//            //span sua,ghi,huy,timkiem,thoat
//        if (trang_thai == '05') {
////          jQuery("#btn_dongy").show();
//          jQuery("#btn_khongdongy").show();
//          jQuery("#btn_duyet").show();
////          jQuery("#btn_dayLai").hide();
//        } else {
//            disableButton();
//        }
//      }else 
      if (chucdanh.indexOf("CBPT-TTTT") >= 0) {
        if (trang_thai == '05') {
          jQuery("#btn_duyet").show();
//          jQuery("#btn_dayLai").show();
//          jQuery("#btn_dongy").hide();
          jQuery("#btn_khongdongy").show();
        } else {
          disableButton();
        }
      }else {
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
                        }else { 
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
            if (objectCOT.trang_thai == '09') {
                strTableData += "<img src=" + contextRoot + "/styles/images/return.jpeg title=\"&#272;&#7849;y l&#7841;i\"/>";
            }
            else if (objectCOT.trang_thai == '05') {
                strTableData += "<img src=" + contextRoot + "/styles/images/wait.jpeg title=\"Ch&#7901; &#273;&#7891;ng &#253;\"/>";
            }
            else if (objectCOT.trang_thai == '06') {
                strTableData += "<img src=" + contextRoot + "/styles/images/active.gif title=\"&#272;&#227; &#273;&#7891;ng &#253;\"/>";
            }
            else if (objectCOT.trang_thai == '07') {
                strTableData += "<img src=" + contextRoot + "/styles/images/sended-false.jpeg title=\"Kh&#244;ng &#273;&#7891;ng &#253;\"/>";
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
        type : "POST", url : "ycnoigioNH.do", data :  {
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