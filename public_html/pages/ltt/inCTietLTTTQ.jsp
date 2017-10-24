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
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String TTTT = request.getAttribute("TTTT")==null?"":request.getAttribute("TTTT").toString();
  

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
  <html:form action="listCTLTTTQ.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Bảng kê chi tiết LTT toàn quốc</span>
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
          <legend><font color="blue">&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m </font></legend>
          <div>
            <table  class="data-grid" id="data-grid" 
                                                style="width:100%" border="0"
                                               cellspacing="0" cellpadding="1" >
                <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc tỉnh
                  </td>
                  <td width="20%">
                    <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <%if(request.getAttribute("TTTT") != null){
                           %>
                                <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                  
                            <%}%>
                        <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                    </html:select>
                    </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc huyện
                  </td>
                  <td width="30%">
                    <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="nhkb_huyenval();"
                                  onkeydown="if(event.keyCode==13) event.keyCode=9;">                               
                                <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                                
                  </html:select>
                  </td>
                    
                  <td  align="center" rowspan="3" width="15%">
                    <%if(request.getAttribute("TTTT") != null){
                    %>
                    <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="d" >
                            <span class="sortKey">D</span>anh m&#7909;c KB
                    </button> <p/>
                    <%}%>
                    <button type="button" onclick="check('print');" class="ButtonCommon" accesskey="i" >
                            <span class="sortKey">I</span>n
                    </button>               
  
                 </td>
                </tr>
                <tr>
                  <td align="right" bordercolor="#e1e1e1">
                      <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                    </td>
                    <td >
                      <html:select property="ma_dv" styleId="ngan_hang" onchange="nhangval()"
                               style="width: 80%;font-size:12px"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="" >-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                      <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                  </html:select>
                  </td> 
                  <td align="right">
                    Loại QT
                  </td>
                  <td>
                    <html:select property="loai_qtoan" styleId="loai_qtoan" onchange="loai_qtVal()"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n lo&#7841;i QT---</html:option>
                      <html:option value="D">Thu</html:option>
                      <html:option value="C">Chi</html:option>
                  </html:select>                 
                  </td>
                </tr>              
                <tr>
                            
                    <td   align="right">
                     Từ ngày
                  </td>
                  <td>
                  <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                          onkeypress="return numbersonly(this,event,true) "
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
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
                    
                    <td   align="right">
                     Đến ngày
                  </td>
                  <td>
                  <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                          onkeypress="return numbersonly(this,event,true) "
                         onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
                         tabindex="107" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                       border="0" id="den_ngay_btn"
                       style="vertical-align:middle;width:20"/>   
                      <script type="text/javascript">
                        Calendar.setup( {
                            inputField : "den_ngay", // id of the input field
                            ifFormat : "%d/%m/%Y", // the date format
                            button : "den_ngay_btn"// id of the button
                        });
                      </script> &nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">Loại tiền</td>
                  <td>
                      <html:select property="loai_tien" styleId="loai_tien" style="width:74px">
                        <html:optionsCollection value="ma" label="ma" name="tienTe"/>
                      </html:select>
                  </td>
                   <td width="15%" align="right" bordercolor="#e1e1e1">Trạng thái</td>
                  <td>
                      <html:select  style="font-size:12px;width:80%"
                                    property="qtoan_dvi" styleId="qtoan_dvi"> 
                        <html:option value="">Tất cả</html:option>
                        <html:option value="Y">Đã quyết toán</html:option>
                        <html:option value="N">Chưa quyết toán</html:option>
                    </html:select>
                  </td>
                </tr>
             </table>
           </div>
          </fieldset>
       </td>
      </tr>
      <tr>
 </table>
    <html:hidden property="pageNumber" value="1"/>
    <html:hidden property="nhkb_huyen" styleId="a"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

  var f = document.forms[0];
  function check(type) {  
     if (type == 'print') {
        tu_ngay= jQuery('#tu_ngay').val();
        den_ngay= jQuery('#den_ngay').val();
        if(tu_ngay.trim()==null || ""==tu_ngay.trim()){
          alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;ng tin ng&#224;y quy&#7871;t to&#225;n'));
          jQuery('#tu_ngay').focus();
          return false;
        }
        if(den_ngay.trim()==null || ""==den_ngay.trim()){
          alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;ng tin ng&#224;y quy&#7871;t to&#225;n'));
          jQuery('#den_ngay').focus();
          return false;
        }
        else{
          f.action = 'printCTLTTTQ.do'; 
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);  
          f.target='_form';
        }
      }  
 if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
   function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=  document.getElementById("nhkb_huyen").value;     
      return nhkb_huyen;
  }
  function nhangval() {
      var ngan_hang;
      ngan_hang=document.getElementById("ngan_hang").value;
      return ngan_hang;
  }

  function loai_qtVal() {
      var loai_qt;
      loai_qt=document.getElementById("loai_qtoan").value;
      return loai_qt;
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
    var TTTT="<%=TTTT%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     if(TTTT!=null && ''!=TTTT){
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
       }else if(TTTT==null || ''==TTTT){
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

</script>