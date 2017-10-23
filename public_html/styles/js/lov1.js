function fillLovDM() {
    var ma = jQuery('#ma_lov').val();   
    var ten = jQuery("#ten_lov").val();
    var loai = jQuery("#loai_lov").val();

    if (loai == null || loai == '') {
        loai = "DMNH";
    }
    if (ma == null)
        ma = "";
    if (ten == null)
        ten = "";
    jQuery.ajax( {    
        type : "POST", url : "lovAction.do", data :  {
            "ma" : ma, "ten" : ten, "loai" : loai
        },        
        success : function (data, textstatus) {            
            jQuery("#tblLovDM").html("");           
            if (textstatus != null && textstatus == 'success') {
                var strTrTag = "";
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                      strTrTag = strTrTag + "<tr class='ui-widget-content jqgrow ui-row-ltr' id='row_lov_"+i+"' ondblclick=\"setIntoFieldLOV('"+objectDM.id+"','"+objectDM.ma_nh+"','"+objectDM.ten+"'); rowSelectedFocusLOV('row_lov_"+i+"');\" onclick=\"rowSelectedFocusLOV('row_lov_"+i+"');\"><td width='20%' onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ma_nh+"</td><td onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ten+"</td></tr>";                      
                    });
                    strTrTag = strTrTag + "";   
                    jQuery('#tblLovDM').append(strTrTag);                
                    
                    jQuery("#row_lov_0").addClass('ui-state-highlight');
                    jQuery("#0").addClass('ui-state-highlight');
                    jQuery("#0").focus();
                }                
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function fillLovDMKBTCUU() {

    var ma = jQuery('#ma_lov').val();   
    var ten = jQuery("#ten_lov").val();
    var loai = jQuery("#loai_lov").val();

    if (loai == null || loai == '') {
        loai = "DMKBTCUU";
    }
    if (ma == null)
        ma = "";
    if (ten == null)
        ten = "";
    jQuery.ajax( {    
        type : "POST", url : "lovAction.do", data :  {
            "ma" : ma, "ten" : ten, "loai" : loai
        },        
        success : function (data, textstatus) {            
            jQuery("#tblLovDM").html("");           
            if (textstatus != null && textstatus == 'success') {
                var strTrTag = "";
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    
                      strTrTag = strTrTag + "<tr class='ui-widget-content jqgrow ui-row-ltr' id='row_lov_"+i+"' ondblclick=\"setIntoFieldLOVTCUU('"+objectDM.id+"','"+objectDM.ma_nh+"','"+objectDM.ten+"','"+objectDM.id_cha+"'); rowSelectedFocusLOV('row_lov_"+i+"');\" onclick=\"rowSelectedFocusLOV('row_lov_"+i+"');\"><td width='20%' onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ma_nh+"</td><td onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ten+"</td></tr>";                      
                      
                    });
                    strTrTag = strTrTag + "";  
                    jQuery('#tblLovDM').append(strTrTag);                
                    
                    jQuery("#row_lov_0").addClass('ui-state-highlight');
                    jQuery("#0").addClass('ui-state-highlight');
                    jQuery("#0").focus();
                }                
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}

function fillLovDMKBTCUULTT() {

    var ma = jQuery('#ma_lov').val();   
    var ten = jQuery("#ten_lov").val();
    var loai = jQuery("#loai_lov").val();

    if (loai == null || loai == '') {
        loai = "DMKBTCUU";
    }
    if (ma == null)
        ma = "";
    if (ten == null)
        ten = "";
    jQuery.ajax( {    
        type : "POST", url : "lovAction.do", data :  {
            "ma" : ma, "ten" : ten, "loai" : loai
        },        
        success : function (data, textstatus) {            
            jQuery("#tblLovDM").html("");           
            if (textstatus != null && textstatus == 'success') {
                var strTrTag = "";
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    
                      strTrTag = strTrTag + "<tr class='ui-widget-content jqgrow ui-row-ltr' id='row_lov_"+i+"' ondblclick=\"setIntoFieldLOVTCUULTT('"+objectDM.id+"','"+objectDM.ma_nh+"','"+objectDM.ten+"','"+objectDM.ma_cha+"'); rowSelectedFocusLOV('row_lov_"+i+"');\" onclick=\"rowSelectedFocusLOV('row_lov_"+i+"');\"><td width='20%' onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ma_nh+"</td><td onkeydown=\"arrowUpDownLOV(event);\" id='"+i+"'>"+objectDM.ten+"</td></tr>";                      
                      
                    });
                    strTrTag = strTrTag + "";  
                    jQuery('#tblLovDM').append(strTrTag);                
                    
                    jQuery("#row_lov_0").addClass('ui-state-highlight');
                    jQuery("#0").addClass('ui-state-highlight');
                    jQuery("#0").focus();
                }                
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}



function setIntoFieldLOV(id, ma, ten){
  var ma_field_id = jQuery("#ma_field_id_lov").val();
  var ten_field_id = jQuery("#ten_field_id_lov").val();
  var id_field_id = jQuery("#id_field_id_lov").val();
  
  jQuery("#"+id_field_id).val(id);  
  jQuery("#"+ma_field_id).val(ma);
  jQuery("#"+ten_field_id).val(ten);
  
  jQuery("#tblLovDM").html("");  
  jQuery("#ma_lov").val("");
  jQuery("#ten_lov").val("");
  jQuery("#dialog-form-lov-dm").dialog( "close" );  
  jQuery("#"+ma_field_id).focus()
}

function setIntoFieldLOVTCUU(id, ma, ten,id_cha){
  var ma_field_id = jQuery("#ma_field_id_lov").val();
  var ten_field_id = jQuery("#ten_field_id_lov").val();
  var id_field_id = jQuery("#id_field_id_lov").val();
  var id_cha_field_id = jQuery("#id_cha_field_id_lov").val();
  
  if(id_cha==1||id_cha=='1'){
  jQuery("#"+id_field_id).val("");  
    jQuery("#"+ma_field_id).val(ma);
    jQuery("#"+ten_field_id).val(ten);
    jQuery("#"+id_cha_field_id).val(id);
    if(ma=='0003'){
//      getTenKhoBacDC(id,'1');
    }
    if(ma!='0003'){
//      getTenKhoBacDC('',id);
    }
    
  }else if(id_cha!=1&&id_cha!='1'){
    jQuery("#"+id_field_id).val(id);  
    jQuery("#"+ma_field_id).val(ma);
    jQuery("#"+ten_field_id).val(ten);
    jQuery("#"+id_cha_field_id).val(id_cha);
//    getTenKhoBacDC(id,id_cha);
  }
  
  
  jQuery("#tblLovDM").html("");  
  jQuery("#ma_lov").val("");
  jQuery("#ten_lov").val("");
  jQuery("#dialog-form-lov-dm").dialog( "close" );  
  jQuery("#"+ma_field_id).focus()
  
}

function setIntoFieldLOVTCUULTT(id, ma, ten,ma_cha){
  var ma_field_id = jQuery("#ma_field_id_lov").val();
  var ten_field_id = jQuery("#ten_field_id_lov").val();
  var id_field_id = jQuery("#id_field_id_lov").val();
  var ma_cha_field_id = jQuery("#ma_cha_field_id_lov").val();
//  alert(ma+'----'+ma_cha);
  
  if(ma_cha=='0001'){
    jQuery("#"+id_field_id).val("");  
    jQuery("#"+ma_field_id).val(ma);
    jQuery("#"+ten_field_id).val(ten);
    jQuery("#"+ma_cha_field_id).val(ma);
    if(ma=='0003'){
//      getTenKhoBacLTT('0003','0003');
    }
    if(ma!='0003'){
//      getTenKhoBacLTT('',ma);
    }
    
  }else if(ma_cha!='0001'){
  
    jQuery("#"+id_field_id).val(id);  
    jQuery("#"+ma_field_id).val(ma);
    jQuery("#"+ten_field_id).val(ten);
    jQuery("#"+ma_cha_field_id).val(ma_cha);
//    getTenKhoBacLTT(ma,ma_cha);
  }
  
  jQuery("#ma_kb").val(ma);  // xoa
  jQuery("#tblLovDM").html("");  
  jQuery("#ma_lov").val("");
  jQuery("#ten_lov").val("");
  jQuery("#dialog-form-lov-dm").dialog( "close" );  
  jQuery("#"+ma_field_id).focus()
  
}

function rowSelectedFocusLOV(rowId){
    classRowHighLightLOV(rowId);
    jQuery('#'+rowId).find('input').focus();
    
   
  }
   //Change style
   function classRowHighLightLOV(tr_id){     
      var trs = document.getElementById('data-grid-lov').getElementsByTagName('tr');     
      for (var j = 0;j < trs.length;j++) {
          jQuery("#row_lov_" + j).attr( {'class' : 'ui-widget-content ui-row-ltr'});
          jQuery("#row_lov_" + j).find('input').attr({'class':''});
          if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
              jQuery("#" + tr_id).attr( {'class' : 'ui-row-ltr ui-state-highlight'});
              jQuery("#" + tr_id).find('input').attr( {'class' : 'ui-state-highlight'});
          }
      }
  }
function arrowUpDownLOV(e){
    var keyCode = e.keyCode || e.which,
        arrow = {up: 38, down: 40, enter: 13, esc: 27};
       var input_id="";
       var total_row="";       
    switch (keyCode) {
       case arrow.up:            
            input_id=parseInt(jQuery(document.activeElement).attr('id'));
            
            if(input_id>=0){
              //remove class highlight tr select
              if(input_id>0)
                input_id=input_id-1;
              jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
              jQuery(".ui-row-ltr").addClass('ui-widget-content ');
            
              //add class highligh tr previous
              jQuery("#row_lov_"+input_id).addClass('ui-state-highlight');
              jQuery("#"+input_id).addClass('ui-state-highlight');
              jQuery("#"+input_id).focus();
              
            }
      break;
     case arrow.down:        
        total_row=jQuery('#data-grid-lov tr').length;
        input_id=parseInt(jQuery(document.activeElement).attr('id'));
        if(input_id<=parseInt(total_row)-1){
          //remove class highlight tr select
          if(input_id<parseInt(total_row)-1)
            input_id=parseInt(input_id)+1;
          jQuery("#"+input_id).focus();
          jQuery(".ui-state-highlight").removeClass('ui-state-highlight');
          jQuery(".ui-row-ltr").addClass('ui-widget-content ');
          //add class highligh tr previous
          jQuery("#row_lov_"+input_id).addClass('ui-state-highlight');
          jQuery("#"+input_id).addClass('ui-state-highlight');
        }
        break;
      case arrow.enter:          
          if(jQuery(document.activeElement).attr('class')=='ui-state-highlight'){
              jQuery('#row_lov_'+jQuery(document.activeElement).attr('id')).dblclick();
          }
        break;
      case arrow.esc:
        var ma_field_id = jQuery("#ma_field_id_lov").val();
        jQuery("#"+ma_field_id).focus();  
        break;
      default:        
    }
   }
   