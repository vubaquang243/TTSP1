<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
    jQuery(document).init(function () {
        
    });
    
    function check(type) {
        var f = document.forms[0];
        if(type == 'clear'){
            f.ma_dv.value = '';
            f.fromDate.value = ''
            f.loaiTien.value = ''
            f.laiSuat.value = ''
            f.loaiLaiSuat.value = ''
            return;
        }
        if(type=='add'){
            if(f.ma_dv.value==''){
                alert("Phải chọn ngân hàng");
                f.ma_dv.focus();
                return
            }
            if(f.fromDate.value==''){
                alert("Phải chọn từ ngày");
                f.fromDate.focus();
                return
            }
            if(f.loaiTien.value==''){
                alert("Phải chọn loại tiền");
                f.loaiTien.focus();
                return
            }
            if(f.laiSuat.value==''){
                alert("Phải nhập lãi suất");
                f.laiSuat.focus();
                return
            }
            if(f.loaiLaiSuat.value==''){
                alert("Phải chọn loại lãi suất");
                f.loaiLaiSuat.focus();
                return
            }
            if(confirm("(Khi thêm mới lãi hiện hành sẽ được đóng lại sau một ngày) Bạn có chắc chắn muốn thêm không  ?")){
                f.submit();
            }
        }
        if(type=='exit'){
            f.action = 'traCuuLaiSuat.do';
            f.submit();
        }
    }        
    
</script>
<%@ include file="/includes/ttsp_header.inc"%>
<title>Thêm Lãi Suất</title>
<div class="box_common_conten">
<html:form action="/addLaiSuat.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Thêm Lãi Suất</span>
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
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Điều kiện
                                                             t&igrave;m kiếm</font></span>
          </td>
        </tr>
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
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
              <td width="30%">
                <html:select property="ma_dv" styleId="ma_dv" style="width:80%">
                  <html:option value="">Chọn ngân hàng</html:option>
                  <html:options collection="listNganHang" property="ma_dv" labelProperty="ten_nh"/>
                </html:select>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Ngày có hiệu lực</td>
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
            </tr>
            <tr>
              <td align="right" bordercolor="#e1e1e1">Loại lãi suất</td>
              <td>
                <html:select property="loaiLaiSuat" styleId="loaiLaiSuat" style="width:80%">
                  <html:option value="">Chọn loại lãi suất</html:option>
                  <html:option value="D">Ngày</html:option>
                  <html:option value="M">Tháng</html:option>
                  <html:option value="Y">Năm</html:option>
                </html:select>
              </td>
              <td align="right" bordercolor="#e1e1e1">Loại tiền</td>
              <td>
                <html:text property="loaiTien" maxlength="3" style="width:32%"
                           onkeypress="return blockNumber(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td align="right" bordercolor="#e1e1e1">Lãi suất</td>
              <td>
                <html:text property="laiSuat" styleId="laiSuat" maxlength="4" style="width:15%"
                           onkeypress="return blockWords(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>%
              </td>
              <td align="right" bordercolor="#e1e1e1">Ghi chú</td>
              <td><html:textarea property="ghiChu" styleId="ghiChu"/></td>
              <td>
                 <input type="hidden" name="order" value="update"/>
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
          <span id="clear">
            <button type="button" onclick="check('clear')" class="ButtonCommon"
                    accesskey="l" style="width:100px">
              <span class="sortKey">L</span>àm mới
            </button>
          </span>
          <span id="themMoi"> 
            <button type="button" onclick="check('add')"
                    class="ButtonCommon" accesskey="m" style="width:100px">
              Thêm<span class="sortKey"> M</span>ới
            </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="check('exit')" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>hoát
            </button>
          </span>
        </td>
      </tr>
    </table>
</html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
    function blockNumber(event){
        if(event.keyCode >= 65 && event.keyCode <= 90 || event.keyCode >= 97 && event.keyCode <= 122){
            return true;
        }
        return false;
    }
    function blockWords(event){
        if(event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode == 46){
            return true;
        }
        return false;
    }
</script>