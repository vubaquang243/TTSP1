//Lenh thanh toan Di
function checkInput(actionForButtonGhi, ttloai_lenh) {
    var result = true;
    //var trangThai = jQuery("#trang_thai").val();
    var nhan = jQuery("#nhan").val();
    if (nhan != 'Y') {
        if (jQuery("#so_ct_goc").val() == null || jQuery("#so_ct_goc").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p S&#7889; ch&#7913;ng t&#7915; g&#7889;c'));
            jQuery("#so_ct_goc").focus();
            return false;
        }
        if (jQuery("#so_yctt").val() == null || jQuery("#so_yctt").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p S&#7889; y&#234;u c&#7847;u thanh to&#225;n'));
            jQuery("#so_yctt").focus();
            return false;
        }
        else if (!validSoYctt(jQuery("#so_yctt").val())) {
            jQuery("#dialog-confirm").dialog("close");
            //alert("S? Yêu c?u thanh toán ch?a ?úng.\n \n??nh d?ng ?úng ph?i là: N_YYMMDD_N...(ID nhan vien)_N...(Sequence)");
            alert(GetUnicode('S&#7889; y&#234;u c&#7847;u thanh to&#225;n ch&#432;a &#273;&#250;ng.\n \n &#272;&#7883;nh d&#7841;ng &#273;&#250;ng ph&#7843;i l&#224;: N_YYMMDD_N...(ID nhan vien)_N...(Sequence)'));
            jQuery("#so_yctt").focus();
            return false;
        }
        if (jQuery("#ngay_ct").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p Ng&#224;y ch&#7913;ng t&#7915;'));
            jQuery("#ngay_ct").focus();
            return false;
        }
        else if (jQuery("#ngay_ct").val() != "") {
            jQuery("#dialog-confirm").dialog("close");
            ngayct = jQuery("#ngay_ct").val();
            if (ngayct.length > 10) {
                alert(GetUnicode('Ng&#224;y ch&#7913;ng t&#7915; kh&#244;ng &#273;&#250;ng &#273;&#7883;nh d&#7841;ng!'));
                jQuery("#ngay_ct").focus();
                return false;
            }
            else if (CompareDate(jQuery("#ngay_ct").val(), jQuery("#ngay_tt_temp").val()) ==  - 1) {
                alert(GetUnicode('Ng&#224;y ch&#7913;ng t&#7915; ph&#7843;i nh&#7887; h&#417;n ho&#7863;c b&#7857;ng ng&#224;y hi&#7879;n t&#7841;i!'));
                jQuery("#ngay_ct").focus();
                return false;
            }
        }
        if (jQuery("#ndung_tt").val().trim().length == 0) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng!'));
            jQuery("#ndung_tt").val("");
            jQuery("#ndung_tt").focus();
            return false;
        }
        if (jQuery("#ndung_tt").val().length > 220) {
            jQuery("#dialog-confirm").dialog("close");
            // PhuongNTM07 edited 20160926 start
            // Neu la dien LNH thi noidung_tt <= 210 ki tu
            if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
              if (jQuery("#ndung_tt").val().length > 210) {
                alert(GetUnicode('N&#7897;i dung thanh to&#225;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 210 k&#253; t&#7921;!'));
              }
            }
            else 
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 220 k&#253; t&#7921;!'));
            // PhuongNTM07 edited 20160926 end
            jQuery("#ndung_tt").focus();
            return false;
        }
        if (jQuery("#ten_tk_chuyen").val().length > 140) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('T&#234;n ng&#432;&#7901;i chuy&#7875;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 140 k&#253; t&#7921;!'));
            jQuery("#ten_tk_chuyen").focus();
            return false;
        }
        // PhuongNTM07 added 20160926 start - check truong F50KP2 (ten_tk_chuyen+ttin_kh_chuyen) <= 70 ki tu voi dien lien ngan hang
        if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
          if ((jQuery("#ten_tk_chuyen").val().length + jQuery("#ttin_kh_chuyen").val().length) > 70) {
                jQuery("#dialog-confirm").dialog( "close" ); 
                alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i t&#234;n t&#224;i kho&#7843;n chuy&#7875;n v&#224; th&#244;ng tin kh&#225;ch h&#224;ng chuy&#7875;n kh&#244;ng &#273;&#432;&#7907;c qu&#225; 70 k&#253; t&#7921;!'));
                jQuery("#ttin_kh_chuyen").attr("readOnly", false);
                jQuery("#ten_tk_chuyen").attr("readOnly", false);
                jQuery("#ten_tk_chuyen").focus();
                return false;
          }
        }
        // PhuongNTM07 added 20160926 end -
        if (jQuery("#ten_tk_nhan").val().length > 100) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('T&#234;n ng&#432;&#7901;i nh&#7853;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 50 k&#253; t&#7921;!'));
            jQuery("#ten_tk_nhan").focus();
            return false;
        }
        if ((jQuery("#ten_tk_nhan").val().length + jQuery("#ttin_kh_nhan").val().length) > 145) {
            jQuery("#dialog-confirm").dialog("close");
            // PhuongNTM07 edited 20160926 start - check truong F59P2 (ten_tk_nhan+ttin_kh_nhan) <= 70 ki tu voi dien LNH
            if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
              if ((jQuery("#ten_tk_nhan").val().length + jQuery("#ttin_kh_nhan").val().length) > 70) {
                jQuery("#ttin_kh_nhan").attr("readOnly", false);
                jQuery("#ten_tk_nhan").attr("readOnly", false);
                alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i t&#234;n t&#224;i kho&#7843;n nh&#7853;n v&#224; th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n kh&#244;ng &#273;&#432;&#7907;c qu&#225; 70 k&#253; t&#7921;!'));
                //jQuery("sua_ten_tk_flag").val("ten_tk_nhan");
              }
            }
            else alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i t&#234;n t&#224;i kho&#7843;n nh&#7853;n v&#224; &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n kh&#244;ng &#273;&#432;&#7907;c qu&#225; 146 k&#253; t&#7921;!'));
            // PhuongNTM07 edited 20160926 end -
            jQuery("#ten_tk_nhan").focus();
            return false;
        }
        if (jQuery("#ten_tk_chuyen").val() == null || jQuery("#ten_tk_chuyen").val().trim() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#234;n t&#224;i kho&#7843;n chuy&#7875;n'));
            jQuery("#ten_tk_chuyen").focus();
            return false;
        }
        //        else if (jQuery("#ten_tk_chuyen").val().length > 146) {
        //            jQuery("#dialog-confirm").dialog( "close" ); 
        //            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p &#234;n t&#224;i kho&#7843;n chuy&#7875;n d&#432;&#7899;i 146 k&#253; t&#7921;'));
        //            jQuery("#ten_tk_chuyen").focus();
        //            return false;
        //        }
        if (jQuery("#so_tk_chuyen").val() == null || jQuery("#so_tk_chuyen").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('T&#224;i kho&#7843;n chuy&#7875;n kh&#244;ng &#273;&#432;&#7907;c b&#7887; tr&#7889;ng.\n Y&#234;u c&#7847;u nh&#7853;p t&#224;i kho&#7843;n t&#7921; nhi&#234;n &#273;&#7875; c&#243; t&#224;i kho&#7843;n chuy&#7875;n'));
            jQuery("#tkkt_ma").focus();
            return false;
        }
        else if (jQuery("#so_tk_chuyen").val().length > 12 || jQuery("#so_tk_chuyen").val().length < 12) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('T&#224;i kho&#7843;n kh&#244;ng h&#7907;p l&#7879;!'));
            jQuery("#so_tk_chuyen").focus();
            return false;
        }
        if (jQuery("#id_nhkb_chuyen").val() == null || jQuery("#id_nhkb_chuyen").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p M&#227; NH/KB Ng&#432;&#7901;i chuy&#7875;n m&#7903; t&#224;i kho&#7843;n!'));
            jQuery("#ma_nhkb_chuyen").focus();
            return false;
        }
        if (jQuery("#ten_tk_nhan").val() == null || jQuery("#ten_tk_nhan").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p T&#234;n t&#224;i kho&#7843;n nh&#7853;n'));
            jQuery("#ten_tk_nhan").focus();
            return false;
        }
        else if (jQuery("#ten_tk_nhan").val().length > 146) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p T&#234;n t&#224;i kho&#7843;n nh&#7853;n d&#432;&#7899;i 146 k&#253; t&#7921;'));
            jQuery("#ten_tk_nhan").focus();
            return false;
        }
        // PhuongNTM07 replaced 20160926 start
        //if (jQuery("#ttin_kh_chuyen").val() == null && jQuery("#ttin_kh_chuyen").val() == "" && jQuery("#ttin_kh_chuyen").val().length > 146) {
        if (jQuery("#ttin_kh_chuyen").val() == null && jQuery("#ttin_kh_chuyen").val() == "") {
        // PhuongNTM07 replaced 20160926 end
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p th&#244;ng tin kh&#225;ch h&#224;ng chuy&#7875;n kh&#244;ng qu&#225; 146 k&#253; t&#7921;!'));
            var strTT_kh_chuyen = jQuery("#ttin_kh_chuyen").val();
            var strSub = strTT_kh_chuyen.substring(0, 146);
            jQuery("#ttin_kh_chuyen").val(strSub);
            jQuery("#ttin_kh_chuyen").focus();
            return false;
        }
        // PhuongNTM07 replaced 20160926 start
        //if (jQuery("#ttin_kh_nhan").val() == null && jQuery("#ttin_kh_nhan").val() == "" && jQuery("#ttin_kh_nhan").val().length > 146) {
        if (jQuery("#ttin_kh_nhan").val() == null && jQuery("#ttin_kh_nhan").val() == "") {
        // PhuongNTM07 replaced 20160926 end
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n kh&#244;ng qu&#225; 146 k&#253; t&#7921;!'));
            var strTT_kh_nhan = jQuery("#ttin_kh_nhan").val();
            var strSubNhan = strTT_kh_nhan.substring(0, 146);
            jQuery("#ttin_kh_nhan").val(strSubNhan);
            jQuery("#ttin_kh_nhan").focus();
            return false;
        }
        /*
         * param : actionForButtonGhi,ttloai_lenh
         * truong hop them moi va loai lenh la rut tien
         * khong can check cac tt sau  : so_tk_nhan - ma_nhkb_nhan - ten_nhkb_nhan
         * Y/C moi : hoan thien cung lam giong them moi
         * */

        if (ttloai_lenh != null && ttloai_lenh == '02') {
            return true;
        }
        /*
          * End
          * */
        if (jQuery("#ma_nhkb_nhan_cm").val() == null || jQuery("#ma_nhkb_nhan_cm").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p ho&#7863;c ch&#7885;n NH/KB Nh&#7853;n'));
            jQuery("#ma_nhkb_nhan_cm").focus();
            return false;
        }
        else if (jQuery("#ma_nhkb_nhan_cm").val().length < 8 || jQuery("#ma_nhkb_nhan_cm").val().length > 8) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('NH/KB Nh&#7853;n ph&#7843;i c&#243; 8 k&#253; t&#7921;'));
            jQuery("#ma_nhkb_nhan_cm").focus();
            return false;
        }
        if (jQuery("#so_tk_nhan").val() == null || jQuery("#so_tk_nhan").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p S&#7889; t&#224;i kho&#7843;n nh&#7853;n'));
            jQuery("#so_tk_nhan").focus();
            return false;
        }
        if (jQuery("#ten_nhkb_nhan").val() == null || jQuery("#ten_nhkb_nhan").val() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p T&#234;n NH/KB nh&#7853;n!'));
            jQuery("#ten_nhkb_nhan").focus();
            return false;
        }
        else if (jQuery("#ten_nhkb_nhan").val().length > 146) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p T&#234;n NH/KB nh&#7853;n d&#432;&#7899;i 100 k&#253; t&#7921;'));
            jQuery("#ten_nhkb_nhan").focus();
            return false;
        }

    }
    else {
        if (jQuery("#ndung_tt").val().length > 220) {
            jQuery("#dialog-confirm").dialog("close");
            // PhuongNTM07 edited 20160926 start
            // truong noidung_tt <= 210 ki tu neu la dien LNH
            if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
              if (jQuery("#ndung_tt").val().length > 210) {
                alert(GetUnicode('N&#7897;i dung thanh to&#225;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 210 k&#253; t&#7921;!'));
              }
            }
            else 
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n qu&#225; d&#224;i, ph&#7843;i nh&#7887; h&#417;n 220 k&#253; t&#7921;!'));
            // PhuongNTM07 edited 20160926 end
            jQuery("#ndung_tt").focus();
            return false;
        }
        if (jQuery("#ndung_tt").val().trim().length == 0) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng!'));
            jQuery("#ndung_tt").val("");
            jQuery("#ndung_tt").focus();
            return false;
        }
        if (jQuery("#ten_tk_chuyen").val() == null || jQuery("#ten_tk_chuyen").val().trim() == "") {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#234;n t&#224;i kho&#7843;n chuy&#7875;n'));
            jQuery("#ten_tk_chuyen").focus();
            return false;
        }
    }
    //Check COA
    var objTkkt = document.getElementsByName("tkkt_ma");
    var objDvsdns = document.getElementsByName("dvsdns_ma");
    var objCapns = document.getElementsByName("capns_ma");
    if (objTkkt == null) {
        jQuery("#dialog-confirm").dialog("close");
        alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#224;i kho&#7843;n t&#7921; nhi&#234;n'));
        result = false;
    }
    else if (objTkkt.length == 0) {
        jQuery("#dialog-confirm").dialog("close");
        alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#224;i kho&#7843;n t&#7921; nhi&#234;n'));
        result = false;
    }
    else if (objTkkt.length == 1) {
        result = alertInCOAOnlyOneRow();
    }
    else if (objTkkt.length > 1) {
        //kiem tra 03 cot: TK tu nhien, capNS, Dvqhns 
        var ma_nhkb_chuyen_cm = jQuery("#ma_nhkb_chuyen_cm").val();
        var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
        if (ma_nhkb_chuyen != '' && ma_nhkb_chuyen_cm == ma_nhkb_chuyen) {
            var existSpecialCode = false;
            for (var i = 0;i < objTkkt.length;i++) {
                for (var j = 0;j < arrSpecialTKTN.length;j++) {
                    if (objTkkt[i].value == arrSpecialTKTN[j]) {
                        existSpecialCode = true;
                        break;
                    }
                }
            }
            if (!existSpecialCode) {
                for (var _i = 0;i < objTkkt.length - 1;i++) {
                    for (var _j = 1;i < objTkkt.length;i++) {
                        /*if (objTkkt[_i].value != objTkkt[_j].value) {
                            jQuery("#dialog-confirm").dialog("close");
                            alert(GetUnicode('T&#224;i kho&#7843;n t&#7921; nhi&#234;n ph&#7843;i gi&#7889;ng nhau'));
                            document.forms[0].tkkt_ma[i].focus();
                            return false;
                        }*/
                        if (objDvsdns[_i].value != objDvsdns[_j].value) {
                            jQuery("#dialog-confirm").dialog("close");
                            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p m&#227; &#272;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch gi&#7889;ng nhau'));
                            document.forms[0].dvsdns_ma[i].focus();
                            return false;
                        }
                        if (objCapns[_i].value != objCapns[_j].value) {
                            jQuery("#dialog-confirm").dialog("close");
                            alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p m&#227; C&#7845;p ng&#226;n s&#225;ch gi&#7889;ng nhau'));
                            document.forms[0].capns_ma[i].focus();
                            return false;
                        }
                    }
                }
            }
        }

        for (i = 0;i < objTkkt.length;i++) {
            result = alertInCOAMutipleRow(i);
            if (!result)
                break;
        }
    }
    //    if(jQuery("#tong_sotien").val() == null || jQuery("#tong_sotien").val() == ""){
    //      alert("B?n ph?i nh?p COA ?? có T?ng ti?n!");
    //      //Phai tim truong so tien nao empty roi focus vao do
    //      jQuery("#so_tien").focus();
    //      
    //      return false;
    //    }
    return result;
}

