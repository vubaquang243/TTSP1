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
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>

<%
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
%>

<div class="app_error">
  <html:errors/>
</div>


<div class="box_common_conten">
  <html:form action="printBKeGDichTCongAction.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Bảng kê giao dịch thủ công</span>
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
                <td width="22%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:250px"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                
                           <%if(request.getAttribute("QTHTTW") != null){
                           %>
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                          <%}%>
                   
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  Ng&#226;n h&#224;ng &nbsp;
                </td>
                <td width="48%">
                   <html:select property="ngan_hang" styleId="ngan_hang" style="font-size:12px;width:250px" onchange="nhangval();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                      <html:optionsCollection  name="dmucnh" value="ma_dv" label="ten_nh"/> 
                  </html:select>
                 
                </td>
                          
              </tr> 
              <tr>
              <td align="right" bordercolor="#e1e1e1">
                  
                   <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                  </td>
                  <td >
                   <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:250px" onchange="nhkb_huyenval();getTenNHang();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                     <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                             
                  </html:select>
                    
                </td>
                 <td align="right">
                    Loại tiền
                </td>
                <td>
                    <html:select property="loaiTien" styleId="loaiTien" style="font-size:12px;width:250px" 
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                        <html:optionsCollection name="dmuctiente" value="ma" label="ma"/>                      
                    </html:select>
                  </td> 
              </tr>
              <tr>
               
                 <td align="right" bordercolor="#e1e1e1">
                    Từ ngày&nbsp;
                  </td>
                  <td >
                      <html:text property="tuNgay" styleId="tuNgay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tuNgay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:155px"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tuNgay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_btn"// id of the button
                      });
                    </script>                     
                </td>
                <td align="right" bordercolor="#e1e1e1">
                    Đến ngày&nbsp;
                  </td>
                  <td >
                      <html:text property="denNgay" styleId="denNgay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('denNgay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:155px"
                       tabindex="108" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "denNgay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>                     
                </td>                 
              </tr>  
              <tr>
              <td  align="center"  colspan="4">
               <!-- <button type="button" style="width:50%" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                  <span class="sortKey">D</span>anh m&#7909;c KB
          </button>&nbsp;&nbsp;-->
          &nbsp;
                 <button type="button" onclick="check('print','');" class="ButtonCommon" accesskey="x" >
                      <span class="sortKey">I</span>n
                 </button>
               </td>  
              </tr>
           </table>
        
        
        </div>
        </fieldset>
       </td>
      </tr>
      
  </table>

   <input type="hidden" name ="ma_nh"/>
   <input type="hidden" name ="ma_kb"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
//Load form danh muc kho bac
 function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }
     jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });

getTenKhoBacDC('','');

//Lay gia tri cua combobox nhkb 
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
  
//Lay ten kho bac 
function getTenKhoBacDC(id,id_cha) { 
    var TTTT="<%=qthttw%>";
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
                        }
                    }else if(strTinh!=null && strTinh !=''){                   
                       if(!document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);                         
                       }
                    }
                }
            }
              if (id!=null && ''!=id ){                        
                  jQuery('#nhkb_huyen').val(id);
                  
              }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}

//Lay ten ngan hang
  function getTenNHang() {
      document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu     
      var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
       var strTinh="<%=qthttw%>";

      if (nhkb_id !=null && ""!=nhkb_id){
     jQuery.ajax( {
          type : "POST", url : "getDMucNHangHuyenDC.do", data :  {
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
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#ngan_hang option:eq(1)").attr('selected', true);                         
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
  
  //function cho button 
  function check(type,mtid,makb) { 
   var f = document.forms[0];
   f.target='';
     if (type == 'print') {
//        if(!validForm()){
//          return false;      
//        }
//        else {
       f.target='_form';
        f.action = 'printBKeGDichTCongAction.do'; 
      //  }
     }
    
     if (type == 'close') {
        f.action = 'mainAction.do'; 
     } 
     f.submit();
  }
  
  //Kiem tra gia tri dau phu hop voi yeu cau
  function validForm(){
    var nhkb_tinh=jQuery("#nhkb_tinh").val();
    var nhkb_huyen=jQuery("#nhkb_huyen").val();
    var ngan_hang=jQuery("#ngan_hang").val();
    var tuNgay=jQuery("#tuNgay").val();
    var denNgay = jQuery("#denNgay").val();
    if(nhkb_tinh == null || nhkb_tinh == ""){
      alert('Chọn kho bạc tỉnh.');
      nhkb_tinh.focus();
      return false;
    }
     if(nhkb_huyen == null || nhkb_huyen == ""){
      alert('Chọn kho bạc huyện.');
      nhkb_huyen.focus();
      return false;
    }
     if(ngan_hang == null || ngan_hang == ""){
      alert('Chọn kho ngân hàng.');
      ngan_hang.focus();
      return false;
    }
     if(tuNgay == null || tuNgay == ""){
      alert('Chưa nhập ngày bắt đầu.');
      tuNgay.focus();
      return false;
    }
    if(denNgay == null || denNgay == ""){
      alert('Chưa nhập ngày kết thúc.');
      den_ngay.focus();
      return false;
    }
    return true;
  }
  
  </script>