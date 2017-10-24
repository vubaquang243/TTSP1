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

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="detailGDPOSAction.do" method="post" >
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Bảng kê giao dich POS</span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" bordercolor="#999999" width="100%">
      <tr>
        <td>
          <html:hidden property="mt_id"/> 
          <html:hidden property="ma_kb_2"/>
          <html:hidden property="ma_nh_2"/>
          <fieldset>
            <legend><font color="blue"></font></legend>
            <div>
            <table class="data-grid" id="data-grid" style="width:100%" border="0" cellspacing="0" cellpadding="1" >
             
              
              <tr>
                <td colspan="5" align="right">                 
                   <button type="button" onclick="submitAction('exit');" class="ButtonCommon" accesskey="o" >
                    Th<span class="sortKey">o</span>át
                  </button>
                </td>
              </tr>
            </table>
            </div>
          </fieldset>
        </td>
      </tr>
      <% com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
         int rowBegin = (pagingBean.getCurrentPage() -1) * 15; %>
      <tr>
        <td>
          <fieldset>
            <legend><font color="Blue">Chi tiết giao dịch trong ngày</font></legend>
            <fmt:setLocale value="vi_VI"/>
            <table class="data-grid" id="data-grid" border="1" cellspacing="0" cellpadding="0" width="100%">
              <tr>
                <th width="4%">STT</th>
                <th width="10%">Ngày GD</th>
                <th width="10%">Mã GD</th>                
                <th width="6%">Mã chuẩn chi</th>
                <th width="6%">Mã máy POS</th>
                <th width="6%">Số thẻ</th>
                <th width="6%">TCTD cấp thẻ</th>
                <th width="10%">Số tiền giao dịch</th>
                <th width="6%">mức phí</th>
                <th width="10%">Phí</th>
                <th width="10%">số tham chiếu</th>
                <th width="6%">số bảng kê</th>
              </tr>
          
              <logic:notEmpty name="listGDPOSDetail">
                <logic:iterate id="item" name="listGDPOSDetail" indexId="stt">
                  <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                    <td align="center"><%=stt+1+rowBegin%></td>
                    <td align="center">
                      <bean:write name="item" property="ngay_gd" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ma_gd" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ma_cc" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ma_pos" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="so_the" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="tctd" />
                    </td>
                    <td align="right">                      
                      <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                        <bean:write name="item" property="so_tien" />
                      </fmt:formatNumber>                      
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                        <bean:write name="item" property="muc_phi" />
                      </fmt:formatNumber>                      
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                        <bean:write name="item" property="phi_tra" />
                      </fmt:formatNumber>                      
                    </td>
                    <td align="center">                      
                      <bean:write name="item" property="so_thamchieu" />
                    </td>
                    <td align="center">                                            
                      <bean:write name="item" property="mt_id" />
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
    if(type == 'exit'){      
      f.action = 'detailBKePhiPOSAction.do';
    }
    f.submit();
  } 
</script>