//************************************ RESET FROM **********************************
function resetFormDTSTraCuu() {
    jQuery("#ma_don_vi_tra_soat").attr( {
        maxlength : 8
    });
    jQuery("#ma_don_vi_nhan_tra_soat").attr( {
        maxlength : 8
    });
}
//********************** fill form detail ***********************************
function fillDataDTS(id, tr_id, chuc_danh) {
//    resetFormDTS();
    jQuery.ajax( {
        type : "POST", url : "filldatadtsoatdetail.do", data :  {
            "id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (data != null) {
                if (textstatus != null && textstatus == 'success') {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {
                        jQuery("#trang_thai").val(data.trang_thai);
                        jQuery("#id").val(data.id);
                        jQuery("#dts_id").val(data.dts_id);
                        jQuery("#ltt_id").val(data.ltt_id);
                        jQuery("#ngay_thanh_toan").val(data.ngay_thanh_toan);
                        jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                        jQuery("#ma_ktt").val(data.ma_ktt);
                        jQuery("#ngay_duyet").val(data.ngay_duyet);
                        jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                        jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                        jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
                        jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                        jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
                        jQuery("#ttv_nhan").val(data.ttv_nhan);
                        jQuery("#ngay_nhan").val(data.ngay_nhan);
                        jQuery("#noi_dung").val(data.noidung);
                        jQuery("#noidung_traloi").val(data.noidung_traloi);
                        jQuery("#thong_tin_khac").val(data.thong_tin_khac);
                        jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                        jQuery("#nguoi_nhap_nh").val(data.nguoi_nhap_nh);
                        jQuery("#ngay_nhap_nh").val(data.ngay_nhap_nh);
                        jQuery("#nguoi_ks_nh").val(data.nguoi_ks_nh);
                        jQuery("#ngay_ks_nh").val(data.ngay_ks_nh);
                        jQuery("#mo_ta_trang_thai").html(data.mo_ta_trang_thai);
                        jQuery("#rowSelected").val(tr_id);
                        enableButtonDTS(chuc_danh, data.trang_thai);
                        if (data.ltt_id != null && data.ltt_id != '')
                            jQuery("#btn_xemLTT").show();
                    }
                }
            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
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