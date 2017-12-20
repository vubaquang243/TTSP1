<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>     
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
  String chkdchieu = request.getAttribute("chkdchieu")==null?"":request.getAttribute("chkdchieu").toString();
  String ngay = request.getAttribute("ngay")==null?"":request.getAttribute("ngay").toString();
  String ma_nh = request.getAttribute("ma_nh")==null?"":request.getAttribute("ma_nh").toString();
  String tcong = request.getAttribute("tcong")==null?"":request.getAttribute("tcong").toString();
  String idxTK = request.getAttribute("idxTK")==null?"":request.getAttribute("idxTK").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  var chkdchieu = '<%=chkdchieu%>';
  var ngay = '<%=ngay%>';
  var ma_nh = '<%=ma_nh%>';
  var tcong = '<%=tcong%>';
if(tcong!=null && ""!=tcong){
    alert(GetUnicode('Xác nhận chênh lệch thành công.'));
  }
  if(chkdchieu!=null && ""!=chkdchieu){
    alert(GetUnicode('Chưa nhận được sổ chi tiết từ ngân hàng.'));
  }
  if(ngay!=null && ""!=ngay){
    jQuery('#ngay').val(ngay);
  }
 if(ma_nh!=null && ""!=ma_nh){
    jQuery('#ma_nh').val(ma_nh);
  }
  jQuery(document).init(function () {
  });

