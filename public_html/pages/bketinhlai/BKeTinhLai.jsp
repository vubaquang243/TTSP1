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

<%
 String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
  String idxTK = request.getAttribute("idxTK")==null?"":request.getAttribute("idxTK").toString();
  String sum_lai = request.getAttribute("sum_lai")==null?"":request.getAttribute("sum_lai").toString();
  String sum_sdu = request.getAttribute("sum_sdu")==null?"":request.getAttribute("sum_sdu").toString();
%>

<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  
  jQuery(document).init(function () {
      getTenKhoBacDC('','');
//      getTenNHang();
//      getTK_NH_KB();
var sum_sdu='<%=sum_sdu%>'
var sum_lai='<%=sum_lai%>'
  jQuery("#sum_sdu").val(toFormatNumber(sum_sdu,0,'.'));
  jQuery("#sum_lai").val(toFormatNumber(sum_lai,0,'.'));
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
  <html:form action="listBKeTinhLai.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> B&#7843;ng k&#234; t&#237;nh l&#227;i </span>
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
        <legend><font color="blue">&#272;i&#7873;u ki&#7879;n bảng k&#234; </font></legend>
        <div>
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="1" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                
                           <%if(request.getAttribute("QTHTTW") != null){
                           %>
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                          <%}%>
                   
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:100%" onchange="nhkb_huyenval();getTenNHang();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                             
                </html:select>
                </td>
                <td  align="center" rowspan="3" width="15%">
                <%if(request.getAttribute("QTHTTW") != null){
                  %>
                  <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">D</span>anh m&#7909;c KB
                  </button> <p/>
                  <%}%>
                                 

               </td>
              </tr>
              <tr>
              <td align="right" bordercolor="#e1e1e1">
                   Ng&#226;n h&#224;ng &nbsp;
                  </td>
                  <td >
                    <html:select property="ngan_hang" styleId="ngan_hang" style="font-size:12px;width:100%" onchange="nhangval();getTK_NH_KB()"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                      
                  </html:select>
                </td>
                 <td align="right" bordercolor="#e1e1e1">
                    Số TK &nbsp;
                  </td>
                  <td >
                     <html:select property="so_tk" styleId="so_tk" style="font-size:12px;width:100%" onchange="tkval();"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                      
                  </html:select>                          
                </td>
                
              </tr>
              <tr>
                <td align="right">
                    T&#7915; <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                </td>
                <td>
                <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:26%"
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
                <td align="right">
                     &#272;&#7871;n <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                </td>
                <td>
                <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:26%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:13"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                  </td>
                  
              </tr>
              <tr>
                <td colspan="5" align="right">
                  
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="x" >
                          <span class="sortKey">X</span>em
                  </button>
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
      <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      <tr>
         <td>
          <fieldset>
          <legend><font color="Blue">Chi ti&#7871;t bảng k&#234;</font></legend>
            <table  class="data-grid" id="data-grid" 
                                                border="1"
                                               cellspacing="0" cellpadding="0"                                  
                                               width="100%">

                  <tr>
                    <th width="5%">
                      STT
                    </th>
                    <th width="15%">
                      Ng&#224;y PS
                    </th>
                    <th width="15%">
                      S&#7889; d&#432; cu&#7889;i ng&#224;y
                    </th>
                    <th width="5%">
                      L&#227;i su&#7845;t
                    </th>
                    <th width="15%">
                      L&#227;i
                    </th>
                    <th width="45%">
                      Ghi ch&#250;
                    </th>
                  </tr>
                 
                   <logic:present name="colBKTLai">
                      <logic:notEmpty name="colBKTLai">
                        <logic:iterate id="items" name="colBKTLai" indexId="stt">
                         <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                            <td align="center" > 
                              <%=stt+1+rowBegin%>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="ngay"/>
                            </td>
                            <td align="right">
                              <fmt:setLocale value="vi_VI"/>
                               <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="sodu_cuoingay"/>
                              </fmt:formatNumber>&nbsp;
                            </td>
                            <td align="center">
                              <bean:write name="items" property="lai_suat"/>
                            </td>
                            <td align="right">
                            <fmt:setLocale value="vi_VI"/>
                              <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="lai"/>
                            </fmt:formatNumber>&nbsp;
                            </td>
                            <td align="left">
                              &nbsp;<bean:write name="items" property="ghi_chu"/>
                            </td>
                         </tr>
                        </logic:iterate>
                        <tr>
                            <td colspan="2" align="right">
                              Tổng số: &nbsp;
                            </td>
                            <td>
                               <input type="text" name="sum_sdu" id="sum_sdu" style="text-align:right; border:0;font-weight:bold;font-size:12px"/>
                            </td>
                            <td> &nbsp;
                            </td>
                            <td>
                             <input type="text" name="sum_lai" id="sum_lai" style="text-align:right; border:0;font-weight:bold;font-size:12px"/> 
                            </td>
                            <td align="right">  
                            <input type="hidden" name="certserial" id="certserial"/>
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                        </tr>
                      </logic:notEmpty>
                   </logic:present>              
              </table>
           </fieldset>
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

  function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=  jQuery('#nhkb_huyen').val();     
      return nhkb_huyen;
  }
  function nhangval() {
      var ngan_hang;
      ngan_hang=jQuery('#ngan_hang').val();     
      return ngan_hang;
  }
function goPage(page) {
  var f = document.forms[0];
      f.pageNumber.value = page;
      var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
        var idxTK = jQuery('#so_tk option:selected').index();
      f.action = "viewBKeTinhLai.do?pageNumber="+page+"&idxKB="+idxKB+"&idxNH="+idxNH+"&idxTK="+idxTK;
      f.submit();
  }

function tkval() {
      var so_tk;
      so_tk=jQuery('#so_tk').val();     
      return so_tk;
  }

 function check(type) { 
   var f = document.forms[0];
     if (type == 'find') {
        var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
        var idxTK = jQuery('#so_tk option:selected').index();
        var so_tk=jQuery("#so_tk").val();
        var tu_ngay=jQuery("#tu_ngay").val();
        if(so_tk!=null&&""!=so_tk){
            f.action = 'viewBKeTinhLai.do?idxKB='+idxKB+"&idxNH="+idxNH+"&idxTK="+idxTK;      
        }else {
           alert("Chọn số tài khoản cần sao kê.");
           return false;
        }if(tu_ngay.trim()==null || ""==tu_ngay.trim() ){
          alert("Nhập thông tin từ ngày.");
          jQuery("#tu_ngay").focus();
           return false;
        }
     }
     if (type == 'print') {
        var so_tk=jQuery("#so_tk").val();
        var tu_ngay=jQuery("#tu_ngay").val();
        if(so_tk!=null && ""!=so_tk){
            f.action = 'printBKeTinhLai.do';
             var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
              newDialog = window.open('', '_form', params);  
              f.target='_form';
        }else {
           alert("Chọn số tài khoản cần sao kê.");
           return false;
        }if(tu_ngay.trim()==null || ""==tu_ngay.trim() ){
          alert("Nhập thông tin từ ngày.");
          jQuery("#tu_ngay").focus();
           return false;
        }
     }
     if (type == 'close') {
        f.action = 'mainAction.do';      
     } 
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

 function getTenKhoBacDC(id,id_cha) { 

    var qthttw="<%=qthttw%>";
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
      document.getElementById('so_tk').options.length = 1;
       var kb_id;
       if(qthttw!=null && ''!=qthttw){
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
                    if(id == '2' || id == '3')                    
                    jQuery('#nhkb_tinh').val(id);
                    else
                    jQuery('#nhkb_tinh').val(id_cha);
                  }else if (id_cha==null||''==id_cha){
                    kb_id=document.forms[0].nhkb_tinh.value;
                  }
              }
         }else if(qthttw==null || ''==qthttw){
          kb_id=document.forms[0].nhkb_tinh.value;
         }
      var kb_huyen="<%=idxKB%>";   
      var strTinh="<%=qthttw%>";
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
                            getTenNHang();
                          }
                          else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          getTenNHang();
                        }
                        else if(kb_huyen!='0'){
                        jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                          getTenNHang();
                        }
                      }else if(strTinh!=null && strTinh !=''){
                      if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                              getTenNHang(); 
                            }
                         else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          getTenNHang();
                        }
                        else if(kb_huyen!='0'){
                        jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                          getTenNHang();
                        }
                      }
                  }
              }
                if (id!=null && ''!=id){                        
                          jQuery('#nhkb_huyen').val(id);
                          getTenNHang();
                      }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }

