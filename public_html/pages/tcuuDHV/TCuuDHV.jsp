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
        <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
String idxKB_huyen = request.getAttribute("idxKB_huyen")==null?"":request.getAttribute("idxKB_huyen").toString();
String inMax = request.getAttribute("tien_max")==null?"":request.getAttribute("tien_max").toString();
String inMin = request.getAttribute("tien_min")==null?"":request.getAttribute("tien_min").toString();

  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
  String inlan_dc = request.getAttribute("inlan_dc")==null?"":request.getAttribute("inlan_dc").toString();
  String inxtthai = request.getAttribute("inxtthai")==null?"":request.getAttribute("inxtthai").toString();
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
  String cur_page = request.getAttribute("currentPage")==null?"":request.getAttribute("currentPage").toString();
 

%>
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
</script>
<div class="app_error">
  <html:errors/>
  
</div>
<div class="box_common_conten">
  <html:form action="loadTCuuDHV.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Tra cứu LTT cần cấp trên phê duyệt </span>
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
      <td align="center" colspan="2">
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
        <tr>
          <td  align="right" bordercolor="#e1e1e1">
          <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
          </td>
          <td >
          
          <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="width: 100%;font-size:12px" onchange="getTenKhoBacDC('',''); nhkb_tinhval();"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;">           
                    <%if(request.getAttribute("dftinh") != null){
                           %>
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                          <%}%> 
                    <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>           
          </html:select>
          </td>
          <td  align="right" bordercolor="#e1e1e1">
          <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
          </td>
          <td>
          <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="width: 100%;font-size:12px" onchange="nhkb_huyenval();"
                      onkeydown="if(event.keyCode==13) event.keyCode=9;">                          
                    <html:option value="">-----Chon KB huyen-----</html:option>
                    
          </html:select>
          </td>
          <td  align="right">
              <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
            </td>
            <td >
              <html:select property="ma_dv" styleId="ma_dv" onchange="nhangval()"
                       style="width: 100%;font-size:12px"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
              <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
              <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
          </html:select>
          </td>
          <td  align="center" rowspan="3" width="10%">
                  <%if(request.getAttribute("dchieu3") != null){
                  %>
                  
                  <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">D</span>anh m&#7909;c KB
                  </button> <p/>
                  <%}%>
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">T</span>&igrave;m kiếm
                  </button>               

               </td>
        </tr>
        <tr>
          <td width="10%" align="right">
            S&#7889; LTT
          </td>
          <td align="left" width="17%">
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="so_ltt" maxlength="25" property="so_ltt"/>
          </td>
          <td width="10%" align="right">
            S&#7889; ti&#7873;n l&#7899;n h&#417;n
          </td>
          <td align="left" width="20%">
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="tien_min" onblur="fmt_money('tien_min',this.value);" maxlength="25" property="tien_min"/>
          </td>
          <td width="13%" align="right">
            S&#7889; ti&#7873;n nh&#7887; h&#417;n
          </td>
          <td align="left" width="17%">
            
            <html:text style="width:95%; text-align:right; font-size:12px" styleId="tien_max" onblur="fmt_money('tien_max',this.value);" maxlength="25" property="tien_max"/>          

          </td>                
        </tr>
        <tr>
                <td align="right" >
                    T&#7915; ng&#224;y TT &nbsp;
                </td>
                <td align="left">
                <html:text property="tu_ngay_tt" styleId="tu_ngay_tt" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_tt');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay_tt", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_btn"// id of the button
                      });
                    </script> &nbsp;&nbsp;&nbsp;
                </td>
                <td align="right">
                     &#272;&#7871;n ng&#224;y TT &nbsp;
                </td>
                <td align="left">
                <html:text property="den_ngay_tt" styleId="den_ngay_tt" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_tt');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:13"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay_tt", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                  </td>
                  <td align="right" bordercolor="#e1e1e1">
                    Tr&#7841;ng th&#225;i &nbsp;
                  </td>
                  <td >
                    <html:select property="trang_thai_tw" styleId="trang_thai_tw" style="font-size:12px;width:100%" onchange="tthaival()"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                        <html:option value="">T&#7845;t c&#7843;</html:option>
                        <html:option value="01">Chờ thanh toán</html:option>
                        <html:option value="02">TW - Ch&#7901; duy&#7879;t</html:option>
                        <html:option value="03">TW - &#272;&#227; duy&#7879;t</html:option>
                        <html:option value="04">Tỉnh không duyệt</html:option>
                        <html:option value="05">TW không duyệt</html:option>
                        <html:option value="06">Gửi tự động</html:option>
                        <html:option value="07">Lệnh chuyển từ GD huyện</html:option>
                    </html:select>
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
           <td colspan="2">
            <fieldset>
            <legend><font color="Blue">Danh s&#225;ch ch&#7901; duy&#7879;t</font></legend>
            <div style="overflow-x: scroll;width:100%" >
              <table  class="data-grid" id="data-grid" 
                                                  border="1"
                                                 cellspacing="0" cellpadding="0"                                  
                                                 width="130%">
                     <tr>
                     <td align="center" width="9%" class="ui-state-default ui-th-column">Số YCTT</td>
                     <td align="center" width="12%" class="ui-state-default ui-th-column">KH chuy&#7875;n</td>
                     <td align="center" width="12%" class="ui-state-default ui-th-column">T&#224;i kho&#7843;n - KH nh&#7853;n</td>
                     <td align="center" width="12%" class="ui-state-default ui-th-column">NH nh&#7853;n </td>                     
                     <td align="center" width="8%" class="ui-state-default ui-th-column">S&#7889; ti&#7873;n</td>  
                     <td align="center" width="11%" class="ui-state-default ui-th-column"> Tr&#7841;ng th&#225;i </td>
                     <td align="center" width="11%" class="ui-state-default ui-th-column">Ngày TW duyệt</td>
                     <td align="center" width="12%" class="ui-state-default ui-th-column">Lý do Tinh</td>
                     <td align="center" width="12%" class="ui-state-default ui-th-column">Lý do TW</td>
                     
                     </tr>
                     
                    <logic:empty name="colLTT">
                      <tr>
                        <td colspan="5">
                          <font color="Red">Kh&#244;ng t&#236;m th&#7845;y th&#244;ng tin th&#7887;a m&#227;n &#273;i&#7873;u ki&#7879;n </font>
                        </td>
                      </tr>
                    </logic:empty>
                   <logic:notEmpty name="colLTT">
                      <logic:iterate id="items" name="colLTT" indexId="index">
                     <tr id="row_dts_<bean:write name="index"/>" class='<%=index % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' onmouseout="window.status =''" onmouseover="window.status = 'Thanh toán viên: <bean:write  name="items" property="ten_nsd"/> - <bean:write  name="items" property="ma_nsd"/>';">
                     <td align="left"  onmouseover="style.fontWeight='bold'" onmouseout="style.fontWeight='normal'">
                           <a href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>','true')" title="<bean:write  name="items" property="so_yctt"/>-<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen"/>-<bean:write name="items" property="kh_nhan"/>-<bean:write name="items"  property="ten_nhkb_nhan"/>">
                              <bean:write name="items" property="so_yctt"/>
                           </a>
                       </td>
                       <td align="left" >
                       <div title="<bean:write  name="items" property="so_tk_chuyen"/>-<bean:write  name="items" property="kh_chuyen" />" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden; width:130px; font-size:13px ">
                               <bean:write  name="items" property="kh_chuyen" />
                        </div>
                       </td>
                       <td align="left" title="<bean:write name="items" property="kh_nhan"/>">
                        <div style="text-overflow:ellipsis;width:130px;white-space:nowrap;  overflow:hidden; font-size:13px">
                        &nbsp;<bean:write name="items" property="kh_nhan"/></div>
                       </td>
                       <td align="left" >
                       <div title="<bean:write name="items"  property="ten_nhkb_nhan"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:130px; overflow:hidden; font-size:13px">
                       &nbsp;<bean:write name="items"  property="ten_nhkb_nhan"/></div>
                       </td>
                       
                       <td align="right"  style="font-size:12px">
                        
                        <fmt:setLocale value="vi_VI"/>
                          <b> <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">              
                          <bean:write name="items"   property="tong_sotien"/>
                          
                          </fmt:formatNumber>
                          </b>
                       </td>     
                       <td align="center" >
                         <logic:equal value="01" property="trang_thai_tw" name="items">
                            Chờ thanh toán
                         </logic:equal>
                         <logic:equal value="02" property="trang_thai_tw" name="items">
                            TW - Ch&#7901; duy&#7879;t
                         </logic:equal>
                         <logic:equal value="03" property="trang_thai_tw" name="items">
                            TW - &#272;&#227; duy&#7879;t
                         </logic:equal>
                         <logic:equal value="04" property="trang_thai_tw" name="items">
                            Tỉnh không duyệt
                         </logic:equal>
                         <logic:equal value="05" property="trang_thai_tw" name="items">
                            TW - không duy&#7879;t
                         </logic:equal>
                         <logic:equal value="06" property="trang_thai_tw" name="items">
                            Gửi tự động
                         </logic:equal>
                      </td>
                      <td align="center">
                        <bean:write name="items"  property="ngay_duyet_tw"/>
                      </td>
                       <td align="left"  style="font-size:12px">
                       
                          <div title="<bean:write name="items"  property="ly_do_tinh"/>" style="text-overflow:ellipsis;width:130px;white-space:nowrap;  overflow:hidden; font-size:13px">
                       &nbsp;<bean:write name="items"  property="ly_do_tinh"/></div>
                        
                       </td>
                       <td align="left"  style="font-size:12px">
                        
                        <div title="<bean:write name="items"  property="ly_do_tw"/>" style="text-overflow:ellipsis;width:130px;white-space:nowrap;  overflow:hidden; font-size:13px">
                       &nbsp;<bean:write name="items"  property="ly_do_tw"/></div>
                        
                       </td>
                       
                     </tr>
                      </logic:iterate>
                      <tr>
                            <td colspan="10">                 
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                        </tr>
                    </logic:notEmpty>              
                 </table>
                </div>
            </fieldset>
           </td>
          </tr>
          <tr>
             <td align="left">
              <logic:present name="colMonTien">
               <logic:notEmpty name="colMonTien">
                  <logic:iterate id="items" name="colMonTien" indexId="index">
                     <font color="Blue">T&#7893;ng s&#7889; m&#243;n</font>
                    <b><bean:write name="items" property="tong_mon"/></b>&nbsp;&nbsp;
                    <font color="Blue">t&#7893;ng ti&#7873;n </font> 
                     <b>
                     <fmt:setLocale value="vi_VI"/>
                           <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                           <bean:write name="items" property="tong_tien"/>
                           </fmt:formatNumber>
                           </b>
                  </logic:iterate>
                </logic:notEmpty>
                </logic:present>
            </td>
            <td align="right">
              <span id="in"> 
                <button onclick="formAction()"
                        id="btnIn" class="ButtonCommon"
                        type="button" accesskey="I">
                  <span class="sortKey">I</span>n
                </button>
              </span>
              &nbsp;&nbsp;&nbsp;
              <button type="button" onclick="check('close');" class="ButtonCommon" accesskey="t" >
                  Th<span class="sortKey">o</span>&#225;t
              </button>
            </td>
          </tr>
  
  </table>
  <html:hidden property="pageNumber" styleId="pageNumber"/>
  </html:form>
 
      
