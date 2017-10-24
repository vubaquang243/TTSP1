
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
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
%>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="lstQLyDChieuAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.qldc"/></span>
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
        <td align="center">
          <table  class="data-grid" id="data-grid" 
                                              border="0"
                                             cellspacing="6" cellpadding="6"                                  
                                             width="70%">
                <tr>
                  <td width="20%">
                    <fmt:message key="doi_chieu.page.lable.qldc.kb"/>
                  </td>
                  <td width="25%">
                    <html:select property="nhkb_tinh" styleId="nhkb_tinh" onchange="nhkb_tinhval()"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                            
                                <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                          
                        
                        <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>
                        
                    </html:select>
                   </td>
                  <td width="20%">
                    <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                  </td>
                  <td width="30%">
                    <html:select property="nhkb_nhan" styleId="ngan_hang" onchange="nhangval()"
                             style="width: 100%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection name="dmucNH" value="ma_nh" label="ten_nh"/>
                </html:select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <fmt:message key="doi_chieu.page.lable.qldc.ngayqt"/>
                  </td>
                  <td align="left">
                  <html:text property="ngay_qt" styleId="ngay_qt" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_qt');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:35%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_qt_btn"
                     style="vertical-align:middle;width:30"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngay_qt", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_qt_btn"// id of the button
                      });
                    </script>
                  </td>
                   <td>
                    <fmt:message key="doi_chieu.page.lable.qldc.tthai"/>
                  </td>
                  <td align="left">
                    <html:select property="trang_thai" styleId="trang_thai" onchange="tthaival()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----T&#7845;t c&#7843;-----</html:option>
                      <html:option value="02">Kh&#7899;p &#273;&#250;ng</html:option>
                      <html:option value="01">Ch&#234;nh l&#7879;ch</html:option>
                      <html:option value="00">Ch&#432;a &#273;&#7889;i chi&#7871;u</html:option>
                  </html:select>
                  </td>
                </tr>
              <tr>
                <td  align="center" colspan="4">
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                      <span class="sortKey">T</span>&igrave;m kiếm
                  </button>
                  <button type="button" onclick="check('close');" class="ButtonCommon" accesskey="t" >
                      Th<span class="sortKey">o</span>&#225;t
                  </button>
                </td>
              </tr>
             </table>
        </td>
      </tr>
      <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      <tr>
       <td width="100%">
        <fieldset>
        <legend><font color="Blue">K&#7871;t q&#7911;a</font></legend>
          <table  class="data-grid" id="data-grid" 
                                              border="1"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%" style="BORDER-COLLAPSE: collapse">
                 <tr>
                 <td align="center" width="3%" class="ui-state-default ui-th-column">STT</td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.kb"/></td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.kbhuyen"/></td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.nhang"/></td>
                 <td align="center" width="8%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.ngayqt"/></td>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.ngaythien"/></td>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.tthai"/></td>
                 <td align="center" width="5%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.dm"/></td>    
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.qldc.gchu"/></td>
                 <td align="center" width="5%" class="ui-state-default ui-th-column">&nbsp;</td>
                 </tr>
                 <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
              
              <logic:notEmpty name="lstSearch">
              <logic:present name="lstSearch" >          
                <logic:iterate id="items" name="lstSearch" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"><%=stt+1+rowBegin%></td>
                  <td align="left">
                   <bean:write name="items" property="ten_kb_tinh"/>
                  </td>
                  <td align="left">
                  <bean:write name="items" property="ten_kb_huyen"/>
                  </td>
                  <td align="left">
                  <bean:write name="items" property="ten_ngan_hang"/>
                  </td>
                  <td align="center">
                  <bean:write name="items" property="ngay_dc"/>
                  </td>
                  <td align="center">
                  <bean:write name="items" property="ngay_thien_dc"/>
                  </td>
                  <td align="center">
                         <logic:equal value="" property="trang_thai_bk" name="items">
                           <span id="ctiet_chuadc" title="chi tiết" style="cursor:pointer;" onclick="fncChuaDChieu('<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="items" property="lan_dc"/>')">
                          <a href="#"><fmt:message key="doi_chieu.page.lable.00"/></a>
                        </span>
                        </logic:equal>
                        <logic:equal value="98" property="trang_thai_bk" name="items">
                           <span id="ctiet_chuadc" title="chi tiết" style="cursor:pointer;" onclick="fncChuaDChieu('<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="items" property="lan_dc"/>')">
                          <a href="#"><fmt:message key="doi_chieu.page.lable.00"/></a>
                        </span>
                        </logic:equal>
                        <logic:equal value="00" property="trang_thai_bk" name="items">
                        <span id="ctiet_chuadc" title="chi tiết" style="cursor:pointer;" onclick="fncChuaDChieu('<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="items" property="lan_dc"/>')">
                          <a href="#"><fmt:message key="doi_chieu.page.lable.00"/></a>
                        </span>
                        </logic:equal>
                         <logic:equal value="1" property="ket_qua" name="items">
                          <logic:equal value="02" property="trang_thai_kq" name="items">
                              <span id="search" title="chi tiết chênh lệch" style="cursor:pointer;" onclick="fncsearch('<bean:write name="items" property="ketchuyen_thu_kb"/>','<bean:write name="items" property="ketchuyen_chi_kb"/>','<bean:write name="items" property="ketchuyen_thu_nh"/>','<bean:write name="items" property="ketchuyen_chi_nh"/>')">
                               <a href="#"><fmt:message key="doi_chieu.page.lable.bk01"/></a>
                              </span>
                          </logic:equal>
                          <logic:notEqual value="02" property="trang_thai_kq" name="items">
                              <span id="ctiet_chuadc" title="chi tiết chênh lệch" style="cursor:pointer;" onclick="fncChuaDChieu('<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="items" property="lan_dc"/>')">
                          <a href="#"><fmt:message key="doi_chieu.page.lable.00"/></a>
                        </span>
                          </logic:notEqual>
                        </logic:equal>
                        <logic:equal value="0" property="ket_qua" name="items">
                          <logic:equal value="02" property="trang_thai_kq" name="items">
                            <fmt:message key="doi_chieu.page.lable.bk02"/>
                          </logic:equal>
                          <logic:notEqual value="02" property="trang_thai_kq" name="items">
                              <span id="ctiet_chuadc" title="chi tiết" style="cursor:pointer;" onclick="fncChuaDChieu('<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="items" property="lan_dc"/>')">
                              <a href="#"><fmt:message key="doi_chieu.page.lable.00"/></a>
                        </span>
                          </logic:notEqual>
                        </logic:equal>
                  </td>
                  <td align="center" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <logic:equal value="1" property="ket_qua" name="items">
                     <logic:equal value="1" property="tthai_dmo"  name="items">
                       <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_<bean:write name="items" property="kq_id"/>" readonly="readonly" value="&#272;&#243;ng"/>
                      </logic:equal>
                       <logic:equal value="0" property="tthai_dmo" name="items">
                        <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_<bean:write name="items" property="kq_id"/>" readonly="readonly" value="M&#7903;"/>
                      </logic:equal>
                      <logic:equal value="" property="tthai_dmo" name="items">
                        <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_<bean:write name="items" property="kq_id"/>" readonly="readonly" value="M&#7903;"/>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal value="" property="ket_qua" name="items">
                      <logic:equal value="98" property="trang_thai_bk"  name="items">
                       <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_chuadc_<bean:write name="stt"/>" readonly="readonly" value="&#272;&#243;ng"/>
                      </logic:equal>
                      <logic:equal value="" property="trang_thai_bk"  name="items">
                       <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_chuadc_<bean:write name="stt"/>" readonly="readonly" value="M&#7903;"/>
                      </logic:equal>
                      <logic:equal value="" property="trang_thai_bk" name="items">
                        <input type="text" style="border:0;width:90%"  class="fieldTextCenter"  id="dong_mo_id_chuadc_<bean:write name="stt"/>" readonly="readonly"/>
                      </logic:equal>
                    </logic:equal>
                  </td>
                  <td>
                  <logic:notEqual value="98" property="ket_qua" name="items">
                    <input type="text" style="border:0;"   class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'  id="ghi_chu_<bean:write name="items" property="kq_id"/>" readonly="readonly" value="<bean:write name="items" property="ghi_chu"/>"/>
                  </logic:notEqual>
                   <logic:equal value="98" property="trang_thai_bk" name="items">
                    <input type="text" style="border:0;"   class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'  id="ghi_chu_bk_<bean:write name="stt"/>" readonly="readonly" value="<bean:write name="items" property="ghi_chu_bk"/>"/>
                  </logic:equal>
                  <logic:equal value="" property="trang_thai_bk" name="items">
                    <input type="text" style="border:0;"   class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'  id="ghi_chu_bk_<bean:write name="stt"/>" readonly="readonly" value="<bean:write name="items" property="ghi_chu_bk"/>"/>
                  </logic:equal>
                  </td>
                  <td>
                    <logic:equal value="1" property="ket_qua" name="items">
                       <logic:equal value="02" property="trang_thai_kq" name="items">
                          <button type="button" onclick="fncClose('<bean:write name="items" property="kq_id"/>');" class="ButtonCommon" accesskey="t" >
                              <span class="sortKey">C</span>&#7853;p nh&#7853;t
                          </button>
                       </logic:equal>
                    </logic:equal>
                    <logic:equal value="" property="trang_thai_bk" name="items">                     
                          <button type="button" onclick="fncClose_chuadc('<bean:write name="items" property="bk_id"/>','<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="stt"/>')" class="ButtonCommon" accesskey="t" >
                              <span class="sortKey">C</span>&#7853;p nh&#7853;t
                          </button>
                    </logic:equal>
                    <logic:equal value="98" property="trang_thai_bk" name="items">                     
                          <button type="button" onclick="fncClose_chuadc('<bean:write name="items" property="bk_id"/>','<bean:write name="items" property="send_bank"/>','<bean:write name="items" property="receive_bank"/>','<bean:write name="items" property="ngay_dc"/>','<bean:write name="stt"/>')" class="ButtonCommon" accesskey="t" >
                              <span class="sortKey">C</span>&#7853;p nh&#7853;t
                          </button>
                    </logic:equal>
                  </td>
                  
                </tr>
                </logic:iterate>
              </logic:present>
              </logic:notEmpty>
              <tr>
                  <td colspan="10">                 
                 <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                  </td>
              </tr>
              </tbody>
             </table>
        </fieldset>
       </td>
      </tr>

