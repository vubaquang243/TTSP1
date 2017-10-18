<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.tracuusodu.TraCuuSoDuVO"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/quyettoan.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<script type="text/javascript">
  jQuery.noConflict();
  
  
  jQuery(document).ready(function () {    
      
      jQuery("#dialog-form-lov-dm").dialog({
          autoOpen: false,resizable : false,
          maxHeight: "700px",
          width: "550px",
          modal: true
      });
      if(jQuery("#kb_tinh").val() == '0003'){
          try{
            jQuery("#kb_huyen").val("0003");
          }catch(ex){}
      }
      
      jQuery("#btn_Thoat").click(function () {          
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
          jQuery(this).dialog("close");
      });
      jQuery("#btnTra_cuu").click(function(){
        var ma_KB_tinh = jQuery("#id_kho_bac_tinh").val();
        if(ma_KB_tinh == "")
        ma_KB_tinh = "000";
        document.forms[0].action = "traCuuSoDu.do?maKB=" + ma_KB_tinh;
        document.forms[0].submit();
      });
      
      jQuery('.updateRecord').click(function(){
        var so_du = jQuery(this).closest("tr").find('#soDu').text().trim();
        var ma_kb = jQuery(this).closest("tr").find('#ma_kb').text().trim();
        var ma_nh = jQuery(this).closest("tr").find('#ma_nh').text().trim();
        var loai_tien = jQuery(this).closest("tr").find('#loaiTien').text().trim();
        var ngay_gd = jQuery(this).closest("tr").find('#ngay_giao_dich').text().trim();
        var so_du_cot = jQuery(this).closest("tr").find('#soDuCOT').text().trim();        
        document.forms[0].action = "updateSoDuAction.do?so_du="+so_du+"&ma_kb="+ma_kb+"&ma_nh="+ma_nh+"&loai_tien="+loai_tien+"&ngay_gd="+ngay_gd+"&so_du_cot="+so_du_cot;
        document.forms[0].submit();
      });
      
     jQuery('.deleteRecord').click(function(){
         var ma_kb = jQuery(this).closest("tr").find('#ma_kb').text().trim();
         var ma_nh = jQuery(this).closest("tr").find('#ma_nh').text().trim();
         var loai_tien = jQuery(this).closest("tr").find('#loaiTien').text().trim();
         var ngay_gd = jQuery(this).closest("tr").find('#ngay_giao_dich').text().trim();
         jQuery(this).closest("tr").addClass('delete');
         jQuery.ajax({
          type : "POST",
          url : "deleteSoDu.do",
          data : {"ma_kb" : ma_kb, "ma_nh" : ma_nh, "loai_tien" : loai_tien, "ngay_gd" : ngay_gd},
          success : function(data, textstatus){
              if(data != null){
                var result = new Object();
                result = JSON.parse(data[0]);
                if(result == "success"){
                alert("Xóa bản ghi thành công");
                  jQuery('table#table_ket_qua tr.delete').remove();
                }else{
                  alert("Xóa bản ghi không thành công");
                }
              }
          }
         });
      });
      
      jQuery("#btnThem_moi").click(function(){
        document.forms[0].action = "addSoDuAction.do";
        document.forms[0].submit();
      });
      
      jQuery("a#previous").click(function(){
        var a = jQuery("#pageNumber").val();
        if(a > 1){
          jQuey("#pageNumber").val(a-1);
        }
      });
      jQuery("a#next").click(function(){
        var a = jQuery("#pageNumber").val();
        jQuery("#pageNumber").val(a+1);
      });
      
      var page = jQuery("select#selectPage option:selected").val();
      jQuery("#pageNumber").val(page);
      
      var kb_id_tinh = jQuery('#id_kho_bac_tinh option:selected').val();
      if(kb_id_tinh != ""){
        jQuery.ajax({
        type : "POST",
        url : "getNHKBHuyen.do",
        data : {"kb_id" : kb_id_tinh},
        success : function(data, textstatus){
          if(data != null){
            var lstNHKBHuyen = new Object();
            lstNHKBHuyen = JSON.parse(data[0]);
            if(lstNHKBHuyen.size() != 0){
              jQuery('#id_kho_bac_huyen option').remove();
              jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
              for(var i = 0; i < lstNHKBHuyen.size(); i++){
                jQuery('#id_kho_bac_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
              }
            }
          }
        }
        });
      }
  });
  
  function goPage(page) {
      jQuery('#pageNumber').val( page);
      var vform = jQuery('#frmTraCuuSoDu')[0];       
      vform.submit();            
  }
  
  function changeForeignCurrency(nStr){
        nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
       var rgx = /(\d+)(\d{3})/;
       while (rgx.test(x1)) {
          x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
      }
      
  //xu ly dinh dang tien te viet nam
  function changeVNDCurrency(nStr){
    nStr += '';
    x1 = nStr;
      x1 = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    return x1;
  } 
  
  function resetInput(){
    jQuery("#so_du").val("");
    jQuery("#tu_han_muc").val("");
    jQuery("#den_han_muc").val("");
    jQuery("#so_du_COT").val("");
  }
  
  function getTypeMoney(){
    var loai_tien = jQuery("#loai_tien").val();
    var so_du = jQuery("#so_du").val();
    var tu_han_muc = jQuery("#tu_han_muc").val();
    var den_han_muc = jQuery("#den_han_muc").val();
    var so_du_cot = jQuery("#so_du_COT").val();
    if(loai_tien != "")
    if(loai_tien == "VND"){
        jQuery("#so_du").val(changeVNDCurrency(so_du));
        jQuery("#tu_han_muc").val(changeVNDCurrency(tu_han_muc));
        jQuery("#den_han_muc").val(changeVNDCurrency(den_han_muc));
        jQuery("#so_du_COT").val(changeVNDCurrency(so_du_cot));
    }else{
        jQuery("#so_du").val(changeForeignCurrency(so_du));
        jQuery("#tu_han_muc").val(changeForeignCurrency(tu_han_muc));
        jQuery("#den_han_muc").val(changeForeignCurrency(den_han_muc));
        jQuery("#so_du_COT").val(changeForeignCurrency(so_du_cot));
    }
  }
  function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUUQT");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
    
    function getThongTinKB(){
      var kb_id = jQuery("#id_kho_bac_tinh").val();
      if(kb_id != ""){
        jQuery.ajax({
        type : "POST",
        url : "getNHKBHuyen.do",
        data : {"kb_id" : kb_id},
        success : function(data, textstatus){
          if(data != null){
            var lstNHKBHuyen = new Object();
            lstNHKBHuyen = JSON.parse(data[0]);
            if(lstNHKBHuyen.size() != 0){
              jQuery('#id_kho_bac_huyen option').remove();
              jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
              for(var i = 0; i < lstNHKBHuyen.size(); i++){
                jQuery('#id_kho_bac_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
              }
            }
          }
        }
        });
      }
    }
    
    function getThongTinNganHang(){
      var kb_id_huyen = jQuery("#id_kho_bac_huyen").val();
      if(kb_id_huyen != ""){
        jQuery.ajax({
          type : "POST",
          url : "getListNganHang.do",
          data : {"kb_id_huyen" : kb_id_huyen},
          success : function(data, textstatus){
            if(data != null){
              var lstNganHang = new Object();
              lstNganHang = JSON.parse(data[0]);
              if(lstNganHang.size() != 0){
                jQuery('#id_ngan_hang option').remove();
                jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
                for(var i = 0; i < lstNganHang.size(); i++){
                  jQuery('#id_ngan_hang').append('<option value="'+ lstNganHang[i].ma_nh +'">'+ lstNganHang[i].ten_nh +'<\/option>');
                }
              }
            }
          }
        });
      }
    }
    
    function getLoaiTien(){
       var nganHang  = jQuery('#id_ngan_hang').val();
       if(nganHang != ""){
          jQuery.ajax({
            type : "POST",
            url : "getCateMoney.do",
            data : {"nganhang_id" : nganHang},
            success : function(data, textstatus){
              if(data != null){
                var lstLoaiTien = new Object();
                lstLoaiTien = JSON.parse(data[0]);
                if(lstLoaiTien.size() != 0){
                  jQuery('#loai_tien option').remove();
                  jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
                  for(var i = 0; i < lstLoaiTien.size(); i++){
                    jQuery('#loai_tien').append('<option value"' + lstLoaiTien[i].loai_tien + '" >'+ lstLoaiTien[i].loai_tien +'<\/option>');
                  }
                }
              }
            }
          });
       }
    }
</script>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
        <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
        <span class="title2">Tra cứu số dư</span></td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
        <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
      </tr>
    </tbody>
</table> 
<html:form styleId="frmTraCuuSoDu" action="/traCuuSoDu.do">
<font color="red"><html:errors/></font>
<table cellspacing="0" cellpadding="3" width="100%">
  <tr>
    <td width="5%" align="right">Kho bạc tỉnh</td>
    <td width="10%"><html:select property="id_kho_bac_tinh" styleId="id_kho_bac_tinh" onblur="getThongTinKB();" style="width : 170px;" onkeydown="if(event.keyCode==13) event.keyCode=9;">
          <option value="">Chọn thông tin tra cứu</option>
          <logic:notEmpty name="lstNHKBTinh">
          <html:optionsCollection name="lstNHKBTinh" label="ten" value="ma" />
          </logic:notEmpty>
        </html:select>
    </td>
    <td width="5%" align="right">Kho bạc huyện</td>
    <td width="6%"><html:select property="id_kho_bac_huyen" styleId="id_kho_bac_huyen" onblur="getThongTinNganHang();" style="width : 170px;" onkeydown="if(event.keyCode==13) event.keyCode=9;">
          <option value="">Chọn thông tin tra cứu</option>
        </html:select>
    </td>
    <td rowspan="3" colspan="3" width="10%" align="left"> 
      <button type="button" style="width:50%; height: 50px;" onclick="callLov();" class="ButtonCommon" accesskey="t" >
        <span class="sortKey">D</span>anh m&#7909;c KB
      </button>
    </td>
    </tr>
  <tr>
  <td align="right">Hệ thống ngân hàng</td>
  <td>
    <html:select property="id_ngan_hang" styleId="id_ngan_hang" style="width : 170px;" onblur="getLoaiTien();" onkeydown="if(event.keyCode==13) event.keyCode=9;">
        <option value="">Chọn thông tin tra cứu</option>
    </html:select>
    </td>
  <td align="right">Số dư</td>
  <td><html:text property="so_du" styleId="so_du" onblur="getTypeMoney();" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></td>
  </tr>
  <tr>
  <td align="right">Loại tiền</td>
  <td>
    <html:select property="loai_tien" styleId="loai_tien" onblur="resetInput();" onkeydown="if(event.keyCode==13) event.keyCode=9;">
      <option value="">--Chọn loại tiền--</option>
    </html:select>
  </td>
  <td align="right">Số dư COT</td>
  <td>
      <html:text property="so_du_COT" styleId="so_du_COT" onblur="getTypeMoney();" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
  </td>
  </tr>
  <tr>
  <td align="right">Từ hạn mức</td>
  <td>
    <html:text property="tu_han_muc" styleId="tu_han_muc" onblur="getTypeMoney();" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
  </td>
  <td align="right">Loại tài khoản</td>
  <td>
    <html:select property="loai_tai_khoan" styleId="loai_tai_khoan" onkeydown="if(event.keyCode==13) event.keyCode=9;">
      <option value="">Chọn thông tin tra cứu</option>
      <option value="01">Thanh toán tổng hợp</option>
      <option value="02">Thanh toán</option>
      <option value="03">Chuyên thu</option>
    </html:select>
  </td>
  <td colspan="3" rowspan="2">
    <button id="btnTra_cuu">Tra cứu</button>
    <button id="btnThem_moi">Thêm mới</button>
    <button id="btn_Thoat">Thoát</button>
  </td>
  </tr>
  <tr>
  <td align="right">Đến hạn mức</td>
  <td><html:text property="den_han_muc" styleId="den_han_muc" onblur="getTypeMoney();" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></td>
  <td align="right">Ngày GD</td>
  <td>
    <html:text property="ngay_gd" styleId="ngay_gd" styleClass="fieldText"
        onkeypress="return numbersonly(this,event,true) "
        onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_gd');"
        onkeydown="if(event.keyCode==13) event.keyCode=9;"
        style="WIDTH: 40%;"/>
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
        border="0" id="ngay_gd_btn" width="10%"
        style="vertical-align:middle;"/>
        <script type="text/javascript">
          Calendar.setup( {
          inputField : "ngay_gd", // id of the input field
          ifFormat : "%d/%m/%Y", // the date format
          button : "ngay_gd_btn"// id of the button
          });
        </script>
  </td>
  </tr>
</table>
<div style="width : 100%; height : 4px;"></div>
<table cellpadding="0" cellspacing="0" border="1" id="table_ket_qua">
  <thead>
  <tr>
  <th width="4%" align="center">STT</th>
  <th width="6%" align="center">Mã kho bạc</th>
  <th width="10%" align="center">Tên kho bạc</th>
  <th width="6%" align="center">Mã NH</th>
  <th width="12%" align="center">Tên ngân hàng</th>
  <th width="7%" align="center">Ngày giao dịch</th>
  <th width="10%" align="center">Số dư</th>
  <th width="7%" align="center">Loại tiền</th>
  <th width="8%" align="center">Ngày thêm mới</th>
  <th width="10%" align="center">Số dư COT</th>
  <th width="9%" align="center">Hạn mức</th>
  <th width="5%" align="center">Sửa</th>
  <th width="5%" align="center">Xóa</th>
  </tr>
  </thead>
  <logic:notEmpty name="lstTimKiem">
  <%  
     com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
     int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
     List lstTimKiem = (List) request.getAttribute("lstTimKiem"); 
     TraCuuSoDuVO items = null;
     for(int i = 0; i < lstTimKiem.size(); i++){
     items = (TraCuuSoDuVO) lstTimKiem.get(i);
  %>
  <tr>
    <td align="center"><%= i+1 %></td>
    <td id="ma_kb" align="center"><%= items.getMa_kb() %></td>
    <td align="center"><%= items.getTen_kb() %></td>
    <td id="ma_nh" align="center"><%= items.getMa_nh() %></td>
    <td align="center"><%= items.getTen_nh() %></td>
    <td id="ngay_giao_dich" align="center"><%= items.getNgay_gd() %></td>
    <td id="soDu" align="center">
    <%if(items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du() %>
      </fmt:formatNumber>
    <%}%>
    <%if(!items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du() %>
      </fmt:formatNumber>
    <%}%>
    </td>
    <td id="loaiTien" align="center"><%= items.getLoai_tien() %></td>
    <td align="center"><%= items.getInsert_date() %></td>
    <td id="soDuCOT" align="center">
    <%if(items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du_cot() %>
      </fmt:formatNumber>
    <%}%>
    <%if(!items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du_cot() %>
      </fmt:formatNumber>
    <%}%>
    </td>
    <td align="center">
      <%if(items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getHan_muc_no() %>
      </fmt:formatNumber>
    <%}%>
    <%if(!items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getHan_muc_no() %>
      </fmt:formatNumber>
    <%}%>
    </td>
    <td align="center"><img src="<%=request.getContextPath()%>/styles/images/ctu_00.gif" class="updateRecord"/></td>
    <td align="center"><img src="<%=request.getContextPath()%>/styles/images/delete1.gif" class="deleteRecord"/></td>
  </tr>
  <%}%>
  <tr>
    <td colspan="13">
      <div style="float:right;padding-right:40">
        <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
      </div>
    </td>
  </tr>
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
  </logic:notEmpty>
</table>
</html:form>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>