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
<%
  String nguoiTao = session.getAttribute("id_nsd") == null ? "" : session.getAttribute("id_nsd").toString();
%>
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
      var loaiTien = $(this).closest("tr").find(".loaiTien").text();
      var nguoi_tao = $(this).closest("tr").find(".nguoi_tao").val();
      var nguoi_tao_session = '<%=nguoiTao%>';
      if(nguoi_tao != nguoi_tao_session){
        jQuery.ajax({
        type : "POST", url : "duyetDeNghiQuyetToan.do",
        data : {"soLenh" : soLenh, "loaiTien" : loaiTien},
        success : function(data, textstatus){
             var strValue = new Object();
                strValue = JSON.parse(data[0]);
                if(strValue != "" && strValue != "fail"){
                  alert("Duyệt đề nghị quyết toán thành công !");
                  window.location.replace("listDeNghiQuyetToan.do");
                }else{
                  $('#message').text("Hệ thống lỗi, Vui lòng thử lại");
                }
            }
       });
      }else{
        alert("Bạn không được phép duyệt lệnh của chính mình.");
      }
       
    });
  });  
  
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
    
     <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">
      <tr>
       <td>
       <div style="height: 30px; padding-top : 10px;" align="right">
        <button id ="thoat">Thoát</button>
      </div>
       </td>       
       </tr>
       <tr>
       <td>
           <fieldset style="width:auto;">
              <legend><a style="text-color : blue;" >Danh sách đề nghị quyết toán</a></legend>
              <span id="message" style="color : red;"></span>
              <table cellpadding="2" cellspacing="0" border="1px" width="100%" id="listQT" >
              <thead>
              <tr style="text-align:center; height : 20px;" >
                <th width="3%" >STT</th>
                <th width="11%" >Số lệnh</th>
                <th width="17%" >Ngân hàng</th>
                <th width="10%" >Quyết toán thu</th>
                <th width="10%" >Quyết toán chi</th>
                <th width="9%" >Loại tiền</th>
                <th width="10%" >Loại quyết toán</th>
                <th width="10%" >Nội dung</th>
                <th width="10%" >Trạng thái</th>
                <th width="5%" >Duyệt</th>
                <th width="5%" >Hủy</th>
              </tr>
              </thead>
              <tbody>
              <logic:empty name="listData" >
                  <tr><td colspan="11" align="center">&nbsp; Không có dữ liệu</td></tr>
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
                    <td align="center" class="soLenh" ><%= items.getSolenh() %>
                      <input type="hidden" name="nguoi_tao" class="nguoi_tao" value="<%=items.getNguoi_tao()%>" />
                    </td>
                    <td class="tenNH" ><%= items.getTennh()%></td>
                    <td align="center" class="QTthu" >
                        <% if(items.getLoaitien().equals("VND")){%>
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber type="currency" currencySymbol="">
                        <%= items.getQuyettoanthu() %>
                        </fmt:formatNumber>
                        <!-- dinh dang tien te -->
                       <% }if(!items.getLoaitien().equals("VND")){%>
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber type="currency" currencySymbol="">
                        <%= items.getQuyettoanthu() %>
                        </fmt:formatNumber><%}%>
                    </td>
                    <td align="center" class="QTchi numbers" >
                        <% if(items.getLoaitien().equals("VND")){%>
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber type="currency" currencySymbol="">
                        <%= items.getQuyettoanchi() %>
                        </fmt:formatNumber>
                        <!-- dinh dang tien te -->
                     <% }if(!items.getLoaitien().equals("VND")){%>
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber type="currency" currencySymbol="">
                        <%= items.getQuyettoanchi() %>
                        </fmt:formatNumber><%}%>
                     </td>
                    <td align="center" class="loaiTien numbers" ><%= items.getLoaitien() %></td>
                    <td align="center" class="loaiQT" >
                         <% if(items.getLoaiquyettoan().equals("01")){ %>Điện tử
                         <% }if(items.getLoaiquyettoan().equals("02")){ %>Lập mới
                         <% }if(items.getLoaiquyettoan().equals("03")){ %>Quyết toán bù
                         <% }if(items.getLoaiquyettoan().equals("04")){ %>Bù số chi
                         <% }if(items.getLoaiquyettoan().equals("05")){ %>Thấu chi
                         <% }if(items.getLoaiquyettoan().equals("06")){ %>Thu ngày lỗi
                         <% }if(items.getLoaiquyettoan().equals("07")){ %>Loại khác <%}%>
                    </td>
                    <td align="center" class="noidung" ><%= items.getNoidung() %></td>
                    <td align="center" class="trangthai" >
                    <% if(items.getTrang_thai().equals("01")){%>Chờ duyệt<%}%>
                    <% if(items.getTrang_thai().equals("02")){%>Đã duyệt<%}%>
                    <% if(items.getTrang_thai().equals("03")){%>Hủy<%}%>
                    <% if(items.getTrang_thai().equals("04")){%>Gửi NH thành công<%}%>
                    <% if(items.getTrang_thai().equals("05")){%>Gửi NH không thành công<%}%>
                    <% if(items.getTrang_thai().equals("06")){%>Hết hiệu lực<%}%>
                    </td>
                    <td align="center" ><% if(items.getTrang_thai().equals("01")){%><button class="duyetqt" >Duyệt</button><%}%></td>
                    <td align="center" ><% if(items.getTrang_thai().equals("01")){%><button class="huyduyet" >Hủy</button><%}%></td>
                </tr>
               <% }%>
              </logic:notEmpty>
              </tbody>
              </table>
                <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
              </fieldset> 
       
       </td>
       </tr>
       </table>
    
    
     
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>