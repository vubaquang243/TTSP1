<logic:notEmpty name="lstTimKiem">
<fieldset>
<legend><font color="Blue" >Kết quả tìm kiếm</font></legend>
<table cellpadding="0" cellspacing="0" border="1" id="table_ket_qua">
  <thead>
  <tr>
  <th width="4%" align="center">STT</th>
  <th width="16%" align="center">Kho bạc tỉnh</th>
  <th width="16%" align="center">Kho bạc huyện</th>
  <th width="16%" align="center">Tên ngân hàng</th>
  <th width="11%" align="center">Ngày GD</th>
  <th width="15%" align="center">Số dư</th>
  <th width="8%" align="center">Loại tiền</th>
  <th width="12%" align="center">Loại TK</th>
  </tr>
  </thead>
  <%  
     com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
     int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
     List lstTimKiem = (List) request.getAttribute("lstTimKiem"); 
     TraCuuSoDuVO items = null;
     for(int i = 0; i < lstTimKiem.size(); i++){
     items = (TraCuuSoDuVO) lstTimKiem.get(i);
  %>
  <tr>
    <td align="center"><%= i+1 %></td>
    <td align="center"><%= items.getTen_kb_tinh() %></td>
    <td id="ma_nh" align="center"><%= items.getTen_kb() %></td>
    <td align="center"><%= items.getTen_nh() %></td>
    <td id="ngay_giao_dich" align="center"><%= items.getNgay_gd() %></td>
    <td id="soDu" align="center">
    <%if(items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du() %>
      </fmt:formatNumber>
    <%}%>
    <%if(!items.getLoai_tien().equals("VND")){%>
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du() %>
      </fmt:formatNumber>
    <%}%>
    </td>
    <td id="loaiTien" align="center"><%= items.getLoai_tien() %></td>
    <td id="loai_tk" align="center">
    <% if(items.getLoai_tk().equals("01")){ %>Thanh toán tổng hợp<%}%>
    <% if(items.getLoai_tk().equals("02")){ %>Thanh  toán<%}%>
    <% if(items.getLoai_tk().equals("03")){ %>Chuyên thu<%}%>
    </td>
  </tr>
  <%}%>
  <tr>
    <td colspan="13">
      <div style="float:right;padding-right:40">
        <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
      </div>
    </td>
  </tr>
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
</table>
</fieldset>
</logic:notEmpty>