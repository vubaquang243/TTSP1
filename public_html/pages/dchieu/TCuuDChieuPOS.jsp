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
 //String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
//  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
  String idxTT = request.getAttribute("idxTT")==null?"":request.getAttribute("idxTT").toString();
  //String sum_lai = request.getAttribute("sum_lai")==null?"":request.getAttribute("sum_lai").toString();
  //String sum_sdu = request.getAttribute("sum_sdu")==null?"":request.getAttribute("sum_sdu").toString();
%>

<div class="app_error">
  <html:errors/>
</div>


<div class="box_common_conten">
  <html:form action="tcuuDChieuPOS.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Tra cứu đối chiếu POS</span>
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
                  Ng&#226;n h&#224;ng &nbsp;
                </td>
                <td width="22%">
                   <html:select property="ngan_hang" styleId="ngan_hang" style="font-size:12px;width:100%" onchange="nhangval();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                      
                  </html:select>
                 
                </td>
                <td  align="right" width="25%" style="padding-right:20px">
                <button type="button" style="width:50%" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                  <span class="sortKey">D</span>anh m&#7909;c KB
          </button>&nbsp;&nbsp;
                 <button type="button" onclick="check('find','');" class="ButtonCommon" accesskey="x" >
                      <span class="sortKey">T</span>ra cứu
                 </button>
               </td>              
              </tr> 
              <tr>
              <td align="right" bordercolor="#e1e1e1">
                  
                   <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                  </td>
                  <td >
                   <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:100%" onchange="nhkb_huyenval();getTenNHang();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                             
                  </html:select>
                    
                </td>
                 <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.lable.ngaydc"/>&nbsp;
                  </td>
                  <td >
                      <html:text property="ngayDC" styleId="ngayDC" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:155px"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_dc_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngayDC", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_dc_btn"// id of the button
                      });
                    </script>                     
                </td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td align="right">
                    <fmt:message key="doi_chieu.page.lable.tthai"/>&nbsp;đối chiếu&nbsp;
                </td>
                <td>
                    <html:select property="trangThaiDC" styleId="trangThaiDC" style="font-size:12px;width:100%" 
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>  
                              <html:option value="00">Chưa đối chiếu</html:option> 
                              <html:option value="01">Chênh lệch</html:option> 
                              <html:option value="02">Khớp đúng</html:option> 
                  </html:select>
                </td>
                <td align="right">
                    Loại tiền
                </td>
                <td>
                    <html:select property="loaiTien" styleId="loaiTien" style="font-size:12px;width:100%" 
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                        <html:option value="VND">VND</html:option>                          
                    </html:select>
                  </td>
                  <td>
                                  
                  </td>
              </tr>
              <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      
      <logic:present name="lisBangKe">
        <logic:notEmpty name="lisBangKe">
      <tr>
         <td colspan="5">
          <fieldset>
          <legend><font color="Blue">Kết quả tra cứu</font></legend>
           
            <table  class="data-grid" id="data-grid" 
                                                border="1"
                                               cellspacing="0" cellpadding="0"                                  
                                               width="100%">

                  <tr>
                    <th width="3%">
                      STT
                    </th>
                    <th width="30%">
                     Kho bạc 
                    </th>
                    <th width="10%">
                      Ngân hàng
                    </th>
                    <th width="13%">
                     Ngày đối chiếu
                    </th>
                    <th width="14%">
                      Trạng thái đối chiếu
                    </th> 
                    <th width="10%">
                      Thu POS từ TCS
                    </th> 
                    <th width="10%">
                      Thu POS từ NH
                    </th> 
                     <th width="10%">
                      Chi tiết
                    </th> 
                  </tr>
                 
                   
                        <logic:iterate id="items" name="lisBangKe" indexId="stt">
                         <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                            <td align="center" > 
                              <%=stt+1+rowBegin%>
                            </td>
                            <td align="left">
                              <bean:write name="items" property="ten"/>
                            </td>                            
                            <td align="center">
                              <bean:write name="items" property="bi_danh"/>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="ngay_bk"/>
                            </td>                           
                            <td align="center">  
                              <bean:write name="items" property="trang_thai"/>                             
                            </td>
                           <td align="right">
                              <bean:write name="items" property="tong_pos"/>                              
                            </td> 
                            <td align="right">
                              <bean:write name="items" property="tong_qtoan"/>
                            </td> 
                            <td align="center">
                             <button type="button" onclick="check('detail','<bean:write name="items" property="mt_id"/>','<bean:write name="items" property="id"/>');" class="ButtonCommon" accesskey="x" >
                                Chi tiết
                             </button>
                            </td>
                         </tr>
                        </logic:iterate>
                        <tr>
                            <td colspan="6" align="right">                                                          
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                        </tr>
                                  
              </table>
           </fieldset>
          </td>
      </tr>
    </logic:notEmpty>
  </logic:present>     
           </table>
        
        
        </div>
        </fieldset>
       </td>
      </tr>
      
  </table>
   
   
   <input type="hidden" name ="idbk"/>
 <html:hidden property="pageNumber" styleId="pageNumber"/>
  <input type="hidden" name ="idxKB"/>
   <input type="hidden" name ="idxNH"/>
   <input type="hidden" name ="idxTT"/>
   <input type="hidden" name ="ma_nh"/>
   <input type="hidden" name ="ma_kb"/>
   <input type="hidden" name ="bkid"/>
   <input type="hidden" name ="id"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

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
getloadTTe();
function getloadTTe(){
 
 var idxTT = '<%=idxTT%>'; 
  if(idxTT!='')
  jQuery('#loaiTien').index() = idxTT;
  
}
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
                          getTenNHang(id);
                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                          getTenNHang(id);
                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          getTenNHang(id);
                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                          getTenNHang(id);
                      }
                    }
                }
            }
              if (id!=null && ''!=id ){                        
                  jQuery('#nhkb_huyen').val(id);
                   getTenNHang(id);
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
     
      var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
       var strTinh="<%=qthttw%>";
       var ngan_hang="<%=idxNH%>";

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
                            //getTK_NH_KB()
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                            //  getTK_NH_KB();
//                              jQuery("#ngan_hang").attr("disabled", "disabled");
                            }
                        else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                       // getTK_NH_KB()
                        }
                        else if(ngan_hang!='0'){
                        jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
                       // getTK_NH_KB()
                        }
                      }
                  }  
                   if(ngan_hang != null && ngan_hang != '')
                  jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
  
  
  function check(type,mtid,makb) { 
   var f = document.forms[0];
   f.target='';
     if (type == 'find') {
        if(!validForm())
          return false;
        var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
         var idxTT = jQuery('#loaiTien option:selected').index() ;
        f.pageNumber.value = 1;
        document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;
        document.getElementsByName('idxTT')[0].value = idxTT;
        f.action = 'viewDChieuPOS.do'; 
     }
     if (type == 'detail') {
      if(!validForm())
          return false;
        var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
         var idxTT = jQuery('#loaiTien option:selected').index() ;
        f.pageNumber.value = 1;
        
        document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;
        document.getElementsByName('idxTT')[0].value = idxTT;
        document.getElementsByName('bkid')[0].value = mtid;  
        document.getElementsByName('id')[0].value = makb;
        //f.target='_form';
        f.action = 'chitietDChieuPOS.do';         
     }
     if (type == 'close') {
        f.action = 'mainAction.do'; 
     } 
     f.submit();
  }
  
  function validForm(){
    var nhkb_tinh=jQuery("#nhkb_tinh").val();
    var nhkb_huyen=jQuery("#nhkb_huyen").val();
    var ngan_hang=jQuery("#ngan_hang").val();
    var ngayDC=jQuery("#ngayDC").val();
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
      alert('Chọn ngân hàng.');
      ngan_hang.focus();
      return false;
    }
     if(ngayDC == null || ngayDC == ""){
      alert('Chưa nhập ngày đối chiếu.');
      ngayDC.focus();
      return false;
    }
    return true;
  }
  function goPage(page) {
  var f = document.forms[0];
      f.pageNumber.value = page;
      var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
         document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;
      f.action = 'viewDChieuPOS.do';
      f.submit();
  }
  </script>