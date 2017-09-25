 var req;
 var out = new Object();

 var gl_TG = new Array();
 var temp = new Array();
 var temp_LThue = new Array();
 var temp_NTe = new Array();
 var detailNNT = new Array();
 var temp_TKNganSach = new Array();
 var temp_DBHC = new Array();
 var gl_DBHC = new Array();
 var temp_CQTHU = new Array();
 var gl_CQTHU = new Array(); 
 var gl_NDKT = new Array();
 var temp_NDKT = new Array();
 var gl_TK_NH_KB = new Array();
 var temp_TK_NH_KB = new Array();
 var gl_CQTHU_TK = new Array(); 
 var gl_Ten_Tu_MaNT = new Array();  
 var gl_VT_LHXNK = new Array();
 var gl_TKObject = new Array();
 var errors=new Array();
 var listMaNganHang = new Array();
 var listMaDvsdns = new Array();
 var listLhxnk = new Array();
var arr_chuong = new Array();
var arr_tlpc = new Array();
var arr_ndkt = new Array();
var listToKhai = new Array();


/*
 * Ham chan click chuot phai
 */
 
 function autoSelect(selectTarget) {
  	if(selectTarget != null && ((selectTarget.childNodes.length == 1
       && selectTarget.childNodes[0].nodeName == "#text") || (selectTarget.tagName == "INPUT"
       && selectTarget.type == "text"))) {
   		if(selectTarget.tagName == 'TEXTAREA' || (selectTarget.tagName == "INPUT" && selectTarget.type == "text")) {
   			 selectTarget.select();
   		} else if(window.getSelection) { // FF, Safari, Opera
    			var sel = window.getSelection();
    			var range = document.createRange();
    			range.selectNode(selectTarget.firstChild);
    			sel.removeAllRanges();
    			sel.addRange(range);
   		} else { // IE
    			document.selection.empty();
    			var range = document.body.createTextRange();
    			range.moveToElementText(selectTarget);
    			range.select();
   		}
  	}
 }
 function CounterPress(obj, event) {
		
	 var objValue = obj.value;
	 
	 if(objValue.length == '11'){
		 if(event.keyCode == 8){
			return; 
		 }
		 var first = objValue.substring(0, 10);
		 var last = objValue.substring(10);					 
		 document.forms[0].ma_thue.value = first + '-' + last;
	 }	
	 return;
	 
	}	
 function CheckLengthMST() {
	 var MaST = document.forms[0].ma_thue.value;	 
	 var lengthMST = MaST.length;	 
	 if(lengthMST == 14) {
		 if(MaST.indexOf('--') > 0) {
			 alert("Mã số thuế bạn nhập không hợp lệ");
			 document.forms[0].ma_thue.focus();
			 return;
		 }
	 }
 }
function right(e) {
	if (navigator.appName == 'Netscape' &&(e.which == 3 || e.which == 2))
		return false;
	else if (navigator.appName == 'Microsoft Internet Explorer' &&(event.button == 2 || event.button == 3)) 
	{
		alert('Bạn không được phép click chuột phải !');
		return false;
	}
	return true;
}
function sbRefresh(){ 
	
 	   document.forms[0].__action.value="refresh";
       document.forms[0].submit();
}  

function LoadListLapChungTu(){	            	
	document.forms[0].__action.value="search_auto";
    document.forms[0].submit();			
}
function Errors(field_focus,message){
    this.field_focus=field_focus;
    this.message=message;
}   
function checkConstrain(){
    if (errors.length>0){
        for (i=0;i<errors.length;++i){
            var focus1_=errors[i].field_focus;
            alert(errors[i].message);
            document.getElementById(focus1_).focus(); 
            return false;
        }
    }
    return true;
}    
function removeError(field){
    if (errors.length>0){
        for (i=0;i<errors.length;++i){
            if (errors[i]!=null){
                var focus_=errors[i].field_focus;
                if (focus_==field){
                    errors.splice(i,1);
                }
            }
        }
    }
}   
 
function sbCheckLoaiThue(loai_thue, so_bke, ngay_bke, tkhai_so, ngay_dk, lh_xnk, ma_thue, ten_thue, dc_nnthue, dc_nnthue_huyen, dc_nnthue_tinh,
		nguoi_ntien, dc_nntien, tkhai_so, so_qd, so_bke, so_khung, so_may, so_ct_nh,ma_nh_a , dien_giai,ma_nh_nntien ,ngay_nntien) 
{
	var kieu_ctu = document.forms[0].kieu_ctu.value;	
	var ma_dthu = document.forms[0].ma_dthu.value;
	var a = false;        	
	for(ilt = 0; ilt < temp_LThue.length; ilt++)
	{
		if(a == false) {
			var ma_lthue = temp_LThue[ilt].split("-");         			
			if(trim(ma_lthue[0]) == trim(loai_thue)) {
    			a = true;
    			break;
    		}
			else {
				a = false;
			}
		}
	}
	if(a == false){
		alert("Mã loại thuế không tồn tại");
		document.forms[0].loai_thue.focus();
		return false;
	}
	if (isNull(ma_thue)){    	
        alert(tcs_nhap_ct_ma_thue_required);
        document.forms[0].ma_thue.focus();
        return false;
    }else {
    	var lenOfMaThue = Utf8.encode(trim(ma_thue)).length;
    	if(lenOfMaThue > 15) {
    		alert('Mã thuế bạn nhập vượt quá độ dài cho phép');
			document.forms[0].ma_thue.focus();
			return false;
    	}
    }         
   
    if (isNull(ten_thue)){
        alert(tcs_nhap_ct_ten_thue_required);
        document.forms[0].ten_thue.focus();
        return false;
    } 
    else
	{    	
    	var lenOfTenThue = Utf8.encode(trim(ten_thue)).length;    	
    	
    	if(lenOfTenThue > 200) 
    		{
    			alert('Tên ngư�?i nộp thuế bạn nhập vượt quá độ dài cho phép');
    			document.forms[0].ten_thue.focus();
    			return false;
    		}
	}   
    if(isNull(dc_nnthue)) {
    	
    }
    else {
    	var lenOfDcNNT = Utf8.encode(trim(dc_nnthue)).length;
    	if(lenOfDcNNT > 200) 
		{
			alert('�?ịa chỉ ngư�?i nộp thuế bạn nhập vượt quá độ dài cho phép');
			document.forms[0].dc_nnthue.focus();
			return false;
		}
    }   
    if(isNull(dc_nnthue_huyen)) {
    	
    }
    else {
    	var lenOfDcNntHuyen = Utf8.encode(trim(dc_nnthue_huyen)).length;
    	if(lenOfDcNntHuyen > 200) 
		{
			alert('�?ịa chỉ ngư�?i nộp thuế huyện bạn nhập vượt quá độ dài cho phép');
			document.forms[0].dc_nnthue_huyen.focus();
			return false;
		}
    }
    
    if(isNull(dc_nnthue_tinh)) {
    	
    }
    else {
    	var lenOfDcNntTinh = Utf8.encode(trim(dc_nnthue_tinh)).length;
    	if(lenOfDcNntTinh > 100) 
		{
			alert('�?ịa chỉ ngư�?i nộp thuế tỉnh bạn nhập vượt quá độ dài cho phép');
			document.forms[0].dc_nnthue_tinh.focus();
			return false;
		}
    }
    if(isNull(nguoi_ntien)) {
    	
    }
    else {
    	var lenOfDcNNTien = Utf8.encode(trim(nguoi_ntien)).length;
    	if(lenOfDcNNTien > 100) 
		{
			alert('Tên ngư�?i nộp ti�?n bạn nhập vượt quá độ dài cho phép');
			document.forms[0].nguoi_ntien.focus();
			return false;
		}
    	
    }
    if(isNull(dc_nntien)) {
    	
    	
    }
    else {
    	var lenOfDcNnTien = Utf8.encode(trim(dc_nntien)).length;
    	if(lenOfDcNnTien > 200) 
		{
			alert('�?ịa chỉ ngư�?i nộp ti�?n bạn nhập vượt quá độ dài cho phép');
			document.forms[0].dc_nntien.focus();
			return false;
		}
    }    
    if(isNull(ngay_nntien)) {
    	if(ma_dthu == '01') {
		   alert('Bạn phải nhập Ngày ngư�?i nộp thuế');
		   document.forms[0].ngay_nntien.focus();
		   return false;
    	}
	}else{
		 if (!(CheckDateCall("id_ngay_nntien"))){
	           return false;
	        }	        
	}
    if(isNull(tkhai_so)) {
    	
    	
    }
    else {
    	var lenOfTkhaiSo = Utf8.encode(trim(tkhai_so)).length;
    	if(lenOfTkhaiSo > 20) 
		{
			alert('Số t�? khai bạn nhập vượt quá độ dài cho phép');
			document.forms[0].tkhai_so.focus();
			return false;
		}
    }
    if(isNull(so_qd)) {
    	
    	
    }
    else {
    	var lenOfSoQD = Utf8.encode(trim(so_qd)).length;
    	if(lenOfSoQD > 20) 
		{
			alert('Số quyết định bạn nhập vượt quá độ dài cho phép');
			document.forms[0].so_qd.focus();
			return false;
		}
    }
   if(isNull(so_khung)) {
    	
    	
    }
    else {
    	var lenOfSoKhung = Utf8.encode(trim(so_khung)).length;
    	if(lenOfSoKhung > 20) 
		{
			alert('Số khung bạn nhập vượt quá độ dài cho phép');
			document.forms[0].so_khung.focus();
			return false;
		}
    }
   if(isNull(so_may)) {
   	
   	
   }
   else {
   	var lenOfSoMay = Utf8.encode(trim(so_may)).length;
   	if(lenOfSoMay > 20) 
		{
			alert('Số máy bạn nhập vượt quá độ dài cho phép');
			document.forms[0].so_may.focus();
			return false;
		}
   }
   /**
	 * 
	 */
  
   if(isNull(so_ct_nh)){
	   
   }else{
	   var lengOfSoCtuNH = Utf8.encode(trim(so_ct_nh)).length;
	   if(lengOfSoCtuNH > 23)
		   {
			   	alert('Số chứng từ ngân hàng bạn nhập vượt quá độ dài cho phép');
				document.forms[0].so_ct_nh.focus();
				return false;
		   }
   } 
   if(kieu_ctu == 2 || kieu_ctu == 5 || kieu_ctu == 6)
   {
	   if(isNull(ma_nh_nntien)) {	   
	  
		   {
		   		
		   }
	   }
	   else
	   {
		    var bNganhang = false;
			for(inh=0;inh<listMaNganHang.length;inh++)
			{		
				var ma_nh = listMaNganHang[inh].split("-"); 
				if(trim(ma_nh[0]) == trim(ma_nh_nntien)){
					bNganhang = true;
					break;   		
				}
			}
			if(bNganhang == false){
				alert("Mã Ngân hàng không tồn tại");
				document.forms[0].ma_nh_nntien.focus();
				return false;
			}
	   }
   } 
   
   if(kieu_ctu == 2 || kieu_ctu == 5 || kieu_ctu == 6)
   {  	  
	   if(isNull(ma_nh_a)) {
		    alert("Bạn phải nhập mã ngân hàng kho bạc");
	   		document.forms[0].ma_nh_a.focus();
	   		return false;
	   }else
	   {
		    var bNganhang = false;
			for(inh=0;inh<listMaNganHang.length;inh++)
			{		
				var ma_nh = listMaNganHang[inh].split("-"); 
				if(trim(ma_nh[0]) == trim(ma_nh_a)){
					bNganhang = true;
					break;   		
				}
			}
			if(bNganhang == false){
				alert("Mã Ngân hàng không tồn tại");
				document.forms[0].ma_nh_a.focus();
				return false;
			}
	   }
   }
   
   if(isNull(so_bke)) {
	   	
	   	
   }
   else {
   	var lenOfSoBKe = Utf8.encode(trim(so_bke)).length;
   	if(lenOfSoBKe > 20) 
		{
			alert('Số bảng kê bạn nhập vượt quá độ dài cho phép');
			document.forms[0].so_bke.focus();
			return false;
		}
   }
   
   if (!(CheckDateCall("id_ngay_dk"))){
       return false;
    }
   if (!(CheckDateCall("id_ngay_bke"))){
       return false;
    }	
   if (!(CheckDateCall("id_ngay_qd"))){
       return false;
    }	
   if(isNull(dien_giai)) {
	   	
	   	
   }
   else {
   	var lenOfDienGiai = Utf8.encode(trim(dien_giai)).length;
   	if(lenOfDienGiai > 200) 
		{
			alert('Diễn giải bạn nhập vượt quá độ dài cho phép');
			document.forms[0].dien_giai.focus();
			return false;
		}
   }
    
	if(trim(loai_thue) != '04'){         		   	
    	document.forms[0].tkhai_so.value='';
    	document.forms[0].ngay_dk.value='';
    	document.forms[0].lh_xnk.value='';
    	
    	if(trim(so_bke) != null && trim(so_bke).length > 0){
    		if(isNull(trim(ngay_bke))){
    			alert("Bạn phải nhập ngày bảng kê");
            	document.forms[0].ngay_bke.focus();
                return false;
    		}else{
    			   
    		}
    	}
    }
	if(trim(loai_thue) == '04') {
    	if(trim(tkhai_so) != null && trim(tkhai_so).length > 0)
    	{
    		if(isNull(trim(ngay_dk))) {
    			alert("Bạn phải nhập ngày đăng ký");
	        	document.forms[0].ngay_dk.focus();
	            return false;
    		}else{
    			if (!(CheckDateCall("id_ngay_dk"))){
    		           return false;
    		        }
    		}
    		if(isNull(trim(lh_xnk))) {
    			alert("Bạn phải nhập loại hình xuất khẩu");
	        	document.forms[0].lh_xnk.focus();
	            return false;
    		}else {    			
    			/**
				 * Check loai hinh xnk
				 */
    		    if(isNull(trim(lh_xnk))) {
    		    	alert("Bạn phải nhập mã nguyên tệ");
    		    	document.forms[0].lh_xnk.focus();
    		    	return false;
    		    }else{
    		    	/**
					 * Chuyen check lhxnk len tang client
					 */    		    	
    		    	var a = false;
    		    	var ma_lh_cache = '';
    		    	for(ilh = 0; ilh < listLhxnk.length; ilh++)
    		    	{
    		    		if(a == false) {
    		    			var lhxnk = listLhxnk[ilh].split("-");     		    			
    		    			if(lhxnk.length == 4) {
    		    				ma_lh_cache = lhxnk[0] + '-' + lhxnk[1];
    		    				
    		    			}
    		    			else
		    				{
    		    				ma_lh_cache = lhxnk[0];
		    				}
    		    			if(trim(ma_lh_cache) == trim(lh_xnk)){
    		        			a = true;
    		        			break;
    		        		}
    		    			else{
    		    				a = false;
    		    			}
    		    		}
    		    	}
    		    	if(a == false){
    		    		alert("Mã loại hình xuất nhập khẩu không tồn tại");
    		    		document.forms[0].lh_xnk.focus();
    		    		return false;
    		    	}
    		    }
    		}
    		if(!isNull(so_bke)) {
    			alert("Bạn không được phép nhập số bảng kê khi đã nhập số t�? khai");
	        	document.forms[0].so_bke.focus();
	        	return false;
    		}
    		if(!isNull(ngay_bke)) {
    			alert("Bạn không được phép nhập ngày bảng kê khi đã nhập số t�? khai");
	        	document.forms[0].ngay_bke.focus();
	        	return false;
    		}
    	}
        
    	if(isNull(trim(tkhai_so))) {
    		if(isNull(trim(so_bke))) {
    			alert("Bạn phải nhập thông tin t�? khai hoặc số bảng kê");
	        	document.forms[0].tkhai_so.focus();
	        	return false;
    		}
    		if(isNull(trim(ngay_bke))) {
    			alert("Bạn phải nhập ngày bảng kê");
	        	document.forms[0].ngay_bke.focus();
	        	return false;
    		}
    	}
    	if(!isNull(so_bke)) {
    		if(isNull(ngay_bke)) {
    			alert("Bạn phải nhập ngày bảng kê");
	        	document.forms[0].ngay_bke.focus();
	        	return false;
    		}
    	}
    	if(!isNull(ngay_bke)) {
    		if(isNull(so_bke)) {
    			alert("Bạn phải nhập số bảng kê");
	        	document.forms[0].so_bke.focus();
	        	return false;
    		}
    	}
    }
	
    if(trim(loai_thue) != '03'){        	
    	document.forms[0].so_khung.value='';
    	document.forms[0].so_may.value='';
    }
    return true;
	
}

