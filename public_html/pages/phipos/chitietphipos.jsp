<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"u href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>



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
  
  $(document).on("pageload",function(){
    alert("pageload event fired!");
  });
</script>
<%
int rowBegin = 0;
%>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="detailBKePhiPOSAction.do" method="post" >
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Chi bảng kê tính phí POS</span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" bordercolor="#999999" width="100%">
      <tr>
        <td align="right">
          <button type="button" onclick="submitAction('print');" class="ButtonCommon" accesskey="i" >
                      <span class="sortKey">I</span>n
          </button>
          <button type="button" onclick="submitAction('exit');" class="ButtonCommon" accesskey="t" >
                      <span class="sortKey">T</span>hoát
          </button>
          &nbsp;
        </td>
      </tr>
      <tr>
        <td>
          <fieldset>
            <legend><font color="Blue"></font></legend>
            <fmt:setLocale value="vi_VI"/>
            <table class="data-grid" id="data-grid" border="1" cellspacing="0" cellpadding="0" width="100%">
              
              <tr>
                <th width="6%">STT</th>
                <th width="15%">Ngày giao dịch</th>
                <th width="10%">Tổng số chứng từ</th>
                <th width="15%">Tổng số tiền</th>
                <th width="10%">Mức phí</th>                
                <th width="15%">Phí</th>
                <th width="6%"></th>
              </tr>
              <html:hidden property="ma_nh_2"/>   
              <html:hidden property="ma_kb_2"/>
              <html:hidden property="mt_id"/>
              <html:hidden property="ngay_gd"/>
              <logic:notEmpty name="listPhiPOSCTiet">
                <logic:iterate id="item" name="listPhiPOSCTiet" indexId="stt">
                  <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                    <td align="center"><%=stt+1+rowBegin%></td>
                    <td align="center">
                      <bean:write name="item" property="ngay_gd" />
                    </td>
                    <td align="right">
                      <bean:write name="item" property="tong_so_ct" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                        <bean:write name="item" property="tong_so_tien" />
                      </fmt:formatNumber> 
                    </td>
                    <td align="right">
                      <bean:write name="item" property="muc_phi" />
                    </td>                   
                    <td align="right">                      
                      <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                        <bean:write name="item" property="phi" />
                      </fmt:formatNumber>                      
                    </td>                  
                    <td align="center">
                      <button onclick="showCTietGD('<bean:write name="item" property="ngay_gd" />');">Chi tiết</button>
                    </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
            </table>
          </fieldset>
        </td>
      </tr>
    </table>
    <html:hidden property="pageNumber" styleId="pageNumber"/>
  </html:form>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function submitAction(type) {
    var f = document.forms[0];
    if(type == 'print'){
      f.action = 'printBKePhiPOSAction.do';
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
              newDialog = window.open('', '_form', params);  
              f.target='_form';
    }else if(type == 'exit'){
      f.action = 'listBKePhiPOSAction.do';
    }    
    f.submit();
  }
  function showCTietGD(ngay_gd) {
    var f = document.forms[0];
    f.ngay_gd.value = ngay_gd;
    f.action = 'detailGDPOSAction.do';
    f.submit();    
  }
</script>