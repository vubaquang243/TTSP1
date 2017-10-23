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
  String duyet = request.getAttribute("duyet")==null?"":request.getAttribute("duyet").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    
    
    
    var strRowSelected="<%=strRowSelected%>";
    var duyet="<%=duyet%>";
    var viewAcc="<%=viewAcc%>";

      
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
    if(viewAcc!=null && '' != viewAcc){
      jQuery("#data-grid").attr("disabled", true);
    }
    if(duyet != null && ""!=duyet){
    alert(GetUnicode('Duy&#7879;t x&#225;c nh&#7853;n s&#7889; li&#7879;u thanh to&#225;n th&#224;nh c&#244;ng'));
    }
  });
  //manhnv-24/06/2013
  function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
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
    //manhnv-24/06/2013
</script>
<div class="app_error">
  <html:errors/>
  <!--manhnv-24/06/2013-->
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
  <!--manhnv-24/06/2013-->
</div>
<div class="box_common_conten">
  <html:form action="DuyetXNDChieu1Action.do" method="post" >
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
            <span class=title2> <fmt:message key="doi_chieu.page.title.duyetdc1"/></span>
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
        <legend><font color="Blue">Danh s&#225;ch &#273;i&#7879;n x&#225;c nh&#7853;n</font></legend>
        <div  style="height:155px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="0"v>
                 <tr>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                  <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                  <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.lan"/></td>
                 <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" width="25%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>
                 </tr>
                 <logic:empty name="colDChieu">
                  <tr>
                    <td colspan="6">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <input type="hidden" id="tthai_0" value="02" />
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                      id="row_qt_<bean:write name="index"/>"
                      onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataDuyetXNDC('DuyetXNDChieu1Action.do','<bean:write name="UDlist" property="id"/>','row_qt_<bean:write name="index"/>');">
                   <td align="center" width="10%" >
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <td align="center" width="10%" >  
                    <input type="text" name="receive_bank" style="border:0;font-size:10px;width:90%" align="center" value="<bean:write name="UDlist" property="receive_bank"/>"/>                   
                    <input type="hidden" name="bkid" id="bkid_<bean:write name="index"/>" value="<bean:write name="UDlist" property="bk_id"/>" />
                   </td>
                   <td align="center" width="10%" >
                    <bean:write name="UDlist" property="lan_dc"/>                    
                   </td>
                   <td align="center" id="td_qt_<bean:write name="index"/>" width="20%" >
                   <input name="row_item" id="col_qt_<bean:write name="index"/>" 
                      type="text" style="border:0px;font-size:10px;" onkeydown="arrowUpDownXNDC(event)"
                      value="<bean:write name="UDlist" property="id"/>" 
                      readonly="true"/>
                      <input type="hidden" name="tthai" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
                   </td>        
                  <td align="center" width="35%" >
                   <logic:equal name="UDlist" property="trang_thai" value="01">
                      <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                   </logic:equal>
                   <logic:equal name="UDlist" property="trang_thai" value="02">
                        <fmt:message key="doi_chieu.page.lable.duyet.02"/> 
                   </logic:equal>
                   <logic:equal name="UDlist" property="trang_thai" value="04">
                      <fmt:message key="doi_chieu.page.lable.duyet.04"/>
                   </logic:equal>
                   </td>
                   <td align="center" width="15%" >
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
       <td width="40%">
         <fieldset>
            <legend><font color="Blue">T&#7893;ng h&#7907;p b&#7843;ng k&#234;</font> </legend>
            <div style="height:155px;">
              <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
              <logic:empty name="colTHBKDC">
                <tr>
                  <td width="46%">
                    -<fmt:message key="doi_chieu.page.lable.duyet.dlieuthu"/>
                  </td>
                  <td>
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                </tr>
                <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.tcong"/></td>
               <td><input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>           
                  </td>
             </tr>
             <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.dtu"/></td>
               <td>
               <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
               </td>
             </tr>
                <tr>
                  <td>
                    -<fmt:message key="doi_chieu.page.lable.duyet.dlieuchi"/>
                  </td>
                  <td>
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    &nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.duyet.thucong"/>
                  </td>
                  <td>
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    &nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.duyet.dientu"/>
                  </td>
                  <td>
                    <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                  </td>
                </tr>
              </logic:empty>
              <logic:notEmpty name="colTHBKDC">
                <logic:iterate id="items" name="colTHBKDC">
                <tr>
                  <td  width="46%">
                    -<fmt:message key="doi_chieu.page.lable.duyet.dlieuthu"/>
                  </td>
                  <td>
                    <input type="text" name="sotien_thu" class="fieldTextRight" value="<bean:write name="items" property="sotien_thu"  format="###,###"/>" readonly="true"/>
                  </td>
                </tr>
                <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.tcong"/></td>
               <td> 
               <!--<input type="text" name="so_tien_thu_tcong" class="fieldTextRight" value="<bean:write name="items" property="so_tien_thu_tcong"  format="###,###"/>" readonly="true"/>            -->
               &nbsp;
                  </td>
             </tr>
             <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.dtu"/></td>
               <td><input type="text" name="so_tien_thu_dtu" class="fieldTextRight" value="<bean:write name="items" property="so_tien_thu_dtu"  format="###,###"/>" readonly="true"/>
             </tr>
                <tr>
                  <td>
                    -<fmt:message key="doi_chieu.page.lable.duyet.dlieuchi"/>
                  </td>
                  <td>
                    <input type="text" name="sotien_chi" class="fieldTextRight" value="<bean:write name="items" property="sotien_chi"  format="###,###"/>" readonly="true"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    &nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.duyet.thucong"/>
                  </td>
                  <td>
                    <!--<input type="text" name="sotien_tcong" class="fieldTextRight" value="<bean:write name="items" property="sotien_tcong"  format="###,###"/>" readonly="true"/>-->
                    &nbsp;
                  </td>
                </tr>
                <tr>
                  <td>
                    &nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.duyet.dientu"/>
                  </td>
                  <td>
                    <input type="text" name="sotien_dtu" class="fieldTextRight" value="<bean:write name="items" property="sotien_dtu" format="###,###"/>" readonly="true"/>
                  </td>
                </tr>
                </logic:iterate>
              </logic:notEmpty>
              </table>
            </div>
          </fieldset>
          </td>     
          </tr>
          <tr><td><br/></td></tr>
          <tr>
          <td colspan="2">
          <fieldset>
            <legend><font color="Blue"> T&#7893;ng h&#7907;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u </font></legend>
            <div>
              <table width="80%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 <logic:empty name="colTHBKDC">
                   <!-- <tr>
                      <td  width="60%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.dukbnn"/>
                      </td>
                      <td>
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        -<fmt:message key="doi_chieu.page.lable.duyet.chenhlech"/>
                      </td>
                      <td>
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                    </tr>-->
                    <tr>
                        <td width="25%"><fmt:message key="doi_chieu.page.lable.duyet.ttsp"/></td>
                        <td width="25%">
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td><td></td><td></td>
                    </tr>
                    <tr>
                      <td width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.lttthua"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                      <td width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.dtsthua"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.lttthieu"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                      <td width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.dtsthieu"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/>
                      </td>
                    </tr>
                  </logic:empty>
                  <logic:notEmpty name="colTHBKDC">
                  <logic:iterate id="items" name="colTHBKDC">
                   <!-- <tr>
                      <td   width="60%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.dukbnn"/>
                      </td>
                      <td>
                        <input type="text" name="sodu_kbnn" class="fieldTextRight" value="<bean:write name="items" property="sodu_kbnn" format="##,###.##"/>" readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        -<fmt:message key="doi_chieu.page.lable.duyet.chenhlech"/>
                      </td>
                      <td>
                        <input type="text" name="chenh_lech" style="color:red;FONT-WEIGHT: bold" class="fieldTextRight" value="<bean:write name="items" property="chenh_lech" format="##,###.##"/>" readonly="true"/>
                      </td>
                    </tr> -->
                    <tr>
                      <td  width="25%"><fmt:message key="doi_chieu.page.lable.duyet.ttsp"/></td>
                      <td width="25%">
                        <!-- chua doi chieu-->
                        <logic:equal name="items" property="ket_qua" value="0">
                           <input type="text" style="width:98%;color:blue;FONT-WEIGHT: bold" name="ket_qua" id="ket_qua"  class="fieldText" value="<fmt:message key="doi_chieu.page.lable.pht.0"/>" readonly="true"/>
                        </logic:equal>
                        <logic:equal name="items" property="ket_qua" value="01" >
                           <input type="text" style="width:98%;color:red;FONT-WEIGHT: bold" name="ket_qua" id="ket_qua"  class="fieldText" value="<fmt:message key="doi_chieu.page.lable.pht.1"/>" readonly="true"/>
                        </logic:equal>
                            
                      </td><td></td><td></td>
                    </tr>
                    <tr>
                      <td  width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.lttthua"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="ltt_kb_thua" id="ltt_kb_thua" class="fieldTextRight" value="<bean:write name="items" property="ltt_kb_thua" format="##,###.##"/>" readonly="true"/>
                      </td>
                      <td width="25%">
                        -<fmt:message key="doi_chieu.page.lable.duyet.dtsthua"/>
                      </td>
                      <td width="25%">
                        <input type="text" name="dts_kb_thua" id="dts_kb_thua" class="fieldTextRight" value="<bean:write name="items" property="dts_kb_thua" format="##,###.##"/>" readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        -<fmt:message key="doi_chieu.page.lable.duyet.lttthieu"/>
                      </td>
                      <td>
                        <input type="text" name="ltt_kb_thieu" id="ltt_kb_thieu" class="fieldTextRight" value="<bean:write name="items" property="ltt_kb_thieu" format="##,###.##"/>" readonly="true"/>
                      </td>
                      <td>
                        -<fmt:message key="doi_chieu.page.lable.duyet.dtsthieu"/>
                      </td>
                      <td>
                        <input type="text" name="dts_kb_thieu" id="dts_kb_thieu" class="fieldTextRight" value="<bean:write name="items" property="dts_kb_thieu" format="##,###.##"/>" readonly="true"/>
                      </td>
                    </tr>
                  </logic:iterate>
                  </logic:notEmpty>
              </table>
            </div>
          </fieldset>
       </td>
      </tr>
      <tr>
        <td colspan="7">
          <%if(request.getAttribute("huy") != null){
          %>
          
            <font style="color:blue;FONT-WEIGHT: bold" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.huytcong"/>
          </font>
          <%}%>
        </td>
      </tr>
      <tr>
        <td colspan="7">
          <fieldset>
            <legend><font color="Blue"><fmt:message key="doi_chieu.page.lable.duyet.ketqua"/></font></legend>
            <div class="tabber" id="mytabber1" >
        <div class="tabbertab" style="height:150px;overflow-y: scroll;"><h2>L&#7879;nh thanh to&#225;n</h2>           
          <table width="100%" cellspacing="0" cellpadding="1" 
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
              <tr>
              <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.ltt"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.ngaytnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                        <fmt:message key="doi_chieu.page.lable.nhkbchuyen"/>
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.nhkbnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.tennhkbnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.sotchieu"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    <fmt:message key="doi_chieu.page.lable.stien"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    <fmt:message key="doi_chieu.page.lable.tthai"/>
                  </div>
                </th>
                <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Lý do chênh lệch
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Chi tiết chênh lệch
                  </div>
                </th>                
                <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                
                <!--<th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      G&#7917;i NH
                    </div>
                  </th>-->
                  <!--<th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Duy&#7879;t l&#7841;i
                    </div>
                  </th>-->
              </tr>
              <logic:empty name="colKQDCCT">
                <tr>
              <td colspan="9" style="color:red"><b>Kh&#244;ng c&#243; th&#244;ng tin</b></td>
              </tr>
              </logic:empty>
                      <logic:notEmpty name="colKQDCCT">
           <tr>
              <td colspan="9"><b>L&#7879;nh thanh to&#225;n &#273;i</b></td>
              </tr>
          <logic:iterate id="items" name="colKQDCCT" indexId="stt">
          <logic:equal name="items" property="di_den" value="DI" > <!-- lenh thanh toan di -->
            <logic:equal name="items" property="ltt_dts" value="LTT" > <!-- lenh thanh toan di -->

              <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                   <bean:write name="items" property="mt_id"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="send_date"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="send_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="ten_send_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="receive_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="ten"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="f20"/>
                </td>
                <td  align="right">
                  <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                  <bean:write name="items" property="f32as3"/>
                  </fmt:formatNumber>
                </td>
                <td align="center">
                  <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                </td>
                <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                <td align="left">                
                  <bean:write name="items" property="ldo_clech"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="ctiet_clech"/>
                </td>
                <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                
                 <!--<td align="center">
                    <a href="<html:rewrite page="/sendBankAction.do"/>?mt_id=<bean:write name="items" property="mt_id"/>&receive_bank=<bean:write name="items" property="receive_bank"/>&send_bank=<bean:write name="items" property="send_bank"/>">G&#7917;i</a> 
                  </td>-->
                  
              </tr> 
             </logic:equal>
             </logic:equal>
             </logic:iterate>
             <tr><td colspan="9">&nbsp;</td></tr>
             </logic:notEmpty>
             
             
             <logic:notEmpty name="colKQDCCT">
             <tr> 
              <td colspan="9"><b>L&#7879;nh thanh to&#225;n &#273;&#7871;n</b></td>
              </tr>
              <logic:iterate id="items" name="colKQDCCT" indexId="stt">
              <logic:equal name="items" property="di_den" value="DEN" > <!-- lenh thanh toan -->
              <logic:equal name="items" property="ltt_dts" value="LTT" ><!-- lenh thanh toan di -->
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ct"/>
                  </td>           
                  <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f20"/>
                  </td>
                  <td align="right">
                    <fmt:setLocale value="vi_VI"/>
                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                     <bean:write name="items" property="f32as3"/>
                    </fmt:formatNumber>
                     
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <td align="left">                
                    <bean:write name="items" property="ldo_clech"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ctiet_clech"/>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr>  
            </logic:equal>
            </logic:equal>
          </logic:iterate>
          <tr><td colspan="9">&nbsp;</td></tr>
          </logic:notEmpty>
          
         </table>              
         </div>
         <div class="tabbertab" style="height:150px;overflow-y: scroll;">
          <h2>&#272;i&#7879;n tra so&#225;t</h2>
          <table width="100%" cellspacing="0" cellpadding="1" 
                   bordercolor="#e1e1e1" border="1" align="center"
                   style="BORDER-COLLAPSE: collapse">
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.sdts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.ngayts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                          <fmt:message key="doi_chieu.page.lable.dvts"/>
                      </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                      </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.dvnts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.tennhkbnhan"/>
                    </div>
                  </th>

                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.loaits"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      <fmt:message key="doi_chieu.page.lable.lttts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      <fmt:message key="doi_chieu.page.lable.tthai"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Duy&#7879;t l&#7841;i
                    </div>
                    </th>
                    <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                   <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Lý do chênh lệch
                    </div>
                  </th>
                   <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Chi tiết chênh lệch
                    </div>
                  </th> 
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr>
               
          <logic:notEmpty name="colKQDCCT">
           <tr>
              <td colspan="9"><b>&#272;i&#7879;n tra so&#225;t &#273;i</b></td>
              </tr>
            <logic:iterate id="items" name="colKQDCCT" indexId="stt">
            <logic:equal name="items" property="di_den" value="DI" > 
            <logic:equal name="items" property="ltt_dts" value="DTS" >
            
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ts"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>

                  <td align="center">
                    <bean:write name="items" property="mt_type"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f21"/>
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <td align="left">                
                    <bean:write name="items" property="ldo_clech"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ctiet_clech"/>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                  
                  <!--<td align="center">
                  <logic:notEqual value="01" property="tthai_duyet" name="items">
                    <a href="<html:rewrite page="/duyetLaiAction.do"/>?mt_id=<bean:write name="items" property="mt_id"/>&yype=DTS">Duy&#7879;t l&#7841;i</a> 
                  </logic:notEqual>
                  <logic:equal value="01" property="tthai_duyet" name="items">
                    Duy&#7879;t l&#7841;i
                  </logic:equal>
                  </td>-->
                </tr>  
                </logic:equal>
        </logic:equal>
        </logic:iterate>
        <tr><td colspan="9">&nbsp;</td></tr>
        </logic:notEmpty>
            
             
          <logic:notEmpty name="colKQDCCT">
          <tr> 
              <td colspan="9"><b>&#272;i&#7879;n tra so&#225;t &#273;&#7871;n</b></td>
              </tr>
            <logic:iterate id="items" name="colKQDCCT" indexId="stt">
            <logic:equal name="items" property="di_den" value="DEN" > 
            <logic:equal name="items" property="ltt_dts" value="DTS" > 
            
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ts"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="mt_type"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f21"/>
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <td align="left">                
                    <bean:write name="items" property="ldo_clech"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ctiet_clech"/>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr> 
                </logic:equal>
        </logic:equal>
        </logic:iterate>
        <tr><td colspan="9">&nbsp;</td></tr>
        </logic:notEmpty>        
        </table>
           </div>       
          </div>
        </fieldset>
      </td>
    </tr>
    <tr>
    <td align="right" colspan="7">
      <logic:notEmpty name="colTHBKDC">
        <logic:iterate id="items" name="colTHBKDC" indexId="stt">
          <logic:equal name="items" property="trang_thai_kqdc" value="01" >  
            <button type="button" onclick="check('del','<bean:write name="items" property="id"/>')" accesskey="h" id="bt" style="width:60px" name="btton">
              <span class="sortKey">H</span>&#7911;y
            </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            <button type="button" onclick="check('duyet','<bean:write name="items" property="id"/>')" accesskey="d" id="bt" style="width:60px" name="btton">
              <span class="sortKey">D</span>uy&#7879;t
            </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
           </logic:equal>
          </logic:iterate>
        </logic:notEmpty>
        
            <button  type="button" onclick="check('view','')" accesskey="i" id="bt_ctiet">
              Ch<span class="sortKey">i</span> ti&#7871;t b&#7843;ng k&#234;
            </button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button  type="button" onclick="check('close')" accesskey="o" style="width:60px">
              Th<span class="sortKey">o</span>&#225;t
            </button>
        </td>
      </tr>
    </table>    
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
  var strRowSelected="<%=strRowSelected%>";
  function check(type,id) {        
     if (type == 'del') {
     stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      bk_id=document.getElementById("bkid_"+sttNext).value;
        f.action = 'DuyetXNDChieu1Action.do?btton=huy&id='+id+'&bk_id='+bk_id;          
      }
     if (type == 'view') {
        if(strRowSelected!=null && '' != strRowSelected){
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      bk_id=document.getElementById("bkid_"+sttNext).value;
         f.action = 'CTietBKeDChieu1.do?type=kq&bk_id='+bk_id;
        }
      }
     if (type == 'duyet') {
     //manhnv-24/06/2013
        if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
      //manhnv-24/06/2013
        f.action = 'DuyetXNDChieu1Action.do?btton=duyet&id='+id;          
      }
      if (type == 'print') {
        f.action = 'PrintXNDChieu1Action.do?id='+id;          
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
  }
  
  disBt();
  function disBt(){
  if(strRowSelected!=null && '' != strRowSelected){
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      tthai_dxn=document.getElementById("tthai_"+sttNext).value;
      if(tthai_dxn=='01'){
        document.getElementById("bt").disabled=false;
      }else if (tthai_dxn==null||''==tthai_dxn){
          document.getElementById("bt_ctiet").disabled=true;
      } else {
        document.getElementById("bt").disabled=true;
      }
  }else{
    document.getElementById("bt").disabled=true;
  }
  }
  
</script>
