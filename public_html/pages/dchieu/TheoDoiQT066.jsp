<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>

<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String TTTT = request.getAttribute("TTTT")==null?"":request.getAttribute("TTTT").toString();
  

%>

<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************  
  jQuery(document).init(function () {
      getTenKhoBacDC('','');     
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });    
  });
  
  //chuyen doi dinh dang ngoai te
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
    function resetInput(){
     jQuery('#so_tien').val("");
    }
    
    function changeCurrency(str){
    var cateCurrency = jQuery('#loai_tien').val();
    if(cateCurrency != ""){
    if(cateCurrency == "VND"){
      str.value = changeVNDCurrency(str.value);
    }else{
      str.value = changeForeignCurrency(str.value);
    }}
  }
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="lstTheoDoiQT066Action.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Tra cứu đề nghị quyết toán</span>
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
         <fieldset>
          <legend><font color="blue">&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m </font></legend>
          <div>
            <table  class="data-grid" id="data-grid" 
                                                style="width:100%" border="0"
                                               cellspacing="0" cellpadding="1" >
                <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                  </td>
                  <td width="20%">
                    <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <%if(request.getAttribute("TTTT") != null){
                           %>
                                <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                  
                            <%}%>
                        <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                    </html:select>
                    </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                  </td>
                  <td width="30%">
                    <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="nhkb_huyenval();"
                                  onkeydown="if(event.keyCode==13) event.keyCode=9;">                               
                                <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                                
                  </html:select>
                  </td>
                    
                  <td  align="center" rowspan="3" width="15%">
                    <%if(request.getAttribute("TTTT") != null){
                    %>
                    <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                            <span class="sortKey">D</span>anh m&#7909;c KB
                    </button> <p/>
                    <%}%>
                    <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                            <span class="sortKey">T</span>&igrave;m kiếm
                    </button>               
  
                 </td>
                </tr>
                <tr>
                  <td align="right" bordercolor="#e1e1e1">
                      <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                    </td>
                    <td >
                      <html:select property="ma_dv" styleId="ngan_hang" onchange="nhangval()"
                               style="width: 80%;font-size:12px"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value=""   >-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                      <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                  </html:select>
                  </td> 
                  <td align="right">
                    TT điện đề nghị
                  </td>
                  <td>
                    <html:select property="tthai_dxn" styleId="tthai_dxn" onchange="tthai_dxnVal()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n lo&#7841;i &#273;&#7889;i chi&#7871;u---</html:option>
                      <html:option value="01">Chờ KTT duyệt</html:option>
                      <html:option value="02">Đã duyệt</html:option>
                      <html:option value="03">Hủy</html:option> 
                      <html:option value="06">Hết hiệu lực</html:option>  
                      <html:option value="04">Gửi NH thành công</html:option>
                      <html:option value="05">Gửi NH không thành công</html:option>
                  </html:select>                 
                  </td>
                </tr>              
                <tr>
                  <td align="right">
                    TT quyết toán
                  </td>
                  <td>
                    <html:select property="tthai_qt" styleId="tthai_qt" onchange="tthai_qtVal()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n lo&#7841;i &#273;&#7889;i chi&#7871;u---</html:option>
                      <html:option value="00">Chưa QT</html:option>
                      <html:option value="01">Chưa nhận đủ QT</html:option>
                      <html:option value="02">Đã nhận đủ QT</html:option>
                      <%--<html:option value="4">Không QT</html:option>--%>
                  </html:select>                  
                  </td>
                  <td align="right">
                    Loại đề nghị QT
                  </td>
                  <td>
                    <html:select property="loai_qt" styleId="loai_qt" onchange="loai_qtVal()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n lo&#7841;i &#273;&#7889;i chi&#7871;u---</html:option>
                      <html:option value="01">QT tự động</html:option>
                      <html:option value="02">QT lập mới</html:option>
                      <html:option value="07">QT loại khác</html:option>
                  </html:select>                 
                  </td>
                </tr> 
                <tr>
                  <td   align="right">
                      T&#7915; <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                  </td>
                  <td>
                  <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                          onkeypress="return numbersonly(this,event,true) "
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
                         tabindex="107" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="tu_ngay_btn"
                       style="vertical-align:middle;width:20"/>   
                      <script type="text/javascript">
                        Calendar.setup( {
                            inputField : "tu_ngay", // id of the input field
                            ifFormat : "%d/%m/%Y", // the date format
                            button : "tu_ngay_btn"// id of the button
                        });
                      </script> &nbsp;&nbsp;&nbsp;
                    </td>
                    <td align="right">
                       &#272;&#7871;n <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                    </td>
                    <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                          onkeypress="return numbersonly(this,event,true) "
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:20%"
                         tabindex="107" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="den_ngay_btn"
                       style="vertical-align:middle;width:13"/>   
                      <script type="text/javascript">
                        Calendar.setup( {
                            inputField : "den_ngay", // id of the input field
                            ifFormat : "%d/%m/%Y", // the date format
                            button : "den_ngay_btn"// id of the button
                        });
                      </script>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                  <td align="right">Loại tiền</td>
                  <td>
                    <html:select property="loai_tien" styleId="loai_tien" onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="resetInput();">  
                        <html:option value="">---Chọn---</html:option>  
                        <html:option value="VND">VND</html:option>
                        <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
                    </html:select>
                  </td>
                  <td align="right">Số tiền</td>
                  <td><html:text property="so_tien" styleId="so_tien" onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="changeCurrency(this);" />(QT thu/chi)</td>
                </tr>
             </table>
           </div>
          </fieldset>
       </td>
      </tr>
       <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                  style="BORDER-COLLAPSE: collapse;table-layout:fixed">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
                    STT 
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="6%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="6%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.nhang"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="11%">
                  <div align="center">
                    Số điện ĐNQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="7%">
                  <div align="center">
                    Ngày QT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="11%">
                  <div align="center">
                    QT thu
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="11%">
                  <div align="center">
                    QT Chi
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Loại tiền
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="6%">
                  <div align="center">
                    Loại ĐNQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="9%">
                  <div align="center" >
                    Trạng thái ĐNQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="9%">
                  <div align="center" >
                    Trạng thái QT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="13%">
                  <div align="center" >
                    Mô tả
                </th>
                <th class="promptText" bgcolor="#f0f0f0"  width="3%"  >
                  <div align="center" >
                     
                </th>
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="colLst066">
                <logic:present name="colLst066" >          
                  <logic:iterate id="items" name="colLst066" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center"> 
                        <%=stt+1+ rowBegin%>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="nhkb_chuyen"/>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="nhkb_nhan"/>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="id"/>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="ngay_qtoan"/>
                      </td>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:setLocale value="vi_VI"/>
                        <td align="right">
                          <b>
                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_thu"/>
                            </fmt:formatNumber>
                          </b>
                        </td>
                        <td align="right">
                          <b>
                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber>
                          </b>
                        </td>
                      </logic:equal>
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:setLocale value="en_US"/>
                        <td align="right">
                          <b>
                            <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_thu"/>
                            </fmt:formatNumber>
                          </b>
                        </td>
                        <td align="right">
                          <b>
                            <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber>
                          </b>
                        </td>
                      </logic:notEqual>
                      <td align="right">
                         <bean:write name="items" property="loai_tien"/>
                      </td>
                      <td align="center">
                        <logic:equal value="01" name="items" property="loai_qtoan">
                          Tự động
                        </logic:equal>
                        <logic:equal value="02" name="items" property="loai_qtoan">
                          Lập mới
                        </logic:equal>
                      </td>
                      <td align="center">
                        <logic:equal value="01" name="items" property="trang_thai">
                          Chờ duyệt
                        </logic:equal>
                        <logic:equal value="02" name="items" property="trang_thai">
                          Đã duyệt
                        </logic:equal>
                        <logic:equal value="03" name="items" property="trang_thai">
                          Hủy
                        </logic:equal>
                        <logic:equal value="04" name="items" property="trang_thai">
                          Gửi NH thành công
                        </logic:equal>
                        <logic:equal value="05" name="items" property="trang_thai">
                          Gửi NH không thành công
                        </logic:equal>
                        <logic:equal value="06" name="items" property="trang_thai">
                          Hết hiệu lực
                        </logic:equal>
                      </td>
                      <td>
                        <logic:equal value="00" name="items" property="trang_thai_qtoan">
                          Chưa quyết toán
                        </logic:equal>
                        <logic:equal value="01" name="items" property="trang_thai_qtoan">
                            Quyết toán thiếu
                          </logic:equal>
                          <logic:equal value="02" name="items" property="trang_thai_qtoan">
                            Quyết toán đủ
                          </logic:equal>
                      </td>
                      <td align="left" title="<bean:write name="items" property="mo_ta"/>">
                        <div style="text-overflow:ellipsis;width:150px;white-space:nowrap;  overflow:hidden; font-size:12px">
                          <bean:write name="items" property="mo_ta"/>
                        </div>
                      </td>
                      <td align="center">
                            <span id="refresh" onclick="chk_print('print','<bean:write name="items" property="id"/>','<bean:write name="items" property="kq_dxn_thop"/>','<bean:write name="items" property="loai_tien"/>')"  title="In" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/icon_print_small.png" /></span>
                       </td>
                    </tr>
                  </logic:iterate>
                  <tr>
                      <td colspan="13">                 
                     <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                      </td>
                  </tr>
                </logic:present>   
                </logic:notEmpty>
              </tbody>
            </table>
          </div>
        </fieldset>
      </td>
    </tr>
 </table>
    <html:hidden property="pageNumber" value="1"/>
    <html:hidden property="nhkb_huyen" styleId="a"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

  var f = document.forms[0];
