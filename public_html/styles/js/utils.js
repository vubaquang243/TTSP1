//20171120 thuongdt bo sung canh bao thoi han sử dụng CTS
function checkCTSdate(cert,user_name,checkcts){  
    var serial = cert.Serial;
    if(checkcts == ''){
     popupCTS2(serial,user_name);  
     }
     return true;
  } 
  
  function popupCTS2(Serial,user_name){ 
             jQuery.ajax({
             type: "POST",
             url: "viewdetailcts.do",	
             data: {"serial" : Serial,
                    "user_name":user_name,
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='success'){
                   if(data!=null){
                    if(data.ERROR ==undefined || data.ERROR==''){
                      
                       try { 
                          var ngay_temp =  data.validTo.split('/');                          
                           var vnew_date = new Date(); 
                           var ngayhhan  = new Date(ngay_temp[2],''+(Number(ngay_temp[1])-1),ngay_temp[0]);;
                            var so_ngay = Math.round((ngayhhan - vnew_date)/(86400*1000));
                            if(so_ngay <0 ){
                              alert('Chứng thư số đã hết hạn.');
                            }else if(so_ngay <=60) {
                              alert('Chứng thư số chỉ còn '+so_ngay+' ngày hết hiệu lực.');              
                            }           
                        }
                        catch (e) {
                            alert(e.description);
                           
                        }
                    }
                 }
                } 
              },
              error:function(textstatus){
                jQuery("#dialog_message").html(textstatus);
                jQuery("#dialog_message").dialog("open");
             }
            });
           }
  
  
// 20171128 taidd bo xung function dinh dang tien va kiem tra khi nhap.
function CurrencyFormatted2(value, nt_id, allowNegativeNumber){
      if(value == null){
        return "";
      }
      value += "";
      var result = "";
      var j = 0;
      var isNegativeNumber;
      // Bỏ . , và lấy 2 chữ số sau dấu ,
      value = value.trim().replace(/\./g,""); // Bỏ .
      if(value.search(",") > 0){
        if(nt_id == "VND" ){
          value = value.substring(0, value.search(","));
        }else{
          if(value.length > value.search(",") + 3){
            value = value.substring(0, value.search(",") + 3);
            j = 3;
          }else{
            j = value.length - value.search(",");
          }
        }
      }
      
      result = value.substring(value.length - j,value.length).replace(/\,/g,"").replace(/\-/g,""); // Lấy trước phần sau dấu ,
      value = value.substring(0,value.length - j);
      
        
      // Bỏ số 0 ở đâu và kiểm tra số dương hay âm.
        if(value.search("-") >= 0){
          isNegativeNumber = true;
          value = value.replace(/\-/g,"");
          value = value * 1 + "";
        }else{
          isNegativeNumber = false;
          value = value * 1 + "";
        }
      
      if(isNaN(value)){
          return "";
        }
      if(j > 1){
        result = "," + result; // Thêm dấu ,
      }
      // Định dạng lại tiền.
      for(var i = value.length - 1; i >= 0; i--){
        if((value.length - i) % 3 == 1 && i != value.length-1){
            result = "." + result;
        }
        result = value[i] + result;
      }
      if(isNegativeNumber && result !== "0"){
        result = "-" + result;
      }
      if(allowNegativeNumber == undefined || !allowNegativeNumber ){
          result = result.replace(/\-/g,"");
      }
        
        return result;
  }
  

  
  function numbersonly2(event,isPositiveNumber){
    var key = event.keyCode;
     if(key == 45){
       if(isPositiveNumber != undefined){
          return !isPositiveNumber;
        }else{
          return false;
        }
     }
     
     
     if(key != 46 && key != 44 && key != 45){
        if(key < 48 || key > 57){
          return false;
        }
     }
     
     return true;
  }
  
  /*
   * 20171204 thuongdt bo sung de kiem tra tu ngay, den ngay tu js
   * param: tu_ngay_name ten id tu ngay, den_ngay_name ten id den ngay
  */
  function check_tuNgay_denNgay(tu_ngay_name,den_ngay_name){
    var vTuNgay=document.getElementById(tu_ngay_name);
    var vDenNgay=document.getElementById(den_ngay_name);  
    if( ''==vTuNgay.value ||vTuNgay.value ==null){
        alert(GetUnicode('Từ ngày không để trống.'));
        vTuNgay.focus();
        return false;
      } else if(''==vDenNgay.value ||vDenNgay.value ==null){
        alert(GetUnicode('Đến ngày không để trống.'));
        vDenNgay.focus();
        return false;
      }else {      
        var vtu_ngay = vTuNgay.value.split('/');
        var vden_ngay = vDenNgay.value.split('/');
      
      var tu_ngayTemp = new Date(vtu_ngay[2],''+(Number(vtu_ngay[1])-1),vtu_ngay[0]);
       var den_ngayTemp = new Date(vden_ngay[2],''+(Number(vden_ngay[1])-1),vden_ngay[0]);
      var dateNow = new Date();
      var songay =  Math.round((den_ngayTemp - tu_ngayTemp)/(86400*1000));
      
      if(songay <0){
        alert('Từ ngày nhỏ hơn hoặc bằng đến ngày.')
        vTuNgay.focus();
        return false;
      }
      else if(songay > 30){
        alert('Phạm vi tra cứu không quá 30 ngày.')
        vDenNgay.focus();
        return false;
      }
      else if(den_ngayTemp > dateNow){
        alert('Đến ngày nhỏ hơn hoặc bằng ngày hiện tại.')
        vDenNgay.focus();
        return false;      
      }
    }  
  return true;
  }