</table>
 <html:hidden property="pageNumber" value="1"/>
<div id="dialog-form-ctiet-clech"  title="Chi ti&#7871;t ch&#234;nh l&#7879;ch">
        <p class="validateTips"></p>
            <table style="width:100%;height:100%;">
              <tr>
                <td width="50%">
                <fieldset>
                  <legend><font color="Blue">Th&#244;ng tin s&#7889; d&#7921; ki&#7871;n k&#7871;t chuy&#7875;n</font></legend>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.kcthu"/>
                    </label>
                    <input type="text" name="ketchuyen_thu_nh_dia" readonly="readonly" id="ketchuyen_thu_nh_dia" style="text-align:right" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.kcchi"/>
                    </label>
                    <input type="text" name="ketchuyen_chi_nh_dia" readonly="readonly" id="ketchuyen_chi_nh_dia" style="text-align:right" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  </fieldset>
                </td>
                <td width="50%">
                <fieldset>
                  <legend><font color="Blue">Th&#244;ng tin l&#7879;nh quy&#7871;t to&#225;n</font></legend>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.qtthu"/>
                    </label>                    
                  <input type="text" name="ketchuyen_thu_kb_dia" readonly="readonly"  id="ketchuyen_thu_kb_dia" style="text-align:right" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.qtchi"/>
                    </label>
                    <input type="text" name="ketchuyen_chi_kb_dia" readonly="readonly" id="ketchuyen_chi_kb_dia" style="text-align:right" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  </fieldset>
                </td>             
              </tr>
            </table>
      </div>
      <!-- close doi chieu 2-->
      <div id="dialog-form-close-dchieu2"  title="C&#7853;p nh&#7853;t tr&#7841;ng th&#225;i">
        <p class="validateTips"></p>
            <table style="width:100%;height:100%;">
              <tr>
                <td width="50%">
                <div style="padding-top:10px;">
                <label style="padding-left:35px;">
                      c&#7853;p nh&#7853;t tr&#7841;ng th&#225;i
                    </label> 
                  <html:select property="close_trang_thai" styleId="close_trang_thai"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">                  
                      <html:option value="1">&#272;&#243;ng</html:option>
                      <html:option value="0">M&#7903;</html:option>
                  </html:select>
                  
                  <html:hidden property="kq_id_dia" styleId="kq_id_dia"/>
                  
                  </div>
                  <div style="padding-top:10px;">
                  <label style="padding-left:35px;">
                      Ghi ch&#250;
                    </label> 
                  <html:textarea property="close_ldo" styleId="close_ldo" rows="5" cols="60"
                                       value=""/>
                  </textarea>
                  </div>
                </td>             
              </tr>
            </table>
      </div>
      <!-- close doi chieu 2 voi truong hop chua co bang ke dchieu2-->
      <div id="dialog-form-close-dchieu2_chuadc"  title="C&#7853;p nh&#7853;t tr&#7841;ng th&#225;i">
        <p class="validateTips"></p>
            <table style="width:100%;height:100%;">
              <tr>
                <td width="50%">
                <div style="padding-top:10px;">
                <label style="padding-left:35px;">
                      c&#7853;p nh&#7853;t tr&#7841;ng th&#225;i
                    </label> 
                  <html:select property="close_trang_thai_chuadc" styleId="close_trang_thai_chuadc"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">                  
                      <html:option value="1">&#272;&#243;ng</html:option>
                      <html:option value="0">M&#7903;</html:option>
                  </html:select>
                  
                  <html:hidden property="kq_id_dia_chuadc" styleId="kq_id_dia_chuadc"/>
                  <html:hidden property="send_bank_dia_chuadc" styleId="send_bank_dia_chuadc"/>
                  <html:hidden property="receive_bank_dia_chuadc" styleId="receive_bank_dia_chuadc"/>
                  <html:hidden property="ngay_dc_dia_chuadc" styleId="ngay_dc_dia_chuadc"/>
                  <html:hidden property="stt_idx" styleId="stt_idx"/>
                  
                  </div>
                  <div style="padding-top:10px;">
                  <label style="padding-left:35px;">
                      Ghi ch&#250;
                    </label> 
                  <html:textarea property="close_ldo_chuadc" styleId="close_ldo_chuadc" rows="5" cols="60"
                                       value=""/>
                  </textarea>
                  </div>
                </td>             
              </tr>
            </table>
      </div>