function goPage(page) {
 
      f.pageNumber.value = page;
      f.action = 'lstTheoDoiQT066Action.do?pageNumber='+page;
      f.submit();
  }
 jQuery('#id_066').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#id_066').val(this.value.replace(matches, ''),true);
         });

  function check(type) {  
     if (type == 'find') {       
        var inKB = jQuery('#nhkb_huyen option:selected').index();
        var inNH = jQuery('#ngan_hang option:selected').index() ;
//        var inxtthai = jQuery('#tthai_dxn_thop option:selected').index();
//        var lan_dc = jQuery('#lan_dc option:selected').val();
        var vTuNgay=  document.getElementById("tu_ngay"); 
        var vDenNgay=  document.getElementById("den_ngay"); 
        var vTuNgayTemp ;
        var vDenNgayTemp ;
        var vTuNgayDate;
        var vDenNgayDate;
        if(vTuNgay.value ==null || vTuNgay.value == ''){
          alert('Từ ngày không được để trống.');
          vTuNgay.focus();  
          return;
        }
        if(vDenNgay.value ==null || vDenNgay.value == ''){
          vTuNgayTemp =vTuNgay.value.split('/');
          vTuNgayDate = new Date(vTuNgayTemp[2],vTuNgayTemp[1]-1,vTuNgayTemp[0]);
          vDenNgayDate = new Date();            
        }else{
          vTuNgayTemp =vTuNgay.value.split('/');
          vDenNgayTemp =vDenNgay.value.split('/');
          vTuNgayDate = new Date(vTuNgayTemp[2],vTuNgayTemp[1]-1,vTuNgayTemp[0]);
          vDenNgayDate = new Date(vDenNgayTemp[2],vDenNgayTemp[1]-1,vDenNgayTemp[0]);          
          if(Math.round(((new Date()) - vDenNgayDate)/(24*60*60*1000))<0){
              alert('Đến ngày không được lớn hơn ngày hiện tại.');
              vDenNgay.focus();  
              return;          
          }
        }         
         if(Math.round((vDenNgayDate - vTuNgayDate)/(24*60*60*1000)) >31 || Math.round((vDenNgayDate - vTuNgayDate)/(24*60*60*1000)) <0){
              alert('Dữ liệu tra cứu không quá 30 ngày ');
              vTuNgay.focus();  
              return;
          }       
          f.action = 'lstTheoDoiQT066Action.do?inKB='+inKB+'&inNH='+inNH; 
      }
    
    
      if (type == 'close') {
        f.action = 'mainAction.do';  
        
      } 
       f.submit();
    }


    
