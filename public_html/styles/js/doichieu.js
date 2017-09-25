function fillDataDC(id, tr_id) {
      var ngay_dc;
      ngay_dc=document.getElementById("ngay_dc").value;
      if (id !=null && ""!=id){
      jQuery.ajax( {
          type : "POST", url : "DChieu1ListAction.do?strid="+tr_id, data :  { "id" : id, "ngay_dc" : ngay_dc, "tr_id" : tr_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          jQuery("#sodu_daungay").val(CurrencyFormatted(objectDM.sodu_daungay));
                          jQuery("#so_du").val(CurrencyFormatted(objectDM.so_du));
                          jQuery("#sotien_thu").val(CurrencyFormatted(objectDM.sotien_thu));
                          jQuery("#sotien_chi").val(CurrencyFormatted(objectDM.sotien_chi));
                          jQuery("#sotien_tcong").val(CurrencyFormatted(objectDM.sotien_tcong)); // so tien chi thu cong
                          jQuery("#sotien_dtu").val(CurrencyFormatted(objectDM.sotien_dtu));// so tien chi dien tu
                          jQuery("#so_tien_thu_tcong").val(CurrencyFormatted(objectDM.so_tien_thu_tcong));
                          jQuery("#so_tien_thu_dtu").val(CurrencyFormatted(objectDM.so_tien_thu_dtu));
                          jQuery("#id").val(objectDM.id);
                          jQuery("#trang_thai").val(objectDM.trang_thai);
                          jQuery("#ngay_dc").val(objectDM.ngay_dc);
                      });
                  }
              }
 
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
}

function defaultRowSelectedDC() {
//    alert(jQuery("#rowSelected").val());
    if(jQuery("#rowSelected").val()!=null && "" != jQuery("#rowSelected").val()){
      fillDataDC(jQuery("#id").val(), jQuery("#rowSelected").val());
      rowSelectedFocusDC(jQuery("#rowSelected").val());
    }else{
    var col_default = "td_qt_0";
    var row_default = "row_qt_0", input_default = jQuery('#' + col_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        fillDataDC(input_default.val(), row_default);
        rowSelectedFocusDC(row_default);
    }
    }
}