function sbSave(type, print){    
	
    var ngay_ct=document.forms[0].ngay_ct.value;
    var nguoi_lap=document.forms[0].ma_nv.value;
    var nguoi_ks=document.forms[0].nguoi_ks.value;       
    var tk_no=document.forms[0].tk_no.value;
    var ttk_no=document.forms[0].ttk_no.value;
    var tk_co=document.forms[0].tk_co.value;
    var ttk_co=document.forms[0].ttk_co.value;    
   
    var ma_thue=document.forms[0].ma_thue.value;
    var ten_thue=document.forms[0].ten_thue.value;
    var dc_nnthue=document.forms[0].dc_nnthue.value;
    var dc_nnthue_huyen=document.forms[0].dc_nnthue_huyen.value;
    var dc_nnthue_tinh=document.forms[0].dc_nnthue_tinh.value;
    var nguoi_ntien=document.forms[0].nguoi_ntien.value;
    var dc_nntien=document.forms[0].dc_nntien.value;    
    
    var ma_dbhc=document.forms[0].ma_dbhc.value;        
    var ten_dbhc=document.forms[0].ten_dbhc.value;
    var cq_thu=document.forms[0].cq_thu.value;
    var tcq_thu=document.forms[0].tcq_thu.value;
    var loai_thue=document.forms[0].loai_thue.value;
    var tloai_thue=document.forms[0].tloai_thue.value;
    var so_khung=document.forms[0].so_khung.value;
    var so_may=document.forms[0].so_may.value;
    var tkhai_so=document.forms[0].tkhai_so.value;  
    
    var ngay_dk=document.forms[0].ngay_dk.value; 
    var ngay_kb_ss = document.forms[0].ngay_kb.value;         
    var lh_xnk=document.forms[0].lh_xnk.value;
    var tlh_xnk=document.forms[0].tlh_xnk.value;
    var ma_nt=document.forms[0].ma_nt.value;
    var ten_nt=document.forms[0].ten_nt.value;
    var ty_gia=document.forms[0].ty_gia.value;
    var so_bke=document.forms[0].so_bke.value;
    var ngay_bke=document.forms[0].ngay_bke.value;
    var so_qd=document.forms[0].so_qd.value; 
    
    var ngay_qd=document.forms[0].ngay_qd.value;           
    var cq_qd=document.forms[0].cq_qd.value;  
	var tong_tien = document.forms[0].tong_tien.value;
	var tong_tien_nt = document.forms[0].tong_tien_nt.value;
	var dien_giai = document.forms[0].dien_giai.value;
	
	var so_ct_nh = document.forms[0].so_ct_nh.value;	
	
	var ma_nh_a = document.forms[0].ma_nh_a.value;
	var ma_nh_nntien = document.forms[0].ma_nh_nntien.value;
	//var ngay_kb_nh = document.forms[0].ngay_kb_nh.value;
	var ngay_nntien = document.forms[0].ngay_nntien.value;
	
	
	
    if (isNull(ngay_ct)) {
    	alert(tcs_nhap_ct_ngay_ct_required);
        document.forms[0].ngay_ct.focus();
        return;
    }     
    if(isNull(loai_thue)) {
    	alert(tcsttkb_loaithue_required);
        document.forms[0].loai_thue.focus();
        return;
    }
    else{
    	if(!sbCheckLoaiThue(loai_thue, so_bke, ngay_bke, tkhai_so, ngay_dk, lh_xnk, ma_thue, ten_thue, dc_nnthue, dc_nnthue_huyen, 
    			dc_nnthue_tinh, nguoi_ntien, dc_nntien, tkhai_so, so_qd, so_bke, so_khung, so_may, so_ct_nh, ma_nh_a, dien_giai, ma_nh_nntien,ngay_nntien)){
    		
    		return;
    	}
    	
    }         
    
    /**
	 * Check co quan quan ly thu
	 */
    if (isNull(cq_thu)){
        alert(tcs_nhap_ct_cqthu_required);
        document.forms[0].cq_thu.focus();
        return;
    }else{
    	/**
		 * Chuyen check co quan thu len tang client
		 */
    	var kctu = document.forms[0].kieu_ctu.value;	
    	
    	if(kctu == '10' || kctu == '11') {
    		if(isNull(tcq_thu)) {
    			alert('Cơ quan thu không tồn tại');
    			document.forms[0].cq_thu.focus();
        		return;
    		}
    	}else {
	    	var a = false;
	    	for(icqt = 0; icqt < temp_CQTHU.length; icqt++)
	    	{    		
	    		try
	    		{
		    		if(a == false){
		    			var ma_cqthu = temp_CQTHU[icqt].split("-"); 
		    			if(trim(ma_cqthu[0]) == trim(cq_thu)){
		        			a=true;
		        			break;
		        		}
		    			else{
		    				a = false;
		    			}
		    		}
	    		}catch (e) {
	    			alert("Cơ quan thu không phù hợp với loại thuế");    			
	        		document.forms[0].cq_thu.focus();
	        		return;
				}
	    	}
	    	if(a==false){
	    		alert("Cơ quan thu không phù hợp với loại thuế");    		
	    		document.forms[0].cq_thu.focus();
	    		return;
	    	}
    	}
    }    
    /**
	 * Check dia ban hanh chinh
	 */
    if (isNull(ma_dbhc)){
        alert(tcs_nhap_ct_ma_dbhc_required);
        document.forms[0].ma_dbhc.focus();
        return;
    } else{        	
    	/**
		 * Chuyen check dia ban hanh chinh len tang client
		 */
    	var a = false;
    	for(idb=0;idb<temp_DBHC.length;idb++)
    	{
    		if(a==false){
    			var ma_db = temp_DBHC[idb].split("-"); 
    			if(trim(ma_db[0])==trim(ma_dbhc)){
        			a=true;
        			break;
        		}
    			else{
    				a = false;
    			}
    		}
    	}
    	if(a==false){
    		alert("�?ịa bàn hành chính không tồn tại");
    		document.forms[0].ma_dbhc.focus();
    		return;
    	}
    }             
    /**
	 * Check tai khoan no
	 */
    if (isNull(tk_no)){
        alert("Bạn phải nhập tài khoản nợ");
        document.forms[0].tk_no.focus();
        return;
    }else{        	
    	/**
		 * Chuyen check tai khoan no len tang client
		 */
    	var a = false;
    	for(itk=0;itk<temp.length;itk++)
    	{
    		if(a==false){
    			var ma_tk = temp[itk].split("-"); 
    			if(trim(ma_tk[0])==trim(tk_no)){
        			a=true;
        			break;
        		}
    			else{
    				a = false;
    			}
    		}
    	}
    	if(a==false){
    		alert("Tài khoản nợ không tồn tại");
    		document.forms[0].tk_no.focus();
    		return;
    	}
    }
    /**
	 * Check tai khoan co
	 */
    if (isNull(tk_co)) {
        alert("Bạn phải nhập tài khoản có");
        document.forms[0].tk_co.focus();
        return;
    }else{        	
    	/**
		 * Chuyen check tai khoan no len tang client
		 */
    	var a = false;
    	for(itc=0; itc < temp.length; itc++)
    	{
    		if(a == false) {
    			var ma_tk = temp[itc].split("-"); 
    			if(trim(ma_tk[0]) == trim(tk_co)) {
        			a=true;
        			break;
        		}
    			else{
    				a = false;
    			}
    		}
    	}
    	if(a==false){
    		alert("Tài khoản có không tồn tại");
    		document.forms[0].tk_co.focus();
    		return;
    	}
    }     
    
    if(tk_no == tk_co) {
    	alert('Bạn không được phép nhập tài khoản nợ - có bằng nhau');
    	document.forms[0].tk_no.focus();
		return;
    }
    /**
	 * Check ma_nte
	 */
    if(isNull(trim(ma_nt))) {
    	alert("Bạn phải nhập mã nguyên tệ");
    	document.forms[0].ma_nt.focus();
        return;
    }else{
    	/**
		 * Chuyen check ma nguyen te len tang client
		 */
    	var a = false;
    	for(int = 0; int < temp_NTe.length; int++)
    	{
    		if(a == false) {
    			var ma_nte = temp_NTe[int].split("-"); 
    			if(trim(ma_nte[0]) == trim(ma_nt)){
        			a = true;
        			break;
        		}
    			else{
    				a = false;
    			}
    		}
    	}
    	if(a==false){
    		alert("Mã nguyên tệ không tồn tại");
    		document.forms[0].ma_nt.focus();
    		return;
    	}
    }
    if(isNull(ty_gia)){
            alert(tcs_nhap_ct_ty_gia_invalid);
            document.forms[0].ty_gia.focus();
            return;
    }    
    
	// Convert lai ty gia de insert: Convert from ### ### ### to ##########
	if(parseFloat(toNumber2(ty_gia))>0){
			document.forms[0].ty_gia.value = toNumber2(ty_gia);				
	} 
	
    if (!checkConstrain()){ 
        return;
    }      
    else{	    	
    	checkMapTKhoanCTu(type,print);
    }
}
function sbNew(){
	    document.forms[0].__action.value="new";
        document.forms[0].submit();
}

   function sbNew_view(){
   	 	document.forms[0].__action.value="new";
        document.forms[0].submit();
   }
  
   function sbExit(){   
      if(confirm('Bạn có chắc chắn muốn thoát kh�?i chức năng này không?')==false)
        return false;
      else{    
            document.forms[0].__action.value="exit";
            document.forms[0].submit();
        }
    }
    function sbDestroy(){    	
        if(confirm('Bạn có chắc chắn muốn hủy chứng từ này không?')==false)
          return false;
        else{    
            document.forms[0].__action.value="destroy";
            document.forms[0].submit();
        }
    }
    function sbActive(){ 	
    	document.forms[0].__action.value="active";
    	document.forms[0].submit();
    }
     function prvPage(){
	         document.forms[0].__action.value="page";
	         document.forms[0].pageStatus.value="<<";
	         document.forms[0].submit();
	    }  
	    function firstPage(){
	         document.forms[0].__action.value="page";
	         document.forms[0].pageStatus.value="first";
	         document.forms[0].submit();
	    }  
	    function nextPage(){	    	
	         document.forms[0].__action.value="page";
	         document.forms[0].pageStatus.value=">>";			  
	         document.forms[0].submit();
	    }
	    function lastPage(){
	         document.forms[0].__action.value="page";
	         document.forms[0].pageStatus.value="last";
	         document.forms[0].submit();
	    }  
		function clearText(thefield, thefield1){			
			if (thefield.defaultValue!=thefield.value)
				thefield1.value = "";
		}		
	var 	keyCode1;
	function findFocus(keyCode1) {	
		
		var w;
		var el;						
		var curElement = document.activeElement;
		var elementName = curElement.name;		
		
		if(elementName == 'ma_thue') {											
			if(keyCode1 == 120 ) {				
				if(!document.getElementById('id_ma_thue').readOnly) {
					javascript:newWindow650480('/common/search/SearchCommonCT.jsp?r_field1=ma_thue&r_field2=ten_thue&r_field3='+document.TcsLapChungTuForm.loai_thue.value,'window3')	;		
					keyCode1 = 0;
				}
			}
		}	
		if(elementName == 'cq_thu') {											
			if(keyCode1 == 120 ) {		
				if(!document.getElementById('id_cq_thu').readOnly) {
					javascript:newWindow650480('/common/search/SearchCQThu.jsp?r_field1=cq_thu&r_field2=tcq_thu&r_field3='+document.forms[0].loai_thue.value,'window3');				
					keyCode1 = 0;
				}
			}
		}	
		if(elementName == 'ma_dbhc') {											
			if(keyCode1 == 120 ) {			
				if(!document.getElementById('id_ma_dbhc').readOnly) {
					javascript:newWindow650480('/common/search/SearchDiaban.jsp?r_field1=ma_dbhc&r_field2=ten_dbhc&r_field3=0','window3');		
					keyCode1 = 0;
				}
			}
		}	
		if(elementName == 'tk_no') {											
			if(keyCode1 == 120 ) {			
				if(!document.getElementById('id_tk_no').readOnly) {
					javascript:newWindow650480('/common/search/SearchTaiKhoan.jsp?r_field1=tk_no&r_field2=ttk_no','window3');		
					keyCode1 = 0;
				}
			}
		}	
		if(elementName == 'tk_co') {											
			if(keyCode1 == 120 ) {	
				if(!document.getElementById('id_tk_co').readOnly) {
					javascript:newWindow650480('/common/search/SearchTaiKhoan.jsp?r_field1=tk_co&r_field2=ttk_co','window3');		
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'lh_xnk') {											
			if(keyCode1 == 120 ) {		
				if(!document.getElementById('id_lh_xnk1').readOnly) {
					javascript:newWindow650480('/common/search/SearchLHXNK.jsp?r_field1=lh_xnk&r_field2=tlh_xnk','window3');			
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'ma_nh_a') {											
			if(keyCode1 == 120 ) {	
				if(!document.getElementById('id_ma_nh_a').readOnly) {
				    javascript:newWindow650480('/common/search/SearchNganHang.jsp?r_field1=ma_nh_a&r_field2=ten_nh_a','window3');
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'ma_nh_nntien') {											
			if(keyCode1 == 120 ) {	
				if(!document.getElementById('id_ma_nh_nntien').readOnly) {
					javascript:newWindow650480('/common/search/SearchNganHang.jsp?r_field1=ma_nh_nntien&r_field2=ten_nt_nntien','window3');
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'ma_dvsdns') {											
			if(keyCode1 == 120 ) {	
				if(!document.getElementById('id_ma_dvsdns').readOnly) {
					javascript:newWindow650480('/common/search/SearchDvsdNSach.jsp?r_field1=ma_dvsdns&r_field2=ten_dvsdns','window3');
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'ma_nt') {											
			if(keyCode1 == 120 ) {	
				if(!document.getElementById('id_ma_nt').readOnly) {
					javascript:newWindow650480('/common/search/SearchNguyenTe.jsp?r_field1=ma_nt&r_field2=ten_nt','window3');
					keyCode1 = 0;
				}
			}
		}
		if(elementName == 'ttct_chuong') {			
			
			var tbl = document.getElementById('tblGridDetailTable');
	        var lastRow = tbl.rows.length - 1;	       
	        var index = chuongIndex;	        
	        if(lastRow == 1) {
	        	if(keyCode1 == 120 ) {	
					if(!document.getElementById('id_chuong_row1').readOnly) {
						javascript:newWindow650480('/common/search/SearchMaChuong.jsp?r_field1=ttct_chuong&r_field2=id_check_user','window3');
						keyCode1 = 0;
					}
				}
	        }else {	        	
	        	if(keyCode1 == 120 ) {										
						javascript:newWindow650480('/common/search/SearchMaChuong1.jsp?r_field1=ttct_chuong['+index+']&r_field2=id_check_user','window3');
						keyCode1 = 0;					
				}
	        }
			
		}
		
		if(elementName == 'ttct_noidungkt') {			
			var tbl = document.getElementById('tblGridDetailTable');
	        var lastRow = tbl.rows.length - 1;	       
	        var index = ndktIndex;	       
	        if(lastRow == 1) {
	        	if(keyCode1 == 120 ) {	
					if(!document.getElementById('id_noidungkt_row1').readOnly) {
						javascript:newWindow650480('/common/search/SearchNDKTe.jsp?r_field1=ttct_noidungkt&r_field2=id_check_user','window3');
						keyCode1 = 0;
					}
				}
	        }else {	        	
	        	if(keyCode1 == 120 ) {	
					if(!document.getElementById('id_noidungkt_row1').readOnly) {
						javascript:newWindow650480('/common/search/SearchNDKTe1.jsp?r_field1=ttct_noidungkt['+index+']&r_field2=id_check_user','window3');
						keyCode1 = 0;
					}
				}
	        }
			
		}
		
		if(elementName == 'ttct_tlpc') {			
			var tbl = document.getElementById('tblGridDetailTable');
	        var lastRow = tbl.rows.length - 1;	       
	        var index = ndktIndex;	       
	        if(lastRow == 1) {
	        	if(keyCode1 == 120 ) {	
					if(!document.getElementById('id_ttct_tlpc').readOnly) {
						javascript:newWindow650480('/common/search/SearchTLPC.jsp?r_field1=ttct_tlpc&r_field2=id_check_user','window3');
						keyCode1 = 0;
					}
				}
	        }else {	        	
	        	if(keyCode1 == 120 ) {	
					if(!document.getElementById('id_ttct_tlpc').readOnly) {
						javascript:newWindow650480('/common/search/SearchTLPC.jsp?r_field1=ttct_tlpc['+index+']&r_field2=id_check_user','window3');
						keyCode1 = 0;
					}
				}
	        }
			
		}
		
	}
	function my_onkeydown_handler_refresh() 
	{ 				
		//var kieu_ctu = document.forms[0].kieu_ctu.value;
		//var lan_in = document.forms[0].lan_in.value;
		//var ma_dthu = document.forms[0].ma_dthu.value;
		// Them cac tham so de kiem tra Alt+F4
		var tecla = window.event.keyCode;	
		var control = window.event.ctrlKey;
		var alt = window.event.altKey;
		var tipo=event.srcElement.type;
		if (alt) {
			switch (tecla) {		
		            // Alt-F4
		            case 115:  
						  event.keyCode=116;  		                 
		                  event.returnValue=false;
						  window.status = "Bạn không được ấn Alt-F4"; 						 
		                  break;
		      }
		}
		else
		{
			switch (event.keyCode) 
			{ 
				
				case 116 : // 'F5'
					event.returnValue = false; 
					event.keyCode = 0; 					
					window.status = "Bạn không được ấn F5"; 
					break;			
				case 118:
					//performLoadNNT_DB();
				case 120:			
					keyCode1 = event.keyCode;
					//findFocus(keyCode1);
				
					
			}
		} 
	} 	
    function performLoadNNT_DB()
    {    	
    	var shkb = document.forms[0].shkb.value;
    	jQuery.ajax({
  		   type: "POST",
  		   url: "LoadNNT_DB.do",			   
  		   data: "shkb="+shkb,
  		   success: function(msg)
  		   {	  			  
  			    if(msg.ma_thue == null)
		    	{
  			    	alert("Không tồn tại mã đặc biệt");
  			    	document.forms[0].ma_thue.focus();
  			    	return;
		    	}
  			    var ma_nnt = msg.ma_thue;
			    var ten_nnt = msg.ten_thue;			    
			    var dc_nnt = msg.dc_nnthue;			   
			    
			    var ma_dbhc = msg.ma_dbhc;
			    var ma_cqthu = msg.cq_thu;
			    var tai_khoan = msg.tk_tnsach;
			   
			    if(ma_nnt != null)
					document.forms[0].ma_thue.value = ma_nnt;
			    if(ten_nnt != null)
					document.forms[0].ten_thue.value = ten_nnt;
				if(dc_nnt != null) 
					document.forms[0].dc_nnthue.value = dc_nnt;
				if(ma_dbhc != null) {
					for (var i = 0; i < temp_DBHC.length; i++)
					{
						var index = temp_DBHC[i].search(ma_dbhc);							
						if(index >=0) {
							var objMa_dbhc = temp_DBHC[i];
							var strSplitMa_DBHC = objMa_dbhc.split("-");
							document.forms[0].ten_dbhc.value = strSplitMa_DBHC[1];
							break;
						}							
					}
					document.forms[0].ma_dbhc.value = ma_dbhc;		
				}
							
				if(ma_cqthu != null) {
					for (var i = 0; i < temp_CQTHU.length; i++)
						{
							var index = temp_CQTHU[i].search(ma_cqthu);							
							if(index >= 0) {
								var objMa_Cqthu = temp_CQTHU[i];
								var strSplitMa_CQThu = objMa_Cqthu.split("-");
								document.forms[0].tcq_thu.value = strSplitMa_CQThu[1];
								break;
							}							
						}
					document.forms[0].cq_thu.value = ma_cqthu;
				}
					
				if(tai_khoan != null) {
					if(tai_khoan.length == 4) {
						for (var i = 0; i < temp.length; i++)
						{
							var index = temp[i].search(tai_khoan);							
							if(index >= 0) {
								var objTK = temp[i];
								var strSplitTK = objTK.split("-");
								document.forms[0].ttk_co.value = strSplitTK[1];
								break;
							}							
						}
					}
					document.forms[0].tk_co.value = tai_khoan;
				}
					
				
				window.status = "Sử dụng phím hỗ trợ F7"; 
				//document.forms[0].dc_nnthue.focus();		
				return;
  		   }
    	});
    }
    
 // Clear from
 function ClearForm(tk_no, ttk_no, tk_co, ttk_co, nguoi_lap, sysdate, shkb, tenkb, ky_thue){
 		
 		var tbl = document.getElementById('tblGridDetailTable');
        var lastRow = tbl.rows.length;
        	
		document.forms[0].sct.value="";
		document.forms[0].ngay_kh_nh.value="";
		document.forms[0].ma_thue.value="";		
		document.forms[0].ten_thue.value="";
		document.forms[0].dc_nnthue.value="";
		document.forms[0].nguoi_ntien.value="";
		document.forms[0].hthuc_thu.value="0";		
		document.getElementById('id_tr_ma_nh_b').style.display = "";
		document.getElementById('id_tr_tk_co').style.display = "";
		document.getElementById('id_tr_tk_no').style.display = "";
		document.getElementById('id_title_ttnh_ht').style.display = "";

		document.forms[0].dc_nntien.value="";
		document.forms[0].ma_dbhc.value="";
		document.forms[0].ten_dbhc.value="";
		document.forms[0].tk_no.value=tk_no;
		document.forms[0].ttk_no.value=ttk_no;

		document.forms[0].tk_co.value=tk_co;
		document.forms[0].ttk_co.value=ttk_co;
		document.forms[0].cq_thu.value="";
		document.forms[0].tcq_thu.value="";
		document.forms[0].loai_thue.value="";

		document.forms[0].tloai_thue.value="";
		document.forms[0].ma_nt.value="VND";
		document.forms[0].ten_nt.value="Tien VN";
		document.forms[0].ty_gia.value="1";
		document.forms[0].tkhai_so.value="";

		document.forms[0].ngay_dk.value="";
		document.forms[0].lh_xnk.value="";		
		document.forms[0].tlh_xnk.value="";
		document.forms[0].so_bke.value="";
		
		document.forms[0].so_qd.value="";
		document.forms[0].ngay_qd.value="";
		document.forms[0].cq_qd.value="";
		document.forms[0].dien_giai.value="";

		document.forms[0].ngay_bke.value="";
		document.forms[0].ma_nv.value=nguoi_lap;
		document.forms[0].nguoi_ks.value="";
		document.forms[0].dc_nnthue_tinh.value="";
		document.forms[0].dc_nnthue_huyen.value="";

		document.forms[0].so_khung.value="";
		document.forms[0].so_may.value="";
		document.forms[0].tk_tnsach.value="";
		document.forms[0].ttk_tnsach.value="";	
		document.forms[0].tong_tien.value="0";

		document.forms[0].ngay_kb.value=sysdate;
		document.forms[0].kbnn_thu.value=shkb;
		document.forms[0].tkbnn_thu.value=tenkb;
		
		document.forms[0].kbnn_thu.readOnly = false;
		document.forms[0].kbnn_thu.style.backgroundColor="#FFFFFF";	
		document.forms[0].ma_thue.readOnly = false;
		document.forms[0].ma_thue.style.backgroundColor="#FFFFFF";	
		document.forms[0].ten_thue.readOnly = false;
		document.forms[0].ten_thue.style.backgroundColor="#FFFFFF";	
		document.forms[0].dc_nnthue.readOnly = false;
		document.forms[0].dc_nnthue.style.backgroundColor="#FFFFFF";
		document.forms[0].tk_tnsach.readOnly = false;
		document.forms[0].tk_tnsach.style.backgroundColor="#FFFFFF";
		document.forms[0].cq_thu.readOnly = false;
		document.forms[0].cq_thu.style.backgroundColor="#FFFFFF";
		document.forms[0].tkhai_so.readOnly = false;
		document.forms[0].tkhai_so.style.backgroundColor="#FFFFFF";
		document.forms[0].ngay_dk.readOnly = false;
		document.forms[0].ngay_dk.style.backgroundColor="#FFFFFF";
		document.forms[0].lh_xnk.readOnly = false;
		document.forms[0].lh_xnk.style.backgroundColor="#FFFFFF";
		document.forms[0].dien_giai.readOnly = false;
		document.forms[0].dien_giai.style.backgroundColor="#FFFFFF";
		
		document.getElementById('cmdAddDiadiem').style.display = "";		
		document.getElementById('id_delete_row').style.display = "";		
					  
		var length_detail=document.forms[0].ttct_ma_quy.length; 
		if(length_detail>1){
			for(i=0;i<length_detail;i++){
				document.forms[0].ttct_ma_quy[i].value="01";
				document.forms[0].ttct_chuong[i].value="";
				document.forms[0].ttct_nganhkt[i].value="";
				document.forms[0].ttct_noidungkt[i].value="";
				document.forms[0].ttct_noidung[i].value="";
				document.forms[0].ttct_tien_nt[i].value="0";
				document.forms[0].ttct_tien[i].value="0";
				document.forms[0].ttct_ky_thue[i].value=ky_thue;
				document.forms[0].ttct_khtk[i].value="00";
				
				
				document.forms[0].ttct_chuong[i].readOnly = false;
				document.forms[0].ttct_chuong[i].style.backgroundColor="#FFFFFF";	
				
				document.forms[0].ttct_nganhkt[i].readOnly = false;
				document.forms[0].ttct_nganhkt[i].style.backgroundColor="#FFFFFF";
				
				document.forms[0].ttct_noidungkt[i].readOnly = false;
				document.forms[0].ttct_noidungkt[i].style.backgroundColor="#FFFFFF";
				
				document.forms[0].ttct_noidung[i].readOnly = false;
				document.forms[0].ttct_noidung[i].style.backgroundColor="#FFFFFF";
				
				document.forms[0].ttct_tien_nt[i].readOnly = false;
				document.forms[0].ttct_tien_nt[i].style.backgroundColor="#FFFFFF";
			}
		}
		else{			
			document.forms[0].ttct_ma_quy.value="01";
			document.forms[0].ttct_chuong.value="";
			document.forms[0].ttct_nganhkt.value="";
			document.forms[0].ttct_noidungkt.value="";
			document.forms[0].ttct_noidung.value="";
			document.forms[0].ttct_tien_nt.value="0";
			document.forms[0].ttct_tien.value="0";
			document.forms[0].ttct_ky_thue.value=ky_thue;
			document.forms[0].ttct_khtk.value="00";
			
			document.forms[0].ttct_chuong.readOnly = false;
			document.forms[0].ttct_chuong.style.backgroundColor="#FFFFFF";	
			
			document.forms[0].ttct_nganhkt.readOnly = false;
			document.forms[0].ttct_nganhkt.style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_noidungkt.readOnly = false;
			document.forms[0].ttct_noidungkt.style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_noidung.readOnly = false;
			document.forms[0].ttct_noidung.style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_tien_nt.readOnly = false;
			document.forms[0].ttct_tien_nt.style.backgroundColor="#FFFFFF";
		}
		
		if(lastRow>2){
			for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){
				document.all("tblGridDetailTable").deleteRow(rowdelete);
			}
		}
}
function initOnloadPage(){	
      	document.getElementById('ID_SO_KHUNG').style.display = "none";    
	    document.getElementById('ID_SO_MAY').style.display = "none";
	    document.getElementById('ID_TKHAI_SO').style.display = "none";
	    document.getElementById('ID_NGAY_DK').style.display = "none";
	    document.getElementById('ID_LH_XNK').style.display = "none";
		var loai_thue = document.forms[0].loai_thue.value;
      	loai_thue = trim(loai_thue);
      	if(loai_thue=="03"){      		
      		document.getElementById('ID_SO_KHUNG').style.display = "";    
	    	document.getElementById('ID_SO_MAY').style.display = "";
      	}      	
	   if(loai_thue=="04"){
		    document.getElementById('ID_TKHAI_SO').style.display = "";
		    document.getElementById('ID_NGAY_DK').style.display = "";
		    document.getElementById('ID_LH_XNK').style.display = "";	 
	   }
}
function ChangeHTT(tk_tien_mat,ten_tk_tien_mat){    		
		
 		var hthuc_thu = document.forms[0].hthuc_thu.value;
 		var tk_no = document.forms[0].tk_no.value;
 		var ttk_no = document.forms[0].ttk_no.value;          			
 		if (hthuc_thu==0){ 
            document.getElementById('id_tk_no').value=tk_tien_mat;
            document.getElementById('id_ttk_no').value = ten_tk_tien_mat;
            if(document.getElementById('id_ttk_no').value=="undefined"){	            	
            	document.getElementById('id_ttk_no').value='';
            }	     
                 
            document.forms[0].tk_no.readOnly=true;
        	document.forms[0].ttk_no.readOnly=true;
            document.forms[0].tk_no.style.backgroundColor="#E7F1FE";	 
            document.forms[0].ttk_no.style.backgroundColor="#E7F1FE";	           

			document.getElementById('id_tr_ma_nh_b').style.display = "";
			document.getElementById('id_tr_tk_co').style.display = "";
			document.getElementById('id_tr_tk_no').style.display = "";
			document.getElementById('id_title_ttnh_ht').style.display = "";
			document.getElementById('id_view_tien_tk').style.display="none";
        }else if(hthuc_thu==1){
 			document.forms[0].tk_no.value = "";
        	document.forms[0].ttk_no.value = "";
 	        document.forms[0].tk_no.readOnly=false;
 	        document.forms[0].ttk_no.readOnly=false;
            document.forms[0].tk_no.style.backgroundColor="#FFFFFF";
            document.forms[0].ttk_no.style.backgroundColor="#FFFFFF";
			document.getElementById('id_tr_ma_nh_b').style.display = "";
			document.getElementById('id_tr_tk_co').style.display = "";
			document.getElementById('id_tr_tk_no').style.display = "";
			document.getElementById('id_title_ttnh_ht').style.display = "";
			document.getElementById('id_view_tien_tk').style.display="none";
            document.forms[0].tk_no.focus();
        }else if(hthuc_thu==2){
 			document.forms[0].tk_no.value = "";
        	document.forms[0].ttk_no.value = "";
 	        document.forms[0].tk_no.readOnly=false;
 	        document.forms[0].ttk_no.readOnly=false;
            document.forms[0].tk_no.style.backgroundColor="#FFFFFF";
            document.forms[0].ttk_no.style.backgroundColor="#FFFFFF";
			document.getElementById('id_tr_ma_nh_b').style.display = "";
			document.getElementById('id_tr_tk_co').style.display = "";
			document.getElementById('id_tr_tk_no').style.display = "";
			document.getElementById('id_title_ttnh_ht').style.display = "";
			document.getElementById('id_view_tien_tk').style.display="none";
            document.forms[0].tk_no.focus();
        }
        else{
			document.getElementById('id_tr_ma_nh_b').style.display = "none";
			document.getElementById('id_tr_tk_co').style.display = "none";
			document.getElementById('id_tr_tk_no').style.display = "none";
			document.getElementById('id_title_ttnh_ht').style.display = "none";
			document.getElementById('id_view_tien_tk').style.display="none";
		}
}   
    
function CheckSoTaiKhoan(obj){
		var tk = trim(obj.value);
		if(tk==''){									
			return;
		}
		var tk_split = tk.split(".");
		for(v_tk=0;v_tk<tk_split.length;v_tk++){										
			if(tk_split[v_tk] == ''){
				if(v_tk == 0 ){
						alert("Số tài khoản bạn nhập không hợp lệ");	
						obj.focus();											
						return;
				}
				if(v_tk<tk_split.length-1){
					if(tk_split[v_tk++] == ''){
						alert("Số tài khoản bạn nhập không hợp lệ");
						obj.focus();
						return;
					}
				}
				if(v_tk==tk_split.length-1){
					alert("Số tài khoản bạn nhập không hợp lệ");
					obj.focus();
					return;
				}
			}							
			
		}								
}

/*
 * CAC HAM FOTMAT TIEN - HE THONG TCS
 */

	 function checkMST_13so(ma_st_value)
     {     
		
       var char0,char1,char2,char3,char4,char5,char6,char7,char8,char9,char10,char11,char12 ;
       if (ma_st_value.length == 13)
       {
            // alert("Chay nao !");
            char0 = ma_st_value.charAt(0);
            char1 = ma_st_value.charAt(1);
            char2 = ma_st_value.charAt(2);
			char3 = ma_st_value.charAt(3);
			char4 = ma_st_value.charAt(4);
			char5 = ma_st_value.charAt(5);
			char6 = ma_st_value.charAt(6);
			char7 = ma_st_value.charAt(7);
			char8 = ma_st_value.charAt(8);
			char9 = ma_st_value.charAt(9);
			char10 = ma_st_value.charAt(10);    
			char11 = ma_st_value.charAt(11);
			char12 = ma_st_value.charAt(12);
            ma_st_value = ""; 
            ma_st_value = char0 + char1 + char2 + char3 + char4 + char5 + char6 + char7 + char8 + char9 + "-" + char10 + char11 + char12 ;           
          
       }
      
       return ma_st_value;
     }
     function isDate(dateStr) {
		var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
		var matchArray = dateStr.match(datePat); // is the format ok?

		if (matchArray == null) {
			// alert("Bạn hãy vào ngày theo định dạng dd/MM/yyyy.");
			return false;
		}
		day = matchArray[1];
		month = matchArray[3]; // p@rse date into variables
		year = matchArray[5];

		if (month < 1 || month > 12) { // check month range
			// alert("Tháng phải giữa 1 và 12.");
			return false;
		}

		if (day < 1 || day > 31) {
			// alert("Ngày phải giữa 1 và 31.");
			return false;
		}

		if ((month==4 || month==6 || month==9 || month==11) && day==31) {
			// alert("Tháng "+month+" không có 31 ngày!")
			return false;
		}

		if (month == 2) { // check for february 29th
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29 || (day==29 && !isleap)) {
		// alert("Tháng 2" + year + " không có " + day + " ngày!");
		return false;
	}
	}
	return true; // date is valid
	}
     
	 function check_ngaytk_ngayht(obj)
     {     	
     	 if(CheckDate(obj))
     	 {
	         var ngay_tk_ss = document.forms[0].ngay_dk.value ;
	         var ngay_ht_ss = document.forms[0].ngay_ct.value ;	        
	         if (isNull(ngay_tk_ss) == false && isNull(ngay_ht_ss) == false){            	 
	         	 if(isDate(ngay_tk_ss))
	         	 {
	         	 	 if (CompareDate(ngay_tk_ss,ngay_ht_ss) != 1 && CompareDate(ngay_tk_ss,ngay_ht_ss) != 0 )
			         {
			             alert("Ngày t�? khai(ngày đăng ký) không thể muộn hơn ngày hạch toán !!");
			             document.forms[0].ngay_dk.focus(); 	
			         }
	         	 }
		         
	         }
         }
     }	
     function check_ngaybk_ngayht(obj)
     {
     	if(CheckDate(obj)){
	         var ngay_bk_ss = document.forms[0].ngay_bke.value ;
	         var ngay_ht_ss = document.forms[0].ngay_ct.value ;	        
	         
	         if (isNull(ngay_bk_ss) == false && isNull(ngay_ht_ss) == false){
	         if(isDate(ngay_bk_ss)){
			         if (CompareDate(ngay_bk_ss,ngay_ht_ss) != 1 && CompareDate(ngay_bk_ss,ngay_ht_ss) != 0 )
			         {
			             alert("Ngày bảng kê không thể muộn hơn ngày hạch toán !!");
			             document.forms[0].ngay_bke.focus(); 	
			         }
		         }
	          } 
          }
     }
 
    function ChangeLoaiThue(obj){  
     	var loai_thue=obj.value;
     	if(trim(loai_thue)=='03'){
     		document.getElementById('ID_SO_KHUNG').style.display = "";    
	    	document.getElementById('ID_SO_MAY').style.display = "";
	    	document.getElementById('ID_SO_TB').style.display = "";
     	}else{
     		document.getElementById('ID_SO_KHUNG').style.display = "none";    
	    	document.getElementById('ID_SO_MAY').style.display = "none";
	    	document.getElementById('ID_SO_TB').style.display = "none";
     	}
     	if(trim(loai_thue)=='04'){
     		document.getElementById('ID_SO_TKHAI').style.display = "";
	    	document.getElementById('ID_LH_XNK').style.display = "";
     	}else{
     	    document.getElementById('ID_SO_TKHAI').style.display = "none";         		
	    	document.getElementById('ID_LH_XNK').style.display = "none";	 	    	
     	}
     	if(isNull(loai_thue))
 		{
     		document.forms[0].loai_thue.value = '01';
     		document.forms[0].tloai_thue.value = 'Thuế nội địa';
     		LoadMa_CQThu();     		
     		return;
 		}     	
     }     
    function GetThongTinNNT(check_mst, dataExits) { 
    	
    	var tbl = document.getElementById('tblGridDetailTable');
    	var lastRow = tbl.rows.length;
    	
    	var loai_thue = document.forms[0].loai_thue.value;    	
    	var ma_thue = document.forms[0].ma_thue.value;
    	
    	if(isNull(loai_thue)) return;				
		if(isNull(ma_thue)) return;		
    	
    	var arr = new Array("00", "01", "02", "03", "04", "05", "06");
    	var length_MaThue = new Array(10, 14);
    	var bFind = false;  
		
    	for(var i = 0; i < arr.length; i++)
		{
    		if(arr[i] == loai_thue)
    			{
    				bFind = true;
    				break;
    			}
    		
 		}
    	if(bFind == false) {
    		alert("Mã loại thuế không tồn tại. Bạn phải ch�?n lại loại thuế");
    		document.forms[0].loai_thue.focus();
    		return;
    		
    	}    	
    	var ma_sothue_ = checkMST_13so(ma_thue);
    	document.forms[0].ma_thue.value = ma_sothue_;
    	
    	
    	if(check_mst == 'K') {
    		if(ma_sothue_.indexOf('--') >= 0) {
				 alert("Mã số thuế bạn nhập không hợp lệ");
				 document.forms[0].ma_thue.focus();
				 return;
			}
    		bFind = true;
    	}else {  
    		bFind = false;
    		for(var j = 0; j < 2; j++)
			{    			
    			if(length_MaThue[j]  == ma_sothue_.length)
    				{    
    					if(ma_sothue_.indexOf('--') >= 0) {
    						 alert("Mã số thuế bạn nhập không hợp lệ");
    						 document.forms[0].ma_thue.focus();
    						 return;
    					}
    				 	bFind = true;
    				 	break;
    				}    			
			}	
    	}
		
		if(bFind == false)
		{
			alert("Mã NNT không đúng định dạng. Bạn phải ch�?n lại");
    		document.forms[0].ma_thue.focus();
			return;
		}
		var thu_gop = document.getElementsByName("thu_gop");
		var len = thu_gop.length;
		
		for(l=0; l < len; l++) {			
			if(thu_gop[l].checked) {
				document.forms[0].thu_gop.value = "1";
			}
			else {
				document.forms[0].thu_gop.value = "0";
			}
		}
		
	    jQuery.ajax({
		   type: "POST",
		   url: "GetNNTDetail.do",			   
		   data: "ma_thue="+ma_sothue_+"&"+
		   		 "ma_lthue="+loai_thue+"&"+
		   		 "shkb="+document.forms[0].shkb.value+"&"+
		   		 "thu_gop="+document.forms[0].thu_gop.value
		   		 ,
		   success: function(msg) {		
			   
			   if(msg.nguon_dl == '0') 
			   {		
				   if(dataExits == 'No')
					   resetForm();
				   if(confirm('Mã NNT không có trong danh mục. Bạn có muốn nhập tiếp không?')){
					   document.getElementById('id_cq_thu').readOnly = false;
			           document.getElementById('id_cq_thu').style.backgroundColor = "#FFFFFF";
			           document.getElementById('id_tkhai_so').readOnly = false;
			           document.getElementById('id_tkhai_so').style.backgroundColor = "#FFFFFF";	
			           document.getElementById('id_ngay_dk').readOnly = false;
			           document.getElementById('id_ngay_dk').style.backgroundColor = "#FFFFFF";
			           document.getElementById('id_lh_xnk1').readOnly = false;
			           document.getElementById('id_lh_xnk1').style.backgroundColor = "#FFFFFF";
					   document.forms[0].nguon_dl.value=msg.nguon_dl;
					   document.forms[0].ten_thue.focus();					   
					   return;
				   }else{
					   document.getElementById('id_cq_thu').readOnly = false;
			           document.getElementById('id_cq_thu').style.backgroundColor = "#FFFFFF";
			           document.getElementById('id_tkhai_so').readOnly = false;
			           document.getElementById('id_tkhai_so').style.backgroundColor = "#FFFFFF";	
			           document.getElementById('id_ngay_dk').readOnly = false;
			           document.getElementById('id_ngay_dk').style.backgroundColor = "#FFFFFF";
			           document.getElementById('id_lh_xnk1').readOnly = false;
			           document.getElementById('id_lh_xnk1').style.backgroundColor = "#FFFFFF";
					   document.forms[0].nguon_dl.value=msg.nguon_dl;
					   document.forms[0].ma_thue.value = "";
					   document.forms[0].ma_thue.focus();
					   return;
				   }
				   
				   
			   }			  
			   var nguon_temp =  msg.nguon_dl;	  
			   var lengthDetail = msg.dtl_data.length;
			   var dataObj = msg.dtl_data[0];		
			   var ma_hq_ph = dataObj.ma_hq_ph;
			   var ma_hq = dataObj.ma_hq;
			   var nguon_sothue = msg.so_thue;
			  			  
			   if(nguon_temp == 4) {					  
					 document.forms[0].ten_thue.value = dataObj.ten_nnt;					
					 document.forms[0].dc_nnthue.value = dataObj.dia_chi;
					 
				   if(lengthDetail > 1) {
					    javascript:newWindow20110521('/common/search/SearchToKhai.jsp', 'window3');
				   		return;
				   }
			   }	
			   if(nguon_sothue == 'SO_THUE') {
				   document.forms[0].ten_thue.value = dataObj.ten_nnt;					
				   document.forms[0].dc_nnthue.value = dataObj.dia_chi;
				   if(lengthDetail > 1) {
					    javascript:newWindow20110521('/common/search/SearchSoThue.jsp', 'window3');
				   		return;
				   } 
			   }
	       	 	/**
				 * Set cac param len form
				 */
			   /** ------------------------------- */
		         // LOAI THUE
			   
			   
			   if(trim(document.getElementById('id_loai_thue').value)=='04'){
	            	document.getElementById('ID_SO_TKHAI').style.display = "";				   
				    document.getElementById('ID_LH_XNK').style.display = "";	
	            }
	            else if(trim(document.getElementById('id_loai_thue').value)=='03'){
	            	document.getElementById('ID_SO_KHUNG').style.display = "";    
		    		document.getElementById('ID_SO_MAY').style.display = "";
		    		document.getElementById('ID_SO_TB').style.display = "";
	            }else 
	            {
	            	document.getElementById('ID_SO_TKHAI').style.display = "none";				    
				    document.getElementById('ID_LH_XNK').style.display = "none";	
				    document.getElementById('ID_SO_KHUNG').style.display = "none";    
		    		document.getElementById('ID_SO_MAY').style.display = "none";	
		    		document.getElementById('ID_SO_TB').style.display = "none";
	            }
			   
			   if(dataExits == 'No')
			   {
				   
				   if(dataObj.ten_nnt == null){		
					   document.getElementById('id_ten_thue').value = '';		
				   }else{			
					   
					   document.getElementById('id_ten_thue').value = dataObj.ten_nnt;			
				   }
			    
			  
				   if(dataObj.dia_chi==null){
		    	   		document.getElementById('id_dc_nnthue').value = '';       
				   }    
				   else{
		       			document.getElementById('id_dc_nnthue').value = dataObj.dia_chi;       
				   }
				   
				   if(dataObj.tinh_nnt==null){
		    	   		document.getElementById('id_dc_nnthue_tinh').value = '';       
				   }    
				   else{
		       			document.getElementById('id_dc_nnthue_tinh').value = dataObj.tinh_nnt;       
				   }
				   
				   if(dataObj.huyen_nnt==null){
					   document.getElementById('id_dc_nnthue_huyen').value = '';   
				   }    
				   else{
		       			document.getElementById('id_dc_nnthue_huyen').value = dataObj.huyen_nnt;       
				   }
				
				   if(dataObj.ma_cqthu == null || dataObj.ma_cqthu.length == 0){
					   document.getElementById('id_cq_thu').value = '';  
					   
					   document.getElementById('id_cq_thu').readOnly = false;
			           document.getElementById('id_cq_thu').style.backgroundColor = "#FFFFFF";
					   document.getElementById('id_tcq_thu').value = ''; 					   
				   }	
				   else {   
					   if(nguon_temp == '4' || nguon_temp == '3') {
			            	document.getElementById('id_cq_thu').readOnly = true;
				         	document.getElementById('id_cq_thu').style.backgroundColor = "#F0F0F0";
			            }
			            else {
			            	document.getElementById('id_cq_thu').readOnly = false;
				         	document.getElementById('id_cq_thu').style.backgroundColor = "#FFFFFF";
			            }
					   document.getElementById('id_cq_thu').value = dataObj.ma_cqthu;  
					   if(dataObj.ten_cqthu != null && dataObj.ten_cqthu.length > 0) {	
						   document.getElementById('id_tcq_thu').value = dataObj.ten_cqthu;             
					   }
					   else {
						   document.getElementById('id_tcq_thu').value = '';         
						   for (var i = 0; i < temp_CQTHU.length; i++)
							{							   
								var index = temp_CQTHU[i].search(dataObj.ma_cqthu);							
								if(index >= 0) {
									var objMa_cqthu = temp_CQTHU[i];
									var strSplitMa_CQTHU = objMa_cqthu.split("-");
									document.forms[0].tcq_thu.value = strSplitMa_CQTHU[1];
									break;
								}							
							}
		            }		            
		         }    
		         
		       /** ----------------------- */			  
			   
			   if(dataObj.ma_dbhc == null){
				    document.getElementById('id_ma_dbhc').value = '';     
					document.getElementById('id_ten_dbhc').value = '';   
			   }   
			   else{
			   		document.getElementById('id_ma_dbhc').value = dataObj.ma_dbhc;    
					document.getElementById('id_ten_dbhc').value = dataObj.ten_dbhc;
					if(document.getElementById('id_ten_dbhc').value == "undefined" || document.getElementById('id_ten_dbhc').value==""){
						for (var i = 0; i < temp_DBHC.length; i++)
						{
							var index = temp_DBHC[i].search(dataObj.ma_dbhc);							
							if(index >= 0) {
								var objMa_dbhc = temp_DBHC[i];
								var strSplitMa_DBHC = objMa_dbhc.split("-");
								document.forms[0].ten_dbhc.value = strSplitMa_DBHC[1];
								break;
							}							
						}
					}			   		  
					
			   } 

		         /** --------------------------------- */		    
		         // SO TO KHAI
			   
			   	 if(nguon_temp == '4') {
			         if(dataObj.so_tk == null || dataObj.so_tk.length == 0){
			        	 document.getElementById('id_tkhai_so').value = '';  
			        	 document.getElementById('id_tkhai_so').readOnly = false;
			         	 document.getElementById('id_tkhai_so').style.backgroundColor = "#FFFFFF";	
			         }else{	
			         	 document.getElementById('id_tkhai_so').value = dataObj.so_tk;
			         	 document.getElementById('id_tkhai_so').readOnly = true;
			         	 document.getElementById('id_tkhai_so').style.backgroundColor = "#F0F0F0";			         
			         }
		         }else {
		        	 if(dataObj.so_tk == null || dataObj.so_tk.length == 0){
			        	 document.getElementById('id_tkhai_so').value = '';  
			        	 document.getElementById('id_tkhai_so').readOnly = false;
			         	 document.getElementById('id_tkhai_so').style.backgroundColor = "#FFFFFF";	
			         }else{	
			         	 document.getElementById('id_tkhai_so').value = dataObj.so_tk;
			         	 document.getElementById('id_tkhai_so').readOnly = false;
			         	 document.getElementById('id_tkhai_so').style.backgroundColor = "#FFFFFF";			         
			         }
		         }
		         /** --------------------------------- */
		         // NGAY TO KHAI
			   	 if(nguon_temp == '4') {
			         if(dataObj.ngay_tk==null || dataObj.ngay_tk.length == 0){
			        	 document.getElementById('id_ngay_dk').value = '';
			        	 document.getElementById('id_ngay_dk').readOnly = false;
			         	 document.getElementById('id_ngay_dk').style.backgroundColor = "#FFFFFF";
			         }else{
			         	 document.getElementById('id_ngay_dk').value=dataObj.ngay_tk;
			         	 document.getElementById('id_ngay_dk').readOnly = true;
			         	 document.getElementById('id_ngay_dk').style.backgroundColor = "#F0F0F0";
			         }
			   	 }else {
			   		 if(dataObj.ngay_tk==null){
			        	 document.getElementById('id_ngay_dk').value = '';
			        	 document.getElementById('id_ngay_dk').readOnly = false;
			         	 document.getElementById('id_ngay_dk').style.backgroundColor = "#FFFFFF";
			         }else{
			         	 document.getElementById('id_ngay_dk').value=dataObj.ngay_tk;
			         	 document.getElementById('id_ngay_dk').readOnly = false;
			         	 document.getElementById('id_ngay_dk').style.backgroundColor = "#FFFFFF";
			         }
			   	 }
		         /** --------------------------------- */
		         // LHXNK
			   	 if(nguon_temp == '4') {
			         if(dataObj.ten_vt_lhxnk==null || dataObj.ten_vt_lhxnk.length == 0){		        	 
			        	 document.getElementById('id_lh_xnk1').value = '';
			         	 document.getElementById('id_tlh_xnk1').value = '';

			         	 document.getElementById('id_lh_xnk1').readOnly = false;
			         	 document.getElementById('id_lh_xnk1').style.backgroundColor = "#FFFFFF";
			         }else{		        	
			         	document.getElementById('id_lh_xnk1').value = dataObj.ten_vt_lhxnk;
			         	document.getElementById('id_tlh_xnk1').value = dataObj.lhxnk + " - " + dataObj.ten_lhxnk;
			         	
			         	document.getElementById('id_lh_xnk1').readOnly = true;
			         	document.getElementById('id_lh_xnk1').style.backgroundColor = "#F0F0F0";			         	
			         }   
			   	 }
			   	 else {
			   		 if(dataObj.ten_vt_lhxnk==null){		        	 
			        	document.getElementById('id_lh_xnk1').value = '';
			         	document.getElementById('id_tlh_xnk1').value = '';
			         	document.getElementById('id_lh_xnk1').readOnly = false;
			         	document.getElementById('id_lh_xnk1').style.backgroundColor = "#FFFFFF";
			         }else{		        	
			         	document.getElementById('id_lh_xnk1').value = dataObj.ten_vt_lhxnk;
			         	document.getElementById('id_lh_xnk1').readOnly = false;
			         	document.getElementById('id_lh_xnk1').style.backgroundColor = "#FFFFFF";			         	
			         	document.getElementById('id_tlh_xnk1').value = dataObj.lhxnk + " - " + dataObj.ten_lhxnk;
			         	
			         }   
			   	 }
		         // TK_NS
					
		         if(dataObj.tk_ns==null){
		        	 document.getElementById('id_tk_co').value = '';
		         }else{
		         	 document.getElementById('id_tk_co').value=dataObj.tk_ns;
		         	if(dataObj.tk_ns.length == 4) {
						for (var i = 0; i < temp.length; i++)
						{
							var index = temp[i].search(dataObj.tk_ns);							
							if(index >= 0) {
								var objTK = temp[i];
								var strSplitTK = objTK.split("-");
								document.forms[0].ttk_co.value = strSplitTK[1];
								break;
							}							
						}
					}
		         }
		         
		         // SO_QD
					
		         if(dataObj.so_qd==null){
		        	 document.getElementById('id_so_qd').value = '';
		         }else{
		         	 document.getElementById('id_so_qd').value=dataObj.so_qd;
		         }
		         // NGAY_QD
					
		         if(dataObj.ngay_qd==null){
		        	 document.getElementById('id_ngay_qd').value = '';
		         }else{
		         	 document.getElementById('id_ngay_qd').value=dataObj.ngay_qd;
		         }
		         /** ----------------------------------- */
		         /**PHAN DETAIL--------------------------/
		         /** ----------------------------------- */
		        
		         if(lengthDetail == 1) {		        	
	        	 	 if(dataObj.ma_quy == null){
			         	document.getElementById('id_ma_quy_row1').value = '01';
			         }
			         else{
			         	document.getElementById('id_ma_quy_row1').value = dataObj.ma_quy;
			         }
	        	 	 
	        	 	 if(dataObj.ma_chuong == null){
			         	document.getElementById('id_chuong_row1').value = '';
			         	document.getElementById('id_chuong_row1').readOnly = false;
		        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
			         }
			         else{		        			        	 
			        	if (nguon_temp == 1 || nguon_temp == 3 ){
			        		if(dataObj.ma_chuong.length > 0) {
				        		document.getElementById('id_chuong_row1').readOnly = false;//true - 20110822
				        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
			        		}
			        	}else{
			        		document.getElementById('id_chuong_row1').readOnly = false;
			        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
			        	}
			         	document.getElementById('id_chuong_row1').value = dataObj.ma_chuong;
			         }
	        	 	 
	        	 	if(dataObj.ma_ndkt==null){   
				    	 document.getElementById('id_noidungkt_row1').value = '';      
				    	 document.getElementById('id_noidungkt_row1').readOnly = false;
			        	 document.getElementById('id_noidungkt_row1').style.backgroundColor = "#FFFFFF";
			        }
			        else{
				        document.getElementById('id_noidungkt_row1').value = dataObj.ma_ndkt;	  
				        if (nguon_temp == 4 ){	 	 			        		
 			        		document.getElementById('id_noidungkt_row1').readOnly = true;
 			        		document.getElementById('id_noidungkt_row1').style.backgroundColor = "#F0F0F0";
 			        	}else {
 			        		document.getElementById('id_noidungkt_row1').readOnly = false;
 			        		document.getElementById('id_noidungkt_row1').style.backgroundColor = "#FFFFFF";
 			        	}
				    } 	
	        	 	if(dataObj.noi_dung==null){           
				    	 document.getElementById('id_noidung_row1').value = '';      
				    	 document.getElementById('id_noidung_row1').readOnly = false;
				    	 document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
			         }
			         else{
				         document.getElementById('id_noidung_row1').value = dataObj.noi_dung;	  
				         if (nguon_temp == 4 ){	 	 
	 				        	document.getElementById('id_noidung_row1').readOnly = false;
	 			        		document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
	 				        }else {
	 				        	document.getElementById('id_noidung_row1').readOnly = false;
	 			        		document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
	 				        }
				     }	
	        	 	
	        	 	if(dataObj.so_tien == null){
				    	 document.getElementById('id_tien_nt_row1').value='';     
				     }else{
				     	document.getElementById('id_tien_nt_row1').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
				     }   
	        	 	
	        	 	if(dataObj.so_tien==null){
				    	 document.getElementById('id_tien_row1').value = '';  
				     }else{
				     	document.getElementById('id_tien_row1').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
				     }
	        	 	 if(dataObj.ky_thue==null){
			         	
			         }
			         else{
			         	document.getElementById('id_ky_thue_row1').value = dataObj.ky_thue;
			         }
	        	 	 
	        	 	 if(dataObj.so_tien==null){
						 document.getElementById('id_tong_tien_nt').value = '';
					 }else{
						document.getElementById('id_tong_tien_nt').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
					 }
	        	 	 
	        	 	 if(dataObj.so_tien==null){
							document.getElementById('id_tong_tien').value='';	 	
						 }else{
							document.getElementById('id_tong_tien').value=toFormatNumberDe_TCS(dataObj.so_tien,2);
					 }	
	        	 	if(lastRow > 2){
						for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){
							document.all("tblGridDetailTable").deleteRow(rowdelete);
					    }
				     }
		         }else {	
		        	 dataObj = msg.dtl_data[0];
		        	 if(dataObj.ma_quy == null){
	 			         	document.getElementById('id_ma_quy_row1').value = '01';
	 			         }
	 			         else{
	 			         	document.getElementById('id_ma_quy_row1').value = dataObj.ma_quy;
	 			         }
	 	        	 	 
	 	        	 	 if(dataObj.ma_chuong == null){
	 			         	document.getElementById('id_chuong_row1').value = '';
	 			         	document.getElementById('id_chuong_row1').readOnly = false;
 			        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
	 			         }
	 			         else{		        			        	 
	 			        	if (nguon_temp == 1 || nguon_temp == 3 ){	 
	 			        		if(dataObj.ma_chuong.length > 0) {
		 			        		document.getElementById('id_chuong_row1').readOnly = false;//20110822
		 			        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
	 			        		}
	 			        	}else{
	 			        		document.getElementById('id_chuong_row1').readOnly = false;
	 			        		document.getElementById('id_chuong_row1').style.backgroundColor = "#FFFFFF";
	 			        	}
	 			         	document.getElementById('id_chuong_row1').value = dataObj.ma_chuong;
	 			         }
	 	        	 	 
	 	        	 	if(dataObj.ma_ndkt==null){   
	 				    	 document.getElementById('id_noidungkt_row1').value = '';     
	 				    	 document.getElementById('id_noidungkt_row1').readOnly = false;
 			        		 document.getElementById('id_noidungkt_row1').style.backgroundColor = "#FFFFFF";
	 			         }
	 			         else{
	 			        	document.getElementById('id_noidungkt_row1').value = dataObj.ma_ndkt;	
	 			        	if (nguon_temp == 4 ){	 	 			        		
	 			        		document.getElementById('id_noidungkt_row1').readOnly = true;
	 			        		document.getElementById('id_noidungkt_row1').style.backgroundColor = "#F0F0F0";
	 			        	}else {
	 			        		document.getElementById('id_noidungkt_row1').readOnly = false;
	 			        		document.getElementById('id_noidungkt_row1').style.backgroundColor = "#FFFFFF";
	 			        	}
	 				     } 	
	 	        	 	if(dataObj.noi_dung==null){           
	 				    	 document.getElementById('id_noidung_row1').value = '';     
	 				    	 document.getElementById('id_noidung_row1').readOnly = false;
 			        		 document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
	 			         }
	 			         else{
	 				         document.getElementById('id_noidung_row1').value = dataObj.noi_dung;
	 				        if (nguon_temp == 4 ){	 	 
	 				        	document.getElementById('id_noidung_row1').readOnly = false;
	 			        		document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
	 				        }else {
	 				        	document.getElementById('id_noidung_row1').readOnly = false;
	 			        		document.getElementById('id_noidung_row1').style.backgroundColor = "#FFFFFF";
	 				        }
	 				     }	
	 	        	 	
	 	        	 	if(dataObj.so_tien == null){
	 				    	 document.getElementById('id_tien_nt_row1').value='';     
	 				     }else{
	 				     	document.getElementById('id_tien_nt_row1').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
	 				     }   
	 	        	 	
	 	        	 	if(dataObj.so_tien==null){
	 				    	 document.getElementById('id_tien_row1').value = '';  
	 				     }else{
	 				     	document.getElementById('id_tien_row1').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
	 				     }
	 	        	 	 if(dataObj.ky_thue==null){
	 			         	
	 			         }
	 			         else{
	 			         	document.getElementById('id_ky_thue_row1').value = dataObj.ky_thue;
	 			         }
	 	        	 	 
	 	        	 	 if(dataObj.so_tien==null){
	 						 document.getElementById('id_tong_tien_nt').value = '';
	 					 }else{
	 						document.getElementById('id_tong_tien_nt').value = toFormatNumberDe_TCS(dataObj.so_tien,2);
	 					 }
	 	        	 	 
	 	        	 	 if(dataObj.so_tien==null){
	 							document.getElementById('id_tong_tien').value='';	 	
	 						 }else{
	 							document.getElementById('id_tong_tien').value=toFormatNumberDe_TCS(dataObj.so_tien,2);
	 					 }	
	 	        	 	 
	 	        	 	 if(lastRow > 2){
							for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){
								document.all("tblGridDetailTable").deleteRow(rowdelete);
						    }
					     }
	 	        	 	
	 	        		
						for(ik = 1; ik < lengthDetail; ik++){
							
							addDetailRowForQuery(msg.dtl_data[ik].ma_chuong, msg.dtl_data[ik].ma_ndkt, msg.dtl_data[ik].noi_dung,toFormatNumberDe_TCS(msg.dtl_data[ik].so_tien),
								toFormatNumberDe_TCS(msg.dtl_data[ik].tien));	
						}
	 	        	 	var tong_tien = 0;
						for(count_detail=0;count_detail<lengthDetail;count_detail++){
						 	try{						 		
						 		tong_tien = tong_tien + parseFloat(toNumber2(msg.dtl_data[count_detail].so_tien));
						 	}catch(e){
						 		alert(e);
						 	}
						 }
						
						 document.getElementById('id_tong_tien_nt').value=toFormatNumberDe_TCS(tong_tien);					 
						 document.getElementById('id_tong_tien').value=toFormatNumberDe_TCS(tong_tien);	
		        	
	        		
		         }         
			   }else {				  
				   if(nguon_temp == '1' || nguon_temp == '2') {
					   document.getElementById('id_cq_thu').readOnly = false;
					   document.getElementById('id_cq_thu').style.backgroundColor = "#FFFFFF";
				   }
			   }
			   
			     document.forms[0].ma_hq_ph.value = ma_hq_ph;
			     document.forms[0].ma_hq.value = ma_hq;
				 document.forms[0].nguon_dl.value = msg.nguon_dl;
				 document.forms[0].dc_nnthue.focus();
		   }    			
		 });		   
}
    
    
      // Lay ti gia theo Ma Nguyen Te
     function Retrieve_Tygia()
     {	       
    	  var ma_nt = document.forms[0].ma_nt.value;	
    	  if(isNull(ma_nt)) return;
    	  if(ma_nt.length != 3) return;
     	
    	  jQuery.ajax({
 		   type: "POST",
 		   url: "GetTygiaFromMNT.do",			  
 		   data: "ma_nte="+ma_nt,
 		   success: function(msg){	
 			     if(msg == null ) 
		    	 { 			    	 
 			    	 alert("Không có tỉ giá tương ứng vơi mã ngoại tệ bạn ch�?n. Liên hệ quản trị Kho bạc");
 			    	 document.forms[0].ty_gia.value = 0;
			    	 cal_tinhtien();
		    	 	 return;
		    	 }
 			     if(msg.ty_gia == null) 
		    	 {
 			    	 alert("Không có tỉ giá tương ứng vơi mã ngoại tệ bạn ch�?n. Liên hệ quản trị Kho bạc");
 			    	 document.forms[0].ty_gia.value = 0;
 			    	 cal_tinhtien();
		    	 	 return;
		    	 }
 			     ty_gia = msg.ty_gia; 			     
 			     var index1 =  ty_gia.indexOf('.'); 			     
 			     if(index1 >= 0) { 			    	 
 			    	 ty_gia = ty_gia.replace('.',''); 			    	
 			     }
 		         document.forms[0].ty_gia.value = toFormatNumberDe_TCS(ty_gia); 		        
 		         cal_tinhtien();
 		   }
 		 });		   
     
     }
        
    function strStartsWith(str, prefix) {
    	return str.indexOf(prefix) === 0;
	}    
	
	 function checkTaiKhoan(field){
		 var hthuc_thu = document.forms[0].hthuc_thu.value;
		 if(hthuc_thu==1){
			var field_value=field.value.trim();
			if (field_value.length>0){
				if(field_value.length!=13){
						alert("Số tài khoản phải có 13 ký tự số VD: 4567890123"); 
			            field.focus();
				}
			}else
				return;
		}
	}
  
	
	function CheckDateOnClient(type){		
		if(type=='ngay_qd'){
			var ngay_qd = document.forms[0].ngay_qd.value;			
			if(isDate(ngay_qd)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_qd,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày quyết định không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_qd.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_bke'){
			var ngay_bke = document.forms[0].ngay_bke.value;			
			if(isDate(ngay_bke)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_bke,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày bảng kê không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_bke.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_dk'){
			var ngay_dk = document.forms[0].ngay_dk.value;			
			if(isDate(ngay_dk)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_dk+"&"+
					   "flagNgayDK="+1,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày đăng ký không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_dk.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_ct'){
			var ngay_ct = document.forms[0].ngay_ct.value;			
			if(isDate(ngay_ct)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_ct,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày chứng từ không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_ct.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_kh_nh'){
			var ngay_kh_nh = document.forms[0].ngay_kh_nh.value;			
			if(isDate(ngay_kh_nh)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_kh_nh,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày chuyển khoản không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_kh_nh.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_nntien'){
			var ngay_nntien = document.forms[0].ngay_nntien.value;			
			if(isDate(ngay_nntien)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_nntien,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày nộp thuế không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_nntien.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		if(type=='ngay_kb_nh'){
			var ngay_kb_nh = document.forms[0].ngay_kb_nh.value;			
			if(isDate(ngay_kb_nh)){
				 jQuery.ajax({
					   type: "POST",
					   url: "CheckNgayHeThong.do",			  
					   data: "ngay_checked="+ngay_kb_nh,
					   success: function(msg){				   	
					        if(msg.check == true) {
					        	alert("Ngày Kho bạc - Ngân hàng không được lớn hơn ngày hiện tại");
					         	document.forms[0].ngay_kb_nh.focus();
					         	return;
					        }
					   }
					 });
			}	
			else return;
		}
		  	   
	}
      
	function sbTruyVanTheoLoaiThue04(is_have_haiquan){
		if(is_have_haiquan==1){
			sbTruyVanHaiQuan();
		}else{			
			sbTruyVanDB();
		}
	}
	function sbTruyVanDB(lamviec_hq){						
			var ma_thue =  document.forms[0].ma_thue.value;
			var ma_cqthu = document.forms[0].cq_thu.value;		
			var tk_so = document.forms[0].tkhai_so.value;	
			var ngay_tk = document.forms[0].ngay_dk.value;				
			
		    if(lamviec_hq == '1')
		    {		    	
		    	return;
		    } 
       	 	var tbl = document.getElementById('tblGridDetailTable');
        	var lastRow = tbl.rows.length;
        	
        	// /if(lastRow>=3){
        		// for(removeDetailRow=0;removeDetailRow<lastRow-2;removeDetailRow++){l
        			// document.all("tblGridDetailTable").deleteRow(lastRow);
        			 // }
        	// }
			
			if(isNull(ma_thue)){
				// alert("Bạn phải nhập mã số thuế");
		        // document.forms[0].ma_thue.focus();
		        return;		
			}			
			if(isNull(ma_cqthu)){
				// alert("Bạn phải nhập mã cơ quan thu");
		        // document.forms[0].cq_thu.focus();
		        return;
			}			
			if(isNull(tk_so)){
				// alert("Số t�? khai không được để trống");
		        // document.forms[0].tkhai_so.focus();
		        return;
			}			
			if(isNull(ngay_tk)){
				// alert("Ngày t�? khai không được để trống");
		        // document.forms[0].ngay_dk.focus();
		        return;
			}			
			else{
				var objJSON = {
           			"msg" : document.getElementById('id_ma_thue').value+"@"+							
							$('id_cq_thu').value+"@"+							
							document.getElementById('id_tkhai_so_1').value+"@"+
							document.getElementById('id_ngay_dk_1').value							
          		};
 				var strJSON = encodeURIComponent(JSON.stringify(objJSON));
 				new Ajax.Request("truyvanDB.do",
			        {
			          method: "post",
			          async: false,
			          parameters: "strJSON=" + strJSON,
			          onSuccess: onSuccess,
			          onError: onError,
			          onLoading: onLoading					 
			        }
				);
		 		function onLoading(){
		         	document.getElementById('indicator').style.display = "none";                     
		     	}
		     	function onError(){
		         	alert("Error");
		     	}		     	
			    function onSuccess(req){ 
					 var ws_db = new Array();      	
			         document.getElementById('indicator').style.display = "none";        
			         var jsonExpression = "(" + req.responseText + ")";        
			         ws_db = eval(jsonExpression);		
			         var detail_length = ws_db.p_cur.length;
					 var tong_tien=0;
					 // XU LY CONG TIEN
					 				 
					 if(detail_length==0)
					 {					 	
					 	for(mot=lastRow-1;mot>=2;mot--){
					 		document.all("tblGridDetailTable").deleteRow(mot);
					 	}
					 	document.getElementById('id_chuong_row1').value="";
						document.getElementById('id_nganhkt_row1').value="";
						document.getElementById('id_noidungkt_row1').value="";
						document.getElementById('id_noidung_row1').value="";
						document.getElementById('id_tien_row1').value="0";
						document.getElementById('id_tien_nt_row1').value="0"
						document.getElementById('id_tong_tien_nt').value="0";					 
					 	document.getElementById('id_tong_tien').value="0";	
						return;
                     }
                     else
                     {
                     	if(detail_length==1){
							document.getElementById('id_chuong_row1').value=ws_db.p_cur[0].ma_chuong;	
							document.getElementById('id_nganhkt_row1').value=ws_db.p_cur[0].ma_nkt;					 
							document.getElementById('id_noidungkt_row1').value=ws_db.p_cur[0].ma_ndkt;					 
							document.getElementById('id_noidung_row1').value=ws_db.p_cur[0].ten_ndkt;					 
							document.getElementById('id_tien_row1').value=toFormatNumberDe_TCS(ws_db.p_cur[0].so_phainop);					 
							document.getElementById('id_tien_nt_row1').value=toFormatNumberDe_TCS(ws_db.p_cur[0].so_phainop);
						}else{
							document.getElementById('id_chuong_row1').value=ws_db.p_cur[0].ma_chuong;	
							document.getElementById('id_nganhkt_row1').value=ws_db.p_cur[0].ma_nkt;					 
							document.getElementById('id_noidungkt_row1').value=ws_db.p_cur[0].ma_ndkt;					 
							document.getElementById('id_noidung_row1').value=ws_db.p_cur[0].ten_ndkt;					 
							document.getElementById('id_tien_row1').value=toFormatNumberDe_TCS(ws_db.p_cur[0].so_phainop);					 
							document.getElementById('id_tien_nt_row1').value=toFormatNumberDe_TCS(ws_db.p_cur[0].so_phainop);		
							
							if(lastRow>2){
								for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){
								document.all("tblGridDetailTable").deleteRow(rowdelete);
								}
							}				
							for(rs=1;rs<detail_length;rs++){
								addDetailRowForQuery(ws_db.p_cur[rs].ma_chuong, ws_db.p_cur[rs].ma_nkt, ws_db.p_cur[rs].ma_ndkt,
									ws_db.p_cur[rs].ten_ndkt, ws_db.p_cur[rs].so_phainop,ws_db.p_cur[rs].so_phainop)
							}							
						}
					 			 
					 }					 
					 for(count_detail=0;count_detail<detail_length;count_detail++){
					 	try{
					 		tong_tien = tong_tien + parseFloat(toNumber2(ws_db.p_cur[count_detail].so_phainop));
					 	}catch(e){
					 		alert(e);
					 	}
					 }
					 
					 document.getElementById('id_tong_tien_nt').value = toFormatNumberDe_TCS(ReplaceAll(tong_tien,' ',''));					 
					 document.getElementById('id_tong_tien').value = toFormatNumberDe_TCS(ReplaceAll(tong_tien, ' ',''));	
				}				
			}
		}
	function sbTruyVanHaiQuan(){			
			var tk_so = document.forms[0].tkhai_so.value;	
			var ngay_tk = document.forms[0].ngay_dk.value;
			var ma_thue =  document.forms[0].ma_thue.value;
			var ten_thue = document.forms[0].ten_thue.value;
			var ma_cqthu = document.forms[0].cq_thu.value;
			var ten_cqthu = document.forms[0].tcq_thu.value;
			var lh_xnk = document.forms[0].lh_xnk.value;
			var tlh_xnk = document.forms[0].tlh_xnk.value;			
			var kbnn_thu = document.forms[0].kbnn_thu.value;			
			
			
			var tbl = document.getElementById('tblGridDetailTable');
        	var lastRow = tbl.rows.length;
			var tong_tien = 0;
			if(isNull(ma_thue)){
				alert("Bạn phải nhập mã số thuế");
		        document.forms[0].ma_thue.focus();
		        return;		
			}/*
				 * if(isNull(ten_thue)){ alert("Bạn phải nhập tên thuế");
				 * document.forms[0].ten_thue.focus(); return; }
				 */
			if(isNull(ma_cqthu)){
				alert("Bạn phải nhập mã cơ quan thu");
		        document.forms[0].cq_thu.focus();
		        return;
			}/*
				 * if(isNull(ten_cqthu)){ alert("Tên cơ quan thu không được để
				 * trống. Bạn hãy nhập lại mã cơ quan thu");
				 * document.forms[0].cq_thu.focus(); return; }
				 */
			if(isNull(tk_so)){
				alert("Số t�? khai không được để trống");
		        document.forms[0].tkhai_so.focus();
		        return;
			}			
			if(isNull(ngay_tk)){
				alert("Ngày t�? khai không được để trống");
		        document.forms[0].ngay_dk.focus();
		        return;
			}
			if(isNull(lh_xnk)){
				alert("Bạn phải nhập tên viết tắt loại hình xuất nhập khẩu");
		        document.forms[0].lh_xnk.focus();
		        return;
			}
			if(isNull(tlh_xnk)){
				alert("Tên loại hình XNK không được để trống. Bạn phải nhập lại tên viết tắt");
		        document.forms[0].lh_xnk.focus();
		        return;
			}
			else{
				var objJSON = {
           			"msg" : document.getElementById('id_ma_thue').value+"@"+
							document.getElementById('id_ten_thue').value+"@"+
							document.getElementById('id_cq_thu').value+"@"+
							document.getElementById('id_tcq_thu').value+"@"+
							document.getElementById('id_tkhai_so_1').value+"@"+
							document.getElementById('id_ngay_dk_1').value+"@"+
							document.getElementById('id_1_tlh_xnk').value+"@"+
							kbnn_thu
          		};
 				var strJSON = encodeURIComponent(JSON.stringify(objJSON));
 				new Ajax.Request("truyvanHq.do",
			        {
			          method: "post",
			          async: false,
			          parameters: "strJSON=" + strJSON,
			          onSuccess: onSuccess,
			          onError: onError,
			          onLoading: onLoading					 
			        }
				);
		 		function onLoading(){
		         document.getElementById('indicator').style.display = "";                     
		     	}
		     	function onError(){
		         alert("Error");
		     	}		     	
			    function onSuccess(req){ 
					 var ws_hq = new Array();      	
			         document.getElementById('indicator').style.display = "none";        
			         var jsonExpression = "(" + req.responseText + ")";        
			         ws_hq = eval(jsonExpression);
			         /**
						 * set du lieu header
						 */
					 document.forms[0].cq_thu.value  = ws_hq.hdr_data.ma_hq_ph;
					 document.forms[0].ma_hq_ph.value  = ws_hq.hdr_data.cq_thu;
			         var detail_length = ws_hq.dtl_data.length;			         
					
					if(detail_length==1){
						document.getElementById('id_chuong_row1').value=ws_hq.dtl_data[0].chuong;
						document.getElementById('id_nganhkt_row1').value=ws_hq.dtl_data[0].nkt;
						document.getElementById('id_noidungkt_row1').value=ws_hq.dtl_data[0].ndkt;
						document.getElementById('id_noidung_row1').value="";
						document.getElementById('id_tien_nt_row1').value=toFormatNumberDe_TCS(ws_hq.dtl_data[0].tien);					
						document.getElementById('id_tien_row1').value=toFormatNumberDe_TCS(ws_hq.dtl_data[0].tien);									
					}else{
						document.getElementById('id_chuong_row1').value=ws_hq.dtl_data[0].chuong;
						document.getElementById('id_nganhkt_row1').value=ws_hq.dtl_data[0].nkt;
						document.getElementById('id_noidungkt_row1').value=ws_hq.dtl_data[0].ndkt;
						document.getElementById('id_noidung_row1').value="";
						document.getElementById('id_tien_nt_row1').value=toFormatNumberDe_TCS(ws_hq.dtl_data[0].tien);					
						document.getElementById('id_tien_row1').value=toFormatNumberDe_TCS(ws_hq.dtl_data[0].tien);						
						
						if(lastRow>2){
							for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){
							document.all("tblGridDetailTable").deleteRow(rowdelete);
							}
						}
								
						
						for(ik=1;ik<detail_length;ik++){
							
							addDetailRowForQuery(ws_hq.dtl_data[ik].chuong,ws_hq.dtl_data[ik].nkt,ws_hq.dtl_data[ik].ndkt,'',toFormatNumberDe_TCS(ws_hq.dtl_data[ik].tien),
								toFormatNumberDe_TCS(ws_hq.dtl_data[ik].tien));	
						}
						
						
					}
					 for(count_detail=0;count_detail<detail_length;count_detail++){					 	
					 	try
					 		{			
					 			var tongtien_temp = toNumber2(ws_hq.dtl_data[count_detail].tien);						 					 		
					 			tong_tien += parseFloat(tongtien_temp);							 				 		
					 		}
					 	catch(e){
					 		alert(e);
					 	}
					 }
					 
					 document.getElementById('id_tong_tien_nt').value=tong_tien;					 
					 document.getElementById('id_tong_tien').value=tong_tien;	
					
		     	}				
			}
		  }	
		 
	 function LoadMa_Ten_LHXNK(){
		   jQuery.ajax({
			   type: "POST",
			   url: "LoadMa_Ten_LHXNK.do",			  
			   data: "tvt_lhxnk="+document.forms[0].lh_xnk.value,
			   success: function(msg){				   	
				     if(msg ==null ) return;
			         document.forms[0].tlh_xnk.value = msg.ten;
			   }
			 });		   
	}
	function cal_noidung(index) {		
		if(index > 0) {
			var ma_ndkt = document.forms[0].ttct_noidungkt[index].value;
			if(ma_ndkt.length != 4) return;
			jQuery.ajax({
				   type: "POST",
				   url: "LoadNoidungKinhTe.do",			  
				   data: "ma_ndkt="+ma_ndkt,
				   success: function(msg){			
					     if(msg == null ) return;
				         if(msg.ten == null) document.forms[0].ttct_noidung[index].value = "";
				         else document.forms[0].ttct_noidung[index].value = msg.ten;
				   }
				 });	
		}else{
			try{
				var ma_ndkt = document.forms[0].ttct_noidungkt[index].value;
				if(ma_ndkt.length != 4) return;
				jQuery.ajax({
					   type: "POST",
					   url: "LoadNoidungKinhTe.do",			  
					   data: "ma_ndkt="+ma_ndkt,
					   success: function(msg){		
						     if(msg == null ) return;
					         if(msg.ten == null) document.forms[0].ttct_noidung[index].value = "";
					         else document.forms[0].ttct_noidung[index].value = msg.ten;
					   }
					 });
			}catch (e) {
				var ma_ndkt = document.forms[0].ttct_noidungkt.value;				
				if(ma_ndkt.length != 4) return;
				jQuery.ajax({
					   type: "POST",
					   url: "LoadNoidungKinhTe.do",			  
					   data: "ma_ndkt="+ma_ndkt,
					   success: function(msg){		
						     if(msg == null ) return;
					         if(msg.ten == null) document.forms[0].ttct_noidung.value = "";
					         else document.forms[0].ttct_noidung.value = msg.ten;
					   }
					 });
			}
		}
	}
	    /**
		 * Ham tinh so tien theo tung dong chi tiet
		 * 
		 * @param index
		 */
	    function cal_sotien(index){    	
			
			var type_event = 'active_onblur';
			var SO_TIEN_MAX = '999999999999';
			
	   		if(index>0)
	   		{   		
	   		    var ty_gia=document.forms[0].ty_gia.value;            
	            var tiennt=document.forms[0].ttct_tien_nt[index].value;
            	
		        var sotien;
			    if(isNull(ty_gia)){		            
		             document.forms[0].ty_gia.value = '1';
		             document.forms[0].ttct_tien[index].value=0;
		             document.forms[0].ttct_tien_nt[index].value='';
		        }
		        if(isNull(tiennt)){
		             document.forms[0].ttct_tien[index].value=0;       
		             document.forms[0].ttct_tien_nt[index].value='';           
		        }
		        tiennt = toNumber2(tiennt);			        
		        tiennt = tiennt.replace(",",".");			        
		        sotien = toNumber2(ty_gia)*tiennt;				        
		        sotien = Math.round(sotien);
		        
		        if(sotien>=SO_TIEN_MAX){
					msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
 					document.forms[0].ttct_tien[index].value=toFormatNumberDe_TCS(toNumber1(sotien),2);
					if(type_event=='active_onblur')
							showError(msgInvalidToLager_TienNT);
					document.forms[0].ttct_tien_nt[index].focus();
					return;
			     } 
					        
				document.forms[0].ttct_tien[index].value=toFormatNumberDe_TCS(toNumber1(sotien),2);   
		    }else{	    
		    	 try{	    	
		    	 	// dong dau tien cua danh sach
	                var ty_gia=document.forms[0].ty_gia.value;            
	            	var tiennt=document.forms[0].ttct_tien_nt[index].value;        
	            	
	               	if(isNull(ty_gia)){
	               		 document.forms[0].ty_gia.value = '1';
			             document.forms[0].ttct_tien[index].value=0;
			             document.forms[0].ttct_tien_nt[index].value='';
		        	}
			        if(isNull(tiennt)){
			             document.forms[0].ttct_tien[index].value=0;       
			             document.forms[0].ttct_tien_nt[index].value='';           
			        }	           
			        tiennt = toNumber2(tiennt);			        
			        tiennt = tiennt.replace(",",".");			        
			        sotien=toNumber2(ty_gia)*tiennt;				        
			        sotien = Math.round(sotien);
			        
			        if(sotien>=SO_TIEN_MAX){
			        	msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
	 					document.forms[0].ttct_tien[index].value=toFormatNumberDe_TCS(toNumber1(sotien),2);
						if(type_event=='active_onblur')
								showError(msgInvalidToLager_TienNT);
						document.forms[0].ttct_tien_nt[index].focus();
						return;
				     } 
			        
							           
			        document.forms[0].ttct_tien[index].value=toFormatNumberDe_TCS(toNumber1(sotien),2);   
		    	 } catch(e) {	    	 
		    	 	// Chi co 1 dong
	                var ty_gia=document.forms[0].ty_gia.value;            
	            	var tiennt=document.forms[0].ttct_tien_nt.value; 
	            	
	                if(isNull(ty_gia)){
	                	 document.forms[0].ty_gia.value = '1';
			             document.forms[0].ttct_tien.value=0;
		        	}
			        if(isNull(tiennt)){
			             document.forms[0].ttct_tien.value=0;       
			             document.forms[0].ttct_tien_nt.value='';           
			        }			      
			       
			        tiennt = toNumber2(tiennt);			        
			        tiennt = tiennt.replace(",",".");			        
			        sotien=toNumber2(ty_gia)*tiennt;				        
			        sotien = Math.round(sotien);
			        if(sotien>=SO_TIEN_MAX){
			        	msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
						document.forms[0].ttct_tien.value=toFormatNumberDe_TCS(toNumber1(sotien),2); 
						if(type_event=='active_onblur')
							showError(msgInvalidToLager_TienNT);
						document.forms[0].ttct_tien_nt.focus();
						return;
					}	        
					document.forms[0].ttct_tien.value=toFormatNumberDe_TCS(toNumber1(sotien),2); 
		    	 }
		    	 
		    	
		    }	    
	    }	    
	     // Tinh tong kinh phi=sum(sotien cua tat ca cac dong)
	    function cal_tongtien(){	    	
	    	var i;
	    	var TONG_TIEN_MAX = '999999999999';
	        var tbl = document.getElementById('tblGridDetailTable');  	       
	        var rowOnTable = tbl.rows.length-1;                
	        if(rowOnTable > 1)
	        {	        	
	            var tong_tien = 0;
	            for(i = 0; i < rowOnTable; i++) 
	            {
	                var sotien=document.forms[0].ttct_tien[i].value;              
	                if(parseFloat(toNumber2(sotien)) < 0)
	                {     	                	
	                	var sotienTemp = parseFloat(toNumber2(sotien));						
						tong_tien += sotienTemp;
	                }
	                else
	                {	                	
						var sotienTemp = parseFloat(toNumber2(sotien));
						if(sotienTemp > TONG_TIEN_MAX)
						{
							document.forms[0].tong_tien.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);
							return;							
							
						}
						tong_tien += sotienTemp;
	                }
	             }                 
	            try {	            	
	            	tong_tien = Math.round(toNumber2(tong_tien));	
	            }catch(e) {	            	
	            	tong_tien = 0;
	            }
	            if(tong_tien >= TONG_TIEN_MAX){
					msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
					document.forms[0].tong_tien.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);								
					document.forms[0].ttct_tien_nt[0].focus();
					return;
				}	    
	           
	            if(tong_tien == null || tong_tien == "" || isNaN(tong_tien)) tong_tien = 0;	     	            
	            
	            if(tong_tien == 0)
	            	document.forms[0].tong_tien.value = 0;
	            else
	            	document.forms[0].tong_tien.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);
	        }
	        else
	        {	        	
	        	var tong_tien = document.getElementsByName('ttct_tien')[0].value;	        
	        	try {
	        		tong_tien = Math.round(toNumber2(tong_tien));			
	        	}catch (e) {	        		
	        		tong_tien = 0;
				}
	        	  
	            if(tong_tien >= TONG_TIEN_MAX){
	            	msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
					document.forms[0].tong_tien.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);										
					document.forms[0].ttct_tien_nt.focus();
					return;
				}
	            if(tong_tien == null || tong_tien == "" || isNaN(tong_tien)) 
            	{	            	
            		tong_tien = 0;
            	}	            
	           
	            if(tong_tien == 0)
	            	document.forms[0].tong_tien.value = 0;
	            else
	            	document.forms[0].tong_tien.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);    
	        }
	    }
	    function cal_tongtien_nt()
	    {
	    	var i;
	    	var TONG_TIEN_MAX = '999999999999';
	        var tbl = document.getElementById('tblGridDetailTable');  	       
	        var rowOnTable = tbl.rows.length-1;           	
	        if(rowOnTable > 1)
			{
	        	    var tongTienNTe = 0;
		            for(i = 0; i < rowOnTable; i++) 
		            {
		                var sotien=document.forms[0].ttct_tien_nt[i].value; 
		                sotien = ReplaceAll(sotien, ' ', '');
		                if(parseFloat(toNumber2(sotien)) < 0)
		                {     	                	
		                	var sotienTemp = parseFloat(toNumber2(sotien));						
		                	tongTienNTe += sotienTemp;
		                }
		                else
		                {	                	
							var sotienTemp = parseFloat(toNumber2(sotien));							
							if(sotienTemp > TONG_TIEN_MAX)
							{
								document.forms[0].tong_tien_nt.value=toFormatNumberDe_TCS(toNumber1(tongTienNTe),2);
								return;							
								
							}
							tongTienNTe += sotienTemp;
		                }
		             } 
		            if(tongTienNTe >= TONG_TIEN_MAX){
						msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
						document.forms[0].ttct_tien_nt.value=toFormatNumberDe_TCS(toNumber1(tongTienNTe),2);								
						document.forms[0].ttct_tien_nt[0].focus();
						return;
					}	    
		           
		            if(tongTienNTe == null || tongTienNTe == "" || isNaN(tongTienNTe)) 
		            	{
		            		tongTienNTe = 0;	  
		            		document.forms[0].tong_tien_nt.value=0;
		            	}
		            else{
		            	tongTienNTe = tongTienNTe.toString();
		            	document.forms[0].tong_tien_nt.value=formatNumberTCS(tongTienNTe);
		            }
		           
		            
	        }else{	 
				var tongTienNTe = document.getElementsByName('ttct_tien_nt')[0].value;				
				tongTienNTe = ReplaceAll(tongTienNTe, ' ', '');
	            if(tongTienNTe >= TONG_TIEN_MAX){
	            	msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
					document.forms[0].ttct_tien_nt.value=toFormatNumberDe_TCS(toNumber1(tong_tien),2);										
					document.forms[0].ttct_tien_nt.focus();
					return;
				}	            
	            if(tongTienNTe == null || tongTienNTe == "" || isNaN(tongTienNTe)) 
            	{	            	
	            	tongTienNTe = 0;
	            	document.forms[0].tong_tien_nt.value=0;
            	}else{
            		document.forms[0].tong_tien_nt.value=formatNumberTCS(tongTienNTe);
            	}	       
				
	        }
	    }
	     function cal_tinhtien(){
	    	var tbl = document.getElementById('tblGridDetailTable');    	
	    	var sotien;
	        var lastRow = tbl.rows.length-1;         
	        var TONG_TIEN_MAX = '999999999999';
	        /**
			 * Tinh so tien dong detail truoc
			 */
	   		if(lastRow == 1)
	   		{	   			   			
	   			var tiennt = document.getElementsByName('ttct_tien_nt')[0].value;	
				var ty_gia = document.forms[0].ty_gia.value;					
	   			sotien = toNumber2(ty_gia)*toNumber2(tiennt);
	   			sotien = Math.round(toNumber2(sotien));
		        document.forms[0].ttct_tien.value = toFormatNumberDe_TCS(toNumber1(sotien),2);      			 
	   		}
	   		else
	   		{   		   			
	   			var i;
	   			for( i = 0; i < lastRow; i++)
	   			{							
	   				var tiennt = document.forms[0].ttct_tien_nt[i].value;	
					var ty_gia = document.forms[0].ty_gia.value;		                
	   				sotien = toNumber2(ty_gia) * toNumber2(tiennt);				
	   				sotien = Math.round(toNumber2(sotien));								
	   				document.forms[0].ttct_tien[i].value = toFormatNumberDe_TCS(toNumber1(sotien),2);								
	   			}
	   		}
	   		/**
			 * Tinh tong tien
			 */			
			if(lastRow > 1){
					var i;
				 	var tong_tien = 0;
				  	for( i = 0; i < lastRow; i++)
					{
						 var sotien_tt=document.forms[0].ttct_tien[i].value; 
						 if(parseFloat(toNumber2(sotien_tt))<0){     
							 var tempTongTien = parseFloat(toNumber2(sotien_tt));
							 tong_tien += tempTongTien;
						 }
						 else{
								var tempTongTien = parseFloat(toNumber2(sotien_tt));
								if(tempTongTien > TONG_TIEN_MAX) {	
									msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");							
									document.forms[0].tong_tien.value = toFormatNumberDe_TCS(toNumber1(tempTongTien),2);								
									showError(msgInvalidToLager_TienNT);	
									document.forms[0].ttct_tien_nt[tong_tt].focus();	
									return;
								}
	 							tong_tien += tempTongTien;
	 							
						 }	
					}
					tong_tien = Math.round(toNumber2(tong_tien));
					if(tong_tien >= TONG_TIEN_MAX)
					{
						msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
						document.forms[0].tong_tien.value = toFormatNumberDe_TCS(toNumber1(tong_tien),2);
						showError(msgInvalidToLager_TienNT);					
						document.forms[0].ttct_tien_nt[0].focus();
						return;
					}
					document.forms[0].tong_tien.value = toFormatNumberDe_TCS(toNumber1(tong_tien),2);
			} else {				 	
				 	var tong_tien = document.getElementsByName('ttct_tien')[0].value;	
					tong_tien = Math.round(toNumber2(tong_tien)); 
					if(tong_tien >= TONG_TIEN_MAX)
					{
						msgInvalidToLager_TienNT = msgInvalidToLager_TienNT.replace("?","999 999 999 999 VND");
						document.forms[0].tong_tien.value = toFormatNumberDe_TCS(toNumber1(tong_tien),2);								
						document.forms[0].ttct_tien_nt.focus();
						return;
					}
					document.forms[0].tong_tien.value = toFormatNumberDe_TCS(toNumber1(tong_tien),2);
			}
	   }

/**
 * Mot so ham dungtv10 them
 */
function Load_Dm_TaiKhoan(){
	   jQuery.ajax({
		   type: "POST",
 url: "Load_TK_NganSach.do",
		   success: function(msg){
			   if(msg == null ) return;
			   for(i=0;i<temp.length;i++){temp[i]=null;}
			    for(i=0;i<msg.length;i++){
		        	temp[i]=msg[i];
		        }	
		   }
		 });
}
function LoadMa_DBHC(){
	   
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMa_DBHC.do",		  
		   success: function(msg){
			   if(msg == null ) return;
			   for(i=0;i<temp_DBHC.length;i++){
		     		temp_DBHC[i]=null;
		     	}     
		     	for(i=0;i<msg.length;i++){
	        		temp_DBHC[i]=msg[i];
	         	}	  
		   }
		 });
	   
	  
}
function LoadMa_CQThu(){
	   var loai_thue = document.forms[0].loai_thue.value;
	  
	   if(isNull(loai_thue)){
		   
		   return;
	   }
	   else
	   {  
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMa_CQThu.do",		  
		   data: "ma_lthue="+document.forms[0].loai_thue.value,		
		   success: function(msg){		   
			   
			   if(msg == null ) return;
			   for(i=0;i<temp_CQTHU.length;i++){
		        	temp_CQTHU[i]=null;
		         }  
			     for(i=0;i<msg.length;i++){
		        	temp_CQTHU[i]=msg[i];
		         }	 
		   }
		 });
	   }
	   
	  
}
function LoadDMNganHang(){
	   
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMa_NH.do",		  
		   success: function(msg){			   
			   if(msg == null ) return;
			   for(i=0; i < listMaNganHang.length; i++){
		        	listMaNganHang[i] = null;
		         }  
			     for(i=0; i < msg.length; i++){
			    	 listMaNganHang[i] = msg[i];
		         }	 
		   }
		 });
	   
	  
}
function LoadDVSDNS(){
	   
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadDVSDNS.do",		  
		   success: function(msg){			   
			   if(msg == null ) return;
			   for(i=0; i < listMaDvsdns.length; i++){
				   listMaDvsdns[i] = null;
		         }  
			     for(i=0; i < msg.length; i++){
			    	 listMaDvsdns[i] = msg[i];
		         }	 
		   }
		 });
	   
	  
}

