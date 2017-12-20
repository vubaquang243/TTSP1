<logic:notEmpty name="lstTimKiem">
<fieldset>
<legend><font color="Blue" >Kết quả tìm kiếm</font></legend>
<table cellpadding="0" cellspacing="0"  border="1" id="table_ket_qua" style="BORDER-COLLAPSE: collapse"
bordercolor="#999999" width="100%" >
  <thead>
  <!--20171117 thuongdt sua noi dung ket qua tra cuu begin -->
  <tr>  
  <th width="2%" align="center">STT</th>
  <th width="15%" align="center">Kho bạc tỉnh</th>
  <th width="15%" align="center">Kho bạc huyện</th>
  <th width="14%" align="center">Tên ngân hàng</th>
  <th width="7%" align="center">Ngày DC<br/>khớp đúng</th>  
  <th width="10%" align="center">Số dư <br/>đầu ngày</th>
  <th width="10%" align="center">Số dư COT</th>
  <th width="7%" align="center">Số dư <br/>cuối ngày</th>
   <th width="8%" align="center">Hạn mức <br/>(tỷ)</th>
  <th width="4%" align="center">Loại tiền</th>
  <th width="8%" align="center">Loại TK</th>  
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
  <tr >
    <td align="center"><%= i+1 %></td>
    <td align="left" style="padding-left:2px"><%= items.getTen_kb_tinh() %></td>
    <td id="ma_nh" align="left"  style="padding-left:2px"><%= items.getTen_kb() %></td>
    <td align="left"  style="padding-left:2px"><%= items.getTen_nh() %></td>
    <td id="ngay_giao_dich" align="center"><%= items.getNgay_dc_gan()==null?"":items.getNgay_dc_gan() %></td>
    <td id="soDu" align="right"  style="padding-right:2px">    
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du() %>
      </fmt:formatNumber>   
    </td>
     <td id="soDuCOT" align="right"  style="padding-right:2px">    
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du_cot() %>
      </fmt:formatNumber>   
    </td>
   <td id="soDuCuoiNgay" align="right"  style="padding-right:2px">    
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getSo_du_cuoi_ngay()==null?"0":items.getSo_du_cuoi_ngay() %>
      </fmt:formatNumber>   
    </td>
    <!--20171117 thuongdt bo sung them han muc du begin-->
     <td id="soDu" align="right"  style="padding-right:2px">    
      <fmt:setLocale value="vi_VI"/>
      <fmt:formatNumber type="currency" currencySymbol="">
        <%= items.getHan_muc_no()==null?"0":items.getHan_muc_no() %>
      </fmt:formatNumber>    
    </td>
        <!--20171117 thuongdt bo sung them han muc du end-->
        
    <td id="loaiTien" align="center"><%= items.getLoai_tien() %></td>
    <td id="loai_tk" align="left" style="padding-left:2px">
    <% if(items.getLoai_tk()==null) items.setLoai_tk(""); %>
    <% if(items.getLoai_tk().equals("01")){ %>Thanh toán TH<%}%>
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
  <!--20171117 thuongdt sua noi dung ket qua tra cuu end -->
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
</table>
</fieldset>
</logic:notEmpty>