//Lenh thanh toan Den
function checkInputLTTDen(strLoaiHT, bCheck) {
    if (strLoaiHT != null && 'undefined' != strLoaiHT) {
        if (strLoaiHT == '02') {
            if (jQuery("#ly_do_htoan").val() == null || jQuery("#ly_do_htoan").val() == '' || jQuery("#ly_do_htoan").val() == 'undefined') {
                alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p l&#253; do h&#7841;ch to&#225;n'));
                //      document.forms[0].loai_hach_toan.style.backgroundColor = '#f9e422';
                jQuery("#ly_do_htoan").focus();
                return;
            }
            return true;
        }
    }

    var result = true;
    //Check COA
    var objTkkt = document.getElementsByName("tkkt_ma");
    var objDvsdns = document.getElementsByName("dvsdns_ma");
    var objCapns = document.getElementsByName("capns_ma");
    // neu khong co COA ko check
    if (!bCheck) {
        return true;
    }

    if (objTkkt == null) {
        alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#224;i kho&#7843;n t&#7921; nhi&#234;n'));
        result = false;
    }
    else if (objTkkt.length == 0) {
        alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p t&#224;i kho&#7843;n t&#7921; nhi&#234;n'));
        result = false;
    }
    else if (objTkkt.length == 1) {
        result = alertInCOAOnlyOneRow();
    }
    else if (objTkkt.length > 1) {
        //kiem tra 03 cot: TK tu nhien, capNS, Dvqhns 
        for (var i = 0;i < objTkkt.length - 1;i++) {
            for (var j = 1;i < objTkkt.length;i++) {
                if (objTkkt[i].value != objTkkt[j].value) {
                    alert(GetUnicode('T&#224;i kho&#7843;n t&#7921; nhi&#234;n ph&#7843;i gi&#7889;ng nhau'));
                    document.forms[0].tkkt_ma[i].focus();
                    return false;
                }
                if (objDvsdns[i].value != objDvsdns[j].value) {
                    alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p m&#227; &#272;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch gi&#7889;ng nhau'));
                    document.forms[0].dvsdns_ma[i].focus();
                    return false;
                }
                //                if (objCapns[i].value != objCapns[j].value) {
                //                    alert(GetUnicode('B&#7841;n ph&#7843;i nh&#7853;p m&#227; C&#7845;p ng&#226;n s&#225;ch gi&#7889;ng nhau'));
                //                    document.forms[0].capns_ma[i].focus();
                //                    return false;
                //                }
            }
        }

        for (i = 0;i < objTkkt.length;i++) {
            result = alertInCOAMutipleRow(i);
            if (!result)
                break;
        }
    }

    return result;
}

