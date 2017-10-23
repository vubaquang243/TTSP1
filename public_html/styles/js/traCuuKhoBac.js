
jQuery(document).ready(function () {
  jQuery('#traCuuKhoBac').click(function(){
    var ib_kb_tinh = jQuery('#id_kho_bac_tinh_1').val();
    jQuery('#listKhoBac tr').remove();
    if(ib_kb_tinh == ""){
    jQuery('table tr td#id_kho_bac').text("Mã kho bạc tỉnh");    
    jQuery('table tr td#ten_kho_bac').text("Tên kho bạc tỉnh");
  }else{
    jQuery('table tr td#id_kho_bac').text("Mã kho bạc huyện");
    jQuery('table tr td#ten_kho_bac').text("Tên kho bạc huyện");
  }
  });
  
});

function traCuuKhoBac(){
  var ma_kb = jQuery('#ma_kb').val();
  var ten_kb = jQuery('#ten_kb').val();
  var ib_kb_tinh = jQuery('#id_kho_bac_tinh_1').val();
  if(ib_kb_tinh == ""){
   traCuuKhoBacTinh(ma_kb,ten_kb,ib_kb_tinh);
  }else{
   traCuuKhoBacHuyen(ma_kb,ten_kb,ib_kb_tinh);
  }
}

function traCuuKhoBacTinh(ma_kb,ten_kb,ib_kb_tinh){
 jQuery.ajax({
   type : "POST",
   url : "traCuuKhoBac.do",
   data : {"ma_kb" : ma_kb, "ten_kb" : ten_kb, "id_kb_tinh" : ib_kb_tinh},
   success : function(data, textstatus){
    if(data != null){
      var lstKhoBac = new Object();
      lstKhoBac = JSON.parse(data[0]);
      
      var tableHtml = ""; 
      if(lstKhoBac.size() > 0){ 
      jQuery('#listKhoBac tr').remove();
        for(var i = 0; i < lstKhoBac.size(); i++){
          tableHtml += '<tr class="row_kho_bac" ondblclick="selectRow(this);">';
          tableHtml += '<td width="20%" align="center" id="ma">'+lstKhoBac[i].ma+'<\/td>';
          tableHtml += '<td id="ten">'+lstKhoBac[i].ten+'<\/td>';
          tableHtml += '<\/tr>';
        }
        
        jQuery('#listKhoBac').append(tableHtml);
      }
    }
   }
 });
}
function traCuuKhoBacHuyen(ma_kb,ten_kb,ib_kb_tinh){
  jQuery.ajax({
   type : "POST",
   url : "traCuuKhoBac.do",
   data : {"ma_kb" : ma_kb, "ten_kb" : ten_kb, "id_kb_tinh" : ib_kb_tinh},
   success : function(data, textstatus){
    if(data != null){
      var lstKhoBac = new Object();
      lstKhoBac = JSON.parse(data[0]);
      if(lstKhoBac.size() > 0){
       var tableHtml = ""; 
       jQuery('#listKhoBac tr').remove();
        for(var i = 0; i < lstKhoBac.size(); i++){
          tableHtml += '<tr class="row_kho_bac" ondblclick="selectRow(this);">';
          tableHtml += '<td width="20%" id="ma" align="center">'+lstKhoBac[i].ma+'<\/td>';
          tableHtml += '<td id="ten">'+lstKhoBac[i].ten+'<\/td>';
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
 var ib_kb_tinh = jQuery('#id_kho_bac_tinh_1').val();
 if(ib_kb_tinh == ""){
   jQuery('#id_kho_bac_tinh').val(ma);
   getThongTinKB();
 }else{
   jQuery('#id_kho_bac_huyen').val(ma);
   getThongTinNganHang();
 }
 jQuery('#dialog-form-lov-dm').dialog("close");
}