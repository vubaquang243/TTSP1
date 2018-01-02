<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov1.js"></script>
<%
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String capuser = request.getAttribute("capuser")==null?"":request.getAttribute("capuser").toString();
  String dm_kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String dm_ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String dm_so_tk = request.getAttribute("so_tk")==null?"":request.getAttribute("so_tk").toString();
%>
<script type="text/javascript">
    jQuery.noConflict();

    jQuery(document).init(function () {
      getTenNHang();
      jQuery("#dialog-form-lov-dm").dialog({
          autoOpen: false,resizable : false,
          maxHeight: "700px",
          width: "550px",
          modal: true
      });
      var kb_tinh = jQuery('#kb_tinh').val();
      var capUser = '<%=capuser%>';
      if(kb_tinh != "" && capUser == 5){
        getNHKBHuyen();
        getTenNHang();
        getSoTK();
      }
      
      var dm_kb_huyen = '<%=dm_kb_huyen%>';
      if(dm_kb_huyen != ""){
        getNHKBHuyen(dm_kb_huyen);
        jQuery('#kb_huyen').focus();
      }
    });
    
    function check(type) {
        var f = document.forms[0];
        f.target = '';
        if (type == 'close') {
            f.action = 'mainAction.do';
        }
        f.submit();
    }
    
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
    
    function getTenNHang() {
          document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
          var nhkb_id = document.getElementById("kb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
          var strTinh = "<%=strTinh%>";
          var ngan_hang = "<%=ngan_hang%>";
          if (nhkb_id != null && "" != nhkb_id) {
              jQuery.ajax( {
                  type : "POST", url : "getDMucNHLTT.do", data :  {
                      "nhkb_id" : nhkb_id
                  },
                  success : function (data, textstatus) {
                      if (textstatus != null && textstatus == 'success') {
                          if (data != null) {
                              jQuery.each(data, function (i, objectDM) {
                                  document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh));
                              });
                          }
                          if (strTinh == null || strTinh == '') {
                              // request set dftinh ==null
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  jQuery("#ngan_hang option:eq(0)").remove();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                              }
                              else if (ngan_hang == '0' || ngan_hang == null || '' == ngan_hang) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                              }
                              else if (ngan_hang != '0') {
                                  jQuery('#ngan_hang option:eq(' + ngan_hang + ')').attr('selected', true);
                              }
                          }
                          changeNganHang();
//                          loadAccountDetailAJAX();
                      }
                  },
                  error : function (textstatus) {
                      alert(textstatus);
                  }
              });
          }
      }
        
    function changeNganHang(){
        if(jQuery('#ngan_hang').val().length == 0 ){
            jQuery("#hthongNganHang").removeAttr('disabled');
        }else{
            jQuery('#hthongNganHang').attr('disabled', 'disabled');
        }
    }
    
    function changeHTNH(){
        if(jQuery('#hthongNganHang').val().length == 0 ){
            jQuery("#ngan_hang").removeAttr('disabled');
        }else{
            jQuery('#ngan_hang').attr('disabled', 'disabled');
        }
    }
    
    function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
    }
    function getSoTK() {
          document.getElementById('so_tk').options.length = 1;
          var kb_id = document.getElementById("kb_huyen").value;
          var nh_id = document.getElementById("ngan_hang").value;
          var strTinh = "<%=strTinh%>";
          if (kb_id != null && "" != kb_id) {
              jQuery.ajax( {
                  type : "POST", url : "getDMucTK.do", data :  {
                      "kb_id" : kb_id, "nh_id" : nh_id
                  },
                  success : function (data, textstatus) {
                      if (textstatus != null && textstatus == 'success') {
                          if (data != null) {
                              jQuery.each(data, function (i, objectDM) {
                                  document.getElementById('so_tk').options.add(new Option(objectDM.so_tk, objectDM.so_tk));
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
      
      function getNHKBHuyen(){
        var kb_tinh = jQuery('#kb_tinh option:selected').val();
        if(kb_tinh != ""){
          jQuery.ajax({
          type : "POST",
          url : "getKhoBacHuyen.do",
          data : {"kb_id" : kb_tinh},
          success : function(data, textstatus){
            if(data != null){
              var lstNHKBHuyen = new Object();
              lstNHKBHuyen = JSON.parse(data[0]);
              if(lstNHKBHuyen.size() != 0){
                jQuery('#kb_huyen option').remove();
                jQuery('#kb_huyen').append('<option value="">-----Chọn kho bạc huyện-----<\/option>');
                for(var i = 0; i < lstNHKBHuyen.size(); i++){
                  jQuery('#kb_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
                }
              }
            }
          }
          });
        }else{
          jQuery('#kb_huyen option').remove();
          jQuery('#kb_huyen').append('<option value="">-----Chọn kho bạc huyện-----<\/option>');
        }
      }
      
      function getNHKBHuyen(ma){
        var kb_tinh = jQuery('#kb_tinh option:selected').val();
        if(kb_tinh != ""){
          jQuery.ajax({
          type : "POST",
          url : "getKhoBacHuyen.do",
          data : {"kb_id" : kb_tinh},
          success : function(data, textstatus){
            if(data != null){
              var lstNHKBHuyen = new Object();
              lstNHKBHuyen = JSON.parse(data[0]);
              if(lstNHKBHuyen.size() != 0){
                jQuery('#kb_huyen option').remove();
                jQuery('#kb_huyen').append('<option value="">-----Chọn kho bạc huyện-----<\/option>');
                for(var i = 0; i < lstNHKBHuyen.size(); i++){
                  jQuery('#kb_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
                  jQuery('#kb_huyen option[value="'+ma+'"]').attr("selected",true);
                }
              }
            }
          }
          });
        }else{
          jQuery('#kb_huyen option').remove();
          jQuery('#kb_huyen').append('<option value="">-----Chọn kho bạc huyện-----<\/option>');
        }
      }
</script>

<title>Tra cứu đối chiếu sổ chi tiết</title>
<div class="app_error">
  <html:errors/>
</div>
<html:form styleId="TCuuDChieuSoChiTietID" action="/TCuuDChieuSoChiTiet.do" method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">
            Tra cứu đối chiếu sổ chi tiết</span>
        </td>
        <td width="62">
          <img width="62" height="30"
               src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img>
        </td>
        <td width="20%"
            background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4">
          <img width="4" height="30"
               src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
        </td>
      </tr>
    </tbody>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="2" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">Kho bạc tỉnh</td>
        <td width="35%" bordercolor="#e1e1e1">
          <html:select property="kb_tinh" styleId="kb_tinh" style="width:100%"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="getNHKBHuyen();">
          <logic:equal value="0001" name="strMaKBTinh" >
            <html:option value="">-----Chọn kho bạc tỉnh-----</html:option>
          </logic:equal>
          <logic:equal value="0002" name="strMaKBTinh" >
            <html:option value="">-----Chọn kho bạc tỉnh-----</html:option>
          </logic:equal>
            <html:optionsCollection name="lstKBTinh" label="ten" value="ma" />
          </html:select>
        </td>
        <td align="right" width="15%" bordercolor="#e1e1e1">
          <label for="tu_ngay">
            Kho bạc huyện
          </label>
        </td>
        <td width="35%" bordercolor="#e1e1e1">
          <html:select property="kb_huyen" styleId="kb_huyen" style="width:100%"
                       onchange="getTenNHang();getSoTK();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="getTenNHang();getSoTK();">    
            <logic:equal value="0001" name="strMaKBTinh" >
              <html:option value="">-----Chọn kho bạc huyện-----</html:option>
            </logic:equal>
             <logic:equal value="0002" name="strMaKBTinh" >
              <html:option value="">-----Chọn kho bạc huyện-----</html:option>
            </logic:equal>
            <html:optionsCollection name="lstKBHuyen" label="ten" value="ma" />
          </html:select>
        </td>
        </tr>
        <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
        <td bordercolor="#e1e1e1" width="35%">
          <html:select property="ngan_hang" styleId="ngan_hang" style="width:100%"
                       onchange="changeNganHang(); getSoTK();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <html:option value="">-----Chọn ng&acirc;n
                                  h&agrave;ng-----</html:option>
          </html:select>
        </td>
        <td align="right" width="15%" bordercolor="#e1e1e1">
          <label for="tu_ngay">
            Từ ngày
          </label>
        </td>
        <td bordercolor="#e1e1e1">
          <html:text property="from_date" styleId="from_date" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('from_date');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      style="WIDTH: 70px;vertical-align:middle;"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="btn_from_date" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "from_date", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "btn_from_date"// id of the button
            });
          </script>
        </td>
      </tr>
      <tr>
          <td width="15%" align="right" bordercolor="#e1e1e1">Trạng thái</td>
          <td bordercolor="#e1e1e1">
            <html:select property="ketqua" styleId="trangthai"
                         style="width:100%"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;">
              <html:option value="">-----Chọn trạng thái-----</html:option>
              <!--<html:option value="00">Chưa đối chiếu</html:option>-->
              <html:option value="0">Khớp đúng</html:option>
              <html:option value="1">Lệch</html:option>
            </html:select>
          </td>
          
        <td align="right" width="15%" bordercolor="#e1e1e1">
            <label for="den_ngay">
              Đến ngày
            </label>
          </td>
          <td bordercolor="#e1e1e1">
            <html:text property="to_date" styleId="to_date" styleClass="fieldText"
                       onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('to_date');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        style="WIDTH: 70px;vertical-align:middle;"/>
             
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="btn_to_date" width="20"
                 style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "to_date", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "btn_to_date"// id of the button
              });
            </script>
          </td>
      </tr>
      
      <tr>
        <logic:notEmpty name="dmNganHang">
            <td width="5%" align="right" bordercolor="#e1e1e1">Hệ thống ngân hàng</td>
            <td bordercolor="#e1e1e1" width="40%">
                <html:select property="ngan_hang" styleId="hthongNganHang" style="width:100%" onchange="changeHTNH()" onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="">-----Chọn hệ thống ngân hàng-----</html:option>
                    <html:options collection="dmNganHang" property="ma_dv" labelProperty="ten_nh"/>
                </html:select>
            </td>
        </logic:notEmpty>
          <td width="15%" align="right" bordercolor="#e1e1e1">Số TK</td>
          <td width="35%" bordercolor="#e1e1e1">
            <html:select property="so_tk" styleId="so_tk" style="width:100%" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="">              
              <html:option value="">-----Chọn số TK-----</html:option>   
              <!--thuongdt doi so_tk-->
              <html:optionsCollection name="TKList" value="so_tk" label="so_tk"/>
            </html:select>
          </td>
      </tr>
      <!--<tr>
        <td colspan="2"></td>
        <td align="right" bordercolor="#e1e1e1">Loại tk</td>
        <td>
          <b><span id="hintAccountType"></span>&nbsp;-&nbsp;</b>
          <b><span id="hintMoneyType"></span></b>
        </td>
      </tr>-->
      
    </tbody>
  </table>
  <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tracuu">
            <button type="button" onclick="check('find')" class="ButtonCommon" accesskey="t">
              <span class="sortKey">T</span>ra cứu
            </button>
          </span>
          <logic:equal value="0001" name="strMaKBTinh" >
          <span id="tracuu_dmkb">
              <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" style="width:90pt" >
                <span class="sortKey">D</span>anh m&#7909;c KB
              </button>
          </span>
          </logic:equal>
          <logic:equal value="0002" name="strMaKBTinh" >
          <span id="tracuu_dmkb">
              <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" style="width:90pt" >
                <span class="sortKey">D</span>anh m&#7909;c KB
              </button>
          </span>
          </logic:equal>
          <span id="thoat"> 
            <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </span>
        </td>
    </tr>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="18">
            <font color="Gray">Kết quả t&igrave;m kiếm</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
              <tr>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">SHKB</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Tên kho bạc</div>
                </th>                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Tên ngân hàng</div>
                </th>
                <th>
                  <div align="center">Số TK </div>
                </th>
                <th>
                  <div align="center">Nguyên tệ</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ngày ĐC</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Trạng thái ĐC</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Chi tiết</div>
                </th>
              </tr>
              </thead>
               
              <%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1"
                     style="font-size:1.15em">
                <logic:notEmpty name="listTracuu">
                  <logic:iterate id="listTracuu" name="listTracuu" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center" width="4%">
                        <bean:write name="listTracuu" property="shkb"/>
                      </td>
                      <td align="left" width="25%">
                        <bean:write name="listTracuu" property="ten_kb"/>
                      </td>
                      <td align="left" width="30%">
                        <bean:write name="listTracuu" property="ten_nh"/>
                      </td>
                       <td align="left" width="12%">
                        <bean:write name="listTracuu" property="so_tk"/>
                      </td>
                      <td align="left" width="6%">
                        <bean:write name="listTracuu" property="ma_nt"/>
                      </td>
                      <td align="center" width="8%">
                        <bean:write name="listTracuu" property="ngay_dc"/>
                      </td>
                      <td align="left" width="10%">
                        <logic:equal value="0" name="listTracuu" property="ket_qua">Khớp đúng</logic:equal>
                        <logic:equal value="1" name="listTracuu" property="ket_qua">Lệch</logic:equal>
                      </td>
                      <td align="center" width="5%">
                        <a href='<html:rewrite page="/loadDChieuSKeAction.do"/>?ma_kb=<bean:write name="listTracuu" property="ma_kb"/>&shkb=<bean:write name="listTracuu" property="shkb"/>&ma_nh=<bean:write name="listTracuu" property="ma_nh"/>&ngay_dc=<bean:write name="listTracuu" property="ngay_dc"/>&so_tk=<bean:write name="listTracuu" property="so_tk"/>'>Chi tiết</a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <logic:empty name="listTracuu">
                    <tr>
                        <td colspan="8"><div style="color:red;">Không có kết quả tìm kiếm</div></td>
                    </tr>
                </logic:empty>
                <tr>
                  <td colspan="10" align="center">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
</html:form>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUUSODU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
//function loadAccountDetailAJAX(){
//    var soTk = jQuery('#so_tk').val();
//    var nh_id = jQuery('#ngan_hang').val();
//    jQuery.ajax({
//          type : "POST",
//          url : "getTKNHKB.do", 
//          data :  {"soTk":soTk, "nh_id":nh_id}
//        , success : function (data, textstatus) {
//          if (textstatus != null && textstatus == 'success') {
//            if (data != null) {
//              if(data[0].loai_tk == '01'){
//                jQuery('#hintAccountType').text('TK Tiền gửi')
//              }else if(data[0].loai_tk == '02'){
//                jQuery('#hintAccountType').text('TK Thanh toán')
//              }else if(data[0].loai_tk == '03'){
//                jQuery('#hintAccountType').text('TK Chuyên thu')
//              }
//              jQuery('#hintMoneyType').text(data[0].loai_tien)
//            }
//          }
//        },
//        error : function (textstatus) {
//            alert(textstatus);
//        }
//    });
//}
</script>