//Dung chung cho: LTT di, LTT den, Quyet toan
function alertInCOAOnlyOneRow() {
    if (!document.forms[0].tkkt_ma.readOnly) {
        if (document.forms[0].tkkt_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng t&#224;i kho&#7843;n t&#7921; nhi&#234;n'));
            document.forms[0].tkkt_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].ma_quy.readOnly) {
        if (document.forms[0].ma_quy.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; qu&#7929;'));
            document.forms[0].ma_quy.focus();
            return false;
        }
    }
    if (!document.forms[0].dvsdns_ma.readOnly) {
        if (document.forms[0].dvsdns_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng &#272;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch'));
            document.forms[0].dvsdns_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].capns_ma.readOnly) {
        if (document.forms[0].capns_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng C&#7845;p ng&#226;n s&#225;ch'));
            document.forms[0].capns_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].chuongns_ma.readOnly) {
        if (document.forms[0].chuongns_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ch&#432;&#417;ng'));
            document.forms[0].chuongns_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].nganhkt_ma.readOnly) {
        if (document.forms[0].nganhkt_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ng&#224;nh kinh t&#7871;'));
            document.forms[0].nganhkt_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].ndkt_ma.readOnly) {
        if (document.forms[0].ndkt_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; n&#7897;i dung kinh t&#7871;'));
            document.forms[0].ndkt_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].dbhc_ma.readOnly) {
        if (document.forms[0].dbhc_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; &#273;&#7883;a b&#224;n h&#224;nh ch&#237;nh'));
            document.forms[0].dbhc_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].ctmt_ma.readOnly) {
        if (document.forms[0].ctmt_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ch&#432;&#417;ng tr&#236;nh m&#7909;c ti&#234;u'));
            document.forms[0].ctmt_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].ma_nguon.readOnly) {
        if (document.forms[0].ma_nguon.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ngu&#7891;n'));
            document.forms[0].ma_nguon.focus();
            return false;
        }
    }
    if (!document.forms[0].kb_ma.readOnly) {
        if (document.forms[0].kb_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; kho b&#7841;c'));
            document.forms[0].kb_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].du_phong_ma.readOnly) {
        if (document.forms[0].du_phong_ma.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; d&#7921; ph&#242;ng'));
            document.forms[0].du_phong_ma.focus();
            return false;
        }
    }
    if (!document.forms[0].dien_giai.readOnly) {
        if (document.forms[0].dien_giai.value != '' && document.forms[0].dien_giai.value.length > 150) {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('Di&#7877;n gi&#7843;i &#273;&#227; qu&#225; 150 k&#253; t&#7921;. H&#227;y nh&#7853;p l&#7841;i!'));
            document.forms[0].dien_giai.focus();
            return false;
        }
    }
    if (!document.forms[0].so_tien.readOnly) {
        if (document.forms[0].so_tien.value == '') {
            jQuery("#dialog-confirm").dialog("close");
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng S&#7889; ti&#7873;n'));
            document.forms[0].so_tien.focus();
            return false;
        }
    }
    return true;
}
//@param: i type int
function alertInCOAMutipleRow(i) {
    //bat nhap tkkt_ma
    if (!document.forms[0].tkkt_ma[i].readOnly) {
        if (document.forms[0].tkkt_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng T&#236; kho&#7843;n t&#7921; nhi&#234;n'));
            document.forms[0].tkkt_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].ma_quy[i].readOnly) {
        if (document.forms[0].ma_quy[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; qu&#7929;'));
            document.forms[0].ma_quy[i].focus();
            return false;
        }
    }
    if (!document.forms[0].dvsdns_ma[i].readOnly) {
        if (document.forms[0].dvsdns_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng &#272;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch'));
            document.forms[0].dvsdns_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].capns_ma[i].readOnly) {
        if (document.forms[0].capns_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng C&#7845;p ng&#226;n s&#225;ch'));
            document.forms[0].capns_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].chuongns_ma[i].readOnly) {
        if (document.forms[0].chuongns_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ch&#432;&#417;ng'));
            document.forms[0].chuongns_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].nganhkt_ma[i].readOnly) {
        if (document.forms[0].nganhkt_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ng&#224;nh kinh t&#7871;'));
            document.forms[0].nganhkt_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].ndkt_ma[i].readOnly) {
        if (document.forms[0].ndkt_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; n&#7897;i dung kinh t&#7871;'));
            document.forms[0].ndkt_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].dbhc_ma[i].readOnly) {
        if (document.forms[0].dbhc_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; &#273;&#7883;a b&#224;n h&#224;nh ch&#237;nh'));
            document.forms[0].dbhc_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].ctmt_ma[i].readOnly) {
        if (document.forms[0].ctmt_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ch&#432;&#417;ng tr&#236;nh m&#7909;c ti&#234;u'));
            document.forms[0].ctmt_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].ma_nguon[i].readOnly) {
        if (document.forms[0].ma_nguon[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; ngu&#7891;n'));
            document.forms[0].ma_nguon[i].focus();
            return false;
        }
    }
    if (!document.forms[0].kb_ma[i].readOnly) {
        if (document.forms[0].kb_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; kho b&#7841;c'));
            document.forms[0].kb_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].du_phong_ma[i].readOnly) {
        if (document.forms[0].du_phong_ma[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng M&#227; d&#7921; ph&#242;ng'));
            document.forms[0].du_phong_ma[i].focus();
            return false;
        }
    }
    if (!document.forms[0].dien_giai[i].readOnly) {
        if (document.forms[0].dien_giai[i].value != '' && document.forms[0].dien_giai[i].value.length > 150) {
            alert(GetUnicode('Di&#7877;n gi&#7843;i &#273;&#227; qu&#225; 150 k&#253; t&#7921;. H&#227;y nh&#7853;p l&#7841;i!'));
            document.forms[0].dien_giai[i].focus();
            return false;
        }
    }
    if (!document.forms[0].so_tien[i].readOnly) {
        if (document.forms[0].so_tien[i].value == '') {
            alert(GetUnicode('B&#7841;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng S&#7889; ti&#7873;n'));
            document.forms[0].so_tien[i].focus();
            return false;
        }
    }
    return true;
}