function Load_LoaiThue(){ 
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadLThue.do",		  
		   success: function(msg){	
			     if(msg ==null ) return;
		         for(i=0;i<msg.length;i++){
		        	temp_LThue[i]=msg[i];
		         }
		   }
		 });		   
}
function Load_MaNT(){ 
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMaNT.do",			  
		   success: function(msg){	
			     if(msg ==null ) return;
		         for(i=0;i<msg.length;i++){
		        	 temp_NTe[i]=msg[i];
		         }
		   }
		 });		   
}
function Load_LHXNK(){ 
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMa_LHXNK.do",			  
		   success: function(msg){				   
			     if(msg == null ) return;			    
			   
		         for(i = 0;i < msg.length; i++){
		        	
		        	 listLhxnk[i] = msg[i];
		         }
		   }
		 });		   
}
function LoadMa_Ten_LHXNK(){
	   jQuery.ajax({
		   type: "POST",
		   url: "LoadMa_Ten_LHXNK.do",			  
		   data: "tvt_lhxnk="+document.forms[0].lh_xnk.value,
		   success: function(msg){	
			     if(msg ==null ) return;
		         document.forms[0].tlh_xnk.value = msg.ten;
		   }
		 });		   
}
function Testrff() {
	
}
function LoadTenThue() {		
	 jQuery.ajax({
		   type: "POST",
		   url: "LoadTen_Thue.do",			  
		   data: "ma_lthue="+document.forms[0].loai_thue.value,
		   success: function(msg){	
			     if(msg ==null ) 
			     {
			    	 return;
			     }			    	
		         document.forms[0].tloai_thue.value = msg.ten;
		   }
		 });	
}
function getKhoquyInfo(txtMaNV, strdatabaseurlbds, strdatabaseurlbdsvb, strservername, strtimesocklocalportno, strtimesockserverportno, strtimesockserverhostip)
{
	 var strHtmlApplet = "";
	
	 strHtmlApplet += "<param name='Host_Prop' VALUE='";
	 strHtmlApplet += "	<database.driver>org.gjt.mm.mysql.Driver</database.driver>";
	 strHtmlApplet += "	<database.driver>org.gjt.mm.mysql.Driver</database.driver>";
	 strHtmlApplet += "	<database.urlbds>jdbc:mysql://" + strdatabaseurlbds + "</database.urlbds>";
	 strHtmlApplet += "	<database.urlbdsvb>jdbc:mysql://" + strdatabaseurlbdsvb + "<database.urlbdsvb>";
	 strHtmlApplet += "	<server.servername>" + strservername + "<server.servername>";
	 strHtmlApplet += "	<sna.cicsname>SFS<sna.cicsname>";
	 strHtmlApplet += "	<sna.luname>JAVT<sna.luname>";
	 strHtmlApplet += "	<settings.Timeout_Open>15<settings.Timeout_Open>";
	 strHtmlApplet += "	<settings.timeout>300<settings.timeout>";
	 strHtmlApplet += "	<svs.displayflag>0<svs.displayflag>";
	 strHtmlApplet += "	<svs.svsindicator>0<svs.svsindicator>";
	 strHtmlApplet += "	<timesocklocal.portno>" + strtimesocklocalportno + "<timesocklocal.portno>";
	 strHtmlApplet += "	<timesockserver.portno>" + strtimesockserverportno + "<timesockserver.portno>";
	 strHtmlApplet += "	<timesockserver.hostip>" + strtimesockserverhostip + "<timesockserver.hostip>";
	 strHtmlApplet += "' />";
	strHtmlApplet += "</applet>";	
			
	divApplet.innerHTML = strHtmlApplet;
	return document.bdsapp.get(txtMaNV);		
}  
function fncDisplayCom() {
	document.getElementById("id_cq_thu").focus();
	document.getElementById("id_loai_thue").readOnly = true;
	document.getElementById("id_loai_thue").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_thue").readOnly = true;
	document.getElementById("id_ma_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_ten_thue").readOnly = true;
	document.getElementById("id_ten_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_lov_mst").style.display = "none";
	var oLink = document.getElementById('id_ma_thue');		
	oLink.onblur = null;
	var oLink1 = document.getElementById("id_khtk_row1");	
	oLink1.onkeydown = null;
	
	document.getElementById("id_dc_nnthue").readOnly = true;
	document.getElementById("id_dc_nnthue").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dc_nnthue_huyen").readOnly = true;
	document.getElementById("id_dc_nnthue_huyen").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dc_nnthue_tinh").readOnly = true;
	document.getElementById("id_dc_nnthue_tinh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_nguoi_ntien").readOnly = true;
	document.getElementById("id_nguoi_ntien").style.backgroundColor = "#F0F0F0";
	var oLinkNNT = document.getElementById('id_nguoi_ntien');		
	oLinkNNT.onblur = null;
	
	document.getElementById("id_dc_nntien").readOnly = true;
	document.getElementById("id_dc_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_tkhai_so").readOnly = true;
	document.getElementById("id_tkhai_so").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_dk").readOnly = true;
	document.getElementById("id_ngay_dk").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_lh_xnk1").readOnly = true;
	document.getElementById("id_lh_xnk1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nt").readOnly = true;
	document.getElementById("id_ma_nt").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_qd").readOnly = true;
	document.getElementById("id_so_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_qd").readOnly = true;
	document.getElementById("id_ngay_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_dvsdns").readOnly = true;
	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_ct_nh").readOnly = true;
	document.getElementById("id_so_ct_nh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_ct_nh").readOnly = true;
	document.getElementById("id_so_ct_nh").style.backgroundColor = "#F0F0F0";
	
	//document.getElementById("id_ngay_kh_nh").readOnly = true;
	//document.getElementById("id_ngay_kh_nh").style.backgroundColor = "#F0F0F0";
	
	//document.getElementById("id_ngay_kb_nh").readOnly = true;
	//document.getElementById("id_ngay_kb_nh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_nntien").readOnly = true;
	document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ten_nt_nntien").readOnly = true;
	document.getElementById("id_ten_nt_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_a").readOnly = true;
	document.getElementById("id_ma_nh_a").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_tb").readOnly = true;
	document.getElementById("id_so_tb").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_khung").readOnly = true;
	document.getElementById("id_so_khung").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_may").readOnly = true;
	document.getElementById("id_so_may").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_bke").readOnly = true;
	document.getElementById("id_so_bke").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_bke").readOnly = true;
	document.getElementById("id_ngay_bke").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dien_giai").readOnly = true;
	document.getElementById("id_dien_giai").style.backgroundColor = "#F0F0F0";	
	
	document.getElementById("cmdAddDiadiem").style.display = "none";
	
	
}
function A() {
	document.getElementById("id_cq_thu").focus();
	document.getElementById("id_loai_thue").readOnly = true;
	document.getElementById("id_loai_thue").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_thue").readOnly = true;
	document.getElementById("id_ma_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_ten_thue").readOnly = true;
	document.getElementById("id_ten_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_lov_mst").style.display = "none";
	var oLink = document.getElementById('id_ma_thue');		
	var oLink1 = document.getElementById("id_khtk_row1");
	oLink.onblur = null;
	oLink1.onkeydown = null;
	
	document.getElementById("id_dc_nnthue").readOnly = true;
	document.getElementById("id_dc_nnthue").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dc_nnthue_huyen").readOnly = true;
	document.getElementById("id_dc_nnthue_huyen").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dc_nnthue_tinh").readOnly = true;
	document.getElementById("id_dc_nnthue_tinh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_nguoi_ntien").readOnly = true;
	document.getElementById("id_nguoi_ntien").style.backgroundColor = "#F0F0F0";
	
	
	document.getElementById("id_dc_nntien").readOnly = true;
	document.getElementById("id_dc_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_tkhai_so").readOnly = true;
	document.getElementById("id_tkhai_so").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_dk").readOnly = true;
	document.getElementById("id_ngay_dk").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_lh_xnk1").readOnly = true;
	document.getElementById("id_lh_xnk1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nt").readOnly = true;
	document.getElementById("id_ma_nt").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_qd").readOnly = true;
	document.getElementById("id_so_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_qd").readOnly = true;
	document.getElementById("id_ngay_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_dvsdns").readOnly = true;
	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#F0F0F0";	
	
	document.getElementById("id_so_ct_nh").readOnly = true;
	document.getElementById("id_so_ct_nh").style.backgroundColor = "#F0F0F0";
	
	//document.getElementById("id_ngay_kh_nh").readOnly = true;
	//document.getElementById("id_ngay_kh_nh").style.backgroundColor = "#F0F0F0";
	
	//document.getElementById("id_ngay_kb_nh").readOnly = true;
	//document.getElementById("id_ngay_kb_nh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_nntien").readOnly = true;
	document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ten_nt_nntien").readOnly = true;
	document.getElementById("id_ten_nt_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_a").readOnly = true;
	document.getElementById("id_ma_nh_a").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_tb1").readOnly = true;
	document.getElementById("id_so_tb1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_khung1").readOnly = true;
	document.getElementById("id_so_khung1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_may1").readOnly = true;
	document.getElementById("id_so_may1").style.backgroundColor = "#F0F0F0";
	
	
	document.getElementById("id_so_bke").readOnly = true;
	document.getElementById("id_so_bke").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_bke").readOnly = true;
	document.getElementById("id_ngay_bke").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_dien_giai").readOnly = true;
	document.getElementById("id_dien_giai").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_nntien").readOnly = true;
	document.getElementById("id_ngay_nntien").style.backgroundColor = "#F0F0F0";	
	
	
	
	document.getElementById("lov_ma_nh_nntien").style.display = "none";
	document.getElementById("lov_ma_nh_a").style.display = "none";
	document.getElementById("lov_dvsdns").style.display = "none";
	document.getElementById("lov_lhxnk").style.display = "none";
	document.getElementById("id_cq_thu").focus();
	
	
	document.getElementById("cmdAddDiadiem").style.display = "none";
	
	
}
function A1() {		
	document.getElementById("id_loai_thue").readOnly = true;
	document.getElementById("id_loai_thue").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_thue").readOnly = true;
	document.getElementById("id_ma_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_ten_thue").readOnly = true;
	document.getElementById("id_ten_thue").style.backgroundColor = "#F0F0F0";
	document.getElementById("id_lov_mst").style.display = "none";
	var oLink = document.getElementById('id_ma_thue');			
	oLink.onblur = null;
	oLink.onfocus = null;
	var oLink1 = document.getElementById("id_khtk_row1");	
	oLink1.onkeydown = null;
	
	
	document.getElementById("id_dc_nnthue").readOnly = false;
	document.getElementById("id_dc_nnthue").style.backgroundColor = "#FFFFFF";
	
	document.getElementById("id_dc_nnthue_huyen").readOnly = false;
	document.getElementById("id_dc_nnthue_huyen").style.backgroundColor = "#FFFFFF";
	
	document.getElementById("id_dc_nnthue_tinh").readOnly = false;
	document.getElementById("id_dc_nnthue_tinh").style.backgroundColor = "#FFFFFF";
	
	document.getElementById("id_nguoi_ntien").readOnly = true;
	document.getElementById("id_nguoi_ntien").style.backgroundColor = "#F0F0F0";
	
	
	document.getElementById("id_dc_nntien").readOnly = true;
	document.getElementById("id_dc_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_tkhai_so").readOnly = true;
	document.getElementById("id_tkhai_so").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_dk").readOnly = true;
	document.getElementById("id_ngay_dk").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_lh_xnk1").readOnly = true;
	document.getElementById("id_lh_xnk1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nt").readOnly = true;
	document.getElementById("id_ma_nt").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_qd").readOnly = true;
	document.getElementById("id_so_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ngay_qd").readOnly = true;
	document.getElementById("id_ngay_qd").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_dvsdns").readOnly = true;
	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#F0F0F0";	
	
	document.getElementById("id_so_ct_nh").readOnly = true;
	document.getElementById("id_so_ct_nh").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_nntien").readOnly = true;
	document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ten_nt_nntien").readOnly = true;
	document.getElementById("id_ten_nt_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_ma_nh_a").readOnly = true;
	document.getElementById("id_ma_nh_a").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_tb1").readOnly = true;
	document.getElementById("id_so_tb1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_khung1").readOnly = true;
	document.getElementById("id_so_khung1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_may1").readOnly = true;
	document.getElementById("id_so_may1").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("id_so_bke").readOnly = false;
	document.getElementById("id_so_bke").style.backgroundColor = "#FFFFFF";
	
	document.getElementById("id_ngay_bke").readOnly = false;
	document.getElementById("id_ngay_bke").style.backgroundColor = "#FFFFFF";
	
	document.getElementById("id_dien_giai").readOnly = false;
	document.getElementById("id_dien_giai").style.backgroundColor = "#FFFFFF";	
	
	document.getElementById("id_ngay_nntien").readOnly = true;
	document.getElementById("id_ngay_nntien").style.backgroundColor = "#F0F0F0";
	
	document.getElementById("lov_ma_nh_nntien").style.display = "none";
	document.getElementById("lov_ma_nh_a").style.display = "none";
	document.getElementById("lov_dvsdns").style.display = "none";
	document.getElementById("lov_lhxnk").style.display = "none";
	
	
	document.getElementById("cmdAddDiadiem").style.display = "none";
	
}

