//var arrFieldId = new Array("nhkb_chuyen", "nhkb_nhan", "nhan", "ttv_nhan", "ma_ttv_nhan", "ten_ttv_nhan", "ngay_nhan", "ktv_chuyen", "ktt_duyet", "ma_ktt_duyet", "ten_ktt_duyet", "ngay_ktt_duyet", "lydo_ktt_day_lai", "gd_duyet", "ma_gd_duyet", "ten_gd_duyet", "ngay_gd_duyet", "lydo_gd_day_lai", "so_ct_goc", "ngay_ct", "nt_id", "so_yctt", "ngay_tt", "ndung_tt", "tong_sotien", "so_tk_chuyen", "ten_tk_chuyen", "ttin_kh_chuyen", "id_nhkb_chuyen", "ten_nhkb_chuyen", "so_tk_nhan", "ten_tk_nhan", "ttin_kh_nhan", "id_nhkb_nhan", "ten_nhkb_nhan", "trang_thai", "loai_hach_toan", "nguoi_nhap_nh", "ngay_nhap_nh", "nguoi_ks_nh", "ngay_ks_nh", "ma_nhkb_chuyen", "ma_nhkb_nhan", "ma_nhkb_chuyen_cm", "ma_nhkb_nhan_cm", "ten_nhkb_chuyen_cm", "ten_nhkb_nhan_cm", "id", "ngay_hoan_thien", "ttloai_lenh", "ly_do_htoan", "so_tham_chieu_gd", "ma_nt_mua_ban", "so_tien_mua_ban", "phi");
var arrFieldId = new Array("nhkb_chuyen", "nhkb_nhan", "nhan", "ttv_nhan", "ma_ttv_nhan", "ten_ttv_nhan", "ngay_nhan", "ktv_chuyen", "ktt_duyet", "ma_ktt_duyet", "ten_ktt_duyet", "ngay_ktt_duyet", "lydo_ktt_day_lai", "gd_duyet", "ma_gd_duyet", "ten_gd_duyet", "ngay_gd_duyet", "lydo_gd_day_lai", "so_ct_goc", "ngay_ct", "nt_id", "so_yctt", "ngay_tt", "ndung_tt", "tong_sotien", "so_tk_chuyen", "ten_tk_chuyen", "ttin_kh_chuyen", "id_nhkb_chuyen", "ten_nhkb_chuyen", "so_tk_nhan", "ten_tk_nhan", "ttin_kh_nhan", "id_nhkb_nhan", "ten_nhkb_nhan", "ten_nh_tgian", "trang_thai", "loai_hach_toan", "nguoi_nhap_nh", "ngay_nhap_nh", "nguoi_ks_nh", "ngay_ks_nh", "ma_nhkb_chuyen", "ma_nhkb_nhan", "nh_tgian", "ma_nhkb_chuyen_cm", "ma_nhkb_nhan_cm", "ten_nhkb_chuyen_cm", "ten_nhkb_nhan_cm", "id", "ngay_hoan_thien", "ttloai_lenh", "ly_do_htoan", "so_tham_chieu_gd", "ma_nt_mua_ban", "so_tien_mua_ban", "phi");

var arrFieldIdLttCTiet = new Array("ma_quy", "tkkt_ma", "dvsdns_ma", "capns_ma", "chuongns_ma", "nganhkt_ma", "ndkt_ma", "dbhc_ma", "ctmt_ma", "ma_nguon", "kb_ma", "du_phong_ma", "dien_giai", "so_tien");

var arrTextAriaId = new Array("ndung_tt", "ttin_kh_chuyen", "ttin_kh_nhan", "lydo_ktt_day_lai", "lydo_gd_day_lai");

//var arrFieldFocusTTChung = new Array("ma_nhkb_nhan_cm", "so_ct_goc", "ngay_ct", "phi", "so_yctt", "ngay_tt", "ma_ttv_nhan", "ngay_hoan_thien", "ma_ktt_duyet", "tong_sotien", "nt_id", "ndung_tt","ma_nt_mua_ban","so_tien_mua_ban", "ten_tk_chuyen", "ttin_kh_chuyen", "so_tk_chuyen", "ma_nhkb_chuyen");
var arrFieldFocusTTChung = new Array("ma_nhkb_nhan_cm", "so_ct_goc", "ngay_ct", "phi", "so_yctt", "ngay_tt", "ma_ttv_nhan", "ngay_hoan_thien", "ma_ktt_duyet", "tong_sotien", "nt_id", "ndung_tt","ma_nt_mua_ban","so_tien_mua_ban", "ten_tk_chuyen", "ttin_kh_chuyen", "so_tk_chuyen", "ma_nhkb_chuyen");
var arrFieldFocusTTChungLapMoi = new Array("ma_nhkb_nhan_cm", "so_ct_goc", "ngay_ct", "so_yctt", "ngay_tt", "ma_ttv_nhan", "ngay_hoan_thien", "ma_ktt_duyet", "tong_sotien", "nt_id", "ttloai_lenh", "phi","ndung_tt","ma_nt_mua_ban","so_tien_mua_ban", "ten_tk_chuyen", "ttin_kh_chuyen", "so_tk_chuyen", "ma_nhkb_chuyen");
var arrFieldFocusTTNT = new Array("ten_tk_nhan", "ttin_kh_nhan", "so_tk_nhan", "ma_nhkb_nhan", "nh_tgian");
var arrFieldFocusTTNTLapMoi = new Array("ten_tk_nhan", "ttin_kh_nhan", "so_tk_nhan", "ma_nhkb_nhan", "nh_tgian","ten_nh_tgian");
//Global variable duoc khoi tao trong tung trang LTT
//Khi co ca 2 bo ma TKTN dac biet, TKTN anh ninh trong COA, thi se uu tien thang TKTN Dac biet(Vi no co ma 7111)
//arrSpecialTKTN, arrDVQHNSAnNinh
input_id_last = 0;
limitEsc = 1;

function nextElementTCLTTFocus(e) {
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
//************************************ LIMIT CHARS ********************************** 
function limitChars(id, maxlength) {
    var limit = maxlength;
    var text = jQuery("#" + id).val();
    var textlength = text.length;
    if (parseInt(textlength) > parseInt(limit)) {
        jQuery("#ttin_kh_nhan").keyup(function () {
            this.value = this.value.substr(0, limit);
        });
        jQuery("#ttin_kh_chuyen").keyup(function () {
            this.value = this.value.substr(0, limit);
        });
        return false;
    }
}
//var lttGoc_CTietJsonObj = new Object();
//var so_tk_dvnhanBak="";
function loadDetailLTTJson(strUrlAction, id, tr_id, userType, isTraCuu) { 
        
    jQuery("#id_selected").val(id);
    if (id != undefined) {
        jQuery("#btnIn").show();
        //      jQuery("#search").show();
    }
    else {
        jQuery("#btnIn").hide();
        //      jQuery("#search").hide();
        return false;
    }
    clearForm(arrFieldId);
    clearForm(arrFieldIdLttCTiet);
    //BEGIN-20150713:ManhNV-An nut ghi khi load du lieu, khi nao load xong thi hien
    try{
      jQuery("#save").hide();
    }catch(ex){
      alert('Loi khi hide button CHI'); 
    }
    //END-20150713
    var strUrl = '';
    if (strUrlAction != null && strUrlAction != '') {
        strUrl = strUrlAction;
    }
    else {
        strUrl = "loadLTTDiJsonAction.do";
    }
    jQuery.ajax( {
        type : "POST", url : strUrl, data :  {
           "idLoadDetail" : id, "userType" : userType, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data == null) {
                    jQuery("#message").html(GetUnicode("Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c d&#7919; li&#7879;u : ") + textstatus.messge);
                    jQuery("#dialog-message").dialog("open");
                    return;
                }
                if (data.logout != null && data.logout) {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {
                        var lttJsonObj = new Object();
                        var lttCTietJsonObj = new Object();
                        var dmnhNhanJsonObj = null;
                        lttJsonObj = JSON.parse(data[0]);
                        lttCTietJsonObj = JSON.parse(data[1]);
                        if (strUrl == 'loadLTTDiJsonAction.do') {
                            if (data.length >= 3) {
                                dmnhNhanJsonObj = new Object();
                                dmnhNhanJsonObj = JSON.parse(data[2]);
                            }
                        }
                        else {
                            TenDVQHNSJsonObj = JSON.parse(data[2]);
                            jQuery("#so_tk_dvnhan").val(TenDVQHNSJsonObj.ten);
                        }
                        countLttCTiet = 0;
                        if (lttCTietJsonObj != null)
                            countLttCTiet = lttCTietJsonObj.length;

                        countDmnhNhan = 0;
                        if (dmnhNhanJsonObj != null) {
                            //Xoa option cho NHKN Nhan
                            jQuery('#ma_nhkb_nhan_cm option').remove();

                            countDmnhNhan = dmnhNhanJsonObj.length;
                        }
                        if (countDmnhNhan > 0) {
                            for (var h = 0;h < countDmnhNhan;h++) {
                                if (lttJsonObj.ma_nhkb_nhan_cm == dmnhNhanJsonObj[h].ma_nh) {
                                    jQuery('#ma_nhkb_nhan_cm').append('<option value="' + dmnhNhanJsonObj[h].ma_nh + '" selected="selected">' + dmnhNhanJsonObj[h].ma_nh + '<\/option>');
                                }
                                else {
                                    jQuery('#ma_nhkb_nhan_cm').append('<option value="' + dmnhNhanJsonObj[h].ma_nh + '" >' + dmnhNhanJsonObj[h].ma_nh + '<\/option>');
                                }
                            }
                        }
                        else {
                            if (strUrl != 'loadLTTDenJsonAction.do')
                                jQuery('#ma_nhkb_nhan_cm').append('<option value="' + lttJsonObj.ma_nhkb_nhan_cm + '" selected="selected">' + lttJsonObj.ma_nhkb_nhan_cm + '<\/option>');
                        }
                        jQuery("#sua_ten_tk_flag").val("");
                        
                        jQuery("#id").val(lttJsonObj.id);
                        jQuery("#ma_nhkb_chuyen_cm").val(lttJsonObj.ma_nhkb_chuyen_cm);
                        jQuery("#ten_nhkb_chuyen_cm").val(lttJsonObj.ten_nhkb_chuyen_cm);
                        jQuery("#ma_nhkb_nhan_cm").val(lttJsonObj.ma_nhkb_nhan_cm);
                        jQuery("#ten_nhkb_nhan_cm").val(lttJsonObj.ten_nhkb_nhan_cm);
                        jQuery("#so_tham_chieu_gd").val(lttJsonObj.so_tham_chieu_gd);
                        jQuery("#idTraLai").val(lttJsonObj.so_tham_chieu_gd);
                        jQuery("#nhkb_chuyen").val(lttJsonObj.nhkb_chuyen);
                        jQuery("#nhkb_nhan").val(lttJsonObj.nhkb_nhan);
                        jQuery("#nhan").val(lttJsonObj.nhan);
                        jQuery("#noi_dung_ky").val(lttJsonObj.noi_dung_ky);
                        if (strUrl == 'loadLTTDiJsonAction.do') {
                            if (lttJsonObj.nhan == 'N') {
                                jQuery("#lenh_thucong_tabmis").html(GetUnicode("L&#7879;nh th&#7911; c&#244;ng"));                                
                                jQuery("#form_type").val("hoan_thien_lenh_thu_cong");
                            }
                            else if (lttJsonObj.nhan == 'Y') {
                                jQuery("#lenh_thucong_tabmis").html(GetUnicode("L&#7879;nh t&#7915; giao di&#7879;n"));                                
                                jQuery("#form_type").val("hoan_thien_ltt_di");
                            }
                            else {
                                jQuery("#lenh_thucong_tabmis").html('');                                
                                jQuery("#form_type").val("hoan_thien_ltt_di");
                            }
                        }
                        
                        jQuery("#ma_ttv_nhan").val(lttJsonObj.ma_ttv_nhan);
                        jQuery("#ten_ttv_nhan").val(lttJsonObj.ten_ttv_nhan);
                        jQuery("#ttv_nhan").val(lttJsonObj.ttv_nhan);
                        jQuery("#ttloai_lenh").val(lttJsonObj.ttloai_lenh);
                        
                        //ManhNV-20150317   
                        if(strUrl == "loadLTTDiJsonAction.do"){
                          jQuery("#ma_nt_mua_ban").val(lttJsonObj.ma_nt_mua_ban);                        
                          if(lttJsonObj.ma_nt_mua_ban != null && lttJsonObj.ma_nt_mua_ban != '' && lttJsonObj.so_tien_mua_ban != null && lttJsonObj.so_tien_mua_ban != '' && lttJsonObj.ma_nt_mua_ban != 'VND'){                          
                            jQuery("#so_tien_mua_ban").val(CurrencyFormatted(lttJsonObj.so_tien_mua_ban,lttJsonObj.ma_nt_mua_ban));
                          }
                          
                          if(lttJsonObj.ttloai_lenh == '03'){
                            jQuery("#tr_mua_ban").show(); 
                            jQuery("#td_nt_id").html(GetUnicode('M&#227; NT tr&#237;ch n&#7907;'));
                            jQuery("#tip_tong_tien_mua_ban").val(GetUnicode('(S&#7889; ti&#7873;n ngo&#7841;i t&#7879; d&#7921; t&#237;nh tr&#237;ch n&#7907;)'));
                          }else{
                            jQuery("#tr_mua_ban").hide(); 
                            jQuery("#td_nt_id").html(GetUnicode('M&#227; NT'));
                            jQuery("#tip_tong_tien_mua_ban").val('');
                          }
                        }
                        //ManhNV-20150317
                        
                        jQuery("#ngay_hoan_thien").val(lttJsonObj.ngay_hoan_thien);

                        jQuery("#ktv_chuyen").val(lttJsonObj.ktv_chuyen);

                        jQuery("#ma_ktt_duyet").val(lttJsonObj.ma_ktt_duyet);
                        jQuery("#ten_ktt_duyet").val(lttJsonObj.ten_ktt_duyet);
                        jQuery("#ktt_duyet").val(lttJsonObj.ktt_duyet);
                        jQuery("#ngay_ktt_duyet").val(lttJsonObj.ngay_ktt_duyet);
                        jQuery("#lydo_ktt_day_lai").val(lttJsonObj.lydo_ktt_day_lai);
                        jQuery("#cmdAddRow").attr("readonly", true);
                        jQuery.each(arrFieldIdLttCTiet, function (index, value) {
                            strId = value.toString();
                            assignStyle2Field(strId, true);
                            if (document.getElementById(strId) != null) {
                                if (strId == 'so_tien') {
                                    setClass2Field(strId, "fieldTextRightCode");
                                }
                                else {
                                    setClass2Field(strId, "fieldTextCenterCode");
                                }
                            }
                        });
                        jQuery.each(arrFieldId, function (index, value) {
                            strId = value.toString();
                            assignStyle2Field(strId, true);
                            if (document.getElementById(strId) != null) {
                                if (strId == 'tong_sotien') {
                                    setClass2Field(strId, "fieldTextRightCode");
                                }
                                else {
                                    setClass2Field(strId, "fieldTextCode");
                                }
                            }
                        });
                        jQuery("#ma_gd_duyet").val(lttJsonObj.ma_gd_duyet);
                        jQuery("#ten_gd_duyet").val(lttJsonObj.ten_gd_duyet);
                        jQuery("#gd_duyet").val(lttJsonObj.gd_duyet);
                        jQuery("#ngay_gd_duyet").val(lttJsonObj.ngay_gd_duyet);
                        if (userType.indexOf('KTT') !=  - 1 || userType.indexOf('GD') !=  - 1){//20141030
                            jQuery("#lydo_gd_day_lai").val(lttJsonObj.lydo_gd_day_lai);
                            if(strUrl == "loadLTTDiJsonAction.do"){
                              document.getElementById("vay_tra_no_nn").disabled = true;
                            }
                        }
                        if(strUrl == "loadLTTDiJsonAction.do"){
                          if(lttJsonObj.trang_thai == '02' || lttJsonObj.trang_thai == '01'){
                            document.getElementById("vay_tra_no_nn").disabled = false;
                          }else{
                            document.getElementById("vay_tra_no_nn").disabled = true;
                          }
                         
                          if(lttJsonObj.vay_tra_no_nn == 'Y'){
                            document.getElementById("vay_tra_no_nn").checked=true;
                          }else{
                            document.getElementById("vay_tra_no_nn").checked=false;
                          }
                        }
                        
                        jQuery("#so_ct_goc").val(lttJsonObj.so_ct_goc);
                        var ngay_ct = '';
                        if (lttJsonObj.ngay_ct != null)
                            ngay_ct = convertNumber2ddmmyyyy(lttJsonObj.ngay_ct.toString());
                        jQuery("#ngay_ct").val(ngay_ct);

                        var nt_id = lttJsonObj.nt_id;                        
                        jQuery("#rpt_nt_id").val(nt_id);
                    
                        var nt_code = lttJsonObj.nt_code != null ? lttJsonObj.nt_code : "";
                        if (nt_id != 'undefined'){
                            jQuery("#nt_id").val(nt_id);
                            jQuery("#nt_id_old").val(nt_id);
                        }else {
                            jQuery("#nt_id").val('VND');
                            jQuery("#nt_id_old").val('VND');
                        }
                        //ManhNV-20150625   
                        if(strUrl == "loadLTTDiJsonAction.do"){
                          if (nt_id != 'undefined' && nt_id != 'VND' && nt_id != '177'){
                            jQuery("#tr_nh_trung_gian").show(); 
                            //show loai phi                          
                            jQuery("#td_loai_lable_loai_phi").show(); 
                            jQuery("#td_loai_field_loai_phi").show(); 
                          }else{
                            jQuery("#tr_nh_trung_gian").hide(); 
                            //hide loai phi
                            jQuery("#td_loai_lable_loai_phi").hide(); 
                            jQuery("#td_loai_field_loai_phi").hide();
                          }
                        }
                        //ManhNV-20150625
                        jQuery("#so_yctt").val(lttJsonObj.so_yctt);
                        var ngay_tt = "";
                        if (lttJsonObj.ngay_tt != null)
                            ngay_tt = convertNumber2ddmmyyyy(lttJsonObj.ngay_tt.toString());
                        jQuery("#ngay_tt").val(ngay_tt);
                        jQuery("#ndung_tt").val(lttJsonObj.ndung_tt);
                        
                        jQuery("#tong_sotien").val(CurrencyFormatted(lttJsonObj.tong_sotien, nt_code));
                        //                        jQuery("#tong_sotien").val(toFormatNumberDe_TTSP(lttJsonObj.tong_sotien, 0, nt_id));
                        //TT nguoi chuyen
                        jQuery("#ten_tk_chuyen").val(lttJsonObj.ten_tk_chuyen);
                        jQuery("#ten_tk_chuyen_tmp").val(lttJsonObj.ten_tk_chuyen);
                        jQuery("#ttin_kh_chuyen").val(lttJsonObj.ttin_kh_chuyen);
                        jQuery("#so_tk_chuyen").val(lttJsonObj.so_tk_chuyen);
                        jQuery("#id_nhkb_chuyen").val(lttJsonObj.id_nhkb_chuyen);
                        jQuery("#ma_nhkb_chuyen").val(lttJsonObj.ma_nhkb_chuyen);
                        jQuery("#ten_nhkb_chuyen").val(lttJsonObj.ten_nhkb_chuyen);
                        //                        if (lttJsonObj.error_desc != null && "" != lttJsonObj.error_desc)
                        jQuery("#error_desc").val(lttJsonObj.error_desc);
                        if (lttJsonObj.error_desc != null && lttJsonObj.error_desc != '') {
                            jQuery("#error_desc").attr( {
                                'class' : 'fieldTextTransError'
                            });
                        }
                        //TT nguoi nhan
                        if (jQuery("#ttloai_lenh").val() != null && jQuery("#ttloai_lenh").val() != '' && jQuery("#ttloai_lenh").val() != 'undefined') {
                            if (jQuery("#ttloai_lenh").val() == '02') {
                                jQuery("#so_tk_nhan").val('');
                            }
                            else {
                                jQuery("#so_tk_nhan").val(lttJsonObj.so_tk_nhan);
                            }
                        }
                        else {
                            jQuery("#so_tk_nhan").val(lttJsonObj.so_tk_nhan);
                        }
                        var i = lttJsonObj.so_tk_nhan != null ? lttJsonObj.so_tk_nhan.length : 0;
                        var valueToolTip = '';
                        if (i > 0) {
                            // hien thi yeu cau anh phong o day
                            for (var indext = 0;indext <= i;indext += 3) {
                                var valueTmp = '';
                                valueTmp = lttJsonObj.so_tk_nhan.substr(indext, 3);
                                valueToolTip += valueTmp + " ";
                            }
                        }
                        jQuery("#so_tk_nhan_tooltip").val(valueToolTip);
                        jQuery("#ma_nhkb_nhan").val(lttJsonObj.ma_nhkb_nhan);
                        jQuery("#ten_nhkb_nhan").val(lttJsonObj.ten_nhkb_nhan);

                        jQuery("#ten_tk_nhan").val(lttJsonObj.ten_tk_nhan);
                        jQuery("#ttin_kh_nhan").val(lttJsonObj.ttin_kh_nhan);
                        jQuery("#id_nhkb_nhan").val(lttJsonObj.id_nhkb_nhan);
                        jQuery("#trang_thai").val(lttJsonObj.trang_thai);
                        jQuery("#nguoi_nhap_nh").val(lttJsonObj.nguoi_nhap_nh);
                        jQuery("#ngay_nhap_nh").val(lttJsonObj.ngay_nhap_nh);
                        jQuery("#nguoi_ks_nh").val(lttJsonObj.nguoi_ks_nh);
                        jQuery("#ngay_ks_nh").val(lttJsonObj.ngay_ks_nh);
                        jQuery("#phi").val(lttJsonObj.phi);
                        jQuery("#nh_tgian").val(lttJsonObj.nh_tgian);
                        jQuery("#ten_nh_tgian").val(lttJsonObj.ten_nh_tgian);
                        if (strUrl == 'loadLTTDenJsonAction.do' || jQuery("#loai_hach_toan").val() != 'undefined') {
                            jQuery("#ly_do_htoan").val(lttJsonObj.ly_do_htoan);
                            if (lttJsonObj.loai_hach_toan == '02' && lttJsonObj.trang_thai=='02') {
                                jQuery("#ly_do_htoan").attr('readonly', false);
                                jQuery("#ly_do_htoan").attr('class', 'fieldTextArea');
                            }
                            else {
                                jQuery("#ly_do_htoan").attr('readonly', true);
                                jQuery("#ly_do_htoan").attr('class', 'fieldTextAreaRO');
                            }
                            jQuery("#loai_hach_toan").val(lttJsonObj.loai_hach_toan);
                            jQuery("#loai_hach_toan_selected").val(lttJsonObj.loai_hach_toan);
                            //                alert(jQuery("#loai_hach_toan_selected").val());
                        }
                        if (strUrl == 'loadQuyetToanAction.do') {
                            if (lttJsonObj.loai_quyet_toan == 'C') {
                                jQuery("#loai_quyet_toan_text").val(GetUnicode("C&#243;"));
                            }
                            else if (lttJsonObj.loai_quyet_toan == 'D') {
                                jQuery("#loai_quyet_toan_text").val(GetUnicode("N&#7907;"));
                            }
                            else {
                                jQuery("#loai_quyet_toan_text").val('');
                            }
                            jQuery("#loai_quyet_toan").val(lttJsonObj.loai_quyet_toan);
                        }

                        var strTrangThai = '';
                        var trangThai = '';
                        if (lttJsonObj.trang_thai != null)
                            trangThai = lttJsonObj.trang_thai;
                        strTrangThai = getStrTrangThai(trangThai);
                        if (strTrangThai != '')
                            jQuery("#mo_ta_trang_thai").html(GetUnicode(strTrangThai));
                        //Thong tin chi tiet COA
                        var tableCT = document.getElementById('tblThongTinChiTietCOA');
                        var lastRow = tableCT.rows.length;
                        var tabrow = 47;

                        if (parseFloat(lastRow) > 1) {
                            tabrow += (lastRow - 2) * 8;
                        }
                        //Lay dong dau tien              
                        var root = tableCT.getElementsByTagName('tr')[1].parentNode;

                        var rowDetails = countLttCTiet;
                        //alert(rowDetails);
                        var tbodyCT = tableCT.tBodies[0];
                        //alert(tbodyCT.childNodes);
                        var lengTbdChl = tbodyCT.childNodes.length;

                        if (lengTbdChl > 2) {
                            for (var j = lengTbdChl - 2;0 < j;--j) {
                                tbodyCT.removeChild(tbodyCT.childNodes[j]);
                                //alert("Co xoa tr em "+j);
                            }
                        }
                        //Reset gia tri trong COA, Style
                        resetLTTDiCOA();
                        if (rowDetails != 0) {
                            for (var i = 0;i < rowDetails;i++) {
                                //for(var i=rowDetails-1; i>=0; i++){ //se bi loi 
                                var obj = null;
                                if (i != 0) {
                                    //copy dong dau tien        
                                    if (root == null) {
                                        root = document.getElementById('tblThongTinChiTietCOA').getElementsByTagName('tr')[1].parentNode;
                                    }
                                    obj = root.getElementsByTagName('tr')[1].cloneNode(true);
                                    //Dat lai cac ham javascript
                                    obj.getElementsByTagName('td')[1].innerHTML = obj.getElementsByTagName('td')[1].innerHTML.replace("taoTaiKhoanFromCOA();", "");
                                    obj.getElementsByTagName('td')[2].innerHTML = obj.getElementsByTagName('td')[2].innerHTML.replace("taoTaiKhoanFromCOA()", "");
                                    obj.getElementsByTagName('td')[3].innerHTML = obj.getElementsByTagName('td')[3].innerHTML.replace("taoTaiKhoanFromCOA()", "");
                                    obj.id = "coa_" + (i - 1);
                                    root.appendChild(obj);
                                }
                                var rootTR = root.getElementsByTagName('tr')[i + 1];
                                for (var _col = 0;_col < root.getElementsByTagName('tr')[i + 1].getElementsByTagName('td').length;_col++) {
                                    if (_col == 0) {
                                        if (lttCTietJsonObj[i].ma_quy) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ma_quy;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00";
                                        }
                                    }
                                    else if (_col == 1) {
                                        if (lttCTietJsonObj[i].tkkt_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].tkkt_ma;
                                        }
                                        else {
                                            assignValue2Field("tkkt_ma", "0000");
                                        }
                                    }
                                    else if (_col == 2) {
                                        if (lttCTietJsonObj[i].dvsdns_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dvsdns_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000000";
                                        }
                                    }
                                    else if (_col == 3) {
                                        if (lttCTietJsonObj[i].capns_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].capns_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0";
                                        }
                                    }
                                    else if (_col == 4) {
                                        if (lttCTietJsonObj[i].chuongns_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].chuongns_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                        }
                                    }
                                    else if (_col == 5) {
                                        if (lttCTietJsonObj[i].nganhkt_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].nganhkt_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                        }
                                    }
                                    else if (_col == 6) {
                                        if (lttCTietJsonObj[i].ndkt_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ndkt_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000";
                                        }
                                    }
                                    else if (_col == 7) {
                                        if (lttCTietJsonObj[i].dbhc_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dbhc_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00000";
                                        }
                                    }
                                    else if (_col == 8) {
                                        if (lttCTietJsonObj[i].ctmt_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ctmt_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00000";
                                        }
                                    }
                                    else if (_col == 9) {
                                        if (lttCTietJsonObj[i].ma_nguon) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ma_nguon;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00";
                                        }
                                    }
                                    else if (_col == 10) {
                                        if (lttCTietJsonObj[i].kb_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].kb_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000";
                                        }
                                    }
                                    else if (_col == 11) {
                                        if (lttCTietJsonObj[i].du_phong_ma) {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].du_phong_ma;
                                        }
                                        else {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                        }
                                    }
                                    else if (_col == 12) {
                                        if (lttCTietJsonObj[i].dien_giai == null || lttCTietJsonObj[i].dien_giai == 'undefined') {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = '';
                                        }
                                        else 
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dien_giai;
                                    }
                                    else if (_col == 13) {
                                        if (lttCTietJsonObj[i].so_tien == '') {
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0";
                                        }
                                        else 
                                            rootTR.getElementsByTagName('td')[_col].childNodes[0].value = CurrencyFormatted(lttCTietJsonObj[i].so_tien, nt_code);
                                    }
                                }
                            }
                            //end for
                        }
                        else {
                            // neu khong co dong chi tiet cat so tk nhan
                            //************* Click nut Hoan thien *************
                            var so_tk_nhan = jQuery("#so_tk_nhan").val();
                            var tktn_com = "0000";
                            var capns_com = "0";
                            var dvsdns_com = "0000000";
                            var ctmt_com = "00000";
                            if (so_tk_nhan.length == 12) {
                                tktn_com = so_tk_nhan.substr(0, 4);
                                capns_com = so_tk_nhan.substr(4, 1);
                                dvsdns_com = so_tk_nhan.substr(5, 7);
                            }
                            else if (so_tk_nhan.length == 11) {
                                tktn_com = so_tk_nhan.substr(0, 4);
                                dvsdns_com = so_tk_nhan.substr(4, 7);
                            }
                            else if (so_tk_nhan.length == 16) {
                                tktn_com = so_tk_nhan.substr(0, 4);
                                dvsdns_com = so_tk_nhan.substr(4, 7);
                                ctmt_com = so_tk_nhan.substr(11, 5);
                            }
                            else if (so_tk_nhan.length == 17) {
                                tktn_com = so_tk_nhan.substr(0, 4);
                                capns_com = so_tk_nhan.substr(4, 1);
                                dvsdns_com = so_tk_nhan.substr(5, 7);
                                ctmt_com = so_tk_nhan.substr(12, 5);
                            }
                            var ma_nhkb = jQuery("#ma_kb_sotai").val();                            
                            jQuery.each(arrFieldIdLttCTiet, function (index, value) {
                                strId = value.toString();
                                assignStyle2Field(strId, true);
                                if (document.getElementById(strId) != null) {
                                    if (strId == 'so_tien') {
                                        setClass2Field(strId, "fieldTextRightCode");
                                        jQuery("#so_tien").attr("readonly", true);
                                    }
                                    else if (strId == 'tkkt_ma') {
                                        setClass2Field(strId, "fieldTextCenterCode");
                                        jQuery("#tkkt_ma").attr("readonly", true);
                                    }
                                    else if (strId == 'dvsdns_ma') {
                                        setClass2Field(strId, "fieldTextCenterCode");
                                        jQuery("#dvsdns_ma").attr("readonly", true);
                                    }
                                    else {
                                        setClass2Field(strId, "fieldTextCenter");
                                        jQuery("#" + strId).removeAttr("readonly");
                                    }
                                }
                            });
                            jQuery("#ma_quy").val('01');
                            jQuery("#tkkt_ma").val(tktn_com);
                            jQuery("#capns_ma").val(capns_com);
                            jQuery("#capns_ma").attr( {
                                'readOnly' : false
                            });
                            jQuery("#capns_ma").removeAttr('disbled');
                            setClass2Field("capns_ma", "fieldTextCenter");
                            jQuery("#capns_ma").focus();
                            jQuery("#dvsdns_ma").val(dvsdns_com);
                            jQuery("#chuongns_ma").val('000');
                            jQuery("#nganhkt_ma").val('000');
                            jQuery("#ndkt_ma").val('0000');
                            jQuery("#dbhc_ma").val('00000');
                            jQuery("#ctmt_ma").val(ctmt_com);
                            jQuery("#ma_nguon").val('00');
                            jQuery("#du_phong_ma").val('000');
                            jQuery("#so_tien").val(jQuery("#tong_sotien").val());
                            jQuery("#kb_ma").val(ma_nhkb);

                        }
                    }
                }

            }
            //Change style row in master
            if (tr_id != null && tr_id != "") {
                var row_id_select_b4 = document.getElementById('row_select').value;
                jQuery("#" + row_id_select_b4).attr( {
                    'class' : 'ui-widget-content ui-row-ltr'
                });
                jQuery("#" + row_id_select_b4).find('input').attr( {
                    'class' : ''
                });
                jQuery("#" + tr_id).attr( {
                    'class' : 'ui-row-ltr ui-state-highlight'
                });
                jQuery("#" + tr_id).find('input').attr( {
                    'class' : 'ui-state-highlight'
                });
                document.getElementById('row_select').value = tr_id;
                if (userType.indexOf('TTV') !=  - 1) {
                    jQuery("#" + tr_id).find('input').focus();
                }
                else if (userType.indexOf('KTT') !=  - 1) {
                    if (trangThai == '01' || trangThai == '05' || trangThai == '06' || trangThai == '07') {
                        jQuery("#" + tr_id).find('input').focus();
                    }
                }
                else if (userType.indexOf('GD') !=  - 1) {
                    if (trangThai == '04' || trangThai == '06' || trangThai == '07') {
                        jQuery("#" + tr_id).find('input').focus();
                    }
                }
                input_id_last = tr_id.split('row_ltt_')[1];
            }
            //An hien cac nut: Hoan thien, huy; An hien truong ..._day_lai
            if (isTraCuu == 'THOAT' || isTraCuu == 'T4') {
                hideBtnLTTDiDenForFind();
            }
            else {
                if (userType != "" && userType != null) {
                    hideBtnLTTDiDenForFind();  
                    if (strUrl == 'loadLTTDenJsonAction.do' || strUrl == 'loadQuyetToanAction.do') {
                        //                        // kiem tra them truong hop lenh tra lai SO_THAM_CHIEU_GD like 'YY701103xxxxxxxx'
                        //                        var isTraLai = false;
                        //                        if (lttJsonObj.so_tham_chieu_gd != null || lttJsonObj.so_tham_chieu_gd != undefined) {
                        //                            jQuery("#so_tham_chieu_gd").val(lttJsonObj.so_tham_chieu_gd);
                        //                            if (lttJsonObj.so_tham_chieu_gd.substring(2, 8) == '701103') {
                        //                                isTraLai = true;
                        //                                lttGoc_CTietJsonObj = JSON.parse(data[3]);
                        //                            }
                        //                        }                        
                        hideBtnLTTDenByRowMaster(userType, trangThai);
                    }
                    else {
                        var ktvTabmisObj = null;
                        if (data.length == 4) {
                            ktvTabmisObj = data[3] != null ? data[3] : "";
                        }
                        showHideBtnByRowMaster(userType, trangThai, ktvTabmisObj);
                        
                    }
                }
            }
            //thuongdt-20170426 bo sung cho phep duoc in giay gioi thieu
            checkInGioiThieu();
        },
        error : function (textstatus) {
            if (textstatus.timeout != 0) {
                //jQuery("#message" ).html(textstatus.messge);
                jQuery("#message").html(GetUnicode("Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c d&#7919; li&#7879;u : ") + textstatus.responseText);
                jQuery("#dialog-message").dialog("open");
                jQuery("#data-grid").show();
            }
            else {
                jQuery("#message").html(GetUnicode("Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c d&#7919; li&#7879;u : ") + textstatus.responseText);
                jQuery("#dialog-message").dialog("open");
                jQuery("#data-grid").show();
                //                alert(GetUnicode("Ng&#432;&#7901;i kh&#225;c &#273;&#227; &#273;&#259;ng nh&#7853;p b&#7857;ng user c&#7911;a b&#7841;n \n\n Ho&#7863;c b&#7841;n &#273;&#259;ng nh&#7853;p v&#224;o t&#7915; l&#226;u,\n h&#7879; th&#7889;ng t&#7921; &#273;&#7897;ng tho&#225;t &#273;&#7875; &#273;&#7843;m b&#7843;o an to&#224;n!"));
                //                document.forms[0].action = 'logoutAction.do';
                //                document.forms[0].submit();
            }
    
        }      /* Add this line */
       
    });    
}

  //Function kiem tra va hien thi in giay gioi thieu.
  function checkInGioiThieu(){ 
  var ttloai_lenh = jQuery("#ttloai_lenh").val();
  var id = jQuery("#id").val();
   var TrangThai = jQuery("#trang_thai").val();   
   if(ttloai_lenh=='02' && id.indexOf('701') == 2 && (TrangThai =='04'|| TrangThai =='05' || TrangThai =='08'|| TrangThai =='14'|| TrangThai =='15' || TrangThai =='16')){
      jQuery("#print").show();  
   }else{
      jQuery("#print").hide();   
   }
  }
 

