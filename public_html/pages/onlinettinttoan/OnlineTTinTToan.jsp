<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.ltt.LTTTraCuuVO"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.seatech.framework.utils.DateUtils"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuChungTuGDResource"/>
<%@ include file="/includes/ttsp_header.inc"%>

<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"  href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"    type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"     type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<script language="javascript">
  jQuery.noConflict();
  jQuery(document).ready(function(){        
    setTimeout(function(){
      var f = document.forms[0]; 
//      var page=jQuery("#pageNumber").val();
//      if(page==null || '' ==page){
//        page ="1";
//      }
      f.action = 'TTinTToanAction.do';
      jQuery("#frmTTinTToan").submit();
        }, '<%=request.getAttribute("timer_refresh")%>');
     //**************************BUTTON Thoat CLICK********************************************
     jQuery("#btn_thoat").click(function() {
       var frmTTinTToanOnline =jQuery("#frmTTinTToan").attr({'action':'mainAction.do'});
       frmTTinTToanOnline.submit();
     });
  });

  function submitSearch(id){
    if(id=='ma_dv'){ 
      jQuery("#pageNumber").val('1');
    }
    jQuery("#frmTTinTToan").submit();
  }
  function goPage(page) {
  jQuery("#pageNumber").val(page);
    var f = document.forms[0];   
      f.action = 'TTinTToanAction.do?pageNumber='+page;
      jQuery("#frmTTinTToan").submit();
    }
  function getTenKhoBacDC(id,id_cha) { 

     document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id=document.forms[0].nhkb_tinh.value;      
     if (kb_id !=null && ""!=kb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucKBTHopOnlineTToan.do", data :  {
              "kb_id" : kb_id 
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                        // truong hop 1 - luc load khong co thang nao                  
                        document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                       });               
                       var strKBHuyen = '<%=request.getAttribute("strKBHuyen")%>';
                    
                        if(strKBHuyen !=null && strKBHuyen != 'NULL' && strKBHuyen != '' && strKBHuyen != 'null'){
                          jQuery("#nhkb_huyen").val(strKBHuyen);
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
  function nhkb_huyenval() {
      var nhkb_huyen ;
      nhkb_huyen =  document.getElementById("nhkb_huyen").value;     
      return nhkb_huyen;
  }
  jQuery(document).ready(function () {
      var strKBTinh = '<%=request.getAttribute("strKBTinh")%>';
      
      if(strKBTinh != null && strKBTinh != 'NULL' && strKBTinh != '' && strKBTinh != 'null'){ 
        getTenKhoBacDC('','');
      }
  });
  // thêm dialog
 /* jQuery(document).ready(function () {    

      jQuery("#dialog-form-lov-dm").dialog({
          autoOpen: false,resizable : false,
          maxHeight: "700px",
          width: "550px",
          modal: true
      });
      if(jQuery("ma_kb").val() == '0003'){
          try{
            jQuery("#nhkb_huyen").val("0003");
          }catch(ex){}
      }
      
      jQuery("#dialog-confirm").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              "Có" : function () {    
                  // thuc hien update trang thai
                  document.forms[0].action = "thoatView.do";
                  document.forms[0].submit();
                  jQuery(this).dialog("close");
              },
              "Không" : function () {    
                  // thuc hien update trang thai
                  jQuery(this).dialog("close");
              }
          }
      });
      });
      
       function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUUQT");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      }
      */
  
  //Function cho button
    function check(type) { 
      var f = document.forms[0];
     if (type == 'print') {          
        f.action = 'printTDoiTKoanOnline.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
     }
     if (type == 'close') {
        f.action = 'mainAction.do';      
     } 
     f.submit();
  }    
      
 </script>
<title>
  <fmt:message key="tracuu_ctgd.page.title"/>
</title>
<div>
<html:form action="/TTinTToanAction.do" styleId="frmTTinTToan" method="POST">
 <div class="box_common_conten">
     <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>THEO D&#213;I TH&#212;NG TIN THANH TO&#193;N ONLINE</span>
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
       <td >
        <fieldset>
        <legend>&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m</legend>
        <div>
          <table  class="data-grid" id="data-grid" border="1" cellspacing="0" cellpadding="2" style="width:100%" >
              <tr align="center">
                <td align="right" bordercolor="#e1e1e1" width="20%" style="margin-right">Ng&#226;n h&#224;ng&nbsp;:
                  <html:select property="ma_dv" styleId="ma_dv" style="font-size:12px;width:70%" onchange="submitSearch();" onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n ng&#226;n h&#224;ng---</html:option>
                      <html:optionsCollection name="colDMNH" value="ma_dv" label="ten_nh"/>
                  </html:select>
                </td>
                
                <td align="right" bordercolor="#e1e1e1" width="10%">Loại tiền</td>
                <td align="left" bordercolor="#e1e1e1" width="20%">  <html:select property="ma_nt" styleId="ma_nt" onchange="submitSearch();"  onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="VND">VND</html:option>
                      <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
                  </html:select>
                </td>
               
                <td align="left" bordercolor="#e1e1e1" width="25%">&#272;&#417;n v&#7883; ti&#7873;n t&#7879;&nbsp;:
                  <html:select property="dv_tien" styleId="dv_tien" onchange="submitSearch();" onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="1" >&#272;&#7891;ng</html:option>
                      <html:option value="1000">Ngh&#236;n &#273;&#7891;ng</html:option>
                      <html:option value="1000000">Tri&#7879;u &#273;&#7891;ng</html:option>
                      <html:option value="1000000000">T&#7927; &#273;&#7891;ng</html:option>
                  </html:select>
                </td>   
              </tr>
              <tr>
                <td align="right" bordercolor="#e1e1e1" style="margin-right">Kho Bạc tỉnh:
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:70%"  onchange="getTenKhoBacDC('',''); submitSearch();"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                      <html:option value="">---Chọn Kho Bạc tỉnh---</html:option>
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                </td>
                <td align="right" bordercolor="#e1e1e1" style="margin-right">Kho Bạc Huyện</td>
                 <td align="left" bordercolor="#e1e1e1"> <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:70%"
                               onchange="nhkb_huyenval(); submitSearch(); "
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">
                              <html:option value="">---Chọn Kho Bạc huyện---</html:option>                              
                  </html:select>
                </td>
                <td align="left" bordercolor="#e1e1e1" style="margin-right">
                <logic:notEmpty name="colTTinTToan">
                  <button type="button" onclick="check('print');" class="ButtonCommon" accesskey="i" >
                          <span class="sortKey">I</span>n 
                  </button>
              </logic:notEmpty> 
                
                </td>
              </tr>
           </table>
         </div>
        </fieldset>
       </td>
      </tr>
      <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
            <div style="height:50px;overflow-y: scroll;width:100%">
              <table cellspacing="0" width="100%" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse;table-layout:fixed">
                <thead >
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center">
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="12%">
                  <div align="center" >
                    T&#234;n KBNN
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="4%">
                  <div align="center">
                    M&#227; KBNN
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="6%">
                  <div align="center">
                    M&#227; CNNH
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Lo&#7841;i t&#224;i kho&#7843;n
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    S&#7889; hi&#7879;u t&#224;i kho&#7843;n
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                   H&#7841;n m&#7913;c s&#7889; d&#432; n&#7907; TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="11%">
                  <div align="center" >
                  D&#432; &#273;&#7847;u ng&#224;y
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                  T&#7893;ng PS thu
                  </div>
                </th>
                <th class="promptText"  bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                    T&#7893;ng PS chi
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0"  width="12%">
                  <div align="center">
                   S&#7889; d&#432; t&#7841;i th&#7901;i &#273;i&#7875;m
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="7%">
                  <div align="center" >
                   Th&#7901;i gian
                  </div>
                </th>
                <tr>
                 <logic:notEmpty name="colSum">
                 <logic:iterate id="items" name="colSum" indexId="stt">
                <td colspan="6" align="right"> 
                <b>Tổng số </b>
                </td>
                <td align="right">
                    <font color="Red"><b>
                    
                    <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:setLocale value="vi_VI"/>
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="han_muc_no"/>
                    </fmt:formatNumber>
                    </logic:equal>
                    <logic:notEqual property="ma_nt" name="items" value="VND">
                      <fmt:setLocale value="en_US"/>
                      <fmt:formatNumber type="currency" currencySymbol="">
                        <bean:write  name="items" property="han_muc_no"/>
                      </fmt:formatNumber>
                    </logic:notEqual>
                    
                    </b></font> 
                </td>
                <td align="right">
                    <font color="Red"><b>
                    
                    <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write  name="items" property="du_dau_ngay"/>
                    </fmt:formatNumber>
                    </logic:equal>
                    <logic:notEqual property="ma_nt" name="items" value="VND">
                      <fmt:formatNumber type="currency" currencySymbol="">
                        <bean:write  name="items" property="du_dau_ngay"/>
                      </fmt:formatNumber>
                    </logic:notEqual>
                    
                    </b></font> 
                </td>
                <td align="right">
                    <font color="Red"><b>
                    
                    <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write  name="items" property="ps_thu"/>
                    </fmt:formatNumber>
                    </logic:equal>
                    <logic:notEqual property="ma_nt" name="items" value="VND">
                      <fmt:formatNumber type="currency" currencySymbol="">
                        <bean:write  name="items" property="ps_thu"/>
                      </fmt:formatNumber>
                    </logic:notEqual>
                    
                    </b></font>
                </td>
                <td align="right">
                  
                  <font color="Red"><b>
                  
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write  name="items" property="ps_chi"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write  name="items" property="ps_chi"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  
                  </b></font>
                  </td>
                  <td align="right">
                  <font color="Red"><b>
                  
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="chenh_lech"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="chenh_lech"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  
                  </b></font>
                </td>
                <td>
                </td>
            </logic:iterate>
          </logic:notEmpty>
            </tr>
            </thead>
            </table>
            </div>
            <div style="height:400px;overflow-y: scroll;width:100%">
            <table cellspacing="0" width="100%" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse;table-layout:fixed">
              <tbody class="navigateable focused"  cellspacing="0"   cellpadding="1" bordercolor="#e1e1e1" id="tbodyTTinTToan">    
              <logic:notEmpty name="colTTinTToan">
                <logic:iterate id="items" name="colTTinTToan" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"  width="3%">
                   <%=stt+1%>
                  </td>
                  <td  width="12%">
                      <bean:write name="items" property="ten_kb"/>
                  </td>
                  <td align="center"  width="4%">
                    <bean:write name="items" property="ma_kb"/>
                  </td>
                  <td align="center"  width="6%">
                    <bean:write name="items" property="ma_nh"/>
                  </td>
                  <td align="center"  width="5%">
                    <logic:equal value="01" property="loai_tk" name="items">
                        TK TG
                    </logic:equal>
                    <logic:equal value="02"  property="loai_tk" name="items">
                       TK TT
                    </logic:equal>
                    <logic:equal value="03"  property="loai_tk" name="items">
                       TK CT
                    </logic:equal>
                  </td>
                  <td align="center"  width="10%">
                   <bean:write name="items" property="so_tk" />
                  </td>
                  <td align="right"   width="10%">
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="han_muc_no"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="han_muc_no"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  </td>
                  <td align="right" width="11%">
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="du_dau_ngay"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="du_dau_ngay"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  </td>
                  <td align="right"   width="10%">
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="ps_thu"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="ps_thu"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  </td>
                  <td align="right"   width="10%">
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="ps_chi"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="ps_chi"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  </td>
                  <td align="right"    width="12%">
                  <logic:equal property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="items" property="chenh_lech"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="ma_nt" name="items" value="VND">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="items" property="chenh_lech"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                  </td>
                  <td align="center" width="7%">
                   <bean:write name="items" property="ngay_tao" format="dd-MM-yyyy HH:mm:ss" />
                  </td>
                </tr>
                </logic:iterate>
              </logic:notEmpty>
              <logic:empty name="colTTinTToan">
                <tr><td colspan="11">
                  <font color="red">Kh&#244;ng b&#7843;n c&#243; ghi</font>
                </td>
                </tr>
              </logic:empty> 
              </tbody>   
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
      
      <tr>
        <td align="right">
          <button type="button" id="btn_thoat" class="ButtonCommon" accesskey="o">Th<span class="sortKey">o</span>&#225;t</button>
        </td>
      </tr>
      
    </table> 
  </div>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>