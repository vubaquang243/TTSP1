jQuery(document).ready(function(){
  jQuery('#traCuuKhoBac').click(function(){
     jQuery('#listKhoBac tr').remove();
  });
});
function traCuuKhoBac(){
  var ma_kb = jQuery('#ma_kb').val();
  var ten_kb = jQuery('#ten_kb').val();
    jQuery.ajax({
   type : "POST",
   url : "traCuuKhoBac.do",
   data : {"ma_kb" : ma_kb, "ten_kb" : ten_kb},
   success : function(data, textstatus){
    if(data != null){
      var lstKhoBac = new Object();
      lstKhoBac = JSON.parse(data[0]);
      
      var tableHtml = ""; 
      if(lstKhoBac.size() > 0){ 
      jQuery('#listKhoBac tr').remove();
        for(var i = 0; i < lstKhoBac.size(); i++){
          tableHtml += '<tr class="row_kho_bac" ondblclick="selectRow(this);" class="ui-widget-content jqgrow ui-row-ltr" id="row_lov_"'+i+'>';
          tableHtml += '<td width="20%" align="center" id="ma" onkeydown=\"arrowUpDownLOV(event);\">'+lstKhoBac[i].ma+'<input type="hidden" id="ma_cha" value="'+lstKhoBac[i].ma_cha +'" /><\/td>';
          tableHtml += '<td id="ten" onkeydown=\"arrowUpDownLOV(event);\">'+lstKhoBac[i].ten+'<\/td>';
          tableHtml += '<\/tr>';
        }
        jQuery('#listKhoBac').append(tableHtml);
      }
    }
   }
 });
}

function selectRow(row){
 var ma = jQuery(row).find('td#ma').text();
 var ten = jQuery(row).find('td#ten').text();
 var ma_cha = jQuery('td#ma_cha').val();
   if(ma_cha== "undefined"){
    jQuery('#id_kho_bac_tinh').val(ma);
    //jQuery('#id_kho_bac_tinh option[value="'+ma+'"]').attr("selected",true);
    getThongTinKB();
   }else{
    jQuery('#id_kho_bac_tinh').val(ma_cha);
    jQuery('#id_kho_bac_tinh option[value="'+ma_cha+'"]').attr("selected",true);
    getThongTinKB();
    jQuery('#id_kho_bac_huyen').val(ma);
    //getThongTinKB();
    getThongTinNganHang();
   }
 jQuery('#dialog-form-lov-dm').dialog("close");
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
   