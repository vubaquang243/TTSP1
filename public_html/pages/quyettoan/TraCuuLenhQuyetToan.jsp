<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
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

<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
 //20171222 Quang VD them moi 
  String kb_id = session.getAttribute(AppConstants.APP_KB_CODE_SESSION) == null ? "" : session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
  String id_kb = session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ? "" : session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  
  
  jQuery(document).ready(function () {    
//    jQuery("#itong_st").val(CurrencyFormatted(jQuery("#tong_st").val(),'VND'));
// Hongnn 15112013
      
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
      //20171222 Quang VD them moi 
      var kb_id = '<%=kb_id%>';
      if(kb_id == "0001"){
      jQuery('#kb_tinh').append('<option value="" selected>Chọn kho bạc tỉnh<\/option>');
      jQuery('#kb_huyen').append('<option value="" selected>Chọn kho bạc huyện<\/option>');
      }
  // end
    jQuery("#btn_Thoat").click(function () {          
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
          jQuery(this).dialog("close");
      });
      jQuery("#chklist").click(function(){
        jQuery("#colCheckList").find(':checkbox').attr('checked', this.checked);
      });
      
      jQuery("#dialog-confirm").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              "Có" : function () {    
                  // thuc hien update trang thai
                  document.forms[0].action = "thoatView.do";
                  document.forms[0].submit();
                  jQuery(this).dialog("close");
              },
              "Không" : function () {    
                  // thuc hien update trang thai
                  jQuery(this).dialog("close");
              }
          }
      });
  });
  
  //20171129 taidd sua format loai tien
  function changeValue(txt_id, allowNegativeNumber) {  
      var value = jQuery("#"+txt_id +"").val().replace(/\s/g,"");
      var loai_tien = jQuery("#tcg_loai_tien").val();
      
      if(allowNegativeNumber == undefined){
        allowNegativeNumber = false;
      }
      
      if(value==""){
        return;
      }
        
        if(loai_tien == "VND"){
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'VND', allowNegativeNumber));
        }else{
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'USD', allowNegativeNumber));
        }

  }
  
  function goPage(page) {
      jQuery("#frmQTTQ").attr( {
          action : 'TraCuuQToanList.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmQTTQ").submit();
  }
  //20171222 Quang VB sua noi dung ham them moi, kiem tra dinh dang tien
  function FindQT(){
    var inKB = jQuery('#kb_huyen option:selected').index();
    document.forms[0].action="TraCuuQToanList.do?inKB="+inKB;
    var vloaitien =  jQuery("#tcg_loai_tien").val();
    var vsoTien =  jQuery("#soTien").val();
    if(vsoTien != '' && vloaitien == '' && (vsoTien.indexOf('.')>0 || vsoTien.indexOf(',')>0 )){
      alert('Định dạng số tiền không đúng');
      jQuery("#soTien").focus();
    }else
    document.forms[0].submit();
  }
  function changePTQT(lQT){
    var valueSelected = lQT.value;
    if(valueSelected=='01' || valueSelected == '02'){
      jQuery("#pt_qt").val("");
  }
  }
  function getKBHuyen(kbTinh){
      var idkbTinh = kbTinh.value;
      jQuery.ajax( {
          type : "POST", url : "DSachKBHuyen.do", data :  {
              "idkbTinh" : idkbTinh, "nd" : Math.random() * 100000
          },
          dataType : 'json', success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                        }
                      else {
                        var lstKBHuyen = new Object();
                        lstKBHuyen = JSON.parse(data[0]);
                        var countKBHuyen = 0;
                        if (lstKBHuyen != null) {
                            jQuery('#kb_huyen option').remove();
                            countKBHuyen = lstKBHuyen.size();
                        }
                        if (countKBHuyen > 0) {
                            jQuery('#kb_huyen').append('<option value="" selected="selected">Chọn kho bạc huyện<\/option>');
                            for (var h = 0;h < countKBHuyen;h++) {
                              jQuery('#kb_huyen').append('<option value="' + lstKBHuyen[h].ma + '">' + lstKBHuyen[h].ten + '<\/option>');
                            }
                        }

                      }
                  }
              }
          },
          error : function (textstatus) {
              alert(textstatus.messge);
          }
        });
  }
   //**************************LINK TRANG CLICK********************************************
    function makeGetRequest(id){
      var hsc_nh = jQuery("#hsc_nh").val();
      var kb_tinh = jQuery("#kb_tinh").val();
      var kb_huyen = jQuery("#kb_huyen").val();
      var tu_ngay_qt = jQuery("#tu_ngay").val();
      var den_ngay_qt = jQuery("#den_ngay").val();
      var ngay_tt =jQuery("#ngay_tt").val();
      var loai_qt = jQuery("#loai_qt").val();
      var pt_qt = jQuery("#pt_qt").val();
      var tcg_loai_tien = jQuery("#tcg_loai_tien").val();
      var urlRequest="XuLyLenhQuyetToanList.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id;
      if(hsc_nh != null && hsc_nh != ''){
        urlRequest += "&hsc_nh="+hsc_nh+"";
      }
      if(kb_tinh != null && kb_tinh != ''){
        urlRequest += "&kb_tinh="+kb_tinh+"";
      }
      if(kb_huyen != null && kb_huyen != ''){
        urlRequest += "&kb_huyen="+kb_huyen+"";
      }
       if(tu_ngay_qt != null && tu_ngay_qt != ''){
        urlRequest += "&tu_ngay="+tu_ngay_qt+"";
      }
       if(den_ngay_qt != null && den_ngay_qt != ''){
        urlRequest += "&den_ngay="+den_ngay_qt+"";
      }
       if(ngay_tt != null && ngay_tt != ''){
        urlRequest += "&ngay_tt="+ngay_tt+"";
      }
      
      if(loai_qt != null && loai_qt != ''){
        urlRequest += "&loai_qt="+loai_qt+"";
      }
      if(pt_qt != null && pt_qt != ''){
        urlRequest += "&pt_qt="+pt_qt+"";
      }
       if(tcg_loai_tien != null && tcg_loai_tien != ''){
        urlRequest += "&tcg_loai_tien="+tcg_loai_tien+"";
      }
      window.location = urlRequest;
    }