function fncInitFormT4(){
	
	var tbl = document.getElementById('tblGridDetailTable');
	var lastRow = tbl.rows.length - 1;   
	
	var ma_dthu = document.forms[0].ma_dthu.value;
	var lan_in = document.forms[0].lan_in.value;
	var kieu_ctu = document.forms[0].kieu_ctu.value;
	var oTK_no = document.getElementById("id_tk_no");
	var oTK_co = document.getElementById("id_tk_co");
	
	if(ma_dthu == '99') {		
		
		if(lastRow == 1) {
			document.forms[0].ttct_tien_nt.readOnly = true;
			document.forms[0].ttct_tien_nt.style.backgroundColor = "#F0F0F0";	
			
			document.forms[0].ttct_ky_thue.readOnly = true;
			document.forms[0].ttct_ky_thue.style.backgroundColor = "#F0F0F0";	
			
			document.forms[0].ttct_khtk.readOnly = true;
			document.forms[0].ttct_khtk.style.backgroundColor = "#F0F0F0";	
			
			document.getElementById("idDeleteRow").style.display = "none";
		}else {
			for(var i = 0; i < lastRow; i++) {								
				document.forms[0].ttct_tien_nt[i].readOnly = true;
				document.forms[0].ttct_tien_nt[i].style.backgroundColor = "#F0F0F0";	
				
				document.forms[0].ttct_ky_thue[i].readOnly = true;
				document.forms[0].ttct_ky_thue[i].style.backgroundColor = "#F0F0F0";	
				
				document.forms[0].ttct_khtk[i].readOnly = true;
				document.forms[0].ttct_khtk[i].style.backgroundColor = "#F0F0F0";	
				
				document.getElementsByName("idDeleteRow")[i].style.display = "none";
				
				
			}
		}
		
		A();
		
		document.getElementById("id_ngay_nntien").readOnly = false;
		document.getElementById("id_ngay_nntien").style.backgroundColor = "#FFFFFF";	
	}else if(kieu_ctu == 0) {
		A1();
		if(lastRow == 1) {
			document.forms[0].ttct_tien_nt.readOnly = true;
			document.forms[0].ttct_tien_nt.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_ma_quy.readOnly = true;
			document.forms[0].ttct_ma_quy.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_chuong.readOnly = true;
			document.forms[0].ttct_chuong.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_noidungkt.readOnly = true;
			document.forms[0].ttct_noidungkt.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_tlpc.readOnly = true;
			document.forms[0].ttct_tlpc.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_noidung.readOnly = true;
			document.forms[0].ttct_noidung.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_ky_thue.readOnly = true;
			document.forms[0].ttct_ky_thue.style.backgroundColor = "#F0F0F0";
			
			document.forms[0].ttct_khtk.readOnly = true;
			document.forms[0].ttct_khtk.style.backgroundColor = "#F0F0F0";
			
			document.getElementById("idDeleteRow").style.display = "none";
		}else {
			for(var i = 0; i < lastRow; i++) {								
				document.forms[0].ttct_tien_nt[i].readOnly = true;
				document.forms[0].ttct_tien_nt[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_ma_quy[i].readOnly = true;
				document.forms[0].ttct_ma_quy[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_chuong[i].readOnly = true;
				document.forms[0].ttct_chuong[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_noidungkt[i].readOnly = true;
				document.forms[0].ttct_noidungkt[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_tlpc[i].readOnly = true;
				document.forms[0].ttct_tlpc[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_noidung[i].readOnly = true;
				document.forms[0].ttct_noidung[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_ky_thue[i].readOnly = true;
				document.forms[0].ttct_ky_thue[i].style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_khtk[i].readOnly = true;
				document.forms[0].ttct_khtk[i].style.backgroundColor = "#F0F0F0";
				
				document.getElementById("idDeleteRow").style.display = "none";
				
				
			}
		}
		document.getElementById("id_cq_thu").readOnly = true;
		document.getElementById("id_cq_thu").style.backgroundColor = "#F0F0F0";	
		document.getElementById("lov_cqthu").style.display = "none";
		
		document.getElementById("id_ma_dbhc").readOnly = true;
		document.getElementById("id_ma_dbhc").style.backgroundColor = "#F0F0F0";	
		document.getElementById("lov_dbhc").style.display = "none";
		
		document.getElementById("id_tk_no").readOnly = true;
		document.getElementById("id_tk_no").style.backgroundColor = "#F0F0F0";	
		document.getElementById("id_tk_co").readOnly = true;
		document.getElementById("id_tk_co").style.backgroundColor = "#F0F0F0";	
		document.getElementById("lov_tk_no").style.display = "none";
		document.getElementById("lov_tk_co").style.display = "none";
		
		oTK_no.onblur = null;
		oTK_co.onblur = null;
	}
	else {
		if(lan_in == '1') {
			if(lastRow == 1) {
				document.forms[0].ttct_tien_nt.readOnly = true;
				document.forms[0].ttct_tien_nt.style.backgroundColor = "#F0F0F0";
				
				document.forms[0].ttct_khtk.readOnly = true;
				document.forms[0].ttct_khtk.style.backgroundColor = "#F0F0F0";
				
				document.getElementById("idDeleteRow").style.display = "none";
			}else {
				for(var i = 0; i < lastRow; i++) {								
					document.forms[0].ttct_tien_nt[i].readOnly = true;
					document.forms[0].ttct_tien_nt[i].style.backgroundColor = "#F0F0F0";	
					
					document.forms[0].ttct_khtk[i].readOnly = true;
					document.forms[0].ttct_khtk[i].style.backgroundColor = "#F0F0F0";
					
					document.getElementsByName("idDeleteRow")[i].style.display = "none";
					
					
				}
			}
			A();		
		}
	}
	
}
function fncInitFormCTu(){
	initOnload();
	fncLoadDMucCTu();
}
function fncLoadDMucCTu(){
	Load_LoaiThue();
	Load_LHXNK();
	Load_MaNT();
	LoadMa_CQThu();
	LoadMa_DBHC();
	Load_Dm_TaiKhoan();
	LoadDMNganHang();
	//LoadDVSDNS();
}
function fncInitFormGom() {
	var form_mode = document.forms[0].form_mode.value;
	if(form_mode == 'BIEN_LAI') {
		var oTK_no = document.getElementById("id_tk_no");
		var oTK_co = document.getElementById("id_tk_co");
		var oMa_nnt = document.getElementById("id_ma_thue");
		
		oTK_no.onbur = null;
		oTK_co.onblur = null;
		oMa_nnt.onblur = null;
		var oLink1 = document.getElementById("id_khtk_row1");	
		oLink1.onkeydown = null;
		
		document.forms[0].loai_thue.readOnly = true;
		document.forms[0].loai_thue.style.backgroundColor = "#F0F0F0";
		
		document.forms[0].ma_thue.readOnly = false;
		document.forms[0].ma_thue.style.backgroundColor = "#FFFFFF";
		
		document.forms[0].ten_thue.readOnly = false;
		document.forms[0].ten_thue.style.backgroundColor = "#FFFFFF";			
		
		document.forms[0].tk_co.readOnly = true;
		document.forms[0].tk_co.style.backgroundColor = "#F0F0F0";
		
		document.forms[0].ma_nt.readOnly = true;
		document.forms[0].ma_nt.style.backgroundColor = "#F0F0F0";		
		
		document.forms[0].kieu_ctu.value = 0;
		
		document.forms[0].nguon_dl.value = 5;
		
		document.getElementById('cmdAddDiadiem').style.display = "none";
		
		
		var tbl = document.getElementById('tblGridDetailTable');
        var lastRow = tbl.rows.length - 1;
        
        
        if(lastRow == 1) {	        	
        	if(!isNull(document.forms[0].ttct_ma_quy.value)){
	        	document.forms[0].ttct_ma_quy.readOnly = true;
				document.forms[0].ttct_ma_quy.style.backgroundColor = "#F0F0F0";
        	}
        	document.forms[0].ttct_tien_nt.readOnly = true;
			document.forms[0].ttct_tien_nt.style.backgroundColor = "#F0F0F0";
			document.getElementById("idDeleteRow").style.display = "none";
	        	    	
        }else {
        	for(var i = 0; i < lastRow; i++) {		
        		
        		if(!isNull(document.forms[0].ttct_ma_quy[i].value)){
	        		document.forms[0].ttct_ma_quy[i].readOnly = true;
	    			document.forms[0].ttct_ma_quy[i].style.backgroundColor = "#F0F0F0";
        		}
    			
				document.forms[0].ttct_tien_nt[i].readOnly = true;
				document.forms[0].ttct_tien_nt[i].style.backgroundColor = "#F0F0F0";				
				document.getElementsByName("idDeleteRow")[i].style.display = "none";
				
				
			}
         	
        }        
        
		
	}
}
function initOnload(){		
	
	 var kieu_ctu = document.forms[0].kieu_ctu.value;	
	 var nguon_dl = document.forms[0].nguon_dl.value;
	 var kieu_form = document.forms[0].kieu_form.value;	
	
	 
	 if(kieu_ctu == '0')
	  { 				 
		   if(kieu_form == '1') {
			    document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";			      
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
			    document.getElementById('ID_QD_SO').style.display = "";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		   }
		   else if(kieu_form == '2') {
			   document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "";			     
			    document.getElementById('ID_NHA').style.display = "";
			    document.getElementById('ID_NH_NNTHUE').style.display = "";		    
			    document.getElementById('ID_QD_SO').style.display = "";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		   }else if(kieu_form == '3')
			  {		
			  	document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "none";
			    //document.getElementById('ID_TKKH_NH').style.display = "none";		    
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		  else if(kieu_form == '4')
		  {		  			 
			  	document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "none";
			    //document.getElementById('ID_TKKH_NH').style.display = "none";		    
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
			    document.getElementById('ID_QD_SO').style.display = "";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		  else if(kieu_form == '5')
		  {		  
			  	document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "";
			    //document.getElementById('ID_TKKH_NH').style.display = "";		    
			    document.getElementById('ID_NHA').style.display = "";
			    document.getElementById('ID_NH_NNTHUE').style.display = "";	
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		  
		 else if(kieu_form == '6')
		  {			
			 	document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "";		    
			    //document.getElementById('ID_TKKH_NH').style.display = "";
			    document.getElementById('ID_NHA').style.display = "";
			    document.getElementById('ID_NH_NNTHUE').style.display = "";	
			    document.getElementById('ID_QD_SO').style.display = "";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		else if(kieu_form == '7')
		  {			
			 	document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "none";		   
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		else if(kieu_form == '8')
		  {			
				document.getElementById('ID_DVSDNS').style.display = "";
			    document.getElementById('ID_SCTU_NH').style.display = "none";
			    //document.getElementById('ID_TKKH_NH').style.display = "none";		   
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
			    document.getElementById('ID_QD_SO').style.display = "";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		else if(kieu_form == '9')
		  {			
				document.getElementById('ID_DVSDNS').style.display = "";		    
			    document.getElementById('ID_SCTU_NH').style.display = "none";
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
		else if(kieu_form == '10')
		  {			
				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";		   	    
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "";
			    document.getElementById('DC_KH_NHAN').style.display = "";
		  }
		else if(kieu_form == '11')
		  {			
				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";		   	    
			    document.getElementById('ID_NHA').style.display = "";
			    document.getElementById('ID_NH_NNTHUE').style.display = "";		    
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "";
			    document.getElementById('DC_KH_NHAN').style.display = "";
		  }else {
			   document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";		       
			    document.getElementById('ID_NH_NNTHUE').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_TKKH_NHAN').style.display = "none";
			    document.getElementById('DC_KH_NHAN').style.display = "none";
		  }
	  }
	 else if(kieu_ctu == '1')
	  { 					 
		 	document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		    //document.getElementById('ID_TKKH_NH').style.display = "none";		    
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	  else if(kieu_ctu == '2')
	  {		   		 
		  	document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "";
		   // document.getElementById('ID_TKKH_NH').style.display = "";		    
		    document.getElementById('ID_NHA').style.display = "";
		    document.getElementById('ID_NH_NNTHUE').style.display = "";		    
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	  else if(kieu_ctu == '3')
	  {		
		  	document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		    //document.getElementById('ID_TKKH_NH').style.display = "none";		    
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	  else if(kieu_ctu == '4')
	  {		  			 
		  	document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		    //document.getElementById('ID_TKKH_NH').style.display = "none";		    
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	  else if(kieu_ctu == '5')
	  {		  
		  	document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "";
		    //document.getElementById('ID_TKKH_NH').style.display = "";		    
		    document.getElementById('ID_NHA').style.display = "";
		    document.getElementById('ID_NH_NNTHUE').style.display = "";	
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	  
	 else if(kieu_ctu == '6')
	  {			
		 	document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "";		    
		    //document.getElementById('ID_TKKH_NH').style.display = "";
		    document.getElementById('ID_NHA').style.display = "";
		    document.getElementById('ID_NH_NNTHUE').style.display = "";	
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	else if(kieu_ctu == '7')
	  {			
		 	document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		   
		    //document.getElementById('ID_TKKH_NH').style.display = "none";
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	else if(kieu_ctu == '8')
	  {			
			document.getElementById('ID_DVSDNS').style.display = "";
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		    //document.getElementById('ID_TKKH_NH').style.display = "none";		   
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	else if(kieu_ctu == '9')
	  {			
			document.getElementById('ID_DVSDNS').style.display = "";		    
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		    //document.getElementById('ID_TKKH_NH').style.display = "none";
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";	
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	  }
	else if(kieu_ctu == '10')
	  {			
			document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		   	    
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "";
		    document.getElementById('DC_KH_NHAN').style.display = "";
	  }
	else if(kieu_ctu == '11')
	  {			
			document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "";		   	    
		    document.getElementById('ID_NHA').style.display = "";
		    document.getElementById('ID_NH_NNTHUE').style.display = "";		    
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "";
		    document.getElementById('DC_KH_NHAN').style.display = "";
	  }
	else if(kieu_ctu == '12')
	  {			
		    document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";
		   // document.getElementById('ID_TKKH_NH').style.display = "none";		    
		    document.getElementById('ID_NHA').style.display = "none";
	  }
	else if(kieu_ctu == '13')
	  {			
		    document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		    
		    //document.getElementById('ID_TKKH_NH').style.display = "none";
		    document.getElementById('ID_NHA').style.display = "none";
	  }
	else if(kieu_ctu == '14')
	  {			
		    document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		    
		    //document.getElementById('ID_TKKH_NH').style.display = "none";
		    document.getElementById('ID_NHA').style.display = "none";
	  }
	else if(kieu_ctu == '15')
	  {			
		    document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		   
		    //document.getElementById('ID_TKKH_NH').style.display = "none";
		    document.getElementById('ID_NHA').style.display = "none";
	  }
	else if(kieu_ctu == '99')
	{ 				  	
		 	document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		   
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";		    
		    document.getElementById('ID_QD_SO').style.display = "";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	}
	
	else 
	{					 
		    document.getElementById('ID_DVSDNS').style.display = "none";
		    document.getElementById('ID_SCTU_NH').style.display = "none";		       
		    document.getElementById('ID_NH_NNTHUE').style.display = "none";
		    document.getElementById('ID_NHA').style.display = "none";
		    document.getElementById('ID_QD_SO').style.display = "none";
		    document.getElementById('ID_TKKH_NHAN').style.display = "none";
		    document.getElementById('DC_KH_NHAN').style.display = "none";
	}
	
	document.getElementById('ID_SO_KHUNG').style.display = "none";    
    document.getElementById('ID_SO_MAY').style.display = "none";
    document.getElementById('ID_SO_TB').style.display = "none"; 
    document.getElementById('ID_SO_TKHAI').style.display = "none";
    document.getElementById('ID_LH_XNK').style.display = "none";    
    
    
	var loai_thue = document.forms[0].loai_thue.value;		
  	loai_thue = trim(loai_thue);      	
  
  	if(loai_thue == "03"){         		
  		document.getElementById('ID_SO_KHUNG').style.display = "";    
    	document.getElementById('ID_SO_MAY').style.display = "";
    	document.getElementById('ID_SO_TB').style.display = "";
  	}      	
    if(loai_thue == "04"){	
    	if(nguon_dl == '4') {
    		document.forms[0].tkhai_so.readOnly = true;
    		document.forms[0].tkhai_so.style.backgroundColor = "#F0F0F0";
    		document.forms[0].ngay_dk.readOnly = true;
    		document.forms[0].ngay_dk.style.backgroundColor = "#F0F0F0";
    		document.forms[0].lh_xnk.readOnly = true;
    		document.forms[0].lh_xnk.style.backgroundColor = "#F0F0F0";
    	}else {
    		document.forms[0].tkhai_so.readOnly = false;
    		document.forms[0].tkhai_so.style.backgroundColor = "#FFFFFF";
    		document.forms[0].tkhai_so.readOnly = false;
    		document.forms[0].ngay_dk.style.backgroundColor = "#FFFFFF";
    		document.forms[0].tkhai_so.readOnly = false;
    		document.forms[0].lh_xnk.style.backgroundColor = "#FFFFFF";
    	}
    	
    	document.getElementById('ID_QD_SO').style.display = "";
	    document.getElementById('ID_SO_TKHAI').style.display = "";	    
	    document.getElementById('ID_LH_XNK').style.display = "";	 
    }     
    if(nguon_dl == '1' || nguon_dl == '3') {
    	var tbl = document.getElementById('tblGridDetailTable');
        var lastRow = tbl.rows.length - 1;
        var form_mode = document.forms[0].form_mode.value;
        if(form_mode != 'BIEN_LAI') {
	        if(lastRow == 1) {	        	
	        	var maChuong = document.forms[0].ttct_chuong.value;
	        	if(isNull(maChuong)==false) {
		        	document.forms[0].ttct_chuong.readOnly = false; //20110822
		        	document.forms[0].ttct_chuong.style.backgroundColor = "#FFFFFF";
	        	}
	        }else {
	        	var maChuong = document.forms[0].ttct_chuong[0].value;
	        	if(isNull(maChuong)==false && maChuong.length > 0) {
		        	document.forms[0].ttct_chuong[0].readOnly = false;
		        	document.forms[0].ttct_chuong[0].style.backgroundColor = "#FFFFFF";
	        	}
	        }        
        }
    }
}	

function checkMapTKhoanCTu(type, print) {	
	
	jQuery.ajax({
		   type: "POST",
		   url: "checkMapTKhoanCTu.do",		  
		   data: "tk_no="+document.forms[0].tk_no.value + "&"+"tk_co="+document.forms[0].tk_co.value,
		   success: function(msg){			
			  
			  var kieu_ctu = msg.kieu_ctu;			
			  if(document.forms[0].form_mode.value == 'BIEN_LAI')
				  kieu_ctu = 0;
			  else {
				  if(kieu_ctu == '0') {
					  kieu_ctu = '99';
				  }
			  }
			  document.forms[0].kieu_ctu.value = kieu_ctu;			  
			  if(kieu_ctu == 1)
			  {
 				 var check = checkMucLucNganSach();  	
 				
 				 if(check == true)
				 { 					    
 					var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
 			        		document.forms[0].tk_co.value,
 			        		document.forms[0].cq_thu.value,
 			        		document.forms[0].ma_dvsdns.value,
 			        		arr_chuong, arr_ndkt, arr_tlpc,
 			        		kieu_ctu, type, print, kieu_ctu);
 					 if(checkLThueMLNS == false)
					 {
 						 return;
					 } 					 
 					 
				 }
 				 else
				 {
 					 return;
				 }
 					
 				 
			  }
 			  else if(kieu_ctu == 2)
			  {
 				 var check = checkMucLucNganSach(); 	
 				 if(check == true)
				 {
 					 var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
  			        		document.forms[0].tk_co.value,
  			        		document.forms[0].cq_thu.value,
  			        		document.forms[0].ma_dvsdns.value,
  			        		arr_chuong, arr_ndkt, arr_tlpc,
  			        		kieu_ctu, type, print, kieu_ctu);
  					 if(checkLThueMLNS == false)
 					 {
  						 return;
 					 }
				 }
 				 else
				 {
 					 return;
				 }
			  }
 			  else if(kieu_ctu == 3)
			  {
 				 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print);
 				 
			  }
 			  else if(kieu_ctu == 4)
			  {
 				 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print);
 				 
			  }
 			  else if(kieu_ctu == 5)
			  {
 				 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print);
 				 
			  }
 			  
 			 else if(kieu_ctu == 6)
			  {
			 	 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print);
 				 
			  }
 			else if(kieu_ctu == 7)
			  {
				 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print);
 				
			  }
 			else if(kieu_ctu == 8)
			  {
				 var ma_dvsdns = document.forms[0].ma_dvsdns.value;
 				 
 				 var returnCheck = checkDVsdNS(ma_dvsdns, kieu_ctu, type, print); 				
				 
			  }
 			else if(kieu_ctu == 9)
			  {
 				var check = checkMucLucNganSach(); 	
				 if(check == true)
				 {
					 var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
	  			        		document.forms[0].tk_co.value,
	  			        		document.forms[0].cq_thu.value,
	  			        		document.forms[0].ma_dvsdns.value,
	  			        		arr_chuong, arr_ndkt, arr_tlpc,
	  			        		kieu_ctu, type, print, kieu_ctu);
  					 if(checkLThueMLNS == false)
 					 {
  						 return;
 					 }  					 
				 }
				 else
				 {
					 return;
				 }
			  }
 			else if(kieu_ctu == 10)
			  {
 				var check = checkMucLucNganSach(); 	
				 if(check == true)
				 {
					 var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
	  			        		document.forms[0].tk_co.value,
	  			        		document.forms[0].cq_thu.value,
	  			        		document.forms[0].ma_dvsdns.value,
	  			        		arr_chuong, arr_ndkt, arr_tlpc,
	  			        		kieu_ctu, type, print, kieu_ctu);
  					 if(checkLThueMLNS == false)
 					 {
  						 return;
 					 }
				 }
				 else
				 {
					 return;
				 }
			  }
 			else if(kieu_ctu == 11)
			  {
 				var check = checkMucLucNganSach(); 	
				 if(check == true)
				 {
					 var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
	  			        		document.forms[0].tk_co.value,
	  			        		document.forms[0].cq_thu.value,
	  			        		document.forms[0].ma_dvsdns.value,
	  			        		arr_chuong, arr_ndkt, arr_tlpc,
	  			        		kieu_ctu, type, print, kieu_ctu);
  					 if(checkLThueMLNS == false)
 					 {
  						 return;
 					 }
				 }
				 else
				 {
					 return;
				 }
			  }
 			else if(kieu_ctu == 12)
			  {
			  
			  }
 			else if(kieu_ctu == 13)
			  {
			  
			  }
 			else if(kieu_ctu == 14)
			  {
			  
			  }
 			else if(kieu_ctu == 99) {
 				
 				 var check = checkMucLucNganSach();  				 
 				 if(check == true)
				 { 					    
 					var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
 			        		document.forms[0].tk_co.value,
 			        		document.forms[0].cq_thu.value,
 			        		document.forms[0].ma_dvsdns.value,
 			        		arr_chuong, arr_ndkt, arr_tlpc,
 			        		kieu_ctu, type, print, kieu_ctu);
 					 if(checkLThueMLNS == false)
					 {
 						 return;
					 } 					 
 					 
				 }
 				 else
				 {
 					 return;
				 }
 			}
 			else {
 				  var form_mode = document.forms[0].form_mode.value;
 				  if(form_mode == 'BIEN_LAI'){
 					 var check = checkMucLucNganSach();  				 
	 				 if(check == true)
					 { 					    
	 					var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
	 			        		document.forms[0].tk_co.value,
	 			        		document.forms[0].cq_thu.value,
	 			        		document.forms[0].ma_dvsdns.value,
	 			        		arr_chuong, arr_ndkt, arr_tlpc,
	 			        		kieu_ctu, type, print, kieu_ctu);
	 					 if(checkLThueMLNS == false)
						 {
	 						 return;
						 }	 					
	 					 
					 }
	 				 else
					 {
	 					 return;
					 }
 				  }else {
 					  alert('Bạn nhập cặp tài khoản nợ - có không tồn tại');
 	 				  document.forms[0].tk_no.focus();
 	 				  return;
 				  }
 				 
 			  }
		   }
		 });
}
function checkMucLucNganSach ()
{
	arr_chuong = new Array();
	arr_tlpc = new Array();
	arr_ndkt = new Array();
	
	 // Kiem tra thong tin chi tiet
    var tbl = document.getElementById('tblGridDetailTable');
    var lastRow = tbl.rows.length - 1;   
    var tong_tien_sum = 0;
    var tong_tien_nt_sum = 0;
    try{
        var ttct_ma_quy = document.forms[0].ttct_ma_quy.value;        
        var ttct_chuong = document.forms[0].ttct_chuong.value;            
        var ttct_noidungkt = document.forms[0].ttct_noidungkt.value;    
        var ttct_tlpc = document.forms[0].ttct_tlpc.value;    
        var ttct_noidung = document.forms[0].ttct_noidung.value;         
        
        var ttct_tien_nt = document.forms[0].ttct_tien_nt.value;
        var ttct_tien = document.forms[0].ttct_tien.value;
        var ttct_ky_thue = document.forms[0].ttct_ky_thue.value;    
        var ttct_khtk = document.forms[0].ttct_khtk.value;   
        
		if(isNull(ttct_ma_quy)) {
             alert(tcs_nhap_ct_ma_quy);
             document.forms[0].ttct_ma_quy.focus();
         	 return false;
        }                
		if(isNull(ttct_chuong)){
           // alert(tcs_nhap_ct_ma_chuong);
           // document.forms[0].ttct_chuong.focus();
           // return false;
			arr_chuong[0] = '';
    	}else{ 
    		arr_chuong[0] = ttct_chuong;    			
    		
    	}    	
    	if(isNull(ttct_noidungkt)){
             // alert(tcs_nhap_ct_noidung_kt);
             // document.forms[0].ttct_noidungkt.focus();
         	 // return false;
    		arr_ndkt[0]='';
    	} else {
    		arr_ndkt[0] = ttct_noidungkt;
    	}   
    	if(isNull(ttct_tlpc)) {
    		// alert('Bạn phải nhập tỉ lệ phân chia');
    		// document.forms[0].ttct_tlpc.focus();
    		// return false;
    		arr_tlpc[0] = '0';
    		
    	}else{    		
    		arr_tlpc[0] = '1';
    	}    	
    	
        if(isNull(ttct_tien_nt)){
            alert(tcs_nhap_ct_tiennt_invalid);
            document.forms[0].ttct_tien_nt.focus();
            return false;
        } 
        
        if(parseFloat(toNumber2(ttct_tien_nt)) < 0 ){
           // alert(tcs_nhap_ct_tiennt_invalid);
           // document.forms[0].ttct_tien_nt.focus();
           // return false;
        }
        if(toNumber2(ttct_tien_nt) == 0 ){
        	alert(tcs_nhap_ct_tiennt_invalid);
            document.forms[0].ttct_tien_nt.focus();
            return false;
         }
		// Convert lai tien nguyen te de insert: Convert from ### ### ### to
		// ##########
		if(parseFloat(toNumber2(ttct_tien_nt)) > 0){
			// document.forms[0].ttct_tien_nt.value = toNumber2(ttct_tien_nt);
		}
		tong_tien_nt_sum = toNumber2(ttct_tien_nt);
		if(tong_tien_nt_sum == 0) {
			// alert(tcs_nhap_ct_tien_invalid);
			// document.forms[0].ttct_tien_nt.focus();
			// return false;
		}
		document.forms[0].ttct_tien_nt.value = toNumber2(ttct_tien_nt);	
        if(parseFloat(toNumber2(ttct_tien)) < 0){
           // alert(tcs_nhap_ct_tien_invalid);
           // document.forms[0].ttct_tien_nt.focus();
           // return false;
        }
        if(toNumber2(ttct_tien) == 0){
             //alert(tcs_nhap_ct_tien_invalid);
             //document.forms[0].ttct_tien_nt.focus();
             //return false;
         }
		// Convert lai so tien de insert: Convert from ### ### ### to ##########
		if(parseFloat(toNumber2(ttct_tien)) > 0){
			// document.forms[0].ttct_tien.value = toNumber2(ttct_tien);
		}   
		tong_tien_sum = toNumber2(ttct_tien);
		if(tong_tien_sum == 0) {
			// alert(tcs_nhap_ct_tien_invalid);
			// document.forms[0].ttct_tien.focus();
			// return false;
		}
		document.forms[0].ttct_tien.value = toNumber2(ttct_tien);				
        if(isNull(ttct_ky_thue)){
            //alert(tcs_nhap_ct_ky_thue);
            // document.forms[0].ttct_ky_thue.focus();
            //return false;
        }           
        if(isNull(ttct_khtk)){
            alert(tcs_nhap_ct_khtk);
            document.forms[0].ttct_khtk.focus();
            return false;
        }
    }catch(e){    
        for(var i = 0; i < lastRow; i++) {                
            var ttct_ma_quy = document.forms[0].ttct_ma_quy[i].value;            
            var ttct_chuong = document.forms[0].ttct_chuong[i].value;               
            var ttct_noidungkt = document.forms[0].ttct_noidungkt[i].value;    
            var ttct_tlpc = document.forms[0].ttct_tlpc[i].value;    
            
            var ttct_noidung = document.forms[0].ttct_noidung[i].value;
            var ttct_tien_nt = document.forms[0].ttct_tien_nt[i].value;
            var ttct_tien = document.forms[0].ttct_tien[i].value;
            var ttct_ky_thue = document.forms[0].ttct_ky_thue[i].value;    
            var ttct_khtk = document.forms[0].ttct_khtk[i].value;           
            
            if(isNull(ttct_ma_quy)){
                 alert(tcs_nhap_ct_ma_quy);
                 document.forms[0].ttct_ma_quy[i].focus();
             	 return false;
            }            
            if(isNull(ttct_chuong)){
            	// alert(tcs_nhap_ct_ma_chuong);
            	// document.forms[0].ttct_chuong[i].focus();
            	// return false;
            	arr_chuong[i] = '';
       	 	}else{
       	 		arr_chuong[i] = ttct_chuong;
       	 	}	       	
	       	if(isNull(ttct_noidungkt)){
               // alert(tcs_nhap_ct_noidung_kt);
                // document.forms[0].ttct_noidungkt[i].focus();
            	// return false;
	       		arr_ndkt[i] = '';
	   	 	}else{
	   	 		arr_ndkt[i] = ttct_noidungkt;
	   	 	} 
	       	if(isNull(ttct_tlpc)){
                // alert('Bạn phải nhập tỉ lệ phân chia');
                // document.forms[0].ttct_tlpc[i].focus();
            	// return false;
	       		arr_tlpc[i] = 0;
	   	 	}else{
	   	 		arr_tlpc[i] = 1;
	   	 	} 
            if(isNull(ttct_tien_nt)) {
            	alert(tcs_nhap_ct_tiennt_invalid);
				document.forms[0].ttct_tien_nt[i].focus(); 
				return false; 
            }
			if(toNumber2(ttct_tien_nt) == 0)
			{
				alert(tcs_nhap_ct_tiennt_invalid);
				document.forms[0].ttct_tien_nt[i].focus(); 
				return false; 
			}
			  
			// Convert lai tien nguyen te de insert: Convert from ### ### ### to
			// ##########
			if(parseFloat(toNumber2(ttct_tien_nt)) > 0) {
				//
			}   
			tong_tien_nt_sum += toNumber2(ttct_tien_nt);
			if(tong_tien_nt_sum == 0) {
				// alert(tcs_nhap_ct_tien_invalid);
				// document.forms[0].ttct_tien_nt[i].focus();
				// return false;
			}
			document.forms[0].ttct_tien_nt[i].value = toNumber2(ttct_tien_nt);		
			
			if(parseFloat(toNumber1(ttct_tien)) < 0)
			{
				//
			}
			if(toNumber1(ttct_tien) == 0)
			{
				alert(tcs_nhap_ct_tien_invalid);
				document.forms[0].ttct_tien[i].focus(); 
				return false; 
			}
			 
		   // Convert lai tien nguyen te de insert: Convert from ### ### ### to
			// ##########
			if(parseFloat(toNumber2(ttct_tien)) > 0){
				// document.forms[0].ttct_tien[i].value = toNumber2(ttct_tien);
		    } 
			tong_tien_sum += toNumber2(ttct_tien);
			if(tong_tien_sum == 0) {
				// alert(tcs_nhap_ct_tien_invalid);
				// document.forms[0].ttct_tien.focus();
				// return false;
			}
			document.forms[0].ttct_tien[i].value = toNumber2(ttct_tien);		
           if(isNull(ttct_ky_thue)){
                //alert(tcs_nhap_ct_ky_thue);
                //document.forms[0].ttct_ky_thue[i].focus();
                //return false;
           }           
           if(isNull(ttct_khtk)){
                alert(tcs_nhap_ct_khtk);
                document.forms[0].ttct_khtk[i].focus();
                return false;
          }   	          
           
          /**
			 * Kiem tra 2 dong chi tiet trung nhau Doi voi loai thue hai quan
			 * thi khong check
			 */
           
           // Kiem tra 2 dong detail trung nhau
           var lThueCheckForDetail = document.forms[0].loai_thue.value;
           if(lThueCheckForDetail == '04'){
           for(j=0; j<i; j++){	             
                    var ttct_ma_quy1 = document.forms[0].ttct_ma_quy[j].value;            
		            var ttct_chuong1 = document.forms[0].ttct_chuong[j].value;    
		            var ttct_tlpc1 = document.forms[0].ttct_tlpc[j].value;    
		            var ttct_noidungkt1 = document.forms[0].ttct_noidungkt[j].value;            
		            var ttct_noidung1 = document.forms[0].ttct_noidung[j].value;			           
		            var ttct_ky_thue1 = document.forms[0].ttct_ky_thue[j].value;    
		            var ttct_khtk1 = document.forms[0].ttct_khtk[j].value;
                    if(ttct_ma_quy1 == ttct_ma_quy){
                    	if(ttct_chuong1 == ttct_chuong){
                    		if(ttct_noidungkt1 == ttct_noidungkt){
                    			if(ttct_tlpc1 == ttct_tlpc){
                    				if(ttct_noidung1 == ttct_noidung){
                    					if(ttct_ky_thue1 == ttct_ky_thue){
                    						if(ttct_khtk1 == ttct_khtk){
                    							 alert(tcs_nhap_ct_detail_row);
                    							 if(parseFloat(i)==0){
                    							 	 	document.forms[0].ttct_ma_quy.focus();
                                						return false;
                    							 }else{
                    							 		document.forms[0].ttct_ma_quy[i].focus();
                                						return false;
                    							 }
                    						}
                    					}
                    				}
                    			}
                    		}
                        }
                    } 
           }
          }          
        }         
     }
    	var tong_tien_nt = document.forms[0].tong_tien_nt.value;
    	var tong_tien = document.forms[0].tong_tien.value;
	    if(isNull(tong_tien_nt)) {
			alert("Tổng ti�?n nguyên tệ bạn nhập không hợp lệ");
			return false;
		}
		if(isNull(tong_tien)) {
			alert("Tổng ti�?n bạn nhập không hợp lệ");
			return false;
		}		
		document.forms[0].tong_tien_nt.value = toNumber2(tong_tien_nt);					
		document.forms[0].tong_tien.value = toNumber2(tong_tien);		
		return true;
}
function checkFistMapTKhoanCTu(obj, page) {
	var tk_no = document.forms[0].tk_no.value;
	var tk_co = document.forms[0].tk_co.value;
	var tkno_co_hidden = document.forms[0].tkno_co_hidden.value;	
	
	
	if(isNull(tk_no)) return;
	if(isNull(tk_co)) return;
	
	if(trim(tk_no).length != 4) return;
	if(trim(tk_co).length != 4) return;
	
	if(tk_no+tk_co == tkno_co_hidden){
		  var kieu_ctu = document.forms[0].kieu_ctu.value;
		 
		  if(kieu_ctu == 0)
			  {
			  	if(obj.name == 'tk_no') {				  		
			  		 document.forms[0].tk_co.focus();
			  		 return;
			  	}
			  	else if(obj.name == 'tk_co'){
			  		 alert('Bạn nhập cặp tài khoản Nợ - Có không tồn tại. Cặp tài khoản bắt buộc phải nhập chính xác trước khi thực hiện ghi chứng từ!');
					 document.forms[0].tk_no.focus();
					 return;
			  	}
				  
			  }	
		  else
			  return;
	}
	else {
		 document.forms[0].tkno_co_hidden.value = tk_no+tk_co;
	}
	
	jQuery.ajax({
		   type: "POST",
		   url: "checkMapTKhoanCTu.do",		  
		   data: "tk_no="+tk_no + "&"+"tk_co="+tk_co,
		   success: function(msg)
		   {			  
			  var kieu_ctu = msg.kieu_ctu;				
			  document.forms[0].kieu_ctu.value = kieu_ctu;
			  var lan_in = document.forms[0].lan_in.value;
			  
 			  if(kieu_ctu == 1)
			  { 				
 				  	if(lan_in == 1) { 
	 				    document.getElementById('ID_DVSDNS').style.display = "none"; 				    
	 				    document.getElementById('ID_SCTU_NH').style.display = "none";
	 				    //document.getElementById('ID_TKKH_NH').style.display = "none"; 				    
	 				    document.getElementById('ID_NHA').style.display = "none";
	 				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
	 				    document.getElementById('ID_QD_SO').style.display = "";
	 				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
	 				    document.getElementById('DC_KH_NHAN').style.display = "none";
	 				    
	 				    document.getElementById("id_so_qd").readOnly = false;
	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_qd").readOnly = false;
	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF"; 

 				  	}
 				  	else {
 				  		document.getElementById('ID_DVSDNS').style.display = "none"; 				    
	 				    document.getElementById('ID_SCTU_NH').style.display = "none";
	 				    //document.getElementById('ID_TKKH_NH').style.display = "none"; 				    
	 				    document.getElementById('ID_NHA').style.display = "none";
	 				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
	 				    document.getElementById('ID_QD_SO').style.display = "";
	 				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
	 				    document.getElementById('DC_KH_NHAN').style.display = "none";
 				  	}
 				    document.getElementById('id_ma_dvsdns').value = "";
 				    document.getElementById('id_ten_dvsdns').value = "";
 				    
 				    document.getElementById('id_so_ct_nh').value = "";	
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
 				    
			  }
 			  else if(kieu_ctu == 2)
			  { 				    
 				  	if(lan_in == 1) {
					    document.getElementById('ID_DVSDNS').style.display = "none";
					    document.getElementById('ID_SCTU_NH').style.display = "";
					    //document.getElementById('ID_TKKH_NH').style.display = "";				    
					    document.getElementById('ID_NHA').style.display = "";
					    document.getElementById('ID_NH_NNTHUE').style.display = "";
					    document.getElementById('ID_QD_SO').style.display = "";
					    document.getElementById('ID_TKKH_NHAN').style.display = "none";
					    document.getElementById('DC_KH_NHAN').style.display = "none";
					    
					    document.getElementById("id_so_ct_nh").readOnly = false;
						document.getElementById("id_so_ct_nh").style.backgroundColor = "#FFFFFF";						

						document.getElementById("id_ma_nh_nntien").readOnly = false;
						document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#FFFFFF";
						
						document.getElementById("id_ma_nh_a").readOnly = false;
						document.getElementById("id_ma_nh_a").style.backgroundColor = "#FFFFFF";
						
						document.getElementById("id_so_qd").readOnly = false;
	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_qd").readOnly = false;
	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_nntien").readOnly = false;
	 					document.getElementById("id_ngay_nntien").style.backgroundColor = "#FFFFFF";	
	 					
	 					document.getElementById("lov_ma_nh_nntien").style.display = "";
	 					document.getElementById("lov_ma_nh_a").style.display = "";
						
 				  	}
 				  	else {
 				  		document.getElementById('ID_DVSDNS').style.display = "none";
					    document.getElementById('ID_SCTU_NH').style.display = "";
					    //document.getElementById('ID_TKKH_NH').style.display = "";				    
					    document.getElementById('ID_NHA').style.display = "";
					    document.getElementById('ID_NH_NNTHUE').style.display = "";
					    document.getElementById('ID_QD_SO').style.display = "";
					    document.getElementById('ID_TKKH_NHAN').style.display = "none";
					    document.getElementById('DC_KH_NHAN').style.display = "none";
 				  	}
				    document.getElementById('id_ma_dvsdns').value = "";
 				    document.getElementById('id_ten_dvsdns').value = "";
			  }
 			  else if(kieu_ctu == 3)
			  { 					
 				    document.getElementById('ID_DVSDNS').style.display = "";
 				    document.getElementById('ID_SCTU_NH').style.display = "none";
 				    //document.getElementById('ID_TKKH_NH').style.display = "none"; 				    
 				    document.getElementById('ID_NHA').style.display = "none";
 				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
 				    document.getElementById('ID_QD_SO').style.display = "none";
 				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
 				    document.getElementById('DC_KH_NHAN').style.display = "none";
 				    
 				    if(lan_in == 1) {
 				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	
 				    	document.getElementById("id_so_qd").readOnly = false;
 	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
 	 					
 	 					document.getElementById("id_ngay_qd").readOnly = false;
 	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF";
 	 					
 	 					document.getElementById("lov_dvsdns").style.display = "";
 				    }
 				    
 				    document.getElementById('id_so_ct_nh').value = "";	
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";				    
				    
			  }
 			  else if(kieu_ctu == 4)
			  { 				  	
				    document.getElementById('ID_DVSDNS').style.display = "";
				    document.getElementById('ID_SCTU_NH').style.display = "none";
				    //document.getElementById('ID_TKKH_NH').style.display = "none";
				    document.getElementById('ID_NH_NNTHUE').style.display = "none";				    
				    document.getElementById('ID_NHA').style.display = "none";
				    document.getElementById('ID_QD_SO').style.display = "";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
				    
				    if(lan_in == 1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	
				    	document.getElementById("id_so_qd").readOnly = false;
	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_qd").readOnly = false;
	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("lov_dvsdns").style.display = "";
				    }
				    
 				    document.getElementById('id_so_ct_nh').value = "";
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
			  }
 			  else if(kieu_ctu == 5)
			  { 				  	
				    document.getElementById('ID_DVSDNS').style.display = "";
				    document.getElementById('ID_SCTU_NH').style.display = "";
				    //document.getElementById('ID_TKKH_NH').style.display = "";
				    
				    document.getElementById('ID_NHA').style.display = "";
				    document.getElementById('ID_NH_NNTHUE').style.display = "";
				    document.getElementById('ID_QD_SO').style.display = "none";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
				    if(lan_in == 1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	
					    document.getElementById("id_so_ct_nh").readOnly = false;
						document.getElementById("id_so_ct_nh").style.backgroundColor = "#FFFFFF";						

						document.getElementById("id_ma_nh_nntien").readOnly = false;
						document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#FFFFFF";
						
						document.getElementById("id_ma_nh_a").readOnly = false;
						document.getElementById("id_ma_nh_a").style.backgroundColor = "#FFFFFF";
						
						document.getElementById("id_ngay_nntien").readOnly = false;
	 					document.getElementById("id_ngay_nntien").style.backgroundColor = "#FFFFFF";	
						
	 					document.getElementById("lov_ma_nh_nntien").style.display = "";
	 					document.getElementById("lov_ma_nh_a").style.display = "";
	 					document.getElementById("lov_dvsdns").style.display = "";
				    }
			  }
 			  
 			 else if(kieu_ctu == 6)
			  {	 				
				    document.getElementById('ID_DVSDNS').style.display = "";
				    document.getElementById('ID_SCTU_NH').style.display = "";				    
				    //document.getElementById('ID_TKKH_NH').style.display = "";
				    document.getElementById('ID_NH_NNTHUE').style.display = "";
				    document.getElementById('ID_NHA').style.display = "";
				    document.getElementById('ID_QD_SO').style.display = "";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
				    if(lan_in == 1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	
					    document.getElementById("id_so_ct_nh").readOnly = false;
						document.getElementById("id_so_ct_nh").style.backgroundColor = "#FFFFFF";						

						document.getElementById("id_ma_nh_nntien").readOnly = false;
						document.getElementById("id_ma_nh_nntien").style.backgroundColor = "#FFFFFF";
						
						document.getElementById("id_ma_nh_a").readOnly = false;
						document.getElementById("id_ma_nh_a").style.backgroundColor = "#FFFFFF";
	 					
						document.getElementById("id_so_qd").readOnly = false;
	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_nntien").readOnly = false;
	 					document.getElementById("id_ngay_nntien").style.backgroundColor = "#FFFFFF";	
	 					
	 					document.getElementById("id_ngay_qd").readOnly = false;
	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF";
	 					document.getElementById("lov_ma_nh_nntien").style.display = "";
	 					document.getElementById("lov_ma_nh_a").style.display = "";
	 					document.getElementById("lov_dvsdns").style.display = "";
				    }
			  }
 			else if(kieu_ctu == 7)
			  {
				    document.getElementById('ID_DVSDNS').style.display = "";
				    document.getElementById('ID_SCTU_NH').style.display = "none";				   
				    //document.getElementById('ID_TKKH_NH').style.display = "none";
				    document.getElementById('ID_NHA').style.display = "none";
				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
				    document.getElementById('ID_QD_SO').style.display = "none";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
 				    
 				    document.getElementById('id_so_ct_nh').value = "";	
				    
				    if(lan_in ==1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	document.getElementById("lov_dvsdns").style.display = "";
				    }
				    //document.getElementById('id_ngay_kh_nh').value = "";
				    //document.getElementById('id_ngay_kb_nh').value = "";
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
			  }
 			else if(kieu_ctu == 8)
			  {	 				
				    document.getElementById('ID_DVSDNS').style.display = "";
				    document.getElementById('ID_SCTU_NH').style.display = "none";
				    //document.getElementById('ID_TKKH_NH').style.display = "none";				   
				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
				    document.getElementById('ID_NHA').style.display = "none";
				    document.getElementById('ID_QD_SO').style.display = "";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
				    
				    if(lan_in ==1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";	
 				    	document.getElementById("id_so_qd").readOnly = false;
	 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("id_ngay_qd").readOnly = false;
	 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF";
	 					
	 					document.getElementById("lov_dvsdns").style.display = "";
				    }
				    
				    document.getElementById('id_so_ct_nh').value = "";				
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
			  }
 			else if(kieu_ctu == 9)
			  {	 				
				    document.getElementById('ID_DVSDNS').style.display = "";				    
				    document.getElementById('ID_SCTU_NH').style.display = "none";
				    //document.getElementById('ID_TKKH_NH').style.display = "none";
				    document.getElementById('ID_QD_SO').style.display = "none";
				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
				    document.getElementById('ID_NHA').style.display = "none";
				    document.getElementById('ID_TKKH_NHAN').style.display = "none";
				    document.getElementById('DC_KH_NHAN').style.display = "none";
 				    
 				    document.getElementById('id_so_ct_nh').value = "";
				    if(lan_in ==1) {
				    	document.getElementById("id_ma_dvsdns").readOnly = false;
 				    	document.getElementById("id_ma_dvsdns").style.backgroundColor = "#FFFFFF";
 				    	document.getElementById("lov_dvsdns").style.display = "";
				    }
				    //document.getElementById('id_ngay_kh_nh').value = "";
				    //document.getElementById('id_ngay_kb_nh').value = "";
				    
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
			  }
 			else if(kieu_ctu == 10)
			  {	 				
				    document.getElementById('ID_DVSDNS').style.display = "none";
				    document.getElementById('ID_SCTU_NH').style.display = "none";				    
				    document.getElementById('ID_NH_NNTHUE').style.display = "none";				    
				    document.getElementById('ID_NHA').style.display = "none";
				    document.getElementById('ID_QD_SO').style.display = "none";			    
				    
				    document.getElementById('ID_TKKH_NHAN').style.display = "";
				    document.getElementById('DC_KH_NHAN').style.display = "";
				    
				    
				    document.getElementById('id_ma_dvsdns').value = "";
 				    document.getElementById('id_ten_dvsdns').value = "";
 				    
 				    document.getElementById('id_so_ct_nh').value = "";					    
				   
				    document.getElementById('id_ma_nh_nntien').value = "";
				    document.getElementById('id_ten_nt_nntien').value = "";
				    
				    document.getElementById('id_ma_nh_a').value = "";
				    document.getElementById('id_ten_nh_a').value = "";
			  }
 			else if(kieu_ctu == 11)
			  {
	 				document.getElementById('ID_DVSDNS').style.display = "none";
				    document.getElementById('ID_SCTU_NH').style.display = "";				    
				    document.getElementById('ID_NH_NNTHUE').style.display = "";				    
				    document.getElementById('ID_NHA').style.display = "";
				    document.getElementById('ID_QD_SO').style.display = "none";
				    
				    document.getElementById('ID_TKKH_NHAN').style.display = "";
				    document.getElementById('DC_KH_NHAN').style.display = "";
			  }
 			else if(kieu_ctu == 12)
			  {
 				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";			   
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			    document.getElementById('ID_QD_SO').style.display = "none";
			  }
 			else if(kieu_ctu == 13)
			  {
 				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";			    
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			  }
 			else if(kieu_ctu == 14)
			  {
 				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";			    
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_QD_SO').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			  }
 			else if(kieu_ctu == 15)
			  {
				document.getElementById('ID_DVSDNS').style.display = "none";
			    document.getElementById('ID_SCTU_NH').style.display = "none";			    
			    document.getElementById('ID_QD_SO').style.display = "none";
			    //document.getElementById('ID_TKKH_NH').style.display = "none";
			    document.getElementById('ID_NHA').style.display = "none";
			  }
 			else if(kieu_ctu == 99)
			{
 				if(lan_in == 1) { 
 				    document.getElementById('ID_DVSDNS').style.display = "none"; 				    
 				    document.getElementById('ID_SCTU_NH').style.display = "none";
 				    //document.getElementById('ID_TKKH_NH').style.display = "none"; 				    
 				    document.getElementById('ID_NHA').style.display = "none";
 				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
 				    document.getElementById('ID_QD_SO').style.display = "";
 				    
 				    document.getElementById("id_so_qd").readOnly = false;
 					document.getElementById("id_so_qd").style.backgroundColor = "#FFFFFF";
 					
 					document.getElementById("id_ngay_qd").readOnly = false;
 					document.getElementById("id_ngay_qd").style.backgroundColor = "#FFFFFF"; 

				  	}
				  	else {
				  		document.getElementById('ID_DVSDNS').style.display = "none"; 				    
 				    document.getElementById('ID_SCTU_NH').style.display = "none";
 				    //document.getElementById('ID_TKKH_NH').style.display = "none"; 				    
 				    document.getElementById('ID_NHA').style.display = "none";
 				    document.getElementById('ID_NH_NNTHUE').style.display = "none";
 				    document.getElementById('ID_QD_SO').style.display = "";
				  	}
				    document.getElementById('id_ma_dvsdns').value = "";
				    document.getElementById('id_ten_dvsdns').value = "";
				    
				    document.getElementById('id_so_ct_nh').value = "";	
			    
			    document.getElementById('id_ma_nh_nntien').value = "";
			    document.getElementById('id_ten_nt_nntien').value = "";
			    
			    document.getElementById('id_ma_nh_a').value = "";
			    document.getElementById('id_ten_nh_a').value = "";
			}
		  else 
		  	 {			 			
			  if(obj.name == 'tk_no')
			  {
				  document.forms[0].tk_co.focus();				 
				  return;
			  }
			  else{
				  alert('Bạn nhập cặp tài khoản Nợ - Có không tồn tại. Cặp tài khoản bắt buộc phải nhập chính xác trước khi thực hiện ghi chứng từ!');
				  document.forms[0].tk_no.focus();
				  return;
			  }			  
			  
		  }
		   }
		 });
}
function checkDVsdNS(ma_dvsdns, kieu_ctu, type, print) {
	
	if (isNull(ma_dvsdns)){        
		var check = checkMucLucNganSach(); 	
		if(check == true)
		 {
				var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
		        		document.forms[0].tk_co.value,
		        		document.forms[0].cq_thu.value,
		        		document.forms[0].ma_dvsdns.value,
		        		arr_chuong, arr_ndkt, arr_tlpc,
		        		kieu_ctu, type, print, kieu_ctu);
				 if(checkLThueMLNS == false)
				 {
					 return;
				 }				
		 }
		else
		 {
				 return;
		 }		
    }
	else {		
		 jQuery.ajax({
			   type: "POST",
			   url: "CheckMaDVSDNS.do",		
			   data: "ma_dvsdns="+ma_dvsdns,
			   success: function(msg){			   
				   if(msg.ten == 'NO NAME') {
					   alert('Mã đơn vị sử dụng Ngân sách không tồn tại');
					   document.forms[0].ma_dvsdns.focus();
					   return;
				   }else {
					   var check = checkMucLucNganSach(); 	
						if(check == true)
						 {
								var checkLThueMLNS = checkLoaiThueMLNS(document.forms[0].loai_thue.value, 
						        		document.forms[0].tk_co.value,
						        		document.forms[0].cq_thu.value,
						        		document.forms[0].ma_dvsdns.value,
						        		arr_chuong, arr_ndkt, arr_tlpc,
						        		kieu_ctu, type, print, kieu_ctu);
								 if(checkLThueMLNS == false)
								 {
									 return;
								 }								
						 }
						else
						 {
								 return;
						 }	
				   }
				    
			   }
			 });		 
		 
    }  
}
function checkLoaiThueMLNS(loai_thue, tk_co, cq_thu, dvsdns, arrChuong, arrNdkt, arrTlpc, kieu_thu, type, print, kieu_ctu) {
	
	jQuery.ajax({
		   type: "POST",
		   url: "checkLoaiThueMLNS.do",		  
		   data: "loai_thue="+loai_thue+"&"+
		   		 "tk_co="+tk_co+"&"+
		   		 "cq_thu="+cq_thu+"&"+
		   		 "dvsdns="+dvsdns+"&"+
		   		 "arrChuong="+arrChuong+"&"+
		   		 "arrNdkt="+arrNdkt+"&"+
		   		 "arrTlpc="+arrTlpc+"&"+
		   		 "kieu_ctu="+kieu_ctu,
		   
		   success: function(msg) {
			 
			   	 var checkMlns = msg.checkMlns;
			   	 var errCheckMlns = msg.errCheckMlns;	
			   	 
			   	
			   	 var rowCheckMlns = msg.rowCheckMlns;
			   	 
			   	 
			   	 if(checkMlns == 'Success')
		   		 {			   		 
			   		 if(type == 'insert')
			   		 { 			   			 
				   		 document.forms[0].kieu_ctu.value = kieu_thu;				   		 
						 document.getElementById('id_sbSave').disabled = true;			
						 document.forms[0].allowPrint.value = print;
		 		         document.forms[0].__action.value="save";		 		         
		 		         document.forms[0].submit();
		 		         return true;
			   		 }	
			   		 else {
			   			 document.forms[0].kieu_ctu.value = kieu_thu;
						 document.getElementById('id_sbUpdate').disabled = true;	
						 document.forms[0].allowPrint.value = print;
		 		         document.forms[0].__action.value="update";
		 		         document.forms[0].submit();
		 		         return true;
			   		 }
		   		 }
			   	 else {	
			   		 if(errCheckMlns == 'LTHUE'){
			   			 var rowChecked = parseInt(rowCheckMlns) - 1;
			   			    alert('Mục lục ngân sách dòng thứ '+rowCheckMlns+' bạn nhập không thuộc loại thuế ' +loai_thue +'. Bạn phải nhập lại');
				   				
			   				 if(rowCheckMlns == 1)
			   				 {
			   					try {
			   						document.forms[0].ttct_chuong.focus();
			   					}catch (e) {
			   						document.forms[0].ttct_chuong[0].focus();
								}
				   				
			   				 }else {
			   					document.forms[0].ttct_chuong[rowChecked].focus();
			   				 }
			   		 }
			   		 else if(errCheckMlns == 'DVSDNS') {
			   			 alert('Bạn phải nhập �?ơn vị sử dụng Ngân sách');
			   			 document.forms[0].ma_dvsdns.focus();
			   		 }
			   		 
			   		 else if(errCheckMlns == 'CHUONG') {
			   			 var rowChecked = parseInt(checkMlns) - 1;
			   			 alert("Bạn phải nhập mã chương dòng thứ " + checkMlns);
			   			 if(checkMlns == 1)
		   				 {
			   				document.forms[0].ttct_chuong.focus();
		   				 }else {
		   					document.forms[0].ttct_chuong[rowChecked].focus();
		   				 }
			   			 
			   		 }
			   		 else if(errCheckMlns == 'NDKT') {
			   			 var rowChecked = parseInt(checkMlns) - 1;			   			 
			   			 alert("Bạn phải nhập mã nội dung kinh tế dòng thứ " + checkMlns);
			   			 if(checkMlns == 1)
		   				 {
			   				document.forms[0].ttct_noidungkt.focus();
		   				 }else {
		   					document.forms[0].ttct_noidungkt[rowChecked].focus();
		   				 }
			   			 
			   		 }
			   		 else if(errCheckMlns == 'TLPC') {
			   			 var rowChecked = parseInt(checkMlns) - 1;
			   			 alert("Bạn phải nhập mã tỉ lệ phân chia dòng thứ " + checkMlns);
			   			 if(checkMlns == 1)
		   				 {
			   				document.forms[0].ttct_tlpc.focus();
		   				 }else {
		   					document.forms[0].ttct_tlpc[rowChecked].focus();
		   				 }
			   			 
			   		 }else if(errCheckMlns == 'CHUONG_NDKT') {
			   			var rowChecked = parseInt(checkMlns) - 1;	
			   			
			   			alert('Mục lục ngân sách dòng thứ '+checkMlns+' bạn nhập không thuộc loại thuế ' +loai_thue +'. Bạn phải nhập lại');
		   				if(checkMlns == 1)
		   				 {
		   					try {
		   						document.forms[0].ttct_chuong.focus();
		   					}catch (e) {
		   						document.forms[0].ttct_chuong[0].focus();
							}				   				
		   				 }else {			   					 
		   					 document.forms[0].ttct_chuong[rowChecked].focus();
		   				 }			
			   		 }
			   		 return false;
			   	 }
			   	 
		   }
		 });

	/**
	 * Ham tham khao
	 */
	var Utf8 = {
			 
			// public method for url encoding
			encode : function (string) {
				string = string.replace(/\r\n/g,"\n");
				var utftext = "";
		 
				for (var n = 0; n < string.length; n++) {
		 
					var c = string.charCodeAt(n);
		 
					if (c < 128) {
						utftext += String.fromCharCode(c);
					}
					else if((c > 127) && (c < 2048)) {
						utftext += String.fromCharCode((c >> 6) | 192);
						utftext += String.fromCharCode((c & 63) | 128);
					}
					else {
						utftext += String.fromCharCode((c >> 12) | 224);
						utftext += String.fromCharCode(((c >> 6) & 63) | 128);
						utftext += String.fromCharCode((c & 63) | 128);
					}
		 
				}
		 
				return utftext;
			},
		 
			// public method for url decoding
			decode : function (utftext) {
				var string = "";
				var i = 0;
				var c = c1 = c2 = 0;
		 
				while ( i < utftext.length ) {
		 
					c = utftext.charCodeAt(i);
		 
					if (c < 128) {
						string += String.fromCharCode(c);
						i++;
					}
					else if((c > 191) && (c < 224)) {
						c2 = utftext.charCodeAt(i+1);
						string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
						i += 2;
					}
					else {
						c2 = utftext.charCodeAt(i+1);
						c3 = utftext.charCodeAt(i+2);
						string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
						i += 3;
					}
		 
				}
		 
				return string;
			}
		 
		}
}
function fncInitFormCTuEdit(tkhoan_nte, tkhoan_vnd) {	
	var ma_nte = document.forms[0].ma_nt.value;
	var tk_no = document.forms[0].tk_no.value;
	if(ma_nte != 'VND') {
		document.forms[0].ma_nt.readOnly = false;		
		document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";	
	} else if(ma_nte == 'VND') {
		if(tkhoan_nte.indexOf(tk_no) < 0 && tkhoan_vnd.indexOf(tk_no) < 0) {
			document.forms[0].ma_nt.readOnly = false;		
			document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";		
		}else {
			document.forms[0].ma_nt.readOnly = true;		
			document.forms[0].ma_nt.style.backgroundColor="#F0F0F0";			
		}
	}
	else{				
		document.forms[0].ma_nt.readOnly = false;		
		document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";		
	}
	var radioLength = document.forms[0].nien_do.length;	
	try{
		document.forms[0].nien_do[document.forms[0].vNien_do.value].checked = true;
	}catch (e) {
		document.forms[0].nien_do[1].checked = true;
	}
	
	
}
function LoadMaNte(tkhoan_nte, tkhoan_vnd) {	
	var form_mode = document.forms[0].form_mode.value;
	if(form_mode == 'BIEN_LAI') {
		return;
	}
	var tk_no = document.forms[0].tk_no.value;	
	if(isNull(tk_no)) 
	{
		document.forms[0].ma_nt.value = 'VND';
		document.forms[0].ten_nt.value = 'Ti�?n VN�?';
		document.forms[0].ma_nt.readOnly = false;		
		document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";
		document.forms[0].ty_gia.value = '1';
		cal_tinhtien();
		return;	
	}
		
	
	if(tk_no.length < 4)
	{
		document.forms[0].ma_nt.value = 'VND';
		document.forms[0].ten_nt.value = 'Ti�?n VN�?';
		document.forms[0].ma_nt.readOnly = false;		
		document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";
		document.forms[0].ty_gia.value = '1';
		cal_tinhtien();
		return;
	}
	else
	{		
		if(trim(tkhoan_nte).indexOf(trim(tk_no)) >= 0) {			
			document.forms[0].ma_nt.value = 'USD';
			document.forms[0].ten_nt.value = '�?ô la Mỹ';
			document.forms[0].ma_nt.readOnly = false;		
			document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";
			Retrieve_Tygia();
		}else if(trim(tkhoan_vnd).indexOf(trim(tk_no)) >= 0){
			document.forms[0].ma_nt.value = 'VND';
			document.forms[0].ten_nt.value = 'Ti�?n VN�?';
			document.forms[0].ma_nt.readOnly = true;		
			document.forms[0].ma_nt.style.backgroundColor="#F0F0F0";
			document.forms[0].ty_gia.value = '1';
			cal_tinhtien();
		}else {
			document.forms[0].ma_nt.readOnly = false;		
			document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";			
			cal_tinhtien();
		}
	}
}
function CheckTKhoanNTeMNTe(tkhoan_nte, tkhoan_vnd) {
	var ma_nt = document.forms[0].ma_nt.value;
	var tk_no = document.forms[0].tk_no.value;
	if(isNull(tk_no)) return;
	if(trim(tk_no).length <4) return;
	var form_mode = document.forms[0].form_mode.value;
	if(form_mode == 'BIEN_LAI') {
		return;
	}
	
	if(trim(tkhoan_nte).indexOf(trim(tk_no))>=0)
	{		
		if(ma_nt == 'VND' ) {
			alert('Bạn không được phép ch�?n mã nguyên tệ VND khi đã ch�?n tài khoản nợ là tài khoản ngoại tê.');
			document.forms[0].ma_nt.value = 'USD';
			document.forms[0].ten_nt.value = '�?ô la Mỹ';			
			Retrieve_Tygia();
		}
	} else if(trim(tkhoan_vnd).indexOf(trim(tk_no))>=0) {
		document.forms[0].ma_nt.value = 'VND';
		document.forms[0].ten_nt.value = 'Ti�?n VN�?';
		document.forms[0].ma_nt.readOnly = true;		
		document.forms[0].ma_nt.style.backgroundColor="#F0F0F0";
		document.forms[0].ty_gia.value = '1';
		cal_tinhtien();
	}
	else {		
		document.forms[0].ma_nt.readOnly = false;		
		document.forms[0].ma_nt.style.backgroundColor="#FFFFFF";
		document.forms[0].ty_gia.value = '1';
		cal_tinhtien();
	}
}
function calculateTLPC(src) {
	
	var row = src.parentElement.parentElement;

	
	var rowIndex = parseInt(row.rowIndex)-1;
	performcalculateTLPC(rowIndex);
}
function performcalculateTLPC(index) {	
	var tbl = document.getElementById('tblGridDetailTable');
    var lastRow = tbl.rows.length-1;      
    
	var shkb = document.forms[0].shkb.value;
	
	var ma_dbhc  = document.forms[0].ma_dbhc.value;
	var ma_cqthu = document.forms[0].cq_thu.value;
	var tk_co = document.forms[0].tk_co.value;
	
	if(isNull(ma_dbhc)) return;
	if(isNull(ma_cqthu)) return;
	if(isNull(tk_co)) return;
	
	
	if(index == 0) {		
		var ma_chuong = '';
		var ma_ndkt = '';		
		if(lastRow > 1)
		{ 
			ma_chuong = document.forms[0].ttct_chuong[index].value;
			ma_ndkt = document.forms[0].ttct_noidungkt[index].value;
		}
		else
		{
			ma_chuong = document.forms[0].ttct_chuong.value;
			ma_ndkt = document.forms[0].ttct_noidungkt.value;
		}
		if(isNull(ma_chuong)) return;
		if(isNull(ma_ndkt)) return;		
		CalMa_TLPC(shkb, ma_dbhc, ma_cqthu, ma_chuong, ma_ndkt, tk_co, index);
	}
	else {
		var ma_chuong = document.forms[0].ttct_chuong[index].value;
		var ma_ndkt = document.forms[0].ttct_noidungkt[index].value;
		if(isNull(ma_chuong)) return;
		if(isNull(ma_ndkt)) return;
		CalMa_TLPC(shkb, ma_dbhc, ma_cqthu, ma_chuong, ma_ndkt, tk_co, index);
	}
	
}

function CalMa_TLPC(shkb, ma_dbhc, ma_cqthu, ma_chuong, ma_ndkt, tk_co, index) {	
	var tbl = document.getElementById('tblGridDetailTable');
    var lastRow = tbl.rows.length-1;      	
	 jQuery.ajax({
		   type: "POST",
		   url: "LoadMaTLPC.do",			  
		   data: "shkb="+shkb+"&"+
		   		 "ma_dbhc="+ma_dbhc+"&" + 
		   		 "ma_cqthu="+ma_cqthu+"&" +
		   		 "ma_chuong="+ma_chuong+"&" +
		   		 "ma_ndkt="+ma_ndkt+"&"+
		   		 "tk_co="+tk_co,
		   success: function(msg){				   
			     if(msg ==null ) return;
			     if(msg.tlpc == null)
		    	 {			    	
		    		 
		    		 if(lastRow > 1) {
		    			 document.forms[0].ttct_tlpc[index].value = '';
			    	 	 document.forms[0].ttct_tlpc[index].focus();
			    	 	 return;
		    		 }else {
		    			 document.forms[0].ttct_tlpc.value = '';
			    	 	 document.forms[0].ttct_tlpc.focus();
			    	 	 return;
		    		 }
		    		 
		    	 }else {			    		
		    		 if(lastRow > 1) {
		    			 document.forms[0].ttct_tlpc[index].value =  msg.tlpc;
			    	 	 document.forms[0].ttct_tlpc[index].focus();
			    	 	 return;
		    		 }else {
		    			 document.forms[0].ttct_tlpc.value =  msg.tlpc;
			    	 	 document.forms[0].ttct_tlpc.focus();
			    	 	 return;
		    		 }
		    	 }
			    
		   }
		 });		
}
function resetForm(){		
	var tbl = document.getElementById('tblGridDetailTable');
    var lastRow = tbl.rows.length;

	document.forms[0].dc_nntien.value="";
	document.forms[0].ma_dbhc.value="";
	document.forms[0].ten_dbhc.value="";
	document.forms[0].tk_no.value="";
	document.forms[0].ttk_no.value="";

	document.forms[0].tk_co.value="";
	document.forms[0].ttk_co.value="";
	document.forms[0].cq_thu.value="";
	document.forms[0].tcq_thu.value="";	
	
	
	document.forms[0].ty_gia.value="1";
	document.forms[0].tkhai_so.value="";

	document.forms[0].ngay_dk.value="";
	document.forms[0].lh_xnk.value="";		
	document.forms[0].tlh_xnk.value="";
	document.forms[0].so_bke.value="";
	
	document.forms[0].so_qd.value="";
	document.forms[0].ngay_qd.value="";
	document.forms[0].cq_qd.value="";
	document.forms[0].dien_giai.value="";

	document.forms[0].ngay_bke.value="";	
	document.forms[0].dc_nnthue_tinh.value="";
	document.forms[0].dc_nnthue_huyen.value="";

	document.forms[0].so_khung.value="";
	document.forms[0].so_may.value="";	
	document.forms[0].tong_tien.value="0";	
	
	
	var length_detail=document.forms[0].ttct_ma_quy.length; 
	if(length_detail>1){
		for(i=0;i<length_detail;i++){
			document.forms[0].ttct_ma_quy[i].value="01";
			document.forms[0].ttct_chuong[i].value="";
			document.forms[0].ttct_noidungkt[i].value="";
			document.forms[0].ttct_tlpc[i].value="";
			document.forms[0].ttct_noidung[i].value="";
			document.forms[0].ttct_tien_nt[i].value="0";
			document.forms[0].ttct_tien[i].value="0";			
			document.forms[0].ttct_khtk[i].value="00";
			
			
			document.forms[0].ttct_chuong[i].readOnly = false;
			document.forms[0].ttct_chuong[i].style.backgroundColor="#FFFFFF";	
			
			document.forms[0].ttct_noidungkt[i].readOnly = false;
			document.forms[0].ttct_noidungkt[i].style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_tlpc[i].readOnly = false;
			document.forms[0].ttct_tlpc[i].style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_noidung[i].readOnly = false;
			document.forms[0].ttct_noidung[i].style.backgroundColor="#FFFFFF";
			
			document.forms[0].ttct_tien_nt[i].readOnly = false;
			document.forms[0].ttct_tien_nt[i].style.backgroundColor="#FFFFFF";
		}
	}
	else{			
		document.forms[0].ttct_ma_quy.value="01";
		document.forms[0].ttct_chuong.value="";
		document.forms[0].ttct_noidungkt.value="";
		document.forms[0].ttct_tlpc.value="";
		document.forms[0].ttct_noidung.value="";
		document.forms[0].ttct_tien_nt.value="0";
		document.forms[0].ttct_tien.value="0";		
		document.forms[0].ttct_khtk.value="00";
		
		document.forms[0].ttct_chuong.readOnly = false;
		document.forms[0].ttct_chuong.style.backgroundColor="#FFFFFF";	
		
		document.forms[0].ttct_noidungkt.readOnly = false;
		document.forms[0].ttct_noidungkt.style.backgroundColor="#FFFFFF";
		
		document.forms[0].ttct_tlpc.readOnly = false;
		document.forms[0].ttct_tlpc.style.backgroundColor="#FFFFFF";
		
		document.forms[0].ttct_noidung.readOnly = false;
		document.forms[0].ttct_noidung.style.backgroundColor="#FFFFFF";
		
		document.forms[0].ttct_tien_nt.readOnly = false;
		document.forms[0].ttct_tien_nt.style.backgroundColor="#FFFFFF";
	}
}
function LoadFormGom()
{
	 document.TcsLapChungTuForm.action = '/TcsGomBL.do' ;
     document.TcsLapChungTuForm.submit();
}
function cal_noidung_(src) {
 	var row = src.parentElement.parentElement;

    var rowIndex = parseInt(row.rowIndex)-1;	     
    cal_noidung(rowIndex);
}
function GetThongTinTK() {
	var ma_lthue = document.forms[0].loai_thue.value;
	var ma_thue = document.forms[0].ma_thue.value;
	if(isNull(ma_lthue)) return;
	if(ma_lthue != '04') {
		return;
	}	
	
	if(isNull(ma_thue)) return;
	if(ma_thue.length < 10) return;
	
	jQuery.ajax({
		   type: "POST",
		   url: "LoadThongTinTK.do",			  
		   data: "ma_thue="+ma_thue,
		   success: function(msg){	
			     if(msg == null ) return;			    
			     var len = msg.p_cur.length;
			   
			     var data = msg.p_cur;
			     for(i = 0; i < len; i++) {			    	 
			    	 listToKhai[i] =  data[i].so_tk +"-" +data[i].ngay_tk;
			     }			    			     
		   }
		 });	
	
}
function LoadMLNS() {
	
	var tbl = document.getElementById('tblGridDetailTable');
	var lastRow = tbl.rows.length - 1;
	
	
	var ma_thue = document.forms[0].ma_thue.value;	
	var ma_lthue = document.forms[0].loai_thue.value;	
	var so_tk = document.forms[0].tkhai_so.value;
	var ngay_dk = document.forms[0].ngay_dk.value;
	var lh_xnk = document.forms[0].tlh_xnk.value;
	var cq_thu = document.forms[0].cq_thu.value;
	
	
	if(isNull(ma_lthue)) return;
	if(ma_lthue != '04') return;
	
	if(isNull(ma_thue)) return;
	if(isNull(so_tk)) return;
	if(isNull(ngay_dk)) return;
	if(isNull(lh_xnk)) return;
	if(isNull(cq_thu)) return;
	if(cq_thu.length < 7) return;
	
	jQuery.ajax({
		   type: "POST",
		   url: "LoadMLNS.do",			  
		   data: "ma_thue="+ma_thue + "&so_tk="+so_tk + "&ngay_dk="+ngay_dk + "&lh_xnk="+lh_xnk + "&cq_thu="+cq_thu,
		   success: function(msg){				  
			     if(msg == null ) return;				       
			        var nguon_dl = msg.nguon_dl;
			        if(nguon_dl == null) return;			        
				    var lengthDetail = msg.dtl_data.length;			    
				    if(lengthDetail == 1)
			    	{
				    	   
				    		var dataObj = msg.dtl_data[0];			    		
				    	 	var ma_nnt = dataObj.ma_nnt;
						    var ten_nnt = dataObj.ten_nnt;
						    
						    var dc_nnt = dataObj.dia_chi;			   
						    var ma_dbhc = dataObj.ma_dbhc;
						   
						    var ten_dbhc = dataObj.ten_dbhc;
						    var ma_cqthu = dataObj.ma_cqthu;
						    var ten_cqthu = dataObj.ten_cqthu;
						    var so_tk = dataObj.so_tk;
						    var ngay_tk = dataObj.ngay_tk;
						    var lhxnk = dataObj.lhxnk;
						    var ten_vt_lhxnk = dataObj.ten_vt_lhxnk;
						    var ten_lhxnk = dataObj.ten_lhxnk;
						    
						    var ma_quy = dataObj.ma_quy;
						    var ma_chuong = dataObj.ma_chuong;
						    var ma_ndkt = dataObj.ma_ndkt;
						    var noi_dung = dataObj.noi_dung;
						    var so_tien = toFormatNumberDe_TCS(dataObj.so_tien);		    
						    var ma_chuong = dataObj.ma_chuong;
						    var tk_ns = dataObj.tk_ns;
						    var ky_thue = dataObj.ky_thue;
						    var so_qd = dataObj.so_qd;
						    var ngay_qd = dataObj.ngay_qd;
						    var ma_hq_ph = dataObj.ma_hq_ph;
						    
						    
						    if(ma_nnt != null) {			    	
								document.forms[0].ma_thue.value = ma_nnt;
						    }
						    if(ten_nnt != null)
								document.forms[0].ten_thue.value = ten_nnt;
							if(dc_nnt != null) 
								document.forms[0].dc_nnthue.value = dc_nnt;
							if(ma_dbhc != null) 
								document.forms[0].ma_dbhc.value = ma_dbhc;
							if(ten_dbhc != null) 
								document.forms[0].ten_dbhc.value = ten_dbhc;
							if(ma_cqthu != null && ma_cqthu.length > 0) 
								document.forms[0].cq_thu.value = ma_cqthu;
								if(nguon_dl == 4) {
									document.forms[0].cq_thu.readOnly = true;
									document.forms[0].cq_thu.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].cq_thu.readOnly = false;
									document.forms[0].cq_thu.style.backgroundColor = "#FFFFFF";
								}
							if(ten_cqthu != null && ten_cqthu.length > 0) {
								document.forms[0].tcq_thu.value = ten_cqthu;					
							}
							if(so_tk != null && so_tk.length > 0) {
								document.forms[0].tkhai_so.value = so_tk;
								if(nguon_dl == 4) {
									document.forms[0].tkhai_so.readOnly = true;
									document.forms[0].tkhai_so.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].tkhai_so.readOnly = false;
									document.forms[0].tkhai_so.style.backgroundColor = "#FFFFFF";
								}
							}
							if(ngay_tk != null && ngay_tk.length > 0) {
								document.forms[0].ngay_dk.value = ngay_tk;
								if(nguon_dl == 4) {
									document.forms[0].ngay_dk.readOnly = true;
									document.forms[0].ngay_dk.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].ngay_dk.readOnly = false;
									document.forms[0].ngay_dk.style.backgroundColor = "#FFFFFF";
								}
							}
							if(ten_vt_lhxnk != null && ten_vt_lhxnk.length > 0) {
								document.forms[0].lh_xnk.value = ten_vt_lhxnk;
								if(nguon_dl == 4) {
									document.forms[0].lh_xnk.readOnly = true;
									document.forms[0].lh_xnk.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].lh_xnk.readOnly = false;
									document.forms[0].lh_xnk.style.backgroundColor = "#FFFFFF";
								}
							}
							if(lhxnk != null && ten_lhxnk !=null)
								document.forms[0].tlh_xnk.value = lhxnk + "-" + ten_lhxnk;			
							if(tk_ns != null) {
								document.forms[0].tk_co.value = tk_ns;
							}
							if(so_qd != null) {
								document.forms[0].so_qd.value = so_qd;
							}
							if(ngay_qd != null) {
								document.forms[0].ngay_qd.value = ngay_qd;
							}
							if(ma_quy != null)
								document.forms[0].ttct_ma_quy.value = ma_quy;
							if(ma_chuong != null)
								document.forms[0].ttct_chuong.value = ma_chuong;
							if(ma_ndkt != null)
								document.forms[0].ttct_noidungkt.value = ma_ndkt;
							if(noi_dung != null)
								document.forms[0].ttct_noidung.value = noi_dung;
							if(so_tien != null) {
								document.forms[0].ttct_tien_nt.value = so_tien;
								document.forms[0].ttct_tien.value = so_tien;
								
								document.forms[0].tong_tien.value = so_tien;
								document.forms[0].tong_tien_nt.value = so_tien;					
							}						
							if(ky_thue != null) {
								document.forms[0].ttct_ky_thue.value = ky_thue;
							}
							if(lastRow > 2){
								for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){								
									document.all("tblGridDetailTable").deleteRow(rowdelete);
							    }
						    }
							if(ma_chuong != null && ma_chuong.length > 0) {
								
								document.forms[0].ttct_chuong.value = ma_chuong;					
								if(nguon_dl == 1 || nguon_dl == 3) {												
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
									document.getElementById("id_chuong_row1").readOnly = false;						
								}else {
									document.getElementById("id_chuong_row1").readOnly = false;		
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
								}
							}	
			    	}else {
			    		
			    		var dataObj = msg.dtl_data[0];			    		
			    	 	var ma_nnt = dataObj.ma_nnt;
					    var ten_nnt = dataObj.ten_nnt;
					    
					    var dc_nnt = dataObj.dia_chi;			   
					    var ma_dbhc = dataObj.ma_dbhc;
					   
					    var ten_dbhc = dataObj.ten_dbhc;
					    var ma_cqthu = dataObj.ma_cqthu;
					    var ten_cqthu = dataObj.ten_cqthu;
					    var so_tk = dataObj.so_tk;
					    var ngay_tk = dataObj.ngay_tk;
					    var lhxnk = dataObj.lhxnk;
					    var ten_vt_lhxnk = dataObj.ten_vt_lhxnk;
					    var ten_lhxnk = dataObj.ten_lhxnk;
					    
					    var ma_quy = dataObj.ma_quy;
					    var ma_chuong = dataObj.ma_chuong;
					    var ma_ndkt = dataObj.ma_ndkt;
					    var noi_dung = dataObj.noi_dung;
					    var so_tien = toFormatNumberDe_TCS(dataObj.so_tien);		    
					    var ma_chuong = dataObj.ma_chuong;
					    var tk_ns = dataObj.tk_ns;
					    var ky_thue = dataObj.ky_thue;
					    var so_qd = dataObj.so_qd;
					    var ngay_qd = dataObj.ngay_qd;
					    var ma_hq_ph = dataObj.ma_hq_ph;
					    
					    if(ma_nnt != null) {			    	
							document.forms[0].ma_thue.value = ma_nnt;
					    }
					    if(ten_nnt != null)
							document.forms[0].ten_thue.value = ten_nnt;
						if(dc_nnt != null) 
							document.forms[0].dc_nnthue.value = dc_nnt;
						if(ma_dbhc != null) 
							document.forms[0].ma_dbhc.value = ma_dbhc;
						if(ten_dbhc != null) 
							document.forms[0].ten_dbhc.value = ten_dbhc;
						if(ma_cqthu != null) 
							document.forms[0].cq_thu.value = ma_cqthu;
							if(nguon_dl == 4) {
								document.forms[0].cq_thu.style.readOnly = true;
								document.forms[0].cq_thu.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].cq_thu.style.readOnly = false;
								document.forms[0].cq_thu.style.backgroundColor = "#FFFFFF";
							}
						if(ten_cqthu != null) {
							document.forms[0].tcq_thu.value = ten_cqthu;					
						}
						if(so_tk != null) {
							document.forms[0].tkhai_so.value = so_tk;
							if(nguon_dl == 4) {
								document.forms[0].tkhai_so.style.readOnly = true;
								document.forms[0].tkhai_so.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].tkhai_so.style.readOnly = false;
								document.forms[0].tkhai_so.style.backgroundColor = "#FFFFFF";
							}
						}
						if(ngay_tk != null) {
							document.forms[0].ngay_dk.value = ngay_tk;
							if(nguon_dl == 4) {
								document.forms[0].ngay_dk.style.readOnly = true;
								document.forms[0].ngay_dk.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].ngay_dk.style.readOnly = false;
								document.forms[0].ngay_dk.style.backgroundColor = "#FFFFFF";
							}
						}
						if(ten_vt_lhxnk != null) {
							document.forms[0].lh_xnk.value = ten_vt_lhxnk;
							if(nguon_dl == 4) {
								document.forms[0].lh_xnk.style.readOnly = true;
								document.forms[0].lh_xnk.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].lh_xnk.style.readOnly = false;
								document.forms[0].lh_xnk.style.backgroundColor = "#FFFFFF";
							}
						}
						if(lhxnk != null && ten_lhxnk !=null)
							document.forms[0].tlh_xnk.value = lhxnk + "-" + ten_lhxnk;			
						if(tk_ns != null) {
							document.forms[0].tk_co.value = tk_ns;
						}
						if(so_qd != null) {
							document.forms[0].so_qd.value = so_qd;
						}
						if(ngay_qd != null) {
							document.forms[0].ngay_qd.value = ngay_qd;
						}
						if(ma_quy != null)
							document.forms[0].ttct_ma_quy.value = ma_quy;
						if(ma_chuong != null)
							document.forms[0].ttct_chuong.value = ma_chuong;
						if(ma_ndkt != null)
							document.forms[0].ttct_noidungkt.value = ma_ndkt;
						if(noi_dung != null)
							document.forms[0].ttct_noidung.value = noi_dung;
						if(so_tien != null) {
							document.forms[0].ttct_tien_nt.value = so_tien;
							document.forms[0].ttct_tien.value = so_tien;
							
							document.forms[0].tong_tien.value = so_tien;
							document.forms[0].tong_tien_nt.value = so_tien;					
						}						
						if(ky_thue != null) {
							document.forms[0].ttct_ky_thue.value = ky_thue;
						}
						if(lastRow > 2){
							for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){							
								document.all("tblGridDetailTable").deleteRow(rowdelete);
						    }
					    }
						
						for(ik = 1; ik < lengthDetail; ik++){
							
							OpenerAddDetailRowForQuery(msg.dtl_data[ik].ma_chuong, msg.dtl_data[ik].ma_ndkt, msg.dtl_data[ik].noi_dung,toFormatNumberDe_TCS(msg.dtl_data[ik].so_tien), msg.dtl_data[ik].ky_thue);	
						}
						/**
							Cong tong tien
						*/
						var tong_tien = 0;
						for(count_detail=0;count_detail<lengthDetail;count_detail++){
						 	try{						 		
						 		tong_tien = tong_tien + parseFloat(toNumber2(msg.dtl_data[count_detail].so_tien));
						 	}catch(e){
						 		alert(e);
						 	}
						 }
						
						 document.getElementById('id_tong_tien_nt').value=toFormatNumberDe_TCS(tong_tien);					 
						 document.getElementById('id_tong_tien').value=toFormatNumberDe_TCS(tong_tien);	
						 
						 if(ma_chuong != null && ma_chuong.length > 0) {
								document.forms[0].ttct_chuong.value = ma_chuong;					
								if(nguon_dl == 1 || nguon_dl == 3) {												
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
									document.getElementById("id_chuong_row1").readOnly = false;						
								}else {
									document.getElementById("id_chuong_row1").readOnly = false;		
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
								}
							}	
			    	}
						
					document.forms[0].ma_hq_ph.value = ma_hq_ph;
					document.forms[0].nguon_dl.value = nguon_dl;		
					document.forms[0].ten_thue.focus();
					
			     		    
		   }
		 });	
}
function LoadDetailCTu() {
	var tbl = document.getElementById('tblGridDetailTable');
	var lastRow = tbl.rows.length - 1;
	
	var ma_thue = document.forms[0].ma_thue.value;	
	var ma_lthue = document.forms[0].loai_thue.value;	
	var so_tk = document.forms[0].tkhai_so.value;	
	
	if(isNull(ma_lthue)) return;
	if(ma_lthue != '04') return;
	
	if(isNull(ma_thue)) return;
	if(isNull(so_tk)) return;
	
	jQuery.ajax({
		   type: "POST",
		   url: "LoadDetailCTu.do",			  
		   data: "ma_thue="+ma_thue + "&so_tk="+so_tk,
		   success: function(msg){				  
			     if(msg == null ) return;				       
			        var nguon_dl = msg.nguon_dl;
			        if(nguon_dl == null) return;			        
				    var lengthDetail = msg.dtl_data.length;			    
				    if(lengthDetail == 1)
			    	{
				    	   
				    		var dataObj = msg.dtl_data[0];			    		
				    	 	var ma_nnt = dataObj.ma_nnt;
						    var ten_nnt = dataObj.ten_nnt;
						    
						    var dc_nnt = dataObj.dia_chi;			   
						    var ma_dbhc = dataObj.ma_dbhc;
						   
						    var ten_dbhc = dataObj.ten_dbhc;
						    var ma_cqthu = dataObj.ma_cqthu;
						    var ten_cqthu = dataObj.ten_cqthu;
						    var so_tk = dataObj.so_tk;
						    var ngay_tk = dataObj.ngay_tk;
						    var lhxnk = dataObj.lhxnk;
						    var ten_vt_lhxnk = dataObj.ten_vt_lhxnk;
						    var ten_lhxnk = dataObj.ten_lhxnk;
						    
						    var ma_quy = dataObj.ma_quy;
						    var ma_chuong = dataObj.ma_chuong;
						    var ma_ndkt = dataObj.ma_ndkt;
						    var noi_dung = dataObj.noi_dung;
						    var so_tien = toFormatNumberDe_TCS(dataObj.so_tien);		    
						    var ma_chuong = dataObj.ma_chuong;
						    var tk_ns = dataObj.tk_ns;
						    var ky_thue = dataObj.ky_thue;
						    var so_qd = dataObj.so_qd;
						    var ngay_qd = dataObj.ngay_qd;
						    var ma_hq_ph = dataObj.ma_hq_ph;
						    var ma_hq = dataObj.ma_hq;
						    
						    if(ma_nnt != null) {			    	
								document.forms[0].ma_thue.value = ma_nnt;
						    }
						    if(ten_nnt != null)
								document.forms[0].ten_thue.value = ten_nnt;
							if(dc_nnt != null) 
								document.forms[0].dc_nnthue.value = dc_nnt;
							if(ma_dbhc != null) 
								document.forms[0].ma_dbhc.value = ma_dbhc;
							if(ten_dbhc != null) 
								document.forms[0].ten_dbhc.value = ten_dbhc;
							if(ma_cqthu != null && ma_cqthu.length > 0) 
								document.forms[0].cq_thu.value = ma_cqthu;
								if(nguon_dl == 4) {
									document.forms[0].cq_thu.readOnly = true;
									document.forms[0].cq_thu.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].cq_thu.readOnly = false;
									document.forms[0].cq_thu.style.backgroundColor = "#FFFFFF";
								}
							if(ten_cqthu != null && ten_cqthu.length > 0) {
								document.forms[0].tcq_thu.value = ten_cqthu;					
							}
							if(so_tk != null && so_tk.length > 0) {
								document.forms[0].tkhai_so.value = so_tk;
								if(nguon_dl == 4) {
									document.forms[0].tkhai_so.readOnly = true;
									document.forms[0].tkhai_so.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].tkhai_so.readOnly = false;
									document.forms[0].tkhai_so.style.backgroundColor = "#FFFFFF";
								}
							}
							if(ngay_tk != null && ngay_tk.length > 0) {
								document.forms[0].ngay_dk.value = ngay_tk;
								if(nguon_dl == 4) {
									document.forms[0].ngay_dk.readOnly = true;
									document.forms[0].ngay_dk.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].ngay_dk.readOnly = false;
									document.forms[0].ngay_dk.style.backgroundColor = "#FFFFFF";
								}
							}
							if(ten_vt_lhxnk != null && ten_vt_lhxnk.length > 0) {
								document.forms[0].lh_xnk.value = ten_vt_lhxnk;
								if(nguon_dl == 4) {
									document.forms[0].lh_xnk.readOnly = true;
									document.forms[0].lh_xnk.style.backgroundColor = "#F0F0F0";
								}else {
									document.forms[0].lh_xnk.readOnly = false;
									document.forms[0].lh_xnk.style.backgroundColor = "#FFFFFF";
								}
							}
							if(lhxnk != null && ten_lhxnk !=null)
								document.forms[0].tlh_xnk.value = lhxnk + "-" + ten_lhxnk;			
							if(tk_ns != null) {
								document.forms[0].tk_co.value = tk_ns;
							}
							if(so_qd != null) {
								document.forms[0].so_qd.value = so_qd;
							}
							if(ngay_qd != null) {
								document.forms[0].ngay_qd.value = ngay_qd;
							}
							if(ma_quy != null)
								document.forms[0].ttct_ma_quy.value = ma_quy;
							if(ma_chuong != null)
								document.forms[0].ttct_chuong.value = ma_chuong;
							if(ma_ndkt != null)
								document.forms[0].ttct_noidungkt.value = ma_ndkt;
							if(noi_dung != null)
								document.forms[0].ttct_noidung.value = noi_dung;
							if(so_tien != null) {
								document.forms[0].ttct_tien_nt.value = so_tien;
								document.forms[0].ttct_tien.value = so_tien;
								
								document.forms[0].tong_tien.value = so_tien;
								document.forms[0].tong_tien_nt.value = so_tien;					
							}						
							if(ky_thue != null) {
								document.forms[0].ttct_ky_thue.value = ky_thue;
							}
							if(lastRow > 2){
								for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){								
									document.all("tblGridDetailTable").deleteRow(rowdelete);
							    }
						    }
							if(ma_chuong != null && ma_chuong.length > 0) {
								
								document.forms[0].ttct_chuong.value = ma_chuong;					
								if(nguon_dl == 1 || nguon_dl == 3) {												
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
									document.getElementById("id_chuong_row1").readOnly = false;						
								}else {
									document.getElementById("id_chuong_row1").readOnly = false;		
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
								}
							}	
			    	}else {
			    		
			    		var dataObj = msg.dtl_data[0];			    		
			    	 	var ma_nnt = dataObj.ma_nnt;
					    var ten_nnt = dataObj.ten_nnt;
					    
					    var dc_nnt = dataObj.dia_chi;			   
					    var ma_dbhc = dataObj.ma_dbhc;
					   
					    var ten_dbhc = dataObj.ten_dbhc;
					    var ma_cqthu = dataObj.ma_cqthu;
					    var ten_cqthu = dataObj.ten_cqthu;
					    var so_tk = dataObj.so_tk;
					    var ngay_tk = dataObj.ngay_tk;
					    var lhxnk = dataObj.lhxnk;
					    var ten_vt_lhxnk = dataObj.ten_vt_lhxnk;
					    var ten_lhxnk = dataObj.ten_lhxnk;
					    
					    var ma_quy = dataObj.ma_quy;
					    var ma_chuong = dataObj.ma_chuong;
					    var ma_ndkt = dataObj.ma_ndkt;
					    var noi_dung = dataObj.noi_dung;
					    var so_tien = toFormatNumberDe_TCS(dataObj.so_tien);		    
					    var ma_chuong = dataObj.ma_chuong;
					    var tk_ns = dataObj.tk_ns;
					    var ky_thue = dataObj.ky_thue;
					    var so_qd = dataObj.so_qd;
					    var ngay_qd = dataObj.ngay_qd;
					    var ma_hq_ph = dataObj.ma_hq_ph;
					    var ma_hq = dataObj.ma_hq_ph;
					    
					    if(ma_nnt != null) {			    	
							document.forms[0].ma_thue.value = ma_nnt;
					    }
					    if(ten_nnt != null)
							document.forms[0].ten_thue.value = ten_nnt;
						if(dc_nnt != null) 
							document.forms[0].dc_nnthue.value = dc_nnt;
						if(ma_dbhc != null) 
							document.forms[0].ma_dbhc.value = ma_dbhc;
						if(ten_dbhc != null) 
							document.forms[0].ten_dbhc.value = ten_dbhc;
						if(ma_cqthu != null) 
							document.forms[0].cq_thu.value = ma_cqthu;
							if(nguon_dl == 4) {
								document.forms[0].cq_thu.style.readOnly = true;
								document.forms[0].cq_thu.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].cq_thu.style.readOnly = false;
								document.forms[0].cq_thu.style.backgroundColor = "#FFFFFF";
							}
						if(ten_cqthu != null) {
							document.forms[0].tcq_thu.value = ten_cqthu;					
						}
						if(so_tk != null) {
							document.forms[0].tkhai_so.value = so_tk;
							if(nguon_dl == 4) {
								document.forms[0].tkhai_so.style.readOnly = true;
								document.forms[0].tkhai_so.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].tkhai_so.style.readOnly = false;
								document.forms[0].tkhai_so.style.backgroundColor = "#FFFFFF";
							}
						}
						if(ngay_tk != null) {
							document.forms[0].ngay_dk.value = ngay_tk;
							if(nguon_dl == 4) {
								document.forms[0].ngay_dk.style.readOnly = true;
								document.forms[0].ngay_dk.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].ngay_dk.style.readOnly = false;
								document.forms[0].ngay_dk.style.backgroundColor = "#FFFFFF";
							}
						}
						if(ten_vt_lhxnk != null) {
							document.forms[0].lh_xnk.value = ten_vt_lhxnk;
							if(nguon_dl == 4) {
								document.forms[0].lh_xnk.style.readOnly = true;
								document.forms[0].lh_xnk.style.backgroundColor = "#F0F0F0";
							}else {
								document.forms[0].lh_xnk.style.readOnly = false;
								document.forms[0].lh_xnk.style.backgroundColor = "#FFFFFF";
							}
						}
						if(lhxnk != null && ten_lhxnk !=null)
							document.forms[0].tlh_xnk.value = lhxnk + "-" + ten_lhxnk;			
						if(tk_ns != null) {
							document.forms[0].tk_co.value = tk_ns;
						}
						if(so_qd != null) {
							document.forms[0].so_qd.value = so_qd;
						}
						if(ngay_qd != null) {
							document.forms[0].ngay_qd.value = ngay_qd;
						}
						if(ma_quy != null)
							document.forms[0].ttct_ma_quy.value = ma_quy;
						if(ma_chuong != null)
							document.forms[0].ttct_chuong.value = ma_chuong;
						if(ma_ndkt != null)
							document.forms[0].ttct_noidungkt.value = ma_ndkt;
						if(noi_dung != null)
							document.forms[0].ttct_noidung.value = noi_dung;
						if(so_tien != null) {
							document.forms[0].ttct_tien_nt.value = so_tien;
							document.forms[0].ttct_tien.value = so_tien;
							
							document.forms[0].tong_tien.value = so_tien;
							document.forms[0].tong_tien_nt.value = so_tien;					
						}						
						if(ky_thue != null) {
							document.forms[0].ttct_ky_thue.value = ky_thue;
						}
						if(lastRow > 2){
							for(rowdelete=lastRow-1;rowdelete>=2;rowdelete--){							
								document.all("tblGridDetailTable").deleteRow(rowdelete);
						    }
					    }
						
						for(ik = 1; ik < lengthDetail; ik++){
							
							OpenerAddDetailRowForQuery(msg.dtl_data[ik].ma_chuong, msg.dtl_data[ik].ma_ndkt, msg.dtl_data[ik].noi_dung,toFormatNumberDe_TCS(msg.dtl_data[ik].so_tien),msg.dtl_data[ik].ky_thue);	
						}
						/**
							Cong tong tien
						*/
						var tong_tien = 0;
						for(count_detail=0;count_detail<lengthDetail;count_detail++){
						 	try{						 		
						 		tong_tien = tong_tien + parseFloat(toNumber2(msg.dtl_data[count_detail].so_tien));
						 	}catch(e){
						 		alert(e);
						 	}
						 }
						
						 document.getElementById('id_tong_tien_nt').value=toFormatNumberDe_TCS(tong_tien);					 
						 document.getElementById('id_tong_tien').value=toFormatNumberDe_TCS(tong_tien);	
						 
						 if(ma_chuong != null && ma_chuong.length > 0) {
								document.forms[0].ttct_chuong.value = ma_chuong;					
								if(nguon_dl == 1 || nguon_dl == 3) {												
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
									document.getElementById("id_chuong_row1").readOnly = false;						
								}else {
									document.getElementById("id_chuong_row1").readOnly = false;		
									document.getElementById("id_chuong_row1").style.backgroundColor = "#FFFFFF";		
								}
							}	
			    	}
						
					document.forms[0].ma_hq_ph.value = ma_hq_ph;
					document.forms[0].ma_hq.value = ma_hq;					
					document.forms[0].nguon_dl.value = nguon_dl;		
					document.forms[0].ten_thue.focus();					
			     		    
		   }
		 });	
	
	
	
}
function cal_sotien2(src) {
	
    var row = src.parentElement.parentElement;

    var rowIndex = parseInt(row.rowIndex)-1;	  
   
    cal_sotien(rowIndex);

} 
function sbPrint(shkb, ngay_kb, ma_nv, so_bt, ma_dthu){			
	
	var width  = screen.availWidth-10;
 	var height = screen.availHeight;
	var left   = 0;
 	var top    = 0;
 	var params = 'width='+width+', height='+height;
 	params += ', top='+top+', left='+left;
 	params += ', directories=no';
 	params += ', location=no';
 	params += ', menubar=no';
 	params += ', resizable=yes';
 	params += ', scrollbars=yes';
 	params += ', status=no';
 	params += ', toolbar=no';
	p_keyctu  = 'ngay_kb='+ngay_kb;
	p_keyctu += '&ma_nv='+ma_nv;
	p_keyctu += '&so_bt='+so_bt;
	p_keyctu += '&ma_dthu='+ma_dthu;
	p_keyctu += '&shkb='+shkb;						
	p_link = '/print_GNT_ByKeyCTU.do?' + p_keyctu;
	window.open('' + p_link,'',params);
} 	
function sbExitChungTu() {	 
	  if(confirm('Bạn có chắc chắn thoát kh�?i chức năng này?')==false)
        return false;
     else {             
        window.location="/Index.jsp";
     }
}
function setLocation()
{
	window.location.href="#thebottom";
	document.forms[0].ttct_ma_quy.focus();
}
function clickButton(e, buttonid, currentPageSv )
{  
  var evt = e ? e : window.event;  
  var bt = document.getElementById(buttonid);  
  
  if (bt){
      if (evt.keyCode == 13){   
    	    getCurrentPage(currentPageSv);            
      }        
  }   
}  
function getCurrentPage(currentPageSv) {	
	var currentpage = document.forms[0].currentPage.value;
	var totalPage = document.forms[0].totalPages.value;	
	
	if(typeof(currentpage) == 'string') 
		currentpage = parseInt(currentpage);
	if(typeof(totalPage) == 'string')	
		totalPage = parseInt(totalPage);	
	if(typeof(currentPageSv) == 'string')	
		currentPageSv = parseInt(currentPageSv);	
	if(currentpage == currentPageSv) return;
	if(currentpage <= 0) {
		alert('Số trang không hợp lệ');
		document.forms[0].currentPage.focus();
		return;
	}
	if(currentpage > totalPage) {		
		alert('Số trang không hợp lệ');
		document.forms[0].currentPage.focus();
		return;
	}
	document.forms[0].__action.value="page";
    document.forms[0].pageStatus.value="enterPage";   
    document.forms[0].submit();
}
function getCurrentPage1(currentPageSv) {	
	
	var currentpage = document.forms[0].currentPage.value;
	var totalPage = document.forms[0].totalPages.value;	
	
	
	if(typeof(currentpage) == 'string') 
		currentpage = parseInt(currentpage);
	if(typeof(totalPage) == 'string')	
		totalPage = parseInt(totalPage);
	if(typeof(currentPageSv)=='string')
		currentPageSv = parseInt(currentPageSv);	
	if(currentpage == currentPageSv) return;
	if(currentpage <= 0) {		
		document.forms[0].currentPage.focus();
		return;
	}
	if(currentpage > totalPage) {	
		document.forms[0].currentPage.focus();
		return;
	}
	document.forms[0].__action.value="page";
    document.forms[0].pageStatus.value="enterPage";   
    document.forms[0].submit();
}
function fncAddDetail(e, buttonid){	
	
	var curElement = document.activeElement;
	var elementName = curElement.name;	
	
    var evt = e ? e : window.event;  
    var bt = document.getElementById(buttonid);  
  
    if (bt){		 
      if (evt.keyCode == 13){   	    
    	  //////////
    	  if(!document.getElementById('id_khtk_row1').readOnly) {
    		  addDetailRow();        
    	  }
      }   
    } 
	
}
function formatNumberJQuery(obj) {	
	
	var ma_nt = document.forms[0].ma_nt.value;	
	
	obj.value = ReplaceAll(obj.value, ' ', '' );	
	if(isNaN(obj.value)) {		
		obj.value = '';
		return;
	}		
	var final_value = obj.value;		
	
	var formated_value = 0;
	try {
		if(ma_nt == 'VND') {
			formated_value = $().number_format(final_value, {
			      numberOfDecimals:0,
			      decimalSeparator: '.',
			      thousandSeparator: ' ',
			      symbol: ''});	
		}
		else {
			formated_value = $().number_format(final_value, {
			      numberOfDecimals:2,
			      decimalSeparator: '.',
			      thousandSeparator: ' ',
			      symbol: ''});	
		}
	}catch (e) {
		formated_value = '';
	}		
	if(formated_value == '0.00') {
		formated_value = '';
	}
	if(formated_value == '0') {
		formated_value = '';
	}
	
	obj.value = formated_value;
}


