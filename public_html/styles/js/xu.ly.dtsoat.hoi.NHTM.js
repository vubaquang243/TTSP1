     //************************************ RESET FROM **********************************
      function resetFormDTS() {
        jQuery("#id").val("");
        jQuery("#ltt_id").val("");
        jQuery("#ngay_thanh_toan").val("");
        jQuery("#ma_don_vi_tra_soat").val("");
        jQuery("#ma_don_vi_nhan_tra_soat").val("");
        jQuery("#ttv_nhan").val("");
        jQuery("#ma_ttv_nhan").val("");
        jQuery("#ma_ktt").val("");
        jQuery("#ma_nsd").val("");
        jQuery("#ngay_nhan").val("");
        jQuery("#noi_dung").attr({"readonly":true,value:""});
        jQuery("#thong_tin_khac").attr({"readonly":true,value:""});
        jQuery("#lydo_ktt_day_lai").attr({"readonly":true,value:""});
        jQuery("#ngay_ks_nh").val("");
        jQuery("#nguoi_ks_nh").val("");
        jQuery("#ngay_nhap_nh").val("");
        jQuery("#nguoi_nhap_nh").val("");
        jQuery("#mo_ta_trang_thai").html("");
        jQuery("#trang_thai").val('');
        jQuery("#rowSelected").val('');
        jQuery("#ten_don_vi_tra_soat").val("");
        jQuery("#ten_don_vi_nhan_tra_soat").val("");
         jQuery("#ngay_duyet").val("");
      }
  //********************** fill form detail ***********************************
      function fillDataDTS(id, tr_id,chuc_danh) {
           resetFormDTS();
           disableButton();
           jQuery.ajax({
             type: "POST",
             url: "filldatadtsoatdetail.do",			  
             data: {"id" : id, "action" : 'fillDataForm', "nd" : Math.random() * 100000},
             dataType:'json',
             async:false,
             success: function(data,textstatus){
             if(textstatus!=null && textstatus=='success'){
              if(data!=null){
               if(data.error!=undefined){
                   jQuery("#dialog-message" ).html(data.error);
                   jQuery("#dialog-message" ).dialog( "open" );
                }else if(data.error==undefined){
                  enableButtonDTS(chuc_danh,data.trang_thai,data.id);
                  fillDetailToFormDTS(data,tr_id);
                  if(chuc_danh.indexOf('KTT')>=0 && data.trang_thai=='12'){
                      jQuery("#lydo_ktt_day_lai").attr({disabled:"",readonly:false}).focus();
                  }
                }
              }
            }
            },
            error: function(textstatus){ 
                jQuery("#dialog-message" ).html(textstatus.message);
                jQuery("#dialog-message" ).dialog( "open" );
            }
              
          });
          //Change style
          var trs = document.getElementById('data-grid').getElementsByTagName('tr');
          for (var j = 0;j < trs.length;j++) {
              jQuery("#row_dts_" + j).attr( {'class' : 'ui-widget-content ui-row-ltr'});
              if (jQuery("#" + tr_id).attr('class') == 'ui-widget-content ui-row-ltr') {
                  jQuery("#" + tr_id).attr( {'class' : 'ui-row-ltr ui-state-highlight'});
              }
          }
      }
      
    function fillDetailToFormDTS(data,tr_id_selected){
      jQuery("#ngay_nhap_nh").val(data.ngay_nhap_nh);
      jQuery("#nguoi_nhap_nh").val(data.nguoi_nhap_nh);
      jQuery("#ngay_ks_nh").val(data.ngay_ks_nh);
      jQuery("#nguoi_ks_nh").val(data.nguoi_ks_nh);
      jQuery("#trang_thai").val(data.trang_thai);
      jQuery("#id").val(data.id);
      jQuery("#ltt_id").val(data.ltt_id);
      jQuery("#ngay_thanh_toan").val(data.ngay_thanh_toan);
      jQuery("#nhkb_chuyen").val(data.nhkb_chuyen);
      jQuery("#nhkb_nhan").val(data.nhkb_nhan);
      jQuery("#ma_ktt").val(data.ma_ktt);
      jQuery("#ngay_duyet").val(data.ngay_duyet);
      jQuery("#ttv_nhan").val(data.ttv_nhan);
      jQuery("#ngay_nhan").val(data.ngay_nhan);
      jQuery("#ma_ttv_nhan").val(data.ma_ttv_nhan);
      jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_nhan_tra_soat);
      jQuery("#ten_don_vi_tra_soat").val(data.ten_don_vi_tra_soat);
      jQuery("#ma_don_vi_nhan_tra_soat").val(data.ma_don_vi_nhan_tra_soat);
      jQuery("#ma_don_vi_tra_soat").val(data.ma_don_vi_tra_soat);
      jQuery("#noi_dung").val(data.noidung);
      jQuery("#thong_tin_khac").val(data.thong_tin_khac);
      jQuery("#lydo_ktt_day_lai").val(data.lydo_ktt_day_lai);
      jQuery("#mo_ta_trang_thai").html(data.mo_ta_trang_thai);
      jQuery("#loai_dts").val(data.loai_dts);
      jQuery("#rowSelected").val(tr_id_selected);
      jQuery("#id").val(data.id);
      jQuery("#so_tchieu").val(data.so_tchieu);
     
    }  
      
     //********************** CLICK NUT btn_ghi ***********************************  
    function  btnghiClick(){
           jQuery("#noi_dung").removeAttr("disabled");
           jQuery("#noi_dung").focus();
           jQuery("#btn_sua").hide();
           jQuery("#btn_ghi").show();
           jQuery("#btn_duyet").hide();
           jQuery("#btn_dayLai").hide();
           jQuery("#btn_huy").hide();
           jQuery("#btn_xemLTT").hide();
           jQuery("#btn_timKiem").hide();
         
    }
   function  btndayLaiClick(){
       jQuery("#lydo_ktt_day_lai").removeAttr("readonly");
       jQuery("#lydo_ktt_day_lai").focus();
       jQuery("#btn_ghi").show();
       jQuery("#btn_dayLai").hide();
       jQuery("#btn_xacNhan").hide();
         
    }
  //********************** ROLE USER SHOW HIDE BUTTON*********************************** 
  /**
   * @param chucdanh
   */
     function  disableButton(){
        jQuery("#btn_dayLai").hide();
        jQuery("#btn_ghi").hide();
        jQuery("#btn_xacNhan").hide();
        jQuery("#btn_traLoi").hide();
    }
   //********************** ROLE USER SHOW HIDE BUTTON*********************************** 
  /**
   * @param chucdanh
   */
   function enableButtonDTS(chucdanh,trang_thai,id){   
      jQuery("#btn_ghi").hide();
      if(chucdanh.indexOf('TTV')>=0 && chucdanh.indexOf('KTT')==-1){
       //span sua,btn_ghi,huy,timkiem,thoat
       if(id.substring(5,8)!='199')
            jQuery("#btn_traLoi").show();
       if(trang_thai=='10' || trang_thai=='11'){
         jQuery("#btn_dayLai").hide();
         jQuery("#btn_xacNhan").show();
        }
      }else if(chucdanh.indexOf('KTT')>=0 && chucdanh.indexOf('TTV')==-1){
         if(trang_thai=='12'){
         //duyet,btn_dayLai,timkiem,thoat 
         jQuery("#btn_traLoi").hide();
         jQuery("#btn_xacNhan").show();
         jQuery("#btn_dayLai").show();
        }
      }else if(chucdanh.indexOf('KTT')>=0 && chucdanh.indexOf('TTV')>=0){          
         if(trang_thai=='12'){
           //duyet,btn_dayLai,timkiem,thoat 
           jQuery("#btn_traLoi").hide();
           if(id.substring(5,8)=='199')
            jQuery("#btn_xacNhan").show();
           jQuery("#btn_dayLai").show();
         }else if(trang_thai=='10' || trang_thai=='11'){
           jQuery("#btn_dayLai").hide();
           if(id.substring(5,8)=='199')
            jQuery("#btn_xacNhan").show();
         }
         if(id.substring(5,8)!='199' && trang_thai != '13')
          jQuery("#btn_traLoi").show();
      }else{
        jQuery("#btn_dayLai").hide();
        jQuery("#btn_ghi").hide();
        jQuery("#btn_xacNhan").hide();
        jQuery("#btn_traLoi").hide();
      }
       
     }
    //fill data table
    function fillDataTableDST(data,contextRoot,chucdanh){
        var strTableData="";
         jQuery("#data-grid").html('');
         if(data!=null && data!=''){
           jQuery.each(data, function(i, objectDTS) {
                  strTableData+="<tr class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_dts_"+i+"\" ondblclick=\"rowSelectedFocus('row_dts_"+i+"');\" onclick=\"rowSelectedFocus('row_dts_"+i+"');fillDataDTS('"+objectDTS.id+"','row_dts_"+i+"','"+chucdanh+"');\">";
                  strTableData+="<td id=\"td_dts_"+i+"\" width=\"44%;\" align=\"center\">";
                  strTableData+="<input name=\"row_item\" id=\""+i+"\" type=\"text\" style=\"border:0px;font-size:10px;float:left;width:100%;\" value=\""+objectDTS.id+"\" onkeydown=\"arrowUpDown(event)\" readonly=\"true\"/></td><td align=\"center\" width=\"30%;\">";
                  if(objectDTS.trang_thai=='10'){
                    strTableData+="<img src="+contextRoot+"/styles/images/return.jpeg title=\"KTT &#273;&#7849;y l&#7841;i \"/>";
                  }else if(objectDTS.trang_thai=='11'){
                    strTableData+="<img src="+contextRoot+"/styles/images/wait.jpeg title=\"Ch&#7901; x&#7917; l&#253;\"/>";
                  }else if(objectDTS.trang_thai=='12'){
                    strTableData+="<img src="+contextRoot+"/styles/images/active.gif title=\"&#272;&#227; x&#7917; l&#253;\"/>";
                  }else if(objectDTS.trang_thai=='13'){
                    strTableData+="<img src="+contextRoot+"/styles/images/return.jpeg title=\"&#272;&#227; tr&#7843; l&#7901;i\"/>";
                  }
                  else if(objectDTS.trang_thai=='14'){
                    strTableData+="<img src="+contextRoot+"/styles/images/wait.jpeg title=\"Ch&#7901; KTT duy&#7879;t\"/>";
                  }
                  else if(objectDTS.trang_thai=='15'){
                    strTableData+="<img src="+contextRoot+"/styles/images/active.gif title=\"&#272;&#227; g&#7917;i\"/>";
                  }
                  else if(objectDTS.trang_thai=='16'){
                    strTableData+="<img src="+contextRoot+"/styles/images/active.gif title=\"&#272;&#227; x&#225;c nh&#7853;n\"/>";
                  }
                  else if(objectDTS.trang_thai=='17'){
                    strTableData+="<img src="+contextRoot+"/styles/images/delete1.gif title=\"&#272;&#227; h&#7911;y\"/>";
                  }else if(objectDTS.trang_thai=='18'){
                  strTableData+="<img src="+contextRoot+"/styles/images/send-success.jpg title=\"G&#7917;i NH th&#224;nh c&#244;ng\"/>";
                  }
                  else if(objectDTS.trang_thai=='19'){
                    strTableData+="<img src="+contextRoot+"/styles/images/sended-false.jpg title=\"G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng\"/>";
                  }
                strTableData+="</td></tr>";
          });
         }else{
          strTableData+="<tr>";
          strTableData+="<td colspan=\"5\" align=\"center\">";
          strTableData+="<span style=\"color:red;\">Kh&#244;ng c&#243; b&#7843;n ghi</span>";
          strTableData+=" </td>";
          strTableData+="</tr>";
        }
        jQuery("#tong_ban_ghi").html('T&#7893;ng s&#7889;: '+data.length+' &#273;i&#7879;n tra so&#225;t');
        jQuery("#data-grid").html('<tbody>'+strTableData+'</tbody>');
        tableHighlightRow();
         
    }
  /**
   * CHON BAN btn_ghi BEN SO DIEN TRA SOAT
   */
  function selectedRowBeforeClickButton(){
     var rSelected=jQuery("#rowSelected").val();
      if(rSelected==null || rSelected==''){
        jQuery("#dialog-message" ).html('B&#7841;n ph&#7843;i ch&#7885;n m&#7897;t S&#7889; &#273;i&#7879;n tra so&#225;t trong danh s&#225;ch s&#7889; &#273;i&#7879;n tra so&#225;t.');
        jQuery("#dialog-message" ).dialog( "open" );
        return false;
      }
      return true;
  }

  
  /**
   * Refresh danh sach so dien tra soat
   */
  function refreshGrid(id,contextRoot,chucdanh){
      if(id=='null')id='';
       jQuery.ajax({
           type: "POST",
           url: "xulydtshoitunhtmView.do",			  
           data: {"action":'REFRESH',
                  "id":id,
                  "nd" : Math.random() * 100000},
           dataType:'json',
           success: function(data,textstatus){
               if(data.error!=undefined){
                  jQuery("#dialog-message" ).html(data.error);
                   jQuery("#dialog-message" ).dialog( "open" );
                }else if(data.error==undefined){
                  fillDataTableDST(data,contextRoot,chucdanh);
                  defaultRowSelected(chucdanh);
                }
                if(id!=null && id!=''){
                   jQuery("#row_dts_0").attr({'class':'ui-row-ltr ui-state-highlight'});
                }
           },
            error:function(textstatus){
              jQuery("#dialog-message" ).html(textstatus.responseText);
              jQuery("#dialog-message" ).dialog( "open" );
           }
       });
       resetFormDTS();
       
  }
 //********************** Tim kiem dien tra soat***********************************   
  function findDTSTraLoi(contextRoot,chucdanh){
    var dialogId=jQuery("#dialog-form ~ .ui-dialog-buttonpane button"),
    maTTV=jQuery("#ma_ttvnhan").val(),
    nh_bk_nhan=jQuery("#nh_kb_nhan").val(),
    so_dts=jQuery("#so_dts").val(),
    so_lenh_tt=jQuery("#so_lenh_tt").val(),
    id=jQuery("#id").val();
    jQuery(dialogId[0]).button("disable");
       jQuery.ajax({
           type: "POST",
           url: "xulydtshoitunhtmView.do",			  
           data: {"ma_ttv_nhan" : maTTV,
                  "ma_don_vi_nhan_tra_soat" : nh_bk_nhan,
                  "id":so_dts,
                  "ltt_id":so_lenh_tt,
                  "action":'FIND',
                  "nd" : Math.random() * 100000},
           dataType:'json',
           success: function(data,textstatus){
            if(data!=null ){
              if(textstatus!=null && textstatus=='success'){
                if(data.ERROR!=undefined){
                  jQuery("#dialog-message" ).html(data.ERROR);
                  jQuery("#dialog-message" ).dialog( "open" );
                }else if(data.ERROR==undefined){
                  fillDataTableDST(data,contextRoot,chucdanh,id);
                  if(data!=null && data!=''){
                    defaultRowSelected(chucdanh);
                  }else{
                    resetFormDTS();
                  }
                }
              }
            }
            jQuery(dialogId[0]).button("enable");
           
           },
            error:function(textstatus){
              jQuery("#dialog-message" ).html(textstatus.responseText);
              jQuery("#dialog-message" ).dialog( "open" );
           }
       });

       
  }