function createCOAFromId() {
    var id = jQuery("#idTraLai");
    if (id == undefined || id.val() == '') {
        if (jQuery("#so_tham_chieu_gd").val() == '') {
            alert(GetUnicode("B&#7841;n ph&#7843;i nh&#7853;p s&#7889; ID c&#7911;a l&#7879;nh g&#7889;c!"));
            jQuery("#idTraLai").focus();
            return false;
        }
        else {
            id = jQuery("#so_tham_chieu_gd");
        }
    }

    var idLenhGoc = id.val();
    // Neu lenh khong phai lenh di thong bao sai   
    
    if (idLenhGoc.substr(2, 3) != 701) {
        alert(GetUnicode("Kh&#244;ng &#273;&#250;ng &#273;&#7883;nh d&#7841;ng l&#7879;nh thanh to&#225;n &#273;i. \n Ki&#7875;m tra l&#7841;i s&#7889; l&#7879;nh!"));
        return false;
    }
    if (idLenhGoc != undefined) {
        // thuc hien tim COA cua lenh truyen vao
        // neu khong co thi thong bao
        jQuery.ajax( {
            type : "POST", url : "COAFromSOTC.do", data :  {
                "ltt_id" : idLenhGoc, "nd" : Math.random() * 100000
            },
            dataType : 'json', success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data == null) {
                        alert(GetUnicode("Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c d&#7919; li&#7879;u : ") + textstatus.messge);
                        return;
                    }
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.error != undefined) {
                            jQuery("#dialog-message").html(data.error);
                            jQuery("#dialog-message").dialog("open");
                        }
                        else {
                            var lttCTietJsonObj = new Object();
                            lttCTietJsonObj = JSON.parse(data[0]);
                            lttCtietDVQHNSObj = JSON.parse(data[1]);
                            jQuery("#so_tk_dvnhan").val(lttCtietDVQHNSObj.ten);
                            countLttCTiet = 0;
                            if (lttCTietJsonObj != null)
                                countLttCTiet = lttCTietJsonObj.length;
                            //Thong tin chi tiet COA
                            var e = document.getElementById("nt_id");
                            var nt_code = e.options[e.selectedIndex].text;
                            var tableCT = document.getElementById('tblThongTinChiTietCOA');
                            var lastRow = tableCT.rows.length;
                            var tabrow = 47;

                            if (parseFloat(lastRow) > 1) {
                                tabrow += (lastRow - 2) * 8;
                            }
                            //Lay dong dau tien              
                            var root = tableCT.getElementsByTagName('tr')[1].parentNode;

                            var rowDetails = countLttCTiet;
                            //alert(rowDetails);
                            var tbodyCT = tableCT.tBodies[0];
                            //alert(tbodyCT.childNodes);
                            var lengTbdChl = tbodyCT.childNodes.length;

                            if (lengTbdChl > 2) {
                                for (var j = lengTbdChl - 2;0 < j;--j) {
                                    tbodyCT.removeChild(tbodyCT.childNodes[j]);
                                    //alert("Co xoa tr em "+j);
                                }
                            }
                            //Reset gia tri trong COA, Style
                            //                          resetLTTDiCOA();
                            if (rowDetails != 0) {

                                for (var i = 0;i < rowDetails;i++) {
                                    //for(var i=rowDetails-1; i>=0; i++){ //se bi loi 
                                    var obj = null;
                                    if (i != 0) {
                                        //copy dong dau tien        
                                        if (root == null) {
                                            root = document.getElementById('tblThongTinChiTietCOA').getElementsByTagName('tr')[1].parentNode;
                                        }
                                        obj = root.getElementsByTagName('tr')[1].cloneNode(true);
                                        //Dat lai cac ham javascript
                                        obj.getElementsByTagName('td')[1].innerHTML = obj.getElementsByTagName('td')[1].innerHTML.replace("taoTaiKhoanFromCOA();", "");
                                        obj.getElementsByTagName('td')[2].innerHTML = obj.getElementsByTagName('td')[2].innerHTML.replace("taoTaiKhoanFromCOA()", "");
                                        obj.getElementsByTagName('td')[3].innerHTML = obj.getElementsByTagName('td')[3].innerHTML.replace("taoTaiKhoanFromCOA()", "");
                                        obj.id = "coa_" + (i - 1);
                                        root.appendChild(obj);
                                    }
                                    var rootTR = root.getElementsByTagName('tr')[i + 1];
                                    for (var _col = 0;_col < root.getElementsByTagName('tr')[i + 1].getElementsByTagName('td').length;_col++) {
                                        if (_col == 0) {
                                            if (lttCTietJsonObj[i].ma_quy) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ma_quy;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00";
                                            }
                                        }
                                        else if (_col == 1) {
                                            if (lttCTietJsonObj[i].tkkt_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].tkkt_ma;
                                            }
                                            else {
                                                assignValue2Field("tkkt_ma", "0000");
                                            }
                                        }
                                        else if (_col == 2) {
                                            if (lttCTietJsonObj[i].dvsdns_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dvsdns_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000000";
                                            }
                                        }
                                        else if (_col == 3) {
                                            if (lttCTietJsonObj[i].capns_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].capns_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0";
                                            }
                                        }
                                        else if (_col == 4) {
                                            if (lttCTietJsonObj[i].chuongns_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].chuongns_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                            }
                                        }
                                        else if (_col == 5) {
                                            if (lttCTietJsonObj[i].nganhkt_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].nganhkt_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                            }
                                        }
                                        else if (_col == 6) {
                                            if (lttCTietJsonObj[i].ndkt_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ndkt_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000";
                                            }
                                        }
                                        else if (_col == 7) {
                                            if (lttCTietJsonObj[i].dbhc_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dbhc_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00000";
                                            }
                                        }
                                        else if (_col == 8) {
                                            if (lttCTietJsonObj[i].ctmt_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ctmt_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00000";
                                            }
                                        }
                                        else if (_col == 9) {
                                            if (lttCTietJsonObj[i].ma_nguon) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].ma_nguon;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "00";
                                            }
                                        }
                                        else if (_col == 10) {
                                            if (lttCTietJsonObj[i].kb_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].kb_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0000";
                                            }
                                        }
                                        else if (_col == 11) {
                                            if (lttCTietJsonObj[i].du_phong_ma) {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].du_phong_ma;
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "000";
                                            }
                                        }
                                        else if (_col == 12) {
                                            if (lttCTietJsonObj[i].dien_giai == null || lttCTietJsonObj[i].dien_giai == 'undefined') {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = '';
                                            }
                                            else 
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = lttCTietJsonObj[i].dien_giai;
                                        }
                                        else if (_col == 13) {
                                            if (lttCTietJsonObj[i].so_tien == '') {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = "0";
                                            }
                                            else {
                                                rootTR.getElementsByTagName('td')[_col].childNodes[0].value = CurrencyFormatted(lttCTietJsonObj[i].so_tien, nt_code);
                                            }
                                        }
                                    }
                                }
                                //end for
                            }
                        }
                    }
                }
            }
        });
    }
}

function assignValue2Field(idField, strValue) {
    if (idField != null && strValue != null) {
        if (document.getElementById(idField) != null && document.getElementById(idField).value != 'undefined') {
            document.getElementById(idField).value = strValue;
        }
        else {
            //alert("Chua co truong " + idField);
        }
    }
}

function assignStyle2Field(idField, value) {
    if (idField != null) {
        if (document.getElementById(idField) != null) {
            document.getElementById(idField).readOnly = value
        }
        else {
            //alert("Chua co truong " + idField);
        }
    }
}

function setClass2Field(idField, strClassName) {
    jQuery("#" + idField).attr( {
        'class' : strClassName
    });
}

function convertDateDB2Form(arrayStrDate) {
    if (arrayStrDate != null && arrayStrDate != "" && arrayStrDate.indexOf("-", 0) >  - 1) {
        var array_date = arrayStrDate.split('-');
        var nam = array_date[0];
        var thang = array_date[1];
        if (thang.length == 1)
            thang = "0" + thang.toString();
        var ngay = "";
        if (array_date[2].indexOf(" ") >  - 1) {
            array_date = array_date[2].split(" ");
            ngay = array_date[0];
        }
        if (ngay.length == 1)
            ngay = "0" + ngay.toString();
        var strDate = ngay + "/" + thang + "/" + nam;
        return strDate;
    }
    else {
        return "";
    }
}

function createCurDate() {
    var curDate = new Date();
    return curDate;
}
/*
   * strDate: dd/MM/yyyy
   * number: YYYYMMDD
   * */
function convertDate2Number(strDate) {
    if (strDate == null || strDate == '' || strDate == 'undefined' || strDate == 0)
        return '';

    return strDate.format("YYYYMMDD");
}

function convertNumber2ddmmyyyy(strDate) {
    var result = '';
    if (isNaN(strDate) || strDate == 'undefined' || strDate == 0) {
        return '';
    }
    else if (strDate.length == 8) {
        var nam = strDate.substring(0, 4);
        var thang = strDate.substring(4, 6);
        var ngay = strDate.substring(6, 8);
        result = ngay + "/" + thang + "/" + nam;
    }
    return result;
}

function readOnlyFields(arrTextFieldId, bValue) {
    var strId = '';
    jQuery.noConflict();
    jQuery.each(arrTextFieldId, function (index, value) {
        strId = value.toString();
        jQuery("#" + strId).attr("readonly", bValue);
    });
}

