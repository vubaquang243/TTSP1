function findDC() {
    document.forms[0].action = "DCNoiBoListAction.do";
    document.forms[0].submit();
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
function rowSelectedForUpdateTrangThai(id,trangthai,tt_dong_mo){
    if(trangthai=='02' && tt_dong_mo=='M'){
      jQuery("#idForUpdate").val(id);
      var rowSelected = jQuery("#rowSelected").val();
      if (rowSelected != null && "" != rowSelected) {
          jQuery("#" + jQuery("#rowSelected").val()).removeAttr("style");
      }
      jQuery("#rowSelected").val("trSelected_" + id);
      jQuery("#trSelected_" + id).attr( {
          "style" : "BACKGROUND-COLOR:#ffffb5"
      });
      jQuery.ajax( {
        type : "POST", url : "DCNoiBoViewAction.do", data :  {
            "id" : id,async: true, "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                      jQuery("#select_tt").val(data.trang_thai_dong_mo);
                      jQuery("#ly_do_dong_mo_update").val(data.ly_do_dong);
                    }
                }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus);
            jQuery("#dialog-message").dialog("open");
        }
      });
      jQuery("#dialog_form_update_lydo").dialog("open");
    }
}
function rowSelectedFocus(id,trangthai) {
    var rowSelected = jQuery("#rowSelected").val();
    if (rowSelected != null && "" != rowSelected) {
        jQuery("#" + jQuery("#rowSelected").val()).removeAttr("style");
    }
    jQuery("#rowSelected").val("trSelected_" + id);
    jQuery("#trSelected_" + id).attr( {
        "style" : "BACKGROUND-COLOR:#ffffb5"
    });
    jQuery.ajax( {
        type : "POST", url : "DCNoiBoViewAction.do", data :  {
            "id" : id,async: true, "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                      //dc chenh lech - 02
                      if(trangthai=='02'){
                      jQuery("#thu_sgd_ctiet").val(data.so_tien_bao_no_sgd);
                      jQuery("#chi_sgd_ctiet").val(data.so_tien_bao_co_sgd);
                      jQuery("#thu_dv_ctiet").val(data.ket_chuyen_thu_kb);
                      jQuery("#chi_dv_ctiet").val(data.ket_chuyen_chi_kb);
                      }
                      // chua thuc hien 03
                      else if(trangthai=='03'){
                      jQuery("#ttdchieu_lan1_ctiet").val(data.trang_thai);
                      }
                    }
                }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus);
            jQuery("#dialog-message").dialog("open");
        }
    });
    //dc chenh lech - 02
    if(trangthai=='02')
    jQuery("#dialog-form").dialog("open");
    // chua thuc hien 03
    else if(trangthai=='03')
    jQuery("#dialog_form_cth").dialog("open");
}