function rowSelectedFocusDC(rowId) {
    classRowHighLight(rowId);
    jQuery('#rowSelected').val(rowId);
    jQuery('#' + rowId).find('input').focus();

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

function arrowUpDownDC(e) {
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
                jQuery(".ui-row-ltr").addClass('ui-widget-content');

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
                jQuery(".ui-row-ltr").addClass('ui-widget-content');
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
            rowSelectedFocusDC(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }

}

function setStyleRowEmptyDC() {
    jQuery("#data-grid tr:first").attr( {
        'class' : 'ui-widget-content ui-row-ltr'
    });
    if (jQuery("#data-grid tr:first").attr('class') == 'ui-widget-content ui-row-ltr') {
        jQuery("#data-grid tr:first").attr( {
            'class' : 'ui-row-ltr ui-state-highlight'
        });
    }
}

//function CurrencyFormatted(num)
//{
//    num = num.toString().replace(/\$|\,/g,'');
//    if(isNaN(num))
//    num = "0";
//    sign = (num == (num = Math.abs(num)));
//    num = Math.floor(num*100+0.50000000001);
//    //cents = num%100;
//    num = Math.floor(num/100).toString();
////    if(cents<10)
////    cents = "0" + cents;
//    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
//    num = num.substring(0,num.length-(4*i+3))+'.'+
//    num.substring(num.length-(4*i+3));
//    return (((sign)?'':'-')  + num ); //+ '.' + cents
//}

// XN doi chieu XNDCTHop1Action

function fillDataXNDC(act, ngay_dc, receive_bank, ttsp_id, pht_id, tr_id,tthai) {
    document.forms[0].action= act + "?rowSelected=" + tr_id+"&ngay_dc=" + ngay_dc +"&receive_bank=" + receive_bank+"&ttsp_id=" + ttsp_id+"&pht_id=" + pht_id+"&tthai_dxn_thop=" + tthai;
    document.forms[0].submit();
}


function fillDataDuyetXNDC(act, id, tr_id) {
    document.forms[0].action= act + "?id=" + id + "&rowSelected=" + tr_id;
    document.forms[0].submit();
}

function fillDataDuyetXNTH(act, id,ngay_dc, tr_id, receive_bank,tthai_dxn_thop) {
    document.forms[0].action= act + "?id=" + id + "&rowSelected=" + tr_id+ "&ngay_dc=" + ngay_dc + "&receive_bank="+receive_bank+"&tthai_dxn_thop="+tthai_dxn_thop; 
    document.forms[0].submit();
}

function fillDataDuyetXNDC3(act, id, tr_id) {
    document.forms[0].action= act + "?kq_id=" + id + "&rowSelected=" + tr_id;
    document.forms[0].submit();
}



function fillDataTHopDC1(act, id, kq_id,send_bank, tr_id) {
    document.forms[0].action= act + "?id=" + id + "&kq_id="+kq_id+"&send_bank="+send_bank+"&rowSelected=" + tr_id;
    document.forms[0].submit();
}

function fillDataDC2(act, send_bank, ngay_dc,bk_id, tr_id) {
    document.forms[0].action= act + "?rowSelected=" + tr_id + "&ngay_dc=" + ngay_dc + "&send_bank=" + send_bank+"&bk_id="+bk_id;
    document.forms[0].submit();
}

function fillDataDuyetDC2(act, receive_bank, ngay_dc,bk_id, tr_id) {
    document.forms[0].action= act + "?rowSelected=" + tr_id + "&ngay_dc=" + ngay_dc + "&receive_bank=" + receive_bank+"&bk_id="+bk_id;
    document.forms[0].submit();
}


function rowSelectedFocusXNDC(rowId) {
    classRowHighLight(rowId);
    jQuery('#' + rowId).find('input').focus();
}

function defaultRowSelectedXNDC() {
    var row_default = "row_qt_0", input_default = jQuery('#' + row_default).find('input');
    if (jQuery("#" + row_default).html() != null && jQuery("#" + row_default).html() != 'null') {
        jQuery("#" + row_default).addClass("ui-state-highlight");
        input_default.addClass("ui-state-highlight");
        //fillDataBKQuyetToan(input_default.val(), row_default);
        rowSelectedFocusXNDC(row_default);
    }
}
function arrowUpDownXNDC(e) {
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
                jQuery(".ui-row-ltr").addClass('ui-widget-content');

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
                jQuery(".ui-row-ltr").addClass('ui-widget-content');
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
            rowSelectedFocusXNDC(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            break;
    }
}

function toFormatNumber(pnumber,decimals,char) {
	
	var decimalCharacter = char;
	if (isNaN(pnumber))  
	{
		return ''; 
		
	}
//  alert(pnumber);
	if (pnumber == 0 || pnumber == '0' ) return '0';	
    
	var snum = new String(pnumber); 	
	var sec = snum.split(decimalCharacter); 
//	var arg = toFormatNumberDe_TCS.arguments;

	
	var whole = parseFloat(sec[0]); 
	var result = ''; 
	var temp = ''; 	
	result = whole;

	snum = String(result); 
	sec = snum.split(decimalCharacter); 
	result=sec[0];		
	if (sec[0].length > 3) {
		dec = sec[0]; 
    
		pos = dec.length%3;
    
		temp = dec.substr(0, pos);
		dec = dec.substr(pos,dec.length);
		pos = (dec.length - pos)/3;		
		for(i=0;i<pos;i++) { 
			if (temp.length>0) temp = temp + decimalCharacter;
			temp += dec.substr(3*i,3); 
		} 		
		result= temp;
	}
		
	return result; 
} 