function removeReadOnly2Fields(arrTextFieldId) {
    var strId = '';
    jQuery.noConflict();
    jQuery.each(arrTextFieldId, function (index, value) {
        strId = value.toString();
        if (document.getElementById(strId) != null) {
            jQuery("#" + strId).removeAttr("readOnly");
        }
    });
}

function disableFields(arrTextFieldId, bValue) {
    var strId = '';
    jQuery.noConflict();
    jQuery.each(arrTextFieldId, function (index, value) {
        strId = value.toString();
        if (document.getElementById(strId) != null) {
            document.getElementById(strId).disable = bValue;
        }
    });
}

function clearForm(arrTextFieldId) {
    var strId = '';
    jQuery.noConflict();
    jQuery.each(arrTextFieldId, function (i, l) {
        //alert('cuc cu ' + i +' '+'document.getElementById\('+l.toString()+'\).value');
        strId = l.toString();
        if (document.getElementById(strId) != null) {
            document.getElementById(strId).value = '';
        }
        strId = '';
    });
    //    if(arrFieldId != null && arrFieldId.length){
    //      var leng = arrFieldId.length;
    //      var strId='';
    //      for(var i=0; i <leng; i=i+1){
    //        strId = arrFieldId[i].toString();
    //        if(strId != null && strId != ''){
    //          strId = strId;
    //          //alert('cuc cu ' + i +' '+ strId+' '+'document.getElementById\('+strId+'\).value');
    //          if(document.getElementById(strId) != null && document.getElementById(strId).value != 'undefined'){
    //            document.getElementById(strId).value = '';
    //          }  
    //          strId = '';
    //        }
    //      }
    //    }
}

function fireEvent(element, event) {
    var evt = null;
    if (document.createEvent) {
        // dispatch for firefox + others
        evt = document.createEvent("HTMLEvents");
        evt.initEvent(event, true, true);// event type,bubbling,cancelable
        return !element.dispatchEvent(evt);
    }
    else {
        // dispatch for IE
        evt = document.createEventObject();
        return element.fireEvent('on' + event, evt)
    }
}

//Ham nay dung de add va remove row cua table dong
function removeDetailCOARow(src) {
    if (!jQuery("#tkkt_ma").attr("readOnly")) {
        var oRow = src.parentElement.parentElement;
        if (parseFloat(oRow.rowIndex) > 1) {
            document.all("tblThongTinChiTietCOA").deleteRow(oRow.rowIndex);
            cal_tongtienCOA();
        }
    }
}

/**
 * Duoc dung trong Lenh Thanh Toan
 * @param pNumber
 * @returns
 */
/*function toNumberTTSP(pNumber) {
    s = new String(pNumber);
    while (s.indexOf('.') >= 0)
        s = s.replace('.', '');
    while (s.indexOf(',') >= 0)
        s = s.replace(',', '');

    return s;
}*/

function formatNumberCOAJQuery(obj) {
    var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
    var nt_id_old = '';
    try{
      nt_id_old = jQuery("#nt_id_old").val(); 
    }catch(ex){
      nt_id_old = '';
      alert(ex);
    }

    if (nt_id == null || '' == nt_id || nt_id == 'undefined')
        nt_id = 'VND';
        
    if (nt_id_old == null || '' == nt_id_old || nt_id_old == 'undefined')
        nt_id_old = nt_id;
     
    var final_value = convertCurrencyToNumber(obj.value,nt_id_old);   
    
    var formated_value = "0";
    //BEGIN-manhnv-20150803: Cho phep nhap so am
    //if(parseFloat(final_value) > 0){    
      formated_value = CurrencyFormatted(final_value, nt_id);    
    //}
    //END-manhnv-20150803
    obj.value = formated_value;
    try{
      document.getElementById("nt_id_old").value = nt_id;
    }catch(ex){
      alert(ex);
    }
//    document.getElementById("tong_sotien").value = obj.value;
}
function formatNumberJQuery(so_tien_field, ma_nt_field, ma_nt_old_field) {
    var nt_id = jQuery("#"+ma_nt_field).val();    
    var nt_id_old = jQuery("#"+ma_nt_old_field).val(); 
    var obj = jQuery("#"+so_tien_field);
    
    var final_value = "0";
    
    if (nt_id == null || '' == nt_id || nt_id == 'undefined')
        nt_id = 'VND';
        
    if (nt_id_old != null && nt_id_old != '' && nt_id_old != 'undefined'){
      final_value = convertCurrencyToNumber(obj.val(),nt_id_old);   
    }else{
      final_value = obj.val();
    }
    var formated_value = "0";
    
    if(parseFloat(final_value) > 0){
      formated_value = CurrencyFormatted(final_value, nt_id);
    }
    obj.val(formated_value);
    
    try{
      jQuery("#"+ma_nt_old_field).val(nt_id);
    }catch(ex){
      alert(ex);
    }
//    document.getElementById("tong_sotien").value = obj.value;
}
function formatNumberCOAJQueryDivTimkiem() {
    var nt_id = jQuery("#nt_id_find").val();
    var nt_id_old = jQuery("#nt_id_find_old").val();    
    var obj = document.getElementById("sotien");
        
    if (nt_id == null || '' == nt_id || nt_id == 'undefined')
        nt_id = 'VND';
    if (nt_id_old == null || '' == nt_id_old || nt_id_old == 'undefined')
        nt_id_old = nt_id;
   if(obj.value !=''){
    var final_value = convertCurrencyToNumber(obj.value,nt_id_old);    
   
    var formated_value = CurrencyFormatted(final_value, nt_id);
  
    obj.value = formated_value;
    document.getElementById("tong_sotien").value = obj.value;
   }
    document.getElementById("nt_id_find_old").value = nt_id;
}
function formatTienTe(strTongTien, strNT_Id) {
    var nt_id = strNT_Id;
    if (isNaN(strTongTien)) {
        obj.value = '';
        return '';
    }
    var final_value = strTongTien;
    var formated_value = 0;
    try {
        if (nt_id == 'VND') {
            formated_value = jQuery().number_format(final_value, 
            {
                numberOfDecimals : 0, decimalSeparator : ',', thousandSeparator : '.', symbol : ''
            });
        }
        else {
            formated_value = jQuery().number_format(final_value, 
            {
                numberOfDecimals : 0, decimalSeparator : '.', thousandSeparator : ',', symbol : ''
            });
        }
    }
    catch (e) {
        formated_value = '';
    }
    if (formated_value == '0.00' || formated_value == '0') {
        formated_value = '';
    }

    return formated_value;
}
// Tinh tong so tien =sum(sotien cua tat ca cac dong)
function cal_tongtienCOA2() {
    var i;
    var TONG_TIEN_MAX = '999999999999';
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var rowOnTable = tbl.rows.length - 1;
    var tong_tien = 0;
    var decimals = 0;
    var nt_id = 'VND';//VND
    if (document.forms[0].nt_id != null)
        nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;

    if (rowOnTable > 1) {
        var sotienTemp = 0;
        for (i = 0;i < rowOnTable;i++) {
            var sotien = document.forms[0].so_tien[i].value;

            sotienTemp = 0;
            sotienTemp = parseFloat(toNumber1(sotien));
            if (sotienTemp > TONG_TIEN_MAX) {
                document.forms[0].tong_sotien.value = toFormatNumberDe_TTSP(toNumber1(sotienTemp), decimals, nt_id);
            }
            tong_tien += sotienTemp;
        }
        try {
            tong_tien = Math.round(toNumber1(tong_tien));
            //alert(tong_tien);
        }
        catch (e) {
            tong_tien = 0;
        }
        if (tong_tien >= TONG_TIEN_MAX) {
            document.forms[0].tong_sotien.value = toFormatNumberDe_TTSP(toNumber1(tong_tien), decimals, nt_id);
            return;
        }
    }
    else {
        tong_tien = document.getElementsByName('so_tien')[0].value;
        try {
            tong_tien = Math.round(toNumber1(tong_tien));
        }
        catch (e) {
            tong_tien = 0;
        }

        if (tong_tien >= TONG_TIEN_MAX) {
            document.forms[0].tong_sotien.value = toFormatNumberDe_TTSP(toNumber1(tong_tien), decimals, nt_id);
            //document.forms[0].so_tien.focus();
            return;
        }
    }
    if (tong_tien == null || tong_tien == "") {
        tong_tien = 0;
    }

    tong_tien = formatTienTe(tong_tien, nt_id);

    document.getElementById('tong_sotien').value = tong_tien;
}

function cal_tongtienCOA() { 
   //Neu la form hoan thien LTT thi ko can tinh lai tong COA(manhNV)   
   var objFormType = document.getElementById('form_type');      
   
   if(objFormType != null && objFormType != 'undefined' && objFormType != ''){
     if(objFormType.value == 'hoan_thien_ltt_di'){
       return;        
     }
   }
   //******************************************************

    var i;
    var TONG_TIEN_MAX = '999999999999';
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var rowOnTable = tbl.rows.length - 1;
    var tong_tien = 0;
    var decimals = 0;
    var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
    if(nt_id == '' || nt_id == null){
      nt_id = 'VND';//VND
    }
    if (document.forms[0].nt_id != null)
        nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
    
    if (rowOnTable > 1) {
        var sotienTemp = 0;
        for (i = 0;i < rowOnTable;i++) {
            var sotien = document.forms[0].so_tien[i].value;

            sotienTemp = 0;            
            sotienTemp = convertCurrencyToNumber(sotien, nt_id);
            tong_tien += sotienTemp;  
        }
    }else {      
        tong_tien = convertCurrencyToNumber(document.getElementsByName('so_tien')[0].value, nt_id);     
    }
//    try {
//    tong_tien = convertCurrencyToNumber(tong_tien, nt_id);
//        
//    }
//    catch (e) {    
//        tong_tien = 0;
//    }    
    tong_tien = CurrencyFormatted(tong_tien, nt_id);        
    document.getElementById('tong_sotien').value = tong_tien;
}

/*
   * Tao ra tai khoan nguoi chuyen tu dong dau tien trong COA
   * @return soTK = TKTN(04 Char)+MaCapNS(01Char)+DVQHNS(07 Char)
   * */

function taoTaiKhoanFromCOA() {
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;

    var tkNguoiChuyen = '';
    var ma_nhkb_chuyen_cm = jQuery("#ma_nhkb_chuyen_cm").val();
    var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
    //nhkb chuyen o thong tin chung TRUNG voi Mo tai NHKB tt nguoi chuyen
    if (ma_nhkb_chuyen != '' && ma_nhkb_chuyen_cm == ma_nhkb_chuyen) {
        var tktn = "";
        var maCapNS = "";
        var dvqhns = "";

        if (lastRow > 1) {
            //        tktn = jQuery("#tkkt_ma").val();
            //        maCapNS = jQuery("#capns_ma").val();
            //        dvqhns = jQuery("#dvsdns_ma").val();
            oTktn = document.getElementsByName('tkkt_ma');
            oMaCapNS = document.getElementsByName('capns_ma');
            oMaDvsdns = document.getElementsByName('dvsdns_ma');
            var existSpecialCode = false;
            if (oTktn.length > 0) {
                for (var i = 0;i < oTktn.length;i++) {
                    for (var j = 0;j < arrSpecialTKTN.length;j++) {
                        if (oTktn[i].value == arrSpecialTKTN[j]) {
                            tktn = arrSpecialTKTN[j];
                            existSpecialCode = true;
                            break;
                        }
                    }
                }
                if (tktn == "") {
                    if (oTktn.length == 1) {
                        tktn = oTktn.item(0).value;
                    }
                    else if (oTktn.length > 1) {
                        tktn = oTktn[0].value;
                    }
                }
                maCapNS = oMaCapNS[0].value;
                dvqhns = oMaDvsdns[0].value;

                if (tktn != '' && tktn != 'undefined') {
                    if (existSpecialCode) {
                        tkNguoiChuyen = tktn + "0" + "0000000";
                    }
                    else if (maCapNS != '' && maCapNS != 'undefined' && dvqhns != '' && dvqhns != 'undefined') {
                        tkNguoiChuyen = tktn + maCapNS + dvqhns;
                    }
                    else if ((maCapNS == '' || maCapNS == 'undefined') && dvqhns != '' && dvqhns != 'undefined') {
                        tkNguoiChuyen = tktn + "0" + dvqhns;
                    }
                    else if (maCapNS != '' && maCapNS != 'undefined' && (dvqhns == '' || dvqhns == 'undefined')) {
                        tkNguoiChuyen = tktn + maCapNS + "0000000";
                    }
                    else if ((maCapNS == '' || maCapNS == 'undefined') && (dvqhns == '' || dvqhns == 'undefined')) {
                        tkNguoiChuyen = tktn + "0" + "0000000";
                    }
                }
            }
        }

        jQuery("#so_tk_chuyen").val(tkNguoiChuyen);
        if (tkNguoiChuyen != '') {
            jQuery("#so_tk_chuyen").attr( {
                'readOnly' : true
            });
        }
    }
    else if (ma_nhkb_chuyen_cm != ma_nhkb_chuyen) {
        //nhkb chuyen o thong tin chung khong trung Mo tai NHKB tt nguoi chuyen
        jQuery("#so_tk_chuyen").removeAttr('readOnly');
    }
}

function taoTaiKhoanCOA() {
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;

    var tkNguoiChuyen = '';
    var ma_nhkb_chuyen_cm = jQuery("#ma_nhkb_chuyen_cm").val();
    var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
    //nhkb chuyen o thong tin chung TRUNG voi Mo tai NHKB tt nguoi chuyen
    if (ma_nhkb_chuyen != '' && ma_nhkb_chuyen_cm == ma_nhkb_chuyen) {
        var tktn = "";
        var maCapNS = "";
        var dvqhns = "";

        if (lastRow > 1) {
            //        tktn = jQuery("#tkkt_ma").val();
            //        maCapNS = jQuery("#capns_ma").val();
            //        dvqhns = jQuery("#dvsdns_ma").val();
            oTktn = document.getElementsByName('tkkt_ma');
            oMaCapNS = document.getElementsByName('capns_ma');
            oMaDvsdns = document.getElementsByName('dvsdns_ma');
            var existSpecialCode = false;
            if (oTktn.length > 0) {
                for (var i = 0;i < oTktn.length;i++) {
                    for (var j = 0;j < arrSpecialTKTN.length;j++) {
                        if (oTktn[i].value == arrSpecialTKTN[j]) {
                            tktn = arrSpecialTKTN[j];
                            existSpecialCode = true;
                            break;
                        }
                    }
                }
                if (tktn == "") {
                    if (oTktn.length == 1) {
                        tktn = oTktn.item(0).value;
                    }
                    else if (oTktn.length > 1) {
                        tktn = oTktn[0].value;
                    }
                }
                maCapNS = oMaCapNS[0].value;
                dvqhns = oMaDvsdns[0].value;

                if (tktn != '' && tktn != 'undefined') {
                    if (existSpecialCode) {
                        tkNguoiChuyen = tktn + "0" + "0000000";
                    }
                    else if (maCapNS != '' && maCapNS != 'undefined' && dvqhns != '' && dvqhns != 'undefined') {
                        tkNguoiChuyen = tktn + maCapNS + dvqhns;
                    }
                    else if ((maCapNS == '' || maCapNS == 'undefined') && dvqhns != '' && dvqhns != 'undefined') {
                        tkNguoiChuyen = tktn + "0" + dvqhns;
                    }
                    else if (maCapNS != '' && maCapNS != 'undefined' && (dvqhns == '' || dvqhns == 'undefined')) {
                        tkNguoiChuyen = tktn + maCapNS + "0000000";
                    }
                    else if ((maCapNS == '' || maCapNS == 'undefined') && (dvqhns == '' || dvqhns == 'undefined')) {
                        tkNguoiChuyen = tktn + "0" + "0000000";
                    }
                }
            }
        }

        jQuery("#so_tk_chuyen").val(tkNguoiChuyen);
        if (tkNguoiChuyen != '') {
            jQuery("#so_tk_chuyen").attr( {
                'readOnly' : true
            });
        }
    }
    else if (ma_nhkb_chuyen_cm != ma_nhkb_chuyen) {
        //nhkb chuyen o thong tin chung khong trung Mo tai NHKB tt nguoi chuyen
        jQuery("#so_tk_chuyen").removeAttr('readOnly');
    }
}
// tu dong fill ten len truong don vi chuyen muc thong tin nguoi chuyen
function taoTenDVNhan(value) {

    if (value != null && '' != value && value.length == 7) {
        jQuery.ajax( {
            type : "POST", url : "fillTenFromCOAAction.do", data :  {
                "ma_dvns" : value, "nd" : Math.random() * 100000
            },
            dataType : 'json', async : true, success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data == null) {
                        jQuery("#ten_tk_chuyen").val('');
                        return;
                    }
                    else {
                        jQuery("#ten_tk_chuyen").val(data.ten);
                    }
                }
            }
        });
    }
}

function focusEveryTKTN(isForLTTDen) {
    var lTTDen = false;
    if (isForLTTDen != null || isForLTTDen != 'undefined') {
        lTTDen = isForLTTDen;
    }
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;
    var result = false;
    if (lastRow > 1) {
        oTktn = document.getElementsByName('tkkt_ma');
        if (oTktn.length > 0) {
            var resultTmp = false;
            var strResultCheck = "";
            for (var i = 0;i < oTktn.length;i++) {
                if (oTktn[i] != null) {
                    resultTmp = checkDMTKCheo(oTktn[i], oTktn[i].parentNode.parentNode, lTTDen);
                }
                strResultCheck += resultTmp + '';
            }
            if (strResultCheck.indexOf("false") ==  - 1) {
                result = true;
            }
        }
    }
    return result;
}
// chuc
//function focusEveryTKTNLTTDen(isForLTTDen) {
//    var lTTDen = false;
//    if (isForLTTDen != null || isForLTTDen != 'undefined') {
//        lTTDen = isForLTTDen;
//    }
//    var tbl = document.getElementById('tblThongTinChiTietCOA');
//    var lastRow = tbl.rows.length;
//    var result = false;
//    if (lastRow > 1) {
//        oTktn = document.getElementsByName('tkkt_ma');
//        oMaCapNS = document.getElementsByName('capns_ma');
//        oMaChuong = document.getElementsByName('chuongns_ma');
//        oMaChuong[0].focus();
//        if (oTktn.length > 0) {
//            var resultTmp = false;
//            var strResultCheck = "";
//            for (var i = 0;i < oTktn.length;i++) {
//                if (oTktn[i] != null) {
//                    alert(oTktn[i].value);
//                    alert(oMaChuong[i].value);
//                    
//                    //oTktn[i].focus();
//                    //jQuery('#ten_tk_chuyen').focus();
//                    resultTmp = checkDMTKCheo(oTktn[i], oTktn[i].parentNode.parentNode, lTTDen);
//                }
//                strResultCheck += resultTmp + '';
//            }
//            if (strResultCheck.indexOf("false") ==  - 1) {
//                result = true;
//            }
//        }
//    }
//    return result;
//}
function checkExistSpecialTKTN() {
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;
    var result = false;
    if (lastRow > 1) {
        oQhdvns = document.getElementsByName('dvsdns_ma');
        if (oQhdvns.length > 0) {
            var resultTmp = false;
            var strResultCheck = "";
            for (var i = 0;i < oQhdvns.length;i++) {
                for (var j = 0;j < arrSpecialTKTN.length;j++) {
                    if (oQhdvns[i].value == arrSpecialTKTN[j]) {
                        result = true;
                        break;
                    }
                }
            }
        }
    }
    return result;
}

function checkExistDVQHNSAnNinh() {
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;
    var result = false;
    if (lastRow > 1) {
        oQhdvns = document.getElementsByName('dvsdns_ma');
        if (oQhdvns.length > 0) {
            for (var i = 0;i < oQhdvns.length;i++) {
                for (var j = 0;j < arrDVQHNSAnNinh.length;j++) {
                    if (oQhdvns[i].value.trim() == arrDVQHNSAnNinh[j].trim()) {
                        result = true;
                        break;
                    }
                }
            }
        }
    }
    return result;
}

function caretPos(f) {
    if (f.createTextRange) {
        var r = document.selection.createRange().duplicate();
        r.moveEnd("character", f.value.length);
        return r.text == "" ? f.value.length : f.value.lastIndexOf(r.text)
    }
    else 
        return f.selectionStart;
}

function enterTabFocus2Enable(event, obj) {
    if (obj == null || obj == 'undefined')
        return;
    if (arrFieldIdLttCTiet == null || arrFieldIdLttCTiet == '' || arrFieldIdLttCTiet == 'undefined')
        return;
    if (arrFieldIdLttCTiet.length != 14)
        return;

    var strValue = '';
    var caretpos =  - 1;
    strValue = obj.value;
    if (strValue != '')
        caretpos = caretPos(obj);
    if (event.keyCode == 13)
        event.keyCode = 9;

    // arrow left:37; right:39; up:38 ; down:40
    //if(strValue == '' && event.keyCode == 39) event.keyCode = 9;
    if (event.keyCode == 39 && (strValue == '' || (strValue != '' && obj.value.length == caretpos)))
        event.keyCode = 9;

    //Lay ra vi tri focus hien tai
    var currentIndex =  - 1;
    for (var i = 0;i < arrFieldIdLttCTiet.length;i++) {
        if (obj.name == arrFieldIdLttCTiet[i]) {
            currentIndex = i;
            break;
        }
    }
    //Lay ra dong
    var rowCurrent =  - 1;
    var rowPrevious =  - 1;
    var rowNext =  - 1;
    var idRow = '';
    var objPreviousTmp = null;
    var objCurrentTmp = null;
    var objNextTmp = null;
    var bTmp = false;
    var tbl = null;
    var arrObjCOA = null;
    var countRows = 0;
    while (obj.tagName != 'TR') {
        obj = obj.parentNode;
    }
    idRow = obj.id;
    if (idRow != '') {
        rowCurrent = idRow.replace("coa_", "");
        rowCurrent = parseInt(rowCurrent);
    }
    if (event.keyCode == 37 && (strValue == '' || (strValue != '' && caretpos == 0))) {
        if (currentIndex !=  - 1) {
            if (currentIndex <= 0 || currentIndex > 14)
                return;

            arrObjCOA = getObjectsInTR(obj);

            for (var j = currentIndex;j >= 0;j--) {
                objCurrentTmp = arrObjCOA[j];
                objPreviousTmp = arrObjCOA[j - 1];
                if (objCurrentTmp != null && objPreviousTmp != null && !objPreviousTmp.readOnly) {
                    //objTmp.trigger('focus');
                    objPreviousTmp.focus();
                    break;
                }
                objCurrentTmp = null;
                objPreviousTmp = null;
            }
        }
        event.keyCode = 0;
    }
    else if (event.keyCode == 38) {
        if (rowCurrent > 0 && currentIndex !=  - 1) {
            rowPrevious = rowCurrent - 1;
            tbl = document.getElementById('tblThongTinChiTietCOA');
            var objTrPreviousTmp = null;
            //var objTemp = tbl.rows.item(rowPrevious);
            for (j = rowCurrent;j > 0;--j) {
                objTrPreviousTmp = tbl.rows.item(j);
                if (objTrPreviousTmp.tagName != 'TR')
                    return;
                arrObjCOA = getObjectsInTR(objTrPreviousTmp);
                objPreviousTmp = arrObjCOA[currentIndex];
                if (objPreviousTmp != null && !objPreviousTmp.readOnly) {
                    objPreviousTmp.focus();
                    break;
                }
                objPreviousTmp = null;
                objTrPreviousTmp = null;
                arrObjCOA = null;
            }
        }
        event.keyCode = 0;
    }
    else if (event.keyCode == 40) {
        tbl = document.getElementById('tblThongTinChiTietCOA');
        countRows = tbl.rows.length;
        var countRowReal = 0;
        countRowReal = countRows - 1;
        if (rowCurrent < countRowReal && currentIndex !=  - 1) {
            rowNext = rowCurrent + 1;
            var objTrNextTmp = null;
            //var objTemp = tbl.rows.item(rowPrevious);
            for (j = rowNext;j < countRowReal;++j) {
                objTrNextTmp = tbl.rows.item(j + 1);
                if (objTrNextTmp.tagName != 'TR')
                    return;
                arrObjCOA = getObjectsInTR(objTrNextTmp);

                objNextTmp = arrObjCOA[currentIndex];

                if (objNextTmp != null && !objNextTmp.readOnly) {
                    objNextTmp.focus();
                    break;
                }
                objNextTmp = null;
                objTrNextTmp = null;
                arrObjCOA = null;
            }
        }
        //tbl = null;
        event.keyCode = 0;
    }
    else if (event.keyCode == 9) {
        if (currentIndex !=  - 1) {
            if (currentIndex < 0 || currentIndex > 14)
                return;

            arrObjCOA = getObjectsInTR(obj);

            for (j = currentIndex;j < arrObjCOA.length;j++) {
                objCurrentTmp = arrObjCOA[j];
                objNextTmp = arrObjCOA[j + 1];
                if (objCurrentTmp != null && objNextTmp != null && !objNextTmp.readOnly) {
                    //objTmp.trigger('focus');
                    objNextTmp.focus();
                    break;
                }
                objCurrentTmp = null;
                objNextTmp = null;
            }
        }
        event.keyCode = 0;
        window.event.returnValue = false;
    }
}

