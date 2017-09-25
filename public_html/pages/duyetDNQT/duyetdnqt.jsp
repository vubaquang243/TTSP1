<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.seatech.ttsp.duyetdnqt.DuyetDNQTVO"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<%@ include file="/includes/ttsp_header.inc"%>

<link rel="stylesheet"  type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">  
  $(document).ready(function(){
    $('#thoat').click(function(){
       window.location.replace("mainAction.do");
    });
    
    //Huy de nghi duyet quyet toan
    $('.huyduyet').click(function(){
      var soLenh = $(this).closest("tr").find(".soLenh").text();
       jQuery.ajax({
        type : "POST", url : "huyDeNghiQuyetToan.do",
        data : {"soLenh" : soLenh },
        success : function(data, textstatus){
             var strValue = new Object();
                strValue = JSON.parse(data[0]);
                if(strValue != "" && strValue != "fail"){
                alert("Hủy đề nghị quyết toán thành công");
                window.location.replace("listDeNghiQuyetToan.do");
                }else{
                $('#message').text("Hệ thống lỗi, Vui lòng thử lại");
                }
            }
       });
    });
    
    //duyet de nghi quyet toan
    $('.duyetqt').click(function(){
      var soLenh = $(this).closest("tr").find(".soLenh").text();
       jQuery.ajax({
        type : "POST", url : "duyetDeNghiQuyetToan.do",
        data : {"soLenh" : soLenh },
        success : function(data, textstatus, error){
             var strValue = new Object();
                strValue = JSON.parse(data[0]);
                if(strValue != "" && strValue != "fail"){
                  alert("Gửi đề nghị duyệt thành công");
                  window.location.replace("listDeNghiQuyetToan.do");
                }else{
                  $('#message').text("Hệ thống lỗi, Vui lòng thử lại");
                }
            }
       });
    });
  });  
</script>
<div id="body">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Duyệt đề nghị quyết toán lập mới </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
    </table>
    <div style="height: 30px; padding-top : 10px;" align="right">
        <button id ="thoat">Thoát</button>
    </div>
    <fieldset style="width:auto;">
    <legend><a style="text-color : blue;" >Danh sách đề nghị quyết toán</a></legend>
    <span id="message" style="color : red;"></span>
    <table cellpadding="0" cellspacing="0" border="1px" width="100%" id="listQT" >
    <thead>
    <tr style="text-align:center; height : 20px;" >
      <th width="3%" >STT</th>
      <th width="12%" >Số lệnh</th>
      <th width="17%" >Ngân hàng</th>
      <th width="10%" >Quyết toán thu</th>
      <th width="10%" >Quyết toán chi</th>
      <th width="9%" >Loại tiền</th>
      <th width="10%" >Loại quyết toán</th>
      <th width="13%" >Nội dung</th>
      <th width="8%" >Duyệt</th>
      <th width="8%" >Hủy</th>
    </tr>
    </thead>
    <tbody>
    <logic:empty name="listData" >
        <tr><td colspan="10" align="center">&nbsp; Không có dữ liệu</td></tr>
    </logic:empty>
    <logic:notEmpty name="listData">
     <% 
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      System.out.print(rowBegin);
          int stt = 0;
          List list = new ArrayList();
          DuyetDNQTVO items = null;
          list = (List) request.getAttribute("listData");
          int listSize = list.size();
          for(int i = 0; i < listSize; i++){
            items = (DuyetDNQTVO) list.get(i);
      %>
      <tr>
          <td align="center" ><%= i+1 %></td>
          <td align="center" class="soLenh" ><%= items.getSolenh() %></td>
          <td class="tenNH" ><%= items.getTennh()%></td>
          <td align="center" class="QTthu" ><%= items.getQuyettoanthu() %></td>
          <td align="center" class="QTchi numbers" ><%= items.getQuyettoanchi() %></td>
          <td align="center" class="loaiTien numbers" ><%= items.getLoaitien() %></td>
          <td align="center" class="loaiQT" >
               <% if(items.getLoaiquyettoan().equals("01")){ %>Điện tử
               <% }if(items.getLoaiquyettoan().equals("02")){ %>Lập mới
               <% }if(items.getLoaiquyettoan().equals("03")){ %>Quyết toán bù
               <% }if(items.getLoaiquyettoan().equals("04")){ %>Bù chi ngày lỗi
               <% }if(items.getLoaiquyettoan().equals("05")){ %>Thấu chi
               <% }if(items.getLoaiquyettoan().equals("06")){ %>Thu chi ngày lỗi
               <% }if(items.getLoaiquyettoan().equals("07")){ %>Loại khác <%}%>
          </td>
          <td align="center" class="noidung" ><%= items.getNoidung() %></td>
          <td align="center" ><button class="duyetqt" >Duyệt</button></td>
          <td align="center" ><button class="huyduyet" >Hủy</button></td>
      </tr>
     <% }%>
    </logic:notEmpty>
    </tbody>
    </table>
      <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
    </fieldset>   
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>