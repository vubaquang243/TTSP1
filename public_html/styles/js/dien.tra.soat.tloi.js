   function resetForm(){
    jQuery("#noi_dung").removeAttr("disabled");
    jQuery("#ltt_id").attr({'class':'fieldText'});
    jQuery("#thong_tin_khac").removeAttr("disabled");
    jQuery("#noi_dung").focus();
    
    jQuery("#nhan").attr({disabled:''});
    jQuery("#duyet").hide();
    jQuery("#timKiem").attr({disabled:''});
    jQuery("#btn_xemLTT").hide();
  } 
   function resetButtonDTSTLoi(){
        jQuery("#btn_nhan").attr({disabled:''});
        jQuery("#btn_xemLTT").hide();
        jQuery("#btn_timKiem").attr({disabled:''});
        jQuery("#btn_thoat").attr({disabled:''});        
      }
  function hideAllButtonDTSTraLoi(){
        jQuery("#nhan").hide();
        jQuery("#duyet").hide();
        jQuery("#timKiem").hide();
        jQuery("#thoat").attr({disabled:''});
        jQuery("#btn_xemLTT").hide();
        jQuery("#btn_refresh").hide();
        jQuery("#btn_timKiem").hide();
      }
  function hideButtonDTSTLoi() {
    jQuery("#nhan").hide();
    jQuery("#duyet").hide();
    jQuery("#timKiem").hide();
    jQuery("#thoat").show();
    jQuery("#btn_xemLTT").show();
    
  }
   function resetFormDTSTLoi() {
        jQuery("#ltt_id").val("");
        jQuery("#ngay_thanh_toan").val("");
        jQuery("#so_dien_ts").val("");
        jQuery("#ten_don_vi_nhan_tra_soat").val("");
         jQuery("#ten_don_vi_tra_soat").val("");
        jQuery("#ma_don_vi_tra_soat").val("");
        jQuery("#ma_don_vi_nhan_tra_soat").val("");
        jQuery("#ttv_nhan").val("");
        jQuery("#ma_ttv_nhan").val("");
        jQuery("#ma_ktt").val("");
        jQuery("#ngay_duyet").val("");
        jQuery("#ngay_nhan").val("");
        jQuery("#noi_dung").attr({disabled:"disabled",value:""});
        jQuery("#thong_tin_khac").attr({disabled:"disabled",value:""});
        jQuery("#lydo_ktt_day_lai").attr({disabled:"disabled",value:""});
        jQuery("#ngay_ks_nh").val("");
        jQuery("#nguoi_ks_nh").val("");
        jQuery("#mo_ta_trang_thai").html("");
        jQuery("#trang_thai").val('');
        jQuery("#rowSelected").val('');
        jQuery("#ten_don_vi_tra_soat").html("");
        jQuery("#ten_don_vi_nhan_tra_soat").html("");
        jQuery("#nguoi_nhap_nh").val("");
        jQuery("#ngay_nhap_nh").val("");
        jQuery("#ngay_ks_nh").val("");
        jQuery("#nguoi_ks_nh").val("");
        jQuery("#noidung_traloi").val("");
      }
   //********************** fill form detail ***********************************
       function fillDataDTSTLoi(id, tr_id, chucdanh, action) {
           resetFormDTSTLoi();
           resetButtonDTSTLoi();
           jQuery.ajax({
             type: "POST",
             url: "checkstatebudtstloi.do",			  
             data: {"id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
              if(textstatus!=null && textstatus=='success'){
                if(data!=null){
                  jQuery("#id").val(data.id);
                  jQuery("#ltt_id").val(data.ltt_id);
                  jQuery("#so_dien_ts").val(data.dts_id);
                  jQuery("#ngay_thanh_toan").val(data.ngay_thanh_toan);
                  jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
                  jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                  jQuery("#ma_ktt").val(data.ma_ktt);
                  jQuery("#ngay_duyet").val(data.ngay_duyet);
                  jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
                  jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
                  jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);;
                  jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
                  jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);;
                  jQuery("#nhkb_nhan").val(data.nhkb_nhan);
                  jQuery("#ttv_nhan").val(data.ttv_nhan);
                  jQuery("#ngay_nhan").val(data.ngay_nhan);
                  jQuery("#noi_dung").val(data.noidung);
                  jQuery("#thong_tin_khac").val(data.thong_tin_khac);
                  jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
                  jQuery("#nguoi_nhap_nh").val(data.nguoi_nhap_nh);
                  jQuery("#ngay_nhap_nh").val(data.ngay_nhap_nh);
                  jQuery("#ngay_ks_nh").val(data.ngay_ks_nh);
                  jQuery("#nguoi_ks_nh").val(data.nguoi_ks_nh);
                  jQuery("#mo_ta_trang_thai").html(data.mo_ta_trang_thai);
                  jQuery("#rowSelected").val(tr_id);
                  jQuery("#trang_thai").val(data.trang_thai);
                  jQuery("#noidung_traloi").val(data.noidung_traloi);                  
                  jQuery("#noi_dung_ky").val(data.noi_dung_ky);
                  jQuery("#error_desc").val(data.error_desc);
                  roleUserTLoi(chucdanh,data.trang_thai,action);
                }
              }
            },
            error: function(textstatus){ 
              alert(textstatus.messge);
            }
              
          });
          //Change style
          var trs = document.getElementById('data-grid').getElementsByTagName('tr');
          for (var j = 0;j < trs.length;j++) {
              jQuery("#row_dts_" + j).attr( {'class' : 'ui-widget-content ui-row-ltr'});
              if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
                  jQuery("#" + tr_id).attr( {'class' : 'ui-row-ltr ui-state-highlight'});
                  jQuery("#" + tr_id).find('input').attr( {'class' : 'ui-state-highlight'});
              }
          }
      }
       function tableHighlightRowTLoi() {
          if (document.getElementById && document.createTextNode) {
              var tables = document.getElementsByTagName('table');
              for (var i = 0;i < tables.length;i++) {
                  if (tables[i].className == 'data-grid') {
                      var trs = tables[i].getElementsByTagName('tr');
                      for (var j = 0;j < trs.length;j++) {
                          if (trs[j].parentNode.nodeName == 'TBODY') {
                              trs[j].onmouseover = function () {
                                  if (this.className != 'ui-row-ltr ui-state-highlight')
                                      this.className = 'ui-row-ltr ui-state-hover';
                                  return false
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
       function  roleUserTLoi(chucdanh, trangthai,action){
        if(chucdanh!=null && chucdanh!='null'){
        var arrchucdanh=chucdanh.split("|")
      //  if (action =='VIEW_DETAIL') return;
         hideAllButtonDTSTraLoi();
        for (var i=0; i<arrchucdanh.length; i++)
        {
              if(arrchucdanh[i]=='TTV'){
               //span sua,ghi,huy,timkiem,thoat
               if (trangthai=='00')
               {
               jQuery("#duyet").hide();
               jQuery("#timKiem").hide();
               jQuery("#nhan").show();
               }
              }
               if(arrchucdanh[i]=='KTT'){
                if (trangthai=='02')
               {
               //duyet,daylai,timkiem,thoat 
               jQuery("#nhan").hide();
               jQuery("#duyet").show();
               jQuery("#timKiem").show();
               }
                if (trangthai=='03')
               {
               jQuery("#timKiem").show();
               }
               //jQuery("#xemLTT").hide();
              }
            }
        }
     }
    function findDTSTTLoi(contextRoot){
    var maTTV=jQuery("#ttv_nhan_tk").val(),
        nh_kb_nhan=jQuery("#nh_kb_nhan").val(),
        so_dts=jQuery("#so_dts").val(),
        so_lenh_tt=jQuery("#so_lenh_tt").val(),
        so_traloi=jQuery("#so_traloi").val();
       jQuery.ajax({
           type: "POST",
           url: "dtsoattloiView.do",			  
           data: {"ttv_nhan" : maTTV,
                  "nhkb_nhan" : nh_kb_nhan,
                  "dts_id":so_dts,
                  "id":so_traloi,
                  "ltt_id":so_lenh_tt,
                  "action":'FIND',
                  "nd" : Math.random() * 100000},
           dataType:'json',
           success: function(data,textstatus){
              if(textstatus!=null && textstatus=='success'){
                fillDataTableDSTTLoi(data,contextRoot);
              }
           },
            error:function(textstatus){
              alert(textstatus);
           }
       });
  }  
   function refreshGridTLoi(id,contextRoot){
      if(id=='null')id='';
       jQuery.ajax({
           type: "POST",
           url: "dtsoattloiView.do",			  
           data: {"action":'REFRESH',
                  "id":id,
                  "nd" : Math.random() * 100000},
           dataType:'json',
           success: function(data,textstatus){
               if(data.error!=undefined){
                  alert('CÛ l?i trong qu· trÏnh refresh');
                }else if(data.error==undefined){
                  fillDataTableDSTTLoi(data,contextRoot);
                }
                if(id!=null && id!=''){
                   jQuery("#row_dts_0").attr({'class':'ui-row-ltr ui-state-highlight'});
                }
           },
            error:function(textstatus){
              alert("Loi xay ra");
              alert(textstatus.responseText);
           }
       });
  }
  function selectedTopRowDTSTLoi(id, row_id,chucdanh, action) {
 
    // viet 1 ham select dau tien giong ham nay
    if (id == null || id == '' || id == 'undefined') {
        tableHighlightRowTLoi();
        resetFormDTSTLoi();
    }
    else {          
          fillDataDTSTLoi(id, row_id,chucdanh,action);
        
    }
}
   //fill data table
    function fillDataTableDSTTLoi(data,contextRoot){
        var strTableData="";
        var  id_default=0;
         jQuery("#data-grid").html('');
         jQuery.each(data, function(i, objectDTS) {
           strTableData+="<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_"+i+"\"ondblclick=\"rowSelectedFocus('row_dts_"+i+"');\" onclick=\"rowSelectedFocus('row_dts_"+i+"');fillDataDTSTLoi('"+objectDTS.id+"','row_dts_"+i+"');\">"+
              "<td width=\"44%;\" align=\"center\">";
              strTableData+="<input name=\"row_item\" id=\""+i+"\" type=\"text\" style=\"border:0px;font-size:10px;float:left;width:100%;\" value=\""+objectDTS.id+"\" onkeydown=\"arrowUpDown(event)\" readonly=\"true\"/></td><td align=\"center\" width=\"30%;\">";
                if(objectDTS.trang_thai=='00'){
                  strTableData+="<img src="+contextRoot+"/styles/images/edit.gif title=\"Ch&#7901; x&#7917; l&#253; \"/>";
                }else if(objectDTS.trang_thai=='02'){
                  strTableData+="<img src="+contextRoot+"/styles/images/wait.jpeg title=\"Ch&#7901; KTT duy&#7879;t\"/>";
                }else if(objectDTS.trang_thai=='03'){
                  strTableData+="<img src="+contextRoot+"/styles/images/active.gif title=\"&#272;&#227; duy&#7879;t\"/>";
                }
              strTableData+="</td></tr>";
                     if (i == 0)
          id_default = objectDTS.id;

        });
        jQuery("#data-grid").html('<tbody>'+strTableData+'</tbody>');
      tableHighlightRow();
      selectedTopRowDTSTLoi(id_default,"row_dts_0","KTT","fillDataForm");
    }
    function getTenNganHang_KB(ma,ten, id){
	 var shkb =document.getElementById(ma).value;
   v_ten = ten;   
   v_id = id;
	 if (shkb.length<8 || shkb.length>8){
		 document.getElementById(ten).value = "";
     document.getElementById(id).value = "";
	 }else{
	 var objJSON = {
        "ma" : document.getElementById(ma).value};    
    var strJSON = encodeURIComponent(JSON.stringify(objJSON));        
    new Ajax.Request("loadDVTSAction.do",
     {
       method: "post",
       async: false,
       parameters: "strJSON=" + strJSON,
       onSuccess: onSuccessNH,

       onError: onError,
       onLoading: onLoading
     });
	 }
}
//Callback function
   function onSuccessNH(transport, json){        
      if(json == null){      
       	document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
      }else if(json.executeError != null && json.executeError != 'null'){
        document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
        alert(convertToUTF8(json.executeError));
      }else if(json.ten != null && json.ten != 'null'){
        if(json.ten != '0'){          
          document.getElementById(v_ten).value = convertToUTF8(json.ten);
          document.getElementById(v_id).value = json.id;
        }else{
          document.getElementById(v_ten).value = "";
          document.getElementById(v_id).value = "";
          alert("M√£ NH/KB kh√¥ng t·ªìn t·∫°i.");
          
        }
      }      
   }
   function onLoading(){
      	document.getElementById('indicator').style.display = "";               
   }
   function onError(){
       alert("Error");
   } 
     
