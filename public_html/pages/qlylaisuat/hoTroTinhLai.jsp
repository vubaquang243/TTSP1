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
    var tk_last_selected = '<%=request.getAttribute("tkLastSelected")%>';
    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () {
        var kb_id = jQuery("#ma_kb").val();
        if(kb_id != null){
            getNganHang();
        }
        getSoTK(tk_last_selected);
    });
    var map_detail_tk = {};
    function getSoTK(tk_last){
        var ma_nh = jQuery("#ma_nh").val();
        var kb_id = jQuery("#ma_kb").val();
        jQuery("#so_tk").find('option').remove().end();
        jQuery.ajax({
            type:"post",
            url:"getSoTK.do",
            data:{
                "ma_nh":ma_nh,
                "kb_id":kb_id
            },
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    map_detail_tk = {};
                    index_focus_item_detail = 0;
                    for(var i = 0;i<data.length ;i++){
                        if(data[i].so_tk == tk_last){
                            index_focus_item_detail = i;
                            jQuery('#so_tk').append('<option selected="true" value="' + data[i].so_tk + '" >' + data[i].so_tk + '<\/option>');
                        }else{
                            jQuery('#so_tk').append('<option value="' + data[i].so_tk + '" >' + data[i].so_tk + '<\/option>');
                        }
                        loaiTK = data[i].loai_tk == '01' ? 'TK Tiền gửi' : data[i].loai_tk == '02' ? 'TK Thanh toán' : 'TK Chuyên thu'
                        map_detail_tk[i] = {ma_nt:data[i].ma_nt,loai_tk:loaiTK}
                    }
                    view_detail(index_focus_item_detail);
                }
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    function view_detail(selectedIndex){
       document.getElementById('loaiTien').value = map_detail_tk[selectedIndex].ma_nt;
        jQuery("#tk_detail").find('span').remove()
        jQuery("#tk_detail").append('<span>Loại tiền <b>: '+map_detail_tk[selectedIndex].ma_nt + '</b><br/>Loại tài khoản : <b>' +map_detail_tk[selectedIndex].loai_tk+'</b><input type="hidden" name="ma_nt" value="'+map_detail_tk[selectedIndex].ma_nt +'"></span>')
         
         
         
    }
    function getNganHang(){
        var kb_id = jQuery("#ma_kb").val();
        jQuery("#ma_nh").find('option').remove().end();
        jQuery.ajax({
            type:"post",
            url:"getNganHang.do",
            data:{
                "kb_id":kb_id
            },
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    for(var i = 0;i<data.length ;i++){
                        jQuery('#ma_nh').append('<option value="' + data[i].ma_nh + '" >' + data[i].ten + '<\/option>');
                    }
                    getSoTK();
                }
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    
    function submitForm(type){
        var f = document.forms[0];
         f.target='';
        if(type == 'tinhLai'){        
        f.action = 'hoTroTinhLai.do';
          if(f.fromDate.value==''){
              alert("Phải chọn từ ngày");
              f.fromDate.focus();
              return
          }
          if(f.fromDate.value==''){
                alert("Phải chọn đến ngày");
                f.fromDate.focus();
                return
          }          
        }
        if(type == 'printtinhLai'){
        
          f.action = 'printHoTroTinhLai.do';
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);  
          f.target='_form';
        }
        f.submit();
    }
</script>

<title>Hỗ Trợ Tính Lãi</title>
<div class="app_error">
  <html:errors/>
</div>
<html:form action="/hoTroTinhLai.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Hỗ trợ tính lãi</span>
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
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <c:if test="${not empty requestScope.listNganHang}">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
              <td width="20%">
                <html:select property="ma_nh" styleId="ma_nh" style="width:80%" onchange="getSoTK()">
                    <html:options collection="listNganHang" property="ma_nh" labelProperty="ten"/>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Tài khoản</td>
              <td width="20%">
                <html:select property="so_tk" styleId="so_tk" style="width:80%" onchange="view_detail(this.selectedIndex)">
                </html:select>
              </td>
              <td width="30%" id="tk_detail">
                
              </td>
            </tr>
            <tr>
              <td>
                <input type="hidden" name="execute" value="true"/>
              </td>
            </tr>
            </c:if>
            <c:if test="${empty requestScope.listNganHang}">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Kho bạc huyện</td>
              <td>
                <html:select property="ma_kb" styleId="ma_kb" style="width:80%" onchange="getNganHang()">
                  <html:options collection="listKBTinh" property="id" labelProperty="kb_huyen"/>
                </html:select>
              </td>
              <td width="10%" align="right" bordercolor="#e1e1e1">Ngân hàng</td>
              <td>
                <html:select property="ma_nh" styleId="ma_nh" style="width:80%" onchange="getSoTK()">
                </html:select>
              </td>
            </tr>
            <td align="right" bordercolor="#e1e1e1">Tài khoản</td>
            <td>
              <html:select property="so_tk" styleId="so_tk" style="width:80%">
              </html:select>
            </td>
            <tr>
              <td>
                <input type="hidden" name="check" value="true"/>
              </td>
            </tr>
            </c:if>
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
          </table>
          <br/>
        </td>
      </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tinhLai">
            <button type="button" onclick="submitForm('tinhLai')" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>ính lãi
            </button>
          </span>
          
          
          <c:if test="${requestScope.listKetQua != null}">
              <button type="button" onclick="submitForm('printtinhLai')" class="ButtonCommon"
                    accesskey="t" style="width:50px">
              <span class="sortKey">I</span>n
              </button>
          </c:if>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Kết quả tính lãi</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:14%">
                    <div align="center">Số tài khoản</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Ngày dư cuối</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:17%">
                    <div align="center">Số dư cuối ngày</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Loại tiền</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:14%">
                    <div align="center">Lãi suất hiện tại</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Tỷ giá</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:9%">
                    <div align="center">Loại</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:16%">
                    <div align="center">Lãi</div>
                  </th>
                </tr>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                <c:if test="${requestScope.listKetQua != null}">
                  <c:forEach items="${requestScope.listKetQua}" var="item" varStatus="status">
                    <c:choose>
                      <c:when test="${status.index % 2 != 0}">
                        <tr class="trDanhSachLe">
                      </c:when>
                      <c:otherwise>
                        <tr>
                      </c:otherwise>
                    </c:choose>
                      <td align="center">
                          <bean:write name="item" property="so_tk"/>
                      </td>
                      <td align="center">
                          <bean:write name="item" property="ngay"/>
                      </td>
                      <td align="right">
                          <c:choose>
                            <c:when test="${item.loai_tien == 'VND'}">
                              <fmt:setLocale value="vi_VI"/>
                              <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                <bean:write name="item" property="so_du_cuoi" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:setLocale value="en_US"/>
                              <fmt:formatNumber type="currency"  currencySymbol="">
                                <bean:write name="item" property="so_du_cuoi" />
                              </fmt:formatNumber>
                            </c:otherwise>
                          </c:choose>
                      </td>
                      <td align="center">
                          <bean:write name="item" property="loai_tien"/>
                      </td>
                      <td align="center">
                          <bean:write name="item" property="lai_suat"/>%
                      </td>
                      <td align="center">
                          <bean:write name="item" property="ty_gia"/>
                      </td>
                      <td align="center">
                          <logic:equal value="D" name="item" property="loai_lai_suat">Ngày</logic:equal>
                          <logic:equal value="M" name="item" property="loai_lai_suat">Tháng</logic:equal>
                          <logic:equal value="Y" name="item" property="loai_lai_suat">Năm</logic:equal>
                      </td> 
                      <td align="right">
                          <c:choose>
                            <c:when test="${item.loai_tien == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                <bean:write name="item" property="lai" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency"  currencySymbol="">
                                <bean:write name="item" property="lai" />
                              </fmt:formatNumber>
                            </c:otherwise>
                          </c:choose>
                      </td>
                    </tr>
                  </c:forEach>
                  <tr>
                    <td colspan="6" align="right"></td>
                    <td align="center"><b>Tổng lãi</b></td>
                    <td align="right"><b>
                      <c:choose>
                          <c:when test="${loai_tien_total == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                <bean:write name="total" />
                              </fmt:formatNumber>
                          </c:when>
                          <c:otherwise>
                              <fmt:formatNumber type="currency"  currencySymbol="">
                                <bean:write name="total" />
                              </fmt:formatNumber>
                          </c:otherwise>
                      </c:choose>
                    </b></td>
                  </tr>
                </c:if>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <html:hidden property="loaiTien" styleId = "loaiTien"/>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>