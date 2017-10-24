<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/quyettoan.js"
        type="text/javascript">
</script>
<%
  String strTong_Tien = request.getAttribute("tong_tien")!=null?request.getAttribute("tong_tien").toString():"";
  String strTong_Mon = request.getAttribute("tong_mon")!=null?request.getAttribute("tong_mon").toString():"";
  String str_Err_Desc = request.getAttribute("p_err_desc")!=null?request.getAttribute("p_err_desc").toString():"";
  if(str_Err_Desc!=null && !"".equals(str_Err_Desc)){
%>
<table width="99%">
    <tbody>
    <tr>
      <td><font color="blue">
          <%=str_Err_Desc%>
        </font> 
      </td></tr>
    </tbody>
  </table>
<%
  }
%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () { 
    // set gia tri cho selectbox
      var indexValue = document.getElementsByName("idTmpLst").length;
      var loai_hach_toan = document.getElementsByName("loai_hach_toan");
      for(var i = 0; i<indexValue;i++){
        // mac dinh neu khong co gia tri thi la hach toan dung
        jQuery("#loai_hach_toan_"+i).val(loai_hach_toan[i].value!=null?loai_hach_toan[i].value:"T");
      }
    //end
  
    jQuery("#itong_st").val(jQuery("#tong_st").val());
    jQuery("#tong_tien_field").val(CurrencyFormatted('<%=strTong_Tien%>', 'VND')); 
      jQuery("#tong_mon_field").val(CurrencyFormatted('<%=strTong_Mon%>', 'VND'));
    
    jQuery("#btn_Thoat").click(function () {
          document.forms[0].target='';
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
      });
      
      jQuery("#dialog-confirm").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              "Có" : function () {    
                  // thuc hien update trang thai
                  document.forms[0].target='';
                  document.forms[0].action = "thoatView.do";
                  document.forms[0].submit();
                  jQuery(this).dialog("close");
              },
              "Không" : function () {    
                  // thuc hien update trang thai
                  jQuery(this).dialog("close");
              }
          }
      });
      jQuery("#chklistAll").click(function(){
        jQuery("#colCheckList").find(':checkbox').attr('checked', this.checked);
      });
  });
  function changeValueLoaiHT(id,value){
    jQuery.ajax( {
              type : "POST", url : "QuyetToanToanQuocUpdate.do", data :  {
                  "id" : id,
                  "value" : value,
                  "nd" : Math.random() * 100000
              },
              success : function (data, textstatus) {
                  if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                      if(data!=null){
                        if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                        }
                        else {
                          if (data.error != 'undefined' && data.error != '') {
                              if(data.exception_message !=null && data.exception_message != ""){
                                  alert(GetUnicode('thay &#273;&#7893;i gi&#225; tr&#7883; ph&#432;&#417;ng th&#7913;c h&#7841;ch to&#225;n kh&#244;ng th&#224;nh c&#244;ng :\n' + data.exception_message));
                                  return;
                                }
                              }
                        }
                      }                      
                  }                      
              },
              error : function (textstatus) {
                  alert(GetUnicode('thay &#273;&#7893;i gi&#225; tr&#7883; ph&#432;&#417;ng th&#7913;c h&#7841;ch to&#225;n kh&#244;ng th&#224;nh c&#244;ng :' + textstatus));
                  return;
              }
          });
  }
  function FindQT(){
//    if(jQuery("#loai_qt").val()==''){
//      alert("Bạn phải chọn loại quyết toán !");
//      jQuery("#loai_qt").focus();
//      return;
//    }
    /*
     * ko tich chon nua
     * bat buoc phai nhap ngan hang
     * */ 
    document.forms[0].target='';
    document.forms[0].action="QuyetToanToanQuocList.do";
    document.forms[0].submit();
  }
  function goPage(page) {
      document.forms[0].target='';
      jQuery("#frmQTTQ").attr( {
          action : 'QuyetToanToanQuocList.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmQTTQ").submit();
  }
  function taoBKQTTQ(){
  
//    var arrQT=new Array();
//    checkboxes = document.getElementsByName('chklist');
//    var count=0;    
//    for(var i=0, n=checkboxes.length;i<n;i++) {    
//       if(checkboxes[i].checked){
//       var loai_ht =null;
////        if(jQuery('#chklistAll').is(':checked')){ alert('a');
//          loai_ht = document.getElementById("tblBlah").rows[i+1].cells[8].childNodes[0];          
////        }else{
////          loai_ht = document.getElementById("tblBlah").rows[i].cells[8].childNodes[0];
////        }          
//         var qttqParam = loai_ht.options[loai_ht.selectedIndex].value+"|"+ jQuery("#"+checkboxes[i].id).val();
//         arrQT.push(qttqParam);
//         count++;
//       }
//    }    
//    document.forms[0].action="QuyetToanToanQuocUpdateExc.do?value="+arrQT;
//    document.forms[0].submit();

    /*
     * ko tich chon nua
     * bat buoc phai nhap ngan hang
     * */
     document.forms[0].target='';
    if(jQuery("#hsc_nh").val() != null && ""!=jQuery("#hsc_nh").val()){
      document.forms[0].action="QuyetToanToanQuocUpdateExc.do";
      document.forms[0].submit();
    }else{
      alert("Bạn phải chọn ngân hàng hội sở !");
      jQuery("#hsc_nh").focus();
    }
  }
//  function changeValueCheckList(checkListId,value){
//    if(jQuery('#'+checkListId.id).is(':checked')){
//      var qttqParam = value+"|"+checkListId.value;
//      arrQT.push(qttqParam);
//    }
//    if(!jQuery('#'+checkListId.id).is(':checked')){
//      for(var i = 0 ; i < arrQT.length;i++){
//        
//      }
//    }
//  }
  function forwardBK(){
    document.forms[0].target='';
    document.forms[0].action="XuLyQToanTQuoc.do?action=ViewBK";
    document.forms[0].submit();
  }
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuyetToanToanQuocResource"/>
    <html:form styleId="frmQTTQ" action="/QuyetToanToanQuoc.do">

<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          <fmt:message key="QTToanQuoc.page.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table> 
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="QTToanQuoc.page.dieukien"/>
        </td>
      </tr>
   </tbody>    
  </table>
    <table class="tableBound">
      <tr>
        <td>
          <table width="99%">
            <tbody>
            <tr>
              <td><font color="red">
                  <html:errors/>
                </font> 
              </td></tr>
            </tbody>
          </table>
          <table cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td valign="top">
                  <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                         width="100%">
                      <tbody>
                        <tr>
                          <td width="10%" style="text-align:right">
                            <label for="HSC">
                              <fmt:message key="QTToanQuoc.page.lable.HSC"/>
                            </label>
                          </td>
                          <td width="18%">
                            <html:select styleClass="selectBox" property="hsc_nh" styleId="hsc_nh" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                            <html:option value="">
                              <fmt:message key="QTToanQuoc.page.hsc_nh.default"/>
                            </html:option>
                              <html:optionsCollection label="ten_nh" value="ma_dv" name="lstNHHO"/>
                            </html:select>
                          </td>
                          <td width="10%" style="text-align:right">
                            <label for="NGAYQT">
                              <fmt:message key="QTToanQuoc.page.lable.NGAYQT"/>
                            </label>
                          </td>
                          <td width="18%">
                            <html:text property="ngay_qt" styleId="ngay_qt" styleClass="fieldText"
                            onkeypress="return numbersonly(this,event,true) "
                            onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_qt');"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 77%;"/>
                           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                 border="0" id="ngay_qt_btn" width="16%"
                                 style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                                  inputField : "ngay_qt", // id of the input field
                                  ifFormat : "%d/%m/%Y", // the date format
                                  button : "ngay_qt_btn"// id of the button
                              });
                            </script>
                          </td>
                          <td width="10%" style="text-align:right">
                            <label for="LOAIQT">
                              <fmt:message key="QTToanQuoc.page.lable.LOAIQT"/>
                            </label>
                          </td>
                          <td width="18%">
                            <html:select styleClass="selectBox" property="loai_qt"
                                     styleId="loai_qt" style="width:100%" onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                     onfocus="textfocus(this);">
                                     <html:option value="">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.default"/>
                                     </html:option>
                                     <html:option value="D">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.thu"/>
                                     </html:option>
                                     <html:option value="C">
                                        <fmt:message key="QTToanQuoc.page.loai_quyet_toan.chi"/>
                                     </html:option>                                   
                                 </html:select>
                          </td>
                          <td width="6%" style="text-align:right">
                          Loại tiền
                          </td>
                          <td style="text-align:left">
                            <html:select styleClass="selectBox" property="tcg_loai_tien" styleId="tcg_loai_tien" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                              <html:option value="VND">VND</html:option>
                              <html:optionsCollection label="ma" value="ma" name="lstLoaiTien"/>
                            </html:select>                                              
                          </td>
                        </tr>
                        <tr>
                          <td style="text-align:right">
                            <label for="KBTINH">
                              <fmt:message key="QTToanQuoc.page.lable.KBTINH"/>
                            </label>
                          </td>
                          <td>
                            <html:select styleClass="selectBox" property="kb_tinh" styleId="kb_tinh" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                              <html:option value="">
                              <fmt:message key="QTToanQuoc.page.kbTinh.default"/>
                              </html:option>
                              <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
                            </html:select>
                          </td>
                          <td style="text-align:right">
                            <label for="NGAYTT">
                              <fmt:message key="QTToanQuoc.page.lable.NGAYTT"/>
                            </label>
                          </td>
                          <td>
                            <html:text property="ngay_tt" styleId="ngay_tt" styleClass="fieldText"
                            onkeypress="return numbersonly(this,event,true) "
                            onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_tt');"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 77%;"/>
                           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                 border="0" id="ngay_tt_btn" width="16%"
                                 style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                                  inputField : "ngay_tt", // id of the input field
                                  ifFormat : "%d/%m/%Y", // the date format
                                  button : "ngay_tt_btn"// id of the button
                              });
                            </script>
                          </td>
                          <td style="text-align:right">
                            <label>
                              Loại tài khoản
                            </label>
                          </td>
                          <td>
                           <html:select styleClass="selectBox" property="loai_tk"
                                     styleId="loai_tk" style="width:100%" onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                     onfocus="textfocus(this);">
                              <html:option value="">
                              Chọn loại tài khoản
                              </html:option>
                              <html:option value="01">
                              Tài khoản tiền gửi
                              </html:option>
                              <html:option value="02">
                              Tài khoản thanh toán
                              </html:option>
                              <html:option value="03">
                              Tài khoản chuyên thu
                              </html:option>
                            </html:select>
                          </td>
                          <td></td>
                          <td></td>
                        </tr>
                      </tbody>
                    </table>
                  </td>
                </tr>
              </tbody>
            </table>          
        </td>
      </tr>
    </table>
    <table  class="buttonbar" align="center">
            <tr>
              <td>
                <span id="tracuu">
                  <button  id="btn_timKiem" 
                      tabindex="111"
                      type="button" onclick="FindQT()"
                      class="ButtonCommon" >
                       <fmt:message key="QTToanQuoc.page.button.search"/>
                  </button>
                </span>
                <span id="exit">
                  <button  id="btn_Thoat" 
                      tabindex="111"
                      type="button"
                      class="ButtonCommon" >
                       <fmt:message key="QTToanQuoc.page.button.exit"/>
                  </button>
                </span>
              </td>
            </tr>
          </table>
    <table style="BORDER-COLLAPSE: collapse" id="tblBlah" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <thead class="TR_Selected">
        <tr>
          <!--<th width="1%">
            <input type="checkbox" name="chklistAll" value="" id="chklistAll"/>
          </th>-->
          <th width="1%">
            <fmt:message key="QTToanQuoc.page.ketqua.STT"/>
          </th>
          <th width="15%">
            <fmt:message key="QTToanQuoc.page.ketqua.kbtinh"/>
          </th>
          <th width="15%">
            <fmt:message key="QTToanQuoc.page.ketqua.KB"/>
          </th>
          <th width="15%">
            <fmt:message key="QTToanQuoc.page.ketqua.NHCN"/>
          </th>          
          <th width="8%">
            <fmt:message key="QTToanQuoc.page.ketqua.SLQToan"/>
          </th>
          <th width="5%">
            <fmt:message key="QTToanQuoc.page.ketqua.TKCHUYEN"/>
          </th>
          <th width="12%">
            <fmt:message key="QTToanQuoc.page.ketqua.STien"/>
          </th>
          <th width="5%">
            <fmt:message key="QTToanQuoc.page.ketqua.ngayqt"/>
          </th>
          <th width="5%">
            <fmt:message key="QTToanQuoc.page.ketqua.ngaytt"/>
          </th>
          <th width="5%">
            <fmt:message key="QTToanQuoc.page.ketqua.LOAIQT"/>
          </th>
          <th width="12%">
            <fmt:message key="QTToanQuoc.page.ketqua.PHUONGAN"/>
          </th>
        </tr>
      </thead>
      <tbody id="colCheckList">
        <logic:present name="lstQuyetToan">
          <logic:empty name="lstQuyetToan">
              <tr>
                <td colspan="11" align="center" style="color:red">
                  <fmt:message key="QTToanQuoc.page.ketqua.empty"/>
                </td>
              </tr>
            </logic:empty>
            <logic:notEmpty name="lstQuyetToan">
             <%
              com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
              int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            %>
              <logic:iterate id="items" name="lstQuyetToan" indexId="stt">
                <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <!--<td  align="center">
                        <input type="checkbox" id="checklist_<%=stt%>" name="chklist"  value='<bean:write name="items" property="id"/>' >
                      </td>-->
                      <input type="hidden" id="idTmp_<%=stt%>" name="idTmpLst" value="<bean:write name="items" property="id"/>" />
                      
                      <td>
                        <%=stt+1%>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_kb_tw"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_kb_huyen"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_don_vi_chuyen"/>
                      </td>
                      <td>
                        <bean:write name="items" property="id"/>
                      </td>
                      <td>
                        <bean:write name="items" property="tk_chuyen"/>
                      </td>
                      <td align="right">
                        <c:choose>
                            <c:when test="${items.ma_nt == 'VND'}">
                                <fmt:setLocale value="vi_VI"/>
                                <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                    <bean:write name="items" property="so_tien"/>
                                </fmt:formatNumber>
                            </c:when>
                            <c:otherwise>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                                    <bean:write name="items" property="so_tien"/>
                                </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                      </td>
                      <td>
                        <bean:write name="items" property="ngay_qtoan"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ngay_htoan"/>
                      </td>
                      <td>
                        <logic:equal value="D" name="items" property="loai_qtoan">
                          <fmt:message key="QTToanQuoc.page.loai_quyet_toan.thu"/>
                         </logic:equal>
                         <logic:equal value="C" name="items" property="loai_qtoan">
                          <fmt:message key="QTToanQuoc.page.loai_quyet_toan.chi"/>
                         </logic:equal>
                      </td>
                      <td>
                        <select class="selectBox" id="loai_hach_toan_<%=stt%>" style="width:100%" 
                                onblur="textlostfocus(this);" onkeydown="nextFocus();" 
                                onchange="changeValueLoaiHT('<bean:write name="items" property="id"/>',this.value);"  onfocus="textfocus(this);">
                          <option value="T">
                              <fmt:message key="QTToanQuoc.page.ketqua.loai_hach_toan.dung"/>
                          </option>
                          <option value="P">
                                <fmt:message key="QTToanQuoc.page.ketqua.loai_hach_toan.choxuly"/>                      
                          </option>
                        </select>
                        <html:hidden name="items" property="loai_hach_toan"/>
                      </td>
                </tr>
              </logic:iterate>
              <tr>
              <td></td>
              <td align="center">
                <b>Tổng tiền</b>
              </td>
              <td colspan="1">
                <b>
                  <c:choose>
                      <c:when test="${lstQuyetToan[0].ma_nt == 'VND'}">
                          <fmt:setLocale value="vi_VI"/>
                          <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="tongTienCacQuyetToan" />
                          </fmt:formatNumber>
                      </c:when>
                      <c:otherwise>
                          <fmt:setLocale value="en_US"/>
                          <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                              <bean:write name="tongTienCacQuyetToan" />
                          </fmt:formatNumber>
                      </c:otherwise>
                  </c:choose>
                </b>
              </td>
               <td align="center">
                <b>Tổng món</b>
              </td>
              <td colspan="1" >
                <b><bean:write name="PAGE_KEY" property="numberOfRow"/></b>
              </td>
                    <td colspan="6">
                      <div style="float:right;padding-right:40">
                        <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                      </div>
                    </td>
              </tr>
                  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
            </logic:notEmpty>
        </logic:present>
        <tr>
        </tr>
      </tbody>
    </table>
    <table width="100%" align="right">
            <tr> 
              <td width="7%" align="right">
                <fmt:message key="QTToanQuoc.page.label.tongsotien"/>
              </td>
              <td width="23%" align="left">
                <input  id="itong_st" class="fieldTextRightCode"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"                             
                           style="WIDTH: 98%;">
                </input>
                <html:hidden property="tong_st" styleId="tong_st"/>
              </td>
              <td width="60%" align="right">
                <span id="tao">
                <button id="btn_taoBK" style="width:150px;" accesskey="t" type="button" class="ButtonCommon" onclick="taoBKQTTQ()"  value="taobk">
                 <fmt:message key="QTToanQuoc.page.taobk.button.taobk"/>
                </button>
                </span>
              
                <span id="xem">
                  <button id="btn_xemBK" style="width:200px;" onclick="forwardBK();" accesskey="x" type="button" class="ButtonCommon"   value="xembk">
                 <fmt:message key="QTToanQuoc.page.taobk.button.xembk"/>
                </button>
                </span>
              </td>
            </tr>
          </table>
    
    </html:form>
    <!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm"
     title='<fmt:message key="QTToanQuoc.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>