<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.dchieu.THopQTThuChiVO"%>

<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.ReportsResource"/>
<title>Báo cáo tổng hợp số thu - chi toàn quốc</title>
<%
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String tctinh = request.getAttribute("tctinh")==null?"":request.getAttribute("tctinh").toString();
%>
<%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).init(function () {
      getTenKhoBac();
     // getTenNHang();
      
      jQuery("#dialog-form-lov-dm").dialog({
          autoOpen: false,resizable : false,
          maxHeight: "700px",
          width: "550px",
          modal: true
      });      
  });
  //************************************ LOAD PAGE **********************************
    function callLov(){      
        jQuery("#loai_lov").val("DMKBTCUU");
        jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
        jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
        jQuery("#id_field_id_lov").val("id_nhkb_huyen");
        jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
        jQuery("#dialog-form-lov-dm").dialog( "open" );
    }
  function check(type) {
        var f = document.forms[0];
        f.target = '';
        if (type == 'close') {
            f.action = 'mainAction.do';
        }
        f.submit();
    }
  function formAction(vaction) {
      var f = document.forms[0];
      var nhkb_tinh=jQuery('#nhkb_tinh').val();
      var nhkb_huyen=jQuery('#nhkb_huyen').val();
      var ngan_hang=jQuery('#ngan_hang').val();
      
     if( ''== ngan_hang||ngan_hang ==null){
        alert(GetUnicode('Phải chọn ngân hàng.'));
        document.getElementById("ngan_hang").focus();
        return false;
      } else if( check_tuNgay_denNgay('tu_ngay','den_ngay')){        
        document.getElementById("ma_ngan_hang").value = document.getElementById("ngan_hang").value;
        if(vaction =='print'){
            var params = ['scrollbars=1,height=' + (screen.height - 100), 'width=' + screen.width].join(',');
            newDialog = window.open('', '_form', params);
            f.target = '_form';
            f.action = 'PrintBCTHopQTThuChi.do';
            f.submit();
        }else if(vaction =='view'){           
           var inKB = jQuery('#nhkb_huyen option:selected').index(); 
           var inNH = jQuery('#ngan_hang option:selected').index() ;
            f.pageNumber.value = '1';
            f.action = 'ViewBCTHopQTThuChi.do?inKB='+inKB+"&inNH="+inNH
            f.submit();
        }
      }
  }
  function goPage(page) {
      var inKB = jQuery('#nhkb_huyen option:selected').index(); 
      var inNH = jQuery('#ngan_hang option:selected').index() ;
      f.pageNumber.value = page;
      f.action = 'ViewBCTHopQTThuChi.do?inKB='+inKB+"&inNH="+inNH
      f.submit();
  }
  
</script>
<div class="app_error">
  <html:errors/>
</div>
<html:form styleId="PrintBCTHopQTThuChi" action="/PrintBCTHopQTThuChi.do">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Báo cáo tổng hợp số thu - chi toàn quốc</span>
        </td>
        <td width="62">
          <img width="62" height="30"
               src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img>
        </td>
        <td width="20%"
            background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4">
          <img width="4" height="30"
               src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
        </td>
      </tr>
    </tbody>
  </table>
  <table style="BORDER-COLLAPSE: collapse; border: solid #e1e1e1;" border="1" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          Thông tin in báo cáo
        </td>
      </tr>
      <tr>
        <td>
        <table>
             <tr>
              <td width="20%" align="right" bordercolor="#e1e1e1">KBNN tỉnh:&nbsp;</td>
              <td width="25%" bordercolor="#e1e1e1">
                <html:select property="nhkb_tinh" styleId="nhkb_tinh"
                             onchange="getTenKhoBac()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:90%">
                  <%if(request.getAttribute("dftinh") != null){
                                 %>
                  <html:option value="">-----Chọn th&ocirc;ng tin KBNN tỉnh-----</html:option>
                  <%}%>
                  <html:optionsCollection name="dmuckb_tinh" value="id_cha"
                                          label="kb_tinh"/>
                </html:select>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">KBNN huyện:&nbsp;</td>
              <td bordercolor="#e1e1e1">
                <html:select property="nhkb_huyen" styleId="nhkb_huyen"
                             onchange="nhkb_huyenval(); "
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:90%">
                  <html:option value="">-----Chọn th&ocirc;ng tin KBNN huyện-----</html:option>
                </html:select>
              </td>
              <td rowspan="3" bordercolor="#e1e1e1">                      
                     <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">D</span>anh m&#7909;c KB
                     </button>
              </td>
              </tr>
              <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">Ng&acirc;n
                                                                      h&agrave;ng: &nbsp;</td>
                  <td bordercolor="#e1e1e1">
                    <html:select property="ngan_hang" styleId="ngan_hang" style="width:90%"
                                 onchange="nhangval()"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">
                      <html:option value="">-----Chọn th&ocirc;n tin ng&acirc;n
                                            h&agrave;ng-----</html:option>
                       <html:optionsCollection name="dmNH" value="ma_dv" label="ten_nh"/>  
                    </html:select>
                    
                    <html:hidden property="ma_ngan_hang" styleId="ma_ngan_hang"/> 
                  </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
                  <td bordercolor="#e1e1e1">
                    <html:select property="loai_tien" styleId="loai_tien" style="width:90%"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">
                      <html:option value="VND">VND</html:option>
                      <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
                    </html:select>
                  </td>
                </tr>
                <tr>                 
                  
                    <td align="right" width="15%" bordercolor="#e1e1e1">
                      <label for="tu_ngay">
                       Từ ngày
                      </label>
                    </td>
                    <td bordercolor="#e1e1e1">
                      <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                                 onkeypress="return numbersonly(this,event,true) "
                                 onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                  style="WIDTH: 70px;vertical-align:middle;"/>
                       
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                           border="0" id="tu_ngay_btn"
                           style="vertical-align:middle;width:20"/>   
                          <script type="text/javascript">
                            Calendar.setup( {
                                inputField : "tu_ngay", // id of the input field
                                ifFormat : "%d/%m/%Y", // the date format
                                button : "tu_ngay_btn"// id of the button
                            });
                          </script>
                    </td>
                                      <td align="right" width="15%" bordercolor="#e1e1e1">
                      <label for="tu_ngay">
                       Đến ngày
                      </label>
                    </td>
                    <td bordercolor="#e1e1e1">
                      <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                                 onkeypress="return numbersonly(this,event,true) "
                                 onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                  style="WIDTH: 70px;vertical-align:middle;"/>
                       
                      <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                    </td>
                </tr>
        </table>
        </td>
      </tr>
      
     
        

      <tr>
        <td colspan="4" align="center" valign="top">
          <div style="padding:10px 0px 10px 0px;vertical-align:top; ">
            <button type="button"  onclick="formAction('view');"
                    accesskey="i" class="ButtonCommon">
              Tra cứu
            </button>
            <button type="button"  onclick="formAction('print');"
                    accesskey="i" class="ButtonCommon">
              <fmt:message key="reports.page.button.in"/>
            </button>
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
   <html:hidden property="pageNumber" value="1"/>
