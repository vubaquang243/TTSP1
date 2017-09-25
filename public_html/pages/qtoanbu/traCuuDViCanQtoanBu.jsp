<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.ReportsResource"/>


<%
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).init(function () {
      getTenKhoBac();
      getTenNHang();
      
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
  function formAction() {
      var f = document.forms[0];
      f.submit();

  }
</script>
<div class="app_error">
  <html:errors/>
</div>


<title>Danh sách các đơn vị cần quyết toán bù</title>
<html:form action="/listDViCanQtoanBu.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Danh sách các đơn vị cần quyết toán bù</span>
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
    </table>
    
    <table style="BORDER-COLLAPSE: collapse; border: solid #e1e1e1;" border="0" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          Điều kiện tìm kiếm
        </td>
      </tr>
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
        <td  bordercolor="#e1e1e1">
            <%if(request.getAttribute("dchieu3") != null){%>     
               <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                    <span class="sortKey">D</span>anh m&#7909;c KB
               </button>
            <%}%>
        </td>
        </tr>
        <tr style="display:none">
        <td width="15%" align="right" bordercolor="#e1e1e1">Ng&acirc;n
                                                            h&agrave;ng: &nbsp;</td>
        <td bordercolor="#e1e1e1">
          <html:select property="ngan_hang" styleId="ngan_hang" style="width:90%"
                       onchange="nhangval()"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">
            <html:option value="">-----Chọn th&ocirc;n tin ng&acirc;n
                                  h&agrave;ng-----</html:option>
          </html:select>
          <html:hidden property="ma_ngan_hang" styleId="ma_ngan_hang"/> 
        </td>
        <td align="right" width="15%" bordercolor="#e1e1e1">
         
        </td>
        <td bordercolor="#e1e1e1">
         
        </td>
      </tr>
      <tr>
        <td colspan="5" align="center" valign="top">
          <div style="padding:10px 0px 10px 0px;vertical-align:top; ">
            <button type="button"  onclick="formAction();"
                    accesskey="i" class="ButtonCommon">
              Tìm
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
  
   <table style="BORDER-COLLAPSE: collapse; border: solid #e1e1e1;" border="1" cellspacing="2"
          bordercolor="#e1e1e1" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          Kết quả tìm kiếm
        </td>
      </tr>
      <tr> 
      <td>
    <div id="contentLeft" style="width:100%;float:left;height: 300px; overflow: auto">
        <table  class="data-grid" id="data-grid" 
                                              border="1"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">           
              <tr>
                <td align="center" class="ui-state-default ui-th-column">SHKB</td>
                <td align="center" class="ui-state-default ui-th-column">Tên KB</td>
                <td align="center" class="ui-state-default ui-th-column">Mã NH</td>
                <td align="center" class="ui-state-default ui-th-column">Số TK</td>
                <td align="center" class="ui-state-default ui-th-column">Loại tiền</td>
                <td align="center" class="ui-state-default ui-th-column">Hạn mức</td>
                <td align="center" class="ui-state-default ui-th-column">Số dư</td>
                <td align="center" class="ui-state-default ui-th-column">Số cần QT bù</td>
              </tr>
              <c:if test="${! empty(requestScope.listQToanBu)}">
              <c:forEach var="item" items="${requestScope.listQToanBu}">
              <tr>
                <td align="center"><c:out value="${item.shkb}"/></td>
                <td align="left"><c:out value="${item.ten_kb}"/></td>
                <td align="center"><c:out value="${item.ma_nh}"/></td>
                <td align="left"><c:out value="${item.so_tk}"/></td>
                <td align="center"><c:out value="${item.loai_tien}"/></td>
                <td align="right"><c:out value="${item.han_muc_no}"/></td>
                <td align="right"><c:out value="${item.so_du}"/></td>  
                <td align="right"><c:out value="${item.qtoan_bu_chi}"/></td>
              </tr>
              </c:forEach>
              </c:if>         
        </table>
    </div>
    
    
    </td>
    </tr>
    </table>
      
    
</html:form>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
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
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
      var kb_id = document.forms[0].nhkb_tinh.value;

      var kb_huyen = "<%=kb_huyen%>";
      var strTinh = "<%=strTinh%>";
      if (kb_id == null || "" == kb_id) {
          document.getElementById('ngan_hang').options.length = 1;
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
                                  getTenNHang();
                              }
                          }
                          else if (strTinh != null && strTinh != '') {
                              if (document.getElementById('nhkb_huyen').options.length == 2) {
                                  // select dong thu 2 neu select box co 2 value voi user cap tinh
                                  jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                                  getTenNHang();
                              }
                              else if (kb_huyen == '0' || kb_huyen == null || '' == kb_huyen) {
                                  jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                                  getTenNHang();
                              }
                              else if (kb_huyen != '0') {
                                  jQuery('#nhkb_huyen option:eq(' + kb_huyen + ')').attr('selected', true);
                                  getTenNHang();
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
                            getTenNHang(id)
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                              getTenNHang(id)
                         }
                        else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                        getTenNHang(id)
                        }
                        else if(kb_huyen!='0'){
                            jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                            getTenNHang(id)
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
      
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>