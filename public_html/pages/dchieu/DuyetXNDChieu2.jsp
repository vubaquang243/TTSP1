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
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String viewAcc = request.getAttribute("view")==null?"":request.getAttribute("view").toString();
  String tcong = request.getAttribute("duyettcong")==null?"":request.getAttribute("duyettcong").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var viewAcc="<%=viewAcc%>";
    var tcong="<%=tcong%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
    if(viewAcc!=null && '' != viewAcc){
      jQuery("#row_qt_0").attr("disabled", true);
    }
    if(tcong!=null && '' != tcong){
      alert(GetUnicode('Duy&#7879;t x&#225;c nh&#7853;n &#273;&#7889;i chi&#7871;u quy&#7871;t to&#225;n th&#224;nh c&#244;ng'));
    }
  });
  //<!--manhnv-24/06/2013-->
  function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();  
            
           // 20171120 thuongdt bo sung canh bao han su dung CTS
             var strdomain = '<%=strdomain%>';
            var struser_name = '<%=struser_name%>';
            var strcheckcts = '<%=strcheckcts%>';           
            if(!checkCTSdate(cert,strdomain+'/'+struser_name,strcheckcts)){
             return false;
            }
            
            var serial = cert.Serial;
            jQuery("#certserial").val(serial);            
            var noi_dung_ky = jQuery("#noi_dung_ky").val();
            var sign = cert.Sign(noi_dung_ky);
            jQuery("#chuky_ktt").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
    //<!--manhnv-24/06/2013-->
</script>
<div class="app_error">
  <html:errors/>
  <!--manhnv-24/06/2013-->
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
  <!--manhnv-24/06/2013-->
  
    <% if(request.getAttribute("khopdung") != null){
    %>
      <font color="Red" dir="ltr">
      <fmt:message key="doi_chieu.page.lable.dxndung"/>
      </font>
    <%}else if(request.getAttribute("chenhlech") != null){
    %>
      <font color="Red" dir="ltr">
      <fmt:message key="doi_chieu.page.lable.dxnlech"/>
      </font>
    <%}%>
