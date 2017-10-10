<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js" type="text/javascript" />

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ltt.quyettoan.check.valid.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<style type="text/css">
  #input-form{
    height:23px;
  }
  hr{
  width: 100%;
  }
  .table-input{
    width: 60%;
  }
  .blankspace{
    height: 5px;
  }
</style>
<script type="text/javascript">
      $(document).ready(function(){
        $('#thoat').click(function(){
          window.location.replace("mainAction.do");
        });
        
        //
        $('#ngayQT').attr('disabled','disabled');
      });
      
      function onChange(){
        var maNH = $('#maNH').val();
        if(maNH != '' || '' != maNH){
          jQuery.ajax({
          type : "POST", url : "DSachLoaiTien.do", data : {"maNH" : maNH},
          dataType : 'json', success : function (data, textstatus){
            if(data != null){
              if(data.logout != null && data.logout){
                document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                document.forms[0].submit();
              }else{
                var lstLoaiTien = new Object();
                lstLoaiTien = JSON.parse(data[0]);
                if(lstLoaiTien != null){
                  jQuery('#loaiTien option').remove();
                  jQuery('#loaiTien').append('<option value="">---Chọn loại tiền---<\/option>');
                  for(var i = 0; i < lstLoaiTien.size(); i++){
                    jQuery('#loaiTien').append('<option value="'+ lstLoaiTien[i].ma_nt + '" >' + lstLoaiTien[i].ma_nt + '<\/option>');
                    if(lstLoaiTien[i].ma_nt == "VND"){
                      jQuery('#loaiTien').append('<option value="'+ lstLoaiTien[i].ma_nt + '" selected="true" >' + lstLoaiTien[i].ma_nt + '<\/option>');
                    }
                  }
                }
              }
            }
          }
          });
        }
      }
      function changeInput(){
        var option = $('#loaiQT').val();
          if(option == "04" || option == "05"){
            $('#QToanBu').attr('readonly', true);
            $('#QToanBu').attr('disabled', true);
            $('#QToanChi').attr('readonly', true);
            $('#QToanChi').attr('disabled', true);
          }else if(option == "06"){
            $('#QToanChi').attr('readonly', true);
            $('#QToanChi').attr('disabled', true);
            $('#QToanBu').attr('readonly', false);
            $('#QToanBu').attr('disabled', false);
          }else if(option =="07"){
            $('#QToanChi').attr('readonly', false);
            $('#QToanChi').attr('disabled',false);
            $('#QToanBu').attr('readonly', false);
            $('#QToanBu').attr('disabled',false);
          }
      }
      $(document).ready(function(){
       var time = new Date();
        var display = "";
        if(time.getDate() < 10){
          display += "0" + (time.getDate()) + '/';
        }else display+= (time.getDate()) + '/';
        if((time.getMonth() + 1) < 10){
          display += "0" + (time.getMonth() + 1) + '/';
        }else display += (time.getMonth() + 1) + '/';
        display += time.getFullYear();
        $('#ngayQT').val(display);
      });
      
      function resetValue(){
        $('#maNH').val("");
        $('#loaiTien').val("");
        $('#loaiQT').val("");
        $('#QToanBu').val("");
        $('#QToanChi').val("");
        $('#noiDungQToan').val("");
      }
      
      function getSoTienChi(){
        var loaiQT = $('#loaiQT').val();
        var loatTien = $('#loaiTien').val();
        var maNH = $('#maNH').val();
        if((loaiQT == "04" || loaiQT == "05") && maNH != ""){
          jQuery.ajax({
            type : "POST", url : "getMoney.do", data : {"loaiQT" : loaiQT, "maNH" : maNH }, dataType : "json",
            success : function(data, textstatus){
              if(data != null){
                if(data.logout != null && data.logout){
                  document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                  document.forms[0].submit();
                }else{
                  var getCurren = new Object();
                  getCurren = JSON.parse(data[0]);
                   if(loatTien == "VND")
                    $('#QToanChi').val(changeVNDCurrency(getCurren));
                   else $('#QToanChi').val(changeForeignCurrency(getCurren));
                }
              }else alert("Không có dữ liệu phù hợp");
            }
          });
        }
      }
      
      function submitData(){
          var maNH = $('#maNH').val();
          var loaiTien = $('#loaiTien').val();
          var loaiQT = $('#loaiQT').val();
          var QToanBu = $('#QToanBu').val();
          var QToanChi = $('#QToanChi').val();
          var ngayQT = $('#ngayQT').val();
          var noiDungQToan = $('#noiDungQToan').val();
            jQuery.ajax({
            type : "POST", url : "addDeNghiQuyetToan.do", data :{"maNH" : maNH, "loaiTien" : loaiTien, "loaiQT" : loaiQT,
              "QToanBu" : QToanBu, "QToanChi" : QToanChi, "ngayQT" : ngayQT, "noiDungQToan" : noiDungQToan}, success : function(data, textstatus){
                  if(data == null){
                    $('#idMessage').text("Thêm mới thành công !");
                    resetValue();
                  }else{
                    $('#idMessage').text("Thêm mới không thành công. Vui lòng kiểm tra lại dữ liệu !");
                  }
              }
          });
        }
      
      //xu ly dinh dang tien te nuoc ngoai
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
  
  //dung cho viec thay doi dinh dang tien te
  function changeCurrency(str){
    console.log(str.value);
    var cateCurrency = $('#loaiTien').val();
    if(cateCurrency != ""){
    if(cateCurrency == "VND"){
      str.value = changeVNDCurrency(str.value);
    }else{
      str.value = changeForeignCurrency(str.value);
    }}
  }
  
  function changeMoney(){
    var cateCurrency = $('#loaiTien').val();
    var qtBu = $('#QToanBu').val();
    var qtChi= $('#QToanChi').val();
    if(cateCurrency != ""){
      if(cateCurrency == "VND"){
        if(qtBu != ""){
          $('#QToanBu').val(changeVNDCurrency(qtBu));
        }
        if(qtChi != ""){
          $('#QToanChi').val(changeVNDCurrency(qtChi));
        }
      }else{
        if(qtBu != ""){
          $('#QToanBu').val(changeForeignCurrency(qtBu));
        }
        if(qtChi != ""){
          $('#QToanChi').val(changeForeignCurrency(qtChi));
        }
      }
    }
  }
  function resetInput(){
    var qtBu = $('#QToanBu').val();
    var qtChi= $('#QToanChi').val();
    if(qtBu != ""){
      $('#QToanBu').val("");
    }
    if(qtChi != ""){
      $('#QToanChi').val("");
    }
  }