function chk_print(type,id_066,kq_dxn_Thop,loaitien) {
    if (type == 'print') {       
        f.action = 'PrintXNDCTHop1Action.do?type=066&id_066='+id_066+"&loai_kq066="+kq_dxn_Thop+"&loaitien="+loaitien;    
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
      f.submit();
  }  
  
   function nhkb_huyenval() { 
      var nhkb_huyen;
      nhkb_huyen=  document.getElementById("nhkb_huyen").value;     
      return nhkb_huyen;
  }
  function nhangval() {
      var ngan_hang;
      ngan_hang=document.getElementById("ngan_hang").value;
      return ngan_hang;
  }
  function tthai_dxnVal() {
      var tthai_dxn;
      tthai_dxn=document.getElementById("tthai_dxn").value;
      return tthai_dxn;
  }
  function tthai_qtVal() {
      var tthai_qt;
      tthai_qt=document.getElementById("tthai_qt").value;
      return tthai_qt;
  }
  function loai_qtVal() {
      var loai_qt;
      loai_qt=document.getElementById("loai_qt").value;
      return loai_qt;
  }
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }

function getTenKhoBacDC(id,id_cha) { 
    var TTTT="<%=TTTT%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     if(TTTT!=null && ''!=TTTT){
          if (id==null || ''==id){       
                if(id_cha!=null&&''!=id_cha){               
                      kb_id=id_cha;
                      jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }else if (id!=null && ''!=id){
                if(id_cha!=null&&''!=id_cha){                
                  kb_id=id_cha;
                  jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }
       }else if(TTTT==null || ''==TTTT){
        kb_id=document.forms[0].nhkb_tinh.value;
       }
    var kb_huyen="<%=kb_huyen%>";   
    var strTinh="<%=strTinh%>";
   if (kb_id !=null && ""!=kb_id){
    jQuery.ajax( {
        type : "POST", url : "getDMucKBTHop.do", data :  {
            "kb_id" : kb_id
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    // truong hop 1 - luc load khong co thang nao                  
                    document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                    });
                    if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                        if(document.getElementById('nhkb_huyen').options.length==2){
                          jQuery("#nhkb_huyen option:eq(0)").remove();

                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);

                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);

                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);

                      }
                    }
                }
            }
              if (id!=null && ''!=id){                        
                        jQuery('#nhkb_huyen').val(id);
                    }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}

</script>