// hongnn
  function getTenKhoBacLTT(ma,ma_cha) { 
      document.getElementById('kb_huyen').options.length = 1;// clear du lieu option cu
       var ma_tinh;
        ma_tinh=document.forms[0].kb_tinh.value;
            if (ma==null || ''==ma){       
                  if(ma_cha!=null&&''!=ma_cha){               
                        ma_tinh=ma_cha;
                        jQuery('#kb_tinh').val(ma_cha);
                  }else if (ma_cha==null||''==ma_cha){
                    ma_tinh=document.forms[0].kb_tinh.value;
                  }
              }else if (ma!=null && ''!=ma){
                  if(ma_cha!=null&&''!=ma_cha){                
                    ma_tinh=ma_cha;
                    jQuery('#kb_tinh').val(ma_cha);
                  }else if (ma_cha==null||''==ma_cha){
                    ma_tinh=document.forms[0].kb_tinh.value;
                  }
              }
    var back_huyen = jQuery('#huyen_back').val();
    var init_kb_huyen="<%=kb_huyen%>";      
  //    alert(init_kb_huyen)
     if (ma_tinh !=null && ""!=ma_tinh){
      jQuery.ajax( {
          type : "POST", url : "getDMucKBTCuuLTT.do", data :  {
              "ma_tinh" : ma_tinh
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                      // truong hop 1 - luc load khong co thang nao                  
                      document.getElementById('kb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.ma));
                      });
                         if(document.getElementById('kb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#kb_huyen option:eq(1)").attr('selected', true);
                         }
                        else if(init_kb_huyen=='0'||init_kb_huyen==null||''==init_kb_huyen){
                            if(back_huyen!=null&& ''!=back_huyen){
                              jQuery('#kb_huyen').val(back_huyen);
                            }else if(back_huyen==null|| ''==back_huyen){
                              jQuery('#kb_huyen option:eq(0)').attr('selected', true);
                            }
                        }
                        else if(init_kb_huyen!='0'){
                        jQuery('#kb_huyen option:eq('+init_kb_huyen+')').attr('selected', true);
                        }
                      }
                  }

                if (ma!=null && ''!=ma){                          
                  jQuery('#kb_huyen').val(ma);
               }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
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
  // end
  ////20171222 Quang VB XU ly tien te begin----------------------------------
  function changeForeignCurrency(nStr){
        nStr += '';
        nStr = nStr.replace(/\,/g,"");
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
       var rgx = /(\d+)(\d{3})/;
       while (rgx.test(x1)) {
          x1 = x1.replace(rgx, '$1' + '.' + '$2');
        }
        return x1 + x2;
      }
      
  //xu ly dinh dang tien te viet nam
  function changeVNDCurrency(nStr){
    nStr += '';
    nStr = nStr.replace(/\./g,"");
    nStr = nStr.replace(/\,/g,"");
    x1 = nStr;
      x1 = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    return x1;
  } 
    function resetInput(){
     jQuery('#soTien').val("");
    }
    
    function changeCurrency(str){
    var value =str.value;
    var cateCurrency = jQuery('#tcg_loai_tien').val();
    if(cateCurrency != ""){
    if(cateCurrency == "VND"){
      value = value.split('.').join('');       
      str.value = changeVNDCurrency(value);
    }else{
      value = value.split(',').join(''); 
      str.value = changeForeignCurrency(value);
    }}
  }
  //20171222 Quang VB XU ly tien te begin----------------------------------
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuQTResource"/>
    <html:form styleId="frmQTTQ" action="/TraCuuQToan.do">

<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          <fmt:message key="QTToanQuoc.page.tracuu.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table> 
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="QTToanQuoc.page.dieukien"/>
        </td>
      </tr>
   </tbody>    
  </table>
    <table class="tableBound">
      <tr>
        <td>
          <table width="99%">
            <tbody>
            <tr>
              <td><font color="red">
                  <html:errors/>
                </font> 
              </td></tr>
            </tbody>
          </table>
          <table cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td valign="top">
                <!-- 20171222 Quang VB them moi sua dieu kien và ket qua tra cuu begin-------------------------------------------->
                  <table class="bordertableChungTu" border="0" cellspacing="0" cellpadding="2"
                         width="100%">
                      <tbody>
                        <tr>
                          
                          <td width="10%" style="text-align:right;padding-right:5px">
                            <label for="KBTINH">
                              <fmt:message key="QTToanQuoc.page.lable.KBTINH"/>
                            </label>
                          </td>
                          <td width="20%">
                            <logic:present name="MAT4">
                              <html:select styleClass="selectBox" property="kb_tinh"
                                           onchange="getTenKhoBacLTT('','');"
                                           styleId="kb_tinh" style="width:100%;height:20px" 
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                <html:option value="">
                                <fmt:message key="QTToanQuoc.page.kbTinh.default"/>
                                </html:option>
                                <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
                              </html:select>
                            </logic:present>
                            <logic:notPresent name="MAT4">
                              <html:select styleClass="selectBox" property="kb_tinh" styleId="kb_tinh" style="width:100%;height:20px;" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              onchange="getTenKhoBacLTT('',''); getKBHuyen(this);" >
                              <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
                            </html:select>
                            </logic:notPresent>
                          </td>
                          <td rowspan="3" width="9">
                          <% if("1".equals(id_kb) || "2".equals(id_kb)){%>
                            <button type="button" style="width:100%" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                              <span class="sortKey">D</span>anh m&#7909;c KB
                            </button>
                            <%}%>
                          </td>
                          <td width="10%" style="text-align:right;padding-right:5px">
                            <label for="HSC">
                              <fmt:message key="QTToanQuoc.page.lable.HSC"/>
                            </label>
                          </td>
                          <td width="20%">
                            <html:select styleClass="selectBox" property="hsc_nh" styleId="hsc_nh" style="width:90%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                            <html:option value="">
                              <fmt:message key="QTToanQuoc.page.hsc_nh.default"/>
                            </html:option>
                              <html:optionsCollection label="ten_nh" value="ma_dv" name="lstNHHO"/>
                            </html:select>
                          </td>
                          <td width="10%" style="text-align:right;padding-right:5px">
                            <label for="LOAIQT">
                              <fmt:message key="QTToanQuoc.page.lable.LOAIQT"/>
                            </label>
                          </td>
                          <td width="20%">
                            <html:select styleClass="selectBox" property="loai_qt"
                                     styleId="loai_qt" style="width:90%" onblur="textlostfocus(this);" 
                                     onchange="changePTQT(this)"
                                     onkeydown="nextFocus();"
                                     onfocus="textfocus(this);">
                                     <html:option value="">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.default"/>
                                     </html:option>
                                     <html:option value="D">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.thu"/>
                                     </html:option>
                                     <html:option value="C">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.chi"/>
                                     </html:option> 
                                     <html:option value="01">
                                        <fmt:message key="QTToanQuoc.page.loai_lenh.phi"/>
                                     </html:option>       
                                     <html:option value="02">
                                        <fmt:message key="QTToanQuoc.page.loai_lenh.lai"/>
                                     </html:option>
                                     <html:option value="03">
                                         Báo có chênh lệnh tỷ giá
                                     </html:option>
                                     <html:option value="04">
                                         Báo nợ chênh lệch tỷ giá
                                     </html:option>  
                                     <html:option value="05">
                                         Báo có thu qua POS
                                     </html:option>  
                                     <html:option value="06">
                                         Báo nợ thu phí POS 
                                     </html:option> 
                                     <!--20171129 QuangVB them loai quyet toan-->
                                     <html:option value="07">
                                         Quyết toán bù số chi
                                     </html:option> 
                                     <html:option value="08">
                                         Quyết toán thấu chi
                                     </html:option> 
                                     <html:option value="09">
                                         Quyết toán lập mới khác
                                     </html:option> 
                                 </html:select>
                          </td>                        
                        </tr>
                        <tr>
                        <td width="13%" style="text-align:right;padding-right:5px">
                            <label for="KBHUYEN">
                              <fmt:message key="QTToanQuoc.page.lable.KBHUYEN"/>
                            </label>
                          </td>
                          <td width="20%">
                            <logic:present name="MAT4">
                              <html:select styleClass="selectBox" property="kb_huyen" styleId="kb_huyen" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" > 
                              <% if("1".equals(id_kb) || "2".equals(id_kb)){%>
                              <html:option value="">Chọn kho bạc huyện</html:option>
                              <%}%>
                                <html:options collection="lstKBHuyen" property="ma" labelProperty="ten"/>
                              </html:select>
                            </logic:present>
                            <logic:notPresent name="MAT4">
                              <html:select styleClass="selectBox" property="kb_huyen" styleId="kb_huyen" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                              <% if("1".equals(id_kb) || "2".equals(id_kb)){%>
                              <html:option value="">Chọn kho bạc huyện</html:option>
                              <%}%>
                                <html:options collection="lstKBHuyen" property="ma" labelProperty="ten"/>
                              </html:select>
                            </logic:notPresent>
                          </td>  
                          
                          <td  style="text-align:right;padding-right:5px">
                          </td>
                          <td width="20%">
                          </td>
                          <td  style="text-align:right;padding-right:5px">
                            <label for="KBTINH">
                              Loại tiền
                            </label>
                          </td>
                          <td width="20%">
                              <html:select styleClass="selectBox" property="tcg_loai_tien"
                                           styleId="tcg_loai_tien" style="width:50%;height:20px" 
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="resetInput();"
                                           onchange="changeValue('soTien');">
                                <html:option value="VND">VND</html:option>
                                <html:optionsCollection value="ma" label="ma" name="tienTe"/>
                              </html:select>
                          </td>
                        </tr>
                        <tr>
                          <td width="15%" style="text-align:right;padding-right:5px">
                            <label for="PTQT">
                              <fmt:message key="QTToanQuoc.page.lable.PTQT"/>
                            </label>
                          </td>
                          <td width="20%">
                            <html:select styleClass="selectBox"                                      
                                     property="pt_qt"
                                     styleId="pt_qt" style="width:100%" 
                                     onblur="textlostfocus(this);"  
                                     onkeydown="nextFocus();"
                                     onfocus="textfocus(this);">
                                     <html:option value="">
                                        <fmt:message key="QTToanQuoc.page.pt_quyet_toan.default"/>
                                     </html:option>
                                     <html:option value="01">
                                        <fmt:message key="QTToanQuoc.page.pt_quyet_toan.dt"/>
                                     </html:option>
                                     <html:option value="03">
                                        Loại quyết toán khác
                                     </html:option> 
                              </html:select>
                          </td>
                          <td  style="text-align:right;padding-right:5px">
                            <label for="NGAYTT">
                              <fmt:message key="QTToanQuoc.page.lable.NGAYTT"/>
                            </label>
                          </td>
                          <td width="20%">
                            <html:text property="ngay_tt" styleId="ngay_tt" styleClass="fieldText"
                            onkeypress="return numbersonly(this,event,true) "
                            onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_tt');"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 70%;"/>
                           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                 border="0" id="ngay_tt_btn" width="16%"
                                 style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                                  inputField : "ngay_tt", // id of the input field
                                  ifFormat : "%d/%m/%Y", // the date format
                                  button : "ngay_tt_btn"// id of the button
                              });
                            </script>
                          </td>
                         <td width="10%" style="text-align:right;padding-right:5px">
                            <label for="KBTINH">
                              Số tiền
                            </label>
                          </td>
                          <td width="20%">
                              <html:text property="soTien" styleId="soTien" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                               onkeypress="return numberBlockKey1();" onblur="changeValue('soTien');" style="text-align : right;" maxlength="20" />
                          </td>
                          <td>
                          </td>
                        </tr>
                        <tr>
                          <td style="text-align : right;padding-right:5px">
                            <label for="NGAYQT">
                              Từ ngày QT
                            </label>
                          </td>
                          <td><html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                            onkeypress="return numbersonly(this,event,true) "
                            onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 70%;"/>
                           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                 border="0" id="tu_ngay_btn" width="16%"
                                 style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                                  inputField : "tu_ngay", // id of the input field
                                  ifFormat : "%d/%m/%Y", // the date format
                                  button : "tu_ngay_btn"// id of the button
                              });
                            </script>
                            </td>
                          <td></td>
                          <td  style="text-align:right;padding-right:5px">
                            <label for="NGAYQT">
                              Đến ngày QT
                            </label>
                          </td>
                          <td width="20%">
                               <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                            onkeypress="return numbersonly(this,event,true) "
                            onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 70%;"/>
                           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                 border="0" id="den_ngay_btn" width="16%"
                                 style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                                  inputField : "den_ngay", // id of the input field
                                  ifFormat : "%d/%m/%Y", // the date format
                                  button : "den_ngay_btn"// id of the button
                              });
                            </script>                          
                          </td>
                          <td></td>
                          <td></td>
                        </tr>
                      </tbody>
                    </table>
                     <!-- 20171222 Quang VB them moi sua dieu kien và ket qua tra cuu end-------------------------------------------->
                  </td>
                </tr>
              </tbody>
            </table>          
        </td>
      </tr>
    </table>
    <table  class="buttonbar" align="center">
            <tr>
              <td>
                <span id="tracuu">
                  <button  id="btn_timKiem" 
                      type="button" onclick="FindQT()"
                      class="ButtonCommon" >
                       <fmt:message key="QTToanQuoc.page.button.search"/>
                  </button>
                </span>
                <span id="exit">
                  <button  id="btn_Thoat" 
                      type="button"
                      class="ButtonCommon" >
                       <fmt:message key="QTToanQuoc.page.button.exit"/>
                  </button>
                </span>
              </td>
            </tr>
          </table>
      <div class="scroll_box">
        <table style="BORDER-COLLAPSE: collapse" id="tblBlah" border="1" cellspacing="0"
           bordercolor="#999999"  width="155%">
          <thead class="TR_Selected">
            <tr>
              <th width="1%">
                <fmt:message key="QTToanQuoc.page.ketqua.STT"/>
              </th>
              <th width="12%">
                <fmt:message key="QTToanQuoc.page.ketqua.KB"/>
              </th>
              <th width="13%">
                <fmt:message key="QTToanQuoc.page.ketqua.KBHuyen"/>
              </th>
              <th width="13%">
                <fmt:message key="QTToanQuoc.page.ketqua.NH"/>
              </th>
              <th width="6%">
                <fmt:message key="QTToanQuoc.page.ketqua.TKCHUYEN"/>
              </th>
              <th width="7%">
                <fmt:message key="QTToanQuoc.page.ketqua.TT"/>
              </th>
              <logic:present name="T4">
                <th width="7%">
                  <fmt:message key="QTToanQuoc.page.ketqua.TTDV"/>
                </th>
              </logic:present>
              <th width="4%">
                <fmt:message key="QTToanQuoc.page.ketqua.NGAYQT"/>
              </th>
              <th width="4%">
                <fmt:message key="QTToanQuoc.page.ketqua.NGAYTT"/>
              </th>
              <th width="6%">
                <fmt:message key="QTToanQuoc.page.ketqua.SLQToan"/>
              </th>
              <th width="7%">
                <fmt:message key="QTToanQuoc.page.ketqua.STien"/>
              </th>
              <th width="6%">
                <fmt:message key="QTToanQuoc.page.ketqua.LOAIQT"/>
              </th>
              <th width="6%">
                <fmt:message key="QTToanQuoc.page.lable.PTQT"/>
              </th>
            </tr>
          </thead>
          <tbody id="colCheckList">
            <logic:present name="lstQuyetToan">
              <logic:empty name="lstQuyetToan">
                  <tr>
                    <logic:present name="T4">
                      <td colspan="12" align="center" style="color:red">
                      <fmt:message key="QTToanQuoc.page.ketqua.empty"/>
                    </td>
                    </logic:present>
                    <logic:notPresent name="T4">
                      <td colspan="11" align="center" style="color:red">
                          <fmt:message key="QTToanQuoc.page.ketqua.empty"/>
                        </td>
                    </logic:notPresent>
                  </tr>
                </logic:empty>
                <logic:notEmpty name="lstQuyetToan">
                 <%
                  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                  int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
                %>
                  <logic:iterate id="items" name="lstQuyetToan" indexId="stt">
                    <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                          <td>
                            <%=rowBegin+stt+1%>
                          </td>
                          <td>
                            <bean:write name="items" property="ten_kb_tw"/>
                          </td>
                          <td>
                            <bean:write name="items" property="ten_kb_huyen"/>
                          </td>
                          <td>
                            <div title="<bean:write name="items" property="ten_don_vi_chuyen"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:200px; overflow:hidden; font-size:11px">
                            <bean:write name="items" property="ten_don_vi_chuyen"/>
                            </div>
                          </td>
                          <td align="center">
                            <bean:write name="items" property="tk_chuyen"/>
                          </td>
                          <td title="<bean:write name="items" property="err_desc"/>">
                            <bean:write name="items" property="mo_ta_trang_thai"/>
                          </td>
                          <logic:present name="T4">
                            <td>
                              <logic:equal name="items" property="mo_ta_trang_thai_dv" value="TTSGD">
                                Không gom bảng kê
                              </logic:equal>
                              <logic:equal name="items" property="mo_ta_trang_thai_dv" value="CG">
                                Chưa gom bảng kê
                              </logic:equal>
                              <logic:notEqual name="items" property="mo_ta_trang_thai_dv" value="TTSGD">
                                <logic:notEqual name="items" property="mo_ta_trang_thai_dv" value="CG">
                                  <bean:write name="items" property="mo_ta_trang_thai_dv"/>
                              </logic:notEqual>
                              </logic:notEqual>
                            </td>
                          </logic:present>
                          <td>
                            <bean:write name="items" property="ngay_qtoan"/>
                          </td>
                          <td>
                            <bean:write name="items" property="ngay_htoan"/>
                          </td>
                         <td>
                              <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>)">
                                <bean:write name="items" property="id_ref"/>
                              </a>
                          </td>
                          <td align="right">
                          
                          <logic:equal property="ma_nt" name="items" value="VND">
                            <fmt:setLocale value="vi_VI"/>
                            <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                              <bean:write name="items" property="so_tien"/>
                            </fmt:formatNumber>
                          </logic:equal>
                          <logic:notEqual property="ma_nt" name="items" value="VND">
                            <fmt:setLocale value="vi_VI"/>
                            <fmt:formatNumber type="currency" currencySymbol="">
                              <bean:write  name="items" property="so_tien"/>
                            </fmt:formatNumber>
                          </logic:notEqual>
                          </td>
                          <td>
                            <logic:equal name="items"
                                         property="lai_phi"
                                         value="02">
                            <logic:equal name="items"
                                         property="loai_qtoan"
                                         value="D">
                                         <fmt:message key="QTToanQuoc.page.loai_lenh.phi"/>
                            </logic:equal>
                            <logic:equal name="items"
                                         property="loai_qtoan"
                                         value="C">
                                          <fmt:message key="QTToanQuoc.page.loai_lenh.lai"/>    
                            </logic:equal>         
                            </logic:equal>
                            <logic:equal name="items"
                                         property="lai_phi"
                                         value="01">
                              <logic:equal name="items"
                                           property="loai_qtoan"
                                           value="D">
                                         <fmt:message key="QTToanQuoc.page.loai_quyet_toan.thu"/>
                              </logic:equal>
                              <logic:equal name="items"
                                           property="loai_qtoan"
                                           value="C">
                                          <fmt:message key="QTToanQuoc.page.loai_quyet_toan.chi"/>    
                              </logic:equal>
                             </logic:equal>
                             <logic:equal name="items"
                                          property="lai_phi"
                                          value="03">
                              <logic:equal name="items"
                                             property="loai_qtoan"
                                             value="D">
                                          <fmt:message key="QTToanQuoc.page.loai_quyet_toan.thu"/>
                              </logic:equal>
                              <logic:equal name="items"
                                             property="loai_qtoan"
                                             value="C">
                                          <fmt:message key="QTToanQuoc.page.loai_quyet_toan.chi"/>    
                                </logic:equal>
                            </logic:equal>
                            <logic:equal name="items"
                                            property="lai_phi"
                                            value="04">
                                   <logic:equal name="items"
                                                property="loai_qtoan"
                                                value="D">
                                                Báo nợ chênh lệnh tỷ giá
                                  </logic:equal>
                                  <logic:equal name="items"
                                               property="loai_qtoan"
                                               value="C">
                                               Báo có chênh lệnh tỷ giá
                                   </logic:equal>                                                           
                            </logic:equal>
                            <logic:equal name="items"
                                            property="lai_phi"
                                            value="05">
                                   
                                                Báo có thu qua POS
                                                                                           
                            </logic:equal>
                            <logic:equal name="items"
                                            property="lai_phi"
                                            value="06">
                                   
                                                Báo nợ thu phí POS
                                                                                           
                            </logic:equal>
                          </td>
                          <td>
                            <logic:equal name="items"
                                         property="lai_phi"
                                         value="02">
                            </logic:equal>
                            <logic:equal name="items"
                                         property="lai_phi"
                                         value="04">
                            </logic:equal>                                 
                            <logic:equal name="items"
                                         property="lai_phi"
                                         value="01">
                                         <fmt:message key="QTToanQuoc.page.pt_quyet_toan.dt"/>
                            </logic:equal>
                            <logic:equal name="items"
                                           property="lai_phi"
                                           value="03">
                                           Loại quyết toán khác
                            </logic:equal>
                            
                          </td>
                    </tr>
                  </logic:iterate> 
                  <logic:notEmpty name="colSum">
                  <logic:iterate id="items" name="colSum" indexId="stt">
                    <tr>
                          <td colspan="2" align="left">
                              <b><fmt:message key="QTToanQuoc.page.label.tongsolenh"/>:  <b><bean:write name="items" property="tong_mon"/></b></b>
                          </td>
                          <td></td>
                          <td colspan="1" align="left"><b>T&#7893;ng ti&#7873;n: </b>
                          <b><logic:equal property="ma_nt" name="items" value="VND">
                            <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                              <bean:write  name="items" property="tong_tien"/>
                            </fmt:formatNumber>
                          </logic:equal>
                          <logic:notEqual property="ma_nt" name="items" value="VND">
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write  name="items" property="tong_tien"/>
                              </fmt:formatNumber>
                          </logic:notEqual></b>
                          </td>
                          <logic:present name="T4">
                            <td colspan="9" align="right">
                          </td>
                          </logic:present>
                          <logic:notPresent name="T4">
                            <td colspan="8" align="right">
                            </td> 
                          </logic:notPresent>
                                                  
                    </tr>
                    <tr>
                          <td colspan="2" align="left">
                              <b><%--<fmt:message key="QTToanQuoc.page.label.tongsolenh"/>:--%></b>
                          </td>
                          <td  align="right">
                              <b><%--<bean:write name="items" property="tong_mon"/>--%></b>
                              <%--<html:text property="tong_sl"  styleId="tong_sl" styleClass="fieldTextCode"
                                          onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"                            
                                         style="WIDTH: 50%;">
                              </html:text>--%>
                          </td>
                          <td colspan="2" align="left">
                              <div style="float:right;padding-right:40">
                              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                            </div>
                          </td>
                          <logic:present name="T4">
                            <td colspan="8" align="right">
                            </td> 
                          </logic:present>
                          <logic:notPresent name="T4">
                            <td colspan="7" align="right">
                            </td> 
                          </logic:notPresent>
                    </tr>
                  </logic:iterate>
                  </logic:notEmpty>
                      <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
                </logic:notEmpty>
            </logic:present>
          </tbody>
        </table>
      </div>
    </html:form>
    <!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm"
     title='<fmt:message key="XuLyLenhQT.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
    
    <span id="message_confirm"></span>
  </p>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUULTT.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>