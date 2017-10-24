<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.tloi.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatTraLoiResource"/>

 <script type="text/javascript">
   jQuery.noConflict();
    jQuery(document).ready(function()
      {
         var contectRoot='<%=request.getContextPath()%>';
        tableHighlightRowTLoi();
//        if(jQuery("#id").val()=='' || jQuery("#id").val()=='undefined'){
//          jQuery("#print").hide();
//        }
        selectedTopRowDTSTLoi(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>','<%=(request.getParameter("action"))%>');
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
        if('<%=(request.getParameter("action"))%>'!=null){
          hideAllButtonDTSTraLoi();
//          if('<%=(request.getParameter("action"))%>'=="VIEW_DETAIL"){
//           hideButtonDTSTLoi();
//           if (jQuery("#so_ltt").val()=="" || jQuery("#so_ltt").val()==null ) {
//              jQuery("#btn_xemLTT").hide();
//           }
//            var trs = document.getElementById('data-grid').getElementsByTagName('tr');
//            for (var j = 0;j < trs.length;j++) {
//              jQuery("#row_dts_" + j).attr( {disabled:"disabled"});
//              
//          }
//          }
         
        //add actio
        }else{
          resetForm();
        }
      

   //**************************BUTTON nhận CLICK********************************************
    jQuery("#btn_nhan").click(function(){
          document.forms[0].target='';
           if  (jQuery("#trang_thai").val()=="") {
          alert('Không có điện nào được chọn');
          return;
          }
          if  (jQuery("#trang_thai").val()=="02") {
          alert('Điện trả lời này đã được chuyển sang chờ kiểm soát');
          return;
          }
          if  (jQuery("#trang_thai").val()=="03") {
          alert('Điện trả lời này đã được duyệt');
          return;
          }
          if(confirm('Bạn có chắc chắn nhận điện trả lời tra soát này?')==false)
              return false;
           else {
            document.forms[0].action = 'updatedtstrloi.do?action=ACCEPT';
            document.forms[0].submit();           
           }
    });
    function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certSerial").val(serial);
        
            var noi_dung_ky = jQuery("#noi_dung_ky").val();

            var sign = cert.Sign(noi_dung_ky);

            jQuery("#signature").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
    // Nút duyệt
     jQuery("#btn_duyet").click(function(){
        document.forms[0].target='';
          if  (jQuery("#trang_thai").val()=="") {
            alert('Không có điện nào được chọn');
            return;
          }
          if  (jQuery("#trang_thai").val()=="03") {
            alert('Điện trả lời này đã được duyệt');
            return;
          }
           if(confirm('Bạn có chắc chắn duyệt điện trả lời tra soát này?')==false)
              return false;
           else {
               if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
             
             document.forms[0].action = 'updatedtstrloi.do?action=APPROVED';
             document.forms[0].submit();
           }
    });
    // Nút thoát
    jQuery("#btn_thoat").click(function(){
        document.forms[0].target='';       
        if('<%=(request.getParameter("action"))%>'=="VIEW_DETAIL"){
         var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
          var trang_thai_back = '<%=request.getParameter("trang_thai")%>';
          var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
          var den_ngay_back ='<%=request.getParameter("den_ngay")%>';
          var tu_ngay_lapdien_back = '<%=request.getParameter("tu_ngay_lapdien")%>';
          var den_ngay_lapdien_back ='<%=request.getParameter("den_ngay_lapdien")%>';
          var nhkb_chuyen_back = '<%=request.getParameter("nhkb_chuyen")%>';
          var nhkb_nhan_back = '<%=request.getParameter("nhkb_nhan")%>';
          var so_dts_back = '<%=request.getParameter("so_dts")%>';
          var so_ltt_back = '<%=request.getParameter("so_ltt")%>';
          var loai_ltt_back = '<%=request.getParameter("loai_ltt")%>';
          var loai_tra_soat_back = '<%=request.getParameter("loai_tra_soat")%>';
          var chieu_tra_soat_back = '<%=request.getParameter("chieu_tra_soat")%>';
            var urlBack = "tracuudts.do?action=Back";
             if(ttv_nhan_back != null &&ttv_nhan_back!='null' ){
             urlBack += "&ttv_nhan_back="+ttv_nhan_back+"";
           }
            if(trang_thai_back != null &&trang_thai_back!='null'){
             urlBack += "&trang_thai_back="+trang_thai_back+"";
           }
            if(tu_ngay_back != null &&tu_ngay_back!='null'){
             urlBack += "&tu_ngay_back="+tu_ngay_back+"";
           }
            if(den_ngay_back != null &&den_ngay_back!='null'){
             urlBack += "&den_ngay_back="+den_ngay_back+"";
           }      
           if(tu_ngay_lapdien_back != null &&tu_ngay_lapdien_back!='null'){
             urlBack += "&tu_ngay_lapdien_back="+tu_ngay_lapdien_back+"";
           }
            if(den_ngay_lapdien_back != null &&den_ngay_lapdien_back!='null'){
             urlBack += "&den_ngay_lapdien_back="+den_ngay_lapdien_back+"";
           }   
            if(so_dts_back != null &&so_dts_back!='null'){
             urlBack += "&so_dts_back="+so_dts_back+"";
           }
            if(nhkb_chuyen_back!=null && nhkb_chuyen_back!= 'null'){
               urlBack += "&nhkb_chuyen_back="+nhkb_chuyen_back+"";
            }
            if(nhkb_nhan_back!=null && nhkb_nhan_back!= 'null'){
               urlBack += "&nhkb_nhan_back="+nhkb_nhan_back+"";
            }
            if(so_ltt_back!=null && so_ltt_back!= 'null'){
               urlBack += "&so_ltt_back="+so_ltt_back+"";
            }
            if(loai_ltt_back!=null && loai_ltt_back!= 'null'){
               urlBack += "&loai_ltt_back="+loai_ltt_back+"";
            }
            if(loai_tra_soat_back!=null && loai_tra_soat_back!= 'null'){
               urlBack += "&loai_tra_soat_back="+loai_tra_soat_back+"";
            }
             if(chieu_tra_soat_back!=null && chieu_tra_soat_back!= 'null'){
               urlBack += "&chieu_tra_soat_back="+chieu_tra_soat_back ;
            }
            document.forms[0].action = urlBack;
            document.forms[0].submit();
          }else if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL_DTS_T4%>'){
              var ma_nh_back = '<%=request.getParameter("ma_nh")%>';
              var tinh_back = '<%=request.getParameter("tinh")%>';
              var huyen_back = '<%=request.getParameter("huyen")%>';
              var trang_thai_back ='<%=request.getParameter("trang_thai")%>';
              var loai_lenh_back = '<%=request.getParameter("loai_lenh")%>';
              var tu_ltt_back ='<%=request.getParameter("tu_ltt")%>';
              var den_ltt_back = '<%=request.getParameter("den_ltt")%>';
              var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
              var den_ngay_back = '<%=request.getParameter("den_ngay")%>';
              var so_dts_back = '<%=request.getParameter("so_dts")%>';
              var so_tien_temp_back = '<%=request.getParameter("so_tien_temp")%>';
              var urlBack = "TraCuuDTSToanQuocList.do?action=Back";
              if(ma_nh_back != null && ma_nh_back != '' && ma_nh_back != 'null'){
                  urlBack += "&ma_nh_back="+ma_nh_back+"";
                }
                if(tinh_back != null && tinh_back != '' && tinh_back != 'null'){
                  urlBack += "&tinh_back="+tinh_back+"";
                }    
                if(huyen_back != null && huyen_back != '' && huyen_back != 'null'){
                  urlBack += "&huyen_back="+huyen_back+"";
                }
                if(trang_thai_back != null && trang_thai_back != '' && trang_thai_back != 'null'){
                  urlBack += "&trang_thai_back="+trang_thai_back+"";
                }
                if(loai_lenh_back != null && loai_lenh_back != '' && loai_lenh_back != 'null'){
                urlBack += "&loai_lenh_back="+loai_lenh_back+"";
                }
                if(tu_ltt_back != null && tu_ltt_back != '' && tu_ltt_back != 'null'){
                  urlBack += "&tu_ltt_back="+tu_ltt_back+"";
                }
                if(den_ltt_back != null && den_ltt_back != '' && den_ltt_back != 'null'){
                  urlBack += "&den_ltt_back="+den_ltt_back+"";
                }
                if(tu_ngay_back != null && tu_ngay_back != '' && tu_ngay_back != 'null'){
                  urlBack += "&tu_ngay_back="+tu_ngay_back+"";
                }
                if(den_ngay_back != null && den_ngay_back != '' && den_ngay_back != 'null'){
                  urlBack += "&den_ngay_back="+den_ngay_back+"";
                }
                if(so_dts_back != null && so_dts_back != '' && so_dts_back != 'null'){
                  urlBack += "&so_dts_back="+so_dts_back+"";
                }
                if(so_tien_temp_back!=null && so_tien_temp_back!= '' && so_tien_temp_back != 'null'){
                  urlBack += "&so_tien_temp_back="+so_tien_temp_back+"";
                }
                document.forms[0].action = urlBack;
                    document.forms[0].submit();
              }
              else{
                 if(confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?')==false)
                   return false;
                  else
                 document.forms[0].action ='mainAction.do';
                 document.forms[0].submit();
              }
    });
     jQuery("#btn_xemLTT").click(function(){
          document.forms[0].target='';
            //ltt den
            var id= jQuery("#id").val();
           if(jQuery("#nhkb_nhan_ltt").val()=='<%=request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>')
            window.location.href='listLttDenAdd.do?action=VIEW_LTTDen&so_ltt='+jQuery("#ltt_id").val()+'&referrer=dtsoattloiView&id='+id;
           else
           //ltt di
           window.location.href='listLttAdd.do?action=VIEW_LTTDi&so_ltt='+jQuery("#ltt_id").val()+'&referrer=dtsoattloiView&id='+id;
         })
   ;         
   
   jQuery("#btn_timKiem").click(function() {
        document.forms[0].target='';
          jQuery("#nhkb_nhan").val('');
          jQuery("#so_dts").val('');
          jQuery("#so_lenh_tt").val('');
          if('<%=request.getAttribute("chucdanh")%>'=='TTV'){
           jQuery("#ttv_nhan").hide();
           jQuery("#dialog-form" ).dialog( "open" );
          }else
          {
           jQuery("#ttv_nhan_tk").show();
           jQuery("#dialog-form" ).dialog( "open" );
          }
            jQuery("#ttv_nhan_tk").focus();
           
       });
       
       	jQuery("#dialog-form").dialog({
							autoOpen: false,
              height: 400,
							width: 300,
              modal: true,
							buttons: {
								"Tìm kiếm": function() {
                  resetFormDTSTLoi();
                  findDTSTTLoi('<%=AppConstants.NNT_APP_CONTEXT_ROOT%>');
                },
								"Thoát": function() {
									jQuery("#evenButtonBefore").val('');
									jQuery(this).dialog("close");
								}
							},
							"Đóng": function() {
              }
						}); 
          });
          // In
    function formAction(){
      if(jQuery("#id").val()=='' || jQuery("#id").val()==undefined){
          alert('Không có bản ghi nào để thực hiện in!');
          return;
        }
      var f = document.forms[0];
      f.action = "dtsoattloiRpt.do";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
    } 
</script>
<%
  String str_Err_Desc = request.getAttribute("p_err_desc")!=null?request.getAttribute("p_err_desc").toString():"";
  if(str_Err_Desc!=null && !"".equals(str_Err_Desc)){
%>
<table width="99%">
    <tbody>
    <tr>
      <td><font color="blue">
          Bạn đã nhận điện tra soát <%=str_Err_Desc%> thành công!
        </font> 
      </td></tr>      
    </tbody>
  </table>
<%
  }
%>
<div id="body">
  <%  String strUserType = session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
  if(strUserType.toUpperCase().indexOf(AppConstants.NSD_TTV)==-1){%>
        <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
      <%}%>
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
            <fmt:message key="dtsoat_tloi.page.title"/></span>
              </td>
              <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
            </tr>
          </tbody>
        </table>
        <table class="tableBound">
        <tr>
          <td>
                <table width="99%">
                  <tbody>
                  <tr>
                    <td><font color="red">
                        <html:errors/>
                      </font> 
                    </td>
                  </tr>
                  <tr>
                      <td>
                          <input type="text" class="fieldTextTransError" readonly="readonly" id="error_desc"/>
                      </td>
                  </tr>
                  </tbody>
                </table>
                <table cellspacing="0" cellpadding="0" width="100%">
                  <tbody>
                    <tr>
                      <td valign="top">
                        <table class=bordertableChungTu cellSpacing=0 cellPadding=0 width="100%">
                                <html:form styleId="frmDTSTLoi"   action="/dtsoattloiView.do">
                                <tbody>
                                  <tr>
                                    <td width="15%" vAlign=top>
                                    <div class="clearfix">
                                      <fieldset class="fieldsetCSS">
                                        <legend style="vertical-align:middle">
                                          <fmt:message key="dtsoat_tloi.fieldset.title.so_dien_tra_soat"/>
                                        </legend>
                                        <div class="sodientrasoattable">
                                          <div>
                                              <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                                <thead>
                                                  <tr>
                                                    <th class="ui-state-default ui-th-column"
                                                        width="62%;">
                                                     <fmt:message key="dtsoat_tloi.page.lable.so_dien_tra_soat"/>
                                                    </th>
                                                     <th height="20" width="28%"
                                                         class="ui-state-default ui-th-column">
                                                     <fmt:message key="dtsoat_tloi.page.lable.tinh_trang"/>
                                                    </th>
                                                    <th height="20" width="10%;"
                                                        class="ui-state-default ui-th-column">
                                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </th>
                                                  </tr>
                                                </thead>
                                              </table>
                                            </div>
                                            <div class="ui-jqgrid-bdiv ui-widget-content" style="height:300px;">
                                            <div style="position: relative;">
                                              <table class="data-grid" id="data-grid"  cellSpacing="0" cellPadding="0"  width="100%">
                                                <tbody>
                                                  <logic:present name="listDTSoatTraLoi">
                                                    <logic:iterate id="list_dts_traloi" name="listDTSoatTraLoi" indexId="index">
                                                    <tr style="width:100%;" class="ui-widget-content jqgrow ui-row-ltr" id="row_dts_<bean:write name="index"/>" ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTSTLoi('<bean:write name="list_dts_traloi" property="id"/>','row_dts_<bean:write name="index"/>','<%=request.getAttribute("chucdanh")%>','<%=(request.getParameter("action"))%>');">
                                                      <td width="44%;" align="center">
                                                       <input type="hidden" id='col_dts_<bean:write name="index"/>' value="<bean:write name="list_dts_traloi" property="id"/>"/>
                                                        <input tabindex="0" name="row_item" id="<bean:write name="index"/>" 
                                                        type="text" style="border:0px;font-size:10px;float:left;" value="<bean:write name="list_dts_traloi" property="id"/>" onkeydown="arrowUpDown(event)" readonly="true"/>
                                                        </td>
                                                      <td width="30%;" align="center"> 
                                                          <logic:equal value="00" name="list_dts_traloi" property="trang_thai">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/edit.gif" border="0" title="Chờ xử lý"/>
                                                        </logic:equal>  
                                                          <logic:equal value="03" name="list_dts_traloi" property="trang_thai">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" border="0" title="Đã gửi"/>
                                                        </logic:equal>
                                                          <logic:equal value="02" name="list_dts_traloi" property="trang_thai">
                                                            <img  src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                        </logic:equal>
                                                        <logic:equal value="10" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="KTT đẩy lại"/>
                                                        </logic:equal>  
                                                        <logic:equal value="11" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Chờ xử lý"/>
                                                        </logic:equal>
                                                        <logic:equal value="12" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã xử lý"/>
                                                        </logic:equal>
                                                        <logic:equal value="13" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="Đã trả lời"/>
                                                        </logic:equal>
                                                        <logic:equal value="14" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Chờ KTT duyệt"/>
                                                        </logic:equal>
                                                        <logic:equal value="15" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã gửi"/>
                                                        </logic:equal>
                                                        <logic:equal value="16" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã xác nhận"/>
                                                        </logic:equal>
                                                        <logic:equal value="17" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="Đã hủy"/>
                                                        </logic:equal>
                                                        <logic:equal value="18" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" title="Gửi NH thành công"/>
                                                        </logic:equal>
                                                        <logic:equal value="19" property="trang_thai" name="list_dts_traloi">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" title="Gửi NH không thành công"/>
                                                        </logic:equal>
                                                        </td>
                                                      </tr>
                                                    </logic:iterate>
                                                    <logic:empty name="listDTSoatTraLoi" >
                                                      <tr>
                                                        <td colspan="5" align="center">
                                                          <span style="color:red;"><fmt:message key="dtsoat_tloi.error.not_found"/></span>
                                                        </td>
                                                      </tr>
                                                    </logic:empty>
                                                  </logic:present>
                                               </tbody>
                                              </table>
                                          </div>
                                        </div>
                                        <div>
                                            <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                              <thead>
                                                <tr id="refreshButton">
                                                  <td class="ui-state-default ui-th-column" title="Refresh" id="refresh" colspan="2">              
                                                    <div class="ui-pg-div"><span id="btn_refresh" class="ui-icon ui-icon-refresh" onclick="refreshGridTLoi('<%=request.getParameter("id")%>','<%=AppConstants.NNT_APP_CONTEXT_ROOT%>');" style="cursor:pointer;"></span></div>
                                                  
                                                    <div class="ui-pg-div"><span id="btn_timKiem" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span></div>
                                                  </td>
                                                </tr>
                                              </thead>
                                            </table>
                                            <div style="padding-top:10px;float: left;"><fmt:message key="dtsoat_tloi.page.lable.trang_thai"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"></span></div>
                                          </div>
                                        </div>
                                       </fieldset>
                                      </div>
                                   </td>
                                   <td width="85%" valign="top">
                                     <fieldset class="fieldsetCSS">
                                      <legend style="vertical-align:middle">
                                        <fmt:message key="dtsoat_tloi.fieldset.title.thong_tin_chung"/>
                                      </legend>
                                      <div style="padding:5px 5px 5px 5px;">
                                      <input type="hidden" id="rowSelected"/>
                                      <input type="hidden" id="evenButtonBefore"/>
                                      <input type="hidden" id="focusField"/>
                                      
                                        <html:hidden property="id" styleId="id"/>
                                        <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                        <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                                        <html:hidden property="trang_thai" styleId="trang_thai"/>
                                        <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                                        <html:hidden property="nhkb_nhan_ltt" styleId="nhkb_nhan_ltt"/>
                                        <input type="hidden" id="contenData"/>
                                         <input type="hidden" name="certSerial" id="certSerial" />
                                         <input type="hidden" name="signature" id="signature" />  
                                         <input type="hidden" name="noi_dung_ky" id="noi_dung_ky" /> 
                                        <table style="BORDER-COLLAPSE: collapse;" border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                                          <tbody>
                                           <tr>
                                               <td align="right" width="100px;">
                                                <label for="ltt_id"><fmt:message key="dtsoat_tloi.page.lable.so_lenh_thanh_toan" /></label>
                                              </td>
                                              <td colspan="3" align="left">
                                                <html:text style="width:98%" property="ltt_id" styleId="ltt_id" styleClass="fieldTextCode" />
                                              </td>
                                              <td align="right" width="100px;" >
                                                <label for="ngay_thanh_toan"><fmt:message key="dtsoat_tloi.page.lable.ngay_thanh_toan"/></label>
                                              </td>
                                              <td colspan="3" align="left">
                                                <html:text property="ngay_thanh_toan" style="width:99%" styleId="ngay_thanh_toan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align="right" width="100px;">
                                                <label for="so_dien_ts"><fmt:message key="dtsoat_tloi.page.lable.so_dien" /></label>
                                              </td>
                                              <td colspan="3" align="left">
                                                <html:text property="id" style="width:98%" styleId="so_dien_ts" styleClass="fieldTextCode" />
                                              </td>
                                              
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="nhkb_chuyen"><fmt:message key="dtsoat_tloi.page.lable.don_vi_tra_soat"/></label>
                                              </td>
                                              <td >
                                                <html:text property="ma_don_vi_nhan_tra_soat" style="width:96%" styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                              </td>
                                              <td align="left" colspan="2">
                                                <html:text property="ten_don_vi_nhan_tra_soat"  styleId="ten_don_vi_nhan_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:97%" />
                                              </td>
                                              <td align=right>
                                                <label for="nhkb_nhan"><fmt:message key="dtsoat_tloi.page.lable.don_vi_nhan_tra_soat"/></label>
                                              </td>
                                              <td >
                                                <html:text property="ma_don_vi_tra_soat" style="width:96%" styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                                              </td>
                                              <td align="left" colspan="2">
                                                <html:text property="ten_don_vi_tra_soat"  styleId="ten_don_vi_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:97%"/>
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="ttv_nhan"><fmt:message key="dtsoat_tloi.page.lable.nguoi_lap"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ma_ttv_nhan" style="width:98%" styleId="ma_ttv_nhan" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                              <td align=right>
                                                <label for="ngay_nhan"><fmt:message key="dtsoat_tloi.page.lable.ngay_lap"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ngay_nhan" style="width:99%" styleId="ngay_nhan" styleClass="fieldTextCode"  readonly="true" />
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="ma_ktt"><fmt:message key="dtsoat_tloi.page.lable.ktt"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ma_ktt" styleId="ma_ktt" style="width:98%" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                              <td align=right>
                                                <label for="ngay_duyet"><fmt:message key="dtsoat_tloi.page.lable.ngay_kiem_soat"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ngay_duyet" style="width:99%" styleId="ngay_duyet" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                            </tr>
                                             <tr>
                                              <td align=right>
                                                <label for="nguoi_nhap_nh"><fmt:message key="dtsoat_tloi.page.lable.nguoi_nhap_nh"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="nguoi_nhap_nh" style="width:98%" styleId="nguoi_nhap_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                              <td align=right>
                                                <label for="ngay_nhap_nh"><fmt:message key="dtsoat_tloi.page.lable.ngay_nhap_nh"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ngay_nhap_nh" style="width:99%" styleId="ngay_nhap_nh" styleClass="fieldTextCode"  readonly="true" />
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="nguoi_ks_nh"><fmt:message key="dtsoat_tloi.page.lable.nguoi_ks_nh"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="nguoi_ks_nh" style="width:98%" styleId="nguoi_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                              <td align=right>
                                                <label for="ngay_ks_nh"><fmt:message key="dtsoat_tloi.page.lable.ngay_ks_nh"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <html:text property="ngay_ks_nh" style="width:99%" styleId="ngay_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="noi_dung"><fmt:message key="dtsoat_tloi.page.lable.noi_dung_tra_soat"/></label>
                                              </td>
                                              <td colspan="7">
                                                <html:textarea property="noidung"  styleId="noi_dung" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" disabled="true"></html:textarea>
                                                <span style="color:#FF0000" id="word_count_noi_dung">
                                                <fmt:message key="dtsoat_tloi.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_noi_dung">146</span></span>
                                              </td>
                                            </tr>
                                             <tr>
                                              <td align=right>
                                                <label for="noidung_traloi"><fmt:message key="dtsoat_tloi.page.lable.noi_dung_tra_loi"/></label>
                                              </td>
                                              <td colspan="7">
                                                <html:textarea property="noidung_traloi"   styleId="noidung_traloi" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" disabled="true"></html:textarea>
                                                <span style="color:#FF0000" id="word_count_noi_dung">
                                                <fmt:message key="dtsoat_tloi.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_noi_dung_traloi">146</span></span>
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="thong_tin_khac"><fmt:message key="dtsoat_tloi.page.lable.thong_tin_khac"/></label>
                                              </td>
                                              <td colspan="7">
                                                <html:textarea property="thong_tin_khac" styleId="thong_tin_khac" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" disabled="true"></html:textarea>
                                                <span style="color:#FF0000" id="word_count_thong_tin_khac">
                                                <fmt:message key="dtsoat_tloi.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_thong_tin_khac">146</span></span>
                                              </td>
                                            </tr>
                                            <tr>
                                              <td align=right>
                                                <label for="lydo_ktt_day_lai"><fmt:message key="dtsoat_tloi.page.lable.ktt_day_lai"/></label>
                                              </td>
                                              <td colspan="7">
                                                <html:textarea property="lydo_ktt_day_lai" styleId="lydo_ktt_day_lai" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea " disabled="true"></html:textarea>
                                                <span style="color:#FF0000" id="word_count_lydo_ktt_day_lai">
                                                <fmt:message key="dtsoat_tloi.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_lydo_ktt_day_lai">146</span></span>
                                              </td>
                                            </tr>
                                          </tbody>
                                        </table>                                        
                                       </div>
                                      </fieldset>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td colspan="2">
                                      <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                                      <tr align="right">
                                          <td>
                                          <span id="nhan">
                                            <button id="btn_nhan" accessKey=n class=buttonCommon>
                                              <fmt:message key="dtsoat_tloi.page.button.accept"/>
                                            </button>
                                          </span>
                                          <span id="duyet">
                                            <button id="btn_duyet"  accessKey=d class=buttonCommon>
                                              <fmt:message key="dtsoat_tloi.page.button.approved"/>
                                            </button>
                                          </span>
                                           <!--<span  id="timKiem">
                                            <button id="btn_timKiem"  accessKey=K class=buttonCommon>
                                              <fmt:message key="dtsoat_tloi.page.button.find"/>
                                            </button>
                                          </span>-->
                                          <span  id="print">
                                            <button id="btn_print" onclick="formAction();" class=buttonCommon>
                                              <fmt:message key="dtsoat_tloi.page.button.print"/>
                                            </button>
                                          </span>
                                           <span  id="xemLTT" >
                                            <button id="btn_xemLTT"  accessKey=X class=buttonCommon>
                                              <fmt:message key="dtsoat_tloi.page.button.view_ltt"/>
                                            </button>
                                          </span>
                                          <span>
                                            <button id="btn_thoat" accessKey=o  class=buttonCommon tabIndex=103>
                                              <fmt:message key="dtsoat_tloi.page.button.exit"/>
                                            </button>
                                          </span>
                                          <span>
                                            <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
                                          </span>
                                       </td>
                                     </tr>
                                     </table>
                                    </td>
                                  </tr>
                                </tbody>  
                                </html:form>
                              </table>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </table>
      
       <div id="dialog-form" title="<fmt:message key="dtsoat_tloi.page.title.dialog_form"/>">
        <p class="validateTips"></p>
          <form id="tra-cuu-dts">
              <div id=ma_ttv style="padding-top:10px;">
                <label for="ma_ttv" style="padding-left:60px;">
                  <fmt:message key="dtsoat_tloi.page.lable.ma_ttv"/>
                </label>
                <input type="text" name="ttv_nhan_tk" id="ttv_nhan_tk" onkeydown="nextFocus();" class=" text ui-widget-content ui-corner-all"/>
              </div>
              <div style="padding-top:10px;">
                <label for="nh_bk_nhan" style="padding-left:30px;">
                  <fmt:message key="dtsoat_tloi.page.lable.nh_bk_nhan"/>
                </label>
                <input type="text" name="nh_kb_nhan" id="nh_bk_nhan" onkeydown="nextFocus();" class=" text ui-widget-content ui-corner-all"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_lenh_tt" style="padding-left:40px;">
                  <fmt:message key="dtsoat_tloi.page.lable.so_lenh_tt"/>
                </label>
                <input type="text" name="so_lenh_tt" id="so_lenh_tt" onkeydown="nextFocus();" class=" text ui-widget-content ui-corner-all"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_dts" style="padding-left:12px;">
                  <fmt:message key="dtsoat_tloi.page.lable.so_dts" />
                </label>
                <input type="text" name="so_dts" id="so_dts" onkeydown="nextFocus();" class=" text ui-widget-content ui-corner-all"/> 
              </div>
               <div style="padding-top:10px;">
                <label for="so_dts" style="padding-left:20px;">
                  <fmt:message key="dtsoat_tloi.page.lable.so_traloi" />
                </label>
                <input type="text" name="so_traloi" id="so_traloi" onkeydown="nextFocus();" class=" text ui-widget-content ui-corner-all"/> 
              </div>
          </form>
      </div>
  <%@ include file="/includes/ttsp_bottom.inc"%>