</div>

<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

//getNgan_hang();
//getTTV();



jQuery('#so_ltt').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_ltt').val(this.value.replace(matches, ''),true);
         });
         
 jQuery('#tien_max').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_tien').val(this.value.replace(matches, ''),true);
         });
 jQuery('#tien_minn').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#so_ltt').val(this.value.replace(matches, ''),true);
         });

function fmt_money(tien_type,tien){
  jQuery('#'+tien_type+'').val(toFormatNumber(tien,0,'.'));
}

  function check(type) { 
   var f = document.forms[0];
     if (type == 'find') {
       var idxKB = jQuery('#nhkb_huyen option:selected').index();
        f.action = 'listTCuuDHV.do?idxKB='+idxKB      
      }
     if (type == 'close') {
        f.action = 'mainAction.do';      
      } 
       f.submit();
    }
  function nhangval() {
      var ma_dv;
      nhkb_nhan=document.getElementById("ma_dv").value; 
      return ma_dv;
  }
    function nhkb_tinhval() {
      var nhkb_tinh;
      nhkb_tinh=document.getElementById("nhkb_tinh").value; 
      return nhkb_tinh;
  }
    function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=document.getElementById("nhkb_huyen").value; 
      return nhkb_huyen;
  }
  function tthaival() {
      var trang_thai_tw;
      trang_thai_tw=document.getElementById("trang_thai_tw").value; 
      return trang_thai_tw;
  }

