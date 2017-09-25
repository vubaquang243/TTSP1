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
    function ghi(){
        var f = document.forms[0];
        if(f.tu_ngay.value == ''){
            alert("Từ ngày không được để trống");
            f.tu_ngay.focus();
            return false
        }
        if(f.vat.value == ''){
            alert("VAT không được để trống");
            return false;
        }
        if(f.muc_phi.value == ''){
            alert("Mức phí không được để trống");
            return false;
        }
        //validate giờ từ và giờ đến
        if(f.gio_tu.value != '' || f.den_gio.value != ''){
            if(f.gio_tu.value == ''){
                alert("Cần nhập \"từ giờ\"");
                f.gio_tu.focus();
                return false
            }
            if(f.den_gio.value == ''){
                alert("Cần nhập \"đến giờ\"");
                f.den_gio.focus();
                return false
            }
            var timeRegEx = /^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$/; //hh:mm
            var resultRegEx = f.gio_tu.value.match(/^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])$/);
            if(resultRegEx == '' || resultRegEx == null){
                alert("\"Từ giờ\" phải có định dạng (hh:mm) (00-23h : 00-59') ");
                f.gio_tu.focus();
                return false;
            }
            resultRegEx = f.den_gio.value.match(timeRegEx);
            if(resultRegEx == '' || resultRegEx == null){
                alert("\"Đến giờ\" phải có định dạng (00-23h : 00-59') ");
                f.den_gio.focus();
                return false;
            }
            var tempGioTu = f.gio_tu.value.split(":")
            var tempGioDen = f.den_gio.value.split(":")
            var gioTu = tempGioTu[0]+tempGioTu[1]
            var gioDen = tempGioDen[0]+tempGioDen[1]
            if(gioTu >= gioDen){
                alert("\"Từ giờ\" không thể lớn hơn \"Đến giờ\".");
                return false;
            }
        }
        //validate tiền từ và tiền đến
        if((f.tien_tu.value != '' && f.den_tien.value != '')|| f.den_tien.value != ''){
            if(f.tien_tu.value == ''){
                alert("Cần nhập \"từ tiền\"");
                f.tien_tu.focus();
                return false
            }
            if(Number(f.tien_tu.value) >= Number(f.den_tien.value)){
              alert("\"Từ tiền\" không thể lớn hơn \"Đến tiền\".");
              return false;
            }
        }
        //validate giá sàn giá trần
        if(f.gia_san.value != '' || f.gia_tran.value != ''){
            if(f.gia_san.value == ''){
                alert("Cần nhập \"giá sàn\"");
                f.tien_tu.focus();
                return false
            }
            if(f.gia_tran.value == ''){
                alert("Cần nhập \"giá trần\"");
                f.den_tien.focus();
                return false
            }
            if(Number(f.gia_san.value) >= Number(f.gia_tran.value)){
                alert("\"Giá sàn\" không thể lớn hơn \"Giá trần\".");
                return false;
            }
        }
        //validate ngày đến
        if(f.den_ngay.value != ''){
            var tempTuNgay = f.tu_ngay.value.split("/");
            var tempDenNgay = f.den_ngay.value.split("/");
            var tuNgay = tempTuNgay[2]+tempTuNgay[1]+tempTuNgay[0];
            var denNgay = tempDenNgay[2]+tempDenNgay[1]+tempDenNgay[0];
            if(Number(tuNgay) >= Number(denNgay)){
                alert("\"Từ ngày\" không thể lớn hơn \"Đến ngày\".");
                return false;
            }
        }
        f.submit();
    }
    function thoat(){
        var f = document.forms[0];
        f.action = 'quanLyPhi.do';
        f.submit();
    }
</script>
<title>Hỗ Trợ Tính Phí</title>
<html:form action="/updatePhi.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Sửa mức phí</span>
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
                <td width="15%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
                <td>
                  <html:select property="ma_nh" styleId="ma_nh" style="width:80%" disabled="true">
                    <html:option value="">Chọn ngân hàng</html:option>
                    <html:options collection="listNganHang" property="ma_dv" labelProperty="ten_nh"/>
                  </html:select>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Loại giao dịch</td>
                <td>
                  <html:select property="loai_gd" styleId="loai_gd" style="width:80%" disabled="true">
                    <html:option value="">Chọn loại giao dịch</html:option>
                    <html:option value="INT">Cùng hệ thống NH</html:option>
                    <html:option value="EXT">Khác hệ thống NH</html:option>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Loại phí</td>
                <td>
                  <html:select property="loai_phi" styleId="loai_phi" style="width:80%">
                    <html:option value="">Chọn loại phí</html:option>
                    <html:option value="MON">Theo món</html:option>
                    <html:option value="GIA_TRI">Theo giá trị</html:option>
                  </html:select>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
                <td>
                  <html:text property="loai_tien" maxlength="3" style="width:30%"
                           onkeypress="return blockNumber(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Từ giờ</td>
                <td>
                  <html:text property="gio_tu" maxlength="5" style="width:30%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Đến giờ</td>
                <td>
                  <html:text property="den_gio" maxlength="5" style="width:30%" onkeypress="return blockWordsIncludeDoubleDot(event)"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Từ tiền</td>
                <td>
                  <html:text property="tien_tu" style="width:30%" onkeypress="return blockWords(event)"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Đến tiền</td>
                <td>
                  <html:text property="den_tien" style="width:30%" onkeypress="return blockWords(event)"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Mức phí</td>
                <td>
                  <html:text property="muc_phi" maxlength="4" style="width:30%" onkeypress="return blockWordsIncludeDot(event)"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">VAT</td>
                <td>
                  <html:text property="vat" maxlength="4"  style="width:30%" onkeypress="return blockWordsIncludeDot(event)"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Giá sàn</td>
                <td>
                  <html:text property="gia_san" style="width:30%" onkeypress="return blockWords(event)"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Giá trần</td>
                <td>
                  <html:text property="gia_tran"  style="width:30%" onkeypress="return blockWords(event)"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Từ ngày</td>
                <td width="30%">
                  <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_tu_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "tu_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_tu_ngay"// id of the button
                    });
                  </script>
                </td>
                <td align="right" bordercolor="#e1e1e1">Đến ngày</td>
                <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_den_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "den_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_den_ngay"// id of the button
                    });
                  </script>
                  <span style="color:red;font-size:6.9pt">* Chỉ nên cập nhật khi muốn khóa mức phí này</span>
                </td>
                <td>
                    <input type="hidden" name="execute" value="true"/>
                    <html:hidden property="id" />
                    <html:hidden property="ma_nh" />
                    <html:hidden property="loai_gd" />
                </td>
              </tr>
            </table>
            <br/>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="ghi">
            <button type="button" onclick="ghi()" class="ButtonCommon" accesskey="g" style="width:100px">
              <span class="sortKey">G</span>hi
            </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="thoat()" class="ButtonCommon" accesskey="t" style="width:100px">
              <span class="sortKey">T</span>hoát
            </button>
          </span>
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