</script>
<div id="body">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Lập mới đề nghị quyết toán </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
    </table>
    <!-- <html:form action="/addDeNghiQuyetToan.do" styleId="frmDeNghiQT" > -->
    <fieldset style="width:auto;">
    <span id="idMessage" style="color : red;"></span>
    <div class="app_error">
      <html:errors/>
    </div>
    <table border="0" cellspacing="0" cellpadding="0" class="table-input"
         align="center" >
        <tbody>
          <tr id="input-form">
          <td width="20%">Ngân hàng </td><td width="30%">
            <html:select styleClass="selectBox" property="maNH"
                styleId="maNH" style="width: 200px;" onblur="onChange(); getSoTienChi();" >
                <option value="">---Chọn loại ngân hàng---</option>
                <html:optionsCollection label="ten" value="ma_nh" name="dmNH"/>
           </html:select>
          </td>
          </tr>
          <tr id="input-form">
          <td width="20%">Loại tiền </td>
          <td width="30%">
           <html:select styleClass="selectBox" property="loaiTien"
                styleId="loaiTien" style="width: 200px;" onblur="changeMoney(); resetInput();">
                <option value="" selected="selected">---Chọn loại tiền---</option>
           </html:select>
          </tr>
          <tr id="input-form">
            <td width="20%">Loại quyết toán </td>
            <td width="30%">
            <html:select styleClass="selectBox" property="loaiQT" onblur="getSoTienChi(); changeInput(); resetInput();"
                styleId="loaiQT" style="width: 200px;">
                <option value="">---Chọn loại quyết toán---</option>
                <option value="04">Bù chi ngày lỗi</option>
                <option value="05">Thấu chi</option>
                <option value="06">Thu ngày lỗi</option>
                <option value="07">Loại khác</option>
            </html:select>
          </tr>
          <tr id="input-form">
            <td width="20%">Số tiền quyết toán thu </td>
            <td width="30%">
              <html:text property="QToanBu"  styleId="QToanBu" onkeypress="return numberBlockKey1();" style="width: 200px;"
                onblur="changeMoney();" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true" />
            </td>
          </tr>
          <tr id="input-form">
            <td width="20%">Số tiền quyết toán chi </td>
            <td width="30%">
              <html:text property="QToanChi" styleId="QToanChi" onkeypress="return numberBlockKey1();" style="width: 200px;" 
              onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true" onblur="changeMoney();" />
            </td>
          </tr>
          <tr id="input-form">
            <td width="20%">Ngày quyết toán</td>
            <td width="30%">
              <html:text property="ngayQT" styleId="ngayQT" style="width: 200px;" disabled="disabled" readonly="true" />
            </td>
          </tr>
          <tr id="input-form">
            <td width="20%">Nội dung </td>
            <td width="30%">
              <html:textarea property="noiDungQToan" styleId="noiDungQToan" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width: 200px;" rows="5" />
            </td>
          </tr>
        </tbody>
    </table>
    </fieldset>
    <div class="blankspace"></div>
    <div align="center"><input type="submit" id="create" value="Tạo điện" onclick="submitData();" />
      <button id="thoat" >Thoát</button>
    </div>
   <!-- </html:form> -->
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>