function getTenNHang() {
      document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
      document.getElementById('so_tk').options.length = 1;
      var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
       var strTinh="<%=qthttw%>";
       var ngan_hang="<%=idxNH%>";
      if (nhkb_id !=null && ""!=nhkb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucNHangHuyen.do", data :  {
              "nhkb_id" : nhkb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh))
                      });
                      }
                     if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                          if(document.getElementById('ngan_hang').options.length==2){
                            jQuery("#ngan_hang option:eq(0)").remove();
                            getTK_NH_KB();
                          }
                          //HungBm fix load ket qua khong luu lai ten ngan hang va so tk
                          if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
                          jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          getTK_NH_KB();
                          }
                          else if(ngan_hang!='0'){
                          jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
                          getTK_NH_KB();
                          }
                          
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                              getTK_NH_KB();
//                              jQuery("#ngan_hang").attr("disabled", "disabled");
                            }
                        else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                        getTK_NH_KB();
                        }
                        else if(ngan_hang!='0'){
                        jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
                        getTK_NH_KB();
                        }
                      }
                  }          
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }

function getTK_NH_KB() {
      document.getElementById('so_tk').options.length = 1;// clear du lieu option cu
//      jQuery("#ngan_hang").attr("enabled", "enabled");
      var nh_id = document.getElementById("ngan_hang").value;//document.forms[0].nhkb_huyen.value;" +
      var nhkb_id = document.getElementById("nhkb_huyen").value;
       var so_tk="<%=idxTK%>";
       
      if (nh_id !=null && ""!=nh_id){
      jQuery.ajax( {
          type : "POST", url : "getTKNHKB.do", data :  {
              "nh_id" : nh_id,"nhkb_id" : nhkb_id,"typeDisplay" : "ONLY_VND"
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          document.getElementById('so_tk').options.add(new Option(objectDM.so_tk, objectDM.so_tk))

                      });
                      }
                         if(document.getElementById('so_tk').options.length>=2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#so_tk option:eq(1)").attr('selected', true);
                            }
                        else if(so_tk=='0'||so_tk==null||''==so_tk){
                        jQuery('#so_tk option:eq(0)').attr('selected', true);
                        }
                        if(so_tk!='0'){
                        jQuery('#so_tk option:eq('+so_tk+')').attr('selected', true);
                        }
                      }          
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }



</script>