function enterTabFocus2EnableLTTDen(event, obj) {
    if (obj == null || obj == 'undefined')
        return;
    if (arrFieldIdLttCTiet == null || arrFieldIdLttCTiet == '' || arrFieldIdLttCTiet == 'undefined')
        return;
    if (arrFieldIdLttCTiet.length != 14)
        return;

    var strValue = '';
    var caretpos =  - 1;
    strValue = obj.value;
    if (strValue != '')
        caretpos = caretPos(obj);
    if (event.keyCode == 13)
        event.keyCode = 9;

    // arrow left:37; right:39; up:38 ; down:40
    //if(strValue == '' && event.keyCode == 39) event.keyCode = 9;
    if (event.keyCode == 39 && (strValue == '' || (strValue != '' && obj.value.length == caretpos)))
        event.keyCode = 9;

    //Lay ra vi tri focus hien tai
    var currentIndex =  - 1;
    for (var i = 0;i < arrFieldIdLttCTiet.length;i++) {
        if (obj.name == arrFieldIdLttCTiet[i]) {
            currentIndex = i;
            break;
        }
    }
    //Lay ra dong
    var rowCurrent =  - 1;
    var idRow = '';
    var objCurrentTmp = null;
    var objNextTmp = null;
    var arrObjCOA = null;
    while (obj.tagName != 'TR') {
        obj = obj.parentNode;
    }
    idRow = obj.id;
    if (idRow != '') {
        rowCurrent = idRow.replace("coa_", "");
        rowCurrent = parseInt(rowCurrent);
    }
    else if (event.keyCode == 9) {
        if (currentIndex !=  - 1) {
            if (currentIndex < 0 || currentIndex > 14)
                return;

            arrObjCOA = getObjectsInTR(obj);

            for (j = currentIndex;j < arrObjCOA.length;j++) {
                objCurrentTmp = arrObjCOA[j];
                objNextTmp = arrObjCOA[j + 1];
                if (objCurrentTmp != null && objNextTmp != null && !objNextTmp.readOnly) {
                    //objTmp.trigger('focus');
                    objNextTmp.focus();
                    break;
                }
                objCurrentTmp = null;
                objNextTmp = null;
            }
        }
        event.keyCode = 0;
        window.event.returnValue = false;
    }
}