</html:form>

<logic:notEmpty name="lstthuchi">
<fieldset>
    <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
   
 
        <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                  style="BORDER-COLLAPSE: collapse;table-layout:fixed">
          <tr>
          <th class="promptText" bgcolor="#f0f0f0" width="3%">
            <div align="center" >
              STT 
            </div>
           </th>
          <th class="promptText" bgcolor="#f0f0f0" width="8%">
            <div align="center" >
              Ngày
            </div>
           </th>
         <th class="promptText" bgcolor="#f0f0f0" width="24%">
            <div align="center" >
              Tên KB
            </div>
           </th>
          <th class="promptText" bgcolor="#f0f0f0" width="27%">
            <div align="center" >
              Tên NH
            </div>
           </th>
           <th class="promptText" bgcolor="#f0f0f0" width="8%">
            <div align="center" >
              Loại tiền
            </div>
           </th>
          <th class="promptText" bgcolor="#f0f0f0" width="15%">
            <div align="center" >
              Tổng thu<br/>(TTSP+PHT)
            </div>
           </th>
            <th class="promptText" bgcolor="#f0f0f0" width="15%">
            <div align="center" >
              Tổng chi
            </div>
           </th>
           </tr>
        <logic:present name="lstthuchi" >          
      <logic:iterate id="items" name="lstthuchi" indexId="stt">
          <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
            <td align="center"> 
                    <%=stt+1+rowBegin%>
              </td>
            <td align="center">
                    <bean:write name="items" property="ngay_dc"/>
            </td>
            <td>
                    <bean:write name="items" property="ten_kb"/>
            </td>
            <td>
                    <bean:write name="items" property="ten_nh"/>
            </td>
            <td align="center">
                    <bean:write name="items" property="loai_tien"/>
            </td>
            <td align="right">
              <fmt:setLocale value="vi_VI"/>
               <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                    <bean:write name="items" property="tong_ps_thu"/>
                </fmt:formatNumber>
                 <input type="hidden" name="hd_tong_ps_thu" value = "<bean:write name="items" property="tong_ps_thu"/>"/>
            </td>
            <td align="right">
               <fmt:setLocale value="vi_VI"/>
               <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                    <bean:write name="items" property="tong_ps_chi"/>
                </fmt:formatNumber>
                <input type="hidden" name="hd_tong_ps_chi" value = "<bean:write name="items" property="tong_ps_chi"/>"/>
            </td>            
          </tr>          
       </logic:iterate>
      </logic:present> 
          <tr>
          <%
           THopQTThuChiVO thuchi= (THopQTThuChiVO)request.getAttribute("thchitong");
          %>
          <td colspan="5" align="center"><b>Tổng cộng</b></td>          
          <td align="right"><b>
                <fmt:setLocale value="vi_VI"/>
               <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                  <%=thuchi.getTong_ps_thu()%>
                </fmt:formatNumber>
          </b></td>
          <td align="right"><b>
            <fmt:setLocale value="vi_VI"/>
               <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
          <%=thuchi.getTong_ps_chi()%>
          </fmt:formatNumber>
          </b></td>
          </tr>
          <tr>
            <td colspan="7">                 
           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
            </td>
          </tr>
      </table>
      
 </fieldset>
