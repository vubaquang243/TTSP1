
<%@ page contentType="text/html; charset=UTF-8"%>


<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/sketk.js"
        type="text/javascript">        
</script>
<%
  String nhkb_tinh = request.getAttribute("nhkb_tinh")!=null?request.getAttribute("nhkb_tinh").toString():"";
  String nhkb_huyen = request.getAttribute("nhkb_huyen")!=null?request.getAttribute("nhkb_huyen").toString():"";
  String ma_nh = request.getAttribute("ma_nh")!=null?request.getAttribute("ma_nh").toString():"";
  String so_tk = request.getAttribute("so_tk")!=null?request.getAttribute("so_tk").toString():"";
  String tu_ngay = request.getAttribute("tu_ngay")!=null?request.getAttribute("tu_ngay").toString():"";
//  String den_ngay = request.getAttribute("tu_ngay")!=null?request.getAttribute("den_ngay").toString():"";
%>
<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function () {
      var frmSKTK = jQuery("#frmSKTK");
      frmSKTK.target='';
      jQuery("#nhkb_tinh").focus();
      var tu_ngay = '<%=tu_ngay%>';
      var nhkb_tinh = '<%=nhkb_tinh%>';
      var nhkb_huyen = '<%=nhkb_huyen%>';
      var ma_nh = '<%=ma_nh%>';
      var so_tk = '<%=so_tk%>';
      if(so_tk!=''){
        jQuery("#sotk").val(so_tk);
      }
      if(ma_nh!=''){
        jQuery("#manh").val(ma_nh);
      }
      if(nhkb_huyen!=''){
        jQuery("#nhkb_huyen").val(nhkb_huyen);
      }
      if(nhkb_tinh!=''){
        jQuery("#nhkb_tinh").val(nhkb_tinh);
      }
      if(tu_ngay!=''){
        jQuery("#tu_ngay").val('<%=tu_ngay%>');
      }

      
       try{
          jQuery('#manh').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#manh').val(this.value.replace(matches, ''),true);
         });
         jQuery('#ngay').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#ngay').val(this.value.replace(matches, ''),true);
         });
         jQuery('#thang').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#thang').val(this.value.replace(matches, ''),true);
         });
         jQuery('#nam').keyup(function () {
            var matches = /[^0-9]/g;
            jQuery('#nam').val(this.value.replace(matches, ''),true);
         });
        }catch(e){
         // e.print();
        }
        //dialog confirm message	

        jQuery("#btn_Exit").click(function () {
          document.forms[0].target = '';
          document.forms[0].action = 'thoatView.do';
          document.forms[0].submit();
      });
    });
    function findSK() {
        document.forms[0].target = '';
        if(jQuery("#manh").val()==null || ''==jQuery("#manh").val() || jQuery("#manh").val()=='null'){
          alert(GetUnicode('Kh&#244;ng th&#7875; t&#236;m ki&#7871;m v&#236; kh&#244;ng c&#243; m&#227; ng&#226;n h&#224;ng')); 
          return;
        }else if(jQuery("#sotk").val()==null || ''==jQuery("#sotk").val() || jQuery("#sotk").val()=='null'){
          alert(GetUnicode('Kh&#244;ng th&#7875; t&#236;m ki&#7871;m v&#236; kh&#244;ng c&#243; s&#7889; t&#224;i kho&#7843;n')); 
          return;
        }
        var url = "skeTKListAction.do";
        document.forms[0].action = url;
        document.forms[0].submit();
    }
    function getSoTK(){   
//      jQuery("#sotk option").remove();
        document.forms[0].target = '';
        var manh =jQuery("#manh").val();        
        var makb = jQuery("#nhkb_huyen").val();
        jQuery("#sotk").find('option').remove().end();      

        if(manh==null || manh=='' || manh=='undefined'){
          alert('Khong tim duoc ma Ngan Hang!');
          return;
        }
        if(makb==null || makb=='' || makb=='undefined'){
          alert('Khong tim duoc ma Kho Bac!');
          return;
        }
        jQuery.ajax( {
          type : "POST", url : "skeTKAction.do", data :  {
              action:"Find_TK","manh":manh,"makb":makb, "nd" : Math.random() * 100000
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {                                        
                      if (data.logout != null && data.logout) {                      
                          document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                          document.forms[0].submit();
                      }
                      else {                      
                          var lstTKNHang = new Object();                           
                          lstTKNHang = JSON.parse(data[0]);                          
                          var lstTKNHangSize = lstTKNHang.size();                          
                          if(lstTKNHangSize>0){
                            for(var i=0;i<lstTKNHangSize;i++){
                              jQuery('#sotk').append('<option value="' + lstTKNHang[i].so_tk + '" > '+ lstTKNHang[i].so_tk + '<\/option>');
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
    function getNganHang(){
      document.forms[0].target = '';
      var sizeManh = jQuery("#manh").length;
      if(sizeManh == 1){
        jQuery("#manh option:eq(0)").remove();
      }
      var sizeSoTK = jQuery("#sotk").length;
      if(sizeSoTK == 1){
        jQuery("#sotk option:eq(0)").remove();
      }
        var nhkb_huyen=jQuery("#nhkb_huyen").val();
        if(nhkb_huyen==null || nhkb_huyen=='' || nhkb_huyen=='undefined'){
          return;
        }
        jQuery.ajax( {
          type : "POST", url : "skeTKAction.do", data :  {
              action:"Find_NH",nhkb_huyen:nhkb_huyen, "nd" : Math.random() * 100000
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      if (data.logout != null && data.logout) {
                          document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                          document.forms[0].submit();
                      }
                      else {
                          var lstDSNHang = new Object(); 
                          lstDSNHang = JSON.parse(data[0]);
                          var lstDSNHangSize = lstDSNHang.size();
                          if(lstDSNHangSize>0){
                            for(var i=0;i<lstDSNHangSize;i++){
                              jQuery('#manh').append('<option value="' + lstDSNHang[i].ma_nh + '" >' + lstDSNHang[i].ten + '<\/option>');
                            }
                          }
                          var lstTKNHang = new Object(); 
                          lstTKNHang = JSON.parse(data[1]);
                          var lstTKNHangSize = lstDSNHang.size();
                          if(lstTKNHangSize>0){
                            for(var i=0;i<lstDSNHangSize;i++){
                              var typeTk = '';
                              if(lstTKNHang[i].loai_tk == '01') {
                                typeTk = 'TG'
                              } else if(lstTKNHang[i].loai_tk == '02') {
                                typeTk = 'TT'
                              } else if(lstTKNHang[i].loai_tk == '03'){
                                typeTk = 'CT'
                              }                               
                              jQuery('#sotk').append('<option value="' + lstTKNHang[i].so_tk + '" > ( ' +typeTk+ ' - ' + lstTKNHang[i].ma_nt + ' ) ' + lstTKNHang[i].so_tk + '<\/option>');
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
    function getKBHuyen(){
//    jQuery("#nhkb_huyen").find('option').remove().end().append('<option value="" >--- Chọn kho bạc huyện ---<\/option>');
//    jQuery("#manh").find('option').remove().end().append('<option value="" >--- Chọn ngân hàng kho bạc huyện ---<\/option>');
//    jQuery("#sotk").find('option').remove().end().append('<option value="" >--- Chọn tài khoản ---<\/option>');
      jQuery("#nhkb_huyen").find('option').remove().end();
      jQuery("#manh").find('option').remove().end();
      jQuery("#sotk").find('option').remove().end();
      var nhkb_tinh=jQuery("#nhkb_tinh").val();
      if(nhkb_tinh==null || nhkb_tinh=='' || nhkb_tinh=='undefined'){
        return;
      }
      jQuery.ajax( {
        type : "POST", url : "skeTKAction.do", data :  {
            action:"Find_KB_Huyen",nhkb_tinh:nhkb_tinh, "nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        var lstDMKBHuyen = new Object(); 
                        lstDMKBHuyen = JSON.parse(data[0]);
                        var lstDSNHang = new Object(); 
                        lstDSNHang = JSON.parse(data[1]);
                        var lstTKNHang= new Object(); 
                        lstTKNHang = JSON.parse(data[2]);
                        
                        var lstDMKBHuyenSize = lstDMKBHuyen.size();
                        
                        if(lstDMKBHuyenSize>0){
                          for(var i=0;i<lstDMKBHuyenSize;i++){
                            jQuery('#nhkb_huyen').append('<option value="' + lstDMKBHuyen[i].ma + '" >' + lstDMKBHuyen[i].ten + '<\/option>');
                          }
                        }else{
                          jQuery("#nhkb_huyen").append('<option value="" >--- Chọn kho bạc huyện ---<\/option>')
                        }
                        var lstDSNHangSize = lstDSNHang.size();
                        if(lstDSNHangSize>0){
                          for(var i=0;i<lstDSNHangSize;i++){
                              jQuery('#manh').append('<option value="' + lstDSNHang[i].ma_nh + '" >' + lstDSNHang[i].ten + '<\/option>');
                              
//                              var typeTk = '';
//                              if(lstTKNHang[i].loai_tk == '01') {
//                                typeTk = 'TG'
//                              } else if(lstTKNHang[i].loai_tk == '02') {
//                                typeTk = 'TT'
//                              } else if(lstTKNHang[i].loai_tk == '03'){
//                                typeTk = 'CT'
//                              }                               
//                              jQuery('#sotk').append('<option value="' + lstTKNHang[i].so_tk + '" > ( ' +typeTk+ ' - ' + lstTKNHang[i].ma_nt + ' ) ' + lstTKNHang[i].so_tk + '<\/option>');
                          }
                        }
                        if(lstTKNHang != null){
                          var lstTKNHangSize= lstTKNHang.size();
                          if(lstTKNHangSize>0){
                            for(var i=0;i<lstTKNHangSize;i++){
                              var typeTk = '';
                              if(lstTKNHang[i].loai_tk == '01') {
                                typeTk = 'TG'
                              } else if(lstTKNHang[i].loai_tk == '02') {
                                typeTk = 'TT'
                              } else if(lstTKNHang[i].loai_tk == '03'){
                                typeTk = 'CT'
                              }                               
                              jQuery('#sotk').append('<option value="' + lstTKNHang[i].so_tk + '" > ( ' +typeTk+ ' - ' + lstTKNHang[i].ma_nt + ' ) ' + lstTKNHang[i].so_tk + '<\/option>');
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
    function goPage(page) {
      jQuery("#frmSKTK").target='';
      jQuery("#frmSKTK").attr( {
          action : 'skeTKListAction.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmSKTK").submit();
    }
   function inSK(){        
        var loai_lenh_field = jQuery("#loai_lenh").val();
        var f = document.forms[0];
           f.action = "skeTKPrintAction.do";
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);          
        f.target='_form';
        f.submit();
    }
    function chotSoSaoKe(){        
        document.forms[0].target = '';
        if(jQuery("#manh").val()==null || ''==jQuery("#manh").val() || jQuery("#manh").val()=='null'){
          alert(GetUnicode('Kh&#244;ng th&#7875; t&#236;m ki&#7871;m v&#236; kh&#244;ng c&#243; m&#227; ng&#226;n h&#224;ng')); 
          return;
        }else if(jQuery("#sotk").val()==null || ''==jQuery("#sotk").val() || jQuery("#sotk").val()=='null'){
          alert(GetUnicode('Kh&#244;ng th&#7875; t&#236;m ki&#7871;m v&#236; kh&#244;ng c&#243; s&#7889; t&#224;i kho&#7843;n')); 
          return;
        }
        var url = "skeTKUpdateAction.do";
        document.forms[0].action = url;
        document.forms[0].submit();
    }
    
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.SaoKeTKResource"/>
<html:form styleId="frmSKTK" action="/skeTKAction.do">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          <fmt:message key="SaoKe.TK.page.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table> 
    <table width="99%">
              <tbody>
              <tr>
                <td><font color="red">
                    <html:errors/>
                  </font> 
                </td></tr>
              </tbody>
            </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="SaoKe.page.dieukien"/>
        </td>
      </tr>
   </tbody>    
  </table>
  <% String message = request.getParameter("message") != null ? request.getParameter("message").toString() : ""; %>
  <input type="hidden" value="<%=message%>" id="message" />
  <table  class="tableBound">
    
    <tbody>
      <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">
          <fmt:message key="SaoKe.page.label.kbtinh"/>
        </td>
        <td width="25%">
          <html:select property="nhkb_tinh" style="width:90%" styleId="nhkb_tinh" onchange="getKBHuyen()" 
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
                       <%
                        if(request.getAttribute("lstDSKBTinh")!=null){
                      %>
                        <html:optionsCollection  name="lstDSKBTinh" value="ma" label="ten"/>  
                      <%
                      }else{
                      %>
                        <html:option value=""><fmt:message key="SaoKe.page.label.tracuu.kbtinh"/></html:option>
                      <%
                      }
                      %>
          </html:select>
        </td>
        <td width="15%" align="right" bordercolor="#e1e1e1">
          <fmt:message key="SaoKe.page.label.kbhuyen"/>&nbsp;
        </td>
        <td>
          <html:select property="nhkb_huyen"  styleId="nhkb_huyen" style="width:90%;height:20px" onchange="getNganHang()"
                        onkeydown="if(event.keyCode==13) event.keyCode=9;">
                      <%
                        if(request.getAttribute("lstDSKBHuyen")!=null){
                      %>
                      <html:optionsCollection  name="lstDSKBHuyen" value="ma" label="ten"/>
                      <%
                      }else{
                      %>
                      <html:option value=""><fmt:message key="SaoKe.page.label.tracuu.kbhuyen"/></html:option>
                      <%
                      }
                      %>
        </html:select>
          
        </td>
      </tr>
      <tr>
        <td width="15%" align="right" bordercolor="#e1e1e1">
          <fmt:message key="SaoKe.page.label.nh"/>&nbsp;
        </td>
        <td>
          <html:select property="manh" styleId="manh" style="width:90%" onchange="getSoTK()"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
              <%
                if(request.getAttribute("lstDSNHang")!=null){
              %>
              <html:optionsCollection  name="lstDSNHang" value="ma_nh" label="ten"/>
              <%
              }else{
              %>
              <html:option value=""><fmt:message key="SaoKe.page.label.tracuu.nh"/></html:option>
              <%
              }
              %>
              <%--<html:optionsCollection name="dmucNH" value="ma_nh" label="ten_nh"/>--%>
          </html:select>
        </td>
        <td align="right" width="10%">
          <label for="sotk">
            <fmt:message key="SaoKe.page.dieukien.lablel.TK"/>
          </label>
        </td>
         <td width="30%">
          <html:select tabindex="101" styleClass="selectBox" property="sotk" styleId="sotk" style="width:90%" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="loadAccountDetailAJAX()" >
              <%if(request.getAttribute("lstTKNHang")!=null){ %>
                <html:optionsCollection  name="lstTKNHang" value="so_tk" label="comboBoxPresentTK"/>
              <%}else{ %>
                <html:option value=""><fmt:message key="SaoKe.page.label.tracuu.tk"/></html:option>
              <%} %>
          </html:select>
        </td>
      </tr>
      <tr>
         <td align="right" width="10%">
          <label for="nh">
            <fmt:message key="SaoKe.page.dieukien.lablel.tu_ngay"/>
          </label>
        </td>
        <td  width="30%">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 30%;"/>
           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="10%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" width="10%">
          Loại tiền
        </td>
         <td width="30%">
            <html:select property="loai_tien" styleId="loai_tien" onkeydown="if(event.keyCode==13) event.keyCode=9;">
              <%
                        if(request.getAttribute("lstTKNHang")!=null){
                      %>
                      <html:optionsCollection  name="lstTKNHang" value="ma_nt" label="ma_nt"/>
                      <%
                      }else{
                      %>
                      <html:option value=""><fmt:message key="SaoKe.page.label.tracuu.loaitien"/></html:option>
                      <%
                      }
                      %>
            </html:select>
         </td>
      </tr>
    </tbody>
  </table>
  <table class="buttonbar" align="center">
    <tr>
      <td>
        <span id="tracuu">
          <button  id="btn_timKiem" 
                    tabindex="111"
                    accesskey="X" type="button" onclick="findSK();"
                  class="ButtonCommon" >
            <fmt:message key="SaoKe.page.button.search"/>
          </button>
        </span>
        <span id="print">
          <button  id="btn_print" 
                    tabindex="111"
                    accesskey="I" type="button" onclick="inSK();"
                  class="ButtonCommon" >
            <fmt:message key="SaoKe.page.button.print"/>
          </button>
        </span>
        <span id="exit">
          <button  id="btn_Exit" 
                    tabindex="111"
                    accesskey="T" type="button"
                  class="ButtonCommon" >
            <fmt:message key="SaoKe.page.button.exit"/>
          </button>
        </span>
        <span id="chot_so">
          <button  id="btn_Chot_so" 
                    tabindex="111"
                    accesskey="T" type="button" onclick="chotSoSaoKe();"
                  class="ButtonCommon" >
           Chốt sổ
          </button>
        </span>
      </td>
    </tr>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <thead class="TR_Selected">
      <tr>
        <th width="4%">
          <fmt:message key="SaoKe.page.ketqua.stt"/>
        </th>
        <th width="10%">
          <fmt:message key="SaoKe.page.ketqua.ngayps"/>
        </th>
        <th width="15%">
          <fmt:message key="SaoKe.page.ketqua.no"/>
        </th>
        <th width="15%">
          <fmt:message key="SaoKe.page.ketqua.co"/>
        </th>
        <th width="8%">
          <fmt:message key="SaoKe.page.ketqua.loaitien"/>
        </th>
        <th width="10%">
          <fmt:message key="SaoKe.page.ketqua.magd"/>
        </th>
        <th width="10%">
          <fmt:message key="SaoKe.page.ketqua.tktchieu"/>
        </th>
        <th>
          <fmt:message key="SaoKe.page.ketqua.noidung"/>
        </th>
      </tr>
    </thead>
    <tbody>
      <logic:present name="lstSKe">
        <logic:empty name="lstSKe">
          <tr>
            <td colspan="8" align="center" style="color:red">
              <fmt:message key="SaoKe.page.ketqua.empty"/>
            </td>
          </tr>
        </logic:empty>
        <logic:notEmpty name="lstSKe">
            <%
            com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
            int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            %>
            <!--<tr valign="top" class="trDanhSachChan">
                <td align="center">
                
                </td>
                <td  align="left">
                  <b>Số dư đầu</b>
                </td>
                <td align="center">
                  <logic:equal value="D" property="tinh_chat_du_dau" name="skeForm">
                    <b><bean:write name="skeForm" property="so_tien_du_dau" format="#,###"/></b>
                  </logic:equal>
                  <logic:equal value="C" property="tinh_chat_du_dau" name="skeForm">
                    -
                  </logic:equal>
                </td>
                <td align="right">                  
                  <logic:equal value="D" property="tinh_chat_du_dau" name="skeForm">
                    -
                  </logic:equal>
                  <logic:equal value="C" property="tinh_chat_du_dau" name="skeForm">
                    <b><bean:write name="skeForm" property="so_tien_du_dau" format="#,###"/></b>
                  </logic:equal>
                </td>
                <td align="center">
                  <bean:write name="skeForm" property="loai_tien_du_dau"/>
                </td>
                <td  align="center">
                  <bean:write name="skeForm" property="so_tham_chieu_gd"/>
                </td>
                <td  align="center">
                  <bean:write name="skeForm" property="so_tk"/>
                </td>
                <td  align="left">
                  
                </td>
              </tr>          -->   
              
            <logic:iterate id="items" name="lstSKe" indexId="stt">
              <logic:equal value="PS_DAU" property="loai_ps" name="items">
                
                <!--SET LOCALE -->
                <logic:equal value="VND" name="items" property="loai_tien">
                <fmt:setLocale value="vi_VI"/>
                </logic:equal>
                <logic:notEqual value="VND" name="items" property="loai_tien">
                  <fmt:setLocale value="en_US"/>
                </logic:notEqual>
                <!---->
              
                <tr valign="top" class="trDanhSachChan">
                  <td align="center">
                  
                  </td>
                  <td  align="left">
                    <b>Số dư đầu</b>
                  </td>
                  <td  align="right">
                    <logic:equal value="D" name="items" property="tinh_chat_ps">
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                    </logic:equal>
                  </td>
                  <td  align="right">
                    <logic:equal value="C" name="items" property="tinh_chat_ps">
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                    </logic:equal>
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="loai_tien"/>
                  </td>
                  <td   align="center">
                    
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="tk_tham_chieu"/>
                  </td>
                  <td  align="left">
                    <bean:write name="items" property="noi_dung"/>
                  </td>
                </tr>
              </logic:equal>
              <logic:notEqual value="PS_TRONG" property="loai_ps" name="items">
              <logic:notEqual value="PS_CUOI" property="loai_ps" name="items">
              <logic:notEqual value="PS_DAU" property="loai_ps" name="items">
                <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td  align="center">
                    <%=stt+rowBegin%>            
                  </td>
                  <td  align="center">                  
                    <bean:write name="items" property="ngay_ps"/>                  
                  </td>
                  <td  align="right">
                    <logic:equal value="D" name="items" property="tinh_chat_ps">
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                    </logic:equal>
                  </td>
                  <td  align="right">
                    <logic:equal value="C" name="items" property="tinh_chat_ps"> 
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                    </logic:equal>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="loai_tien"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ma_loai_gd"/>
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="tk_tham_chieu"/>
                  </td>
                  <td  align="left">
                    <bean:write name="items" property="noi_dung"/>
                  </td>
                </tr>
              </logic:notEqual>
              </logic:notEqual>
              </logic:notEqual>
              <logic:equal value="PS_TRONG" property="loai_ps" name="items">
                <tr valign="top" class="trDanhSachChan">
                  <td align="center">
                  
                  </td>
                  <td  align="left">
                    <b>Tổng phát sinh</b>
                  </td>
                  <td  align="right">
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="tong_ps_no"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="tong_ps_no"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                  </td>
                  <td  align="right">
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="tong_ps_co"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="tong_ps_co"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="loai_tien"/>
                  </td>
                  <td   align="center">
                    
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="tk_tham_chieu"/>
                  </td>
                  <td  align="left">
                    <bean:write name="items" property="noi_dung"/>
                  </td>
                </tr>
              </logic:equal>
              <logic:equal value="PS_CUOI" property="loai_ps" name="items">
                <tr valign="top" class="trDanhSachChan">
                  <td align="center">
                  
                  </td>
                  <td  align="left">
                    <b>Số dư cuối</b>
                  </td>
                  <td  align="right">
                    <logic:equal value="D" name="items" property="tinh_chat_ps">
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                    </logic:equal>
                  </td>
                  <td  align="right">
                    <logic:equal value="C" name="items" property="tinh_chat_ps"> 
                      <b>
                      <logic:equal value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber  type="currency" maxFractionDigits="0"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:equal>                      
                      <logic:notEqual value="VND" name="items" property="loai_tien">
                        <fmt:formatNumber type="currency"  currencySymbol="">
                          <bean:write name="items" property="so_tien"/>
                        </fmt:formatNumber>
                      </logic:notEqual>
                      </b>
                    </logic:equal>
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="loai_tien"/>
                  </td>
                  <td   align="center">
                    
                  </td>
                  <td   align="center">
                    <bean:write name="items" property="tk_tham_chieu"/>
                  </td>
                  <td  align="left">
                    <bean:write name="items" property="noi_dung"/>
                  </td>
                </tr>
              </logic:equal>
            </logic:iterate>
            <tr>
              <td colspan="8">
                <div style="float:right;padding-right:40">
                  <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                </div>
              </td>
            </tr>
            <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
        </logic:notEmpty>
      </logic:present>
    </tbody>
  </table>
</html:form>
<script type="text/javascript">
//function loadAccountDetailAJAX(){
//    var soTk = jQuery('#sotk').val();
//    var manh = jQuery('#manh').val();
//    var nhkb_huyen = jQuery('#nhkb_huyen').val();
//    jQuery.ajax({
//          type : "POST",
//          url : "getTKNHKB.do", 
//          data :  {"soTk":soTk , "nh_id":manh , "shkb":nhkb_huyen}
//        , success : function (data, textstatus) {
//          if (textstatus != null && textstatus == 'success') {
//            if (data != null) {
//              if(data[0].loai_tk == '01'){
//                jQuery('#hintAccountType').text('TK Tiền gửi')
//              }else if(data[0].loai_tk == '02'){
//                jQuery('#hintAccountType').text('TK Thanh toán')
//              }else if(data[0].loai_tk == '03'){
//                jQuery('#hintAccountType').text('TK Chuyên thu')
//              }
//              jQuery('#hintMoneyType').text(data[0].loai_tien)
//            }
//          }
//        },
//        error : function (textstatus) {
//            alert(textstatus);
//        }
//    });
//}
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>