</script>
  

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="loadDChieuSKeAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Đối chiếu sổ chi tiết</span>
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
       <td width="60%">
          <fieldset>
              <table  class="data-grid" id="data-grid" 
                                      border="1"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
                  <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                      Ngân hàng
                    </td>
                    <td width="35%">
                         <html:select property="ngan_hang" styleId="ngan_hang" style="font-size:12px;width:100%"  onchange="getTK_NH_KB()"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <html:option value="">---Chọn ngân hàng---</html:option>
                              <html:optionsCollection  name="colTKNHKB" value="ma_nh" label="ten"/>                    
                          </html:select>
                    </td>
                    <td width="10%" align="right" bordercolor="#e1e1e1">
                      Số TK
                    </td>
                    <td width="30%">
                    <html:select property="so_tk" styleId="so_tk" style="font-size:12px;width:100%" onchange="tkval();"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----Chọn số tài khoản-----</html:option>
                      
                  </html:select> 
                    </td>
                    
                  </tr>
                  <tr>
                    <td align="right" bordercolor="#e1e1e1">
                      Ngày 
                    </td>
                    <td align="left">
                <html:text property="ngay" styleId="ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_btn"// id of the button
                      });
                    </script>
                    </td>
                    <td align="right"  colspan="1"><!--Loại TK : --></td>
                    <td align="left"  colspan="1">
                        <!--<b><span id="hintAccountType"></span>&nbsp;-&nbsp;</b>
                        <b><span id="hintMoneyType"></span></b>-->
                    </td>
                  </tr>
                  <tr>
                    <td align="center" colspan="4">
                      <button type="button" onclick="CHKDChieuSKe();"  id="btDchieu" class="ButtonCommon" accesskey="c">
                        Đối <span class="sortKey">C</span>hiếu
                      </button>
                    </td>
                  </tr>
              </table>
          </fieldset>
          
          <logic:notEqual value="00" name="DChieuSKeForm" property="err_code">
            <logic:notEqual value="" name="DChieuSKeForm" property="err_code">
              <font color="Red">Thực hiện đối chiếu không thành công:<bean:write name="DChieuSKeForm" property="err_code"/>-<bean:write name="DChieuSKeForm" property="err_desc"/></font>
            </logic:notEqual>
          </logic:notEqual>
          
        </td>
       </tr>
       <tr>
        <td>
          <fieldset>
            <legend><font color="blue">Kết quả đối chiếu</font></legend>
            <div>
              <table  class="data-grid" id="data-grid" bordercolor="#e1e1e1" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="2" >
                <logic:empty name="colCTiet">
                  <tr>
                    <td bordercolor="#e1e1e1" width="20%" align="right">
                      Kết quả đối chiếu
                    </td>
                    <td bordercolor="#e1e1e1">
                      <input type="text" name="ket_qua" readonly="readonly"/>
                    </td>
                  </tr>
                  <tr>
                    <td bordercolor="#e1e1e1" align="right">
                    </td>
                    <td>
                      <table  class="data-grid" id="data-grid"  bordercolor="#e1e1e1" 
                                              style="width:80%" border="1"
                                             cellspacing="0" cellpadding="5" >
                          <tr>
                            <td rowspan="2" width="16.6%" align="center"><b style="font-size:10pt">Số liệu NH</b></td>
                            <td rowspan="2"  width="15%">
                              &nbsp;
                            </td>
                            <td   bgcolor="#f0f0f0" width="25%"  align="center">
                              <b>Phát sinh có</b>
                            </td>
                            <td  bgcolor="#f0f0f0" width="25%"  align="center">
                              <b>Phát sinh nợ</b>
                            </td>
                            <td  bgcolor="#f0f0f0" width="25%"  align="center">
                              <b>Số dư</b>
                            </td>
                          </tr>
                          <tr>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          
                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td bordercolor="#e1e1e1" align="right"></td>
                    <td>
                      <table  class="data-grid" id="data-grid" bordercolor="#e1e1e1" 
                                              style="width:80%" border="1"
                                             cellspacing="0" cellpadding="2" >
                          <tr>
                            <td rowspan="9" width="16.7%" align="center"><b style="font-size:10pt">Số liệu KB</b></td>
                            <td width="15%"></td>
                            <td  width="25%" bgcolor="#f0f0f0" align="center">
                              <b>Phát sinh có</b>
                            </td>
                            <td width="25%" bgcolor="#f0f0f0" align="center">
                              <b>Phat sinh nợ</b>
                            </td>
                            <td width="25%" bgcolor="#f0f0f0" align="center">
                              <b>Số dư (Bao gồm số thu sau giờ)</b>
                            </td>
                          </tr>
                          <tr>
                            <td colspan="4">
                             <b> Số trong giờ ngày đối chiếu</b>
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + TTSP
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + PHT
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td colspan="4">
                             <b> Số sau giờ ngày đối chiếu</b>
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + TTSP
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + PHT
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td>
                             <b> Quyết toán</b>
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td>
                              Tổng
                            </td>
                            <td>
                              &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                      </table>
                    </td>
                  </tr>
                </logic:empty>
                
                <logic:notEmpty name="colCTiet">
                <logic:iterate id="items" name="colCTiet">
                  <logic:equal value="VND" name="items" property="ma_nt">
                    <fmt:setLocale value="vi_VI"/>
                  </logic:equal>
                  <logic:notEqual value="VND" name="items" property="ma_nt">
                    <fmt:setLocale value="vi_VI"/>
                  </logic:notEqual>
                  <tr>
                    <td bordercolor="#e1e1e1"  width="10.7%" align="right">
                        &nbsp;
                    </td>
                    <td bordercolor="#e1e1e1">Kết quả đối chiếu&nbsp;
                      <logic:equal value="0" property="ket_qua" name="items">
                         <b><font color="Blue"> Khớp đúng </font></b>
                      </logic:equal>
                      <logic:equal value="1" property="ket_qua" name="items">
                        <b><font color="Red"> Chênh lệch </font></b>
                      </logic:equal>
                    </td>
                  </tr>
                  <tr>
                    <td bordercolor="#e1e1e1" align="right"></td>
                    <td>
                      <table  class="data-grid" id="data-grid"  bordercolor="#e1e1e1" style="width:88%" border="1" cellspacing="0" cellpadding="3" >
                        <tr>
                          <td rowspan="2" width="16.6%" align="center"><b style="font-size:10pt">Số liệu NH</b></td>
                          <td rowspan="2" width="15.1%" align="center"></td>
                          <td  bgcolor="#f0f0f0" width="25%"  align="center">
                            <b>Phát sinh có</b>
                          </td>
                          <td  bgcolor="#f0f0f0" width="25%"  align="center">
                            <b>Phát sinh nợ</b>
                          </td>
                          <td  bgcolor="#f0f0f0" width="25%"  align="center">
                            <b>Số dư</b>
                          </td>
                        </tr>
                        <tr>
                          <td style="text-align:right;background-color:#F2F9FE;">
                            <b>
                              <logic:equal value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_ps_co"><font color="Green">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_co_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_ps_co"><font color="Red">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency" currencySymbol="">
                                      <bean:write name="items" property="ps_co_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:notEqual>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_ps_co"><font color="Green">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_co_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_ps_co"><font color="Red">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_co_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:notEqual>
                              </logic:notEqual>
                            </b>
                          </td>
                          <td style="text-align:right;background-color:#F2F9FE;">
                            <b>
                              <logic:equal value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_ps_no"><font color="Green">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_no_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_ps_no"><font color="Red">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_no_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:notEqual>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_ps_no"><font color="Green">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_no_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_ps_no"><font color="Red">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="ps_no_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:notEqual>
                              </logic:notEqual>
                            </b>
                          </td>
                            <td style="text-align:right;background-color:#F2F9FE;">
                              <b>
                              <logic:equal value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_so_du"><font color="Green">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                      <bean:write name="items" property="so_du_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_so_du"><font color="Red">
                                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                      <bean:write name="items" property="so_du_nh"/>
                                    </fmt:formatNumber>
                                  </font></logic:notEqual>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                  <logic:equal value="0" name="items" property="ss_so_du"><font color="Green">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="so_du_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:equal>
                                  <logic:notEqual value="0" name="items" property="ss_so_du"><font color="Red">
                                    <fmt:formatNumber type="currency"  currencySymbol="">
                                      <bean:write name="items" property="so_du_nh"/>
                                    </fmt:formatNumber></font>
                                  </logic:notEqual>
                              </logic:notEqual>
                            </b>
                            </td>
                          </tr>
                          
                      </table>
                    </td>
                  </tr>
                  <tr style="line-height:0px">
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td bordercolor="#e1e1e1" align="right"></td>
                    <td>
                      <table  class="data-grid" id="data-grid"  bordercolor="#e1e1e1" 
                                              style="width:88%" border="1"
                                             cellspacing="0" cellpadding="3" >
                          <tr>
                            <td rowspan="9" width="16.7%" align="center"><b style="font-size:10pt">Số liệu KB</b></td>
                            <td  width="15%"></td>
                            <td  width="25%" bgcolor="#f0f0f0"  align="center">
                              <b>Phát sinh có</b>
                            </td>
                            <td  width="25%" bgcolor="#f0f0f0"  align="center">
                              <b>Phát sinh nợ</b>
                            </td>
                            <td  width="25%" bgcolor="#f0f0f0"  align="center">
                              <b>Số dư (Bao gồm số thu sau giờ)</b>
                            </td>
                          </tr>
                          <tr>
                            <td colspan="4">
                              <b>Số trong giờ ngày đối chiếu</b>
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + TTSP
                            </td>
                             <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_co_ttsp_tg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_co_ttsp_tg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_no_ttsp_tg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_no_ttsp_tg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td> 
                            <td>
                            &nbsp;
                            </td> 
                          </tr>
                          <tr>
                            <td >
                              + PHT
                            </td>
                             
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_pht_tg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_pht_tg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td style="text-align:right">
                              &nbsp
                            </td>
                            <td>
                            &nbsp;
                            </td> 
                          </tr>
                          <tr>
                            <td colspan="4">
                              <b>Số sau giờ ngày đối chiếu</b>
                            </td>
                          </tr>
                          <tr>
                            <td >
                              + TTSP
                            </td>
                             <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_co_ttsp_sg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_co_ttsp_sg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_no_ttsp_sg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_no_ttsp_sg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td> 
                            <td>
                            &nbsp;
                            </td> 
                          </tr>
                          <tr>
                            <td >
                              + PHT
                            </td>
                             
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_pht_sg"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="ps_pht_sg"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td style="text-align:right">
                              &nbsp
                            </td>
                            <td>
                            &nbsp;
                            </td> 
                          </tr>
                          <tr>
                            <td>
                             <b> Quyết toán</b>
                            </td>
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="qtoan_chi"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="qtoan_chi"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td style="text-align:right">
                              <logic:equal value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                  <bean:write name="items" property="qtoan_thu"/>
                                </fmt:formatNumber>
                              </logic:equal>
                              <logic:notEqual value="VND" name="items" property="ma_nt">
                                <fmt:formatNumber type="currency"  currencySymbol="">
                                  <bean:write name="items" property="qtoan_thu"/>
                                </fmt:formatNumber>
                              </logic:notEqual>
                            </td>
                            <td>
                            &nbsp;
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <b>Tổng</b>
                            </td>
                            <td style="text-align:right;background-color:#F2F9FE;">
                              <b>
                                <logic:equal value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_ps_co"><font color="Green">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                       <bean:write name="items" property="tong_ps_co"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_ps_co"><font color="Red">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_co"/>
                                      </fmt:formatNumber></font>
                                    </logic:notEqual>
                                </logic:equal>
                                <logic:notEqual value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_ps_co"><font color="Green">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_co"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_ps_co"><font color="Red">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_co"/>
                                      </fmt:formatNumber>
                                    </font></logic:notEqual>
                                </logic:notEqual>
                              </b>
                            </td>
                            <td style="text-align:right;background-color:#F2F9FE;">
                              <b>
                                <logic:equal value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_ps_no"><font color="Green">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_no"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_ps_no"><font color="Red">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_no"/>
                                      </fmt:formatNumber>
                                    </font></logic:notEqual>
                                </logic:equal>
                                <logic:notEqual value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_ps_no"><font color="Green">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_no"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_ps_no"><font color="Red">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_ps_no"/>
                                      </fmt:formatNumber>
                                    </font></logic:notEqual>
                                </logic:notEqual>
                              </b>
                            </td>
                            <td style="text-align:right;background-color:#F2F9FE;">
                              <b>
                                <logic:equal value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_so_du"><font color="Green">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_so_du"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_so_du"><font color="Red">
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_so_du"/>
                                      </fmt:formatNumber>
                                    </font></logic:notEqual>
                                </logic:equal>
                                <logic:notEqual value="VND" name="items" property="ma_nt">
                                    <logic:equal value="0" name="items" property="ss_so_du"><font color="Green">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_so_du"/>
                                      </fmt:formatNumber></font>
                                    </logic:equal>
                                    <logic:notEqual value="0" name="items" property="ss_so_du"><font color="Red">
                                      <fmt:formatNumber type="currency"  currencySymbol="">
                                        <bean:write name="items" property="tong_so_du"/>
                                      </fmt:formatNumber>
                                    </font></logic:notEqual>
                                </logic:notEqual>
                              </b>
                            </td>
                          </tr>
                      </table>
                    </td>
                  </tr>
                  <logic:equal value="1" property="ket_qua" name="items">
                  <tr>
                    <td colspan="2" align="left">
                      <fieldset>
                      <legend><font color="blue">Chi tiết chênh lệch</font></legend>
                      <div style="margin-left: 10%">
                      <table  class="data-grid" id="data-grid"  bordercolor="#e1e1e1" 
                                              style="width:88.8%" border="1"
                                             cellspacing="0" cellpadding="3">
                        <thead>
                          <tr>
                            <th colspan="2">Số liệu tại Ngân hàng</th>
                            <th colspan="2">Số liệu tại Kho bạc</th>
                            <th rowspan="2">Tính chất PS</th>
                          </tr>
                          <tr>
                            <th style="width:22%">Số tiền</th>
                            <th style="width:22%">Số món</th>
                            <th style="width:22%">Số tiền</th>
                            <th style="width:22%">Số món</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="item" items="${requestScope.colCTietCLech}" varStatus="i">
                          <c:if test="${i.index%2 == 0}">
                          <tr>
                          </c:if>
                          <c:if test="${i.index%2 != 0}">
                          <tr style="background-color:#f2f9fe">
                          </c:if>
                            <td align="right"><c:out value="${item[3]}"/></td>
                            <td align="center"><c:out value="${item[4]}"/></td>
                            <td align="right"><c:out value="${item[5]}"/></td>
                            <td align="center"><c:out value="${item[6]}"/></td>
                            <td align="center">
                                <c:if test="${item[7] == 'C'}">P/s có</c:if>
                                <c:if test="${item[7] == 'D'}">P/s nợ</c:if>
                            </td>
                          </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                      </div>
                      </fieldset>
                    </td>
                  </tr>
                  <tr>
                    <td align="right">
                      Lý do xác nhận
                    </td>
                    <td>
                      <html:text style="width:87.6%"  property="noi_dung" name="items" styleId="noi_dung"/>
                    </td>
                  </tr>         
                  </logic:equal>
                  <tr align="right">
                    <td align="right" colspan="2">
                    <logic:equal value="1" property="ket_qua" name="items">
                      <logic:equal value="00" name="items" property="trang_thai">
                        
                          <button type="button" onclick="check('update')" id="btUpdate" class="ButtonCommon" accesskey="x">
                             <span class="sortKey">X</span>ác nhận
                          </button> &nbsp;&nbsp;&nbsp;&nbsp;
 
                      </logic:equal>
                    </logic:equal>
  
                      <button type="button" onclick="check('thoat')" id="btThoat" class="ButtonCommon" accesskey="t">
                             <span class="sortKey">T</span>hoát
                          </button>
                    </td>
                  </tr>
                </logic:iterate>
                </logic:notEmpty>              
              </table>
            </div>
          </fieldset>
        </td>
       </tr>
    </table>
    <html:hidden property="ma_nh" styleId="ma_nh"/>
    <html:hidden property="shkb" styleId="shkb"/>
    <html:hidden property="ngay_dc" styleId="ngay_dc"/>
    
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>