</logic:notEmpty>

<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
  
 
  
  function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen = document.getElementById("nhkb_huyen").value;
      return nhkb_huyen;
  }

  function nhangval() {
      var ngan_hang;
      selectNH = document.getElementById("ngan_hang");
      ngan_hang = selectNH.value; 
      valueSelected = selectNH.options[selectNH.selectedIndex].value;
      jQuery("#ma_ngan_hang").val(valueSelected);
      return ngan_hang;
  }

  function getTenKhoBac() {    
      var kb_id = document.forms[0].nhkb_tinh.value;

      var kb_huyen = "<%=kb_huyen%>";
      var strTinh = "<%=strTinh%>";
      if (kb_id == null || "" == kb_id) {
       
      }
      else if (kb_id != null && "" != kb_id) {
          jQuery.ajax( {
              type : "POST", url : "getDMucKBLTT.do", data :  {
                  "kb_id" : kb_id
              },
              success : function (data, textstatus) {
                  if (textstatus != null && textstatus == 'success') {
                      if (data != null) {
                          ObjectKB_Huyen = JSON.parse(data[0])
                          jQuery.each(ObjectKB_Huyen, function (i, objectDM) {
                              // truong hop 1 - luc load khong co thang nao                  
                              document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                          });
                          if (strTinh == null || strTinh == '') {
                              // request set dftinh ==null
                              if (document.getElementById('nhkb_huyen').options.length == 2) {
                                  jQuery("#nhkb_huyen option:eq(0)").remove();
                                 // getTenNHang();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('nhkb_huyen').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                                 // getTenNHang();
                              }
                              else if (kb_huyen == '0' || kb_huyen == null || '' == kb_huyen) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                                 // getTenNHang();
                              }
                              else if (kb_huyen != '0') {
                                  jQuery('#nhkb_huyen option:eq(' + kb_huyen + ')').attr('selected', true);
                                 // getTenNHang();
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
  }

      function getTenNHang(kbid) {
          document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
          var nhkb_id
          if(kbid == null || kbid == ''){
              nhkb_id = document.getElementById("nhkb_huyen").value//document.forms[0].nhkb_huyen.value;" +nhkb_id = kbid;
          }else{
              nhkb_id = kbid;
          }
          var strTinh = "<%=strTinh%>";
          var ngan_hang = "<%=ngan_hang%>";
          if (nhkb_id != null && "" != nhkb_id) {
              jQuery.ajax( {
                  type : "POST", url : "getDMucNHLTT.do", data :  {
                      "nhkb_id" : nhkb_id
                  },
                  success : function (data, textstatus) {
                      if (textstatus != null && textstatus == 'success') {
                          if (data != null) {
                              jQuery.each(data, function (i, objectDM) {
                                  document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh));
                              });
                          }
                          if (strTinh == null || strTinh == '') {
                              // request set dftinh ==null
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  jQuery("#ngan_hang option:eq(0)").remove();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('ngan_hang').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                              }
                              else if (ngan_hang == '0' || ngan_hang == null || '' == ngan_hang) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                              }
                              else if (ngan_hang != '0') {
                                  jQuery('#ngan_hang option:eq(' + ngan_hang + ')').attr('selected', true);
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
      
      
  function getTenKhoBacDC(id,id_cha) { 
    var tctinh="<%=tctinh%>";
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
       var kb_id;
       if(tctinh!=null && ''!=tctinh){
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
         }else if(tctinh==null || ''==tctinh){
          kb_id=document.forms[0].nhkb_tinh.value;
         }
      var kb_huyen="<%=kb_huyen%>";   
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
                            //getTenNHang(id)
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                             // getTenNHang(id)
                         }
                        else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                        //getTenNHang(id)
                        }
                        else if(kb_huyen!='0'){
                            jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                         //   getTenNHang(id)
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
  
  function tong_thu_chi(){
    var hd_tong_ps_chi = document.getElementsByName("hd_tong_ps_chi");
    var hd_tong_ps_thu = document.getElementsByName("hd_tong_ps_thu");
    var tong_thu = 0;
    var tong_chi = 0;
    if(hd_tong_ps_thu != null){
      for(var i = 0; i<hd_tong_ps_thu.length; i++){
        tong_thu += Number(hd_tong_ps_thu[i].value);
        tong_chi += Number(hd_tong_ps_chi[i].value);
      }
    }
    document.getElementById("tong_chi").innerHTML = changeVNDCurrency(''+tong_chi);
    document.getElementById("tong_thu").innerHTML = changeVNDCurrency(''+tong_thu);
  }
   //xu ly dinh dang tien te viet nam
  function changeVNDCurrency(nStr){
    nStr += '';
    x1 = nStr;
      x1 = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    return x1;
  }   
</script>