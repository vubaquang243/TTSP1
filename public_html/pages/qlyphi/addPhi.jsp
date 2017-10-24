<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript">
    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () { 
    });
    
    function themMoi(){
        var f = document.forms[0];
        if(f.he_thong_ngan_hang.value == ''){
            alert("Phải chọn ngân hàng");
            f.he_thong_ngan_hang.focus();
            return
        }
        if(f.loai_gd.value == ''){
            alert("Phải chọn loại giao dịch");
            f.loai_gd.focus();
            return
        }
        if(f.loai_phi.value == ''){
            alert("Phải chọn loại phí");
            f.loai_phi.focus();
            return
        }
        if(f.loai_tien.value == ''){
            alert("Phải chọn loại tiền");
            f.loai_tien.focus();
            return
        }
        
        var flag = false;
        jQuery('#addTable tr').each(function() {
            var gio_tu = jQuery(this).find(".gio_tu").val();
            var den_gio = jQuery(this).find(".den_gio").val();
            var tien_tu = jQuery(this).find(".tien_tu").val();
            var den_tien = jQuery(this).find(".den_tien").val();
            var muc_phi = jQuery(this).find(".muc_phi").val();
            var gia_san = jQuery(this).find(".gia_san").val();
            var gia_tran = jQuery(this).find(".gia_tran").val();
            var tu_ngay = jQuery(this).find(".tu_ngay").val();
            var vat = jQuery(this).find(".vat").val();
            var resultRegEx;
            
            var timeRegEx = /^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$/; //hh:mm
            var dateRegEx = /(((0[1-9]|[12][0-9]|3[01])([/])(0[13578]|10|12)([/])(\d{4}))|(([0][1-9]|[12][0-9]|30)([/])(0[469]|11)([/])(\d{4}))|((0[1-9]|1[0-9]|2[0-8])([/])(02)([/])(\d{4}))|((29)(\.|-|\/)(02)([/])([02468][048]00))|((29)([/])(02)([/])([13579][26]00))|((29)([/])(02)([/])([0-9][0-9][0][48]))|((29)([/])(02)([/])([0-9][0-9][2468][048]))|((29)([/])(02)([/])([0-9][0-9][13579][26])))/;     //dd/mm/yyyy
            
            if(muc_phi == '' || muc_phi == null){
                  alert("Mức phí không được để trống");
                  flag = true;
                  return false;
            }
            if(vat == '' || vat == null){
                alert("VAT không được để trống");
                flag = true;
                return false;
            }
            //validate từ giờ - đến giờ nếu có
            if(gio_tu != '' || den_gio != ''){
                if(gio_tu == '' && gio_tu != null){
                    alert("Cần nhập \"từ giờ\"");
                    flag = true;
                    return false
                }
                if(den_gio == '' && den_gio != null){
                    alert("Cần nhập \"đến giờ\"");
                    flag = true;
                    return false
                }
                resultRegEx = gio_tu.match(/^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$/);
                if(resultRegEx == '' || resultRegEx == null){
                    alert("\"Từ giờ\" phải có định dạng (hh:mm) (00-23h : 00-59') ");
                    flag = true;
                    return false;
                }                
                resultRegEx = den_gio.match(timeRegEx);
                if(resultRegEx == '' || resultRegEx == null){
                    alert("\"Đến giờ\" phải có định dạng (00-23h : 00-59') ");
                    flag = true;
                    return false;
                }
                var tempGioTu = gio_tu.split(":")
                var tempGioDen = den_gio.split(":")
                var gioTu = tempGioTu[0]+tempGioTu[1]
                var gioDen = tempGioDen[0]+tempGioDen[1]
                if(gioTu >= gioDen){
                    alert("\"Từ giờ\" không thể lớn hơn \"Đến giờ\".");
                    flag = true;
                    return false;
                }
            }
            //validate tiền từ và tiền đến
            if((tien_tu != '' && den_tien != '')|| den_tien != ''){
                if(tien_tu == ''){
                    alert("Cần nhập \"từ tiền\"");
                    flag = true;
                    return false
                }
                if(Number(tien_tu) >= Number(den_tien)){
                  alert("\"Từ tiền\" không thể lớn hơn \"Đến tiền\".");
                  flag = true;
                  return false;
                }
            }
            //validate giá sàn giá trần
            if(gia_san != '' || gia_tran != ''){
                if(gia_san == ''){
                    alert("Cần nhập \"giá sàn\"");
                    flag = true;
                    return false
                }
                if(gia_tran == ''){
                    alert("Cần nhập \"giá trần\"");
                    flag = true;
                    return false
                }
                if(Number(gia_san) >= Number(gia_tran)){
                    alert("\"Giá sàn\" không thể lớn hơn \"Giá trần\".");
                    flag = true;
                    return false;
                }
            }
            
            if(tu_ngay == '' && tu_ngay == null){
                alert("Từ ngày không được để trống ");
                flag = true;
                return false;
            }else{
                resultRegEx = tu_ngay.match(dateRegEx)
                if(resultRegEx == '' || resultRegEx == null){
                    alert("Từ ngày phải có định dạng dd/mm/yyyy ");
                    flag = true;
                    return false;
                }
            }
            
        });
        
        if(flag){
           return false;
        }
        f.submit();
    }
    function thoat(){
        var f = document.forms[0];
        f.action = 'quanLyPhi.do';
        f.submit();
    }
    function removeRow(r){
        var i = r.parentNode.parentNode.rowIndex;
        document.getElementById("addTable").deleteRow(i-1);
    }
    function addRow(){
        jQuery('#addTable').append(
                '<tr>'+
                  '<td align="center">'+
                    '<input type="text" name="gio_tu" maxlength="5" class="gio_tu" style="width:90%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="den_gio" maxlength="5" class="den_gio" style="width:90%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="tien_tu" class="tien_tu" style="width:90%" onkeypress="return blockWords(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="den_tien" class="den_tien" style="width:90%" onkeypress="return blockWords(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="muc_phi" maxlength="4" class="muc_phi" style="width:90%" onkeypress="return blockWordsIncludeDot(event)"/>'+
                  '</td>'+ 
                  '<td align="center">'+
                   '<input type="text" name="vat" maxlength="4" class="vat" style="width:90%" onkeypress="return blockWordsIncludeDot(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="gia_san" class="gia_san" style="width:90%" onkeypress="return blockWords(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="gia_tran" class="gia_tran" style="width:90%" onkeypress="return blockWords(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="text" name="tu_ngay" maxlength="10" class="tu_ngay" style="WIDTH: 90%;" onkeypress="return blockWordsIncludeSlash(event)"/>'+
                  '</td>'+
                  '<td align="center">'+
                    '<input type="button" onclick="removeRow(this)" class="removeField" style="width:90%" value="-"/>'+
                  '</td>'+
                '</tr>'
        );
    }