<!-- chi tiet chua doi chieu-->
    <div id="dialog-form-ctiet-chuadchieu"  title="Chi ti&#7871;t ch&#234;nh l&#7879;ch">
        <p class="validateTips"></p>
            <table style="width:100%;height:100%;">
              <tr>
                <td width="80%" align="center">
                 <div style="padding-top:10px;width:100%">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.tthaidc1"/>
                    </label>
                    <input type="text" name="tthai_dc1" readonly="readonly" id="tthai_dc1" style="text-align:left;width:30%" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.tthaidc2"/>
                    </label>
                    <input type="text" name="tthai_dc2" readonly="readonly" id="tthai_dc2" style="text-align:left;width:30%" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.qtthu"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>                    
                  <input type="text" name="qtoan_thu_dia" readonly="readonly" id="qtoan_thu_dia" style="text-align:right;width:30%" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                  <div style="padding-top:10px;">
                    <label style="padding-left:35px;">
                      <fmt:message key="doi_chieu.page.lable.qldc.qtchi"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <input type="text" name="qtoan_chi_dia" readonly="readonly" id="qtoan_chi_dia" style="text-align:right;width:30%" class=" text ui-widget-content ui-corner-all"/>
                  </div>
                </td>             
              </tr>
            </table>
      </div>
  </html:form>
 
      
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
function goPage(page) {
      var initAction ="<%=initAction%>";
      f.pageNumber.value = page;
      if(initAction==null||""==initAction){
        f.action = 'TCuuQLyDChieuAction.do'; 
      }else if(initAction!=null&& ""!=initAction){
        f.action = 'lstQLyDChieuAction.do'; 
      }
      f.submit();
  }
  var f = document.forms[0];
    function nhkb_tinhval() {
      var nhkb_tinh;
      nhkb_tinh=  document.getElementById("nhkb_tinh").value; 
      return nhkb_tinh;
  }
      function tthaival() {
      var tthai;
      tthai=document.getElementById("trang_thai").value;
      return tthai;
  }
      function nhangval() {
      var nhkb_nhan;
      nhkb_nhan=document.getElementById("ngan_hang").value; 
      return nhkb_nhan;
  }
  jQuery.noConflict(); 	
  jQuery(document).ready(function(){ 
    jQuery("#dialog-form-ctiet-clech").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Thoát": function() {
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
    // chi tiet chua doi chieu
    
    jQuery("#dialog-form-ctiet-chuadchieu").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Thoát": function() {
         jQuery("#tthai_dc2").val("");
         jQuery("#tthai_dc1").val("");
         jQuery("#qtoan_thu_dia").val("");
         jQuery("#qtoan_chi_dia").val("");
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
    
    // CLose DChhieu
    jQuery("#dialog-form-close-dchieu2").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Ghi": function() {
        close_DChieu() ;
          jQuery( this ).dialog( "close" );
        },
        "Thoát": function() {
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
    
    jQuery("#dialog-form-close-dchieu2_chuadc").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Ghi": function() {
        var close_trang_thai=jQuery('#close_trang_thai_chuadc').val();
//        alert(close_trang_thai);
          if(close_trang_thai==1 || close_trang_thai=='1'){
            var close_ldo_chuadc=jQuery('#close_ldo_chuadc').val();
             if (""==close_ldo_chuadc.trim() || close_ldo_chuadc.trim() == null) {
                    alert(GetUnicode('Nhập lý do đóng đối chiếu'));                 
                      return false;
              }
          }
          close_DChieu_chuadc();
          jQuery( this ).dialog( "close" );
        },
        "Thoát": function() {
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
    
   }); 

  function check(type) {  
     if (type == 'find') {
        f.action = 'TCuuQLyDChieuAction.do';
        
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
  function fncsearch(thu_nh,chi_nh,thu_kb,chi_kb){
      jQuery("#ketchuyen_thu_nh_dia").val(toFormatNumber(thu_nh,0,'.'));
      jQuery("#ketchuyen_chi_nh_dia").val(toFormatNumber(chi_nh,0,'.'));
      jQuery("#ketchuyen_thu_kb_dia").val(toFormatNumber(thu_kb,0,'.'));
      jQuery("#ketchuyen_chi_kb_dia").val(toFormatNumber(chi_kb,0,'.'));
      jQuery("#dialog-form-ctiet-clech").dialog( "open" );    
    }
    
    function fncChuaDChieu(send_bank, receive_bank, ngay_dc,lan_dc){
    load_KQDChieu(send_bank,receive_bank,ngay_dc,lan_dc);
      jQuery("#dialog-form-ctiet-chuadchieu").dialog( "open" );    
    }
    
   function fncClose(kq_id,stt_idx){
   jQuery("#kq_id_dia").val(kq_id);  
      jQuery("#dialog-form-close-dchieu2").dialog("open");    
    }
    function fncClose_chuadc(bk_id, send_bank,receive_bank, ngay_dc,stt_idx){
          jQuery("#kq_id_dia_chuadc").val(bk_id);
          jQuery("#send_bank_dia_chuadc").val(send_bank);
          jQuery("#receive_bank_dia_chuadc").val(receive_bank);
          jQuery("#ngay_dc_dia_chuadc").val(ngay_dc);
          var close_trang_thai = jQuery('#close_trang_thai_chuadc').val();
          alert(close_trang_thai);
          if(close_trang_thai =='0' || close_trang_thai==0 || close_trang_thai==null || ''==close_trang_thai){
           jQuery('#close_trang_thai_chuadc').val('1');
           jQuery("#close_trang_thai_chuadc").attr("disabled", "disabled");
         }
         else if(close_trang_thai =='1' || close_trang_thai==1 ){
           jQuery('#close_trang_thai_chuadc').val('0');
           jQuery("#close_trang_thai_chuadc").attr("disabled", "disabled");
         }
//          alert(stt_idx);
          jQuery("#stt_idx").val(stt_idx);
      jQuery("#dialog-form-close-dchieu2_chuadc").dialog("open");    
    }
   
   function close_DChieu() {
    jQuery.ajax( {
        type : "POST", url : "closeDChieuAction.do", data :  {
            "stt_idx" : jQuery("#stt_idx").val(), "kq_id_dia" : jQuery("#kq_id_dia").val(), "close_trang_thai" : jQuery("#close_trang_thai").val(),"close_ldo" : jQuery("#close_ldo").val()
        },
        success : function (data, textstatus) {     
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDC) { 
                      if(objectDC.tthai_dmo==1 || objectDC.tthai_dmo=="1"){
                        jQuery("#dong_mo_id_"+jQuery("#kq_id_dia").val()).val("Đóng");
                      }
                      if(objectDC.tthai_dmo==0 || objectDC.tthai_dmo=="0"){
                        jQuery("#dong_mo_id_"+jQuery("#kq_id_dia").val()).val("Mở");
                      }
                        jQuery("#ghi_chu_"+jQuery("#kq_id_dia").val()).val(objectDC.ghi_chu);
                    });
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
} 

function close_DChieu_chuadc() {
     
    jQuery.ajax( {
        type : "POST", url : "closeKhongBKeAction.do", data :  {
            "stt_idx" : jQuery("#stt_idx").val(),"bk_id" : jQuery("#kq_id_dia_chuadc").val(), "close_trang_thai" : jQuery("#close_trang_thai_chuadc").val(),"close_ldo" : jQuery("#close_ldo_chuadc").val(), "send_bank" : jQuery("#send_bank_dia_chuadc").val(),"receive_bank" : jQuery("#receive_bank_dia_chuadc").val(),"ngay_dc" : jQuery("#ngay_dc_dia_chuadc").val()
        },
        
        success : function (data, textstatus) {   
//        alert(jQuery("#stt_idx").val());
//        alert(data)
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
               
                    jQuery.each(data, function (i, objectDC) { 
//                     alert(stt_idx);
                      if(objectDC.trang_thai_bk==98 || objectDC.trang_thai_bk=="98"){
                        jQuery("#dong_mo_id_chuadc_"+jQuery("#stt_idx").val()).val("Đóng");
                      }
//                        alert(jQuery("#stt_idx").val());
                        jQuery("#ghi_chu_bk_"+jQuery("#stt_idx").val()).val(objectDC.ghi_chu_bk);
//                        alert(objectDC.ghi_chu_bk);
//                        jQuery("#ghi_chu_bk_"+jQuery("#stt_idx").val()).val();
//                        jQuery("#ghi_chu_bk_"+jQuery("#stt_idx").val()).val(objectDC.ghi_chu_bk);
                    });
                }
            }
            if (data==null||''==data){
//                  if(objectDC.trang_thai_bk==null || objectDC.trang_thai_bk==""){
//                    alert('a');
                        jQuery("#dong_mo_id_chuadc_"+jQuery("#stt_idx").val()).val("Mở");
                        jQuery("#ghi_chu_bk_"+jQuery("#stt_idx").val()).val("");
//                      }
                }
        }, 
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}  


function load_KQDChieu(send_bank, receive_bank ,ngay_dc,lan_dc) {
    jQuery.ajax( {
        type : "POST", url : "notDChieuAction.do", data :  {
            "send_bank" : send_bank, "receive_bank" : receive_bank, "ngay_dc" : ngay_dc, "lan_dc" : lan_dc
        },
        success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {               
                    if (data != null) { 
                    // list ra trang thai doi chieu 1
                         var lstKQDC1 = new Object();
                        lstKQDC1 = JSON.parse(data[2]);
                        var lstKQDCSize1 = lstKQDC1.size();
                        
                        if (lstKQDC1 != null) {
                            if (lstKQDCSize1 > 0) {
                              for (var i = 0;i < lstKQDCSize1;i++) {
                                    var objectDC1 = lstKQDC1[i];
                                  if (objectDC1.tthai_dxn_thop=="00"){
                                    if(objectDC1.trang_thai=="02"){
                                       if(objectDC1.ket_qua=="0"){
                                         jQuery("#tthai_dc1").val("Đối chiếu TT khớp đúng TTV, chưa tạo ĐXN TH");
                                       }
                                       if(objectDC1.ket_qua=="1"){
                                         jQuery("#tthai_dc1").val("Đối chiếu TT chênh lệch KTT, chưa tạo ĐXN TH");
                                       }
                                    }else if(objectDC1.trang_thai!="02"){
                                      jQuery("#tthai_dc1").val("Chưa hoàn thành đối chiếu TT");
                                    }
                                  }else if (objectDC1.tthai_dxn_thop=="01"){
                                     if(objectDC1.ket_qua=="0"){
                                       jQuery("#tthai_dc1").val("Đối chiếu TT khớp đúng, chờ KTT duyệt TH");
                                     }
                                     if(objectDC1.ket_qua=="1"){
                                       jQuery("#tthai_dc1").val("Đối chiếu TT chênh lệch, chờ KTT duyệt TH");
                                     }
                                  }else if (objectDC1.tthai_dxn_thop=="02"){
//                                      if(objectDC1.ket_qua_dxn_thop=="0"){
                                       jQuery("#tthai_dc1").val("Đã duyệt XNQT");
//                                     }
//                                     if(objectDC1.ket_qua_dxn_thop=="1"){
//                                       jQuery("#tthai_dc1").val("Đối chiếu TT chênh lệch, KTT duyệt");
//                                     }
                                  }
                              }
                            }
                        }
                    // list ra ket qua doi chieu 2
                        var lstKQDC2 = new Object();
                        lstKQDC2 = JSON.parse(data[0]);
                        var lstKQDCSize2 = lstKQDC2.size();                       
                        if (lstKQDC2 != null) {
                            if (lstKQDCSize2 > 0) {
                              for (i = 0;i < lstKQDCSize2;i++) {
                                  var objectDC2 = lstKQDC2[i];
                                  // thong tin trang thai doi chieu 2
                                  if (objectDC2.trang_thai=="01"){ 
                                    if (objectDC2.trang_thai_kq=="01"){ 
                                      jQuery("#tthai_dc2").val("Chênh lệch-chờ KTT duyệt");
                                    }else if (objectDC2.trang_thai_kq=="02"){ 
                                      jQuery("#tthai_dc2").val("Chênh lệch-KTT duyệt");
                                    }                                 
                                  }else if (objectDC2.trang_thai=="02"){
                                      if (objectDC2.trang_thai_kq=="01"){ 
                                      jQuery("#tthai_dc2").val("Khớp đúng-chờ KTT duyệt");
                                    }else if (objectDC2.trang_thai_kq=="02"){ 
                                      jQuery("#tthai_dc2").val("Khớp đúng-KTT duyệt");
                                    }
                                  }else if (objectDC2.trang_thai=="00"){
                                      jQuery("#tthai_dc2").val("Chưa đối chiếu");
                                  }
                              }
                            }
                        }
                        // list ra ket qua doi chieu 2
                        var lstQT = new Object();
                        lstQT = JSON.parse(data[1]);
                        var lstQTSize = lstQT.size();                       
                        if (lstQT != null) {
                            if (lstQTSize > 0) {
                              for (i = 0;i < lstQTSize;i++) {
                                  var objectQT = lstQT[i];
                                  if(objectQT.loai_qtoan=="Thu"){
                                  jQuery("#qtoan_thu_dia").val(toFormatNumber(objectQT.so_tien,0,'.'));
                                  }if(objectQT.loai_qtoan=="Chi"){
                                  jQuery("#qtoan_chi_dia").val(toFormatNumber(objectQT.so_tien,0,'.'));
                                  }    
                              }
                            }
                        }
                    }
                }                
            },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}  


</script>