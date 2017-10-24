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
<script type="text/javascript">
  function check(type) {
        var f = document.forms[0];
        f.target = '';
        if (type == 'close') {
            f.action = 'mainAction.do';
        }
        if(type == 'in'){
            var ma_dv=jQuery('#ma_dv').val();
            
            var from_date = jQuery('#from_date').val()
            var to_date = jQuery('#to_date').val()
            
            if(ma_dv == '' || ma_dv == null){
              alert(GetUnicode('Chọn ngân hàng'));
              return false;
            }else if(from_date == null || from_date == ''){
              alert(GetUnicode('Chọn từ ngày'));
              return false;
            }else if(to_date == null || to_date == ''){
              alert(GetUnicode('Chọn đến ngày'));
              return false;
            }else {
              var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
              newDialog = window.open('', '_form', params);
              f.target = '_form';
            }
        }
        f.submit();
  }
</script>
<title>Báo cáo thu hộ chi hộ</title>
<div class="app_error">
  <html:errors/>
</div>
<html:form styleId="PrintReportThuHoChiHoAction" action="/PrintReportThuHoChiHoAction.do">
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
            Thu hộ chi hộ</span>
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
  <table style="BORDER-COLLAPSE: collapse; border: solid #e1e1e1;" border="1" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          Thông tin in báo cáo
        </td>
      </tr>
      <tr>
        <td width="20%" align="right" bordercolor="#e1e1e1">Hệ thống ngân hàng</td>
        <td width="40%" bordercolor="#e1e1e1">
          <html:select property="ma_dv" styleId="ma_dv"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:90%">
            <html:option value="">-----Chọn hệ thống ngân hàng-----</html:option>
            <html:optionsCollection name="dmucnh" value="ma_dv" label="ten_nh"/>
          </html:select>
        </td>
        <td align="right" width="15%" bordercolor="#e1e1e1">
          <label for="from_date">Từ ngày</label>
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
        <td align="right" width="15%" bordercolor="#e1e1e1">
          Loại tiền
        </td>
        <td bordercolor="#e1e1e1">
          <html:select property="loai_tien" styleId="loai_tien"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:20%">
            <html:option value="VND">VND</html:option>
            <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
          </html:select>
        </td>
        <td align="right" width="15%" bordercolor="#e1e1e1">
          <label for="to_date">
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
        <td colspan="4" align="center" valign="top">
          <div style="padding:10px 0px 10px 0px;vertical-align:top; ">
            <button type="button"  onclick="check('in');"
                    accesskey="i" class="ButtonCommon">
              <span class="sortKey">I</span>n
            </button>
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>