<script type="text/javascript">
  var f = document.forms[0];
sltNH();  
  function sltNH(){
    var nhlgth= document.getElementById("ngan_hang").options.length;
    
    if(nhlgth>=2){ 
      jQuery("#ngan_hang option:eq(0)").remove();
      getTK_NH_KB();
    } 
  }
  
function getTK_NH_KB() {
  document.getElementById("so_tk").options.length = 1;// clear du lieu option cu
  var nh_id = null; //jQuery('#ngan_hang').val()==''?jQuery('#ma_nh').val():jQuery('#ngan_hang').val();//document.forms[0].nhkb_huyen.value;" +
  var shkb = jQuery('#shkb').val();
  var ngay_dc = jQuery('#ngay_dc').val();
  var soTk="<%=idxTK%>";
  if(shkb!=null && ''!=shkb){
    jQuery('#btUpdate').hide();
    jQuery('#btDchieu').hide();
    nh_id =jQuery('#ma_nh').val();
    jQuery('#ngay').val(ngay_dc);
    jQuery("#ngay").attr({readonly : "readonly"});
    jQuery("#noi_dung").attr({readonly : "readonly"});
  }else {
    nh_id =jQuery('#ngan_hang').val();
  }
  if (nh_id !=null && ""!=nh_id){
      jQuery.ajax( {
          type : "POST", url : "TKNHKBAction.do", data :  {
              "nh_id" : nh_id, "shkb" : shkb
          },
          success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
              if (data != null) {
                jQuery.each(data, function (i, objectDM) {
                  var presentText = ' - (';
                  if(objectDM.loai_tk == '01'){
                    presentText = presentText + ' Tiền gửi ';
                  }else if(objectDM.loai_tk == '02'){
                    presentText = presentText + ' Thanh toán ';
                  }else if(objectDM.loai_tk == '03'){
                    presentText = presentText + ' Chuyên thu ';
                  }
                  presentText = presentText+objectDM.loai_tien;
                  document.getElementById('so_tk').options.add(new Option(objectDM.so_tk + presentText + ' )', objectDM.so_tk))
                });
              }
              if(document.getElementById('so_tk').options.length>=2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                  jQuery("#so_tk option:eq(0)").remove();
              }
              if(soTk != null && soTk != ""){
                  jQuery("#so_tk").val(soTk);
              }
//              loadAccountDetailAJAX();
            }          
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
    }
}
  
  function check(type) {
      if (type == 'dchieu') {
      document.getElementById("btDchieu").disabled=true;
          f.action = 'DChieuSKeAction.do';        
        }
      if (type == 'thoat') {
          f.action = 'TCuuDChieuSoChiTiet.do';        
      }
      if (type == 'update') {
      document.getElementById("btUpdate").disabled=true;
          f.action = 'updateDChieuSKeAction.do';        
      }
      f.submit();
  }
  
   function CHKDChieuSKe() { 
      var ma_nh = jQuery('#ngan_hang').val(); 
      var ngay_dc = jQuery('#ngay').val(); 
      var so_tk = jQuery('#so_tk').val(); 
      if(ngay_dc.trim ==null || ''==ngay_dc.trim){
        alert('Cần nhập thông tin ngày đối chiếu');     
        jQuery('#ngay').focus();
        return false;   
      }
      
      if (ma_nh !=null && ""!=ma_nh){
          jQuery.ajax( {
              type : "POST", url : "CHKDChieuSKeAction.do", 
              data :  {"ma_nh" : ma_nh, "ngay_dc": ngay_dc, "so_tk":so_tk},
              success : function (data, textstatus) {
                  if (textstatus != null && textstatus == 'success') {
                      if (data != null) {
                          jQuery.each(data, function (i, objectDM) {
                              var ngay_thien = objectDM.abc;
                              var count_tt_065 = objectDM.count_tt_065;
                              var count_tt_sk = objectDM.count_tt_sk;
                              var count_tt_trc = objectDM.count_tt_trc;                         
                              isCurrDate(ngay_thien,ngay_dc);
                              if(isCurrDate(ngay_thien,ngay_dc)==false){
                                  alert('Không thể đối chiếu sổ chi tiết khi chưa có đủ thông tin');
                                  return false;
                              }                                                
                              if(count_tt_trc ==0){
                                  alert('Chưa hoàn thành đối chiếu sổ chi tiết ngày trước');
                                  return false;
                              }else if(count_tt_065 ==0){
                                  alert('Chưa có đầy đủ thông tin thu');
                                  return false;
                              }else if(count_tt_sk !=0){
                                  alert('Sổ chi tiết ngày '+ ngay_dc +' đã được đối chiếu');
                                  return false;
                              }else if (count_tt_065!=0 && count_tt_sk==0){
                                  check('dchieu');
                              }
    
                          });
                          }
                          }          
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
  
     function isCurrDate(txt_ngay, curDate){
//    var curDate=jQuery('#ngay').val();
//    alert(curDate +'----'+dateValue);
    if(CompareDate(txt_ngay, curDate) ==  - 1)
      return  true;
    else
      return false;
  }
  
//function loadAccountDetailAJAX(){
//    var soTk = jQuery('#so_tk').val();
//    var nh_id = jQuery('#ngan_hang').val();
//    jQuery.ajax({
//          type : "POST",
//          url : "getTKNHKB.do", 
//          data :  {"soTk":soTk, "nh_id":nh_id}
//        , success : function (data, textstatus) {
//          if (textstatus != null && textstatus == 'success') {
//            if (data != null) {
//              if(data[0].loai_tk == '01'){
//                jQuery('#hintAccountType').text('TK Tiền gửi')
//              }else if(data[0].loai_tk == '02'){
//                jQuery('#hintAccountType').text('TK Thanh toán')
//              }else if(data[0].loai_tk == '03'){
//                jQuery('#hintAccountType').text('TK Chuyên thu')
//              }
//              jQuery('#hintMoneyType').text(data[0].loai_tien)
//            }
//          }
//        },
//        error : function (textstatus) {
//            alert(textstatus);
//        }
//    });
//}
</script>