</div>
<div class="box_common_conten">
  <html:form action="DuyetXNDChieu2Action.do" method="post" >
   <!--manhnv-24/06/2013-->
  <html:hidden property="chuky_ktt" styleId="chuky_ktt" />
  <html:hidden property="certserial" styleId="certserial" />
  <html:hidden property="noi_dung_ky" styleId="noi_dung_ky" />
  <!--manhnv-24/06/2013-->
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.duyetdc2"/></span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" width="100%">
      <tr>
       <td width="45%">
        <fieldset>
        <legend>Danh s&#225;ch b&#7843;ng k&#234;</legend>
        <div  style="height:255px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="0" >
                 <tr>
                 <th align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></th>
                 <th align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></h>
                 <th align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sbke"/></th>            
                 <th align="center" width="30%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></th>
                 <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>
                 </tr>               
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                      id="row_qt_<bean:write name="index"/>" onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataDuyetDC2('loadXNDChieu2Action.do','<bean:write name="UDlist" property="receive_bank"/>','<bean:write name="UDlist" property="ngay_dc"/>','<bean:write name="UDlist" property="bk_id"/>','row_qt_<bean:write name="index"/>');">                  
                   
                   <td align="center" width="15%">
                   <input name="ngay_dc" id="col_qt_<bean:write name="index"/>" 
                      type="text" style="border:0px;font-size:10px;text-align:center;width:98%" 
                      value="<bean:write name="UDlist" property="ngay_dc"/>" 
                      readonly="true"/>                         
                   </td>
                   <td  align="center" id="td_qt_<bean:write name="index"/>" width="15%">
                   <input name="row_item" id="col_qt_<bean:write name="index"/>" 
                      type="text" style="border:0px;font-size:10px;width:98%" onkeydown="arrowUpDownXNDC(event)"
                      value="<bean:write name="UDlist" property="receive_bank"/>" 
                      readonly="true"/>
                     
                   </td>
                  <td align="left" width="20%">
                    <bean:write name="UDlist" property="bk_id"/>
                    <input type="hidden" name="bkid_hidden" id="bkid_<bean:write name="index"/>" value="<bean:write name="UDlist" property="bk_id"/>" />
                     <input type="hidden" name="kqid_hidden" id="kqid_<bean:write name="index"/>" value="<bean:write name="UDlist" property="id"/>" />
                     <input type="hidden" name="tthai_hidden" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
                     <input type="hidden" name="bank_hidden" id="bank_<bean:write name="index"/>" value="<bean:write name="UDlist" property="receive_bank"/>" />
                     <input type="hidden" name="sendbank_hidden" id="bank_<bean:write name="index"/>" value="<bean:write name="UDlist" property="send_bank"/>" />
                     <input type="hidden" name="kq_hidden" id="kq_<bean:write name="index"/>" value="<bean:write name="UDlist" property="ket_qua"/>" />
                     
                   </td>
                    
                   <td align="center" width="30%">
                      <logic:equal name="UDlist" property="trang_thai" value="00">
                           <fmt:message key="doi_chieu.page.lable.00"/>
                      </logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="">
                          <fmt:message key="doi_chieu.page.lable.dc1.99"/>
                      </logic:equal>
                      <logic:notEqual name="UDlist" property="trang_thai" value="00">
                          <logic:equal name="UDlist" property="ket_qua" value="1">
                              <fmt:message key="doi_chieu.page.lable.bk01"/> -
                          </logic:equal>
                          <logic:equal name="UDlist" property="ket_qua" value="0">
                              <fmt:message key="doi_chieu.page.lable.bk02"/> -
                        </logic:equal>
                      </logic:notEqual>      
                      <logic:equal name="UDlist" property="trang_thai" value="01">
                          <fmt:message key="doi_chieu.page.lable.duyet.01"/> 
                      </logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="02">
                          <fmt:message key="doi_chieu.page.lable.duyet.02"/> 
                      </logic:equal>                                
                   </td>
                   <td align="center" width="20%" >
                     <logic:equal name="UDlist" property="tthai_ttin" value="01">
                        &#272;&#227; g&#7917;i NH
                     </logic:equal>
                     <logic:equal name="UDlist" property="tthai_ttin" value="02">
                          NH nh&#7853;n th&#224;nh c&#244;ng 
                     </logic:equal>
                     <logic:equal name="UDlist" property="tthai_ttin" value="03">
                        G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng
                     </logic:equal>
                   </td>
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
           </div>
        </fieldset>
       </td>
       <td width="55%">
         <fieldset>
            <legend>S&#7889; Kho b&#7841;c x&#225;c nh&#7853;n k&#7871;t chuy&#7875;n</legend>
            <div style="height:35px;">
              <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <logic:empty name="colTHBKDC">
                <tr>
                  <td align="center" width="25%">
                    -<fmt:message key="doi_chieu.page.label.dc2.kckbthu"/>
                  </td>
                  <td width="25%">
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                  <td align="center" width="25%">
                    -<fmt:message key="doi_chieu.page.label.dc2.kckbchi"/>
                  </td>
                  <td width="25%">
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                </tr>
                </logic:empty>
                <logic:notEmpty name="colTHBKDC">
                <logic:iterate id="items" name="colTHBKDC" indexId="index">          
                <tr>
                  <td align="center" width="25%">
                    -<fmt:message key="doi_chieu.page.label.dc2.kckbthu"/>
                  </td>
                  <td width="25%">
                  <input type="text" name="so_ketchuyen" class="fieldTextRight" value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_ketchuyen"/></fmt:formatNumber>" readonly="true"/>
                    
                    <html:hidden property="sodu_daungay" name="items"/>
                    <html:hidden property="so_ketchuyen" name="items"/>
                    <html:hidden property="ps_chi" name="items"/>
                    <html:hidden property="so_du" name="items"/>
                    <html:hidden property="ngay_dc" name="items"/>
                  </td>
                  <td align="center" width="25%">
                    -<fmt:message key="doi_chieu.page.label.dc2.kckbchi"/>
                  </td>
                  <td width="25%">
                    <input type="text" name="ps_chi" class="fieldTextRight" value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="ps_chi"/></fmt:formatNumber>" readonly="true"/> 
                  </td>
                </tr>
                </logic:iterate>
                </logic:notEmpty>               
              </table>
            </div>
          </fieldset>
          <br/>
          <%if(request.getAttribute("kocobangke") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.kocobke"/>
            </font>
        <%}%>
          <fieldset>
            <legend>S&#7889; Ng&#226;n h&#224;ng k&#7871;t chuy&#7875;n</legend>
            <div style="height:190px;overflow-y: scroll;">
              <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="1" align="center" id="abc"
                 style="BORDER-COLLAPSE: collapse">
                 <tr>
                   <th class="promptText" bgcolor="#f0f0f0"  style="width:20%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.sqtoan"/>
                    </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:5%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.lqtoan"/>
                    </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:15%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.ngaytt"/>
                    </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:15%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.ngayqt"/>
                    </div>
                   </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:22%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.chi"/>
                    </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:23%">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.label.dc2.thu"/>
                    </div>
                   </th>

                 </tr>
                 <logic:empty name="colThuChi">
                  <tr>
                    <td colspan="7">
                  <font color="red">Kh&#244;ng ph&#225;t sinh chi ti&#7871;t quy&#7871;t to&#225;n</font>
                </td>
                  </tr>
                 </logic:empty>
                 <logic:notEmpty name="colThuChi">
                  <logic:present name="colThuChi">
                  <logic:iterate id="items" name="colThuChi" indexId="stt">
                   <logic:equal name="items" property="loai_qtoan" value="Chi">
                   <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>     
                     <td align="center">
                      <bean:write name="items" property="qtoan_id"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="loai_qtoan"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_htoan"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_qtoan"/>                    
                     </td>
                     <td align="right">
                         
                     </td>
                     <td align="right">
                     <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                     <bean:write name="items" property="so_tien"/> 
                     </fmt:formatNumber>
                     </td>
                   </tr>
                   </logic:equal>
                   <logic:equal name="items" property="loai_qtoan" value="Thu">
                   <tr>     
                     <td align="center">
                      <bean:write name="items" property="qtoan_id"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="loai_qtoan"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_htoan"/>                    
                     </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_qtoan"/>                    
                     </td>
                     <td align="right">
                     <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                     <bean:write name="items" property="so_tien"/>      
                     </fmt:formatNumber>
                     </td>
                     <td align="right">
                                   
                     </td>
                   </tr>
                   </logic:equal>
                </logic:iterate>
                </logic:present>
                
               </logic:notEmpty>
               
                
                <logic:notEmpty name="colSum">
                  <logic:present name="colSum">
                  <logic:iterate id="items" name="colSum">
                  <tr>
                  <td colspan="4">
                  <b>T&#7893;ng s&#7889;  </b>
                   </td>
                <td align="right">
                      <b>
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write property="ket_chuyen_thu_nh" name="items"/>
                      </fmt:formatNumber>
                      </b>
                      <html:hidden property="ket_chuyen_thu_nh" styleId="ket_chuyen_thu_nh"  name="items" />
                </td>
                <td align="right">   
                      <b>
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write property="ket_chuyen_chi_nh" name="items"/>
                      </fmt:formatNumber>
                      </b>
                      <html:hidden property="ket_chuyen_chi_nh" styleId="ket_chuyen_chi_nh"  name="items" />
                </td>
                </tr>
                </logic:iterate>
                </logic:present>
              </logic:notEmpty>
               
            </table>
          </div>
        </fieldset>
        </td>
      </tr>
      <tr > 
      <td align="right" colspan="2">
          <button type="button" onclick="check('create')" style="width:50px" accesskey="t" id="bt">
            <span class="sortKey">D</span>uy&#7879;t
          </button>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <button  type="button" onclick="check('print')" style="width:50px" accesskey="i" id="inbt">
            <span class="sortKey">I</span>n
          </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button  type="button" onclick="check('printBK')" style="width:100px" accesskey="i" id="inbtbk">
            In <span class="sortKey">b</span>&#7843;ng k&#234;
          </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button  type="button" onclick="check('close')" style="width:50px" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
      </td>
    </tr>
    </table>"
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>

