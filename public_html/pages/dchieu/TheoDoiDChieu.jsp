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
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>

<%
String sum_dvi = request.getAttribute("sum_dvi")==null?"":request.getAttribute("sum_dvi").toString();

%>


<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="lstTheoDoiDChieuAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.theodoi"/></span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
   </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">
           <tr>
              <td align="left" bordercolor="#e1e1e1" width="35%">
                    <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>

                    <html:select property="ma_dv" styleId="ngan_hang"  onchange="nhangval()"
                             style="width: 180px;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value=""   >-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                </html:select> 
                   <span id="refresh" onclick="renew()"  title="Làm mới" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/gtk_refresh.png" /></span>
                </td>
                
                <td align="left" bordercolor="#e1e1e1"> Ng&#224;y tra c&#7913;u 
                  <html:text property="ngay_tdoi" styleId="ngay_tdoi" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_tdoi');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:60px"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_tdoi_btn"
                     style="vertical-align:middle;width:30"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay_tdoi", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_tdoi_btn"// id of the button
                      });
                    </script>
                  </td>
                  <td align="left" bordercolor="#e1e1e1" width="35%">
                  <button  type="button" onclick="nhangval()" accesskey="t" style="width:170px" >
                      <span class="sortKey">T</span>ra c&#7913;u
                    </button>
                </td>
           </tr>
      <tr>
         <td colspan="3">
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    Tr&#7841;ng th&#225;i BK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    Tr&#7841;ng th&#225;i TTSP
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    Tr&#7841;ng th&#225;i PHT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    TT &#273;i&#7879;n &#272;NQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    LQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    S&#7889; l&#432;&#7907;ng DV
                  </div>
                </th>
                
             <logic:notEmpty name="colSumTDoi">
              <logic:present name="colSumTDoi" >          
                <logic:iterate id="items" name="colSumTDoi" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"> 
                    <%=stt+1%>
                  </td>
                  <td>
                    <logic:equal value="CHUA_CO_BKE" property="bkid" name="items">
                      Chưa có bảng kê
                    </logic:equal>
                    <logic:equal value="DA_CO_BKE" property="bkid" name="items">
                      Đã có bảng kê
                    </logic:equal>
                  </td>
                  <td>
                    <logic:equal value="" property="ttsp" name="items">
                      Chưa đối chiếu TTSP
                    </logic:equal>
                    <logic:equal value="0" property="ttsp" name="items">
                      Khớp đúng
                    </logic:equal>
                    <logic:equal value="1" property="ttsp" name="items">
                      Chênh lệch
                    </logic:equal>                 
                  </td>
                  <td>
                    <logic:equal value="" property="pht" name="items">
                        Chưa đối chiếu PHT
                    </logic:equal>
                    <logic:equal value="0" property="pht" name="items">
                        Khớp đúng
                    </logic:equal>
                    <logic:equal value="1" property="pht" name="items">
                        Chênh lệch
                    </logic:equal>
                  </td>               
                  <td align="center">
                       <logic:equal value="" property="tthai_066" name="items">
                          Chưa tạo ĐXN QT
                        </logic:equal>
                        <logic:equal value="00" property="tthai_066" name="items">
                          Chưa tạo ĐNQT
                        </logic:equal>
                        <logic:equal value="01" property="tthai_066" name="items">
                          &#272;&#227; t&#7841;o ch&#7901; KS
                        </logic:equal>
                        <logic:equal value="02" property="tthai_066" name="items">
                          &#272;&#227; tạo ĐNQT
                      </logic:equal>
                      <logic:equal value="03" property="tthai_066" name="items">
                          &#272;&#227; XN ĐNQT
                      </logic:equal>
                  </td>
                  <td align="center">
                        <logic:equal value="" property="loaiqt" name="items">
                          Chưa quyết toán
                        </logic:equal>
                        <logic:equal value="01" property="loaiqt" name="items">
                          Điện tử
                        </logic:equal>
                        <logic:equal value="03" property="loaiqt" name="items">
                          Thủ công
                      </logic:equal>
                  </td>
                  <td align="center">                 
                    <a href="#" onclick="goDetail('<bean:write name="items" property="bkid"/>','<bean:write name="items" property="ttsp"/>','<bean:write name="items" property="pht"/>','<bean:write name="items" property="tthai_066"/>','<bean:write name="items" property="loaiqt"/>')">
                      <bean:write name="items" property="tong_dv"/>
                     </a>                  
                  </td>
                </tr>
                </logic:iterate>
                </logic:present>
                <tr>
                  <td colspan="6" align="right">
                   <b> Tổng số: &nbsp;</b>
                  </td>
                  <td align="center">
                    <b><%=sum_dvi%></b>
                  </td>
                </tr>
              </logic:notEmpty>        
              </table>
            </div>
         </td>
      </tr>
      
    </table> 

  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
 function nhangval() {      
      var ma_dv = jQuery('#ngan_hang').val();
      f.action = 'lstTheoDoiDChieuAction.do?ma_dv='+ma_dv; 
      f.submit();
  }
  function goDetail(bkid,ttsp,pht,tthai_066,loaiqt){
//    page="/lstCTietTheoDoiDChieuAction.do";
    var ma_dv = jQuery('#ngan_hang').val(); 
    var ngay_tdoi = jQuery('#ngay_tdoi').val();
      f.action = 'lstCTietTheoDoiDChieuAction.do?ma_dv='+ma_dv+'&bkid='+bkid+'&ttsp='+ttsp+'&pht='+pht+'&tthai_066='+tthai_066+'&loaiqt='+loaiqt+'&ngay_tdoi='+ngay_tdoi; 
      f.submit();
  }
  function renew(){
      var ma_dv = jQuery('#ngan_hang').val();
      if(''!=ma_dv && ma_dv!=null){
      f.action = 'lstTheoDoiDChieuAction.do?ma_dv='+ma_dv; 
      }else if(''==ma_dv || ma_dv==null){
      f.action = 'lstTheoDoiDChieuAction.do'; 
      }
      f.submit();
  }
</script>
