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
    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () {
        var kb_id = jQuery("#ma_kb").val();
        if(kb_id != null){
            getNganHang();
        }
    });
    function viewDetail(){
        var f = document.forms[0];
        if(jQuery("#tu_ngay").val() == ''){
          alert('Cần nhập từ ngày');
          jQuery("#tu_ngay").focus();
          return;
        }
        if(jQuery("#den_ngay").val() == ''){
          alert('Cần nhập đến ngày');
          jQuery("#den_ngay").focus();
          return;
        }
        f.action = 'chiTietPhi.do';
        f.submit();
    }
    function blockNumber(event){
        if(event.keyCode >= 65 && event.keyCode <= 90 || event.keyCode >= 97 && event.keyCode <= 122){
            return true;
        }
        return false;
    }
    function getNganHang(){
        var ma_nh_selected_='<%=request.getAttribute("ma_nh_selected_")==null?"":request.getAttribute("ma_nh_selected_")%>';
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
                        buildPhiTable(data[i].ma_nh);
                    }
                }
                jQuery('#ma_nh').val(ma_nh_selected_);
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    function buildPhiTable(ma_nh){
        jQuery.ajax({
            type:"post",
            url:"buildPhiTable.do",
            data:{
                "ma_nh":ma_nh
            },
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    for(var i = 0;i<data.length ;i++){
                        jQuery("#phiTableBody:last").append(
                        '<tr>' +
                          '<td align="left">'+data[i].he_thong_nh+'</td>' +
                          '<td align="left">'+(data[i].loai_gd == "INT" ? "Trong hệ thống" : "Ngoài hệ thống") +'</td>' +
                          '<td align="left">'+data[i].loai_tien +'</td>' +
                          '<td align="center">'+(data[i].gio_tu != null ? data[i].gio_tu : "")+'</td>' +
                          '<td align="center">'+(data[i].gio_den != null ? data[i].gio_den : "")+'</td>' +
                          '<td align="right">'+(data[i].gia_tri_tu != null ? data[i].gia_tri_tu : "")+'</td>' +
                          '<td align="right">'+(data[i].gia_tri_den != null ? data[i].gia_tri_den : "")+'</td>' +
                          '<td align="left">'+(data[i].loai_phi == "MON" ? "Món" : "Giá trị") +'</td>' +
                          '<td align="right">'+data[i].muc_phi+'</td>' +
                          '<td align="right">'+(data[i].san != null ? data[i].san : "")+'</td>' +
                          '<td align="right">'+(data[i].tran != null ? data[i].tran : "")+'</td>' +
                          '<td align="right">'+data[i].vat+'%</td>' +
                          '<td align="center">'+data[i].tu_ngay+'</td>' +
                          '<td align="center">'+(data[i].den_ngay != null ? data[i].den_ngay : "")+'</td>' +
                        '</tr>');
                    }
                }
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    function tinhPhi(){
        var f = document.forms[0];
        f.action = 'hoTroTinhPhi.do';
        f.target='';
        f.submit();
    }
    
    //Function in bang ke tinh phi
        function intinhPhi(){
        var f = document.forms[0];
        f.action = 'printHoTroTinhPhi.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
        f.submit();
    }
</script>
<title>Hỗ Trợ Tính Phí</title>
<html:errors/>
<html:form action="/hoTroTinhPhi.do" method="post">
    <c:choose>
        <c:when test="${requestScope.locale == 'VND'}">
          <fmt:setLocale value="vi_VI"/>
        </c:when>
        <c:otherwise>
          <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Hỗ trợ tính phí</span>
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
            <table width="80%" cellspacing="0" cellpadding="1"
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
                  <html:select property="ma_nh" styleId="ma_nh" style="width:80%">
                  
                  </html:select>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
                <td>
                  <html:select property="loai_tien" styleId="loai_tien">
                    <html:optionsCollection value="ma" label="ma" name="tienTe"/>
                  </html:select>
                </td>
                <td>
                  <input type="hidden" name="execute" value="true"/>
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Từ ngày</td>
                <td width="30%">
                  <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_tu_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "tu_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_tu_ngay"// id of the button
                    });
                  </script>
                </td>
                <td align="right" bordercolor="#e1e1e1">Đến ngày</td>
                <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              style="WIDTH: 70px;vertical-align:middle;"/>
                   
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="btn_den_ngay" width="20"
                       style="vertical-align:middle;"/>
                  <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "den_ngay", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "btn_den_ngay"// id of the button
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
          <span id="tinhPhi">
            <button type="button" onclick="tinhPhi()" class="ButtonCommon"
                    accesskey="t" style="width:100px">
              <span class="sortKey">T</span>ính phí
            </button>
            <button type="button" onclick="intinhPhi()" class="ButtonCommon"
                    accesskey="t" style="width:50px">
              <span class="sortKey">I</span>n
            </button>
          </span>
          
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
            <font color="#006699">Thông tin mức phí đang hiện hành</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Hệ thống</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:11%">
                    <div align="center">Loại GD</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:3%">
                    <div align="center">NT</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Từ giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Đến giờ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:8%">
                    <div align="center">Giá tiền từ</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Đến giá tiền</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">Loại</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Mức phí</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Giá trị sàn</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                    <div align="center">Giá trị trần</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">VAT</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Ngày bắt đầu</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Ngày hết hạn</div>
                  </th>
                </tr>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em" id="phiTableBody">
              </tbody>
              <tfoot>
                <tr><td colspan="14" style="color:green;font-size:1em;padding-top:8px">* Chú ý : Thông tin mức phí ở trên là mức phí "CÒN HIỆU LỰC" không hiển thị mức phí "HẾT HIỆU LỰC". Nhưng khi tính phí cho quá khứ thì mức phí quá khứ (dù còn hay hết hiệu lực) sẽ vẫn được sử dụng để tính phí.</td></tr>
              </tfoot>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse;margin-top:10px" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6" background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg" height="24">
            <font color="#006699">Kết quả tính phí</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" bordercolor="#999999" width="100%">
              <thead>
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                    <div align="center">Ngày thanh toán</div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">Số chứng từ</div>
                  </th>
                  <c:if test="${requestScope.locale == 'VND'}">
                      <th class="promptText" bgcolor="#f0f0f0" style="width:30%">
                        <div align="center">Số tiền</div>
                      </th>
                  </c:if>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <th class="promptText" bgcolor="#f0f0f0" style="width:15%">
                      <div align="center">Số tiền</div>
                    </th>
                  </c:if>
                  <th colspan="2" class="promptText" bgcolor="#f0f0f0" style="width:20%">
                    <div align="center">Mức phí</div>
                  </th>
                  <!--<th class="promptText" bgcolor="#f0f0f0" style="width:10%">
                    <div align="center">VAT</div>
                  </th>-->
                  <c:if test="${requestScope.locale != 'VND'}">
                    <th class="promptText" bgcolor="#f0f0f0" style="width:15%">
                      <div align="center">Tiền phí (Nguyên Tệ)</div>
                    </th>
                  </c:if>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                    <div align="center">Tiền phí VND</div>
                  </th>
                </tr>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1.2em">
                <tr>
                  <td bgcolor="#f0f0f0" colspan="3" style="color:black;font-size:1em;font-weight: bold;">I. Trong cùng hệ thống</td>
                  <th align="center">Món</th>
                  <th align="center">Giá trị(%)</th>
                  <c:if test="${requestScope.locale == 'VND'}">
                    <th colspan="1"></th>
                  </c:if>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <th colspan="2"></th>
                  </c:if>
                </tr>
                <c:if test="${requestScope.phiInternal != null}">
                  <c:forEach var="item" items="${requestScope.phiInternal}" varStatus="status">
                    <c:choose>
                      <c:when test="${status.index % 2 != 0}">
                        <tr class="trDanhSachLe">
                      </c:when>
                      <c:otherwise>
                        <tr>
                      </c:otherwise>
                    </c:choose>
                      <td align="center">
                        <c:out value="${item.ngay_tt}"/>
                      </td>
                      <td align="right">
                        <c:out value="${item.so_ltt}"/>
                      </td>
                      <td align="right">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="item" property="so_tien" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write name="item" property="so_tien" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </td>
                      <td align="center">
                        <c:if test="${item.loai_phi eq 'MON'}">
                          <c:out value="${item.muc_phi}"/><!-- muc 1 -->
                        </c:if>
                      </td>
                      <td align="center">
                        <c:if test="${item.loai_phi eq 'GIA_TRI'}">
                          <c:out value="${item.muc_phi}"/><!-- muc 2 -->
                        </c:if>
                      </td>
                      <!--<td align="center">
                        --><!--VAT --><!--
                      </td>-->
                      <c:if test="${requestScope.locale != 'VND'}">
                        <td align="right">
                          <c:if test="${requestScope.totalPhiNguyenTe != null}">
                            <span style="color:green">
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write name="item" property="tong_phi_nguyen_te" />
                              </fmt:formatNumber>
                            </span>
                          </c:if>
                        </td>
                      </c:if>
                      <td align="right">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="item" property="phi" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                 <bean:write name="item" property="phi" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </td>
                    </tr>
                  </c:forEach>
                </c:if>
                <c:if test="${requestScope.locale == 'VND'}">
                  <tr>
                    <td bgcolor="#f0f0f0" colspan="6" style="color:black;font-size:1em;font-weight: bold;">II. Khác hệ thống</td>
                  </tr>
                </c:if>
                <c:if test="${requestScope.locale != 'VND'}">
                  <tr>
                    <td bgcolor="#f0f0f0" colspan="7" style="color:black;font-size:1em;font-weight: bold;">II. Khác hệ thống</td>
                  </tr>
                </c:if>
                <c:if test="${requestScope.phiExternal != null}">
                  <c:forEach var="item" varStatus="status" items="${requestScope.phiExternal}">
                    <c:choose>
                      <c:when test="${status.index % 2 != 0}">
                        <tr class="trDanhSachLe">
                      </c:when>
                      <c:otherwise>
                        <tr>
                      </c:otherwise>
                    </c:choose>
                      <td align="center">
                        <c:out value="${item.ngay_tt}"/>
                      </td>
                      <td align="right">
                        <c:out value="${item.so_ltt}"/>
                      </td>
                      <td align="right">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="item" property="so_tien" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write name="item" property="so_tien" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </td>
                      <td align="center">
                        <c:if test="${item.loai_phi eq 'MON'}">
                          <c:out value="${item.muc_phi}"/><!-- muc 1 -->
                        </c:if>
                      </td>
                      <td align="center">
                        <c:if test="${item.loai_phi eq 'GIA_TRI'}">
                          <c:out value="${item.muc_phi}"/><!-- muc 2 -->
                        </c:if>
                      </td>
                      <!--<td align="center">
                        --><!--VAT --><!--
                      </td>-->
                      <c:if test="${requestScope.locale != 'VND'}">
                        <td align="right">
                          <c:if test="${requestScope.totalPhiNguyenTe != null}">
                            <fmt:formatNumber type="currency" currencySymbol="">
                              <bean:write name="item" property="tong_phi_nguyen_te" />
                            </fmt:formatNumber>
                          </c:if>
                        </td>
                      </c:if>
                      <td align="right">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="item" property="phi" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                 <bean:write name="item" property="phi" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </td>
                    </tr>
                  </c:forEach>
                </c:if>
                <tr>
                  <td bgcolor="#f0f0f0" style="color:black;font-size:1.1em;font-weight: bold;">III. Tổng</td>
                  <td align="right">
                    <c:if test="${requestScope.totalLtt != null}">
                      <span style="color:green">
                        <c:out value="${requestScope.totalLtt}"/>
                      </span>
                    </c:if>
                  </td>
                  <td align="right">
                    <c:if test="${requestScope.totalSoTien != null}">
                      <span style="color:green">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="totalSoTien" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write name="totalSoTien" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </span>
                    </c:if>
                  </td>
                  <td align="right"></td>
                  <td align="right"></td>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <td align="right">
                      <c:if test="${requestScope.totalPhiNguyenTe != null}">
                        <span style="color:green">
                          <fmt:formatNumber type="currency" currencySymbol="">
                            <bean:write name="totalPhiNguyenTe" />
                          </fmt:formatNumber>
                        </span>
                      </c:if>
                    </td>
                  </c:if>
                  <td align="right">
                    <c:if test="${requestScope.totalPhi != null}">
                      <span style="color:green">
                        <c:choose>
                            <c:when test="${requestScope.locale == 'VND'}">
                              <fmt:formatNumber maxFractionDigits="0" type="currency"  currencySymbol="">
                                <bean:write name="totalPhi" />
                              </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                              <fmt:formatNumber type="currency" currencySymbol="">
                                <bean:write name="totalPhi" />
                              </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </span>
                    </c:if>
                  </td>
                </tr>
                <tr>
                  <td bgcolor="#f0f0f0" style="color:black;font-size:1.1em;font-weight: bold;" align="right">VAT <c:out value="${vatSample}"/>%</td>
                  <c:if test="${requestScope.locale == 'VND'}">
                    <th colspan="4"></th>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0" type="currency" currencySymbol="">
                        <c:out value="${totalPhi*vatSample/100}"/>
                      </fmt:formatNumber>
                    </td>
                  </c:if>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <th colspan="5"></th>
                    <td align="right">
                      <fmt:formatNumber type="currency" currencySymbol="">
                        <c:out value="${totalPhi*vatSample/100}"/>
                      </fmt:formatNumber>
                    </td>
                  </c:if>
                </tr>
                <tr>
                  <td bgcolor="#f0f0f0" style="color:black;font-size:1.1em;font-weight: bold;" align="right">Tổng sau VAT</td>
                  <c:if test="${requestScope.locale == 'VND'}">
                    <th colspan="4"></th>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0" type="currency" currencySymbol="">
                        <c:out value="${totalPhi*vatSample/100 + totalPhi}"/>
                      </fmt:formatNumber>
                    </td>
                  </c:if>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <th colspan="5"></th>
                    <td align="right">
                      <fmt:formatNumber type="currency" currencySymbol="">
                        <c:out value="${totalPhi*vatSample/100 + totalPhi}"/>
                      </fmt:formatNumber>
                    </td>
                  </c:if>
                </tr>
                <tr>
                  <c:if test="${requestScope.locale == 'VND'}">
                    <td colspan="6" align="left">
                      <input type="button" value="Tra cứu thông tin chi tiết tính phí" onclick="viewDetail()"/>
                    </td>
                  </c:if>
                  <c:if test="${requestScope.locale != 'VND'}">
                    <td colspan="7" align="left">
                      <input type="button" value="Tra cứu thông tin chi tiết tính phí" onclick="viewDetail()"/>
                    </td>
                  </c:if>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>