<script type="text/javascript">
  var f = document.forms[0];
  function check(type) { 
  var strRowSelected="<%=strRowSelected%>";
      if (type == 'create') {
        
         if(strRowSelected==null || ""==strRowSelected){
           alert('Kh&#244;ng c&#243; th&#244;ng tin c&#7847;n t&#7841;o &#272;XN');
           return false;
         }else if(strRowSelected!=null && ""!=strRowSelected){
            stt= strRowSelected.substr(7,5);
            sttNext=parseInt(stt);
            kq_id=document.getElementById("kqid_"+sttNext).value;
            bk_id=document.getElementById("bkid_"+sttNext).value;
            bank=document.getElementById("bank_"+sttNext).value;
        
        // manhnv 24/06/2013
        if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
        //manhnv-24/06/2013
        f.action = 'DuyetXNDChieu2Action.do?kq_id='+kq_id+'&bk_id='+bk_id+'&receive_bank='+bank;
        
        }
      } else if (type == 'print') {
        stt= strRowSelected.substr(7,5);
        sttNext=parseInt(stt);
        kq_id=document.getElementById("kqid_"+sttNext).value;
       kq=document.getElementById("kq_"+sttNext).value;
        f.action = 'printDuyetXNDChieu2Action.do?kq_id='+kq_id+'&ket_qua='+kq;
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
      } else if (type == 'printBK') {
        stt= strRowSelected.substr(7,5);
        sttNext=parseInt(stt);
        bk_id=document.getElementById("bkid_"+sttNext).value;
        
        f.action = 'printBKeDChieu2Action.do?bk_id='+bk_id;
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
      } else if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
  }
disBt();
  function disBt(){
    var strRowSelected="<%=strRowSelected%>";
      if(strRowSelected!=null && '' != strRowSelected){
          stt= strRowSelected.substr(7,5);
          sttNext=parseInt(stt);
          tthai_dxn=document.getElementById("tthai_"+sttNext).value;
            if(tthai_dxn=='01'){
                document.getElementById("bt").disabled=false;
           }else{
                document.getElementById("bt").disabled=true;
            }
      }
      else{
        document.getElementById("bt").disabled=true;
        document.getElementById("inbt").disabled=true;
      }
  }
</script>
