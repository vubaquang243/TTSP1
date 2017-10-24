<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<script type="text/javascript">
  jQuery.noConflict();
//  function check(type) {
//      var f = document.forms[0];
//      if (type == 'create') {
//          if (document.forms[0].ly_do_xn.disabled == false) {
//              if (""==document.forms[0].ly_do_xn.value.trim() || (document.forms[0].ly_do_xn.value == null)) {
//                  alert(GetUnicode('Nhập lý do khớp đúng'));                 
//                  return false;
//              }           
//          }
//          f.action = 'TaoDTSChenhLechAction.do';
//          f.submit();
//      }
//      else if (type == 'close') {
//          f.action = 'DChieu1Action.do';
//          f.submit();
//      }
//  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="TaoDXNDCTHop1Action.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="doi_chieu.page.title"/></span>
          </td>
          <td width="62">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
                 width="62" height="30"/>
          </td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
              width="20%">&nbsp;</td>
          <td width="4">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
                 width="4" height="30"/>
          </td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0"
           class="tableBound" bordercolor="#999999" width="100%">
        <tr>
          <td style="font-size:15pt; color:red; FONT-WEIGHT: bold"
              align="center" bordercolor="#e1e1e1" colspan="2">
            <fmt:message key="doi_chieu.page.lable.tbao1"/>
          </td>
        </tr>            
        <tr>
          <td style="font-size:15pt; color:red; FONT-WEIGHT: bold"
              align="center" bordercolor="#e1e1e1" colspan="2">
            <fmt:message key="doi_chieu.page.lable.tbao2"/>
          </td>
        </tr>            
        <tr>
          <td style="font-size:15pt; FONT-WEIGHT: bold" align="center"
              bordercolor="#e1e1e1" colspan="2">
            <br/>                 
            <fmt:message key="doi_chieu.page.lable.tbao3"/>
          </td>
        </tr>             
        <tr>
          <td>
            <fieldset >
              <legend>Tổng hợp bảng k&ecirc;</legend>
              <table width="40%" cellspacing="0" cellpadding="1"
                     bordercolor="#e1e1e1" border="0" align="center"
                     style="BORDER-COLLAPSE: collapse">
                <tr>
                  <td colspan="2">
                    <%if(request.getAttribute("DXN_dung_du") != null){
                     %>                       
                    <font color="Red" dir="ltr">
                      <fmt:message key="doi_chieu.page.lable.dxndung"/>
                    </font>
                     
                    <%}else if(request.getAttribute("DXN_lech_du") != null){
                     %>                       
                    <font color="Red" dir="ltr">
                      <fmt:message key="doi_chieu.page.lable.dxnlech"/>
                    </font>                       
                    <%}%>
                  </td>
                </tr>  
                <logic:notEmpty name="colTCong">
                  <logic:iterate id="items" name="colTCong">
                <tr>
                  <td width="30%">
                    <fmt:message key="doi_chieu.page.lable.xnthop.monthutcong"/>
                  </td>
                  <td width="30%">
                    <input type="text" name="mon_thu_tcong_kbnn" class="fieldTextRight" value=""/>
                    <html:hidden property="so_mon_thu_tcong" name="items"/>
                  </td>
                </tr>                     
                <tr>
                  <td width="30%">
                    <fmt:message key="doi_chieu.page.lable.xnthop.tienthutcong"/>
                  </td>
                  <td width="30%">
                    <input type="text" name="tien_thu_tcong_kbnn" class="fieldTextRight" value=""/>
                    <html:hidden property="so_tien_thu_tcong" name="items"/>
                  </td>
                </tr>
                <tr>
                  <td width="30%">
                    <fmt:message key="doi_chieu.page.lable.xnthop.monchitcong"/>
                  </td>
                  <td width="30%">
                    <input type="text" name="mon_chi_tcong_kbnn" class="fieldTextRight" value=""/>
                    <html:hidden property="somon_tcong" name="items"/>
                  </td>
                </tr>                     
                <tr>
                  <td width="30%">
                    <fmt:message key="doi_chieu.page.lable.xnthop.tienchitcong"/>
                  </td>
                  <td width="30%">
                    <input type="text" name="tien_chi_tcong_kbnn" class="fieldTextRight" value=""/>
                    <html:hidden property="sotien_tcong" name="items"/>
                  </td>
                </tr>
                </logic:iterate>
                </logic:notEmpty>
              </table>
            </fieldset>
          </td>
        </tr>            
        <tr>
          <td align="center" colspan="2">
            <button type="button" onclick="check('create');" accesskey="t">
              <span class="sortKey">T</span>ổng hợp thanh to&aacute;n
            </button>                
            <button type="button" onclick="check('close');"
                    class="ButtonCommon" accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </td>
        </tr>
    </table>
    <html:hidden property="id" styleId="h"/>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      if (type == 'create') {

          var tien_thu_tcong_bk= f.so_tien_thu_tcong.value;
          var mon_thu_tcong_bk= f.so_mon_thu_tcong.value;
          var tien_chi_tcong_bk= f.sotien_tcong.value;
          var mon_chi_tcong_bk= f.somon_tcong.value;
          var tien_thu_tcong_kbnn= f.tien_thu_tcong_kbnn.value;
          var mon_thu_tcong_kbnn= f.mon_thu_tcong_kbnn.value;
          var tien_chi_tcong_kbnn= f.tien_chi_tcong_kbnn.value;
          var mon_chi_tcong_kbnn= f.mon_chi_tcong_kbnn.value;
          
            if(tien_thu_tcong_bk !=tien_thu_tcong_kbnn){
                var ask1=  confirm('Có sự chênh lệch số tiền thu thủ công giữa NH và KBNN. Bạn có tiếp tục tạo ĐXN khớp?');
                if(ask1==false){
                 f.tien_thu_tcong_kbnn.focus();
                  return false;
                }
             }else if(mon_thu_tcong_bk != mon_thu_tcong_kbnn){
              var ask2=  confirm('Có sự chênh lệch số món thu thủ công giữa NH và KBNN. Bạn có tiếp tục tạo ĐXN khớp?');
                if(ask2==false) {
                f.mon_thu_tcong_kbnn.focus();
                  return false;
                }      
            }else if(tien_chi_tcong_bk!= tien_chi_tcong_kbnn){
              var ask3=  confirm('Có sự chênh lệch số tiền chi thủ công giữa NH và KBNN. Bạn có tiếp tục tạo ĐXN khớp?');
                if(ask3==false){ 
                  f.tien_chi_tcong_kbnn.focus();
                  return false;
                }       
            }else if(mon_chi_tcong_bk!= mon_chi_tcong_kbnn){
              var ask4=  confirm('Có sự chênh lệch số tiền chi thủ công giữa NH và KBNN. Bạn có tiếp tục tạo ĐXN khớp?');
                if(ask4==false){ 
                f.mon_chi_tcong_kbnn.focus();
                  return false;
                } 
            }
              f.action = 'excTaoDXNDCTHop1Action.do';
              f.submit();
      }
      else if (type == 'close') {
          f.action = 'XNDCTHop1Action.do';
          f.submit();
      }
  }
</script>