function formatNumberTCS(final_value) {	
	var ma_nt = document.forms[0].ma_nt.value;
	final_value = ReplaceAll(final_value,' ','');	
	if(isNaN(final_value)) {
		obj.value = '';
		return;
	}			
	
	var formated_value = 0;
	try {
		if(ma_nt == 'VND') {
		  formated_value = $().number_format(final_value, {
		      numberOfDecimals:0,
		      decimalSeparator: '.',
		      thousandSeparator: ' ',
		      symbol: ''});	
		} else {
			formated_value = $().number_format(final_value, {
		      numberOfDecimals:2,
		      decimalSeparator: '.',
		      thousandSeparator: ' ',
		      symbol: ''});	
		}
	}catch (e) {
		formated_value = '';
	}	
	if(formated_value == '0.00') {
		formated_value = '';
	}
	if(formated_value == '0') {
		formated_value = '';
	}
	
	return formated_value;
}

var chuongIndex = 0;
var ndktIndex = 0;

function selectId(src) {
	chuongIndex = 0;
    var row = src.parentElement.parentElement;

    var rowIndex = parseInt(row.rowIndex)-1;	
    
    chuongIndex = rowIndex;
} 
function selectIdNdkt(src) {
	ndktIndex = 0;
    var row = src.parentElement.parentElement;

    var rowIndex = parseInt(row.rowIndex)-1;	
    
    ndktIndex = rowIndex;
}
function fncCheckAjax(ma_cqthu) {
	jQuery.ajax({
		   type: "POST",
		   url: "CheckMaCQThu.do",			  
		   data: "ma_cqthu"+ma_cqthu,		   		
		   success: function(msg){				  
			     if(msg == null ) return;			     
			     if(table == 'TCS_DM_KHOBAC'){
			    	 if(msg.ten != null) {
				    	 
			    	 }
			    	 else {
			    		 alert('Mã cơ quan thu không tồn tại');
			    		 return;
			    	 }
			    	 
			    	 
			     }
		   }
	});
}
function LoadMaHQ_PH() {
	var ma_thue = document.forms[0].ma_thue.value;	
	var tkhai_so = document.forms[0].tkhai_so.value;	
	var ngay_dk = document.forms[0].ngay_dk.value;
	var lhxnk = document.forms[0].lh_xnk.value;
	var ma_cqthu = document.forms[0].cq_thu.value;
	if(isNull(ma_thue)) return;
	if(isNull(tkhai_so)) return;
	if(isNull(ngay_dk)) return;
	if(isNull(lhxnk)) return;
	if(isNull(ma_cqthu)) return;
	
	jQuery.ajax({
		   type: "POST",
		   url: "LoadMaHQ_PH.do",			  
		   data: 
			 "ma_thue="+ma_thue+"&"+
	   		 "tkhai_so="+tkhai_so+"&"+
	   		 "ngay_dk="+ngay_dk+"&"+
	   		 "ma_cqthu="+ma_cqthu+"&"+
	   		 "lhxnk="+lhxnk,		   		
	   		 success: function(msg){				  
			     alert(msg);
		   }
	});
}