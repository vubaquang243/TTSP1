<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppConstants" %>

<%@ include file="/includes/ttsp_header.inc"%>
    <title>Tổng hợp Lệnh thanh toán đi</title>
  <center>
    <p class="title2"><b>Tổng hợp Lệnh thanh toán đi</b></p>
    <html:form action="/thLTTDiReport" target="_blank">
      <table width="50%" cellpadding="3" cellspacing="0">
        <tr>
          <td width="20%">
            Từ ngày:
          </td>
          <td width="80%">
            <input type="text" name="tungay" id="tungay" size="20"/>
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="tungay_btn" width="20" style="vertical-align:middle;" />														
														<script type="text/javascript">
													    Calendar.setup(
													    {
													        inputField  : "tungay",      // id of the input field
													        ifFormat    : "%d/%m/%Y",      // the date format
													        button      : "tungay_btn"    // id of the button
						   							    } );
												 		</script>
          </td>
        </tr>
        <tr>
          <td>
            Đến ngày:
          </td>
          <td>
            <input type="text" name="denngay" id="denngay" size="20"/>
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="denngay_btn" width="20" style="vertical-align:middle;" />														
														<script type="text/javascript">
													    Calendar.setup(
													    {
													        inputField  : "denngay",      // id of the input field
													        ifFormat    : "%d/%m/%Y",      // the date format
													        button      : "denngay_btn"    // id of the button
						   							    } );
												 		</script>
          </td>
        </tr>
        <tr>
          <td>
            Trạng thái:
          </td>
          <td>
            <select name="trangthai" id="trangthai">
              <option value="00">All</option>
              <option value="01">KTT đẩy lại</option>
              <option value="02">Chờ hoàn thiện</option>
              <option value="03">Chờ KTT duyệt</option>
              <option value="04">GĐ đẩy lại</option>
              <option value="05">Chờ GD duyệt</option>
              <option value="06">Hủy</option>
              <option value="07">Đã gửi</option>
              <option value="11">Đã gửi chưa vào giao diện</option>
              <option value="12">Đã gửi chờ chạy giao diện</option>
              <option value="13">Đã gửi giao diện thành công</option>
              <option value="14">Đã gửi giao diện thất bại</option>
            </select>
            <br/>
          </td>
        </tr>
        <tr>
          <td align="left">Xuất ra File:     
          </td>
          <td  style="padding-right:80px;">
            <select name="xuatra" id="xuatra" >
              <option value="CHON">Chọn</option>
              <option value="PDF">Xuất ra PDF</option>
              <option value="XLS">Xuất ra EXCEL</option>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right">      
          </td>
          <td align="right">&nbsp;
          </td>
        </tr>
        <tr>
          <td align="right">           
          </td>
          <td  style="padding-right:80px;">            
            <html:submit value="Báo cáo"/>  
          </td>
        </tr>
        <tr>
          <td align="right">      
          </td>
          <td align="right">&nbsp;
          </td>
        </tr>
      </table>
    </html:form>
    </center>
<%@ include file="/includes/ttsp_bottom.inc"%>