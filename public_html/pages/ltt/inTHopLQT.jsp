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

<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************  
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="listTHopLQT.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Bảng kê tổng hợp LQT</span>
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
                  <tr>
                  <td align="right" bordercolor="#e1e1e1">
                      <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                    </td>
                    <td >
                      <html:select property="ma_dv" styleId="ngan_hang" onchange="nhangval()"
                               style="width: 80%;font-size:12px"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                        <html:option value="701" >-----Toàn quốc-----</html:option>
                        <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                      </html:select>
                    </td> 
                    <td align="right">Loại tiền</td>
                    <td>
                      <html:select property="loai_tien" styleId="loai_tien" style="width: 30%;font-size:12px"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                          <html:option value="VND">VND</html:option>
                          <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
                      </html:select>
                    </td>
                </tr>
                <tr>               
                  <td   align="right">
                     Từ ngày
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
                    
                    <td   align="right">
                     Đến ngày
                  </td>
                  <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                          onkeypress="return numbersonly(this,event,true) "
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
                         tabindex="107" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="den_ngay_btn"
                       style="vertical-align:middle;width:20"/>   
                      <script type="text/javascript">
                        Calendar.setup( {
                            inputField : "den_ngay", // id of the input field
                            ifFormat : "%d/%m/%Y", // the date format
                            button : "den_ngay_btn"// id of the button
                        });
                      </script> &nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                  <td colspan="4" align="right">
                    <button type="button" onclick="check('print');" class="ButtonCommon" accesskey="i" >
                            <span class="sortKey">I</span>n
                    </button> 
                  </td>
                </tr>
                
             </table>
           </div>
          </fieldset>
       </td>
      </tr>
      <tr>
 </table>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
  function check(type) {  
     if (type == 'print') {
         tu_ngay= jQuery('#tu_ngay').val();
         den_ngay= jQuery('#den_ngay').val();
      if(tu_ngay.trim()==null || ""==tu_ngay.trim()){
         alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;ng tin ng&#224;y quy&#7871;t to&#225;n'));
         jQuery('#tu_ngay').focus();
       return false;
      }else if(den_ngay.trim()==null || ""==den_ngay.trim()){
         alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;ng tin ng&#224;y quy&#7871;t to&#225;n'));
         jQuery('#den_ngay').focus();
       return false;
      }else{
        f.action = 'printTHopLQT.do'; 
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
      }
      f.submit();
  }
</script>