function getObjectsInTR(obj) {
    if (obj == null || obj == 'undefined')
        return;
    objMaQuy = obj.childNodes[0].childNodes[0];
    objTKTN = obj.childNodes[1].childNodes[0];
    objDVQHNS = obj.childNodes[2].childNodes[0];
    objCapNS = obj.childNodes[3].childNodes[0];
    objChuong = obj.childNodes[4].childNodes[0];
    objNganhKT = obj.childNodes[5].childNodes[0];
    objNDKT = obj.childNodes[6].childNodes[0];
    objDB = obj.childNodes[7].childNodes[0];
    objCTMT = obj.childNodes[8].childNodes[0];
    objMN = obj.childNodes[9].childNodes[0];
    objKBNN = obj.childNodes[10].childNodes[0];
    objDP = obj.childNodes[11].childNodes[0];
    objDienG = obj.childNodes[12].childNodes[0];
    objSoTien = obj.childNodes[13].childNodes[0];

    //Tao Array de lay focus bo qua nhung field read only, khi tktn_ma mat focus
    var arrObj = new Array();
    arrObj[0] = objMaQuy;
    arrObj[1] = objTKTN;
    arrObj[2] = objDVQHNS;
    arrObj[3] = objCapNS;
    arrObj[4] = objChuong;
    arrObj[5] = objNganhKT;
    arrObj[6] = objNDKT;
    arrObj[7] = objDB;
    arrObj[8] = objCTMT;
    arrObj[9] = objMN;
    arrObj[10] = objKBNN;
    arrObj[11] = objDP;
    arrObj[12] = objDienG;
    arrObj[13] = objSoTien;

    return arrObj;
}
//set tabindex COA
var tabContinue = 200;
//Check DM tai khoan cheo
function checkDMTKCheo(obj, objTrParent, isForLTTDen) {
    var tktn = obj.value;

    if (tktn == '' || tktn == 'undefined') {
        return;
    }
    var numberRowCurrent = objTrParent.sectionRowIndex;
    var lTTDen = false;
    if (isForLTTDen != null || isForLTTDen != 'undefined') {
        lTTDen = isForLTTDen;
    }
    //alert(numberRowCurrent);
    while (obj.tagName != 'TR') {
        obj = obj.parentNode;
    }

    objMaQuy = null;
    objTKTN = null;
    objDVQHNS = null;
    objCapNS = null;
    objChuong = null;
    objNganhKT = null;
    objNDKT = null;
    objDB = null;
    objCTMT = null;
    objMN = null;
    objDP = null;
    objKBNN = null;
    objDienG = null;
    objSoTien = null;
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var lastRow = tbl.rows.length;
    var arrObj = getObjectsInTR(obj);

    var arrFocus = new Array(14);
    arrFocus[0] = false;
    var resultCheck = false;
    /*
                             * Author : Chuc
                             * Required : Xuyen 
                             * Des : LTT ??n khi ho�n thi?n ch? ???c s?a Ch??ng, Ng�nh KT, NDKT, ?B, CTMT, MN, DP, Di?n gi?i. 
                             * */
    //    if (!lTTDen) {
    //    var actionForButtonGhi = jQuery("#actionForSave").val();
    //      alert('hanh dong '+actionForButtonGhi);
    if (!objTKTN.readOnly) {
        jQuery.ajax( {
            type : "POST", url : "loadKHCheoTKAction.do", data :  {
                "tktn" : tktn, "nd" : Math.random() * 100000
            },
            dataType : 'json', async : false, success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data != null) {
                        if (data.logout != null && data.logout) {
                            document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                            document.forms[0].submit();
                        }
                        else {

                            //                        if(!lTTDen){
                            //TKTN
                            if (lTTDen) {
                                objMaQuy.className = 'fieldTextCenterCode';
                                objMaQuy.readOnly = true;
                                objTKTN.readOnly = true;
                                objTKTN.className = 'fieldTextCenterCode';
                                arrFocus[1] = false;
                            }
                            else {
                                objTKTN.className = 'fieldTextCenter';
                                objTKTN.readOnly = false;
                                //Ma quy                                            
                                arrFocus[1] = false;
                            }
                            if (lTTDen) {
                                objDVQHNS.readOnly = true;
                                objDVQHNS.className = 'fieldTextCenterCode';
                                arrFocus[2] = false;
                            }
                            else {
                                objDVQHNS.className = 'fieldTextCenter';
                                objDVQHNS.readOnly = false;
                                arrFocus[2] = true;
                            }

                            if (lTTDen) {
                                objCapNS.readOnly = false;
                                objCapNS.className = 'fieldTextCenter';
                                arrFocus[3] = true;
                            }
                            else {
                                objCapNS.className = 'fieldTextCenter';
                                objCapNS.readOnly = false;
                                arrFocus[3] = true;
                            }
                            //Xu ly COA khong cho sua TKTN, QHNS, CapNS, So Tien; 
                            //Neu co Ma TKTN dac biet thi ko cho sua:TKTN, so tien                      
                            if (lTTDen) {
                                var existSpecialTKTN = false;
                                for (var i = 0;i < arrSpecialTKTN.length;i++) {
                                    if (arrSpecialTKTN[i] == objTKTN.value) {
                                        existSpecialTKTN = true;
                                        break;
                                    }
                                }
                                objTKTN.className = 'fieldTextCenterCode';
                                objTKTN.readOnly = true;

                                objSoTien.className = 'fieldTextCenterCode';
                                objSoTien.readOnly = true;

                                if (!existSpecialTKTN) {
                                    objDVQHNS.className = 'fieldTextCenterCode';
                                    objDVQHNS.readOnly = true;
                                    //
                                    //                                    objCapNS.className = 'fieldTextCenterCode';
                                    //                                    objCapNS.readOnly = true;
                                }
                            }
                            else {
                                //Tao So TK Chuyen cho truong hop capns=1 va dvqhns=1
                                if (data.ma_cap == '1' && data.ma_dvsdns == '1') {
                                    var ma_nhkb_chuyen_cm = jQuery("#ma_nhkb_chuyen_cm").val();
                                    var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
                                    if (ma_nhkb_chuyen_cm == ma_nhkb_chuyen) {
                                        var soTKChuyen = tktn + "00000000";
                                        jQuery("#so_tk_chuyen").val(soTKChuyen);
                                    }
                                }
                            }
                            //Chuong
                            if (lTTDen) {
                                objChuong.readOnly = false;
                                arrFocus[4] = false;
                                objChuong.className = 'fieldTextCenter';

                            }
                            else {
                                objChuong.className = 'fieldTextCenter';
                                objChuong.readOnly = false;
                                arrFocus[4] = true;
                            }
                            //Nganh KT
                            if (lTTDen) {
                                objNganhKT.readOnly = false;
                                objNganhKT.className = 'fieldTextCenter';
                                arrFocus[5] = false;
                            }
                            else {
                                objNganhKT.className = 'fieldTextCenter';
                                objNganhKT.readOnly = false;
                                arrFocus[5] = true;
                            }
                            //Noi dung KT
                            if (lTTDen) {
                                objNDKT.readOnly = false;
                                objNDKT.className = 'fieldTextCenter';
                                arrFocus[6] = false;
                            }
                            else {
                                objNDKT.className = 'fieldTextCenter';
                                objNDKT.readOnly = false;
                                arrFocus[6] = true;
                            }
                            //Dia ban
                            if (lTTDen) {
                                objDB.readOnly = false;
                                objDB.className = 'fieldTextCenter';
                                arrFocus[7] = false;
                            }
                            else {
                                objDB.className = 'fieldTextCenter';
                                objDB.readOnly = false;
                                arrFocus[7] = true;
                            }
                            //CTMT
                            if (lTTDen) {
                                objCTMT.readOnly = false;
                                objCTMT.className = 'fieldTextCenter';
                                arrFocus[8] = false;
                            }
                            else {
                                objCTMT.className = 'fieldTextCenter';
                                objCTMT.readOnly = false;
                                arrFocus[8] = true;
                            }
                            //Nguon 
                            if (lTTDen) {
                                objMN.readOnly = false;
                                objMN.className = 'fieldTextCenter';
                                arrFocus[9] = false;
                            }
                            else {
                                objMN.className = 'fieldTextCenter';
                                objMN.readOnly = false;
                                arrFocus[9] = true;

                            }
                            //Kho bac
                            objKBNN.className = 'fieldTextCenter';
                            objKBNN.readOnly = false;
                            if (!lTTDen)
                                arrFocus[10] = true;
                            else 
                                arrFocus[10] = false;
                            //Du phong 
                            if (lTTDen) {
                                objDP.readOnly = false;
                                objDP.className = 'fieldTextCenter';
                                arrFocus[11] = true;
                            }
                            else {
                                objDP.className = 'fieldTextCenter';
                                objDP.readOnly = false;
                                arrFocus[9] = false;
                            }
                            objDienG.className = 'fieldText';

                            if (!lTTDen) {
                                objSoTien.readOnly = false;
                                objSoTien.className = 'fieldTextRight';
                            }
                            else {
                                objSoTien.readOnly = true;
                                objSoTien.className = 'fieldTextRightCode';
                            }

                            //Nhay focus den field co read Only la false                      
                            arrFocus[12] = true;
                            arrFocus[13] = true;
                            var focusIndex = 0;
                            for (var ii = 0;ii < arrFocus.length;ii++) {
                                if (arrFocus[ii] == true) {
                                    focusIndex = ii;
                                    //alert(i);
                                    break;
                                }
                                //focusIndex = i;
                            }
                            if (!lTTDen) {
                                if (focusIndex > 0) {
                                    if (lastRow > 1) {
                                        for (var ik = 1;ik < lastRow;ik++) {
                                            if (ik == numberRowCurrent) {
                                                for (var j = 0;j < arrFieldIdLttCTiet.length;j++) {
                                                    if (focusIndex == j) {
                                                        if (arrObj[j] != null) {
                                                            arrObj[j].focus();
                                                            arrObj[j].style.visibility = "visible";
                                                        }
                                                        break;
                                                        //return;
                                                    }
                                                }
                                                break;
                                                //return;
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                if (focusIndex > 0) {
                                    if (lastRow > 1) {
                                        var firstRow = 1;
                                        for (var ik = 1;ik <= firstRow;ik++) {
                                            if (ik == numberRowCurrent) {
                                                for (var j = 0;j < arrFieldIdLttCTiet.length;j++) {
                                                    if (focusIndex == j) {
                                                        if (arrObj[j] != null) {
                                                            arrObj[j].focus();
                                                            arrObj[j].style.visibility = "visible";
                                                        }
                                                        break;
                                                        //return;
                                                    }
                                                }
                                                break;
                                                //return;
                                            }
                                        }
                                    }
                                }
                            }

                            resultCheck = true;
                        }
                    }
                    else if (data == null) {
                        if (!lTTDen) {
                            //                        if (!obj.childNodes[0].childNodes[0].readOnly && !firstCheck) {
                            alert(GetUnicode("M&#227; T&#224;i kho&#7843;n t&#7921; nhi&#234;n kh&#244;ng t&#7891;n t&#7841;i"));
                            objTKTN.value = '';
                            objTKTN.style.backgroundColor = '#f9e422';
                            objTKTN.focus();
                        }
                        else {
                            objTKTN.readOnly = true;
                            objTKTN.className = 'fieldTextCenterCode';
                            objMaQuy.readOnly = true;
                            objMaQuy.className = 'fieldTextCenterCode';
                            objDVQHNS.readOnly = true;
                            objDVQHNS.className = 'fieldTextCenterCode';
                            objCapNS.readOnly = false;
                            objCapNS.className = 'fieldTextCenter';
                            objKBNN.readOnly = true;
                            objKBNN.className = 'fieldTextCenterCode';
                            objSoTien.readOnly = true;
                            objSoTien.className = 'fieldTextRightCode';
                            objChuong.readOnly = false;
                            objChuong.className = 'fieldTextCenter';
                            objNganhKT.readOnly = false;
                            objNganhKT.className = 'fieldTextCenter';
                            objNDKT.readOnly = false;
                            objNDKT.className = 'fieldTextCenter';
                            objDB.readOnly = false;
                            objDB.className = 'fieldTextCenter';
                            objCTMT.readOnly = false;
                            objCTMT.className = 'fieldTextCenter';
                            objMN.readOnly = false;
                            objMN.className = 'fieldTextCenter';
                            objDP.readOnly = false;
                            objDP.className = 'fieldTextCenter';
                            objDienG.readOnly = false;
                            objDienG.className = 'fieldText';
                            objKBNN.readOnly = false;
                            objKBNN.className = 'fieldTextCenter';
                            objMaQuy.className = 'fieldTextCenter';
                            // focus len truong chuong
                            //                          id chuong = 4 
                            //if(isForLTTDen && jQuery("#loai_hach_toan").val()=='01')
                            var focusIndex1 = 3;
                            if (lastRow > 1) {
                                var firstRowEmp = 1;
                                for (var ikE = 1;ikE <= firstRowEmp;ikE++) {
                                    if (ikE == numberRowCurrent) {
                                        for (var jE = 0;jE < arrFieldIdLttCTiet.length;jE++) {
                                            if (focusIndex1 == jE) {
                                                if (arrObj[jE] != null) {
                                                    arrObj[jE].focus();
                                                    arrObj[jE].style.visibility = "visible";
                                                }
                                                break;
                                                //return;
                                            }
                                        }
                                        break;
                                        //return;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (!obj.childNodes[0].childNodes[0].readOnly && !firstCheck) {
                            alert(GetUnicode("M&#227; T&#224;i kho&#7843;n t&#7921; nhi&#234;n kh&#244;ng t&#7891;n t&#7841;i!"));
                            obj.value = '';
                            obj.focus();
                            //jQuery("#ten_nhkb_chuyen").trigger('focus');
                        }
                    }
                }
            },
            error : function (textstatus) {
                //alert(GetUnicode("C&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh Check danh m&#7909;c t&#224;i kho&#7843;n ch&#233;o. \n\n H&#227;y tho&#225;t ra v&#224; th&#7917; l&#7841;i. \n L&#7895;i: ")+textstatus.responseText);
                alert(GetUnicode("\n C&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh Check danh m&#7909;c t&#224;i kho&#7843;n ch&#233;o. \n\n H&#227;y tho&#225;t ra v&#224; th&#7917; l&#7841;i."));
            }
        });
    }
    jQuery.each(arrFieldIdLttCTiet, function (index, value) {
        strId = value.toString();
        if (document.getElementById(strId) != null) {
            if (document.getElementById(strId).readOnly != true) {
                document.getElementById(strId).tabIndex = tabContinue++;
            }
        }
    });
    firstCheck = false;
    return resultCheck;
}

/**
 * Focus to Cell(Row X Collumn) in COA
 * param: errorAtRow type number
 */
function focus2CellInCOA(errorAtRow, strErrorAt) {
    var objRows = document.getElementById('tblThongTinChiTietCOA').getElementsByTagName('tr');
    var numberRowinTable = 0;
    numberRowinTable = objRows.length - 1;
    //arrErrMsgCOA co 14 phan tu (= arrObj = arrFieldIdLttCTiet), th? t? c�c ph?n t? trong m?ng ph?i gi? nguy�n
    var arrErrMsgCOA = new Array('M&#227; Q&#361;y kh&#244;ng &#273;&#250;ng', 'M&#227; TK t&#7921; nhi&#234;n kh&#244;ng &#273;&#250;ng', 'M&#227; &#272;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch kh&#244;ng &#273;&#250;ng', 'M&#227; C&#7845;p ng&#226;n s&#225;ch kh&#244;ng &#273;&#250;ng', 'M&#227; Ch&#432;&#417;ng kh&#244;ng &#273;&#250;ng', 'M&#227; Ng&#224;nh kinh t&#7871; kh&#244;ng &#273;&#250;ng', 'M&#227; N&#7897;i dung kinh t&#7871; kh&#244;ng &#273;&#250;ng', 'M&#227; &#272;&#7883;a b&#224;n kh&#244;ng &#273;&#250;ng', 'M&#227; Ch&#432;&#417;ng tr&#236;nh m&#7909;c ti&#234;u kh&#244;ng &#273;&#250;ng', 'M&#227; Ngu&#7891;n kh&#244;ng &#273;&#250;ng', 'M&#227; Kho b&#7841;c kh&#244;ng &#273;&#250;ng', 'M&#227; D&#7921; ph&#242;ng kh&#244;ng &#273;&#250;ng', 'Di&#7877;n gi&#7843;i kh&#244;ng h&#7907;p l&#7879;', 'S&#7889; ti&#7873;n kh&#244;ng h&#7907;p l&#7879;');
    //    if (jQuery("#loai_hach_toan_selected").val() != null && jQuery("#loai_hach_toan_selected").val() != '' && jQuery("#loai_hach_toan_selected").val() != "undefined") {
    //        jQuery("#loai_hach_toan").val(jQuery("#loai_hach_toan_selected").val());
    //    }
    jQuery("#refresh").removeAttr('onclick');
    //    var nhan = jQuery("#nhan").val();
    //    if (nhan == 'Y') {
    //        jQuery("#tblThongTinChiTietCOA tr input").attr("readonly", true);
    //        jQuery("#tblThongTinChiTietCOA tr input").attr("style",'#f9e421');
    ////        alert(GetUnicode("&#272;&#226;y l&#224; l&#7879;nh nh&#7853;n t&#7915; tabmis !\n Y&#234;u c&#7847;u ki&#7875;m tra l&#7841;i chi ti&#7871;t c&#225;c kho&#7843;n m&#7909;c"));
    //    }
    //    else {
    for (var k = 1;k <= numberRowinTable;k++) {
        while (objRows[k].tagName != 'TR') {
            objRows[k] = objRows[k].parentNode;
        }
        objMaQuy = objRows[k].childNodes[0].childNodes[0];
        objTKTN = objRows[k].childNodes[1].childNodes[0];
        objDVQHNS = objRows[k].childNodes[2].childNodes[0];
        objCapNS = objRows[k].childNodes[3].childNodes[0];
        objChuong = objRows[k].childNodes[4].childNodes[0];
        objNganhKT = objRows[k].childNodes[5].childNodes[0];
        objNDKT = objRows[k].childNodes[6].childNodes[0];
        objDB = objRows[k].childNodes[7].childNodes[0];
        objCTMT = objRows[k].childNodes[8].childNodes[0];
        objMN = objRows[k].childNodes[9].childNodes[0];
        objKBNN = objRows[k].childNodes[10].childNodes[0];
        objDP = objRows[k].childNodes[11].childNodes[0];
        objDienG = objRows[k].childNodes[12].childNodes[0];
        objSoTien = objRows[k].childNodes[13].childNodes[0];

        //Tao Array de lay focus bo qua nhung field read only, khi tktn_ma mat focus,
        var arrObj = new Array();
        arrObj[0] = objMaQuy;
        arrObj[1] = objTKTN;
        arrObj[2] = objDVQHNS;
        arrObj[3] = objCapNS;
        arrObj[4] = objChuong;
        arrObj[5] = objNganhKT;
        arrObj[6] = objNDKT;
        arrObj[7] = objDB;
        arrObj[8] = objCTMT;
        arrObj[9] = objMN;
        arrObj[10] = objKBNN;
        arrObj[11] = objDP;
        arrObj[12] = objDienG;
        arrObj[13] = objSoTien;
        if (errorAtRow == k) {
            var countArrErr = 0;

            if (arrFieldIdLttCTiet != null) {
                countArrErr = arrFieldIdLttCTiet.length;
            }
            var msg = "";
            for (var h = 0;h < countArrErr;h++) {
                if (strErrorAt == arrFieldIdLttCTiet[h]) {
                    if (arrObj[h] != null && arrObj[h] != 'undefined') {
                        arrObj[h].focus();
                    }
                    msg = arrErrMsgCOA[h];
                    if (h == 10) {
                        msg = msg + " \n\n B&#7841;n ph&#7843;i nh&#7853;p M&#227; Kho B&#7841;c hi&#7879;n t&#7841;i c&#7911;a b&#7841;n.";
                    }
                    break;
                }
            }
            if (msg != "") {
                alert(GetUnicode(msg));
            }
            break;
        }
    }
    //    }
}

/**
 * Refresh danh sach LTT Master
 */
function refreshGridLTT(strUserType, strUrlRefresh, strUrlAction) {
    var urlRefresh = '';
    if (strUrlRefresh != null && strUrlRefresh != '') {
        urlRefresh = strUrlRefresh;
    }
    else {
        urlRefresh = 'listLttAdd.do';
    }
    jQuery.ajax( {
        type : "POST", url : urlRefresh, data :  {
            "action" : 'REFRESH', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (data != null) {
                if (data.logout != null && data.logout) {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {                    
                        fillData2TableMasterLTTDi(data, strUserType, strUrlAction);
                    }
                }

            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}



//fill data table
function fillData2TableMasterLTTDi(data, strUserType, strUrlAction) {
    var strTableData = "";
    var strImg = "";
    var strTitle = "";
    var strSoYCTT_ID = "";
    var id_ltt_default = 0;
    jQuery("#data-grid").html('');
    if (data != null && data != 'undefined' && data != '') {
        jQuery.each(data, function (i, objectLTTDi) {
            if (objectLTTDi.trang_thai == '01') {
                strImg = "/TTSP/styles/images/return.jpeg";
                strTitle = "KTT &#273;&#7849;y l&#7841;i";
            }
            else if (objectLTTDi.trang_thai == '02') {
                strImg = "/TTSP/styles/images/edit.gif";
                strTitle = "Ch&#7901; TTV ho&#224;n thi&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '03') {
                strImg = "/TTSP/styles/images/wait.jpeg";
                strTitle = "Ch&#7901; KTT dy&#7879;t";
            }
            else if (objectLTTDi.trang_thai == '04') {
                strImg = "/TTSP/styles/images/gd_return.jpeg";
                strTitle = "G&#272; &#273;&#7849;y l&#7841;i";
            }
            else if (objectLTTDi.trang_thai == '05') {
                strImg = "/TTSP/styles/images/wait-gd.png";
                strTitle = "Ch&#7901; G&#272; duy&#7879;t";
            }
            else if (objectLTTDi.trang_thai == '06') {
                strImg = "/TTSP/styles/images/delete1.gif";
                strTitle = "H&#7911;y";
            }
            else if (objectLTTDi.trang_thai == '07') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i - ch&#432;a v&#224;o giao di&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '08') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i Ng&#226;n h&#224;ng";
            }
            else if (objectLTTDi.trang_thai == '11') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Ch&#7901; ch&#7841;y giao di&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '12') {
                strImg = "/TTSP/styles/images/send-success.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '13') {
                strImg = "/TTSP/styles/images/send-false.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#7845;t b&#7841;i";
            }

            else if (objectLTTDi.trang_thai == '14') {
                strImg = "/TTSP/styles/images/send-success.jpg";
                strTitle = "G&#7917;i ng&#226;n h&#224;ng th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '15') {
                strImg = "/TTSP/styles/images/send-false.jpg";
                strTitle = "G&#7917;i ng&#226;n h&#224;ng kh&#244;ng th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '16') {
                strImg = "/TTSP/styles/images/sended-false.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Ng&#226;n h&#224;ng x&#7917; l&#253; th&#7845;t b&#7841;i";
            }

            if (strUrlAction == 'loadLTTDenJsonAction.do' || strUrlAction == 'loadQuyetToanAction.do')
                strSoYCTT_ID = objectLTTDi.id;
            else 
                strSoYCTT_ID = objectLTTDi.so_yctt;

            strSoYCTT_ID = "<input name=\"row_item\" id=\"" + i + "\" type=\"text\" value=\"" + strSoYCTT_ID + "\" onkeydown=\"arrowUpDownLTT(event);\" readonly=\"true\"  style=\"border:0px;font-size:10px;float:left;width:106px;\" />" + "<input  id=\"rowSelected\" type=\"hidden\" value=\'" + objectLTTDi.id + "\'/>"+"<input  name=\"tongsotien\"  type=\"hidden\" value=\'" + objectLTTDi.tong_sotien + "\'/>"+"<input  name=\"ntid\"  type=\"hidden\" value=\'" + objectLTTDi.nt_id + "\'/>";

            strTableData = strTableData + "<tr onmouseover=\"lttMouseOver('row_ltt_" + i + "');\"  onmouseout=\"lttMouseOut('row_ltt_" + i + "');\" " + "class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_ltt_" + i + "\" onclick=\"loadDetailLTTJson('" + strUrlAction + "', '" + objectLTTDi.id + "', 'row_ltt_" + i + "', '" + strUserType + "');\">" + "<td width=\"44%;\" align=\"left\">" + strSoYCTT_ID + "<\/td>" + "<td width=\"30%;\" align=\"center\"> <img src=\"" + strImg + "\" border=\"0\" title=\"" + strTitle + "\" <\/td>" + "<\/tr>";

            if (i == 0)
                id_ltt_default = objectLTTDi.id;
        });
    }
    else {
        deleteRowsInCOA("tblThongTinChiTietCOA");
        strTableData = '<tr><td colspan="5" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr>';
        jQuery('#mo_ta_trang_thai').html('');
        clearForm(arrFieldId);
        clearForm(arrFieldIdLttCTiet);
        clearForm(arrTextAriaId);
    }
    jQuery("#data-grid").html('<tbody>' + strTableData + '<\/tbody>');
    //tableHighlightLTTRow(); 
    //      var row_default="row_ltt_0",
    //        input_default=jQuery('#'+row_default).find('input');
    //      if(jQuery("#"+row_default).html()!= null && jQuery("#"+row_default).html()!='null'){
    //        jQuery("#"+row_default).addClass("ui-state-highlight");
    //        input_default.addClass("ui-state-highlight");        
    //        if(id_ltt_default != 0)
    //          loadDetailLTTJson('loadLTTDiJsonAction.do', id_ltt_default, row_default, "'"+strUserType+"'");
    //      }
    defaultSelectedLTT(strUrlAction, "'" + strUserType + "'", id_ltt_default);

    var strNumberResultSearch = 0;
    if (data != null && data != 'undefined')
        strNumberResultSearch = data.length;
    var strAlert = "K&#7871;t qu&#7843;: " + strNumberResultSearch + " L&#7879;nh thanh to&#225;n th&#7887;a m&#227;n.";
    jQuery("#resultSearch").html(GetUnicode(strAlert));

//20171010 thuongdt bo sung tra cuu nhanh begin

    var vtempmant = document.getElementsByName('tempmant');
    var vnt_id_find = jQuery("#nt_id_find").val();
    var vindx = 0;
    if(vnt_id_find != ''){
    for(var i =0; i<vtempmant.length; i++){
      vindx ++;
      if(vtempmant[i].value == vnt_id_find ){        
        break;
      }
    }
    document.getElementById("nt_id_tke_tong").selectedIndex = vindx;
    }else{
      jQuery("#nt_id_tke_tong").val('');
    }
  var loaiTien = document.getElementById('nt_id_tke_tong');
  
  changeMaNTThongKeTongJS(loaiTien);
//20171010 thuongdt bo sung tra cuu nhanh end
}

/**
 * highlignt LTT
 */
function tableHighlightLTTRow() {
    if (document.getElementById && document.createTextNode) {
        var tables = document.getElementsByTagName('table');
        for (var i = 0;i < tables.length;i++) {
            if (tables[i].className == 'data-grid') {
                var trs = tables[i].getElementsByTagName('tr');
                for (var j = 0;j < trs.length;j++) {
                    if (trs[j].parentNode.nodeName == 'TBODY') {
                        trs[j].onmouseover = function () {
                            if (this.className != 'ui-row-ltr ui-state-highlight')
                                this.className = 'ui-state-hover';
                            return false;
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
//********************** Tim kiem So Yeu cau Thanh Toan ***********************************   
function findLTTSoYCTT(strUrlRefresh, strUserType, strUrlAction) {
    var urlRefresh = '';
    if (strUrlRefresh != null && strUrlRefresh != '') {
        urlRefresh = strUrlRefresh;
    }
    else {
        urlRefresh = 'listLttAdd.do';
    }
    var ttvnhan = jQuery("#ttvnhan").val(), nhkbchuyennhan = jQuery("#nhkbchuyennhan").val(), sotien = jQuery("#sotien").val(), soyctt = jQuery("#soyctt").val(), trangthai = jQuery("#trangthai").val();
    var soltt = jQuery("#soltt").val();
    var nt_id_find = jQuery("#nt_id_find").val();
    
    jQuery.ajax( {
        type : "POST", url : urlRefresh, data :  {
            "nt_id_find":nt_id_find,"ttvnhan" : ttvnhan, "nhkbchuyennhan" : nhkbchuyennhan, "sotien" : sotien, "soyctt" : soyctt, "trangthai" : trangthai, "soltt" : soltt, "action" : 'FIND_SoYCTT', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (data != null && textstatus != null && textstatus == 'success') {
                if (data.logout != null && data.logout != 'undefined') {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {
                        fillData2TableMasterLTTDi(data, strUserType, strUrlAction);
                    }
                }

            }
        },
        error : function (textstatus) {
            jQuery("#message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}

function lttMouseOver(tr_id) {
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trs.length;j++) {
        if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
            jQuery("#" + tr_id).attr( {
                'class' : 'ui-state-hover'
            });
        }
    }
}

function lttMouseOut(tr_id) {
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trs.length;j++) {
        if (jQuery("#" + tr_id).attr('class') == 'ui-state-hover') {
            jQuery("#" + tr_id).attr( {
                'class' : 'ui-widget-content ui-row-ltr'
            });

        }
        else if (jQuery("#" + tr_id).attr('class') == 'ui-row-ltr ui-state-highlight') {
            jQuery("#" + tr_id).attr( {
                'class' : 'ui-row-ltr ui-state-highlight'
            });
        }
    }
}

function addDetailCOARow() {
    var tbl = document.getElementById('tblThongTinChiTietCOA').getElementsByTagName("tbody")[0];;
    var lastRow = tbl.rows.length;
    var tabrow = 47;
    var allowClone = true;
    if (lastRow == 2) {
        if (document.forms[0].tkkt_ma.value == '') {
            allowClone = false;
            if (!allowClone) {
                alert(GetUnicode("B&#7841;n ph&#7843;i nh&#7853;p M&#227; t&#224;i kho&#7843;n t&#7921; nhi&#234;n \n tr&#432;&#7899;c khi th&#234;m d&#242;ng!"));
                document.forms[0].tkkt_ma.focus();
            }
        }
    }
    else if (lastRow > 2) {
        for (var i = 1;i < lastRow;i++) {
            if ((document.forms[0].tkkt_ma[i] != null && document.forms[0].tkkt_ma[i].value == '') || (document.forms[0].tkkt_ma[i] != null && document.forms[0].tkkt_ma[i].style.backgroundColor == '#f9e422')) {
                allowClone = false;
            }
            if (!allowClone) {
                alert(GetUnicode("B&#7841;n ph&#7843;i nh&#7853;p M&#227; t&#224;i kho&#7843;n t&#7921; nhi&#234;n \n tr&#432;&#7899;c khi th&#234;m d&#242;ng!"));
                document.forms[0].tkkt_ma[i].focus();
                break;
            }
        }
    }
    if (allowClone) {
        if (parseFloat(lastRow) > 1) {
            tabrow += (lastRow - 2) * 8;
        }
        //Lay dong dau tien de copy      
        var root = tbl.getElementsByTagName('tr')[1].parentNode;
        var obj = root.getElementsByTagName('tr')[1].cloneNode(true);
        //Dat lai cac ham javascript
        obj.getElementsByTagName('td')[0].innerHTML = obj.getElementsByTagName('td')[0].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[1].innerHTML = obj.getElementsByTagName('td')[1].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[2].innerHTML = obj.getElementsByTagName('td')[2].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[3].innerHTML = obj.getElementsByTagName('td')[3].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        // 4 chuong
        obj.getElementsByTagName('td')[4].innerHTML = obj.getElementsByTagName('td')[4].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[5].innerHTML = obj.getElementsByTagName('td')[5].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[6].innerHTML = obj.getElementsByTagName('td')[6].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[7].innerHTML = obj.getElementsByTagName('td')[7].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[8].innerHTML = obj.getElementsByTagName('td')[8].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[9].innerHTML = obj.getElementsByTagName('td')[9].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[10].innerHTML = obj.getElementsByTagName('td')[10].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[11].innerHTML = obj.getElementsByTagName('td')[11].innerHTML.replace("class=fieldTextCenter", "class=fieldTextCenter onfocus=\"focusAfterAddCOA(this);\"");

        obj.getElementsByTagName('td')[12].innerHTML = obj.getElementsByTagName('td')[12].innerHTML.replace("class=fieldText", "class=fieldText onfocus=\"focusAfterAddCOA(this);\" onblur=\"blurAfterAddCOA(this);\"");
        obj.getElementsByTagName('td')[13].innerHTML = obj.getElementsByTagName('td')[13].innerHTML.replace("class=fieldTextRight", "class=fieldTextRight onfocus=\"focusAfterAddCOA(this);\" ");

        obj.getElementsByTagName('td')[1].innerHTML = obj.getElementsByTagName('td')[1].innerHTML.replace("taoTaiKhoanFromCOA();", "blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[2].innerHTML = obj.getElementsByTagName('td')[2].innerHTML.replace("taoTaiKhoanFromCOA();", "blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[3].innerHTML = obj.getElementsByTagName('td')[3].innerHTML.replace("taoTaiKhoanFromCOA();", "blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[1].innerHTML = obj.getElementsByTagName('td')[1].innerHTML.replace("taoTaiKhoanCOA();", "blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[2].innerHTML = obj.getElementsByTagName('td')[2].innerHTML.replace("taoTaiKhoanCOA();", "blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[3].innerHTML = obj.getElementsByTagName('td')[3].innerHTML.replace("taoTaiKhoanCOA();", "blurAfterAddCOA(this);");
        // 4 chuong
        obj.getElementsByTagName('td')[4].innerHTML = obj.getElementsByTagName('td')[4].innerHTML.replace("autoFillDefaultValue('chuongns_ma');", "autoFillDefaultValue('chuongns_ma'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[5].innerHTML = obj.getElementsByTagName('td')[5].innerHTML.replace("autoFillDefaultValue('nganhkt_ma');", "autoFillDefaultValue('nganhkt_ma'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[6].innerHTML = obj.getElementsByTagName('td')[6].innerHTML.replace("autoFillDefaultValue('ndkt_ma');", "autoFillDefaultValue('ndkt_ma'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[7].innerHTML = obj.getElementsByTagName('td')[7].innerHTML.replace("autoFillDefaultValue('dbhc_ma');", "autoFillDefaultValue('dbhc_ma'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[8].innerHTML = obj.getElementsByTagName('td')[8].innerHTML.replace("autoFillDefaultValue('ctmt_ma');", "autoFillDefaultValue('ctmt_ma'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[9].innerHTML = obj.getElementsByTagName('td')[9].innerHTML.replace("autoFillDefaultValue('ma_nguon');", "autoFillDefaultValue('ma_nguon'," + lastRow + ");blurAfterAddCOA(this);");
        obj.getElementsByTagName('td')[11].innerHTML = obj.getElementsByTagName('td')[11].innerHTML.replace("autoFillDefaultValue('du_phong_ma');", "autoFillDefaultValue('du_phong_ma'," + lastRow + ");blurAfterAddCOA(this);");

        obj.getElementsByTagName('td')[13].innerHTML = obj.getElementsByTagName('td')[13].innerHTML.replace("onblur=formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();", "onblur=formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();blurAfterAddCOA(this);");
        //        alert(lastRow);
        //        lastRow = lastRow - 1;
        obj.id = "coa_" + lastRow;
        //Them dong vao tables
        root.appendChild(obj);
        var tkkt_ma_row1 = '';
        var dvsdns_ma = '';
        var capns_ma = '';
        //alert(lastRow);
        if (lastRow == 2) {
            if (document.forms[0].tkkt_ma.value != '') {
                tkkt_ma_row1 = document.forms[0].tkkt_ma.value;
            }
            if (document.forms[0].dvsdns_ma.value != '') {
                dvsdns_ma = document.forms[0].dvsdns_ma.value;
            }
            if (document.forms[0].capns_ma.value != '') {
                capns_ma = document.forms[0].capns_ma.value;
            }
            document.forms[0].tkkt_ma.value = tkkt_ma_row1;
            document.forms[0].dvsdns_ma.value = dvsdns_ma;
            document.forms[0].capns_ma.value = capns_ma;

        }
        else if (lastRow > 2) {
            if (document.forms[0].tkkt_ma[(lastRow - 1)].value != '') {
                tkkt_ma_row1 = document.forms[0].tkkt_ma[(lastRow - 1)].value;
            }
            if (document.forms[0].dvsdns_ma[(lastRow - 1)].value != '') {
                dvsdns_ma = document.forms[0].dvsdns_ma[(lastRow - 1)].value;
            }
            if (document.forms[0].capns_ma[(lastRow - 1)].value != '') {
                capns_ma = document.forms[0].capns_ma[(lastRow - 1)].value;
            }
            document.forms[0].tkkt_ma[(lastRow - 1)].value = tkkt_ma_row1;
            document.forms[0].dvsdns_ma[(lastRow - 1)].value = dvsdns_ma;
            document.forms[0].capns_ma[(lastRow - 1)].value = capns_ma;
        }

        document.forms[0].ma_quy.value = "";
        document.forms[0].chuongns_ma[(lastRow - 1)].value = "";
        document.forms[0].nganhkt_ma[(lastRow - 1)].value = "";
        document.forms[0].ndkt_ma[(lastRow - 1)].value = "";
        document.forms[0].dbhc_ma[(lastRow - 1)].value = "";
        document.forms[0].ctmt_ma[(lastRow - 1)].value = "";
        document.forms[0].du_phong_ma[(lastRow - 1)].value = "";
        document.forms[0].ma_nguon[(lastRow - 1)].value = "";
        document.forms[0].so_tien[(lastRow - 1)].value = "";
        document.forms[0].dien_giai[(lastRow - 1)].value = "";
        document.forms[0].kb_ma[(lastRow - 1)].value = document.forms[0].kb_ma[0].value;
        document.forms[0].tkkt_ma[(lastRow - 1)].focus();
    }
}

function deleteRowsInCOA(tableId) {
    var tableCOA = document.getElementById(tableId);
    var tbodyCT = tableCOA.tBodies[0];
    var lengTbdChl = tbodyCT.childNodes.length;
    for (var j = lengTbdChl - 2;0 < j;--j) {
        tbodyCT.removeChild(tbodyCT.childNodes[j]);
    }
}

function resetLTTDiCOA() {
    var strId = "";
    jQuery.each(arrFieldIdLttCTiet, function (index, value) {
        strId = value.toString();
        //assignValue2Field("tkkt_id", "");
        assignValue2Field(strId, "");
        assignStyle2Field(strId, true);
    });
}

function ghiThemMoiLTTDi(actionForButtonGhi, ttloai_lenh) {
    if (checkInput(actionForButtonGhi, ttloai_lenh)) {
        var allowSubmit = false;
        allowSubmit = preventDuplicateSubmit();
        if (allowSubmit) {     
          if(document.getElementById("vay_tra_no_nn").checked){
            document.forms[0].vay_tra_no_nn.value = 'Y';
          }else{
            document.forms[0].vay_tra_no_nn.value = '';
          }
          document.forms[0].submit();
        }
    }
}
function checkTongTienChiTiet(){





    return true;
//   //ManhNV-20141119
//    //20141119: check so tien va tong tien chi tiet  
//      var arrSo_tien = document.getElementsByName("so_tien");           
//      var so_tien_compare = 0;
//      var tong_sotien = jQuery("#tong_sotien").val();    
//     // alert(71505.47 + 2459.32);
//      var nt_id = jQuery("#nt_id").val();    
//      for (i = 0;i < arrSo_tien.length;i++) {         
//        so_tien_compare += convertCurrencyToNumber(arrSo_tien[i].value, nt_id)*100;     
//      }
//      so_tien_compare = so_tien_compare/100;
//      //alert('Tong Ctiet: '+so_tien_compare);
//      //alert('Tong so tien: '+convertCurrencyToNumber(tong_sotien, nt_id))
//      if (so_tien_compare != convertCurrencyToNumber(tong_sotien, nt_id)) {
//          alert(GetUnicode("T&#7893;ng s&#7889; ti&#7873;n trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#225;c s&#7889; ti&#7873;n c&#7911;a LTT"));
//          return false;
//      } 
//      return true;
//      //20141119
}
function checkSuccess(strValid) {
    var strErrorAtCol = 0, strErrorAtRow = 0;
    var actionForButtonGhi = jQuery("#actionForSave").val();
    var messageError = '';
    
    //20150115-manhnv:Check tong_sotien khong duoc bang 0
    if(convertCurrencyToNumber(jQuery("#tong_sotien").val(),jQuery("#nt_id").val()) <= 0){
      alert(GetUnicode("S&#7889; ti&#7873;n l&#7879;nh thanh to&#225;n ph&#7843;i > 0"));
      jQuery("#so_tien").focus();
      return;
    }
    //***************************************************
    
    if (!(strValid == 'lttDenAdd' && jQuery("#loai_hach_toan").val() == '02')) {
        messageError = checkBeforeSubmit(strValid, actionForButtonGhi);
    }
    var nhan = jQuery("#nhan").val();
    var ngay_tt = jQuery("#ngay_tt").val();
    var ngay_tt_temp = jQuery("#ngay_tt_temp").val();
    if (ngay_tt == null || '' == ngay_tt) {
        alert(GetUnicode("Ng&#224;y thanh to&#225;n kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng !"));
        return false;
    }else {
        if (strValid != 'lttDenAdd' && actionForButtonGhi != 'update') {
            if (ngay_tt_temp != null && '' != ngay_tt_temp && 'undefined' != ngay_tt_temp) {
                if (CompareDate(ngay_tt, ngay_tt_temp) ==  - 1) {
                    alert(GetUnicode('Ng&#224;y thanh to&#225;n ph&#7843;i nh&#7887; h&#417;n ho&#7863;c b&#7857;ng ng&#224;y hi&#7879;n t&#7841;i'));
                    document.getElementById("ngay_tt").focus();
                    return;
                }
            }
        }
    }    
    if (strValid == 'lttDiAdd') {
      if(!checkTongTienChiTiet()){           
        return false;
      }
      //20141212
      if(jQuery("#ten_nhkb_nhan").val().trim()=='Ngan hang nuoc ngoai'){
        alert(GetUnicode('Ph&#7843;i s&#7917;a t&#234;n ng&#226;n h&#224;ng h&#432;&#7903;ng'));
        jQuery("#ten_nhkb_nhan").focus();
        return;
      }
      //20141212
       //20150312-Check trong TH mua ban ngoai te
                if(jQuery("#ma_nhkb_nhan").val().trim() == '' ){
                  alert(GetUnicode('C&#7847;n nh&#7853;p m&#227; NH/KB ng&#432;&#7901;i nh&#7853;n'));
                  jQuery("#ma_nhkb_nhan").focus();
                  return;
                }
                
                if(jQuery("#ttloai_lenh").val() == '03'){
//                  if(document.forms[0].ma_nt_mua_ban.options[document.forms[0].ma_nt_mua_ban.options.selectedIndex].innerText == ''){
                  
                  if(jQuery("#ma_nt_mua_ban").val().trim() == ''){
                    alert(GetUnicode('C&#7847;n nh&#7853;p m&#227; ngo&#7841;i t&#234; mua trong tr&#432;&#7901;ng h&#7907;p l&#224; mua/b&#225;n ngo&#7841;i t&#7879;'));
                    jQuery("#ma_nt_mua_ban").focus();                    
                    return;
                  }
                  if(jQuery("#ma_nt_mua_ban").val().trim() != 'VND'){
                    if(jQuery("#so_tien_mua_ban").val().trim() == '' || jQuery("#so_tien_mua_ban").val().trim() == '0'){
                      alert(GetUnicode('c&#7847;n nh&#7853;p s&#7889; ti&#7873;n mua.'));
                      jQuery("#so_tien_mua_ban").focus();
                      return;
                    }
                  }
                  if(jQuery("#ma_nt_mua_ban").val()==document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText){
                    alert(GetUnicode('M&#227; ngo&#7841;i t&#7879; mua v&#224; b&#225;n kh&#244;ng &#273;&#432;&#7907;c tr&#249;ng nhau.'));
                    jQuery("#ma_nt_mua_ban").focus();
                    return;
                  }
                }
                //20150312
    }
    if (nhan == 'Y' && strValid == 'lttDiAdd') {
    //ManhNV sua (20140819)
        //Kiem tra do dai ten tai khoan chuyen/nhan neu dai hon 140 thi cho phep sua       
        if(jQuery("#ten_tk_chuyen").val().length > 146){
          jQuery("#ten_tk_chuyen").removeAttr("readonly");
          setClass2Field('ten_tk_chuyen', 'fieldText');
          jQuery("#ten_tk_chuyen").focus();
          
          jQuery("#sua_ten_tk_flag").val("ten_tk_chuyen");
          
          alert(GetUnicode('T&#234;n &#273;&#417;n v&#7883; chuy&#7875;n c&#243; s&#7889; k&#253; t&#7921; ('+jQuery("#ten_tk_chuyen").val().length+') v&#432;&#7907;t qua s&#7889; l&#432;&#7907;ng cho ph&#233;p, c&#7847;n s&#7917;a nh&#7887; h&#417;n 146 k&#253; t&#7921;'));
          return;
        }
        if(jQuery("#ten_tk_nhan").val().length > 146){
            jQuery("#ten_tk_nhan").removeAttr("readonly");
            setClass2Field('ten_tk_nhan', 'fieldText');
            jQuery("#ten_tk_nhan").focus();
            
            jQuery("#sua_ten_tk_flag").val("ten_tk_nhan");
            
            alert(GetUnicode('T&#234;n &#273;&#417;n v&#7883; nh&#7853;n c&#243; s&#7889; k&#253; t&#7921; ('+jQuery("#ten_tk_nhan").val().length+') v&#432;&#7907;t qua s&#7889; l&#432;&#7907;ng cho ph&#233;p, c&#7847;n s&#7917;a nh&#7887; h&#417;n 146 k&#253; t&#7921;'));
            return;
        }
        //20140819
    }
    
    if (nhan == 'Y' && strValid == 'lttDiAdd' && messageError != '') {
        jQuery("#tblThongTinChiTietCOA tr input").attr("readonly", true);
        jQuery("#tblThongTinChiTietCOA tr input").attr("style", '#f9e421');
        
        ghiDuLieu2DB(actionForButtonGhi);
    }
    else {
        var flag = true;
        if (messageError != '') {
            flag = false;
        }
        if (!flag) {
            if (messageError.search("-") !=  - 1) {
                // khong check trung ngay o day vi con truong hop update SO_YCTT
                var arrTmp = messageError.split("-");
                if (arrTmp.length == 2) {
                    strErrorAtCol = arrTmp[0];
                    strErrorAtRow = parseInt(arrTmp[1]) + 1;
                    focus2CellInCOA(strErrorAtRow, strErrorAtCol);
                }
            }
            else if (messageError == 'TRUNG_SO_YCTT_CANCEL') {
                alert(GetUnicode('S&#7889; y&#234;u c&#7847;u thanh to&#225;n &#273;&#227; t&#7891;n t&#7841;i !'));
                return false;
            }
            else if (messageError == 'TRUNG_SO_YCTT') {
                if (strValid == 'lttDiAdd') {
                    // truong hop lenh thanh toan di 
                    /* Kiem tra action la them moi hay hoan thien
                     * action = addnew ==> them moi
                     * action = update ==> hoan thien
                     **/
                    if (actionForButtonGhi != null) {
                        /**
                         * Kiem tra so yeu cau thanh toan bi trung
                         * Neu trung alert message confirm
                         * Yes insert
                         * No cancel
                         */
                        if (actionForButtonGhi == 'update') {
                            completeActionLttDi();
                        }
                        else {
                            msgAlertByAction('ADD', messageError);
                        }
                    }
                    //            ghiDuLieu2DB();
                }
                else if (strValid == 'lttAdd') {
                    // truong hop nhap lenh thu cong
                    msgAlertByAction('ADD', messageError);
                }
            }else {
                // truong hop loi checkDMKHCheo
                alert(GetUnicode('\n  L&#7895;i services ki&#7875;m tra danh m&#7909;c k&#7871;t h&#7907;p ch&#233;o nh&#7853;p l&#7879;nh thanh to&#225;n : ' + messageError));
                return false;
            }
        }
        else {
            var selectElement = document.getElementById("nt_id");
            var textValue = selectElement.options[selectElement.options.selectedIndex].innerText != null ? selectElement.options[selectElement.options.selectedIndex].innerText : "";
            if (strValid == 'lttDiAdd') {
                if (textValue != 'VND') {
                    alert(GetUnicode("L&#432;u &#253; : b&#7841;n &#273;ang l&#7853;p giao d&#7883;ch ngo&#7841;i t&#7879; !"));
                }
                // kiem tra truong hop them moi valid thong tin loai lenh 
                //                if(actionForButtonGhi=='addnew' && jQuery("#ttloai_lenh").val()=='02'){
                //                  return;
                //                }
                // PhuongNTM07 add on 01/08/2016 start - 
                // Neu la dien lien ngan hang thi noidung_tt < 210 ki tu va (ttin_kh_chuyen+ten_tk_chuyen) < 70 ki tu (F50KP2) va (ttin_kh_nhan+ten_tk_nhan) < 70 ki tu (F59P2)
                // neu khong thi noidung_tt phai nho hon 220 ki tu
                if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
                    if(jQuery("#ndung_tt").val().length > 210){
                      jQuery("#ndung_tt").attr("readOnly", false);
                        alert(GetUnicode('N&#7897;i dung thanh to&#225;n ph&#7843;i nh&#7887; h&#417;n 210 k&#253; t&#7921; !'));
                        jQuery("#ndung_tt").focus();
                        return;
                    }
                    if(jQuery("#ttin_kh_chuyen").val().length + jQuery("#ten_tk_chuyen").val().length > 70){
                      alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng chuy&#7875;n v&#224; t&#234;n t&#224;i kho&#7843;n chuy&#7875;n kh&#244;ng v&#432;&#7907;t qu&#225; 70 k&#237; t&#7921; &#273;&#7889;i v&#7899;i l&#7879;nh li&#234;n ng&#226;n h&#224;ng'));
                        jQuery("#ten_tk_chuyen").focus();
                        jQuery("#ttin_kh_chuyen").attr("readOnly", false);
                        jQuery("#ten_tk_chuyen").attr("readOnly", false);
                        return;
                    }
                    if(jQuery("#ttin_kh_nhan").val().length + jQuery("#ten_tk_nhan").val().length > 70){
                      jQuery("#ttin_kh_nhan").attr("readOnly", false);
                      jQuery("#ten_tk_nhan").attr("readOnly", false);
                      alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n v&#224; t&#234;n t&#224;i kho&#7843;n nh&#7853;n kh&#244;ng v&#432;&#7907;t qu&#225; 70 k&#237; t&#7921; &#273;&#7889;i v&#7899;i l&#7879;nh li&#234;n ng&#226;n h&#224;ng'));
                      jQuery("#sua_ten_tk_flag").val("ten_tk_nhan");
                        jQuery("#ten_tk_nhan").focus();
                        return;
                    }
                }
                else if (jQuery("#ndung_tt").val().length > 220) {
                    alert(GetUnicode("N&#7897;i dung thanh to&#225;n ph&#7843;i nh&#7887; h&#417;n 220 k&#253; t&#7921; !"));
                    jQuery("#ndung_tt").focus();
                    return;
                }
                // PhuongNTM07 add on 01/08/2016 end -
                ghiDuLieu2DB(actionForButtonGhi, jQuery("#ttloai_lenh").val());
            }
            else if (strValid == 'lttDenAdd') {
                ghiLTTDenQToan2DB('lttDenUpdateExc.do?checkLTT=' + bCheckLTTDen, bCheckLTTDen);
            }
            else if (strValid == 'lttAdd') {
                if (textValue != 'VND') {
                    alert(GetUnicode("L&#432;u &#253; : b&#7841;n &#273;ang l&#7853;p giao d&#7883;ch ngo&#7841;i t&#7879; !"));
                }
                if (jQuery("#ten_tk_chuyen").val().trim() == '') {
                    alert(GetUnicode("B&#7841;n ph&#7843;i nh&#7853;p th&#244;ng tin &#273;&#417;n v&#7883; chuy&#7875;n !"));
                    jQuery("#ten_tk_chuyen").focus();
                    return false;
                }
      
                //20150312-Check trong TH mua ban ngoai te
                if(jQuery("#ma_nhkb_nhan").val().trim() == '' ){
                  alert(GetUnicode('C&#7847;n nh&#7853;p m&#227; NH/KB ng&#432;&#7901;i nh&#7853;n'));
                  jQuery("#ma_nhkb_nhan").focus();
                  return;
                }
                
                if(jQuery("#ttloai_lenh").val() == '03' ){
                  if(document.forms[0].ma_nt_mua_ban.options[document.forms[0].ma_nt_mua_ban.options.selectedIndex].innerText == ''){
                    alert(GetUnicode('C&#7847;n nh&#7853;p m&#227; ngo&#7841;i t&#234; mua trong tr&#432;&#7901;ng h&#7907;p l&#224; mua/b&#225;n ngo&#7841;i t&#7879;'));
                    jQuery("#ma_nt_mua_ban").focus();
                    return;
                  }
                  if(document.forms[0].ma_nt_mua_ban.options[document.forms[0].ma_nt_mua_ban.options.selectedIndex].innerText != 'VND'){
                    if(jQuery("#so_tien_mua_ban").val().trim() == '' || jQuery("#so_tien_mua_ban").val().trim() == '0'){
                      alert(GetUnicode('c&#7847;n nh&#7853;p s&#7889; ti&#7873;n mua.'));
                      jQuery("#so_tien_mua_ban").focus();
                      return;
                    }
                  }
                  if(jQuery("#ma_nt_mua_ban").val()==document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText){
                    alert(GetUnicode('M&#227; ngo&#7841;i t&#7879; mua v&#224; b&#225;n kh&#244;ng &#273;&#432;&#7907;c tr&#249;ng nhau.'));
                    jQuery("#ma_nt_mua_ban").focus();
                    return;
                  }
                }
                //20150312
                // PhuongNTM07 add on 12/09/2016 start - check ndung_tt <= 210 va va (ttin_kh_chuyen+ten_tk_chuyen) < 70 ki tu (F50KP2) va (ttin_kh_nhan+ten_tk_nhan) < 70 ki tu (F59P2)
                // 20160912
                if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
                    if(jQuery("#ndung_tt").val().length > 210){            
                        jQuery("#ndung_tt").attr("readOnly", false);
                        alert(GetUnicode('N&#7897;i dung thanh to&#225;n ph&#7843;i nh&#7887; h&#417;n 210 k&#253; t&#7921; !'));
                        jQuery("#ndung_tt").focus();
                        return;
                    }
                    if (jQuery("#ttin_kh_nhan").val().length + jQuery("#ten_tk_nhan").val().length > 70) {
                      jQuery("#ttin_kh_nhan").attr("readOnly", false);
                      jQuery("#ten_tk_nhan").attr("readOnly", false);
                      alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n v&#224; t&#234;n t&#224;i kho&#7843;n nh&#7853;n kh&#244;ng v&#432;&#7907;t qu&#225; 70 k&#237; t&#7921; &#273;&#7889;i v&#7899;i l&#7879;nh li&#234;n ng&#226;n h&#224;ng'));
                      jQuery("sua_ten_tk_flag").val("ten_tk_nhan");
                      jQuery("#ten_tk_nhan").focus();
                      return;
                    }
                    if (jQuery("#ttin_kh_chuyen").val().length + jQuery("#ten_tk_chuyen").val().length > 70) {
                      jQuery("#ttin_kh_chuyen").attr("readOnly", false);
                      jQuery("#ten_tk_chuyen").attr("readOnly", false);
                      alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng chuy&#7875;n v&#224; t&#234;n t&#224;i kho&#7843;n chuy&#7875;n kh&#244;ng v&#432;&#7907;t qu&#225; 70 k&#237; t&#7921; &#273;&#7889;i v&#7899;i l&#7879;nh li&#234;n ng&#226;n h&#224;ng'));
                      jQuery("#ten_tk_chuyen").focus();
                      return;
                    }
                }
                //PhuongNTM07 add on 12/09/2016 end -
                ghiThemMoiLTTDi(actionForButtonGhi, jQuery("#ttloai_lenh").val());
            }
        }
    }
}

/**
 *
 *
 * */
var bCheckLTTDen = true;

function checkBeforeSubmit(actionForm, actionButton) {
    jQuery.ajaxSettings.traditional = true;
    var arrTkkt_ma = document.getElementsByName("tkkt_ma"), arrMa_quy = document.getElementsByName("ma_quy"), arrDvsdns_ma = document.getElementsByName("dvsdns_ma"), arrCapns_ma = document.getElementsByName("capns_ma"), arrChuongns_ma = document.getElementsByName("chuongns_ma"), arrNganhkt_ma = document.getElementsByName("nganhkt_ma"), arrNdkt_ma = document.getElementsByName("ndkt_ma"), arrDbhc_ma = document.getElementsByName("dbhc_ma"), arrCtmt_ma = document.getElementsByName("ctmt_ma"), arrMa_nguon = document.getElementsByName("ma_nguon"), arrDu_phong_ma = document.getElementsByName("du_phong_ma"), arrMa_kb = document.getElementsByName("kb_ma"), arrDien_giai = document.getElementsByName("dien_giai"), arrSo_tien = document.getElementsByName("so_tien");
    var str_so_yctt = jQuery("#so_yctt").val();
    var str_id_selected = jQuery("#id_selected") != null && jQuery("#id_selected") != 'null' ? jQuery("#id_selected").val() : "";

    var arrTkkt_ma_res = new Array();
    var arrMa_quy_res = new Array();
    var arrDvsdns_ma_res = new Array();
    var arrCapns_ma_res = new Array();
    var arrChuongns_ma_res = new Array();
    var arrNganhkt_ma_res = new Array();
    var arrNdkt_ma_res = new Array();
    var arrDbhc_ma_res = new Array();
    var arrCtmt_ma_res = new Array();
    var arrMa_nguon_res = new Array();
    var arrDu_phong_ma_res = new Array();
    var arrMa_kb_res = new Array();
    var arrDien_giai_res = new Array();
    var arrSo_tien_res = new Array();

    for (i = 0;i < arrTkkt_ma.length;i++) {
        // add gia tri vao mang
        //arrTkkt_ma_res
        arrTkkt_ma_res[i] = arrTkkt_ma[i].value;

        //arrMa_quy_res
        arrMa_quy_res[i] = arrMa_quy[i].value;
        //arrDvsdns_ma_res
        arrDvsdns_ma_res[i] = arrDvsdns_ma[i].value;
        //arrCapns_ma_res
        arrCapns_ma_res[i] = arrCapns_ma[i].value;
        //arrChuongns_ma_res
        arrChuongns_ma_res[i] = arrChuongns_ma[i].value;
        //arrNganhkt_ma_res
        arrNganhkt_ma_res[i] = arrNganhkt_ma[i].value;
        //arrNdkt_ma_res
        arrNdkt_ma_res[i] = arrNdkt_ma[i].value;
        //arrDbhc_ma_res
        arrDbhc_ma_res[i] = arrDbhc_ma[i].value;
        //arrCtmt_ma_res
        arrCtmt_ma_res[i] = arrCtmt_ma[i].value;
        //arrMa_nguon_res
        arrMa_nguon_res[i] = arrMa_nguon[i].value;
        //arrDu_phong_ma_res
        arrDu_phong_ma_res[i] = arrDu_phong_ma[i].value;
        //arrMa_kb_res
        arrMa_kb_res[i] = arrMa_kb[i].value;
        //arrDien_giai_res
        arrDien_giai_res[i] = arrDien_giai[i].value;
        //arrSo_tien_res
        arrSo_tien_res[i] = arrSo_tien[i].value;
        // truong hop khong co coa thi khong check nua
        if (arrTkkt_ma.length == 1 && (arrTkkt_ma_res[i] == '' || arrTkkt_ma_res[i] == 'undefined')) {
            bCheckLTTDen = false;
            return '';
        }
        //end
    }
    var messageError = '';
    jQuery.ajax( {
        type : "POST", url : "checkPhanDoanCOA.do",  data :  {
            "tkkt_ma" : arrTkkt_ma_res, "ma_quy" : arrMa_quy_res, "dvsdns_ma" : arrDvsdns_ma_res, "capns_ma" : arrCapns_ma_res, 
            "chuongns_ma" : arrChuongns_ma_res, "nganhkt_ma" : arrNganhkt_ma_res, "ndkt_ma" : arrNdkt_ma_res, 
            "dbhc_ma" : arrDbhc_ma_res, "ctmt_ma" : arrCtmt_ma_res, "ma_nguon" : arrMa_nguon_res, "du_phong_ma" : arrDu_phong_ma_res, 
            "kb_ma" : arrMa_kb_res, "dien_giai" : arrDien_giai_res, "so_tien" : arrSo_tien_res, "so_yctt" : str_so_yctt, 
            "id_selected" : str_id_selected, 'actionFormSubmit' : actionForm, 'actionButton' : actionButton, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.coa_err != null) {
                        // tim ra row va col
                        if (data.coa_err) {
                            messageError = data.coa_err;
                        }
                        else if (data.khcheo_err) {
                            messageError = data.khcheo_err;
                        }
                    }
                }
            }
        },
        error : function (jqXHR, exception) {
            messageError = exception;
            if (exception == 'parsererror') {
                messageError += 'Requested JSON parse failed.'
            }

        }
        //        error : function (request, status, error) {
        //          messageError="err";
        //          alert(request.responseText);
        //          alert(status);
        //          alert(error);
        //        }
    });
    return messageError;
}

function msgAlertByAction(action, msgLTTDi) {
    if (action == 'ADD') {
        msgAlert = '';
        if (msgLTTDi == 'TRUNG_SO_YCTT') {
            msgAlert = 'B&#7841;n c&#243; ch&#7855;c ch&#7855;n mu&#7889;n ho&#224;n thi&#7879;n b&#7843;n ghi n&#224;y kh&#244;ng ?';
            jQuery("#dialog-confirm").html(msgAlert);
            jQuery("#dialog-confirm").dialog("open");
            //jQuery("#message" ).html('S? y�u c?u thanh to�n ?� t?n t?i! <br/> B?n ph?i nh?p m?t s? kh�c!');
        }
    }
}

/**
 *
 * ghiDuLieu2DB
 */
function ghiDuLieu2DB(strActdo, bCheck) {
    var strSaveVal = '';
    var allowSubmit = false;
    if (document.getElementById("save").value != null)
        strSaveVal = document.getElementById("save").value;
    if (checkInput(strActdo, bCheck)) {
        document.getElementById("ma_nhkb_chuyen_cm").focus();
        if (strSaveVal == 'ghi' || strSaveVal == '&nbsp;<SPAN class=sortKey>G</SPAN>hi') {
            document.forms[0].action = 'lttAddExc.do';
            allowSubmit = preventDuplicateSubmit();
            if (allowSubmit) {
                document.forms[0].submit();
            }
        }
        else {
            //            var ttvnhan = jQuery("#ttvnhan").val(), nhkbchuyennhan = jQuery("#nhkbchuyennhan").val(), sotien = jQuery("#sotien").val(), soyctt = jQuery("#soyctt").val(), trangthai = jQuery("#trangthai").val();
            //            var strTimKiem = '?ttvnhan=' + ttvnhan + '&soyctt=' + soyctt + '&nhkbchuyennhan=' + nhkbchuyennhan + '&sotien=' + sotien + '&trangthai=' + trangthai;
            // day lai ko check
            if (checkExistDVQHNSAnNinh() && jQuery("#trang_thai").val() != '01') {
                if (jQuery("#ten_tk_chuyen_tmp").val() == jQuery("#ten_tk_chuyen").val()) {
                    alert(GetUnicode("L&#432;u &#253; th&#244;ng tin t&#234;n &#273;&#417;n v&#7883; chuy&#7875;n ph&#7847;n th&#244;ng tin ng&#432;&#7901;i chuy&#7875;n!"));
                    jQuery("#ten_tk_chuyen").focus();
                    return false;
                }
            }

            //            document.forms[0].action = 'lttUpdateExc.do' + strTimKiem;
            //            allowSubmit = preventDuplicateSubmit();
            //            if (allowSubmit) {
            completeActionLttDi();
            //            }
        }
    }
    //      jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", true);
    //      alert(GetUnicode("&#272;&#226;y l&#224; l&#7879;nh nh&#7853;n t&#7915; tabmis !\n Y&#234;u c&#7847;u ki&#7875;m tra l&#7841;i chi ti&#7871;t c&#225;c kho&#7843;n m&#7909;c"));
}

function ghiLTTDenQToan2DB(strActdo, bCheck) {
    if (strActdo == null || strActdo == '' || strActdo == 'undefined')
        return;
    if (checkInputLTTDen(jQuery("#loai_hach_toan").val(), bCheck)) {
        var strSaveVal = '';
        document.getElementById("ma_nhkb_nhan_cm").focus();

        if (document.getElementById("save").value != null)
            strSaveVal = document.getElementById("save").value;

        if (strSaveVal == 'capnhat' || strSaveVal == '<SPAN class=sortKey>G</SPAN>hi') {
            jQuery("#nt_id").attr( {
                "enabled" : true
            });
            //            var ttvnhan = jQuery("#ttvnhan").val(), nhkbchuyennhan = jQuery("#nhkbchuyennhan").val(), sotien = jQuery("#sotien").val(), soyctt = jQuery("#soyctt").val(), trangthai = jQuery("#trangthai").val();
            //            var strTimKiem = '&ttvnhan=' + ttvnhan + '&soyctt=' + soyctt + '&nhkbchuyennhan=' + nhkbchuyennhan + '&sotien=' + sotien + '&trangthai=' + trangthai;
            //            document.forms[0].action = strActdo + strTimKiem;
            completeActionLttDen(bCheck);
        }
    }
}
function skipTabReadonlyField(event, strIdFocus) {
    if (strIdFocus == '' || strIdFocus == null) {
        if (event.keyCode == 13) {
            event.keyCode = 9;
        }
    }
    else {
        if (event.keyCode == 13 || event.keyCode == 9) {
            //Dat con tro chuot vao cuoi chuoi, roi moi focus        
            document.getElementById(strIdFocus).focus();
        }
    }
}

function skipTabReadonlyField1(event, strIdPreviousIdFocus) {
    if (event.keyCode == 13)
        event.keyCode = 9;
    if (strIdPreviousIdFocus != '' || strIdPreviousIdFocus != null) {
        if (event.keyCode == 9) {
            //Dat con tro chuot vao cuoi chuoi, roi moi focus        
            jQuery("#" + strIdPreviousIdFocus).trigger('focus');
            //document.getElementById(strIdFocus).focus();
        }
    }
}

function KTTJump2ApprovedOrReject(event, obj) {
    if (event.keyCode == 13)
        event.keyCode = 9;
    if (event.keyCode == 9) {
        if (obj.value == '') {
            //approved focus
            jQuery("#lydo_gd_day_lai").trigger('focus');
            jQuery("#rejectLtt").trigger('focus');

            return;
        }
        else {
            //rejectLtt focus
            jQuery("#lydo_gd_day_lai").focus();
            return;
        }
    }
    else if (event.keyCode == 27) {
        if (jQuery(document.activeElement).attr('id') == 'lydo_ktt_day_lai' || jQuery(document.activeElement).attr('id') == 'lydo_gd_day_lai') {
            //alert("input_id_last = "+input_id_last);
            document.getElementById('row_select').value = "row_ltt_" + input_id_last;
            jQuery("#row_ltt_" + input_id_last).find('input').focus();

        }
    }
}

function KTTJump2ApprovedOrRejectLTTDen(event, obj) {
    if (event.keyCode == 13)
        event.keyCode = 9;
    if (event.keyCode == 9) {
        if (obj.value == '') {
            //approved focus
            jQuery("#rejectLtt").trigger('focus');
            return;
        }
        else {
            //rejectLtt focus
            jQuery("#lydo_ktt_day_lai").focus();
            return;
        }
    }
}

function GDJump2ApprovedOrReject(event, obj) {
    if (event.keyCode == 13)
        event.keyCode = 9;
    if (event.keyCode == 9) {
        if (obj.value == '') {
            jQuery("#rejectLtt").trigger('focus');
            return;
        }
        else {
            obj.focus();
            //jQuery("#rejectLtt").focus();
            //document.getElementById("rejectLtt").focus();
            return;
        }
    }
}

function noenter() {
    return !(window.event && window.event.keyCode == 13);
}

function enterTab2SoTKorCOA(event) {
    if (event.keyCode == 13)
        event.keyCode = 9;
    if (event.keyCode == 9) {
        var ma_nhkb_chuyen_cm = jQuery("#ma_nhkb_chuyen_cm").val();
        var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
        var so_tk_chuyen = jQuery("#so_tk_chuyen").val();
        //nhkb chuyen o thong tin chung TRUNG voi Mo tai NHKB tt nguoi chuyen    
        if (so_tk_chuyen == '' && ma_nhkb_chuyen != '') {
            if (ma_nhkb_chuyen_cm == ma_nhkb_chuyen) {
                //document.getElementById("tkkt_ma").focus();
                jQuery("#cmdAddRow").trigger('focus');
            }
            else if (ma_nhkb_chuyen_cm != ma_nhkb_chuyen) {
                //document.getElementById("so_tk_chuyen").focus();
                jQuery("#ttin_kh_chuyen").trigger('focus');

            }
        }
        else if (ma_nhkb_chuyen == '') {
            if (!jQuery("#ma_nhkb_chuyen").attr("readOnly")) {
                alert(GetUnicode("B&#7841;n ph&#7843;i nh&#7853;p M&#7903; t&#7841;i NH/KB"));
                //document.getElementById("ma_nhkb_chuyen").focus();        
                jQuery("#so_tk_chuyen").trigger('focus');
            }
        }
        else if (so_tk_chuyen != '') {
            //document.getElementById("tkkt_ma").focus();
            jQuery("#cmdAddRow").trigger('focus');
        }
    }
}

function getStrTrangThai(strNumberTT) {
    var strTrangThai = '';
    if (strNumberTT == '01') {
        strTrangThai = "KTT &#272;&#7849;y l&#7841;i";
    }
    else if (strNumberTT == '02') {
        strTrangThai = "Ch&#7901; TTV ho&#224;n thi&#7879;n";
    }
    else if (strNumberTT == '03') {
        strTrangThai = "Ch&#7901; KTT duy&#7879;t";
    }
    else if (strNumberTT == '04') {
        strTrangThai = "G&#272; &#273;&#7849;y l&#7841;i";
    }
    else if (strNumberTT == '05') {
        strTrangThai = "Ch&#7901; G&#272; duy&#7879;t";
    }
    else if (strNumberTT == '06') {
        strTrangThai = "H&#7911;y";
    }
    else if (strNumberTT == '07') {
        strTrangThai = "&#272;&#227; g&#7917;i - Ch&#432;a v&#224;o giao di&#7879;n";
    }
    else if (strNumberTT == '11') {
        strTrangThai = "&#272;&#227; g&#7917;i - Ch&#7901; ch&#7841;y giao di&#7879;n";
    }
    else if (strNumberTT == '12') {
        strTrangThai = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#224;nh c&#244;ng";
    }
    else if (strNumberTT == '13') {
        strTrangThai = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#7845;t b&#7841;i";
    }
    else if (strNumberTT == '08') {
        strTrangThai = "&#272;&#227; g&#7917;i - Ng&#226;n h&#224;ng";
    }
    else if (strNumberTT == '14') {
        strTrangThai = "G&#7917;i NH th&#224;nh c&#244;ng";
    }
    else if (strNumberTT == '15') {
        strTrangThai = "G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng";
    }
    else if (strNumberTT == '16') {
        strTrangThai = "L&#7895;i truy&#7873;n tin";
    }
    return strTrangThai;
}

function showHideBtnByRowMaster(userType, trangThai, ktvTabmisObj) {
    if (userType.indexOf("TTV") !=  - 1) {
        if (trangThai == '01') {
            if (ktvTabmisObj != '0') {
                completeLttClicked();
                jQuery("#cancel").show('fast');
            }
        }
        else if (trangThai == '02') {
            if (ktvTabmisObj != '0') {            
                completeLttClicked();
                jQuery("#cancel").show('fast');
            }
        }
        else if (trangThai == '08') {
            //          gui ngan hang thanh cong
            jQuery("#resend").show();
        }
        else {
            jQuery("#completeLtt").hide();
            jQuery("#cancel").hide();
        }
    }
    if (userType.indexOf("KTT") !=  - 1) {  
        
        if (trangThai == '03' || trangThai == '04') {
            jQuery("#rejectLtt").show();
            jQuery("#approved").show();
            jQuery("#fsLyDoDayLai").show();//20141030
            
            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextArea'
            });
            jQuery("#lydo_ktt_day_lai").removeAttr("readOnly");
            jQuery("#lydo_ktt_day_lai").focus();
        }
        else if (trangThai == '07' || trangThai == '01') {
            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_ktt_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
            jQuery("#fsLyDoDayLai").show();//20141030
        }
        else {
            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_ktt_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
        }
    }
    if (userType.indexOf("GD") !=  - 1) {
        if (trangThai == '05') {
            jQuery("#rejectLtt").show();
            jQuery("#approved").show();
            jQuery("#lydo_gd_day_lai").attr( {
                'class' : 'fieldTextArea'
            });
            jQuery("#lydo_gd_day_lai").removeAttr("readOnly");
            jQuery("#lydo_gd_day_lai").focus();
        }
        else if (trangThai == '07' || trangThai == '04') {
            jQuery("#lydo_gd_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_gd_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
        }
        else {
            jQuery("#lydo_gd_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_gd_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
        }
    }
}

function hideBtnLTTDiDenForFind() {
    jQuery("#resend").hide();
    jQuery("#rejectLtt").hide();
    jQuery("#approved").hide();
    jQuery("#completeLtt").hide();
    jQuery("#cancel").hide();
    jQuery("#btn_in").show();
    jQuery("#tralai").hide();
    jQuery("#tdIdTraLai").hide();
    jQuery("#save").hide();
    //    jQuery("#trLenhGoc").attr("style","display:none");
}

function hideBtnLTTDenByRowMaster(userType, trangThai) {
    //Hien nut In chung tu (Cac trang thai tham khao tron AppConstant)
    var elemBtn_in = document.getElementById('btn_in');
    if (typeof (elemBtn_in) != 'undefined' && elemBtn_in != null) {
        if (trangThai == '07' || trangThai == '11' || trangThai == '12' || trangThai == '13') {
            jQuery("#btn_in").show();
        }
        else {
            jQuery("#btn_in").hide();
        }
    }
//    jQuery("#ly_do_htoan").attr("readonly", true);
//    jQuery("#ly_do_htoan").attr("class", "fieldTextCode");
    //Tam sua cho show nut In
    //    jQuery("#btn_in").show();    
    
    if (userType.indexOf("TTV") !=  - 1) {
        if (trangThai == '01' || trangThai == '02') {
            // hoan thien lenh tra lai
            jQuery("#tralai").show();
            jQuery("#tdIdTraLai").show();
            completeLttDenClicked();
        }
        else {
            jQuery("#completeLtt").hide();
        }
    }
    if (userType.indexOf("KTT") !=  - 1) {    
        if (trangThai == '03') {
            jQuery("#rejectLtt").show();
            jQuery("#approved").show();

            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextArea'
            });
            jQuery("#lydo_ktt_day_lai").removeAttr("readOnly");
            jQuery("#lydo_ktt_day_lai").focus();

            jQuery("#lydo_ktt_day_lai").keydown(function (event) {
                if (event.keyCode == 13)
                    event.keyCode = 9;
                if (event.keyCode == 9) {
                    if (jQuery("#lydo_ktt_day_lai").val() == '') {
                        //approved focus
                        jQuery("#rejectLtt").trigger('focus');
                        return;
                    }
                    else {
                        //rejectLtt focus
                        jQuery("#lydo_ktt_day_lai").focus();
                        return;
                    }
                }
            });

        }
        else if (trangThai == '07') {
            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_ktt_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
        }
        else {
            jQuery("#lydo_ktt_day_lai").attr( {
                'class' : 'fieldTextAreaRO'
            });
            jQuery("#lydo_ktt_day_lai").attr("readOnly", true);
            jQuery("#rejectLtt").hide();
            jQuery("#approved").hide();
        }
    }
}

function isGreaterCurrDate(dateValue) {
    var dayDiff =  - 1;
    var d = new Date()
    var y = d.getFullYear()
    var m = d.getMonth() + 1
    var day = d.getDate();
    if (day < 10)
        day = "0" + day;
    if (m < 10)
        m = "0" + m;
    var curDate = day + "/" + m + "/" + y

    dayDiff = CompareDate(dateValue, curDate);

    if (dayDiff ==  - 1)
        return true;
    else if (dayDiff == 0 || dayDiff == 1)
        return false;
}

function sbExitLtt(strLastAct) {
    if (strLastAct != null && strLastAct != "") {
        //window.history.go(-1);
        if (strLastAct.indexOf('.do') ==  - 1)
            strLastAct = strLastAct + ".do";
        window.location = strLastAct;
    }
    else {
        if (confirm('Ban co chac muon thoat khoi chuc nang nay?') == false)
            return false;
        else {
            window.location = "listLttAdd.do";
            //window.location = strLastAct + ".do";         
        }
    }
}
// Copyright: CodePug.com 2009
// Do not REMOVE these comments
// Usage: setReadonly('mySelect');
function setReadonly(selectElementId) {
    var selectElement = document.getElementById(selectElementId);
    if (selectElement) {
        var parent = selectElement.parentElement;
        var textValue = selectElement.options[selectElement.options.selectedIndex].innerText;
        if (!parent) {
            parent = selectElement.parentNode;
            textValue = selectElement.options[selectElement.options.selectedIndex].text;
        }
        var input = document.createElement("input");
        input.setAttribute("id", selectElement.id);
        input.setAttribute("type", "text");
        input.setAttribute("value", textValue);
        input.style.background = "#cccccc";
        input.readOnly = true;
        parent.appendChild(input);
    }
    selectElement.style.display = "none";
}

function removeReadonly(selectElementId) {
    var selectElement = document.getElementById(selectElementId);
    if (selectElement) {
        selectElement.style.display = "true";
    }
}

function inspect(obj, maxLevels, level) {
    var str = '', type, msg;

    // Start Input Validations
    // Don't touch, we start iterating at level zero
    if (level == null)
        level = 0;

    // At least you want to show the first level
    if (maxLevels == null)
        maxLevels = 1;
    if (maxLevels < 1)
        return '<font color="red">Error: Levels number must be > 0</font>';

    // We start with a non null object
    if (obj == null)
        return '<font color="red">Error: Object <b>NULL</b></font>';
    // End Input Validations
    // Each Iteration must be indented
    str += '<ul>';

    // Start iterations for all objects in obj
    for (property in obj) {
        try {
            // Show "property" and "type property"
            type = typeof (obj[property]);
            str += '<li>(' + type + ') ' + property + ((obj[property] == null) ? (': <b>null</b>') : ('')) + '</li><br/>';

            // We keep iterating if this property is an Object, non null
            // and we are inside the required number of levels
            if ((type == 'object') && (obj[property] != null) && (level + 1 < maxLevels))
                str += inspect(obj[property], maxLevels, level + 1);
        }
        catch (err) {
            // Is there some properties in obj we can't access? Print it red.
            if (typeof (err) == 'string')
                msg = err;
            else if (err.message)
                msg = err.message;
            else if (err.description)
                msg = err.description;
            else 
                msg = 'Unknown';

            str += '<li><font color="red">(Error) ' + property + ': ' + msg + '</font></li>';
        }
    }

    // Close indent
    str += '</ul>';

    return str;
}

function createDiv() {
    var divTag = document.createElement("div");

    divTag.id = "div1";

    divTag.setAttribute("align", "center");

    divTag.style.margin = "0px auto";

    divTag.className = "dynamicDiv";

    divTag.innerHTML = "  ";

    document.body.appendChild(divTag);

    var pTag = document.createElement("p");

    pTag.setAttribute("align", "center");

    pTag.innerHTML = " ";

    document.getElementById("div1").appendChild(pTag);
}

function formatPhan(number) {
    var num = number.toString().replace(/\$|\./g, '');
    if (num.length > 15)
        num = num.substring(0, 15);
    num = Math.floor(num * 100 + 0.50000000001);
    num = Math.floor(num / 100).toString();
    for (var i = 0;i < Math.floor((num.length - (1 + i)) / 3);i++)
        num = num.substring(0, num.length - (4 * i + 3)) + '.' + num.substring(num.length - (4 * i + 3));
    return num;
}

/**
 * Ham : toFormatNumberDe_TTSP()
 * Dinh dang 1 gia tri so theo dinh dang #,###.##.
 * 	pnumber : Gia tri so can format.
 * 	decimals : So chu so thap phan.
 * Gia tri: So da duoc format
 *
 * Su dung: x = toFormatNumberDe_TTSP(123,999, 2)
 */
function toFormatNumberDe_TTSP(pnumber, decimals, currencyId) {
    if (isNaN(pnumber)) {
        return '';
    }    
    if (pnumber == 0 || pnumber == '0')
        return '';

    if (currencyId == null || currencyId == '' || currencyId == 'undefined')
        currencyId = 'VND';
    var decimalSeparator = '';
    var thousandSeparator = '';
    if (currencyId == 'VND') {
        decimalSeparator = ',';
        thousandSeparator = '.';
    }
    else {
        decimalSeparator = '.';
        thousandSeparator = ',';
    }

    var snum = new String(pnumber);
    var sec = snum.split(decimalSeparator);
//    var arg = toFormatNumberDe_TTSP.arguments;

    var whole = parseFloat(sec[0]);
    //Can kiem tra lai doan nay - khang
    var strWhole = whole + "";
    if (strWhole.indexOf("e") !=  - 1) {
        whole = sec[0];
    }
    var result = '';
    var temp = '';
    if (decimals != 0) {
        if (sec.length > 1) {
            var dec = new String(sec[1]);
                    dec = String(parseFloat(sec[1])/Math.pow(10,(dec.length - decimals))); 
                    dec = String(whole + Math.round(parseFloat(dec))/Math.pow(10,decimals)); 
                    var dot = dec.indexOf(decimalSeparator); 
                    if(dot == -1){ 
                      dec += decimalSeparator;
                      for(i=1;i<=decimals;i++) { 
                        dec += '0'; 
                      }
                    }
            result = dec;
        }
        else {
            result = whole;
        }
    }
    else {
        result = whole;
    };
    snum = String(result);
    sec = snum.split(decimalSeparator);
    result = sec[0];
    if (sec[0].length > 3) {
        dec = sec[0];
        pos = dec.length % 3;
        temp = dec.substr(0, pos);
        dec = dec.substr(pos, dec.length);
        pos = (dec.length - pos) / 3;
        for (i = 0;i < pos;i++) {
            if (temp.length > 0)
                temp = temp + thousandSeparator;
            temp += dec.substr(3 * i, 3);
        }
        result = temp;
    }
    //Tam thoi bo phan Thap phan
    //    if (sec.length > 1) { 
    //      result += decimalSeparator;
    //      temp = sec[1];
    //      
    //      pos = temp.length;
    //      for(i=pos;i<decimals;i++){
    //        temp += "0";
    //      }
    //      result += temp;
    //    } else if (arg.length > 2) {
    //      result += decimalSeparator;
    //      
    //      for(i=0;i<arg[2];i++){
    //        result += '0';
    //      }
    //    } 			
    return result;
}
/*1.000.000.000.000.000.000
   * param: obj
   * */
function validSoTien(obj) {    
    if (obj == null || obj.readOnly == true || obj.readOnly == "readOnly") {
        return;
    }
    var soTien = obj.value;
    
    //chuan la 25
    if (soTien.length > 24) {
        alert(GetUnicode("S&#7889; ti&#7873;n l&#7899;n v&#432;&#7907;t qu&#225; gi&#225; tr&#7883; cho ph&#233;p c&#7911;a h&#7879; th&#7889;ng"));
        obj.select();
        obj.focus();
        return;
    }
    //      while(soTien.indexOf(".") != -1){
    //        soTien = soTien.replace(".", "");
    //      }
    var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
    soTien = convertCurrencyToNumber(soTien, nt_id);
    
    //chuan la 19
    if (soTien.length > 18) {
        alert(GetUnicode("S&#7889; ti&#7873;n l&#7899;n v&#432;&#7907;t qu&#225; gi&#225; tr&#7883; cho ph&#233;p c&#7911;a h&#7879; th&#7889;ng"));
        obj.select();
        obj.focus();
        return;
    }
}
function changeMaNTThongKeTong(obj, url){    
    nt_id = obj.value;
    if (nt_id != null && nt_id != '') {
        jQuery.ajax( {
            type : "POST", url : url, data :  {
                "nt_id" : nt_id, "type":url, "nd" : Math.random() * 100000
            },
            dataType : 'json',  success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data == null) {
                        alert('Không lấy được dữ liệu');
                        return;
                    }
                    else {                   
                        jQuery("#tong_so_mon").val(data.tong_so_mon);
                        jQuery("#tong_so_tien").val(CurrencyFormatted(data.tong_so_tien, nt_id));
                    }
                }
            }
        });
    }
}


/**
*20171010 thuongdt bo sung ham tra cuu nhanh su dung js kiem tra tinh tong tien tong lenh
*param: obj
**/
function changeMaNTThongKeTongJS(obj){ 
    var vtongsotien =  document.getElementsByName('tongsotien');
    var vntid =  document.getElementsByName('ntid');
    var vntidtemp = '';
    var vcheck = true;
    var tongtien = 0;
    var vcount = 0;
    if(vntid.length>0){
     if(obj == null || obj.value == '') {
        for(var i = 0; i< vntid.length; i++){
          if(i >0 && vntidtemp != vntid[i].value){
            vcheck = false;
          }
          tongtien = tongtien + Number(vtongsotien[i].value);
          vntidtemp = vntid[i].value;
        }
       if(vcheck == true){
        jQuery("#tong_so_mon").val(''+vntid.length);
        jQuery("#tong_so_tien").val(CurrencyFormatted(tongtien, vntidtemp));        
       }else{
        jQuery("#tong_so_mon").val(''+vntid.length);
        jQuery("#tong_so_tien").val('');
       }
     }else{
      for(var i = 0; i< vntid.length; i++){
        if(obj.value == vntid[i].value){
         tongtien = tongtien + Number(vtongsotien[i].value);
         vcount ++;
        }
      }
       jQuery("#tong_so_mon").val(''+vcount);
       jQuery("#tong_so_tien").val(CurrencyFormatted(tongtien, obj.value));      
      }
    }else{
      jQuery("#tong_so_mon").val('0');
      jQuery("#tong_so_tien").val('0');
    }
}

function changeMaNT() {
    var i;
    var tbl = document.getElementById('tblThongTinChiTietCOA');
    var rowOnTable = tbl.rows.length - 1;
    //manhnv-20141114
//    var nt_id = document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText;
//    var nt_id_old = '';
//    try{
//      nt_id_old = jQuery("#nt_id_old").val(); 
//    }catch(ex){
//      nt_id_old = '';
//      alert(ex);
//    }
//    //alert('changeMaNT='+nt_id_old);
//    if (nt_id == null || '' == nt_id || nt_id == 'undefined')
//        nt_id = 'VND';
//        
//    if (nt_id_old == null || '' == nt_id_old || nt_id_old == 'undefined')
//        nt_id_old = nt_id;
//    //------------------------------     
    if (rowOnTable > 1) {
        for (i = 0;i < rowOnTable;i++) {
            //manhnv-20141114
//            try{
//              document.getElementById("nt_id_old").value = nt_id_old;
//            }catch(ex){
//              alert(ex);
//            }            
            //----------------            
            document.forms[0].so_tien[i].focus();            
        }
    }
    else {
        document.forms[0].so_tien.focus();
    }
    if (!jQuery("#nt_id").attr("disabled")) {
        document.forms[0].nt_id.focus();
    }
    var selectElement = document.getElementById("nt_id");
    var textValue = selectElement.options[selectElement.options.selectedIndex].innerText != null ? selectElement.options[selectElement.options.selectedIndex].innerText : "";
    if (textValue.toUpperCase().trim() != 'VND') {
        jQuery("#tr_nh_trung_gian").show();
        //show loai phi
        document.getElementById("td_tong_sotien").colSpan = "3";
        jQuery("#td_loai_lable_loai_phi").show(); 
        jQuery("#td_loai_field_loai_phi").show();                 
                
        alert(GetUnicode("L&#432;u &#253; : b&#7841;n &#273;ang l&#7853;p giao d&#7883;ch ngo&#7841;i t&#7879; !"));
    }else{
      jQuery("#tr_nh_trung_gian").hide(); 
      //hide loai phi      
      jQuery("#td_loai_lable_loai_phi").hide(); 
      jQuery("#td_loai_field_loai_phi").hide(); 
      document.getElementById("td_tong_sotien").colSpan = "5";
            
    }
}
/*
   * param: N_YYMMDD_N...(ID nhan vien)_N...(Sequence)
   * */
function validSoYctt(strSoYctt) {
    if (strSoYctt == null || strSoYctt == '' || strSoYctt == 'undefined') {
        return false;
    }
    if (strSoYctt.length < 12 || strSoYctt.indexOf("__") !=  - 1) {
        return false;
    }
    if ((strSoYctt.split("_").length - 1) != 3) {
        return false;
    }
    var arrSoyctt = strSoYctt.split("_");
    if (isNaN(arrSoyctt[0]) || isNaN(arrSoyctt[1]) || isNaN(arrSoyctt[2]) || isNaN(arrSoyctt[3]) || arrSoyctt[0].length != 1 || arrSoyctt[1].length != 6) {
        return false;
    }

    var month = arrSoyctt[1].substr(2, 2);
    var date = arrSoyctt[1].substr(4, 2);
    if (month > 12 || month < 1 || date > 31 || date < 1) {
        return false;
    }

    return true;
}

function showLoading() {
    jQuery("#loading").show();
}

function hideLoading() {
    jQuery("#loading").hide();
}

/**
 * Check Date
 * Used: onblur="checkDateTTSP(this)"
 * Format: dd/mm/yyyy
 */
function checkDateTTSP(field) {
    var checkstr = "0123456789";
    var DateField = field;
    var Datevalue = "";
    var DateTemp = "";
    var seperator = "/";
    var day;
    var month;
    var year;
    var leap = 0;
    var err = 0;
    var i;
    var j = 0;
    err = 0;
    DateValue = DateField.value;
    if (DateValue == "")
        return true;

    /* d/m/yyyy hoac d/m/yy  */
    if (DateValue.indexOf("/") >= 0) {
        var arrDMY = DateValue.split("/");
        if (arrDMY != null && arrDMY.length == 3) {
            if (arrDMY[0] == 'null' || arrDMY[0] == '' || arrDMY[1] == 'null' || arrDMY[1] == '') {
                alertDateInvalid(DateField);
                return false;
            }
            if (arrDMY[0].length == 1)
                day = "0" + arrDMY[0];
            else 
                day = arrDMY[0];

            if (arrDMY[1].length == 1)
                month = "0" + arrDMY[1];
            else 
                month = arrDMY[1];

            if ((arrDMY[2] != null) && (arrDMY[2].length == 2 || arrDMY[2].length == 4)) {
                if (arrDMY[2].length == 2) {
                    year = "20" + arrDMY[2];
                }
                else if (arrDMY[2].length == 4) {
                    year = arrDMY[2];
                }
            }
            else {
                alertDateInvalid(DateField);
                return false;
            }
            DateValue = day + month + year;
        }
    }

    /* Delete all chars except 0..9 */
    for (i = 0;i < DateValue.length;i++) {
        if (checkstr.indexOf(DateValue.substr(i, 1)) >= 0) {
            DateTemp = DateTemp + DateValue.substr(i, 1);
        }
    }
    DateValue = DateTemp;
    /* Always change date to 8 digits - string*/
    /* if year is entered as 2-digit / always assume 20xx */
    if (DateValue.length == 6) {
        DateValue = DateValue.substr(0, 4) + '20' + DateValue.substr(4, 2);
    }
    if (DateValue.length != 8) {
        err = 19;
    }
    /* year is wrong if year = 0000 */
    year = DateValue.substr(4, 4);
    if (year == 0) {
        err = 20;
    }
    /* Validation of month*/
    day = DateValue.substr(0, 2);
    month = DateValue.substr(2, 2);
    if ((month < 1) || (month > 12)) {
        err = 21;
    }
    /* Validation of day*/
    if (day < 1) {
        err = 22;
    }

    /* Validation leap-year / february / day */
    if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
        leap = 1;
    }
    if ((month == 2) && (leap == 1) && (day > 29)) {
        err = 23;
    }
    if ((month == 2) && (leap != 1) && (day > 28)) {
        err = 24;
    }
    /* Validation of other months */
    if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
        err = 25;
    }
    if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
        err = 26;
    }
    /* if 00 ist entered, no error, deleting the entry */
    if ((day == 0) && (month == 0) && (year == 00)) {
        err = 0;
        day = "";
        month = "";
        year = "";
        seperator = "";
    }
    /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */

    if (err == 0) {
        DateField.value = day + seperator + month + seperator + year;
        if (DateField.value == "") {
            alertDateInvalid(DateField)
            return false;
        }
    }
    /* Error-message if err != 0 */
    else {
        alertDateInvalid(DateField)
        return false;
    }

    if (parseInt(year) < 1800) {
        alertDateInvalid(DateField)
        return false;
    }
    return true;
}

function alertDateInvalid(DateField) {
    alert(GetUnicode("&#272;&#7883;nh d&#7841;ng ng&#224;y kh&#244;ng h&#7907;p l&#7879;"));
    DateField.focus();
}

function mask(str, textbox, loc, seperator) {
    if (str != null && str != '' && str.indexOf("/") ==  - 1) {
        var locs = loc.split(',');
        for (var i = 0;i <= locs.length;i++) {
            for (var k = 0;k <= str.length;k++) {
                if (k == locs[i]) {
                    if (str.substring(k, k + 1) != seperator) {
                        str = str.substring(0, k) + seperator + str.substring(k, str.length)
                    }
                }
            }
        }
        textbox.value = str
    }
}

function preventDuplicateSubmit() {
    counter++;
    if (counter > 1) {
        return false;
    }
    return true;
}

function rowSelectedSetFocus(rowId) {
    var row_id_select_b4 = document.getElementById('row_select').value;
    jQuery("#" + row_id_select_b4).attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    jQuery("#" + row_id_select_b4).find('input').attr( {
        'class' : ''
    });
    jQuery("#" + rowId).find('input').attr( {
        'class' : 'ui-state-highlight'
    });
    jQuery('#' + rowId).find('input').focus();
    document.getElementById('row_select').value = rowId;
    //classRowHighLightLTT(rowId);
    //jQuery('#'+rowId).find('input').focus();
}
//Change style (dang ko dung)
function classRowHighLightLTT(tr_id) {
    var trs = document.getElementById('data-grid').getElementsByTagName('tr');
    for (var j = 0;j < trs.length;j++) {
        jQuery("#row_ltt_" + j).attr( {
            'class' : 'ui-widget-content ui-row-ltr'
        });
        jQuery("#row_ltt_" + j).find('input').attr( {
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
    document.getElementById('row_select').value = tr_id;
}

/*
   * defaultRowSelected()
   * Lua chon ban ghi dau tin o master
   * Su dung khi refresh/find/load page
   * Cung su dung sua khi thao tac xong duyet/huy/sua...
   */
function defaultSelectedLTT(strUrlAction, chucdanh, idLTT) {
    var row_default = "row_ltt_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        loadDetailLTTJson(strUrlAction, idLTT, row_default, chucdanh);
        rowSelectedSetFocus(row_default);
    }
}

function defaultRowSelectedLTT() {
    var row_default = "row_ltt_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        //loadDetailLTTJson(strUrlAction, idLTT, row_default, chucdanh);
        rowSelectedSetFocus(row_default);
    }
}

function defaultRowSelectedFirstRowLTT(strUrlAction, input_default, row_default, chucdanh, quyenLttDi) {
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        jQuery('#' + row_default).find('input').addClass("ui-state-highlight");
        loadDetailLTTJson(strUrlAction, input_default, row_default, chucdanh, quyenLttDi);
        rowSelectedSetFocus(row_default);
    }
}
/*
    * ham arrowUpDown
    * Xu ly move up/down ban ghi o master
    * Enter la click vao ban ghi da select
    * Esc thoat khoi su kien dang lam va quay ve selected vao ban ghi dang chon o master
    */
function arrowUpDownLTT(e) {
    var keyCode = e.keyCode || e.which, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
    //    alert(keyCode);
    var input_id = "";
    var total_row = "";
    limitEsc = 1;
    //alert("key code pressed: "+keyCode);
    switch (keyCode) {
        case arrow.up:
            input_id = parseInt(jQuery(document.activeElement).attr('id'));
            if (input_id >= 0) {
                //remove class highlight tr select
                if (input_id > 0)
                    input_id = input_id - 1;
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');

                //add class highligh tr previous
                jQuery("#row_ltt_" + input_id).addClass('ui-state-highlight');
                jQuery("#" + input_id).addClass('ui-state-highlight');
                jQuery("#" + input_id).focus();
                document.getElementById('row_select').value = "row_ltt_" + input_id;
            }
            break;
        case arrow.down:
            total_row = (jQuery("input[name='row_item']").length);
            input_id = parseInt(jQuery(document.activeElement).attr('id'));
            //alert(input_id);
            if (input_id <= parseInt(total_row) - 1) {
                //remove class highlight tr select
                if (input_id < parseInt(total_row) - 1)
                    input_id = parseInt(input_id) + 1;
                jQuery("#" + input_id).focus();
                jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
                jQuery(".ui-row-ltr").addClass('ui-widget-content ');
                //add class highligh tr previous
                jQuery("#row_ltt_" + input_id).addClass('ui-state-highlight');
                jQuery("#" + input_id).addClass('ui-state-highlight');
                document.getElementById('row_select').value = "row_ltt_" + input_id;
            }
            break;
        case arrow.enter:
            if (jQuery(document.activeElement).attr('class') == 'ui-state-highlight') {
                //alert(jQuery(document.activeElement).attr('id'));
                input_id_last = parseInt(jQuery(document.activeElement).attr('id'));
                jQuery('#row_ltt_' + jQuery(document.activeElement).attr('id')).click();
                document.getElementById('row_select').value = "row_ltt_" + input_id_last;
                //alert("input_id_last = "+input_id_last);              
            }
            break;
        case arrow.esc:
            if (jQuery(document.activeElement).attr('class') == 'ui-state-highlight') {
                //alert(jQuery(document.activeElement).attr('id'));
                jQuery('#lydo_ktt_day_lai').focus();
                //alert("input_id_last = "+input_id_last);              
            }
            if (jQuery(document.activeElement).attr('id') == 'lydo_ktt_day_lai' || jQuery(document.activeElement).attr('id') == 'lydo_gd_day_lai') {
                //alert("input_id_last = "+input_id_last);
                document.getElementById('row_select').value = "row_ltt_" + input_id_last;
                jQuery("#row_ltt_" + input_id_last).find('input').focus();

                //              var idTRSelected=jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
                //               jQuery('#'+idTRSelected).click();
                //               //alert("rowid ? ="+jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
                //               rowSelectedSetFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            }
            else if (jQuery(document.activeElement).attr('id') == input_id_last) {
                input_id_last = parseInt(jQuery(document.activeElement).attr('id'));
                jQuery('#row_ltt_' + jQuery(document.activeElement).attr('id')).click();
                document.getElementById('row_select').value = "row_ltt_" + input_id_last;
            }
            break;
        default :
            break;
        //        case arrow.back:
        //            if (jQuery(document.activeElement).attr('id') == 'lydo_ktt_day_lai' || jQuery(document.activeElement).attr('id') == 'lydo_gd_day_lai') {
        //                //alert("input_id_last = "+input_id_last);
        //                document.getElementById('row_select').value = "row_ltt_" + input_id_last;
        //                jQuery("#row_ltt_" + input_id_last).find('input').focus();
        //
        //                //              var idTRSelected=jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
        //                //               jQuery('#'+idTRSelected).click();
        //                //               //alert("rowid ? ="+jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
        //                //               rowSelectedSetFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
        //            }
        //            else if (jQuery(document.activeElement).attr('id') == input_id_last) {
        //                input_id_last = parseInt(jQuery(document.activeElement).attr('id'));
        //                jQuery('#row_ltt_' + jQuery(document.activeElement).attr('id')).click();
        //                document.getElementById('row_select').value = "row_ltt_" + input_id_last;
        //            }
        //            break;
    }
}

//function keyDownLTT() { alert('a');
//    jQuery.noConflict();
//    jQuery.each(arrTextFieldId, function (i, l) {
//        //alert('cuc cu ' + i +' '+'document.getElementById\('+l.toString()+'\).value');
//        strId = l.toString();
//        if (document.getElementById(strId) != null) {
//            document.getElementById(strId).tabIndex  = '101';
//        }
//    });
//}
function keyDownLTT() {
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
            defaultRowSelectedLTT();
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
        defaultRowSelectedLTT();
        return false;
    }
}
//function focusDefaultRow(){
//  var row_default = "row_ltt_0", input_default = jQuery('#' + row_default).find('input');
//    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
//        jQuery("#" + row_default).addClass("ui-state-highlight");
//        input_default.addClass("ui-state-highlight");
//        fillDataDTSTuDo(input_default.val(), row_default, bCheck);
//        rowSelectedFocus(row_default);
//    }
//}
function focusAfterAddCOA(obj) {
    if (!obj.readOnly) {
        obj.style.backgroundColor = '#ffeeaa';
    }
}

function blurAfterAddCOA(obj) {
    if (!obj.readOnly) {
        obj.style.backgroundColor = '';
    }
}
//function skipTabReadonlyFields(event, strIdFocus){
//  // Lay ra field dang focus
//  if (event.keyCode == 13) {
//            event.keyCode = 9;
//    }
//  var currentIndex=0;
//  for (var i = 0;i < arrFieldId.length;i++) {
//        if (strIdFocus.name == arrFieldId[i]) {
//            currentIndex = i;
//            for(var j = currentIndex;j<arrFieldId.length;j++ ){
//              if(jQuery("#"+arrFieldId[j]).is('[readonly]') || jQuery("#"+arrFieldId[j]).is('[disabled]') ){
//                continue;
//              }else{
//                jQuery("#" + arrFieldId[j]).focus(); 
//                //jQuery('#'+arrFieldId[j]+'').focus();
//                break;
//              }
//            }
//                    break;
//        }
//    }
//}
function showDown(evt) {
    evt = (evt) ? evt : ((event) ? event : null);
    if (evt) {
        if (event.keyCode == 27 && (event.srcElement.type == "text" || event.srcElement.type == "textarea" || event.srcElement.type == "password")) {
            // stop escape
            cancelKey(evt);
            return false;
        }
    }
}

function cancelKey(evt) {
    if (evt.preventDefault) {
        evt.preventDefault();
        return false;
    }
    else {
        evt.keyCode = 0;
        evt.returnValue = false;
    }
}

function autoFocusNextItem(value, nextItem) {
    //tktn
    if (value.length == 4) {
        jQuery("#" + nextItem).focus();
    }
}

function autoFillDefaultValue(id, lastRow) {
    if (lastRow == 'undefined' || lastRow == null) {
        if (jQuery("#" + id).val() == null && jQuery("#" + id).val() == '')
            return;
        if (id == 'dvsdns_ma') {
            if (jQuery("#" + id).val().length < 7 && jQuery("#" + id).val().length > 1) {
                alert(GetUnicode('M&#227; &#273;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch ph&#7843;i &#273;&#7911; 7 k&#253; t&#7921;'));
                jQuery("#" + id).focus();
                return;
            }
            else if (jQuery("#" + id).val().length == 0) {
                jQuery("#" + id).val('0000000');
            }
        }
        else if (id == 'capns_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('0');
        }
        else if (id == 'chuongns_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('000');
        }
        else if (id == 'nganhkt_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('000');
        }
        else if (id == 'ndkt_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('0000');
        }
        else if (id == 'dbhc_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('00000');
        }
        else if (id == 'ctmt_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('00000');
        }
        else if (id == 'ma_nguon') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('00');
        }
        else if (id == 'du_phong_ma') {
            if (jQuery("#" + id).val().length == 0)
                jQuery("#" + id).val('000');
        }
    }
    else {
        if (id == 'dvsdns_ma') {
            if (document.forms[0].dvsdns_ma[(lastRow - 1)].value == null || document.forms[0].dvsdns_ma[(lastRow - 1)].value == '')
                if (document.forms[0].dvsdns_ma[(lastRow - 1)].value.length < 7 && document.forms[0].dvsdns_ma[(lastRow - 1)].value.length > 1) {
                    alert(GetUnicode('M&#227; &#273;&#417;n v&#7883; quan h&#7879; ng&#226;n s&#225;ch ph&#7843;i &#273;&#7911; 7 k&#253; t&#7921;'));
                    document.forms[0].dvsdns_ma[(lastRow - 1)].focus();
                    document.forms[0].capns_ma[(lastRow - 1)].value = '';
                    return;
                }
                else if (document.forms[0].dvsdns_ma[(lastRow - 1)].value.length == 0) {
                    jQuery("#" + id).val('0000000');
                }
        }
        else if (id == 'capns_ma') {
            if (document.forms[0].capns_ma[(lastRow - 1)].value == null || document.forms[0].capns_ma[(lastRow - 1)].value == '')
                document.forms[0].capns_ma[(lastRow - 1)].value = "0";
        }
        else if (id == 'chuongns_ma') {
            if (document.forms[0].chuongns_ma[(lastRow - 1)].value == null || document.forms[0].chuongns_ma[(lastRow - 1)].value == '')
                document.forms[0].chuongns_ma[(lastRow - 1)].value = "000";
        }
        else if (id == 'nganhkt_ma') {
            if (document.forms[0].nganhkt_ma[(lastRow - 1)].value == null || document.forms[0].nganhkt_ma[(lastRow - 1)].value == '')
                document.forms[0].nganhkt_ma[(lastRow - 1)].value = "000";
        }
        else if (id == 'ndkt_ma') {
            if (document.forms[0].ndkt_ma[(lastRow - 1)].value == null || document.forms[0].ndkt_ma[(lastRow - 1)].value == '')
                document.forms[0].ndkt_ma[(lastRow - 1)].value = "0000";
        }
        else if (id == 'dbhc_ma') {
            if (document.forms[0].dbhc_ma[(lastRow - 1)].value == null || document.forms[0].dbhc_ma[(lastRow - 1)].value == '')
                document.forms[0].dbhc_ma[(lastRow - 1)].value = "00000";
        }
        else if (id == 'ctmt_ma') {
            if (document.forms[0].ctmt_ma[(lastRow - 1)].value == null || document.forms[0].ctmt_ma[(lastRow - 1)].value == '')
                document.forms[0].ctmt_ma[(lastRow - 1)].value = "00000";
        }
        else if (id == 'ma_nguon') {
            if (document.forms[0].ma_nguon[(lastRow - 1)].value == null || document.forms[0].ma_nguon[(lastRow - 1)].value == '')
                document.forms[0].ma_nguon[(lastRow - 1)].value = "00";
        }
        else if (id == 'du_phong_ma') {
            if (document.forms[0].du_phong_ma[(lastRow - 1)].value == null || document.forms[0].du_phong_ma[(lastRow - 1)].value == '')
                document.forms[0].du_phong_ma[(lastRow - 1)].value = "000";
        }
    }
}

function changeBtnDuyetState() {
    if (jQuery("#lydo_ktt_day_lai").val().length > 0)
        jQuery("#approved").hide();
    else 
        jQuery("#approved").show();
}

function enableLyDoHT() {
    var valueCheck = jQuery("#loai_hach_toan").val();
    if (valueCheck == '02') {
        jQuery("#ly_do_htoan").removeAttr("readonly");
        jQuery("#ly_do_htoan").removeAttr("class");
        jQuery("#ly_do_htoan").addClass("fieldTextArea");
    }
    else {
        jQuery("#ly_do_htoan").val('');
        jQuery("#ly_do_htoan").attr("readonly", true);
        jQuery("#ly_do_htoan").addClass("fieldTextAreaRO");
    }
}
function checkSotienBeforForcus(obj, ma_nt){
  if(convertCurrencyToNumber(obj.value,ma_nt) == 0){
    obj.value = "";
  }
}
function checkMaNTMuaBan(){
      if(jQuery("#ma_nt_mua_ban").val() == ''){
        alert(GetUnicode("C&#7847;n nh&#7853;p m&#227; ngo&#7841;i t&#7879; c&#7847;n mua."));
        jQuery("#ma_nt_mua_ban").focus();
      }
}
function checkMuaVND(){     
    if(jQuery("#ma_nt_mua_ban").val() == 'VND'){        
        jQuery("#so_tien_mua_ban").val('');
        jQuery("#so_tien_mua_ban").attr("readonly", true);
        jQuery("#so_tien_mua_ban").removeAttr("class");
        jQuery("#so_tien_mua_ban").addClass("fieldTextCode");
    }
    else {
        jQuery("#so_tien_mua_ban").removeAttr("readonly");
        jQuery("#so_tien_mua_ban").removeAttr("class");
        jQuery("#so_tien_mua_ban").addClass("fieldText");
        formatNumberJQuery('so_tien_mua_ban','ma_nt_mua_ban','ma_nt_mua_ban_old');
    }
}