function goPage(page) {
 var f = document.forms[0],
          initAction ='<%=initAction%>';
      f.pageNumber.value = page;
      
      if(initAction==null || ""==initAction){
      f.action = 'listTCuuDHV.do?pageNumber='+page;
        
      }else if(initAction!=null && ""!=initAction){
        f.action = 'loadTCuuDHV.do?pageNumber='+page;
      }
      
      f.submit();
  }
// In
  function formAction(){
    var f = document.forms[0];
    f.action = "printTCuuDHV.do";
//      if(asyncCommand != null)
//      {
//         asyncCommand.Cancel();
//      }
     var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
    newDialog = window.open('', '_form', params);          
    f.target='_form';
    f.submit();
  }
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }

function makeGetRequestView(id, type) {
      var urlRequest = null;
          urlRequest = "listLttAdd.do?action=VIEW_LTTDi_DHV&so_ltt_details=" + id + "";
      window.location = urlRequest;
  }   

function getTenKhoBacDC(id,id_cha) { 
     var dchieu3="<%=dchieu3%>";
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
       var kb_id;
       if(dchieu3!=null && ''!=dchieu3){
//       alert('id='+id+'id_cha='+id_cha);
            if (id==null || ''==id){       
                  if(id_cha!=null&&''!=id_cha){               
                        kb_id=id_cha;
                        jQuery('#nhkb_tinh').val(id_cha);
                  }else if (id_cha==null||''==id_cha){
                    kb_id=document.forms[0].nhkb_tinh.value;
                  }
              }else if (id!=null && ''!=id){
                  if(id_cha!=null&&''!=id_cha){                
                    kb_id=id_cha;
                    jQuery('#nhkb_tinh').val(id_cha);
                  }else if (id_cha==null||''==id_cha){
                    kb_id=document.forms[0].nhkb_tinh.value;
                  }
              }
         }else if(dchieu3==null || ''==dchieu3){
          kb_id=document.forms[0].nhkb_tinh.value;
         }
      var kb_huyen="<%=idxKB_huyen%>";   
      var strTinh="<%=strTinh%>";  
      
     if (kb_id !=null && ""!=kb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucKBTHop.do", data :  {
              "kb_id" : kb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                      // truong hop 1 - luc load khong co thang nao                  
                      document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                      });
                      if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                          if(document.getElementById('nhkb_huyen').options.length==2){
                            jQuery("#nhkb_huyen option:eq(0)").remove();
 
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);

                         }
                        else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);

                        }
                        else if(kb_huyen!='0'){
                        jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);

                        }
                      }
                  }
              }
                if (id!=null && ''!=id){                        
                          jQuery('#nhkb_huyen').val(id);
                      }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }

// function getTenKhoBacLOTW() {  
//      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
//      var kb_id = document.forms[0].nhkb_tinh.value;
//      alert(kb_id);
//      var kb_huyen="<%=idxKB_huyen%>";   
//      if (kb_id ==null || ""==kb_id){
//          document.getElementById('ngan_hang').options.length = 1; 
//      }else if (kb_id !=null && ""!=kb_id){
//      jQuery.ajax( {
//          type : "POST", url : "getDMKBLTTTW.do", data :  {
//              "kb_id" : kb_id
//          },
//          success : function (data, textstatus) {
//              if (textstatus != null && textstatus == 'success') {
//                  if (data != null) {
//                      jQuery.each(data, function (i, objectDM) {
//                      // truong hop 1 - luc load khong co thang nao                  
//                      document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
//                      });
//                          if(kb_huyen==null||''==kb_huyen){
//                             if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
//                                  jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
//                             }
//                          }else if(kb_huyen!='0'){
//                            jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
//                          }
//                  }
//              }
//
//          },
//          error : function (textstatus) {
//              alert(textstatus);
//          }
//      });
//      }
//  }



</script>