</script>
<title>Khai báo phí</title>
<html:form action="/themPhi.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Khai báo phí</span>
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
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <c:if test="${requestScope.error != null}">
          <tr>
            <td style="color:red"><c:out value="${requestScope.error}"/></td>
          </tr
        </c:if>
        <c:if test="${requestScope.success != null}">
          <tr>
            <td style="color:green"><c:out value="${requestScope.success}"/></td>
          </tr
        </c:if>
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Hệ thống ngân hàng</td>
              <td width="30%">
                <html:select property="he_thong_ngan_hang" styleId="he_thong_ngan_hang" style="width:80%">
                  <html:option value="">Chọn ngân hàng</html:option>
                  <html:options collection="listNganHang" property="ma_dv" labelProperty="ten_nh"/>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại giao dịch</td>
              <td width="30%">
                <html:select property="loai_gd" styleId="loai_gd" style="width:80%">
                  <html:option value="">Chọn loại giao dịch</html:option>
                  <html:option value="INT">Cùng hệ thống NH</html:option>
                  <html:option value="EXT">Khác hệ thống NH</html:option>
                </html:select>
              </td>
            </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại phí</td>
              <td width="30%">
                <html:select property="loai_phi" styleId="loai_phi" style="width:80%">
                  <html:option value="">Chọn loại phí</html:option>
                  <html:option value="MON">Theo món</html:option>
                  <html:option value="GIA_TRI">Theo giá trị</html:option>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td width="30%">
                <html:text property="loai_tien" maxlength="3" style="width:15%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Thông tin khai báo phí</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%" id="mainTable">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Từ giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Đến giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Giá tiền từ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Đến giá tiền</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                    <div align="center">Mức phí</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                    <div align="center">VAT</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Giá trị sàn</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:13%">
                    <div align="center">Giá trị trần</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:12%">
                    <div align="center">Ngày bắt đầu</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">
                        #
                    </div>
                  </th>
                </tr>
              </thead>
              <tbody class="navigateable focused" id="addTable" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                <tr>
                  <td align="center">
                    <input type="text" name="gio_tu" maxlength="5" class="gio_tu" style="width:90%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="den_gio" maxlength="5" class="den_gio" style="width:90%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="tien_tu" class="tien_tu" style="width:90%" onkeypress="return blockWords(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="den_tien" class="den_tien" style="width:90%" onkeypress="return blockWords(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="muc_phi" maxlength="4" class="muc_phi" style="width:90%" onkeypress="return blockWordsIncludeDot(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="vat" maxlength="4" class="vat" style="width:90%" onkeypress="return blockWordsIncludeDot(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="gia_san" class="gia_san" style="width:90%" onkeypress="return blockWords(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="gia_tran" class="gia_tran" style="width:90%" onkeypress="return blockWords(event)"/>
                  </td>
                  <td align="center">
                    <input type="text" name="tu_ngay" maxlength="10" class="tu_ngay" style="WIDTH: 90%;" onkeypress="return blockWordsIncludeSlash(event)"/>
                  </td>
                  <td align="center">
                    <input onclick="addRow()" id="add_row" type="button" value="+" style="width:90%" />
                  </td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="right">
      <tr>
        <td>
          <span id="ghi">
            <button type="button" onclick="themMoi()" class="ButtonCommon"
                    accesskey="g" style="width:100px">
              <span class="sortKey">G</span>hi
            </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="thoat()" class="ButtonCommon"
                    accesskey="o" style="width:100px">
              Th<span class="sortKey">o</span>át
            </button>
          </span>
        </td>
        <td>
          <input type="hidden" value="add" name="add" />
        </td>
      </tr>
    </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
    function blockNumber(event){
        if(event.keyCode >= 65 && event.keyCode <= 90 || event.keyCode >= 97 && event.keyCode <= 122){
            return true;
        }
        return false;
    }
    function blockWordsIncludeDot(event){
        if(event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode == 46){
            return true;
        }
        return false;
    }
    function blockWords(event){
        if(event.keyCode >= 48 && event.keyCode <= 57){
            return true;
        }
        return false;
    }
    function blockWordsIncludeDoubleDot(event){
        if(event.keyCode >= 48 && event.keyCode <= 58 ){
            return true;
        }
        return false;
    }
    function blockWordsIncludeSlash(event){
        if(event.keyCode >= 47 && event.keyCode <= 58 ){
            return true;
        }
        return false;
    }
</script>