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
    jQuery(document).ready(function(){
        getNganHang();
    });
    
    function inputTyGia(){
        jQuery('.inputElement').val(jQuery('#inputAll').val())
    }
    
    function submitForm(type){
        var f = document.forms[0];
        if(type == 'traCuu'){
            f.action = "khaiTyGia.do";
        }
        f.submit();
    }
    function blockWords(event){
        if(event.keyCode >= 48 && event.keyCode <= 57){
            return true;
        }
        return false;
    }
    function getNganHang(){
        if(jQuery('#ma_kb').val() == '3'){
            jQuery('#loaiTyGia').removeAttr('disabled');
        }else{
            jQuery("#loaiTyGia").attr('disabled','disabled');
        }
        var ma_nh_selected_='<%=request.getAttribute("ma_nh_selected_")==null?"":request.getAttribute("ma_nh_selected_")%>';
        var kb_id = jQuery("#ma_kb").val();        
        jQuery("#ma_nh").find('option').remove().end();
        jQuery.ajax({
            type:"post",
            url:"getNganHang.do",
            data:{"kb_id":kb_id},
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    for(var i = 0;i<data.length ;i++){
                        jQuery('#ma_nh').append('<option value="' + data[i].ma_nh + '" >' + data[i].ten + '<\/option>');
                    }
                }
                jQuery('#ma_nh').val(ma_nh_selected_);
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
</script>
<title>Khai tỷ giá</title>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/ghiTyGia.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Khai tỷ giá</span>
        </td>
        <td width="62">
          <img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img>
        </td>
        <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4">
          <img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
      <tbody>
      <tr>
        <td>
          <br/>
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
                <td width="10%" align="right" bordercolor="#e1e1e1">Kho bạc</td>
                <td>
                  <html:select property="ma_kb" styleId="ma_kb" style="width:80%" onchange="getNganHang()">
                    <html:options collection="listKhoBac" property="id" labelProperty="kb_huyen"/>
                  </html:select>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
                <td>
                  <html:select property="ma_nh" styleId="ma_nh" style="width:80%"></html:select>
                </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Từ ngày</td>
              <td width="30%">
                <html:text property="fromDate" styleId="fromDate" styleClass="fieldText"
                           onkeypress="return numbersonly(this,event,true) "
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('fromDate');"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            style="WIDTH: 70px;vertical-align:middle;"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="btn_fromDate" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "fromDate", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "btn_fromDate"// id of the button
                  });
                </script>
              </td>
              <td align="right" bordercolor="#e1e1e1">Đến ngày</td>
              <td>
                <html:text property="toDate" styleId="toDate" styleClass="fieldText"
                           onkeypress="return numbersonly(this,event,true) "
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('toDate');"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                            style="WIDTH: 70px;vertical-align:middle;"/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="btn_toDate" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "toDate", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "btn_toDate"// id of the button
                  });
                </script>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td>
                  <html:select property="ma_nt" styleId="ma_nt" style="width:74px">
                    <html:optionsCollection value="ma" label="ma" name="tienTe"/>
                  </html:select>
              </td>
              <c:if test="${requestScope.isThanhToanVien  == 'false'}">
              <td width="15%" align="right" bordercolor="#e1e1e1">Loại tỷ giá</td>
              <td>
                  <html:select property="loaiTyGia" styleId="loaiTyGia" style="width:74px" disabled="true">
                    <html:option value="LAI_PHI">Lãi phí</html:option>
                    <html:option value="HACH_TOAN">Hạch toán</html:option>
                  </html:select>
              </td>
              </c:if>
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
          <span id="traCuu">
            <button type="button" id="btnTraCuu" onclick="submitForm('traCuu')" class="ButtonCommon"
                    accesskey="g" style="width:200px">
              <span class="sortKey">C</span>ập nhật danh sách
            </button>
          </span>
        </td>
      </tr>
    </table>
    <c:if test="${status != null}">
        <c:if test="${status eq 'success'}">
          <span style="color:green">Cập nhật tỷ giá thành công.</span>
        </c:if>
        <c:if test="${status eq 'error'}">
          <span style="color:red">Cập nhật tỷ giá không thành công.</span>
        </c:if>
    </c:if>
    <c:if test="${requestScope.tyGiaHienTai != null}">
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Danh sách tỷ giá để cập nhật</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="40%" align="center">
              <thead>
                <tr>
                  <th>Nhập tỷ giá chung</th>
                  <td align="center"><input id="inputAll" style="width:96%" class="inputTyGia" type="text" onkeyup="inputTyGia()" onkeypress="return blockWords(event)" /></td>
                </tr>
                <tr>
                  <th>Ngày</th>
                  <th>Tỷ giá</th>
                </tr>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                  <c:forEach items="${requestScope.tyGiaHienTai}" var="item" varStatus="status">
                    <c:if test="${status.index % 2 != 0}">
                    <tr class="trDanhSachLe">
                      <td align="center">
                          <b>
                            <input type="hidden" name="ngay" value="<bean:write name="item" property="ngay_gd"/>" />
                            <bean:write name="item" property="ngay_gd"/>
                          </b>
                      </td>
                      <td align="center">
                          <input class="inputElement inputTyGia" style="width:96%;background-color:rgb(232, 255, 249)" name="tyGia" type="text" value="<bean:write name="item" property="ty_gia"/>" onkeypress="return blockWords(event)" />
                      </td>
                    </tr>
                    </c:if>
                    <c:if test="${status.index % 2 == 0}">
                    <tr>
                      <td align="center">
                          <b>
                            <input type="hidden" name="ngay" value="<bean:write name="item" property="ngay_gd"/>" />
                            <bean:write name="item" property="ngay_gd"/>
                          </b>
                      </td>
                      <td align="center">
                          <input class="inputElement inputTyGia" style="width:96%" name="tyGia" type="text" value="<bean:write name="item" property="ty_gia"/>" onkeypress="return blockWords(event)" />
                      </td>
                    </tr>
                    </c:if>
                  </c:forEach>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="ghi">
            <button type="button" onclick="submitForm('ghi')" class="ButtonCommon"
                    accesskey="g" style="width:100px">
              <span class="sortKey">G</span>hi
            </button>
          </span>
        </td>
      </tr>
    </table>
    </c:if>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>